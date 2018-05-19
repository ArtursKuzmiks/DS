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
import java.util.Vector;

public class Ld1_11 {

    private static int array[][] = new int [10][10];
    private static Vector<Integer> vektors = new Vector<>();
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean arraycreate=false;
        boolean vectorcreate=false;
        boolean out=false;
        int menu;

        System.out.println("INFO PAR AUTORU");

        System.out.println("Menu");
        System.out.println("Masiva aizpildisana ar gadijuma vertibam       : 1");
        System.out.println("Masiva nehomogenu vertibu ierakstisana vektora : 2");
        System.out.println("Vektora elementa lineara algoritma meklesana   : 3");
        System.out.println("Vektora skirosana ar iesprausanu (Insertion)   : 4");
        System.out.println("Ja velaties iziet no sistemas nospiediet       : 0");

        do{
            try{
                System.out.print("\nMenu: ");
                menu = Integer.parseInt(read.readLine());

                loop: for (; ; ) {

                    switch (menu) {

                        case 1:
                            simarraycreate(array);
                            arrayout(array);
                            arraycreate = true;
                            break loop;
                        case 2:
                            if (!arraycreate) {
                                System.out.println("Masiva nav.");
                                System.out.print("Izveidot masivu(y/n): ");
                                String str = read.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 1;
                                else menu = 0;

                            } else {
                                vectorcreate(array);
                                vectorout(vektors);
                                vectorcreate = true;
                                break loop;
                            }
                            break;
                        case 3:
                            if(!vectorcreate){
                                System.out.println("Vektora nav.");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = read.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 2;
                                else menu = 0;

                            }else{
                                vectorsearch(vektors);
                                break loop;
                            }
                            break;
                        case 4:
                            if(!vectorcreate){
                                System.out.println("Vektors nav izveodots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = read.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 2;
                                else menu = 0;

                            }else{
                                vectorsort(vektors);
                                vectorout(vektors);
                                break loop;
                            }
                            break;
                        case 0:
                            System.out.println("Darbs ir pabeigts!");
                            out=true;
                            break loop;
                        default:
                            System.out.print("Nav tadu variantu, meiginet vel reiz:");
                            menu = Integer.parseInt(read.readLine());
                            break;
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Ievaddatu kluda");
                return;
            }

        }while(!out);

    }

    private static void simarraycreate(int [][] a){

        Random rd = new Random();
        for(int i=0;i<a.length;i++) {
            for (int j = 0; j <= i; j++) {
                array[i][j] = rd.nextInt(100)+1;
                array[j][i] = array[i][j];
            }
        }
    }

    private static void vectorcreate(int[][] a){

        vektors.setSize((a.length*(a.length+1))/2);

        for(int count=0,j=0;j<a.length;j++) {
            for (int i = 0; i <= j; i++){
                vektors.set(count++, array[j][i]);
            }
        }
    }

    private static void arrayout(int [][] a){
        System.out.println("Masivs:\n");
        for (int[] i : a) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    private static void vectorout(Vector vec){
        System.out.print("\nVektors: ");
        for(int i=0;i<vec.size();i++)
            System.out.print(vec.elementAt(i)+"\t");
    }

    private static void vectorsearch(Vector vec) throws IOException{
        int need;
        int i=0;
        boolean found=false;

        try{
            System.out.print("\nIevadiet vektora vertibu: ");
            need=Integer.parseInt(read.readLine());
        }catch (IllegalArgumentException e){
            System.out.println("Input error");
            return;
        }

        while(!found && i< vektors.size()){
            if(Integer.valueOf(need).equals(vec.get(i)))
                found=true;
            else
                i++;
        }
        if(found)
            System.out.println("Vertiba "+ need + " atrasta pozicija " + (i+1));
        else
            System.out.println("Vertibu "+ need + " nav vektora." );
    }

    private static void vectorsort(Vector<Integer> vec){
        int temp;
        int j;
        for(int i=1;i<vec.size();i++){
            temp = vec.get(i);
            j=i-1;
            while(j>=0 && vec.get(j)>temp){
                vec.setElementAt(vec.get(j),j+1);
                j--;
            }
            vec.setElementAt(temp,j+1);
        }
    }

}
