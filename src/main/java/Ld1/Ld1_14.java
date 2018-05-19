package Ld1;

/*
 * Author: Arturs Kuzmiks
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

public class Ld1_14 {

    private static int Arr[][] = new int[10][10];
    private static Vector<Integer> vector = new Vector<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean arrcreate = false;
        boolean veccreate = false;
        boolean out = false;
        int izvel;

        System.out.println("Arturs Kuzmiks 12.gr. 111REB779");

        System.out.println("Izvelne:");
        System.out.println("Masiva aizpildisana ar gadijuma vertibam       : 1");
        System.out.println("Masiva nehomogenu vertibu ierakstisana vektora : 2");
        System.out.println("Vektora elementa lineara algoritma meklesana   : 3");
        System.out.println("Vektora skirosana ar iesprausanu (Insertion)   : 4");
        System.out.println("Ja velaties iziet no sistemas nospiediet       : 0");

        do {
            try {
                System.out.print("\nIevaddati: ");
                izvel = Integer.parseInt(br.readLine());

                loop:
                for ( ; ; ) {

                    switch (izvel) {

                        case 1:
                            arrcreate(Arr);
                            arrout(Arr);
                            arrcreate = true;
                            break loop;
                        case 2:
                            if (!arrcreate) {
                                System.out.println("Masivs nav izveidots");
                                System.out.print("Izveidot masivu(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    izvel = 1;
                                else izvel = 0;

                            } else {
                                veccreate(Arr);
                                vecout(vector);
                                veccreate = true;
                                break loop;
                            }
                            break;
                        case 3:
                            if (!veccreate) {
                                System.out.println("Vektors nav izveidots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    izvel = 2;
                                else izvel = 0;

                            } else {
                                vecsearch(vector);
                                break loop;
                            }
                            break;
                        case 4:
                            if (!veccreate) {
                                System.out.println("Vektors nav izveodots");
                                System.out.print("Izveidot vektoru(y/n): ");
                                String str = br.readLine();

                                if (str.equals("Y") || str.equals("y"))
                                    izvel = 2;
                                else izvel = 0;

                            } else {
                                vecsort(vector);
                                vecout(vector);
                                break loop;
                            }
                            break;
                        case 0:
                            System.out.println("Darbs ir pabeigts");
                            out = true;
                            break loop;
                        default:
                            System.out.print("Nav tadu variantu, meiginet vel reiz:");
                            izvel = Integer.parseInt(br.readLine());
                            break;
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Ievaddatu kluda");
                return;
            }

        } while (!out);

    }

    private static void arrcreate(int[][] a) {
        Random rd = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <= i; j++) {
                Arr[i][j] = rd.nextInt(100) - 50;
                if (Arr[i][j] == 0)
                    Arr[i][j] = rd.nextInt(99) + 1;
            }
        }
    }

    private static void veccreate(int[][] a) {
        vector.setSize((a.length * (a.length + 1)) / 2);
        for (int count = 0, i = 0; i < a.length; i++) {
            for (int j = 0; j <= i; j++) {
                vector.set(count++, Arr[i][j]);
            }
        }
    }

    private static void arrout(int[][] a) {
        System.out.println("Masivs:");
        for (int[] i : a) {
            for (int j : i) {
                System.out.printf("%-5d",j);
            }
            System.out.println();
        }
    }

    private static void vecout(Vector vec) {
        System.out.print("\nVektors: ");
        for (int i = 0; i < vec.size(); i++)
            System.out.print(vec.elementAt(i) + "\t");
    }

    private static void vecsearch(Vector vec) throws IOException {
        int n;
        boolean found = false;

        try {
            System.out.print("\nIevadiet vektora vertibu, kuru velaties atrast: ");
            n = Integer.parseInt(br.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
            return;
        }
        for (int i = 0; i < vec.size(); i++) {
            if (Integer.valueOf(n).equals(vec.get(i))) {
                System.out.println("Vertiba " + n + " atrasta pozicija " + (i + 1));
                found = true;
            }
        }
        if (!found) System.out.println("Vertibu " + n + " nav vektora.");

    }

    private static void vecsort(Vector<Integer> vec) {
        int temp;
        int j;
        for (int i = 1; i < vec.size(); i++) {
            temp = vec.get(i);
            j = i - 1;
            while (j >= 0 && vec.get(j) < temp) {
                vec.setElementAt(vec.get(j), j + 1);
                j--;
            }
            vec.setElementAt(temp, j + 1);
        }
    }

}
