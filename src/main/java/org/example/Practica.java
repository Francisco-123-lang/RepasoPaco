package org.example;

import java.util.Scanner;

class Practica {
    private static final Scanner sc = new Scanner(System.in);

    public static int suma() {
        System.out.println("Dime un número positivo: ");
        int suma = sc.nextInt();
        sc.nextLine();
        while (suma < 0) {
            System.out.println("No válido, Dime un número positivo: ");
            suma = sc.nextInt();
            sc.nextLine();
        }
        return suma;
    }

    public static double[] array() {
        int suma = suma();
        double[] array = new double[suma];

        for (int i = 0; i < suma; i++) {
            System.out.println("Dime un número: ");
            double content = sc.nextDouble();
            sc.nextLine();
            array[i] = content;
        }
        return array;
    }

    public static double[] array2() {
        int suma = suma();
        double[] array2 = new double[suma];

        for (int i = 0; i < suma; i++) {
            System.out.println("Dime un número: ");
            double content = sc.nextDouble();
            sc.nextLine();
            array2[i] = content;
        }
        return array2;
    }

    public static double average(double[] array, double[] array2) {
        int suma1 = array.length + array.length;
        double suma = 0;
        for (double sumatorio : array) {
            suma += sumatorio;
        }
        for (double sumatorio : array2) {
            suma += sumatorio;
        }

        return suma / suma1;
    }

    public static void printImage(double average) {
        System.out.println("El resultado es " + average);
    }

    public static void main(String[] args) {
        double[] array = array();
        double[] array2 = array2();
        double average = average(array, array2);
        printImage(average);
    }
}