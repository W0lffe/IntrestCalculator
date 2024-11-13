import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.awt.Desktop;




class Files {

    private static String path = "saved/";
    private static LocalDateTime currentDate = LocalDateTime.now();

    public static void SaveFile(Stage primaryStage, Investment investment) {
        VBox container = new VBox(2);

        Label info = new Label("Do you wish to save this data?");
        Button yes = new Button("Yes");
        Button no = new Button("No");

        no.setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });
        yes.setOnAction(e -> {
            TextField fileName = new TextField();
            Button submit = new Button("Submit");
            String dataToSave = toSave(investment);

            container.getChildren().addAll(fileName, submit);

            submit.setOnAction(event -> {
                String name = fileName.getText();

                if (Test.test.getTestCase()) {
                    name = "Testcase";
                } 

            String newFilePath = path + name + ".txt";
            try {
                File newFile = new File(newFilePath);
                if (newFile.createNewFile()) {
                    System.out.println("Created file: " + newFile.getName());
                    FileWriter myWriter = new FileWriter(newFile);
                    myWriter.write(dataToSave);
                    myWriter.close();
                } else if (!newFile.createNewFile()) {
                    System.out.println("File already exists! Data appended! \n");
                    FileWriter myWriter = new FileWriter(newFile, true);
                    myWriter.append("\n\n" + dataToSave);
                    myWriter.close();
                }
            } catch (IOException ex) {
                System.out.println("Something went wrong!");
            }
            primaryStage.setScene(Main.mainMenu);
            });

        });
    
        container.getChildren().addAll(info, yes, no);
        Scene newScene = new Scene(container, 600, 400);
        primaryStage.setScene(newScene);

    }

    public static void ReadFile(Stage primaryStage) {
        VBox container = new VBox(5);
        Label info = new Label("");

        File folder = new File(path);
        int a = 1;
        container.getChildren().addAll(info);
        
        if (folder.exists() && folder.isDirectory()) {
            File[] folderFiles = folder.listFiles(file -> file.isFile() && file.canRead() && file.canWrite());

            info.setText("Directory has files:");

            for (File file : folderFiles) {
                if (file.canRead() && file.canWrite()) {
                    Label files = new Label(a + ". " + file.getName());
                    container.getChildren().addAll(files);
                    a++;
                }
            }
            Label read = new Label("Which file would you like to read?");
            TextField answer = new TextField();
            Button submit = new Button("Submit");

            container.getChildren().addAll(read, answer, submit);

            submit.setOnAction(e -> {
                int choice = Utility.UserInputINT(answer.getText());

                if(choice > 0 && choice <= folderFiles.length){
                    String newPath = path + folderFiles[choice - 1].getName();

                    File file = new File(newPath);

                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        Utility.Delay();
                        try {
                            desktop.open(file);
        
                        } catch (IOException ex) {
                            System.out.println("Unable to open file!");
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("This system is not capable for desktop operations.");
                    }
                }
                primaryStage.setScene(Main.mainMenu);
            });
        } else {
            info.setText("No files found!");
        }
        Scene readScene = new Scene(container, 600, 400);
        primaryStage.setScene(readScene);
    }
   
    private static String toSave(Investment investment) {
        String dataToSave = "Current date: " + currentDate;
        dataToSave += " (METHOD: " + investment.getType() + ") ";
        dataToSave += "\n" + investment;
        return dataToSave;
    }
} 