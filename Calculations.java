import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

class Calculations {
    
    /*DEFINES VARIABLES*/
    public static float VOLATILITY_PERCENTAGE = 0;
    public static ArrayList<String> Storage = new ArrayList<String>();
    
    public static void Calculate(int time, float percentage, float deposit, Scanner myScanner, String period){
            float afterIntrest = deposit;
            float temp = afterIntrest;
            float increment;

            String method = DataCollect.CheckMode();

            switch (method) {
                case "Linear":
                    increment = (percentage/100);
                    for (int i = 0; i < time; i++) {
                        afterIntrest += temp * increment;
                    }
                    break;
                case "Volatility":
                    increment = CalculateVolatilityAverage(percentage, time, period);
                    for (int i = 0; i < time; i++) {
                        afterIntrest += temp * increment;
                    }
                    percentage = (increment*100);
                    //System.out.println("Calculated percent: " + percentage); //for bug checking
                    break;
            }
          
            OtherFunctions.print(4);

            System.out.printf("\nAfter: %d %s\nWith %.2f%%\nYour initial deposit: %.2f euros\nhas theoretically risen to: %.2f euros. \n", time, period, percentage, deposit, afterIntrest);
            float earnings = afterIntrest - deposit;
            System.out.printf("Your earnings are: %.2f euros. \n", earnings);

            if (Test.testCase==false) {
                StoreData(time, period, percentage, deposit, afterIntrest, earnings);
            }

            Files.SaveFile(myScanner, time, percentage, deposit, afterIntrest, earnings, period);   

        }

    private static float CalculateVolatilityAverage(float percentage, int time, String period){   
            
        float increment;
        float newPercentage = 0f;
        float average = 0f;
        float volatility;

        if (period.equals("month")){
            volatility = VOLATILITY_PERCENTAGE/12f;
        }
        else{
            volatility = VOLATILITY_PERCENTAGE;
        }
        
        float[] increments = new float[time];
        float percent = (percentage/100f);
        float volatility_min = percent - volatility;
        float volatility_max = percent + volatility;

        for (int j = 0; j < time; j++) {
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

        private static void StoreData(int time, String period, float percentage, float deposit, float afterIntrest, float earnings){
            DecimalFormat df = new DecimalFormat("0.00");
            String toStore = "Time: " + time + " " + period;
            toStore += "\nPercentage: " + df.format(percentage);
            toStore += "\nDeposit: " + df.format(deposit);
            toStore += "\nAfter intrest: " + df.format(afterIntrest);
            toStore += "\nEarnings: " + df.format(earnings);
            Storage.add(toStore);
        }
}
