import java.util.Scanner;

class DataCollect{

    public static final String[] YEAR = {"years", "year"};
    public static final String[] MONTH = {"months", "month"};


    /*Function collects all information needed for calculating intrest */
    public static void Collect(Scanner myScanner){

        OtherFunctions.print(2);
        
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
        Calculations.Calculate(time, percentage, myScanner, duration);
        
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

        public static float Deposit(Scanner myScanner){
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

}
