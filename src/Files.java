import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Desktop;

class Files {

    private static String path = "saved/";
    private static LocalDateTime currentDate = LocalDateTime.now();

    public static String SaveFile(String fileName, Investment investment) {
        
        String dataToSave = toSave(investment);
        String name = fileName;
       
        if (Test.test.getTestCase()) {
            name = "Testcase";
        }
       
        String newFilePath = path + name + ".txt";
        String returnResponse = "";

        try {
            File newFile = new File(newFilePath);
            if (newFile.createNewFile()) {
                //System.out.println("Created file: " + newFile.getName());
                returnResponse = "Created file: " + newFile.getName();
                FileWriter myWriter = new FileWriter(newFile);
                myWriter.write(dataToSave);
                myWriter.close();

            } else if (!newFile.createNewFile()) {
                //System.out.println("File already exists! Data appended! \n");
                returnResponse = "File already exists! Data appended!";
                FileWriter myWriter = new FileWriter(newFile, true);
                myWriter.append("\n\n" + dataToSave);
                myWriter.close();
                }

            return returnResponse;
        } catch (IOException ex) {
            System.out.println("Something went wrong!");
            return "Something went wrong!";
        }
           

    }

    public static void ReadFile(Stage primaryStage) {
       
        CacheContainer fileRead = new CacheContainer(10, "Read Saved Files", "Select file to read", "Main Menu");

        File folder = new File(path);
        
        if (folder.exists() && folder.isDirectory()) {
            File[] folderFiles = folder.listFiles(file -> file.isFile() && file.canRead() && file.canWrite());
            int i = 1;

            if (folderFiles.length > 0) {
                for (File file : folderFiles) {
                    if (file.canRead() && file.canWrite()) {
                        String fileEntry = i + ". " + file.getName();
                        EntryContainer files = new EntryContainer(10, fileEntry, "Read File");
    
                        files.getButton().setOnAction(e -> {
                        File fileToOpen = file;
    
                            if (Desktop.isDesktopSupported()) {
                                Desktop desktop = Desktop.getDesktop();
                                Utility.Delay();
                                try {
                                    desktop.open(fileToOpen);
                
                                } catch (IOException ex) {
                                    fileRead.setInfo("Unable to open file!");
                                    ex.printStackTrace();
                                }
                            } else {
                                fileRead.setInfo("This system is not capable for desktop operations.");
                            }
                        });
                        fileRead.getChildren().addAll(files);
                        i++;
                    }
                }
            }
            else {
                fileRead.setInfo("Directory is empty.");
            }
        }
        else {
            fileRead.setInfo("Directory does not exist!");
        }

        fileRead.getButton().setOnAction(e -> {
            primaryStage.setScene(Main.mainMenu);
        });

    Scene readScene = new Scene(fileRead, 600, 400);
    primaryStage.setScene(readScene);
}
   
    private static String toSave(Investment investment) {
        String dataToSave = "Current date: " + currentDate;
        dataToSave += " (METHOD: " + investment.getType() + ") ";
        dataToSave += "\n" + investment;
        return dataToSave;
    }
} 