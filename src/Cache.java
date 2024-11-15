import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import com.google.gson.Gson;
import javafx.scene.layout.BorderPane;


public class Cache {

    private static int method = 1;
    private static String URL = "https://www.cc.puv.fi/~e2301740/IC_Backend";

    public static void ShowCache(BorderPane root) {
        Gson gson = new Gson();
        
        Vertical container = new Vertical(10, "Local Cache");
        container.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/2);

        VerticalChoicesBox2 entries = new VerticalChoicesBox2(10, "Select entry from the list", "Show Entry", "Save Entry");
        container.getChildren().addAll(entries);
        root.setCenter(container);


        int entryNumber = 1;
        for (Investment investment : Calculations.Storage) {
            String entry = entryNumber + ". " + investment.getType();
            entries.getChoices().getItems().addAll(entry);
            entryNumber++;
        }

        entries.getButton().setOnAction(e -> {
            String selectedEntry = entries.getChoices().getValue();

            if (selectedEntry != null) {
                int selectedEntryIndex = entries.getChoices().getItems().indexOf(selectedEntry);
                Investment investment = Calculations.Storage.get(selectedEntryIndex);
                entries.getTextArea().setText(investment.toString());
            }
            else{
                entries.setVBoxTitleLabel("Please select entry first!");
            }
        });

        entries.getButton2().setOnAction(e -> {
            String selectedEntry = entries.getChoices().getValue();

            if (selectedEntry != null) {
                int selectedEntryIndex = entries.getChoices().getItems().indexOf(selectedEntry);
                Investment investment = Calculations.Storage.get(selectedEntryIndex);

                String stringifiedData = gson.toJson(investment);
                String response = SendToServer(stringifiedData);
                entries.getTextArea().appendText(response);
            }
            else{
                entries.setVBoxTitleLabel("Please select entry first!");
            }
        });
       
    }

     private static String SendToServer(String StringJSON) {

        try {
            URI serverURI = URI.create(URL + "/IC_Backend.php");
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = StringJSON.getBytes("utf-8");
                os.write(input);
            }

            int Response = connection.getResponseCode();

            String message;
            if (Response == HttpURLConnection.HTTP_OK) {
                //System.out.println("Data successfully sent!");
                message = "Data successfully sent!";
            } else {
                message = "Error! " + Response;
                //System.out.println("Error! " + Response);
            }
            connection.disconnect();
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
 
    public static void ShowServerCache(BorderPane root) {
        Gson gson = new Gson();

        Vertical container = new Vertical(10, "Server Cache");
        container.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/2);

        VerticalChoicesBox2 entries = new VerticalChoicesBox2(10, "Select entry from the list", "Show Entry", "Delete Entry");
        container.getChildren().addAll(entries);
        root.setCenter(container);

        try {

            URI serverURI = URI.create(URL + "/IC_Backend.php?method=" + method);
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                ArrayList<Investment> Entries = Utility.JsonDataParser(response.toString(), gson);
                
                if (Entries != null) {

                    for (Investment entry : Entries) {
                        String entryText = "Entry: " + entry.getId();
                        entries.getChoices().getItems().addAll(entryText);
                    };
                }
                else{
                    entries.setVBoxTitleLabel("No data found.");
                }

                entries.getButton().setOnAction(e -> {
                    String selectedEntry = entries.getChoices().getValue();
        
                    if (selectedEntry != null) {
                        int selectedEntryIndex = entries.getChoices().getItems().indexOf(selectedEntry);
                        Investment investment = Entries.get(selectedEntryIndex);
                        entries.getTextArea().setText(investment.toString());
                    }
                    else{
                        entries.setVBoxTitleLabel("Please select entry first!");
                    }
                });

                entries.getButton2().setOnAction(e -> {
                    String selectedEntry = entries.getChoices().getValue();
        
                    if (selectedEntry != null) {
                        int selectedEntryIndex = entries.getChoices().getItems().indexOf(selectedEntry);
                        Investment investment = Entries.get(selectedEntryIndex);
                        String serverResponse = DeleteData(investment.getId());
                        entries.getTextArea().appendText(serverResponse);
                    }
                    else{
                        entries.setVBoxTitleLabel("Please select entry first!");
                    }
                });
            }
            else {
                entries.setVBoxTitleLabel("Error: " + connection.getResponseCode());
            }
            connection.disconnect();

        } catch (IOException e) {
            entries.setVBoxTitleLabel("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

 }

    
    private static String DeleteData(int id) {

        try {

            URI serverURI = URI.create(URL + "/IC_Backend.php?id=" + id);
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("DELETE");

            int Response = connection.getResponseCode();

            String message;
            if (Response == HttpURLConnection.HTTP_OK) {
                message = "Data successfully deleted!";
            } else {
                message = "Error! " + Response;
            }
            connection.disconnect();
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error! " + e.getMessage();
        }
    }

}
 