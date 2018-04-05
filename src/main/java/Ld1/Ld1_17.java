package Ld1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Ld1_17 {

    private static double[][] mas = new double[10][10];
    private static double[] vector = new double[(mas.length * (mas.length + 1)) / 2];

    //Creating array with type double
    private static void createMas() {
        Random r = new Random();
        //Creating array
        for (int i = 0; i < mas.length; i++)
            for (int j = 0; j <= i; j++){
                mas[i][j] = Math.rint(r.nextDouble()*1000.0)/1000.0;
                mas[j][i] = mas[i][j];
            }
        System.out.println("Double tipa matrica\n");
        //Array output
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mas[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Creating vector and output
    private static void vectorCAO() {
        int k = 0;
        for (int j = 0; j < mas.length; j++) {
            for (int i = 0; i <= j; i++) {
                vector[k++] = mas[j][i];
            }
        }

        System.out.println("Vektors: ");
        for (int i=0;i<vector.length;i++) {
            System.out.print(vector[i] + "\t");
        }
    }

    //Creating Linear Search
    private static void linearSearch(double element, double[] vector){
        boolean found=false;

        for(int i=0;i<vector.length;i++){
            if(element == vector[i]){
                System.out.println("Vertiba "+ element + " atrasta pozicija " + (i+1));
                found=true;
            }
        }
        if(!found) System.out.println("Vertibu "+ element + " nav vektora." );
    }

    // Creating Bubble Sort
    private static void BubbleSort(double[] vector) {
        double temp;
        int counter;

        do {
            counter = 0;
            for (int i = 0; i < vector.length - 1; i++) {
                if (vector[i] > vector[i + 1]) {
                    counter++;
                    temp = vector[i];
                    vector[i] = vector[i + 1];
                    vector[i + 1] = temp;
                }
            }
        } while (counter > 0);

        System.out.println("Vektors: ");
        for (int i=0;i<vector.length;i++) {
            System.out.print(vector[i] + "\t");
        }
    }

    public static void main(String[] args) {
        System.out.println("Darja Lebedeva IRDBD12 171RDB317");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Options for user on what to do with array
        System.out.println("1: Masiva aizpildisana ar gadijuma vertibam un ta izvade");
        System.out.println("2: Nehomogenu vertibu ierakstisana vektora");
        System.out.println("3: Vektora elementu meklesana ar sec. mekl. alg. (Linear search)");
        System.out.println("4: Vektora skirosana ar Bubble Sort un ta izvade (Bubble sort)");
        System.out.println("0: Sesijas beigas");

        boolean inputValueChecker = false; // Checks if any value was input
        boolean vectorCreated = false; // Checks if vector was created
        boolean endSession = false; // checks whether user closed session

        int choiceAnswer;

        try {
            do {
                System.out.println();
                System.out.print("Jusu izvele: ");
                choiceAnswer = Integer.parseInt(br.readLine());
                switch (choiceAnswer) {
                    case 1:
                        System.out.print(" Masiva aizpildisana ar gadijuma vertibam un ta izvade");
                        createMas(); // Create and output array
                        inputValueChecker = true;
                        break;
                    case 2:

                        if (!inputValueChecker) {
                            System.out.println("Nehomogenu vertibu ierakstisana vektora!");
                            continue;
                        }
                        vectorCAO(); // Create and output vector
                        vectorCreated = true;
                        break;
                    case 3:

                        if (!inputValueChecker || !vectorCreated) {
                            System.out.println("Sakuma nepieciesams izveidot masivu un vektoru!");
                            continue;
                        }
                        System.out.print("\n" + "Elementa meklesana");
                        double inputElement =
                                Double.parseDouble(br.readLine());
                        linearSearch(inputElement, vector); // Linear search function call
                        break;
                    case 4:

                        if (!inputValueChecker || !vectorCreated) {
                            System.out.println("Sakuma nepieciesams izveidot masivu un vektoru!");
                            continue;
                        }
                        BubbleSort(vector); // Bubble sort function call
                        break;
                    case 0:
                        endSession = true;
                        break;
                    default:
                        System.out.print("Ievadita nepareiza vertiba!");
                }
            } while(!endSession);
        }catch (NumberFormatException | IOException e) {
            System.out.println("Nepareizs datu formats!");
        }
        System.out.println("Sesija pabeigta");
    }
}