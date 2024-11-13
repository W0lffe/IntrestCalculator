import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Cache {

    private static int method = 1;

    public static void ShowCache(Stage primaryStage) {
        Gson gson = new Gson();

        VBox container = new VBox(2);

        int entry = 1;
        for (Investment investment : Calculations.Storage) {
            Label investmentEntry = new Label("Entry: " + entry + "\n" + investment);
            container.getChildren().add(investmentEntry);
            entry++;
        }

        Label info = new Label("Would you like to save entry to server?");
        Button yes = new Button("Yes");
        Button no = new Button("No");
        container.getChildren().addAll(info, yes, no);
        

        no.setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });
        yes.setOnAction(e -> {
            Label sendingInfo = new Label("");
            Label entrySelect = new Label("Wich entry would you like to be saved?");
            TextField selected = new TextField();
            Button submit = new Button("Submit");

            container.getChildren().addAll(sendingInfo, entrySelect, selected, submit);

            submit.setOnAction(event -> {
                int choice = Utility.UserInputINT(selected.getText());

                if (choice > 0 && choice <= Calculations.Storage.size()) {
                    String selectedEntry = gson.toJson(Calculations.Storage.get(choice - 1));
                    //SendToServer(selectedEntry);
                    primaryStage.setScene(Main.mainMenu);
                }
                else{
                    sendingInfo.setText("This entry doesnt exist.");
                }
            });

        });
        Scene cache = new Scene(container, 600, 400);
        primaryStage.setScene(cache);
    }

    /*  private static void SendToServer(String StringJSON) {

        try {
            URI serverURI = URI.create("yourURLhere");
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = StringJSON.getBytes("utf-8");
                os.write(input);
            }

            int Response = connection.getResponseCode();

            if (Response == HttpURLConnection.HTTP_OK) {
                System.out.println("Data successfully sent!");
            } else {
                System.out.println("Error! " + Response);
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowServerCache(Scanner myScanner) {
        Gson gson = new Gson();

        try {

            URI serverURI = URI.create("yourURLhere?method=" + method);
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
                        System.out.println("Entry: " + entry.getId());
                        System.out.println(entry);
                    }
                    SelectDataToDelete(Entries, myScanner);
                } else {
                    System.out.println("No data found.");
                }

            } else {
                System.out.println("Error: " + connection.getResponseCode());
            }
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void SelectDataToDelete(ArrayList<Investment> Entries, Scanner myScanner) {
        int userInput;

        System.out.println("Do you wish to delete entry?\n1.Yes\n2.No");

        while (true) {
            userInput = Validation.UserInput(myScanner);
            if (userInput == 1 || userInput == 2) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        if (userInput == 1) {
            int choice;
            System.out.println("Wich entry would you like to be deleted?");
            while (true) {
                choice = Validation.UserInput(myScanner);
                if (choice > 0 && choice <= Entries.size()) {

                    System.out.println("\n" + Entries.get(choice - 1));
                    int selectedID = Entries.get(choice - 1).getId();
                    // System.out.println(Entries.indexOf(Entries.get(choice-1)));
                    // System.out.println(selectedID);

                    DeleteData(selectedID);
                    break;
                } else {
                    System.out.println("Entry doesnt exist.");
                    return;
                }
            }
        } else {
            return;
        }
    }

    private static void DeleteData(int id) {

        try {

            URI serverURI = URI.create("yourURLhere?id=" + id);
            URL server_url = serverURI.toURL();
            HttpURLConnection connection = (HttpURLConnection) server_url.openConnection();
            connection.setRequestMethod("DELETE");

            int Response = connection.getResponseCode();

            if (Response == HttpURLConnection.HTTP_OK) {
                System.out.println("Data successfully deleted!");
            } else {
                System.out.println("Error! " + Response);
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
 