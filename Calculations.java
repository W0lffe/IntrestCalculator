import java.util.Scanner;

class Calculations {
    
    /*DEFINES VARIABLES*/
    private static final int OPEN_MARKET_YEARLY = 252;
    private static final int OPEN_MARKET_MONTHLY = 21;
    private static final float VOLATILITY_PERCENTAGE = 0.15f;
    
    public static void Calculate(int time, float percentage, Scanner myScanner, String period){
            
            float deposit;

            if (Test.testCase == true) {
                deposit = 10000;
            }
            else{
                deposit = DataCollect.Deposit(myScanner, 1);
            }

            float afterIntrest = deposit;
            float temp = afterIntrest;
            float increment;

            if (MainKorkolaskuri.includeVolatility == true ) {
                
                increment = CalculateVolatilityAverage(percentage, time, period);
                for (int j = 0; j < time; j++) {
                    afterIntrest += temp * increment;
                }
                percentage = (increment*100);
                //System.out.println("Calculated percent: " + percentage); for bug checking

            }      
            else{   
                increment = (percentage/100);

                for (int i = 0; i < time; i++) {
                    afterIntrest += temp * increment;
                }
            }
            OtherFunctions.print(3);

            System.out.printf("\nAfter: %d %s\nWith %.2f%%\nYour initial deposit: %.2f euros\nhas theoretically risen to: %.2f euros. \n", time, period, percentage, deposit, afterIntrest);
            float earnings = afterIntrest - deposit;
            System.out.printf("Your earnings are: %.2f euros. \n", earnings);

            Files.SaveFile(myScanner, time, percentage, deposit, afterIntrest, earnings, period);   

            MainKorkolaskuri.includeVolatility = false;


        }

    private static float CalculateVolatilityAverage(float percentage, int time, String period){   
            
        float increment;
        float newPercentage = 0;

        int days;
        if (period.equals("year")){
            days = OPEN_MARKET_YEARLY * time; //conversion to open market days
        }
        else{
            days = OPEN_MARKET_MONTHLY * time; //conversion to open market days
        }

        float percent = (percentage/100f)/(float)days;
        float volatility = VOLATILITY_PERCENTAGE/days; //15% divided by time and converted to decimal form
        float volatility_min = percent - volatility;
        float volatility_max = percent + volatility;

        for (int j = 0; j < days; j++) {
            increment = (float)(Math.random() * (volatility_max - volatility_min)) + volatility_min;
            newPercentage += increment;
        }

        //System.out.println("Volatility percentage " + newPercentage); for bug checking
        return newPercentage;
    
        }
}
