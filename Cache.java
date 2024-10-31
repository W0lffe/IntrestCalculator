import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Cache {

    private static int method = 1;
    public static void ShowCache(Scanner myScanner){
        Gson gson = new Gson();

        int entry = 1;
        for (Investment investment : Calculations.Storage) {
                System.out.println("\nEntry: " + entry + "\n" + investment);
                entry++;
        }
        int userInput;
        System.out.println("Would you like to save entry to server? \n1.Yes\n2.No");  
        
        while (true) {
            userInput = Validation.UserInput(myScanner);
            if (userInput == 1 || userInput == 2) {
                break;
            }
            else{
                System.out.println("Invalid choice!");
            }
        }

        if (userInput == 1) {
            int choice;
            System.out.println("Wich entry would you like to be saved?");
            while (true) {
                choice = Validation.UserInput(myScanner);
                if(choice > 0 && choice <= Calculations.Storage.size()){

                    System.out.println("\n" + Calculations.Storage.get(choice-1));
                    String selectedEntry = gson.toJson(Calculations.Storage.get(choice-1));
                    //System.out.println(selectedEntry);
                    SendToServer(selectedEntry);
                    break;
                }
                else{
                    System.out.println("Entry doesnt exist.");
                    return;
                }
            }
        }
        else{
            return;
        }
}


private static void SendToServer(String StringJSON){

        try {
            URI serverURI = URI.create("https://www.cc.puv.fi/~e2301740/IC_Backend/IC_Backend.php");
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()){
                byte[] input = StringJSON.getBytes("utf-8");
                os.write(input);
            }

            int Response = connection.getResponseCode();

            if(Response == HttpURLConnection.HTTP_OK){
                System.out.println("Data successfully sent!");
            }
            else{
                System.out.println("Error! " + Response);
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 public static void ShowServerCache(Scanner myScanner){
        Gson gson = new Gson();
        
        try {
           
            URI serverURI = URI.create("https://www.cc.puv.fi/~e2301740/IC_Backend/IC_Backend.php?method=" + method);
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while((line = br.readLine()) != null){
                    response.append(line);
                }

                Type listType = new TypeToken<List<Investment>>() {}.getType();
                List<Investment> Entries = gson.fromJson(response.toString(), listType);

                if (Entries != null) {
                    for (Investment entry : Entries) {
                        System.out.println("Entry: " + entry.getId());
                        System.out.println(entry);
                    }
                }
                else{
                    System.out.println("No data found.");
                }

                SelectDataToDelete(Entries, myScanner);
            }
            else{
                System.out.println("Error: " + connection.getResponseCode());
            }
            connection.disconnect();
      
        } catch (IOException e) {
           System.out.println("Error fetching data: " + e.getMessage());
           e.printStackTrace();
        }

}
        private static void SelectDataToDelete(List<Investment> Entries, Scanner myScanner){
            int userInput;

            System.out.println("Do you wish to delete entry?\n1.Yes\n2.No");
            
            while (true) {
                userInput = Validation.UserInput(myScanner);
                if(userInput == 1 || userInput ==2){
                    break;
                }
                else{
                    System.out.println("Invalid choice!");
                }
            }

            if(userInput == 1){
                int choice;
                System.out.println("Wich entry would you like to be deleted?");
                while (true) {
                    choice = Validation.UserInput(myScanner);
                    if(choice > 0 && choice <= Entries.size()){
    
                        System.out.println("\n" + Entries.get(choice-1));
                        int selectedID = Entries.get(choice-1).getId();
                        //System.out.println(Entries.indexOf(Entries.get(choice-1)));
                        //System.out.println(selectedID);
                        
                        DeleteData(selectedID);
                        break;
                    }
                    else{
                        System.out.println("Entry doesnt exist.");
                        return;
                    }
                }
            }
            else{
                return;
            }
        }

        private static void DeleteData(int id){

            try {

                URI serverURI = URI.create("https://www.cc.puv.fi/~e2301740/IC_Backend/IC_Backend.php?id=" + id);
                URL server_url = serverURI.toURL();
                HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
                connection.setRequestMethod("DELETE");

                int Response = connection.getResponseCode();

                if(Response == HttpURLConnection.HTTP_OK){
                    System.out.println("Data successfully deleted!");
                }
                else{
                    System.out.println("Error! " + Response);
                }
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
