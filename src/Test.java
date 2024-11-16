import javafx.scene.layout.BorderPane;

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

    public static void TestMain(BorderPane root) {

        VerticalInputBox testCaseWindow = new VerticalInputBox(10, "Test Case", "Run");
        root.setCenter(testCaseWindow);
        testCaseWindow.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/3);
        testCaseWindow.getStyleClass().add("vertical");


        testCaseWindow.getButton().setOnAction(e -> {
            String test = TestCase(root);
            testCaseWindow.getTextArea().appendText(test);
            Utility.Delay();
        });

    }

    private static String TestCase(BorderPane root) {
        test.setTestCase(true);

        Investment investment1 = new Investment(12, 1.5f, 10000, "months", "month", 0, 0, "Linear");
        Investment investment2 = new Investment(1, 18f, 10000, "years", "year", 0, 0, "Linear");

        Funds fund1 = new Funds(12, 1.5f, 10000, "months", "month", 0, 0, "Funds/ETF");
        Funds fund2 = new Funds(1, 18f, 10000, "years", "year", 0, 0, "Funds/ETF");

        Stocks stock1 = new Stocks(12, 1.5f, 1008, "months", "month", 0, 0, "Stocks", 60);
        Stocks stock2 = new Stocks(1, 18f, 1008, "years", "year", 0, 0, "Stocks", 60);

        Calculations.Calculate(investment1, root);
        Calculations.Calculate(investment2, root);
        Calculations.Calculate(fund1, root);
        Calculations.Calculate(fund2, root);
        Calculations.Calculate(stock1, root);
        Calculations.Calculate(stock2, root);

        test.setTestCase(false);

        return "Test completed! Inspect calculations from file.";
    }
}
