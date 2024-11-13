import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class Main extends Application {
    
    public static Scene mainMenu;

    @Override
    public void start(Stage primaryStage){

        VBox verticalBox = new VBox(15);

        Button collectData = new Button("CALCULATE INTREST");
        Button readFile = new Button("SAVED FILES");
        Button showLocalCache = new Button("SHOW LOCAL CACHE");
        Button showServerCache = new Button("SHOW SERVER CACHE");
        Button testCase = new Button("RUN TESTCASE");


        collectData.setOnAction(e -> {
            DataCollect.ModeScene(primaryStage);
        });

        readFile.setOnAction(e -> {
            Files.ReadFile(primaryStage);
        });

        showLocalCache.setOnAction(e -> {
            Cache.ShowCache(primaryStage);
        });

        showServerCache.setOnAction(e -> {
            //showServerCache function
        });

        testCase.setOnAction(e -> {
            //Test.TestMain(primaryStage);
        });
        
        verticalBox.getChildren().addAll(collectData, readFile, showLocalCache, showServerCache, testCase);

        mainMenu = new Scene(verticalBox, 600, 400);
        primaryStage.setTitle("Investment Tool");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
