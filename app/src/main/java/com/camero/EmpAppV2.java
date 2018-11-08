package com.camero;

import java.util.Scanner;

public class EmpAppV2 {
    public static  int num, n;
    private  static String degree, state,age, years, language, firstName, lastName;
    private static Scanner input;


    public EmpAppV2() {
    }

    public static void main(String[] args) {
        System.out.println("WELCOME TO MICROSOFT'S APPLICATION FOR THE POSITION OF THE CENTRAL MANAGER");
        System.out.println("PLEASE KINDLY FILL IN YOUR DETAILS BELOW: ");


        input = new Scanner(System.in);

        for (n = 1; n < 5 ; n++) {
            System.out.println("-------------------------------------------------------------- ");
            System.out.println("APPLICATION: " + n);
            getInput();
        }

}


    private static void getInput() {


        System.out.println("Enter First Name: ");
        firstName = input.next();

        System.out.println("Enter Last Name: ");
        lastName = input.next();


        toAge();




}

    private static void toAge() {

        System.out.println("Enter Age: ");
        age = input.next();

        if (age.matches("[0-9]+")) {
            String agex = age;

            System.out.println("Working experience (Years): ");
            years = input.next();

            String yearsx = years;

            if (Integer.valueOf(agex) >= 30 || Integer.valueOf(yearsx) < 5 ) {
                System.out.println("Candidate not accepted");
                System.exit(0);
            } else if ( Integer.valueOf(age) >= 18 && Integer.valueOf(age) < 30 ) {

                System.out.println( "Enter State of Origin: " );
                state = input.next();

                System.out.println( "Degree Specification: [MSc, BSc, BEng]" );
                degree = input.next();

                System.out.println("Languages: ");
                language = input.next();


                if (Validate( firstName + "" + lastName, degree, language, state )){
                    System.out.println( "Candidate Accepted");
                }else if (!Validate( firstName + "" + lastName, degree, language, state )){
                    System.out.println( "Candidate Not Accepted");
                }


            }else{
                System.out.println("Invalid Age");
                System.out.println("-------------------------------------------------------------- ");
                toAge();
            }
        }
    }

    private static boolean Validate(String s, String degree, String language, String state) {

        if(s.isEmpty()){

            System.out.println( "Invalid name" );

            return false;
        }

        if (!degree.toLowerCase().contains( "bsc" ) && !degree.toLowerCase().contains( "msc" ) && !degree.toLowerCase().contains( "beng" )) {
            System.out.println( "Invalid degree" );
            return false;
        }

        if (state.isEmpty() || state.length() < 3) {
            System.out.println( "Invalid state" );
            return false;
        }


        if (!language.toLowerCase().contains( "english" ) && !language.toLowerCase().contains( "german" ) && !language.toLowerCase().contains( "spanish" ) && !language.toLowerCase().contains( "french" )) {
           System.out.println( "Invalid language" );

           return false;
       }

        return true;
    }
}

