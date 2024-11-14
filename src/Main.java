import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;
    public static Scene mainMenu;

    @Override
    public void start(Stage primaryStage){

        Vertical menu = new Vertical(10, "Main Menu");
        menu.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT/6);

        HorizontalMenu main = new HorizontalMenu(10, "", "Calculate Intrest", "Read Saved Files",
                                                "Show Local Cache", "Show Server Cache", "Test Case", "");
        menu.getChildren().add(main);

        Vertical left = new Vertical(10, "FILLER");
        left.setPrefSize(WINDOW_WIDTH/4, 500);

        Vertical right = new Vertical(10, "FILLER");
        right.setPrefSize(WINDOW_WIDTH/4, 500);

        Vertical center = new Vertical(10, "FILLER");
        center.setPrefSize(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);


        Interface2 root = new Interface2(menu, center, left, right);
      

        main.getButton1().setOnAction(e -> {
            DataCollect.ModeScene(root);
            main.setMenuInfo("");
        });

        main.getButton2().setOnAction(e -> {
            //Files.ReadFile(root);
            main.setMenuInfo("");
        });

        main.getButton3().setOnAction(e -> {
            if (!Calculations.Storage.isEmpty()) {
                //Cache.ShowCache(root);
            }
            else{
                main.setMenuInfo("Cache is empty!");
            }
            
        });

        main.getButton4().setOnAction(e -> {
           //Cache.ShowServerCache(root);
            main.setMenuInfo("");
        });

        main.getButton5().setOnAction(e -> {
            //Test.TestMain(root);
            main.setMenuInfo("");
        });
        
        mainMenu = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Investment Tool");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
