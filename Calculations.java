import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

class Calculations {
    
    /*DEFINES VARIABLES*/
    public static ArrayList<String> Storage = new ArrayList<String>();
    
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
                //System.out.println("Calculated percent: " + PERCENTAGE); //for bug checking
            }
            else{
                increment = (investment.getPercentage()/100);
                for (int i = 0; i < investment.getTime(); i++) {
                    afterIntrest += temp * increment;
                }
            }
       
            OtherFunctions.print(4);

            System.out.printf("\nAfter: %d %s\nWith %.2f%%\nYour initial deposit: %.2f euros\nhas theoretically risen to: %.2f euros. \n", investment.getTime(), investment.getPeriod(), investment.getPercentage(), investment.getDeposit(), afterIntrest);
            float earnings = afterIntrest - investment.getDeposit();
            System.out.printf("Your earnings are: %.2f euros. \n", earnings);

             if (!Test.test.getTestCase()) {
                StoreData(afterIntrest, earnings, investment);
            }

            Files.SaveFile(myScanner, afterIntrest, earnings, investment);  
 
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
        //System.out.println("Volatility percentage " + (newPercentage)); //for bug checking
        return newPercentage;
    
        }

        private static void StoreData(float afterIntrest, float earnings, Investment investment){
            DecimalFormat df = new DecimalFormat("0.00");
            String toStore = "Time: " + investment.getTime() + " " + investment.getPeriod();
            toStore += "\nPercentage: " + df.format(investment.getPercentage());
            toStore += "\nDeposit: " + df.format(investment.getDeposit());
            toStore += "\nAfter intrest: " + df.format(afterIntrest);
            toStore += "\nEarnings: " + df.format(earnings);
            Storage.add(toStore);
        } 
}
