import java.util.Scanner;

class DataCollect{

    public static final String[] YEAR = {"years", "year"};
    public static final String[] MONTH = {"months", "month"};
    public static boolean LinearCalc = false;
    public static boolean VolatilityCalc = false;
    public static boolean IntervalCalc = false;
    public static boolean yearlyExpectation = false;
    public static boolean monthlyExpectation = false;

    public static void Mode(Scanner myScanner){
        OtherFunctions.print(2);
        int userInput;

      
        while (true) {
            userInput = Validation.Selection(myScanner);
                if (userInput == 0 || userInput == 1 || userInput == 2 || userInput == 3) {
                    break;       
                }
        }
            switch (userInput) {
                case 1:
                    LinearCalc = true;
                    System.out.println("\nYou chose: Linear calculation!\n");
                    break;
                case 2:
                    VolatilityCalc = true;
                    System.out.println("\nYou chose: Including volatility calculation!\n");
                    break;
                case 3:
                    IntervalCalc = true;
                    System.out.println("\nYou chose: Interval calculation!\n");
                    break;
                case 0:
                    return;
            }

            Collect(myScanner);
    }

    /*Function collects all information needed for calculating intrest */
    private static void Collect(Scanner myScanner){
    
        int userInput;
        int time = 0;
        String duration = "";
        String period = "";
        float percentage = 0;
        float deposit = 0;
        
        do {
            OtherFunctions.print(3);

            do {
                userInput = Validation.Selection(myScanner);
                if(userInput == 0 || userInput == 1 || userInput == 2 || userInput == 3 
                || userInput == 4 || userInput == 9){         
                    break;
                }
            } while (true);

            switch (userInput) {
                case 1:
                    time = askTime(myScanner);
                    if (yearlyExpectation == true) {
                        duration = YEAR[0];
                        period = YEAR[1];
                    }
                    else{
                        duration = MONTH[0];
                        period = MONTH[1];
                    }
                    break;
                case 2:
                    percentage = askPercentage(myScanner, period);
                    break;
                case 3:
                    deposit = Deposit(myScanner);
                    break;
                case 4:
                    Calculations.VOLATILITY_PERCENTAGE=InvestmentType(myScanner);
                    break;
                case 9:
                    LinearCalc = false;
                    VolatilityCalc = false;
                    IntervalCalc = false;
                    yearlyExpectation = false;
                    monthlyExpectation = false;
                    return;
                case 0:
                if (time !=0 && percentage !=0 && deposit !=0){
                    Calculations.Calculate(time, percentage, deposit, myScanner, duration);
                    break;
                }
                else{
                    System.out.println("Insufficient Setup!\n");
                }
                    
            }
        } while (userInput != 9);
                
        }

        private static int askTime(Scanner myScanner){
            System.out.println("\n1. Yearly\n2. Monthly");
            int choice, time;
            String duration;
            while (true) {
                choice = Validation.Selection(myScanner);
                if (choice == 1 || choice == 2) {
                    break;   
                }
            }
            switch (choice) {
                case 1:
                    System.out.println("You chose yearly expectation.");
                    yearlyExpectation = true;
                    break;
                case 2:
                    System.out.println("You chose monthly expectation.");
                    monthlyExpectation = true;
                    break;
            }

            if (yearlyExpectation == true) {
                duration = YEAR[0];
            }
            else{
                duration = MONTH[0];
            }
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

        private static float InvestmentType(Scanner myScanner){
            OtherFunctions.print(5);
            int userInput;
            float volatility = 0f;
            while (true) {
                userInput = Validation.Selection(myScanner);
                if(userInput == 1 || userInput == 2 || userInput == 3){
                    break;
                }
            }

            switch (userInput) {
                case 1:
                    volatility = 0.10f;
                    break;
                case 2:
                    volatility = 0.18f;
                    break;
                case 3:
                    volatility = 0.40f;
                    break;
            }

            return volatility;
        }

        public static String checkMode(){

            if (VolatilityCalc==true) {
                return "Volatility";
            }
            else if(IntervalCalc==true){
                return "Interval";
            }
            else{
                return "Linear";
            }
        }

}
