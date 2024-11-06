import java.util.Scanner;

public class Test {

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

    public static void TestMain(Scanner myScanner) {

        System.out.println("TEST CASE");
        System.out.println("1. Run");
        System.out.println("0. Go back");
        int userInput;

        while (true) {
            try {
                userInput = Validation.UserInput(myScanner);
                if (userInput == 1) {
                    TestCase(myScanner);
                    break;
                } else if (userInput == 0) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error occured!");
            }
        }
    }

    private static void TestCase(Scanner myScanner) {
        test.setTestCase(true);

        Investment investment1 = new Investment(12, 1.5f, 10000, "months", "month", 0, 0, "Linear");
        Investment investment2 = new Investment(1, 18f, 10000, "years", "year", 0, 0, "Linear");

        Funds fund1 = new Funds(12, 1.5f, 10000, "months", "month", 0, 0, "Funds/ETF");
        Funds fund2 = new Funds(1, 18f, 10000, "years", "year", 0, 0, "Funds/ETF");

        Stocks stock1 = new Stocks(12, 1.5f, 1008, "months", "month", 0, 0, "Stocks", 60);
        Stocks stock2 = new Stocks(1, 18f, 1008, "years", "year", 0, 0, "Stocks", 60);

        Calculations.Calculate(myScanner, investment1);
        Calculations.Calculate(myScanner, investment2);
        Calculations.Calculate(myScanner, fund1);
        Calculations.Calculate(myScanner, fund2);
        Calculations.Calculate(myScanner, stock1);
        Calculations.Calculate(myScanner, stock2);

        test.setTestCase(false);
    }

}
