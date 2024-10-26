import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class DataCollect{

    private static final String[] YEAR = {"years", "year"};
    private static final String[] MONTH = {"months", "month"};
   
    private static ArrayList<Integer[]> validInputs = new ArrayList<>(Arrays.asList(
        new Integer[]{0, 1, 2},
        new Integer[]{0, 1, 2, 3, 4, 5, 9},
        new Integer[]{0, 1, 2, 3}
    ));

    private static Investment investment = new Investment(false, 0, 0, 0, 0, null, null, 0, 0);


    private static void Mode(Scanner myScanner){
        OtherFunctions.print(2);
        int userInput;

        while (true) {
            userInput = Validation.UserInput(myScanner);
                if (Arrays.asList(validInputs.get(0)).contains(userInput)) {
                    break;       
                }
                else{
                    System.out.println("Invalid choice!\n");
                }
        }
            switch (userInput) {
                case 1:
                    investment.setVolatility(false);
                    System.out.println("\nYou chose: Linear calculation!\n");
                    break;
                case 2:
                    investment.setVolatility(true);
                    System.out.println("\nYou chose: Volatility included calculation!\n");
                    break;
                case 0:
                    return;
            }

    }

    /*Function collects all information needed for calculating intrest */
    public static void CollectData(Scanner myScanner){
    
        int userInput;  
        do {
            OtherFunctions.print(3);

            do {
                userInput = Validation.UserInput(myScanner);
                if(Arrays.asList(validInputs.get(1)).contains(userInput)){         
                    break;
                }
                else{
                    System.out.println("Invalid choice!");
                }

            } while (true);

            switch (userInput) {
                case 1:
                    Mode(myScanner);
                    break;
                case 2:
                    InputTime(myScanner);
                    break;
                case 3:
                    if (investment.getTime() != 0) {
                        InputPercentage(myScanner);
                        break;
                    }
                    else{
                        System.out.println("Please enter time first!\n");
                        break;
                    }
                case 4:
                    InputDeposit(myScanner);
                    break;
                case 5:
                    if (investment.getVolatility()) {
                        InvestmentType(myScanner);  
                        break;

                    }
                    else{
                        System.out.println("You have chosen volatility calculation!");
                        break;
                    }
                case 9:
                    if (investment.getTime() !=0 && investment.getPercentage() !=0 && investment.getDeposit() !=0){
                        Calculations.Calculate(myScanner, investment);
                        break;
                    }
                    else{
                        System.out.println("Insufficient Setup!\n");
                    }
                case 0:
                    ResetValues();
                    return;
    
            }
        } while (userInput != 0);
                
        }

        private static void InputTime(Scanner myScanner){
            System.out.println("\n1. Yearly\n2. Monthly\n0. Go Back");
            int userInput;
            while (true) {
                userInput = Validation.UserInput(myScanner);
                if (Arrays.asList(validInputs.get(0)).contains(userInput)) {
                    break;   
                }
                else{
                    System.out.println("Invalid choice!");
                }
            }

            switch (userInput) {
                case 1:
                    System.out.println("You chose yearly expectation.");
                    investment.setPeriod(YEAR[0]);
                    investment.setDuration(YEAR[1]);
                    break;
                case 2:
                    System.out.println("You chose monthly expectation.");
                    investment.setPeriod(MONTH[0]);
                    investment.setDuration(MONTH[1]);
                    break;
                case 0:
                    return;
            }

            investment.setTime(Validation.Time(myScanner, investment.getPeriod()));
         
        } 

        private static void InputPercentage(Scanner myScanner){
            float userInput;
    
            while (true) {
                try {
                    System.out.printf("Enter estimated growth percentage per %s: ", investment.getDuration());
                    userInput = Float.parseFloat(myScanner.nextLine());
                    if (userInput > 0 || userInput < 0) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("\nUnknown input format! Enter again!\n");
                }
            }
            investment.setPercentage(userInput);
        }

        private static void InputDeposit(Scanner myScanner){
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

            investment.setDeposit(userInput);

        }

        private static void InvestmentType(Scanner myScanner){
            OtherFunctions.print(5);
            int userInput;
            while (true) {
                userInput = Validation.UserInput(myScanner);
                if(Arrays.asList(validInputs.get(2)).contains(userInput)){
                    break;
                }
                else{
                    System.out.println("Invalid choice!");
                }
            }

            switch (userInput) {
                case 1:
                    investment.setType(0.10f);
                    break;
                case 2:
                    investment.setType(0.20f);
                    break;
                case 3:
                    investment.setType(0.40f);
                    break;
                case 0:
                    return;
            }

        }

        private static void ResetValues(){
            investment.setDeposit(0);
            investment.setDuration("");
            investment.setPercentage(0);
            investment.setPeriod("");
            investment.setTime(0);
            investment.setType(0);
            investment.setVolatility(false);
        }

}
