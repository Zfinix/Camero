package com.camero;

import java.util.Scanner;

public class EmpApp {
    public static int num;
    public static int n;
    public static int age;
    public static String degree;
    public static int years;
    public static String language;
    public static String BSc;

    public EmpApp() {
    }

    public static void main(String[] args) {
        label77:
        for(num = 1; num <= 5; ++num) {
            System.out.println("WELCOME TO MICROSOFT'S APPLICATION FOR THE POSITION OF THE CENTRAL MANAGER");
            System.out.println("PLEASE KINDLY FILL IN YOUR DETAILS BELOW: ");

            for(n = 1; n <= 5; ++n) {
                System.out.println("APPLICATION: " + n);
                Scanner input = new Scanner(System.in);
                System.out.println("Enter First Name: ");
                String fname = input.next();
                System.out.println("Enter Last Name: ");
                String lname = input.next();
                System.out.println("Enter Age: ");
                int age = input.nextInt();
                if (age >= 30) {
                    System.out.println("Candidate not accepted");
                    break;
                }

                if (age < 30 && age >= 18) {
                    System.out.println("Enter State of Origin: ");
                    String state = input.next();
                    System.out.println("Degree Specification: ");
                    String degree = input.next();
                    byte var8 = -1;
                    switch(degree.hashCode()) {
                        case 66098:
                            if (degree.equals("BSc")) {

                            }else  {

                            }
                    }

                    switch(var8) {
                        case 0:
                            break;
                        default:
                            System.out.println("Working experience (Years): ");
                            int years = input.nextInt();
                            if (years < 5) {
                                System.out.println("Candidate not accepted");
                                continue label77;
                            }

                            if (years < 5) {
                                System.out.println("Languages: ");
                                String language = input.next();
                                byte var10 = -1;
                                switch(language.hashCode()) {
                                    case -1816030064:
                                        if (language.equals("English, Spanish")) {
                                            var10 = 2;
                                        }
                                        break;
                                    case -646135836:
                                        if (language.equals("English, German, Spanish, French")) {
                                            var10 = 4;
                                        }
                                        break;
                                    case 785410238:
                                        if (language.equals("English, Spanish, French")) {
                                            var10 = 3;
                                        }
                                        break;
                                    case 1375032354:
                                        if (language.equals("English, French, Spanish")) {
                                            var10 = 1;
                                        }
                                        break;
                                    case 2065057406:
                                        if (language.equals("English, French")) {
                                            var10 = 0;
                                        }
                                }

                                switch(var10) {
                                    case 0:
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                        break;
                                    default:
                                        System.out.println("Invalid degree");
                                }
                            }
                    }
                }
            }
        }

    }
}
