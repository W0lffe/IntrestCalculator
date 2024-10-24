public class Investment {

    private boolean volatility;
    private int time;
    private float percentage;
    private float deposit;
    private float type;
    private String period;
    private String duration;
    
    
    public Investment(boolean volatility, int time, float percentage, float deposit, float type, String period, String duration) {
        this.volatility = volatility;
        this.time = time;
        this.percentage = percentage;
        this.deposit = deposit;
        this.type = type;
        this.period = period;
        this.duration = duration;

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



}
