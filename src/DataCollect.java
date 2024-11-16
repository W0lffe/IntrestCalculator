import javafx.scene.layout.BorderPane;


public class DataCollect{
    
    private static final String[] YEAR = { "years", "year" };
    private static final String[] MONTH = { "months", "month" };

    public static void ModeScene(BorderPane root){

        HorizontalMenu mode = new HorizontalMenu(10,"Select one:" , "Linear Calculation", "Funds/ETF", "Stocks");
        Vertical container = new Vertical(0, "Settings for Calculation");

        container.getChildren().add(mode);
        container.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/3);
        container.getStyleClass().add("vertical");
        root.setCenter(container);
        
        mode.getButton1().setOnAction(event -> {
            Investment linearInvestment = new Investment(0, 0f, 0f, "", "", 0f, 0f, "Linear");
            CollectScene(linearInvestment, root);
        });

        mode.getButton2().setOnAction(event -> {
            Investment fundInvestment = new Funds(0, 0f, 0f, "", "", 0f, 0f, "Funds/ETF");
            CollectScene(fundInvestment, root);
        });

        VerticalInputBox stocks = new VerticalInputBox(10, "How many stocks?", "Submit");
        mode.getButton3().setOnAction(event -> {

            if(container.getChildren().contains(stocks)) {
                container.getChildren().remove(stocks);
            }
            else{
                container.getChildren().add(stocks);
            }
                
            stocks.getButton().setOnAction(submitEvent -> {
            int quantity = Utility.UserInputINT(stocks.getTextArea().getText());

            if (quantity > 0){
                Investment stockInvestment = new Stocks(0, 0f, 0f, "", "", 0f, 0f, "Stocks", quantity);
                CollectScene(stockInvestment, root);
            }
            else{
                stocks.setVBoxTitleLabel("Invalid quantity! Quantity has to be a positive number.");
            }
            });
        });

           
    }

    private static void CollectScene(Investment investment, BorderPane root){

        Vertical container = new Vertical(0, "Settings for Calculation");
        container.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/3);
        
        HorizontalMenu menu = new HorizontalMenu(10, "", "Time", "Percentage", "Deposit", "Calculate", "Cancel", "");
        container.getChildren().addAll(menu);
        root.setCenter(container);
        container.getStyleClass().add("vertical");
        
        VerticalInputBox2 investingTime = new VerticalInputBox2(10, "Set time", "Submit");
        investingTime.getChoices().getItems().addAll("Monthly", "Yearly");

        menu.getButton1().setOnAction(e -> {

            if (container.getChildren().contains(investingTime)) {
                container.getChildren().remove(investingTime);
            }
            else{
                container.getChildren().addAll(investingTime);
            }

            investingTime.getButton().setOnAction(event -> {
                int time = Utility.UserInputINT(investingTime.getTextArea().getText());

                if (time > 0 && (investingTime.getChoices().getValue() != null)) {
                    investment.setTime(time);

                    if (investingTime.getChoices().getValue().equals("Monthly")) {
                        investment.setPeriod(MONTH[0]);
                        investment.setDuration(MONTH[1]);
                    }
                    else if(investingTime.getChoices().getValue().equals("Yearly")){
                        investment.setPeriod(YEAR[0]);
                        investment.setDuration(YEAR[1]);
                    }
                    container.getChildren().remove(investingTime);
                }
                else{
                    if(time <= 0){
                        investingTime.setVBoxTitleLabel("Time must be a positive number.");
                    }
                    else if(investingTime.getChoices().getValue() == null){
                        investingTime.setVBoxTitleLabel("Please select either monthly or yearly for the investing period.");
                    }
                    else{
                        investingTime.setVBoxTitleLabel("Please enter a valid time and select either monthly or yearly for the investing period.");
                    }
                }
                
            });

        });


        VerticalInputBox percentage = new VerticalInputBox(10, "Enter estimated percentage", "Submit");
        menu.getButton2().setOnAction(e -> {

            if (container.getChildren().contains(percentage)) {
                container.getChildren().remove(percentage);
            }
            else{
                container.getChildren().addAll(percentage);
            }


            percentage.getButton().setOnAction(event -> {
                
                float userInput = Utility.UserInputFL(percentage.getTextArea().getText());
                if (userInput > 0f) {
                    investment.setPercentage(userInput);
                    container.getChildren().removeAll(percentage);
                }
                else{
                    percentage.setVBoxTitleLabel("Please input a percentage value!");
                }
            });
        });


        VerticalInputBox deposit = new VerticalInputBox(0, "", "Submit");
        menu.getButton3().setOnAction(e -> {

            if (container.getChildren().contains(deposit)) {
                container.getChildren().remove(deposit);
            }
            else{
                container.getChildren().addAll(deposit);
            }

            if(investment instanceof Stocks){
                Stocks stock = (Stocks) investment;
                deposit.setVBoxTitleLabel("Enter price per stock: ");
                
                deposit.getButton().setOnAction(event -> {
                    float price = Utility.UserInputFL(deposit.getTextArea().getText());
                    if(price > 0f){
                        float sum = stock.getQuantity() * price;
                        stock.setDeposit(sum);
                        container.getChildren().removeAll(deposit);
                    }
                    else{
                        deposit.setVBoxTitleLabel("Enter a valid price!");
                    }
                });
            }
            else{
                deposit.setVBoxTitleLabel("Enter deposit amount:");
                
                deposit.getButton().setOnAction(event -> {
                    float userInput = Utility.UserInputFL(deposit.getTextArea().getText());
                    if (userInput > 0f) {
                        investment.setDeposit(userInput);
                        container.getChildren().removeAll(deposit);
                    }
                    else{
                        deposit.setVBoxTitleLabel("Enter a valid amount!");
                    }
                });
            }
        });

        menu.getButton4().setOnAction(e -> {
            if (investment.getTime() !=0 && investment.getDeposit() !=0 && investment.getPercentage() !=0){
                Calculations.Calculate(investment, root);
            }
            else{
                container.setVBoxTitleLabel("Insufficient Setup!");
            }
                
            
        });
        
        menu.getButton5().setOnAction(e -> {
            
            root.setCenter(null);
        });

    }
}
   
      
