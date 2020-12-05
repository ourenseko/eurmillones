/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.



    Resumen de los conocimientos obtenidos en Java

Correciones:
-Si no se ingresa un numero en el rango permitido vuelve a pedir ese mismo numero
-Con do-while se muestran los menus y con switch la operatividad

Errores:
-En boleto manual si introduciomos una letra en  vez de un numero el programa se rompe, control de errores?
-

 */
package eurmillones;

        
        /*
            Reglas del juego:
            Tienen que salir minimo dos numeros en su posición, los dos ultimos numeros son estrellas y no cuentan para el minimo
            2 + 0
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
            5 + 2
        */

import java.lang.Math;
import java.util.Scanner;

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
        
        int[] numeros = new int[7];
        int[] premio = new int[7];
        int aciertos=0;
        int estrellas=0;
        
        boolean metodo=false;
        do {
            // Pedimos al usuario un metodo de boleto
            System.out.print("Boleto Automatico/Manual [A/M]\n>>> ");
            Scanner teclado = new Scanner(System.in);
            String modo = teclado.next();
            switch(modo){
                case "A": case "a":
                        // Boleto aleatorio
                        for (int i=0;i<7;i++){ // 0 1 2 3 4 5 6
                            if(i < 5){
                                numeros[i] = (int) (Math.random() * 51) + 1; //  (<tipo_de_dato>) (Math.random() * <numero_maximo_intervalo_cerrado>) + <valor_inicial=0_intervalo_abierto>;
                            }else{
                                numeros[i] = (int) (Math.random() * 13) + 1;
                            }
                        }
                break;
                case "M": case "m":
                        // Boleto definido por el usuario
                        for (int i=0;i<7;i++){ // 0 1 2 3 4 5 6
                            if(i < 5){
                                do{
                                    System.out.print("Ingrese el "+(i+1)+"º digito (1, 50)\n>>> ");
                                    numeros[i] = teclado.nextInt();
                                }while(!intervalocheck(numeros[i], 1, 50)); // intervalocheck(); x: numero a analizar, y: numero minimo incluido, z: numero maximo incluido
                            }else{
                                do{
                                    System.out.print("Ingrese la "+(i-4)+"º estrella (1, 12)\n>>> ");
                                    numeros[i] = teclado.nextInt();
                                }while(!intervalocheck(numeros[i], 1, 12));
                            }
                        }

                        
                break;
                default: metodo=true;
            }
        } while (metodo);
 
        // Mostramos el boleto elegido
        System.out.println("Su boleto: "+numeros[0]+", "+numeros[1]+", "+numeros[2]+", "+numeros[3]+", "+numeros[4]+", "+numeros[5]+", "+numeros[6]+"\n");
        
        
       int iteracion=0;
       do{
                iteracion++;
                        
                // Asignamos aleatoriamente numeros al boleto premiado
                for (int i=0;i<7;i++){ // 0 1 2 3 4 5 6
                    if(i < 5){
                        premio[i] = (int) (Math.random() * 51) + 1;
                    }else{
                        premio[i] = (int) (Math.random() * 13) + 1;
                    }
                }
                
                // AUTOMATIZAR Y BUSCAR UN PATRON ._.
                boolean pareja2  = false;
                boolean trio3  = false;
                boolean poker4  = false;
                boolean repoker5  = false;
                // Todas las combinaciones de 2 pares de numeros con la posicion de los 5 numeros 
                pareja2 =
                           /*0 posicion1*/
		premio[0] == numeros[0] &&
		premio[1] == numeros[1] ||
		premio[0] == numeros[0] &&
		premio[2] == numeros[2] ||
		premio[0] == numeros[0] &&
		premio[3] == numeros[3] ||
		premio[0] == numeros[0] &&
		premio[4] == numeros[4] ||
		/*1 posicion2*/
		premio[1] == numeros[1] &&
		premio[0] == numeros[0] ||
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] ||
		premio[1] == numeros[1] &&
		premio[3] == numeros[3] ||
		premio[1] == numeros[1] &&
		premio[4] == numeros[4] ||
		/*2 posicion3*/
		premio[1] == numeros[1] &&
		premio[0] == numeros[0] ||
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] ||
		premio[1] == numeros[1] &&
		premio[3] == numeros[3] ||
		premio[1] == numeros[1] &&
		premio[4] == numeros[4] ||
		/*3 posicion4*/
		premio[3] == numeros[3] &&
		premio[0] == numeros[0] ||
		premio[3] == numeros[3] &&
		premio[1] == numeros[1] ||
		premio[3] == numeros[3] &&
		premio[2] == numeros[2] ||
		premio[3] == numeros[3] &&
		premio[4] == numeros[4] ||
		/*4 posicion5*/
		premio[4] == numeros[4] &&
		premio[0] == numeros[0] ||
		premio[4] == numeros[4] &&
		premio[1] == numeros[1] ||
		premio[4] == numeros[4] &&
		premio[2] == numeros[2] ||
		premio[4] == numeros[4] &&
		premio[3] == numeros[3] 
                ;
                // AUTOMATIZAR Y BUSCAR UN PATRON ._.
                // Todas las combinaciones de 3 pares de numeros con la posicion de los 5 numeros 
                    trio3 = 
                        /*0*/
		premio[0] == numeros[0] &&
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] ||
		premio[0] == numeros[0] &&
		premio[1] == numeros[1] &&
		premio[3] == numeros[3] ||
		premio[0] == numeros[0] &&
		premio[1] == numeros[1] &&
		premio[4] == numeros[4] ||
		premio[0] == numeros[0] &&
		premio[2] == numeros[1] &&
		premio[3] == numeros[4] ||	
		premio[0] == numeros[0] &&
		premio[2] == numeros[2] &&
		premio[4] == numeros[3] ||
		premio[0] == numeros[0] &&
		premio[3] == numeros[2] &&
		premio[4] == numeros[4] ||
		/*1*/
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] &&
		premio[3] == numeros[3] ||
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] &&
		premio[4] == numeros[4] ||
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] &&
		premio[0] == numeros[0] ||
		premio[1] == numeros[1] &&
		premio[3] == numeros[3] &&
		premio[4] == numeros[4] ||	
		premio[1] == numeros[1] &&
		premio[3] == numeros[3] &&
		premio[0] == numeros[0] ||
		premio[1] == numeros[1] &&
		premio[4] == numeros[4] &&
		premio[0] == numeros[0] ||
		/*2*/
		premio[2] == numeros[2] &&
		premio[3] == numeros[3] &&
		premio[4] == numeros[4] ||
		premio[2] == numeros[2] &&
		premio[3] == numeros[3] &&
		premio[0] == numeros[0] ||
		premio[2] == numeros[2] &&
		premio[3] == numeros[3] &&
		premio[1] == numeros[1] ||
		premio[2] == numeros[2] &&
		premio[4] == numeros[4] &&
		premio[0] == numeros[0] ||
		premio[2] == numeros[2] &&
		premio[4] == numeros[4] &&
		premio[1] == numeros[1] ||
		premio[2] == numeros[2] &&
		premio[0] == numeros[0] &&
		premio[1] == numeros[1] ||
		/*3*/
		premio[3] == numeros[3] &&
		premio[4] == numeros[4] &&
		premio[0] == numeros[0] ||
		premio[3] == numeros[3] &&
		premio[4] == numeros[4] &&
		premio[1] == numeros[1] ||
		premio[3] == numeros[3] &&
		premio[4] == numeros[4] &&
		premio[2] == numeros[2] ||
		premio[3] == numeros[3] &&
		premio[0] == numeros[0] &&
		premio[1] == numeros[1] ||	
		premio[3] == numeros[3] &&
		premio[0] == numeros[0] &&
		premio[2] == numeros[2] ||
		premio[3] == numeros[3] &&
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] ||
		/*4*/
		premio[4] == numeros[4] &&
		premio[1] == numeros[1] &&
		premio[0] == numeros[0] ||
		premio[4] == numeros[4] &&
		premio[0] == numeros[0] &&
		premio[2] == numeros[2] ||
		premio[4] == numeros[4] &&
		premio[0] == numeros[0] &&
		premio[3] == numeros[3] ||
		premio[4] == numeros[4] &&
		premio[1] == numeros[1] &&
		premio[2] == numeros[2] ||	
		premio[4] == numeros[4] &&
		premio[1] == numeros[1] &&
		premio[3] == numeros[3] ||	
		premio[4] == numeros[4] &&
		premio[2] == numeros[2] &&
		premio[3] == numeros[3] 
                ;
                
                
                for (int n=0;n<5;n++){
                    for (int m=0;m<5;m++){
                        
                        /*
                            0.0
                            0.1
                            0.2
                            ...
                            1.0
                            1.1
                            1.2
                            ...
                            4.0
                            4.1
                            ...
                            4.5
                        
                        */
                        
                    }
                }
                

                //REENCUENTO:
                // Aciertos
                if(pareja2){
                    aciertos+=2; // 2
                    if(trio3){
                        aciertos++;  // 3
                         if(poker4){
                            aciertos++;  // 4
                            if(repoker5){
                                aciertos++;  // 5 wow
                            }
                        }
                    }
                }
                // Estrellas
	if (premio[5] == numeros[5] || premio[6] == numeros[6] ){
                    estrellas++; // 1
                        if (premio[5] == numeros[5] && premio[6] == numeros[6] ){
		estrellas++; // 2
                        }
	}
                // Mostramos el numero de intento, la inversión total, el numero de boleto y el boleto premiado
                System.out.println("Intento: \tnº"+(iteracion)+"\n"+"Inversion:\t"+(2.5*iteracion)+" Eur");
                System.out.println("Tu boleto: \t"+numeros[0]+", "+numeros[1]+", "+numeros[2]+", "+numeros[3]+", "+numeros[4]+", "+numeros[5]+", "+numeros[6]);
                System.out.println("Boleto ganador: "+premio[0]+", "+premio[1]+", "+premio[2]+", "+premio[3]+", "+premio[4]+", "+premio[5]+", "+numeros[6]+"\n");  
       }while(aciertos < 2 && estrellas >= 0);  

        // si coinciden 2 numeros sale del bucle do while
       System.out.println("FELICIDADES!!");
       


    }
    
    public static boolean intervalocheck(int x, int y, int z) {
        boolean n=false;
        if (x >= y && x <= z){
            n = true;
        }
        return n;
    }
    
    
    
}
