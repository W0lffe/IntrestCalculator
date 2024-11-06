import java.util.ArrayList;
import java.util.Scanner;

class Calculations {
    
    public static ArrayList<Investment> Storage = new ArrayList<>();
    
    public static void Calculate(Scanner myScanner, Investment investment){
            float afterIntrest = investment.getDeposit();
            float temp = afterIntrest;
            float increment;

            if(investment instanceof Stocks || investment instanceof Funds) {
                increment = CalculateVolatilityAverage(investment);

                for (int i = 0; i < investment.getTime(); i++) {
                    afterIntrest += temp * increment;
                }
                investment.setPercentage((increment*100));
                //System.out.println("Calculated percent: " + investment.getPercentage()); //for bug checking
            }
            else{
                increment = (investment.getPercentage()/100);

                for (int i = 0; i < investment.getTime(); i++) {
                    afterIntrest += temp * increment;
                }
            }
            investment.setAfterIntrest(afterIntrest);
       
            Utility.print(4);

            System.out.printf("\nAfter: %d %s\nWith %.2f%%\nYour initial deposit: %.2f euros\nhas theoretically risen to: %.2f euros. \n", investment.getTime(), investment.getPeriod(), investment.getPercentage(), investment.getDeposit(), investment.getAfterIntrest());
            investment.setEarnings(investment.getAfterIntrest()-investment.getDeposit());
            System.out.printf("Your earnings are: %.2f euros. \n", investment.getEarnings());

             if (!Test.test.getTestCase()) {
                Investment copiedInvestment = investment.Clone();
                Storage.add(copiedInvestment);
            }

            Files.SaveFile(myScanner, investment);  
 
        }

    private static float CalculateVolatilityAverage(Investment investment){   
        float increment;
        float newPercentage = 0f;
        float average = 0f;
        float volatility = 0f;
        
        if (investment instanceof Stocks) {
            Stocks stock = (Stocks)investment;
            
            if (stock.getDuration().equals("month")){
                volatility = stock.getVolatility()/12f;
            }
            else{
                volatility = stock.getVolatility();
            }
        }
        else if(investment instanceof Funds){
            Funds fund = (Funds)investment;

            if (fund.getDuration().equals("month")){
                volatility = fund.getVolatility()/12f;
            }
            else{
                volatility = fund.getVolatility();
            }
        }
       
        
        float[] increments = new float[investment.getTime()];
        float percent = (investment.getPercentage()/100f);
        float volatility_min = percent - volatility;
        float volatility_max = percent + volatility;

        for (int j = 0; j < investment.getTime(); j++) {
            increment = (float)(Math.random() * (volatility_max - volatility_min)) + volatility_min;
            increments[j] = increment;
        }

        for (float increase : increments) {
                average += increase;
        }

        newPercentage = (average/increments.length);
        //System.out.println("Volatility percentage " + newPercentage); //for bug checking
        return newPercentage;
    
        }

}
