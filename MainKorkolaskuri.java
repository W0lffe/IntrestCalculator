/*FILE: Korkolaskuri.java
 * Version: 0.3
 * Date: 1.10.2024
 * Author: Henry Karppinen
 * Description: This program is made for my own use to calculate intrest made with funds.
 * It is designed to collect information such as deposit amount, investment time, theoretical intrest percentage 
 * and program will calculate based on that information. PROGRAM IS IN EARLY STAGES SO IT IS SUBJECT TO CHANGE!!
 */


/******IMPORTS******/

import java.util.Scanner;

public class MainKorkolaskuri {
    public static boolean advancedCalculation = false;


     /******Main******/
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);      
        int command;
        System.out.println("KORKOLASKURI\n");
        System.out.println("!!Please follow the instructions carefully!!");

        do {
            OtherFunctions.print(1);

            do {
                command = Validation.Selection(myScanner);
                if(command == 0 || command == 1 || command == 2 
                    || command == 3 || command == 4){
                    
                        if (command == 2) {
                        advancedCalculation = true;
                    }
                    break;
                }
            } while (true);
            

            switch (command) {
                case 1:
                case 2:
                    DataCollect.Collect(myScanner);
                    break;
                case 3:
                    Files.ReadFile(myScanner);
                    break;
                case 4:
                    Test.TestMain(myScanner);
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
class OtherFunctions{

        public static void Delay(){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /*Prints different menus based on INT FUNCTION(value given from different functions*/
        public static void print(int function){
        
            switch (function) {
                case 1:
                    System.out.println("\nSelect one: ");
                    System.out.println("1. CALCULATE INTREST (LINEAR)");
                    System.out.println("2. CALCULATE INTREST (ADVANCED)");
                    System.out.println("3. READ PREVIOUSLY SAVED DATA");
                    System.out.println("4. TEST CASE");
                    System.out.println("0. CLOSE PROGRAM");
                    break;
                
                case 2:
                    if (MainKorkolaskuri.advancedCalculation == true) {
                        System.out.println("\nCALCULATE INTREST (ADVANCED)");
                    }
                    else{
                        System.out.println("\nCALCULATE INTREST (LINEAR)");
            
                    }
                    System.out.println("Choose how to calculate intrest: ");
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
}
