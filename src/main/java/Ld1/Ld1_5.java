package Ld1;

/*
 * Author: Arturs Kuzmiks
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

public class Ld1_5 {

    private static int Massivs[][] = new int[10][10];
    private static Vector<Integer> vektors = new Vector<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean masscreate = false;
        boolean vektcreate = false;
        boolean out = false;
        int menu;

        System.out.println("INFO PAR AUTORU");

        System.out.println("Menu:");
        System.out.println("Masiva aizpildisana ar gadijuma vertibam       : 1");
        System.out.println("Masiva nehomogenu vertibu ierakstisana vektora : 2");
        System.out.println("Vektora elementa lineara algoritma meklesana   : 3");
        System.out.println("Vektora skirosana ar iesprausanu (Insertion)   : 4");
        System.out.println("Ja velaties iziet no sistemas nospiediet       : 0");

        do {
            try {
                System.out.print("\nIevaddati: ");
                menu = Integer.parseInt(br.readLine());

                loop:
                for (; ; ) {

                    switch (menu) {

                        case 1:
                            masscreate(Massivs);
                            massout(Massivs);
                            masscreate = true;
                            break loop;
                        case 2:
                            if (!masscreate) {
                                System.out.println("Masivs nav izveidots");
                                System.out.print("Izveidot masivu(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 1;
                                else menu = 0;

                            } else {
                                vektcreate(Massivs);
                                vektout(vektors);
                                vektcreate = true;
                                break loop;
                            }
                            break;
                        case 3:
                            if (!vektcreate) {
                                System.out.println("Vektors nav izveidots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 2;
                                else menu = 0;

                            } else {
                                vektsearch(vektors);
                                break loop;
                            }
                            break;
                        case 4:
                            if (!vektcreate) {
                                System.out.println("Vektors nav izveodots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    menu = 2;
                                else menu = 0;

                            } else {
                                vektsort(vektors);
                                vektout(vektors);
                                break loop;
                            }
                            break;
                        case 0:
                            System.out.println("Darbs ir pabeigts");
                            out = true;
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

        } while (!out);

    }

    private static void masscreate(int[][] a) {
        Random rd = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = (a.length - 1) - i; j < a.length; j++) {
                Massivs[i][j] = rd.nextInt(100) - 50;
                if (Massivs[i][j] == 0)
                    Massivs[i][j] = rd.nextInt(99) + 1;
            }
        }
    }  //veidojam massivu

    private static void vektcreate(int[][] a) {
        vektors.setSize((a.length * (a.length + 1)) / 2);
        for (int count = 0, i = 0; i < a.length; i++) {
            for (int j = (a.length - 1) - i; j < a.length; j++) {
                vektors.set(count++, Massivs[i][j]);
            }
        }
    }  //veidojam vektoru

    private static void massout(int[][] a) {
        System.out.println("Masivs:");
        for (int[] i : a) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }  //masiva izvade

    private static void vektout(Vector vec) {
        System.out.print("\nVektors: ");
        for (int i = 0; i < vec.size(); i++)
            System.out.print(vec.elementAt(i) + " ");
    }  //vektora izvade

    private static void vektsearch(Vector vec) throws IOException {
        int n;
        int i = 0;
        boolean found = false;

        try {
            System.out.print("\nIevadiet vektora vertibu, kuru velaties atrast: ");
            n = Integer.parseInt(br.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
            return;
        }

        while (!found && i < vektors.size()) {
            if (Integer.valueOf(n).equals(vec.get(i)))
                found = true;
            else
                i++;
        }
        if (found)
            System.out.println("Vertiba " + n + " atrasta pozicija " + (i + 1));
        else
            System.out.println("Vertibu " + n + " nav vektora.");

    } //meklesana ar robez markeri

    private static void vektsort(Vector<Integer> vec) {
        int temp;
        int j;
        for (int i = 1; i < vec.size(); i++) {
            temp = vec.get(i);
            j = i - 1;
            while (j >= 0 && vec.get(j) > temp) {
                vec.setElementAt(vec.get(j), j + 1);
                j--;
            }
            vec.setElementAt(temp, j + 1);
        }
    }  //vektora skirosana

}

