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
                13ª Categoria    2 + 0
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
                1ª Categoria      5 + 2


Correciones:
-Si no se ingresa un numero en el rango permitido vuelve a pedir ese mismo numero
-Con do-while se muestran los menus y con switch la operatividad
-Externalizamos funciones que se repiten en el programa
-Ordenamos el numero y las estrellas de los boletos crecientemente 
-No se puede escribor el nombre de un array "boletoApuesta[]" sin numero para mostrar su contenido, usar Arrays.toString(boletoApuesta) 

Errores:
-En boleto manual si introduciomos una letra en vez de un numero el programa se rompe, control de errores?
-Hay que añadir que no se pueden repetir los numeros ni las estrellas
-Cuando buscando otros numeros premiados cuenta el mismo numero aunque ya ha sido contado como premiado
    Resultado:  	5+0
    Tu boleto:  	[1, 1, 1, 1, 1, 1, 1]
    El premiado: 	[1, 8, 15, 23, 47, 6, 11]
-


 */
package eurmillones;


import com.sun.xml.internal.ws.message.FaultMessage;
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
//import java

/**
 *
 * @author manuelbaceiredo
 * @license MIT License 
 * @date 03/12/2020
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
        boolean memo[]={true, true, true, true, true, true, true};
        
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
                                boletoApuesta[i] = (int) (Math.random() * 51) + 1; //  (<tipo_de_dato>) (Math.random() * <numero_maximo_intervalo_cerrado>) + <valor_inicial=0_intervalo_abierto>;
                            }else{
                                boletoApuesta[i] = (int) (Math.random() * 13) + 1;
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
                                }while(!intervalocheck(boletoApuesta[i], 1, 50)); // intervalocheck(); x: numero a analizar, y: numero minimo incluido, z: numero maximo incluido
                            }else{
                                do{
                                    System.out.print("Ingrese la "+(i-4)+"º estrella (1, 12)\n>>> ");
                                    boletoApuesta[i] = teclado.nextInt();
                                }while(!intervalocheck(boletoApuesta[i], 1, 12));
                            }
                        }  
                break;
                default: metodo=true;
            }
        } while (metodo);
        
        // Ordenamos ASCendentemente y mostramos el boleto elegido
        boletoApuesta = ordenar(boletoApuesta);
        System.out.println("Su boleto: "+Arrays.toString(boletoApuesta)+"\n");
        
        int nIteracion=0;
        int nAciertos,  nEstrellas;
       do{
                nAciertos=0;
                nEstrellas=0;
                for (int i=0;i<memo.length;i++){
                    memo[i] = true;
                }
                nIteracion++;     
                // Asignamos aleatoriamente numeros a boletoPremiado
                for (int i=0;i<7;i++){ // (0 , 6)
                    if(i < 5){
                        boletoPremiado[i] = (int) (Math.random() * 51) + 1;
                    }else{
                        boletoPremiado[i] = (int) (Math.random() * 13) + 1;
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
                                if (memo[i]){
                                    nAciertos++;
                                    memo[i]=false;
                                    //LOGS: System.out.println("Numero acertado: "+boletoApuesta[j]);
                                }
                            }
                        }
                        else if(j>=5 && i>=5){ // (5.5 , 5.6) U (6.5 , 6.6)
                            if(boletoApuesta[j]==boletoPremiado[i]){
                                if (memo[i]){
                                    nEstrellas++;
                                    memo[i]=false;
                                    //LOGS: System.out.println("Estrella acertada: "+boletoApuesta[j]);
                                }
                            }
                        }
                        //Nothing (5.0 , 5.4) U (6.0 , 6.4)
                        
                        //LOGS: System.out.println("SCAN: \tj:"+j+"="+boletoApuesta[j]+"\ti:"+i+"="+boletoPremiado[i]+"\t"+memo[i]);
                        /*
                        
Numero acertado: 3
Numero acertado: 50
Numero acertado: 50
Intento: 	nº51
Resultado: 	3+0
Inversion:	127.5 Eur
Tu boleto: 	[3, 35, 35, 49, 50, 2, 8]
El premiado: 	[3, 19, 32, 50, 50, 9, 9]
                        
                        
                        Que no se puedan repetir los numeros
                        
                        */
                    }
                }
                // Mostramos el numero de intento, la inversión total, el numero de boleto y el boleto premiado
                System.out.println("Intento: \tnº"+nIteracion);
                System.out.println("Resultado: \t"+nAciertos+"+"+nEstrellas);
                System.out.println("Inversion:\t"+(2.5*nIteracion)+" Eur"); // Precio del boleto 2,5€
                System.out.println("Tu boleto: \t"+Arrays.toString(boletoApuesta));
                System.out.println("El premiado: \t"+Arrays.toString(boletoPremiado)+"\n");
       }while(nAciertos <= 2 && nEstrellas >= 0);  // TRUE continua, FALSE termina
       
       System.out.println("\nFELICIDADES!! ");
    }
    
    
    
    //Usamos el metodo para saber si n es x<=n<=y
    public static boolean intervalocheck(int n, int x, int y) {
        boolean rango=false;
        if (n >= x && n <= y){
            rango = true;
        }
        return rango;
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
    
    
}
