import java.util.ArrayList;
import java.util.Scanner;

class Calculations {
    
    public static ArrayList<Investment> Storage = new ArrayList<>();
    
    public static void Calculate(Scanner myScanner, Investment investment){
            float afterIntrest = investment.getDeposit();
            float temp = afterIntrest;
            float increment;

            if(investment.getVolatility()) {
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
       
            OtherFunctions.print(4);

            System.out.printf("\nAfter: %d %s\nWith %.2f%%\nYour initial deposit: %.2f euros\nhas theoretically risen to: %.2f euros. \n", investment.getTime(), investment.getPeriod(), investment.getPercentage(), investment.getDeposit(), investment.getAfterIntrest());
            investment.setEarnings(investment.getAfterIntrest()-investment.getDeposit());
            System.out.printf("Your earnings are: %.2f euros. \n", investment.getEarnings());

             if (!Test.test.getTestCase()) {
                Investment copiedInvestment = investment.clone();
                Storage.add(copiedInvestment);
            }

            Files.SaveFile(myScanner, investment);  
 
        }

    private static float CalculateVolatilityAverage(Investment investment){   
            
        float increment;
        float newPercentage = 0f;
        float average = 0f;
        float volatility;

        if (investment.getDuration().equals("month")){
            volatility = investment.getType()/12f;
        }
        else{
            volatility = investment.getType();
        }
        
        float[] increments = new float[investment.getTime()];
        float percent = (investment.getPercentage()/100f);
        float volatility_min = percent - volatility;
        float volatility_max = percent + volatility;

        for (int j = 0; j < investment.getTime(); j++) {
            increment = (float)(Math.random() * (volatility_max - volatility_min)) + volatility_min;
            increments[j] = increment;
        }

        for (float f : increments) {
                average += f;
        }

        newPercentage = (average/increments.length);
        //System.out.println("Volatility percentage " + newPercentage); //for bug checking
        return newPercentage;
    
        }

}
