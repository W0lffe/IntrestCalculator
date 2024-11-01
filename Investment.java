import java.text.DecimalFormat;
import java.util.List;

public class Investment {

    private boolean volatility;
    private int time;
    private float percentage;
    private float deposit;
    private float type;
    private String period;
    private String duration;
    private float afterIntrest;
    private float earnings;
    private int id;
    private List<Investment> Data;
    
    public Investment(boolean volatility, int time, float percentage, float deposit, float type, String period, String duration, float afterIntrest, float earnings) {
        this.volatility = volatility;
        this.time = time;
        this.percentage = percentage;
        this.deposit = deposit;
        this.type = type;
        this.period = period;
        this.duration = duration;
        this.afterIntrest = afterIntrest;
        this.earnings = earnings;

    }
    
    public Investment clone(){
        Investment copy = new Investment(volatility, time, percentage, deposit, type, period, duration, afterIntrest, earnings);
        copy.deposit = this.deposit;
        copy.time = this.time;
        copy.percentage = this.percentage;
        copy.volatility = this.volatility;
        copy.type = this.type;
        copy.period = this.period;
        copy.duration = this.duration;
        copy.afterIntrest = this.afterIntrest;
        copy.earnings = this.earnings;

        return copy;
    }

    public int getId() {
        return id;
    }

    public String getPeriod() {
        return period;
    }


    public void setPeriod(String period) {
        this.period = period;
    }


    public String getDuration() {
        return duration;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean getVolatility() {
        return volatility;
    }


    public void setVolatility(boolean volatility) {
        this.volatility = volatility;
    }


    public int getTime() {
        return time;
    }


    public void setTime(int time) {
        this.time = time;
    }


    public float getPercentage() {
        return percentage;
    }


    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }


    public float getDeposit() {
        return deposit;
    }


    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }


    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }

    public float getAfterIntrest() {
        return afterIntrest;
    }


    public void setAfterIntrest(float afterIntrest) {
        this.afterIntrest = afterIntrest;
    }


    public float getEarnings() {
        return earnings;
    }


    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    public List<Investment> getData() {
        return Data;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String enabled;
        if (getVolatility()) {
            enabled = "Yes";
        }
        else{
            enabled = "No";
        }
        return "Volatility: " + enabled + 
                "\nTime: " + time + " " + period + 
                "\nPercentage: " + df.format(percentage) + "%" +
                "\nDeposit: " + df.format(deposit) + "€" +
                "\nAfter intrest: " + df.format(afterIntrest) + "€" +
                "\nEarnings: " + df.format(earnings) + "€" +
                "\n";
    }


}
