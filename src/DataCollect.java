import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataCollect{

    private static final String[] YEAR = { "years", "year" };
    private static final String[] MONTH = { "months", "month" };

    public static void ModeScene(Stage primaryStage){


            VBox container = new VBox(5);
            VBox inputBox = new VBox(5);
        
            Label select = new Label("Select one:");
            Button linear = new Button("Linear Calculation");
            Button funds = new Button("Funds/ETF");
            Button stocks = new Button("Stocks");

            linear.setOnAction(event -> {
                Investment linearInvestment = new Investment(0, 0f, 0f, "", "", 0f, 0f, "Linear");
                CollectScene(linearInvestment, primaryStage);
            });

            funds.setOnAction(event -> {
                Investment fundInvestment = new Funds(0, 0f, 0f, "", "", 0f, 0f, "Funds/ETF");
                CollectScene(fundInvestment, primaryStage);
            });

            stocks.setOnAction(event -> {
                Label amount = new Label("How many stocks?");
                TextField stockAmount = new TextField();
                stockAmount.setMaxWidth(150);
                Button submit = new Button("Submit");
                Label info = new Label("");
              
                inputBox.getChildren().addAll(info, amount, stockAmount, submit);
                
                submit.setOnAction(submitEvent -> {
                    int quantity = Utility.UserInputINT(stockAmount.getText());

                    if (quantity > 0){
                        Investment stockInvestment = new Stocks(0, 0f, 0f, "", "", 0f, 0f, "Stocks", quantity);

                        inputBox.getChildren().removeAll(info, amount, stockAmount, submit);
                        CollectScene(stockInvestment, primaryStage);
                    }
                    else{
                        info.setText("Invalid quantity!");
                    }
                });
            });

            container.getChildren().addAll(select, linear, funds, stocks, inputBox);
            Scene modeScene = new Scene(container, 600, 400);
            primaryStage.setScene(modeScene);
    }

    private static void CollectScene(Investment investment, Stage primaryStage){
        TextField textField = new TextField();
        Button submit = new Button("Submit");
        textField.setMaxWidth(150);

        Label info = new Label("");
        
        VBox container = new VBox(5);
        VBox verticalBox = new VBox(15);
        VBox inputBox = new VBox(5);

        Label collect = new Label("Settings for Calculation");
        Button inputTime = new Button("Time");
        Button inputPercentage = new Button("Percentage");
        Button inputDeposit = new Button("Deposit");
        Button calculate = new Button("Calculate");
        Button backToMainMenu = new Button("Main Menu");
        
        inputTime.setOnAction(e -> {
            CheckBox monthly = new CheckBox();
            Label month = new Label("Monthly");
            CheckBox yearly = new CheckBox();
            Label year = new Label("Yearly");
            
            inputBox.getChildren().addAll(info, monthly, month,  yearly, year, textField, submit);
       
            submit.setOnAction(event -> {
                int time = Utility.UserInputINT(textField.getText());

                if (time > 0 && (monthly.isSelected() || yearly.isSelected())) {
                    investment.setTime(time);

                    if (monthly.isSelected()) {
                        investment.setPeriod(MONTH[0]);
                        investment.setDuration(MONTH[1]);
                    }
                    else if(yearly.isSelected()){
                        investment.setPeriod(YEAR[0]);
                        investment.setDuration(YEAR[1]);
                    }

                    info.setText("");
                    textField.clear();
                    inputBox.getChildren().removeAll(info, textField,submit, monthly, yearly, month, year);
                }
                else{
                    if(time <= 0){
                        info.setText("Time must be a positive number.");
                    }
                    else if(!monthly.isSelected() || !yearly.isSelected()){
                        info.setText("Please select either monthly or yearly for the payment period.");
                    }
                    else{
                        info.setText("Please enter a valid time and select either monthly or yearly for the payment period.");
                    }
                }
                
            });

        });

        inputPercentage.setOnAction(e -> {
            Label percent = new Label("Enter percentage:");
          
            inputBox.getChildren().addAll(info, percent, textField, submit);

            submit.setOnAction(event -> {
                float percentage = Utility.UserInputFL(textField.getText());
                if (percentage > 0f) {
                    investment.setPercentage(percentage);
                    info.setText("");
                    textField.clear();
                    inputBox.getChildren().removeAll(info, percent, textField, submit);
                }
                else{
                    info.setText("Please input a percentage value!");
                }
            });
        });

        inputDeposit.setOnAction(e -> {
            Label depositLabel = new Label("");
          
            inputBox.getChildren().addAll(info, depositLabel, textField, submit);

            if(investment instanceof Stocks){
                Stocks stock = (Stocks) investment;
                depositLabel.setText("Enter price per stock: ");
                
                submit.setOnAction(event -> {
                    float price = Utility.UserInputFL(textField.getText());
                    if(price > 0f){
                        float deposit = stock.getQuantity() * price;
                        stock.setDeposit(deposit);
                        textField.clear();
                        inputBox.getChildren().removeAll(info, depositLabel, textField, submit);
                        
                    }
                    else{
                        info.setText("Enter a valid price!");
                    }
                });
            }
            else{
                depositLabel.setText("Enter deposit amount:");
                
                submit.setOnAction(event -> {
                    float deposit = Utility.UserInputFL(textField.getText());
                    if (deposit > 0f) {
                        investment.setDeposit(deposit);
                        textField.clear();
                        inputBox.getChildren().removeAll(info, depositLabel, textField, submit);

                    }
                    else{
                        info.setText("Enter a valid amount!");
                    }
                });
            }
        });

        calculate.setOnAction(e -> {
            Calculations.Calculate(investment, primaryStage);
        });
        
        backToMainMenu.setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });

        verticalBox.getChildren().addAll(collect,inputTime,inputDeposit,inputPercentage,calculate, backToMainMenu);
        container.getChildren().addAll(verticalBox, inputBox);
        Scene dataCollectScene = new Scene(container, 600, 400);
        primaryStage.setScene(dataCollectScene);
    }
}
   
      
