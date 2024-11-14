import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    
    public static Scene mainMenu;

    @Override
    public void start(Stage primaryStage){

       Container3 main = new Container3(10, "Main Menu", "Calculate Intrest", "Read Saved Files",
                                        "Show Local Cache", "Show Server Cache", "Test Case", "");

        main.getButton1().setOnAction(e -> {
            DataCollect.ModeScene(primaryStage);
            main.setInfo("");
        });

        main.getButton2().setOnAction(e -> {
            Files.ReadFile(primaryStage);
            main.setInfo("");
        });

        main.getButton3().setOnAction(e -> {
            if (!Calculations.Storage.isEmpty()) {
                Cache.ShowCache(primaryStage);
            }
            else{
                main.setInfo("Cache is empty!");
            }
            
        });

        main.getButton4().setOnAction(e -> {
            Cache.ShowServerCache(primaryStage);
            main.setInfo("");
        });

        main.getButton5().setOnAction(e -> {
            Test.TestMain(primaryStage);
            main.setInfo("");
        });
        
        mainMenu = new Scene(main, 600, 400);
        primaryStage.setTitle("Investment Tool");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
