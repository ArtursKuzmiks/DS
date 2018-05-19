/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Ld1_16 {

    private static double mass[][] = new double [10][10];
    private static double [] vector;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean arrcreate=false;
        boolean veccreate=false;
        boolean out=false;
        int menu;

        System.out.println("INFO PAR AUTORU");

        System.out.println("Menu");
        System.out.println("Masiva aizpildisana ar gadijuma vertibam       : 1");
        System.out.println("Masiva nehomogenu vertibu ierakstisana vektora : 2");
        System.out.println("Vektora elementa lineara algoritma meklesana   : 3");
        System.out.println("Vektora skirosana ar Buble                     : 4");
        System.out.println("Ja velaties iziet no sistemas nospiediet       : 0");

        do{
            try{
                System.out.print("\nIzveles dati: ");
                menu = Integer.parseInt(br.readLine());

                loop: for (; ; ) {

                    switch (menu) {

                        case 1:
                            arrcreate(mass);
                            arrout(mass);
                            arrcreate = true;
                            break loop;
                        case 2:
                            if (!arrcreate) {
                                System.out.println("Masivs nav izveidots");
                                System.out.print("Izveidot masivu(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 1;
                                else menu = 0;

                            } else {
                                veccreate(mass);
                                vecout(vector);
                                veccreate=true;
                                break loop;
                            }
                            break;
                        case 3:
                            if(!veccreate){
                                System.out.println("Vektors nav izveidots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 2;
                                else menu = 0;

                            }else{
                                vecsearch(vector);
                                break loop;
                            }
                            break;
                        case 4:
                            if(!veccreate){
                                System.out.println("Vektors nav izveodots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 2;
                                else menu = 0;

                            }else{
                                vecsort(vector);
                                vecout(vector);
                                break loop;
                            }
                            break;
                        case 0:
                            System.out.println("Darbs ir pabeigts");
                            out=true;
                            break loop;
                        default:
                            System.out.print("Nav tadu variantu, meiginet vel reiz:");
                            menu = Integer.parseInt(br.readLine());
                            break;
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Ievaddatu kluda");
                return;
            }

        }while(!out);

    }

    private static void arrcreate(double [][] a){
        Random rd = new Random();
        for(int i=0;i<a.length;i++) {
            for (int j = 0; j <= i; j++) {
                mass[i][j] = Math.rint(rd.nextDouble()*1000.0)/1000.0;
                mass[j][i] = mass[i][j];
            }
        }
    }

    private static void veccreate(double [][] a){
        vector = new double[(a.length*(a.length+1))/2];

        for(int count=0,i=0;i<a.length;i++) {
            for (int j = 0; j <= i; j++){
                vector[count++]= mass[i][j];
            }
        }
    }

    private static void arrout(double [][] a){
        System.out.println("Masivs:");
        for (double [] i : a) {
            for (double j : i) {
                System.out.printf("%.3f  ",j);
            }
            System.out.println();
        }
    }

    private static void vecout(double [] a){
        System.out.print("\nVektors: ");
        for(int i=0;i<a.length;i++)
            System.out.printf("%.3f ",a[i]);
    }

    private static void vecsearch(double [] a) throws IOException{
        double need;
        int i=0;
        boolean found=false;

        try{
            System.out.print("\nIevadiet vektora vertibu: ");
            need=Double.parseDouble(br.readLine());
        }catch (IllegalArgumentException e){
            System.out.println("Input error");
            return;
        }

        while(!found && i< a.length){
            if(need == a[i])
                found=true;
            else
                i++;
        }
        if(found)
            System.out.println("Vertiba "+ need + " atrasta pozicija " + (i+1));
        else
            System.out.println("Vertibu "+ need + " nav vektora." );

    }

    private static void vecsort(double [] a){
        double temp;
        int counter;

        do {
            counter = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] < a[i + 1]) {
                    counter++;
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        } while (counter > 0);
    }

}

