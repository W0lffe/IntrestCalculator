import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;

class Files {

    private static String path = "saved/";
    private static LocalDateTime currentDate = LocalDateTime.now();

    public static void SaveFile(Scanner myScanner, Investment investment) {
        int userInput;
        System.out.println("\nDo you wish to save this data?\n1.Yes\n2.No");

        do {

            if (Test.test.getTestCase()) {
                userInput = 1;
                break;
            } else {
                userInput = Validation.UserInput(myScanner);
            }
            if (userInput == 1 || userInput == 2) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }

        } while (true);

        if (userInput == 2) {
            return;
        }

        String dataToSave = toSave(investment);

        while (true) {
            String name;

            if (Test.test.getTestCase()) {
                name = "Testcase";
            } else {
                System.out.print("Give a name to file: ");
                name = myScanner.nextLine();
            }

            String newFilePath = path + name + ".txt";

            try {
                File newFile = new File(newFilePath);
                if (newFile.createNewFile()) {
                    System.out.println("Created file: " + newFile.getName());
                    FileWriter myWriter = new FileWriter(newFile);
                    myWriter.write(dataToSave);
                    myWriter.close();
                    break;
                } else if (!newFile.createNewFile()) {
                    System.out.println("File already exists! Data appended! \n");
                    FileWriter myWriter = new FileWriter(newFile, true);
                    myWriter.append("\n\n" + dataToSave);
                    myWriter.close();
                    break;
                }
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }

        Utility.Delay();
        System.out.println("Data saved!");
    }

    public static void ReadFile(Scanner myScanner) {

        File folder = new File(path);
        int a = 1;
        int command;

        if (folder.exists() && folder.isDirectory()) {
            File[] folderFiles = folder.listFiles(file -> file.isFile() && file.canRead() && file.canWrite());

            System.out.println("\nDirectory has files: \n0. Go back");

            for (File file : folderFiles) {
                if (file.canRead() && file.canWrite()) {
                    System.out.println(a + ". " + file.getName());
                    a++;
                }
            }

            System.out.println("Which file would you like to read?\n");

            while (true) {
                try {
                    System.out.print("Your choice: ");
                    command = Integer.parseInt(myScanner.nextLine());
                    if (command > 0 && command < a) {
                        break;
                    } else if (command == 0) {
                        return;
                    } else {
                        System.out.println("File not found! Try again");
                    }
                } catch (Exception e) {
                    System.out.println("Error occured!");
                }
            }

            System.out.println("You chose file: " + folderFiles[command - 1].getName());

            String newPath = path + folderFiles[command - 1].getName();
            File file = new File(newPath);

            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                Utility.Delay();
                try {
                    desktop.open(file);

                } catch (IOException e) {
                    System.out.println("Unable to open file!");
                    e.printStackTrace();
                }
            } else {
                System.out.println("This system is not capable for desktop operations.");
            }
        } else {
            System.out.println("Folder is not found.");
        }
    }

    private static String toSave(Investment investment) {
        String dataToSave = "Current date: " + currentDate;
        dataToSave += " (METHOD: " + investment.getType() + ") ";
        dataToSave += "\n" + investment;
        return dataToSave;
    }
}