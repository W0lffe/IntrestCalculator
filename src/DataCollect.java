import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataCollect{

    private static final String[] YEAR = { "years", "year" };
    private static final String[] MONTH = { "months", "month" };

    public static void ModeScene(Stage primaryStage){

            Container1 mode = new Container1(10,"Select one:" , "Linear Calculation", "Funds/ETF", "Stocks");
        
            mode.getButton1().setOnAction(event -> {
                Investment linearInvestment = new Investment(0, 0f, 0f, "", "", 0f, 0f, "Linear");
                CollectScene(linearInvestment, primaryStage);
            });

            mode.getButton2().setOnAction(event -> {
                Investment fundInvestment = new Funds(0, 0f, 0f, "", "", 0f, 0f, "Funds/ETF");
                CollectScene(fundInvestment, primaryStage);
            });

            mode.getButton3().setOnAction(event -> {

                inputBox1 stocks = new inputBox1(10, "How many stocks?", "Submit", "");
                mode.getChildren().addAll(stocks);
                
                stocks.getButton().setOnAction(submitEvent -> {
                    int quantity = Utility.UserInputINT(stocks.getTextArea().getText());

                    if (quantity > 0){
                        Investment stockInvestment = new Stocks(0, 0f, 0f, "", "", 0f, 0f, "Stocks", quantity);
                        CollectScene(stockInvestment, primaryStage);
                    }
                    else{
                        stocks.setText("Invalid quantity!");
                    }
                });
            });

            Scene modeScene = new Scene(mode, 600, 400);
            primaryStage.setScene(modeScene);
    }

    private static void CollectScene(Investment investment, Stage primaryStage){

        Container2 collect = new Container2(10, "Settings for Calculation", "Time", "Percentage",
                                             "Deposit", "Calculate", "Main Menu");
       
        
  
    
        collect.getButton1().setOnAction(e -> {
            inputBox2 investingTime = new inputBox2(10, "Set time", "Submit", "Monthly", "Yearly");
            
            collect.getChildren().addAll(investingTime);

            investingTime.getButton().setOnAction(event -> {
                int time = Utility.UserInputINT(investingTime.getTextArea().getText());
                boolean monthly = investingTime.getCb1().isSelected();
                boolean yearly = investingTime.getCb2().isSelected();


                if (time > 0 && (monthly || yearly)) {
                    investment.setTime(time);

                    if (monthly) {
                        investment.setPeriod(MONTH[0]);
                        investment.setDuration(MONTH[1]);
                    }
                    else if(yearly){
                        investment.setPeriod(YEAR[0]);
                        investment.setDuration(YEAR[1]);
                    }
                    collect.getChildren().remove(investingTime);
                }
                else{
                    if(time <= 0){
                        investingTime.setTitle("Time must be a positive number.");
                    }
                    else if(!monthly || !yearly){
                        investingTime.setTitle("Please select either monthly or yearly for the payment period.");
                    }
                    else{
                        investingTime.setTitle("Please enter a valid time and select either monthly or yearly for the payment period.");
                    }
                }
                
            });

        });

        collect.getButton2().setOnAction(e -> {
            inputBox percentage = new inputBox(10, "Enter estimated percentage", "Submit");
            collect.getChildren().addAll(percentage);

            percentage.getButton().setOnAction(event -> {
                
                float userInput = Utility.UserInputFL(percentage.getTextArea().getText());
                if (userInput > 0f) {
                    investment.setPercentage(userInput);
                    collect.getChildren().removeAll(percentage);
                }
                else{
                    collect.setTitle("Please input a percentage value!");
                }
            });
        });

        collect.getButton3().setOnAction(e -> {

            inputBox1 deposit = new inputBox1(0, "", "Submit", "");
            collect.getChildren().addAll(deposit);

            if(investment instanceof Stocks){
                Stocks stock = (Stocks) investment;
                deposit.setTitle("Enter price per stock: ");
                
                deposit.getButton().setOnAction(event -> {
                    float price = Utility.UserInputFL(deposit.getTextArea().getText());
                    if(price > 0f){
                        float sum = stock.getQuantity() * price;
                        stock.setDeposit(sum);
                        collect.getChildren().removeAll(deposit);
                    }
                    else{
                        deposit.setText("Enter a valid price!");
                    }
                });
            }
            else{
                deposit.setTitle("Enter deposit amount:");
                
                deposit.getButton().setOnAction(event -> {
                    float userInput = Utility.UserInputFL(deposit.getTextArea().getText());
                    if (userInput > 0f) {
                        investment.setDeposit(userInput);
                        collect.getChildren().removeAll(deposit);
                    }
                    else{
                        deposit.setText("Enter a valid price!");
                    }
                });
            }
        });

        collect.getButton4().setOnAction(e -> {
            //Calculations.Calculate(investment, primaryStage);
        });
        
        collect.getButton5().setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });

        Scene dataCollectScene = new Scene(collect, 600, 400);
        primaryStage.setScene(dataCollectScene);
    }
}
   
      
