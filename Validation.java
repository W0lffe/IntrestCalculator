import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/******Class contains error handling functions******/
class Validation{

    /*Function is called from different part of code, this makes sure that value returned is INT*/

    public static int UserInput(Scanner myScanner){
        
        ArrayList<Integer> validInputs = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int userInput;

        while (true) {
            try {
                System.out.print("\nYour choice: ");
                userInput = Integer.parseInt(myScanner.nextLine());
                
                if (validInputs.contains(userInput)) {
                    break;
                }
                else{
                    System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("\nUnknown input format! Enter again!");
            }
        }
        return userInput;
    }

    public static int Time(Scanner myScanner, String period){
        int userInput;
    
        while (true) {
            try {
                System.out.printf("Enter time (in %s): ", period);
                userInput = Integer.parseInt(myScanner.nextLine());
                if (userInput > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\nUnknown input format! Enter time again!");
            }
        }

        return userInput;

    }
}
