import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;
import javafx.scene.layout.BorderPane;


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

    public static void ReadFile(BorderPane root) {
        Vertical container = new Vertical(10, "Select file to read");
        container.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/3);

        VerticalChoicesBox files = new VerticalChoicesBox(10, "", "Confirm");
        container.getChildren().addAll(files);
        container.getStyleClass().add("vertical");
        root.setCenter(container);

        File folder = new File(path);
        
        if (folder.exists() && folder.isDirectory()) {
            File[] folderFiles = folder.listFiles(file -> file.isFile() && file.canRead() && file.canWrite());

            if (folderFiles.length > 0) {

                for (File file : folderFiles) {
                    
                    if (file.canRead() && file.canWrite()) {
                        files.getChoices().getItems().addAll(file.getName());
                    }
                }
            }
            else{
                files.setVBoxTitleLabel("Directory is empty!");
            }
        }
        else{
            files.setVBoxTitleLabel("Directory is doesnt exist!");
        }
            
        files.getButton().setOnAction(e -> {
            String fileName = files.getChoices().getValue();

            if(fileName != null){
                File fileToOpen = new File(path + fileName);

                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    Utility.Delay();
                    try {
                        desktop.open(fileToOpen);
    
                    } catch (IOException ex) {
                        files.setVBoxTitleLabel("Unable to open file!");
                        ex.printStackTrace();
                    }
                } else {
                    files.setVBoxTitleLabel("This system is not capable for desktop operations.");
                }
            }
            else{
                files.setVBoxTitleLabel("Please select a file from the list.");
            }
        });     

               
      
    }
   
    private static String toSave(Investment investment) {
        String dataToSave = "Current date: " + currentDate;
        dataToSave += " (METHOD: " + investment.getType() + ") ";
        dataToSave += "\n" + investment;
        return dataToSave;
    }
}
