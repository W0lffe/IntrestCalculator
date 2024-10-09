/*FILE: Korkolaskuri.java
 * Version: 0.4
 * Date: 1.10.2024
 * Author: Henry Karppinen
 * Description: This program is made for my own use to calculate intrest made with funds.
 * It is designed to collect information such as deposit amount, investment time, theoretical intrest percentage 
 * and program will calculate based on that information. PROGRAM IS IN EARLY STAGES SO IT IS SUBJECT TO CHANGE!!
 */


/******IMPORTS******/

import java.util.Scanner;

public class MainKorkolaskuri {

     /******Main******/
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);      
        int command;
        System.out.println("KORKOLASKURI\n");
        System.out.println("!!Please follow the instructions carefully!!");

        do {
            OtherFunctions.print(1);
            do {
                command = Validation.UserInput(myScanner);
                if(command == 0 || command == 1 || command == 2 || command == 3){         
                    break;
                }
            } while (true);
            

            switch (command) {
                case 1:
                    DataCollect.Mode(myScanner);
                    break;
                case 2:
                    Files.ReadFile(myScanner);
                    break;
                case 3:
                    Test.TestMain(myScanner);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
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
                    System.out.println("1. CALCULATE INTREST");
                    System.out.println("2. READ PREVIOUSLY SAVED DATA");
                    System.out.println("3. TEST CASE");
                    System.out.println("0. CLOSE PROGRAM");
                    break;
                
                case 2:
                    System.out.println("Which mode would you like to use?\n");
                    System.out.println("1. Linear Calculation");
                    System.out.println("2. Included Volatility Calculation");
                    System.out.println("3. Interval Calculation");
                    System.out.println("0. Go back");
                    break;

                case 3: 
                    System.out.println("Settings for calculation");
                    System.out.println("1. Time");
                    System.out.println("2. Estimated percentage");
                    System.out.println("3. Initial Deposit");
                    System.out.println("4. Type of Investment (ignore if going Linear)");
                    System.out.println("9. Go back to Main Menu. (Resets all values)");
                    System.out.println("0. Calculate");
                    break;
    
                case 4:
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
                case 5:
                    System.out.println("What kind of investment is this?");
                    System.out.println("1. Bonds, Low-Risk ETF");
                    System.out.println("2. Index Funds, Medium-Risk ETF");
                    System.out.println("3. Stocks, Cryptocurrencies, Commodities");
                    break;
                default:
                    break;
            } 
        }
}
