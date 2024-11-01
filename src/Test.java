import java.util.Scanner;

public class Test{

    private boolean testCase;
    
    private Test(boolean testCase) {
        this.testCase = testCase;
    }

    public boolean getTestCase() {
        return testCase;
    }

    private void setTestCase(boolean testCase) {
        this.testCase = testCase;
    }

    public static Test test = new Test(false);

    
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
                    break;
                }
                else if(userInput == 0){
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error occured!");
            }
        }
    }

    private static void TestCase(Scanner myScanner){
        test.setTestCase(true);
        
        Investment investment1 = new Investment(false, 12, 1.5f, 10000, 0, "months", "month", 0f, 0f);
        Investment investment2 = new Investment(false, 1, 18f, 10000, 0, "years", "year", 0f, 0f);
        Investment investment3 = new Investment(true, 12, 1.5f, 10000, 0.15f, "months", "month", 0f, 0f);
        Investment investment4 = new Investment(true, 1, 18f, 10000, 0.15f, "years", "year", 0f, 0f);
        Calculations.Calculate(myScanner, investment1);
        Calculations.Calculate(myScanner, investment2);
        Calculations.Calculate(myScanner, investment3);
        Calculations.Calculate(myScanner, investment4);

        test.setTestCase(false);
    }



}

