import java.text.DecimalFormat;
import java.util.List;

public class Investment {
    public static DecimalFormat df = new DecimalFormat("0.00");

    private int time;
    private float percentage;
    private float deposit;
    private String period;
    private String duration;
    private float afterIntrest;
    private float earnings;
    private int id;
    private List<Investment> Data;
    private String type;

    public Investment(int time, float percentage, float deposit, String period, String duration, float afterIntrest,
            float earnings, String type) {
        this.time = time;
        this.percentage = percentage;
        this.deposit = deposit;
        this.period = period;
        this.duration = duration;
        this.afterIntrest = afterIntrest;
        this.earnings = earnings;
        this.type = type;

    }

    public Investment Clone() {
        Investment copy = new Investment(
                getTime(),
                getPercentage(),
                getDeposit(),
                getPeriod(),
                getDuration(),
                getAfterIntrest(),
                getEarnings(),
                getType());

        return copy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getId() {
        return id;
    }

    public List<Investment> getData() {
        return Data;
    }

    @Override
    public String toString() {
        return "Type: " + type +
                "\nTime: " + time + " " + period +
                "\nPercentage: " + df.format(percentage) + "%" +
                "\nDeposit: " + df.format(deposit) + "€" +
                "\nAfter intrest: " + df.format(afterIntrest) + "€" +
                "\nEarnings: " + df.format(earnings) + "€" +
                "\n";
    }

}

class Stocks extends Investment {

    private float volatility;
    private int quantity;

    public Stocks(int time, float percentage, float deposit, String period, String duration, float afterIntrest,
            float earnings, String type, int quantity) {

        super(time, percentage, deposit, period, duration, afterIntrest, earnings, type);
        this.volatility = 0.40f;
        this.quantity = quantity;
    }

    public float getVolatility() {
        return volatility;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Type: " + getType() +
                "\nTime: " + getTime() + " " + getPeriod() +
                "\nPercentage: " + df.format(getPercentage()) + "%" +
                "\nTotal price of stocks: " + df.format(getDeposit()) + "€" +
                "\nAmount of stocks: " + getQuantity() +
                "\nAfter intrest: " + df.format(getAfterIntrest()) + "€" +
                "\nEarnings: " + df.format(getEarnings()) + "€" +
                "\n";
    }

    public Stocks Clone() {
        Stocks copy = new Stocks(
                getTime(),
                getPercentage(),
                getDeposit(),
                getPeriod(),
                getDuration(),
                getAfterIntrest(),
                getEarnings(),
                getType(),
                getQuantity());

        return copy;
    }

}

class Funds extends Investment {

    private float volatility;

    public Funds(int time, float percentage, float deposit, String period, String duration, float afterIntrest,
            float earnings, String type) {

        super(time, percentage, deposit, period, duration, afterIntrest, earnings, type);
        this.volatility = 0.18f;
    }

    public float getVolatility() {
        return volatility;
    }

    @Override
    public String toString() {
        return "Type: " + getType() +
                "\nTime: " + getTime() + " " + getPeriod() +
                "\nPercentage: " + df.format(getPercentage()) + "%" +
                "\nDeposit: " + df.format(getDeposit()) + "€" +
                "\nAfter intrest: " + df.format(getAfterIntrest()) + "€" +
                "\nEarnings: " + df.format(getEarnings()) + "€" +
                "\n";
    }

    public Funds Clone() {
        Funds copy = new Funds(
                getTime(),
                getPercentage(),
                getDeposit(),
                getPeriod(),
                getDuration(),
                getAfterIntrest(),
                getEarnings(),
                getType());

        return copy;
    }

}