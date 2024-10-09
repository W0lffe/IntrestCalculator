import java.util.Scanner;

class Test{

    public static boolean testCase = false;

    public static void TestMain(Scanner myScanner){
        
        System.out.println("TEST CASE");
        System.out.println("1. Run");
        System.out.println("0. Go back");
        int userInput;
        
        while (true) {
            try {
                userInput = Validation.UserInput(myScanner);
                if(userInput == 1){
                    TestCase(myScanner);   
                }
                break;
            } catch (Exception e) {
                System.out.println("Error occured!");
            }
        }
    }

    private static void TestCase(Scanner myScanner){
        testCase = true;
        
        DataCollect.LinearCalc = true;
        Calculations.Calculate(12, 1.5f, 10000, myScanner, DataCollect.MONTH[0]);
        Calculations.Calculate(1, 18.0f, 10000, myScanner, DataCollect.YEAR[0]);
        DataCollect.LinearCalc = false;


        DataCollect.VolatilityCalc = true;
        Calculations.VOLATILITY_PERCENTAGE=0.15f;
        Calculations.Calculate(12, 1.5f, 10000, myScanner, DataCollect.MONTH[0]);
        Calculations.Calculate(1, 18.0f, 10000, myScanner, DataCollect.YEAR[0]);
        Calculations.VOLATILITY_PERCENTAGE=0f;
        DataCollect.VolatilityCalc = false;

        testCase = false;
    }



}

