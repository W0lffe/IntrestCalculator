import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class DataCollect{

    private static final String[] YEAR = {"years", "year"};
    private static final String[] MONTH = {"months", "month"};
   
    private static final ArrayList<Integer[]> validInputs = new ArrayList<>(Arrays.asList(
        new Integer[]{0, 1, 2, 3, 4, 5, 9},
        new Integer[]{0, 1, 2, 3},
        new Integer[]{0, 1, 2}
    ));

    public static void Mode(Scanner myScanner){
        Utility.print(2);
        int userInput;

        while (true) {
            userInput = Validation.UserInput(myScanner);
                if (Arrays.asList(validInputs.get(1)).contains(userInput)) {
                    break;       
                }
                else{
                    System.out.println("Invalid choice!\n");
                }
        }
            switch (userInput) {
                case 1:
                    System.out.println("\nYou chose: Linear calculation!\n");
                    Investment linear = new Investment(0, 0f, 0f, "", "", 0f, 0f, "Linear");
                    CollectData(myScanner, linear);
                    break;
                case 2:
                    System.out.println("\nYou chose: Stocks!\n");
                    Investment stocks = new Stocks(0, 0f, 0f, "", "", 0f, 0f, "Stocks", 0);
                    CollectData(myScanner, stocks);
                    break;
                case 3:
                    System.out.println("\nYou chose: Funds/ETF!\n");
                    Investment funds = new Funds(0, 0f, 0f, "", "", 0f, 0f, "Funds/ETF");
                    CollectData(myScanner, funds);
                    break;
                case 0:
                    return;
            }

    }

    /*Function collects all information needed for calculating intrest */
    private static void CollectData(Scanner myScanner, Investment investment){
        
        int userInput;  
        do {
            Utility.print(3);

            do {
                userInput = Validation.UserInput(myScanner);
                if(Arrays.asList(validInputs.get(0)).contains(userInput)){         
                    break;
                }
                else{
                    System.out.println("Invalid choice!");
                }

            } while (true);

            switch (userInput) {
                case 1:
                    InputTime(myScanner, investment);
                    break;
                case 2:
                    if(investment.getTime() != 0) {
                        InputPercentage(myScanner, investment);
                        break;
                    }
                    else{
                        System.out.println("Please enter time first!\n");
                        break;
                    }
                case 3:
                    InputDeposit(myScanner, investment);
                    break;
                case 4:
                    System.out.printf("Type: %s \nTime: %d %s \nPercentage: %.2f \nDeposit: %.2f \n", investment.getType(), investment.getTime(), 
                                            investment.getPeriod(), investment.getPercentage(), investment.getDeposit());
                    break;
                case 9:
                    if (investment.getTime() !=0 && investment.getPercentage() !=0 && investment.getDeposit() !=0){
                        Calculations.Calculate(myScanner, investment);
                        break;
                    }
                    else{
                        System.out.println("Insufficient Setup!\n");
                    }
                case 0:
                    return;
    
            }
        } while (userInput != 0);
                
        }

        private static void InputTime(Scanner myScanner, Investment investment){
            System.out.println("\n1. Yearly\n2. Monthly\n0. Go Back");
            int userInput;
            while (true) {
                userInput = Validation.UserInput(myScanner);
                if (Arrays.asList(validInputs.get(2)).contains(userInput)) {
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

        private static void InputPercentage(Scanner myScanner, Investment investment){
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

        private static void InputDeposit(Scanner myScanner, Investment investment){
            float userInput;
            String toPrint = "";
            int stocksQuantity;
            
            if (investment instanceof Stocks){
                toPrint = "Enter price per stock: ";
            }
            else{
                toPrint = "Enter initial deposit amount: ";
            }

            while (true) {
                try {
                    System.out.print(toPrint);
                    userInput = Float.parseFloat(myScanner.nextLine());
                    if (userInput > 0) {
                        break;
                    }
                    else{
                        System.out.println("Invalid amount!");
                    }
                } catch (Exception e) {
                    System.out.println("\nUnknown input format! \nEnter again!");
                }
            }

            if (investment instanceof Stocks){
                float sum;

                while (true) {
                    try {
                        System.out.print("Enter amount of stocks you want to buy: ");
                        stocksQuantity = Integer.parseInt(myScanner.nextLine());
                        if (stocksQuantity > 0) {
                            sum = stocksQuantity * userInput;
                            investment.setDeposit(sum);
                            Stocks stock = (Stocks)investment;
                            stock.setQuantity(stocksQuantity);
                            break;
                        }
                        else{
                            System.out.println("Invalid amount!");
                        }
                    } catch (Exception e) {
                        System.out.println("\nUnknown input format! \nEnter again!");
                    }
                }
            }
            else{
                investment.setDeposit(userInput);
            }
        }

}
