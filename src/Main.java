
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);      
        int userInput;
        ArrayList<Integer> validInputs = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        System.out.println("KORKOLASKURI\n");
        System.out.println("!!Please follow the instructions carefully!!");

        do {
            Utility.print(1);
            do {
                userInput = Validation.UserInput(myScanner);
                if(validInputs.contains(userInput)){         
                    break;
                }
            } while (true);
            

            switch (userInput) {
                case 1:
                    DataCollect.Mode(myScanner);
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
                    Cache.ShowServerCache(myScanner);
                    break;
                case 5:
                    Test.TestMain(myScanner);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (userInput != 0);
        myScanner.close();
    }
}
