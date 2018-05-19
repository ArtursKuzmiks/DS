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


public class Ld1_3 {

    private static char[][] array = new char[10][10];
    private static char[] vector = new char[(array.length * (array.length + 1)) / 2];
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean arrcreate = false;
        boolean veccreate = false;
        boolean out = false;
        int izvel;

        System.out.println("INFO");

        System.out.println("Izvelne:");
        System.out.println("Masiva aizpildisana ar gadijuma vertibam       : 1");
        System.out.println("Masiva nehomogenu vertibu ierakstisana vektora : 2");
        System.out.println("Vektora elementa lineara algoritma meklesana   : 3");
        System.out.println("Vektora skirosana ar Selection sort            : 4");
        System.out.println("Ja velaties iziet no sistemas nospiediet       : 0");

        do {
            try {
                System.out.print("\nIevaddati: ");
                izvel = Integer.parseInt(br.readLine());

                loop:
                for (; ; ) {

                    switch (izvel) {

                        case 1:
                            arrcreate(array);
                            arrout(array);
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
                                veccreate(array);
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
                                System.out.print("\nIevadiet vektora vertibu, kuru velaties atrast: ");
                                String str = br.readLine().replaceAll("[^a-z]", "");
                                if (str.length() != 1) System.out.println("Ievaddatu kluda");
                                else vecsearch(str.charAt(0), vector);
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

    private static void arrcreate(char[][] a) {
        Random rd = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = 48;
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a[i].length; j++) {
                a[i][j] = (char) (rd.nextInt(26) + 97);
            }
        }
    }

    private static void veccreate(char[][] a) {
        for (int count = 0, i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 48)
                    vector[count++] = a[i][j];
            }
        }
    }

    private static void arrout(char[][] a) {
        System.out.println("Masivs:");
        for (char[] i : a) {
            for (char j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    private static void vecout(char[] vec) {
        System.out.print("\nVektors: ");
        for (char aVec : vec) System.out.print(aVec + "\t");
    }

    private static void vecsearch(char element, char[] vec) {
        boolean found = false;

        for (int i = 0; i < vec.length; i++) {
            if (element == vec[i]) {
                System.out.println("Vertiba " + element + " atrasta pozicija " + (i + 1));
                found = true;
            }
        }
        if (!found) System.out.println("Vertibu " + element + " nav vektora.");

    }

    private static void vecsort(char[] vec) {
        int first;
        for (int i = vec.length - 1; i > 0; i--) {
            first = 0;
            for (int j = 1; j <= i; j++) {
                if (vec[j] > vec[first]) first = j;
            }
            char temp = vec[first];
            vec[first] = vec[i];
            vec[i] = temp;
        }
    }

}
