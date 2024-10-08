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
                userInput = Validation.Selection(myScanner);
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
        
        Calculations.Calculate(12, 2.7f, myScanner, DataCollect.MONTH[0]);
        Calculations.Calculate(1, 12.7f, myScanner, DataCollect.YEAR[0]);

        MainKorkolaskuri.includeVolatility = true;
        Calculations.Calculate(12, 3.9f, myScanner, DataCollect.MONTH[0]);
        MainKorkolaskuri.includeVolatility = true;
        Calculations.Calculate(1, 25.4f, myScanner, DataCollect.YEAR[0]);

        testCase = false;
    }



}

