/*FILE: Korkolaskuri.java
 * Version: 0.2
 * Date: 1.10.2024
 * Author: Henry Karppinen
 * Description: This program is made for my own use to calculate interest made with funds.
 * It is designed to collect information such as deposit amount, investment time, theoretical interest percentage 
 * and program will calculate based on that information. PROGRAM IS IN EARLY STAGES SO IT IS SUBJECT TO CHANGE!!
 */


/******IMPORTS******/
import java.util.Scanner;


public class Korkolaskuri {
    
    /******Main******/
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);      
        int command = -1;
        System.out.println("KORKOLASKURI\n");
        System.out.println("Please follow the instructions carefully");


        do {
            Functions.print(1);

            do {
                command = Error.Selection(myScanner);
                if(command == 0 || command == 1){
                    break;
                }
            } while (true);
            

            switch (command) {
                case 1:
                    Functions.Collect(myScanner);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
            
                default:
                    System.out.println("Unknown command!");
            }
            
            
        } while (command != 0);

        myScanner.close();
    }

}

/******Class contains Functions******/
class Functions{

    /*Prints different menus based on INT FUNCTION(value given from different functions*/
    public static void print(int function){
        
        switch (function) {
            case 1:
            System.out.println("\nSelect one: ");
            System.out.println("1. CALCULATE INTEREST ");
            System.out.println("0. CLOSE PROGRAM");
                break;
            
            case 2:
            System.out.println("CALCULATE INTEREST\n");
            System.out.println("Choose how to calculate interest: ");
            System.out.println("1. Yearly expectation");
            System.out.println("2. Monthly expectation");
            System.out.println("0. Go back");
                break;
            
            case 3:
            int x = 0;
            String toPrint = "Calculating";
            System.out.printf("%s", toPrint);
            
            /*Make a delay between printing "." */
            while (x != 3) {
                try {
                    Thread.sleep(350);
                    System.out.print(".");
                    x++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("");

            break;
            default:
                break;
        }
        
        

    }


    /*Function collects all information needed for calculating interest */
    public static void Collect(Scanner myScanner){

        print(2);

        float percentage;
        int time;
        int input;
        
        do {
            input = Error.Selection(myScanner);
            if (input == 0 || input == 1 ||input == 2){
                break;
            }
        } while (true);
        

        switch (input) {
            case 1:
                time = askTime(1, myScanner);
                percentage  = askPercentage(myScanner, 1); 
                Calculate(time, percentage, myScanner,1);
                break;
            case 2:
                time = askTime(2, myScanner);
                percentage  = askPercentage(myScanner, 2); 
                Calculate(time, percentage, myScanner, 2);
                break;
            case 0:
                System.out.println("Going back to Main Menu\n");
                break;
        }

        }

        /*Function asks from deposit and handles errors, making sure value is float-type*/
        public static float Deposit(Scanner myScanner){
            float input;
            while (true) {
                try {
                    System.out.print("Enter initial deposit amount: ");
                    input = Float.parseFloat(myScanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("\nUnknown input format! \nEnter again!");
                }
            }

            return input;


        }

        /*Function asks interest percentage*/
        public static float askPercentage(Scanner myScanner, int period){
            float input = 0;
            String toPrint = "";

            if (period == 1) {
                toPrint = "per year";
            }
            else{
                toPrint = "per month";

            }
    
            while (true) {
                try {
                    System.out.printf("Enter estimated growth percentage (%s): ", toPrint);
                    input = Float.parseFloat(myScanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("\nUnknown input format! \nEnter again!");
                }
            }

            return input;
        }

        /*Function asks for time*/
        public static int askTime(int duration, Scanner myScanner){

            int time = 0;

            switch (duration) {
                case 1:
                    System.out.println("How many years?");
                    time = Error.Time(myScanner, 1);
                    break;
                case 2:
                    System.out.println("How many months?");
                    time = Error.Time(myScanner, 2);
                    break;
                
            }

            return time;  
        } 

        /*Function uses all the information collected and makes a theoretical calculation for interest. And prints out.*/
        public static void Calculate(int time, float percentage, Scanner myScanner, int period){

            float deposit = Deposit(myScanner);
            float afterInterest = deposit;
            float temp = afterInterest;
            String toPrint = "";
            float increment = (percentage/100);
            print(3);

            if (period == 1) {
                toPrint = "years";
            }
            else{
                toPrint = "months";
            }
    
            
            for (int i = 0; i < time; i++) {
                afterInterest += temp * increment;
            }

            System.out.printf("\nAfter %d %s and %.2f%% your deposit: %.2f euros has theoretically risen to: %.2f euros. \n", time, toPrint, percentage, deposit, afterInterest);
            float earnings = afterInterest - deposit;
            System.out.println("Your earnings are: " +  earnings);

        }
    }


/******Class contains error handling functions******/
class Error{

    /*Function is called from different part of code, this makes sure that value returned is INT*/
    public static int Selection(Scanner myScanner){
        
        boolean test = false;
        int input = -1;

        while (test==false) {
            try {
                System.out.print("\nYour choice: ");
                input = Integer.parseInt(myScanner.nextLine());
            } catch (Exception e) {
                System.out.println("\nUnknown input format! \nEnter again!");
            }

            if (input == 0 || input == 1 || input == 2) {
                test = true;   
            } 
           
        }
        return input;
    }

    public static int Time(Scanner myScanner, int period){
        int input;
        String toPrint = "";

        if (period == 1) {
            toPrint = "in years";
        }
        else{
            toPrint = "in months";
        }

        while (true) {
            try {
                System.out.printf("Enter time (%s): ", toPrint);
                input = Integer.parseInt(myScanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("\nUnknown input format! \nEnter time again!");
            }
        }

        return input;


    }
}

