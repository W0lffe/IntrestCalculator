import java.util.Scanner;
import com.google.gson.Gson;

public class Cache {

    public static void ShowCache(Scanner myScanner){
        Gson gson = new Gson();

        int entry = 1;
        for (Investment investment : Calculations.Storage) {
                System.out.println("\nEntry: " + entry + "\n" + investment);
                entry++;
        }
        int userInput;
        System.out.println("Would you like to save entry to server? \n1.Yes\n2.No");  
        
        while (true) {
            userInput = Validation.UserInput(myScanner);
            if (userInput == 1 || userInput == 2) {
                break;
            }
            else{
                System.out.println("Invalid choice!");
            }
        }

        if (userInput == 1) {
            int choice;
            System.out.println("Wich entry would you like to be saved?");
            while (true) {
                choice = Validation.UserInput(myScanner);
                if(choice > 0 && choice <= Calculations.Storage.size()){

                    System.out.println("\n" + Calculations.Storage.get(choice-1));
                    String selectedEntry = gson.toJson(Calculations.Storage.get(choice-1));
                    //System.out.println(selectedEntry);
                    
                    break;
                }
                else{
                    System.out.println("Entry doesnt exist.");
                }
            }
        }
        else{
            return;
        }
}

}
