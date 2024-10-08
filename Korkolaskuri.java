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
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.awt.Desktop;
import java.time.LocalDate;


public class Korkolaskuri {
    public static boolean advancedCalculation = false;

     /******Main******/
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);      
        int command = -1;
        System.out.println("KORKOLASKURI\n");
        System.out.println("!!Please follow the instructions carefully!!");

        do {
            Print.print(1);

            do {
                command = Validation.Selection(myScanner);
                if(command == 0 || command == 1 || command == 2 || command == 3){
                    if (command == 2) {
                        advancedCalculation = true;
                    }
                    break;
                }
            } while (true);
            

            switch (command) {
                case 1:
                case 2:
                    Functions.Collect(myScanner);
                    break;
                case 3:
                    Files.ReadFile(myScanner);
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

    /*DEFINES FOR PRIVATE VARIABLES*/
    private static final int OPEN_MARKET_YEARLY = 252;
    private static final int OPEN_MARKET_MONTHLY = 21;
    private static final float VOLATILITY_PERCENTAGE = 0.15f;
    private static final String[] YEAR = {"years", "year"};
    private static final String[] MONTH = {"months", "month"};


    /*Function collects all information needed for calculating intrest */
    public static void Collect(Scanner myScanner){

        Print.print(2);
        
        float percentage;
        int time, userInput;
        String duration = "";
        String period = "";

        
        do {
            userInput = Validation.Selection(myScanner);
            if (userInput == 0 || userInput == 1 ||userInput == 2){
                break;
            }
        } while (true);
        
        if (userInput == 1){
            duration  = YEAR[0];
            period = YEAR[1];
            System.out.println("You chose yearly expectation.");
            
        }
        else if(userInput == 2){
            duration  = MONTH[0];
            period = MONTH[1];
            System.out.println("You chose monthly expectation.");

        }
        else if (userInput == 0) {
            System.out.println("Going back to Main Menu\n");
            return;
        }

        time = askTime(duration, myScanner);
        percentage  = askPercentage(myScanner, period); 
        Calculate(time, percentage, myScanner, duration);
        
        }

        private static int askTime(String duration, Scanner myScanner){

            int time = 0;
            System.out.printf("How many %s?\n", duration);
            time = Validation.Time(myScanner, duration);
         
            return time;  
        } 

        private static float askPercentage(Scanner myScanner, String time){
            float userInput;
    
            while (true) {
                try {
                    System.out.printf("Enter estimated growth percentage per %s: ", time);
                    userInput = Float.parseFloat(myScanner.nextLine());
                    if (userInput > 0) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("\nUnknown input format! Enter again!\n");
                }
            }

            return userInput;
        }

        private static void Calculate(int time, float percentage, Scanner myScanner, String period){
            
            float deposit = Deposit(myScanner);
            float afterIntrest = deposit;
            float temp = afterIntrest;
            float increment;

            if (Korkolaskuri.advancedCalculation == true) {
                
                increment = CalculateVolatilityAverage(percentage, time, period);

                for (int j = 0; j < time; j++) {
                    afterIntrest += temp * increment;
                }
                
                percentage = (increment*100);
                //System.out.println("Calculated percent: " + percentage); for bug checking

            }
            else{   
                increment = (percentage/100);

                for (int i = 0; i < time; i++) {
                    afterIntrest += temp * increment;
                }
            }
            Print.print(3);

            System.out.printf("\nAfter: %d %s\nWith %.2f%%\nYour initial deposit: %.2f euros\nhas theoretically risen to: %.2f euros. \n", time, period, percentage, deposit, afterIntrest);
            float earnings = afterIntrest - deposit;
            System.out.printf("Your earnings are: %.2f euros. \n", earnings);



            Files.SaveFile(myScanner, time, percentage, deposit, afterIntrest, earnings, period);
            Korkolaskuri.advancedCalculation = false;


        }

        private static float Deposit(Scanner myScanner){
            float userInput;
            while (true) {
                try {
                    System.out.print("Enter initial deposit amount: ");
                    userInput = Float.parseFloat(myScanner.nextLine());
                    if (userInput > 0) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("\nUnknown input format! \nEnter again!");
                }
            }

            return userInput;


        }


        public static float CalculateVolatilityAverage(float percentage, int time, String period){   
            
            float increment;
            float newPercentage = 0;

            int days;
            if (period.equals("year")){
                days = OPEN_MARKET_YEARLY * time; //conversion to open market days
            }
            else{
                days = OPEN_MARKET_MONTHLY * time; //conversion to open market days
            }

            float percent = (percentage/100f)/(float)days;
            float volatility = VOLATILITY_PERCENTAGE/days; //15% divided by time and converted to decimal form
            float volatility_min = percent - volatility;
            float volatility_max = percent + volatility;

            for (int j = 0; j < days; j++) {
                increment = (float)(Math.random() * (volatility_max - volatility_min)) + volatility_min;
                newPercentage += increment;
            }

            //System.out.println("Volatility percentage " + newPercentage); for bug checking
            return newPercentage;
    
        }


        public static void Delay(){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}


/******This class contains methods for saving data on textfile******/
class Files{

    private static String path = "saved/";
    private static LocalDate currentDate = LocalDate.now();


    public static void SaveFile(Scanner myScanner, int time, float percentage, float deposit, float afterIntrest, float earnings, String period){
        int userInput;
        System.out.println("\nDo you wish to save this data?\n1.Yes\n2.No");

        do {
            userInput = Validation.Selection(myScanner);
            if (userInput == 1 || userInput == 2) {
                break;
            }
        } while (true);
        
        if (userInput == 2) {
            return;
        }

        String dataToSave = toSave(time, period, percentage, deposit, afterIntrest, earnings);

        while (true) {
            System.out.print("Give a name to file: ");
            String name = myScanner.nextLine();
            String newFilePath = path + name + ".txt";
    
            try {
                File newFile = new File(newFilePath);
                if (newFile.createNewFile()) {
                    System.out.println("Created file: " + newFile.getName());
                    FileWriter myWriter = new FileWriter(newFile);
                    myWriter.write(dataToSave);
                    myWriter.close();
                    break;
                }
                else if(!newFile.createNewFile()){
                    System.out.println("File already exists! Data appended! \n");
                    FileWriter myWriter = new FileWriter(newFile, true);
                    myWriter.append("\n\n" + dataToSave);
                    myWriter.close();
                    break;
                }
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
       

        Functions.Delay();
        System.out.println("Data saved!");
        

    }

    public static void ReadFile(Scanner myScanner){

        File folder = new File(path);
        int a = 1;
        int command;

        if(folder.exists() && folder.isDirectory()){
            File[] folderFiles = folder.listFiles(file -> file.isFile() && file.canRead() && file.canWrite());

            System.out.println("\nFolder has files: ");
           
            for (File file : folderFiles) {
                if (file.canRead() && file.canWrite()) {
                    System.out.println(a + ". " +file.getName());
                    a++;
                }
            }
            
            System.out.println("Which file would you like to read?\n");

            while (true) {
                try {
                    System.out.print("Your choice: ");
                    command = Integer.parseInt(myScanner.nextLine());
                    if (command > 0 && command < a) {
                        break;
                    }
                    else{
                        System.out.println("File not found! Try again");
                    }
                } catch (Exception e) {
                    System.out.println("Error occured!");

                }
            }
            System.out.println("You chose file: " + folderFiles[command-1].getName());
            String newPath = path + folderFiles[command-1].getName();

            File file = new File(newPath);

            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                Functions.Delay();
                    try {
                        desktop.open(file);
                        
                    } catch (IOException e) {
                       System.out.println("Unable to open file!");
                       e.printStackTrace();
                    }
                }
            
            else{
                System.out.println("This system is not capable for desktop operations.");
            }
            
        }
        else{
            System.out.println("Folder is not found.");
        }
    }

    private static String toSave(int time, String period, float percentage, float deposit, float afterIntrest, float earnings){

      
        String dataToSave = "Current date: " + currentDate;
        if (Korkolaskuri.advancedCalculation == true) {
            dataToSave += " (ADVANCED)";
        }
        dataToSave += "\n\nInvesting time: " + Integer.toString(time) + " " + period;
        dataToSave += "\nPercentage:  "+ Float.toString(percentage) + "%";
        dataToSave += "\nInitial deposit:  " + Float.toString(deposit) + " euros";
        dataToSave += "\nAfter intrest: "  + Float.toString(afterIntrest) + " euros";
        dataToSave += "\nEarnings: " + Float.toString(earnings) + " euros";
        return dataToSave;
    }
}


/******This class contains method for printing various things through program******/
class Print{
    /*Prints different menus based on INT FUNCTION(value given from different functions*/
    public static void print(int function){
        
        switch (function) {
            case 1:
                System.out.println("\nSelect one: ");
                System.out.println("1. CALCULATE INTREST (LINEAR)");
                System.out.println("2. CALCULATE INTREST (ADVANCED)");
                System.out.println("3. READ PREVIOUSLY SAVED DATA");
                System.out.println("0. CLOSE PROGRAM");
                break;
            
            case 2:
                if (Korkolaskuri.advancedCalculation == true) {
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

/******Class contains error handling functions******/
class Validation{

    /*Function is called from different part of code, this makes sure that value returned is INT*/

    public static int Selection(Scanner myScanner){
        
        boolean test = false;
        int input = -1;

        while (test==false) {
            try {
                System.out.print("\nYour choice: ");
                input = Integer.parseInt(myScanner.nextLine());
            } catch (Exception e) {
                System.out.println("\nUnknown input format! Enter again!");
            }

            if (input == 0 || input == 1 || input == 2 || input == 3) {
                test = true;   
            }else{
                System.out.println("Invalid choice!");
            }
           
        }
        return input;
    }

    public static int Time(Scanner myScanner, String duration){
        int input;
    
        while (true) {
            try {
                System.out.printf("\nEnter time (in %s): ", duration);
                input = Integer.parseInt(myScanner.nextLine());
                if (input > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\nUnknown input format! Enter time again!");
            }
        }

        return input;

    }
}
