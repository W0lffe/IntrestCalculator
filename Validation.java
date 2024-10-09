import java.util.Scanner;

/******Class contains error handling functions******/
class Validation{

    /*Function is called from different part of code, this makes sure that value returned is INT*/

    public static int Selection(Scanner myScanner){
        
        int userInput;

        while (true) {
            try {
                System.out.print("\nYour choice: ");
                userInput = Integer.parseInt(myScanner.nextLine());
                
                if (userInput == 0 || userInput == 1 || userInput == 2 || userInput == 3
                    || userInput == 4  || userInput == 9) {
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
