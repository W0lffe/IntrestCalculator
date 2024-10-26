
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//java -cp "F:\Java-programs\gson\gson-2.11.0.jar;." Main  RUN
//javac -cp "F:\Java-programs\gson\gson-2.11.0.jar;." Main.java Calculations.java Test.java Files.java Cache.java Investment.java DataCollect.java Validation.java 

public class Main{

    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);      
        int userInput;
        ArrayList<Integer> validInputs = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        System.out.println("KORKOLASKURI\n");
        System.out.println("!!Please follow the instructions carefully!!");

        do {
            OtherFunctions.print(1);
            do {
                userInput = Validation.UserInput(myScanner);
                if(validInputs.contains(userInput)){         
                    break;
                }
            } while (true);
            

            switch (userInput) {
                case 1:
                    DataCollect.CollectData(myScanner);
                    break;
                case 2:
                    Files.ReadFile(myScanner);
                    break;
                case 3:
                     if (!Calculations.Storage.isEmpty()) {
                        Cache.ShowCache(myScanner);
                        break;
                    }
                    else{
                        System.out.println("Cache is empty! Nothing to show..");
                        break;
                    } 
                case 4:
                    Test.TestMain(myScanner);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
            }
        } while (userInput != 0);
        myScanner.close();
    }
}


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
                System.out.println("2. READ PREVIOUSLY SAVED FILES");
                System.out.println("3. SHOW CACHE");
                System.out.println("4. TEST CASE");
                System.out.println("0. CLOSE PROGRAM");
                break;
            
            case 2:
                System.out.println("Which mode would you like to use?\n");
                System.out.println("1. Linear Calculation");
                System.out.println("2. Included Volatility Calculation");
                System.out.println("0. Go back");
                break;

            case 3: 
                System.out.println("\nSettings for calculation");
                System.out.println("1. Mode");
                System.out.println("2. Time");
                System.out.println("3. Estimated percentage");
                System.out.println("4. Initial Deposit");
                System.out.println("5. Type of Investment (NOT AVAILABLE FOR LINEAR)");
                System.out.println("9. Calculate");
                System.out.println("0. Go back to Main Menu. (Resets all values)");
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
                System.out.println("0. Go Back");

                break;
            default:
                break;
        } 
    }

    } 
