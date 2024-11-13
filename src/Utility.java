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

    public static int UserInputINT(String textFieldInput) {

        int userInput = -1;
        
            try {
                userInput = Integer.parseInt(textFieldInput);
             
            } catch (NumberFormatException e) {
                System.out.println("\nUnknown input format! Enter again!");
            }
        return userInput;
    }


    public static float UserInputFL(String textFieldInput) {

        float userInput = 0f;
        
            try {
                userInput = Float.parseFloat(textFieldInput);

            } catch (NumberFormatException e) {
                System.out.println("\nUnknown input format! Enter again!");
            }
        return userInput;
    }
   
    }

