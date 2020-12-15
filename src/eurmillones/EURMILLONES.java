/*
EUROMILLONES SIMULATOR @ JAVA 
        Reglas del juego:
            Hay que elegir 5 numeros ente 1 y 50 y 2 estrellas entre 1 y 12, los numeros no se pueden repetir.
DESARROLLO:
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

import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
/**
 * @author manuelbaceiredo
 * @date 03/12/2020
 * @version v1.0 (Terminal)
 */
public class EURMILLONES {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Make with Java - @soyprogramador.gif\n");
        System.out.println("\tSIMULATOR DE LOTERIA!!\n\t----------------------\n");
        
        int boletoApuesta[] = {0, 0, 0, 0, 0, 0, 0};
        int[] boletoPremiado = new int[7];
        /*
        boolean memo[]={false, false, false, false, false, false, false};
        */
        Scanner teclado = new Scanner(System.in);
        
        
        int nJuegos = 2;
        System.out.println("[1] EUROMILLONES \n[2] LOTERIA NAVIDAD");
        
        
        boolean juego = false;
        int jugar;
        do{
            System.out.print("Elije un juego: \n>>> ");
            jugar = teclado.nextInt();
            
            if (jugar > 0 && jugar <=nJuegos){
                juego = true;
            }
        }while(!juego);
        
        jugar =0;
        int longitudBoleto=0;
        switch(jugar){
            case 1: /* EUROMILLONES*/
                longitudBoleto = 7;
            break;
            
            case 2:
                longitudBoleto = 5;
            break; /* LOTERIA NAVIDAD */
            
            default: 
                System.out.println("Juego no disponible ._.");
                System.exit(0);
        }
        
        

        boolean metodo=false;
        do {
            /* 
            Pedimos al usuario un metodo de boleto
            */
            System.out.print("Boleto Automatico? [S/N]\n>>> ");
            String modo = teclado.next();
            switch(modo){
                case "S": case "Y": case "Q": case "W": 
                case "s": case "y": case "q": case "w": 
                case "1": case "2": case "4": 
                case "    ": case "º":
                        /*
                        Funcion generar boleto con longitud variables
                    
                        Boleto aleatorio
                        */
                        //nuevoBoletoApuestaAuto(7); Para Euromillones
                        nuevoBoletoApuestaAuto(longitudBoleto);
                        
                break;
                case "N": case"I": case "P":
                case "n": case"i": case "p":
                case "0":  case "9": 
                case ".": case"'":  case "": case " ":
                        /*
                        Longitud del boleto segun el tipo de juego
                    
                        Boleto definido por el usuario
                        */
                        //nuevoBoletoApuestaManual(7); Para Euromillones
                        nuevoBoletoApuestaManual(longitudBoleto);
                    
                break;
                default: metodo=true;
            }
        } while (metodo);
        /*
        Ordenamos ASCendentemente y mostramos el boleto elegido
        */
        boletoApuesta = ordenarBoletoAsc(boletoApuesta);
        System.out.println("Su boleto: "+Arrays.toString(boletoApuesta)+"\n");
        
        

/*
        MOSTRAR SEGUN EL TIPO DE JUEG0
        Pedimos al usuario la categoría de premio a la que queremos apuntar
*/



        int aciertosNumeros=0;
        int aciertosEstrellas=0;
        
        switch(jugar){
            case 1:
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
                        
                        /* 
                        Es preferible pedir unicamente la categoria para pedir menos datos al usuario.

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
            break;
            case 2: //LOTERIA NAVIDAD
                
            break;
            default: 
                
        }
        
        /*

LOTERIA NAVIDAD

5 NUMEROS (0, 9)
170 SERIES  (PDEN SER MAS) DESVIACION 2

1 SERIE 20 EUROS
1 BILLETE 200 EUROS


*/
        
        long nIteracion=0;
        int nAciertos,  nEstrellas;
        int resultado[] = {0, 0};
        
        
        /*
            Declarar variables segun el tipo de juego
        
        
        
        */
        
       do{
                nAciertos=0;
                nEstrellas=0;
                nIteracion++;     
                nuevoBoletoPremio(7);
                boletoPremiado = ordenarBoletoAsc(boletoPremiado);
                
                resultado = compararBoletos(boletoPremiado, boletoApuesta, nAciertos, nEstrellas); // devuelve nAciertos y nEstrellas 
                
                /*
                Aciertos en boletoApuesta y boletoEstrellas
                Obtiene un numero de boletoApuesta y escanea todas las posiciones del boletoPremio en busca de si coinciden 
                Anotamos la posicion de los numeros que ya han sido acertados para que no los cuente si hay otro numero igual (Omitido, es inicesario al no poder repetirse)
                */

                /*
                Mostramos el numero de intento, la inversión total, el numero de boleto y el boleto premiado
                */
                System.out.println("Intento: \tnº"+nIteracion);
                System.out.println("Resultado: \t"+nAciertos+"+"+nEstrellas);
                System.out.println("Inversion:\t"+(2.5*nIteracion)+" Eur"); // Precio del boleto 2,5€
                System.out.println("Tu boleto: \t"+Arrays.toString(boletoApuesta));
                System.out.println("El premiado: \t"+Arrays.toString(boletoPremiado)+"\n");
       }while(resultado[0] < aciertosNumeros || resultado[1] < aciertosEstrellas);  
       /* 
       TRUE continua, FALSE termina; Categoria seleccionada o superior. 
            PARA EUROMILLONES: resultado[0] < aciertosNumeros || resultado[1] < aciertosEstrellas
            PARA LOTERIA NAVIDAD: 
       */
       /* 
       nAciertos < 5 || nEstrellas < 1  ==> Que salga un 5+1 o más 
       */
       System.out.println("\nFELICIDADES!! ");
    }
     
    
    /*
    Usamos el metodo para saber si n es x<=n<=y
    */
    public static boolean intervaloCheck(int n, int x, int y) {
        boolean intervalo=false;
        if (n >= x && n <= y){
            intervalo = true;
        }
        return intervalo;
    }

    
    /*
    Usamos el metodo para ordenarBoletoAsc los numeros arreglo[] crecientemente
    */
    public static int[] ordenarBoletoAsc(int[] arreglo){ 
        int memo;
        /* 
        burbujeamos las posiciones 0, 1, 2, 3 y 4 
        */
        for(int j=0;j<4;j++){
                for (int i=0;i<4;i++){ 
                    if (arreglo[i]>arreglo[i+1]){
                        memo=arreglo[i];
                        arreglo[i]=arreglo[i+1];
                        arreglo[i+1]=memo;
                    }
                }
        }
        /* 
        burbujeamos las posicones 5 y 6 
        */
        if (arreglo[5]>arreglo[6]){
            memo = arreglo[5];
            arreglo[5] = arreglo[6];
            arreglo[6] = memo;
        }
        return arreglo;
    } 
    
    
    /*
    Usamos el metodo para saber si Existe n Contenido en arreglo
    */
    public static boolean numeroCheck(int[] arreglo, int n, int arrayPosicion){ 
        boolean seRepite = false;
        for(int i=0;i<arreglo.length;i++){ 
            if (i>=0 && i<5 && arrayPosicion <5){
                if(n == arreglo[i] &&  i != arrayPosicion){ 
                    /*
                    Si n es igual a algun numero del array && Que las posicion del numero en el array es diferente a la del numero actual
                    System.out.println(n+"=="+arreglo[i]+"\t"+i+"!="+arrayPosicion);
                    */
                    seRepite = true;
                }
                /*
                numero-introducido / posicion-del-introducido / array[posicion-en-el-array]=numero-en-el-array / se-ha-cumplido?
                System.out.println("SEARCH: n="+n+"\t position="+arrayPosicion+" array["+i+"]="+arreglo[i]+"\t"+seRepite);
                */
            }
            else if(i>=5 && arrayPosicion >=5){
                if (arreglo[6]==arreglo[5]){
                    seRepite = true;
                }
            }
        }
        return seRepite;
    }
    
    
    /*
    Usamos el metodo nuevoBoletoApuestaAuto para generar un boleto automaticamente con el que juega el ususario (Esta funcion es prescindible si se usa la  generarBoleto y se asigna a la variable del usuario)
    */
    public static int[] nuevoBoletoApuestaAuto(int longitud){
                        int[] boletoApuesta = new int[longitud];
                        for (int i=0;i<7;i++){ 
                            if(i < 5){
                                do{
                                    boletoApuesta[i] = (int) (Math.random() * 51) + 1; 
                                    /*  
                                    (<tipo_de_dato>) (Math.random() * <numero_maximo_intervalo_cerrado>) + <valor_inicial=0_intervalo_abierto>;
                                    */
                                }while(numeroCheck(boletoApuesta, boletoApuesta[i], i));
                            }else{
                                do{
                                    boletoApuesta[i] = (int) (Math.random() * 13) + 1;
                                }while(numeroCheck(boletoApuesta, boletoApuesta[i], i));
                            }
                        }
                        

            
                        return boletoApuesta;
    }
    
    
     /*
    Usamos el metodo nuevoBoletoApuestaAuto para generar un boleto definido por el usuario con el que vamos a jugar.
    */
    public static int[] nuevoBoletoApuestaManual(int longitud){ 
                        int[] boletoApuesta = new int[longitud];

                        Scanner teclado = new Scanner(System.in);
                        for (int i=0;i<7;i++){ 
                            if(i < 5){
                                do{
                                    System.out.print("Ingrese el "+(i+1)+"º digito (1, 50)\n>>> ");
                                    boletoApuesta[i] = teclado.nextInt();
                                }while(!intervaloCheck(boletoApuesta[i], 1, 50) || numeroCheck(boletoApuesta, boletoApuesta[i], i) ); 
                                /*
                                1<=x<=50 && ∃x∈ boletoApuesta  
                                */
                            }else{
                                do{
                                    System.out.print("Ingrese la "+(i-4)+"º estrella (1, 12)\n>>> ");
                                    boletoApuesta[i] = teclado.nextInt();
                                }while(!intervaloCheck(boletoApuesta[i], 1, 12) || numeroCheck(boletoApuesta, boletoApuesta[i], i) ); 
                            }
                        }
            
                        return boletoApuesta;
    }
    
    
     /*
    Usamos el metodo nuevoBoletoPremio para generar un boleto automaticamente
    */
    public static int[] nuevoBoletoPremio(int longitud){ 
                        int[] boletoPremiado = new int[longitud];
                        
                        for (int i=0;i<longitud;i++){ 
                            if(i < 5){    //////////////////////////////// SOLO PARA EUROMILLONES
                                do{
                                    boletoPremiado[i] = (int) (Math.random() * 51) + 1;
                                }while(numeroCheck(boletoPremiado, boletoPremiado[i], i));
                            }else{
                                do{
                                    boletoPremiado[i] = (int) (Math.random() * 13) + 1;
                                }while(numeroCheck(boletoPremiado, boletoPremiado[i], i));   
                            }
                        }
            
                        return boletoPremiado;
    }
    
    
    
    public static int [] compararBoletos(int[] boletoPremiado, int[] boletoApuesta, int nAciertos, int nEstrellas) {
                for (int j=0;j<boletoPremiado.length;j++){
                    for (int i=0;i<boletoPremiado.length;i++){
                        if (j<5 && i<5){ 
                            /* 
                            (0.0 , 0.4) U (1.0 , 1.4) U (2.0 , 2.4) U (3.0 , 3.4) U (4.0 , 4.4)
                            */
                            if(boletoApuesta[j]==boletoPremiado[i]) {
                                    nAciertos++;
                            }
                        }
                        else if(j>=5 && i>=5){ // (5.5 , 5.6) U (6.5 , 6.6)
                            if(boletoApuesta[j]==boletoPremiado[i]){
                                    nEstrellas++;
                            }
                        }
                        /*
                        Nothing (5.0 , 5.4) U (6.0 , 6.4)
                        System.out.println("SCAN: \tj:"+j+"="+boletoApuesta[j]+"\ti:"+i+"="+boletoPremiado[i]+"\t"+memo[i]);
                        */
                    }
                }
                int resultado[] = {nAciertos, nEstrellas}; //Ejemplo: 4+0,  5+2
                return resultado;
    }
    
        

}
