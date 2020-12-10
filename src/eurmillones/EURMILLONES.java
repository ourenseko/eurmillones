/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


    Resumen de los conocimientos obtenidos en Java

        EUROMILLONES SIMULATOR @ JAVA TERMINAL
            Reglas del juego:
            Hay que elegir numeros ente 1 y 50 y estrellas entre 1 y 12, los numeros no se pueden repetir.
                5 Numeros + 2 Estrellas
            Sorteos:       

            Premios:
                13ª Categoria           2 + 0
                                        2 + 1
                                        1 + 2
                                        3 + 0
                                        3 + 1
                                        2 + 2
                                        4 + 0
                                        3 + 1
                                        4 + 0
                                        3 + 2
                                        4 + 1
                                        4 + 2
                                        5 + 0
                                        5 + 1
                1ª Categoria            5 + 2

Notas:
-Se ha optado por verificar que el usuario introduce datos correctos en el momento que los introduce y no una vez compuesto el boleto.
-Con do-while se muestran los menus y con switch la operatividad del usuario
-en Python boletoApuesta[] ==> en Java Arrays.toString(boletoApuesta) 
-Sobra la correción en la que hacemos que no sume numeros ya acertados (memo[]). Si no se pueden repetir los numeros, esto ya no puede ocurrir. 


Correciones:
-Si no se ingresa un numero en el rango permitido vuelve a pedir el numero
-Externalizamos funciones que se repiten en el programa
-Ordenamos el numero y las estrellas de los boletos crecientemente 
-Si se ingrersa un numero repetido vuelve a pedir el numero 

Errores:
-En boleto manual si introduciomos una letra en vez de un numero el programa se rompe. Establecer control de errores
-Al intoducir el tipo de boleto, si escribimos un texto superior a lo aceptable por el tipo de dato el programa se rompe. Establecer control de errores
-Al intoducir numeros y estrellas, si escribimos un numero superior a lo aceptable por el tipo de dato el programa se rompe. Establecer control de errores

 */
package eurmillones;

import java.lang.Math; // Usamos random
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author manuelbaceiredo
 * @license MIT License 
 * @date 03/12/2020 - 09/12/2020
 * @version v1.0 (Terminal)
 *  
 */
public class EURMILLONES {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Make with Java - @soyprogramador.gif\n");
        System.out.println("\tEUROMILLONES SIMULATOR!!\n\t------------------------\n");
        
        int[] boletoApuesta = new int[7]; 
        int[] boletoPremiado = new int[7];
        //boolean[] memo = new boolean[7]; // todos los valores true
        boolean memo[]={false, false, false, false, false, false, false};
        
        boolean metodo=false;
        do {
            // Pedimos al usuario un metodo de boleto
            System.out.print("Boleto Automatico/Manual [A/M]\n>>> ");
            Scanner teclado = new Scanner(System.in);
            String modo = teclado.next();
            switch(modo){
                case "A": case "a": case "0": case ".":
                        // Boleto aleatorio
                        for (int i=0;i<7;i++){ // 0 1 2 3 4 5 6
                            if(i < 5){
                                do{
                                    boletoApuesta[i] = (int) (Math.random() * 51) + 1; //  (<tipo_de_dato>) (Math.random() * <numero_maximo_intervalo_cerrado>) + <valor_inicial=0_intervalo_abierto>;
                                }while(numeroCheck(boletoApuesta, boletoApuesta[i], i));
                            }else{
                                do{
                                    boletoApuesta[i] = (int) (Math.random() * 13) + 1;
                                }while(numeroCheck(boletoApuesta, boletoApuesta[i], i));
                            }
                        }
                break;
                case "M": case "m": case "1":
                        // Boleto definido por el usuario
                        for (int i=0;i<7;i++){ // 0 1 2 3 4 5 6
                            if(i < 5){
                                do{
                                    System.out.print("Ingrese el "+(i+1)+"º digito (1, 50)\n>>> ");
                                    boletoApuesta[i] = teclado.nextInt();
                                    

                                }while(!intervaloCheck(boletoApuesta[i], 1, 50) || numeroCheck(boletoApuesta, boletoApuesta[i], i) ); // 1<=x<=50 && ∃x∈ boletoApuesta  
                                
                            }else{
                                do{
                                    System.out.print("Ingrese la "+(i-4)+"º estrella (1, 12)\n>>> ");
                                    boletoApuesta[i] = teclado.nextInt();
                                }while(!intervaloCheck(boletoApuesta[i], 1, 12) || numeroCheck(boletoApuesta, boletoApuesta[i], i) ); 
                            }
                        }  
                break;
                default: metodo=true;
            }
        } while (metodo);
        
        // Ordenamos ASCendentemente y mostramos el boleto elegido
        boletoApuesta = ordenar(boletoApuesta);
        System.out.println("Su boleto: "+Arrays.toString(boletoApuesta)+"\n");
        
// Pedimos al usuario el tipo de premio que queremos buscar
        System.out.println("Categoría de premios:\n" +
        "1ª \t(5 + 2 Aciertos)\n" +
        "2ª \t(5 + 1 Aciertos)\n" +
        "3ª \t(5 + 0 Aciertos)\n" +
        "4ª \t(4 + 2 Aciertos)\n" +
        "5ª \t(4 + 1 Aciertos)\n" +
        "6ª \t(4 + 0 Aciertos)\n" +
        "7ª \t(3 + 2 Aciertos)\n" +
        "8ª \t(2 + 2 Aciertos)\n" +
        "9ª \t(3 + 1 Aciertos)\n" +
        "10ª \t(3 + 0 Aciertos)\n" +
        "11ª \t(1 + 2 Aciertos)\n" +
        "12ª \t(2 + 1 Aciertos)\n" +
        "13ª \t(2 + 0 Aciertos)");
        
        
        int aciertosNumeros, aciertosEstrellas;
        /*
        do{
            System.out.println("Selecione aciertos en los numeros: (1, 5): ");
            aciertosNumeros = teclado.nextInt();
        }while(!intervaloCheck(aciertosNumeros, 1, 5));
        do{
            System.out.println("Selecione aciertos en las estrellas: (1, 5): ");
            aciertosEstrellas = teclado.nextInt();
        }while(!intervaloCheck(aciertosEstrellas, 1, 5));
        */
                
        int aciertosCategoria=0;
        do{
            System.out.print("Selecione categoría: (1, 13): \n>>> ");
            aciertosCategoria = teclado.nextInt();
        }while(!intervaloCheck(aciertosCategoria, 1, 13));
        
        switch (aciertosCategoria) {
                case 1:
                    aciertosNumeros=5;
                    aciertosEstrellas=2;
                break;
                case 2:
                    aciertosNumeros=5;
                    aciertosEstrellas=1;
                break;
                case 3:
                    aciertosNumeros=5;
                    aciertosEstrellas=0;
                break;
                case 4:
                    aciertosNumeros=4;
                    aciertosEstrellas=2;
                break;       
                case 5:
                    aciertosNumeros=4;
                    aciertosEstrellas=1;
                break;         
                case 6:
                    aciertosNumeros=4;
                    aciertosEstrellas=0;
                break;                
                case 7:
                    aciertosNumeros=3;
                    aciertosEstrellas=2;
                break;                
                case 8:
                    aciertosNumeros=2;
                    aciertosEstrellas=2;
                break;                
                case 9:
                    aciertosNumeros=3;
                    aciertosEstrellas=1;
                break;                
                case 10:
                    aciertosNumeros=3;
                    aciertosEstrellas=0;
                break;                
                case 11:
                    aciertosNumeros=1;
                    aciertosEstrellas=2;
                break;                
                case 12:
                    aciertosNumeros=2;
                    aciertosEstrellas=1;
                break;          
                case 13:
                    aciertosNumeros=2;
                    aciertosEstrellas=0;
                break;         
                default:
                    aciertosNumeros=1;
                    aciertosEstrellas=0;
        }
        
        long nIteracion=0;
        int nAciertos,  nEstrellas;
       do{
                nAciertos=0;
                nEstrellas=0;
                for (int i=0;i<memo.length;i++){
                    memo[i] = false;
                }
                nIteracion++;     
                // Asignamos aleatoriamente numeros a boletoPremiado
                for (int i=0;i<7;i++){ // (0 , 6)
                    if(i < 5){
                        do{
                            boletoPremiado[i] = (int) (Math.random() * 51) + 1;
                        }while(numeroCheck(boletoPremiado, boletoPremiado[i], i));
                    }else{
                        do{
                            boletoPremiado[i] = (int) (Math.random() * 13) + 1;
                        }while(numeroCheck(boletoPremiado, boletoPremiado[i], i));   
                    }
                }
                boletoPremiado = ordenar(boletoPremiado);
                
                //Aciertos en boletoApuesta y boletoEstrellas
                // Obtiene un numero de boletoApuesta y escanea todas las posiciones del boletoPremio en busca de si coinciden 
                //  Anotamos la posicion de los numeros que ya han sido acertados para que no se repitan
                     
                for (int j=0;j<boletoPremiado.length;j++){ 
                    for (int i=0;i<boletoPremiado.length;i++){
                        if (j<5 && i<5){ // (0.0 , 0.4) U (1.0 , 1.4) U (2.0 , 2.4) U (3.0 , 3.4) U (4.0 , 4.4)
                            if(boletoApuesta[j]==boletoPremiado[i]) {
                                if (!memo[i]){ // true numero ya acertado
                                    nAciertos++;
                                    memo[i]=true;
                                    //System.out.println("Numero acertado: "+boletoApuesta[j]);
                                }
                            }
                        }
                        else if(j>=5 && i>=5){ // (5.5 , 5.6) U (6.5 , 6.6)
                            if(boletoApuesta[j]==boletoPremiado[i]){
                                if (!memo[i]){
                                    nEstrellas++;
                                    memo[i]=true;
                                    //System.out.println("Estrella acertada: "+boletoApuesta[j]);
                                }
                            }
                        }
                        //Nothing (5.0 , 5.4) U (6.0 , 6.4)
                        //LOGs: System.out.println("SCAN: \tj:"+j+"="+boletoApuesta[j]+"\ti:"+i+"="+boletoPremiado[i]+"\t"+memo[i]);
                    }
                }
                // Mostramos el numero de intento, la inversión total, el numero de boleto y el boleto premiado
                System.out.println("Intento: \tnº"+nIteracion);
                System.out.println("Resultado: \t"+nAciertos+"+"+nEstrellas);
                System.out.println("Inversion:\t"+(2.5*nIteracion)+" Eur"); // Precio del boleto 2,5€
                System.out.println("Tu boleto: \t"+Arrays.toString(boletoApuesta));
                System.out.println("El premiado: \t"+Arrays.toString(boletoPremiado)+"\n");
       }while(nAciertos < aciertosNumeros || nEstrellas < aciertosEstrellas);  // TRUE continua, FALSE termina; Categoria seleccionada o superior.
       // nAciertos < 5 || nEstrellas < 1  ==> Que salga un 5+1 o más
       System.out.println("\nFELICIDADES!! ");
    }
     
    
    //Usamos el metodo para saber si n es x<=n<=y
    public static boolean intervaloCheck(int n, int x, int y) {
        boolean intervalo=false;
        if (n >= x && n <= y){
            intervalo = true;
        }
        return intervalo;
    }

    
    //Usamos el metodo para ordenar los numeros arreglo[] crecientemente
    public static int[] ordenar(int[] arreglo){ 
        int memo;
        // burbujeamos las posiciones 0, 1, 2, 3 y 4
        for(int j=0;j<4;j++){
                for (int i=0;i<4;i++){ 
                    if (arreglo[i]>arreglo[i+1]){
                        memo=arreglo[i];
                        arreglo[i]=arreglo[i+1];
                        arreglo[i+1]=memo;
                    }
                }
        }
        // burbujeamos las posicones 5 y 6
        if (arreglo[5]>arreglo[6]){
            memo = arreglo[5];
            arreglo[5] = arreglo[6];
            arreglo[6] = memo;
        }
        return arreglo;
    } 
    
    
    //Usamos el metodo para saber si ∃ n ∈ arreglo
    public static boolean numeroCheck(int[] arreglo, int n, int arrayPosicion){ 
        boolean seRepite = false;
        for(int i=0;i<arreglo.length;i++){ 
            
            if (i>=0 && i<5 && arrayPosicion <5){
                if(n == arreglo[i] &&  i != arrayPosicion){ //Si n es igual a algun numero del array && Que las posicion del numero en el array es diferente a la del numero actual
                    //System.out.println(n+"=="+arreglo[i]+"\t"+i+"!="+arrayPosicion);
                    seRepite = true;
                }// numero-introducido / posicion-del-introducido / array[posicion-en-el-array]=numero-en-el-array / se-ha-cumplido?
                //System.out.println("SEARCH: n="+n+"\t position="+arrayPosicion+" array["+i+"]="+arreglo[i]+"\t"+seRepite);
            }
            else if(i>=5 && arrayPosicion >=5){
                if (arreglo[6]==arreglo[5]){
                    seRepite = true;
                }
            }
        }
        return seRepite;
    } 
    

}
