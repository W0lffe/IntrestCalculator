import java.util.ArrayList;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.Gson;

public class Utility {

    public static void Delay() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Prints different menus based on INT FUNCTION(value given from different
     * functions
     */
    public static void print(int function) {

        switch (function) {
            case 1:
                System.out.println("\nSelect one: ");
                System.out.println("1. CALCULATE INTREST");
                System.out.println("2. READ PREVIOUSLY SAVED FILES");
                System.out.println("3. SHOW CACHE(LOCAL)");
                System.out.println("4. SHOW CACHE(SERVER)");
                System.out.println("5. TEST CASE");
                System.out.println("0. CLOSE PROGRAM");
                break;

            case 2:
                System.out.println("Which mode would you like to use?\n");
                System.out.println("1. Linear Calculation");
                System.out.println("2. Stocks");
                System.out.println("3. Funds/ETF");
                System.out.println("0. Go back");
                break;

            case 3:
                System.out.println("\nSettings for calculation");
                System.out.println("1. Time");
                System.out.println("2. Estimated percentage");
                System.out.println("3. Initial Deposit");
                System.out.println("4. Show selections");
                System.out.println("9. Calculate");
                System.out.println("0. Go back to Main Menu. (Resets all values)");
                break;

            case 4:
                int x = 0;
                String toPrint = "Calculating";
                System.out.printf("%s", toPrint);

                /* Make a delay between printing "." */
                while (x != 3) {
                    try {
                        Thread.sleep(350);
                        System.out.print(".");
                        x++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("");
                break;
            case 5:
                System.out.println("What kind of investment is this?");
                System.out.println("1. Bonds, Low-Risk ETF");
                System.out.println("2. Index Funds, Medium-Risk ETF");
                System.out.println("3. Stocks, Cryptocurrencies, Commodities");
                System.out.println("0. Go Back");

                break;
            default:
                break;
        }
    }

    public static ArrayList<Investment> JsonDataParser(String DataArray, Gson gson) {

        ArrayList<Investment> temporary = new ArrayList<>();

        JsonObject jsonObject = JsonParser.parseString(DataArray).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("Data");

        // System.out.println(jsonArray);

        for (JsonElement element : jsonArray) {
            JsonObject object = element.getAsJsonObject();

            if (object.has("quantity")) {
                temporary.add(gson.fromJson(object, Stocks.class));
            } else {
                temporary.add(gson.fromJson(object, Investment.class));
            }
        }
        return temporary;
    }

}
