import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import com.google.gson.Gson;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Cache {

    private static int method = 1;

    public static void ShowCache(Stage primaryStage) {
        Gson gson = new Gson();
        CacheContainer cache = new CacheContainer(10, "Local Cache", "Press button to save entry, or go back to Main Menu", "Main Menu");

        int entry = 1;
        for (Investment investment : Calculations.Storage) {
            String entryText = "Entry: " + entry + "\n" + investment;
            EntryContainer localEntry = new EntryContainer(10, entryText, "Save Entry");

            localEntry.getButton().setOnAction(e -> {
                String selectedEntry = gson.toJson(investment);
                String response = SendToServer(selectedEntry);
                cache.setInfo(response);
            });
            cache.getChildren().addAll(localEntry);
            entry++;
        }

        cache.getButton().setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });

        Scene cacheScene = new Scene(cache, 600, 400);
        primaryStage.setScene(cacheScene);
    }

     private static String SendToServer(String StringJSON) {

        try {
            URI serverURI = URI.create("https://www.cc.puv.fi/~e2301740/IC_Backend/IC_Backend.php");
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
 
    public static void ShowServerCache(Stage primaryStage) {
        Gson gson = new Gson();

        CacheContainer cache = new CacheContainer(10, "Server Cache", 
                                                "Press button to delete entry from server, or go back to Main Menu", "Main Menu");

        try {

            URI serverURI = URI.create("https://www.cc.puv.fi/~e2301740/IC_Backend/IC_Backend.php?method=" + method);
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
                        String entryText = "Entry: " + entry.getId() + "\n" + entry;
                        EntryContainer serverEntry = new EntryContainer(10, entryText, "Delete Entry");

                        serverEntry.getButton().setOnAction(e -> {
                            String serverResponse = DeleteData(entry.getId());
                            cache.setInfo(serverResponse);
                            Utility.Delay();
                            ShowServerCache(primaryStage);
                        });

                        cache.getChildren().addAll(serverEntry);
                    }
                } else {
                    cache.setInfo("No data found.");
                }

            } else {
                cache.setInfo("Error: " + connection.getResponseCode());
            }
            connection.disconnect();

        } catch (IOException e) {
            cache.setInfo("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

        cache.getButton().setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });

        Scene serverScene = new Scene(cache, 600, 400);
        primaryStage.setScene(serverScene);

    }
    private static String DeleteData(int id) {

        try {

            URI serverURI = URI.create("https://www.cc.puv.fi/~e2301740/IC_Backend/IC_Backend.php?id=" + id);
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
 