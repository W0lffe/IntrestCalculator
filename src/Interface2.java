import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;


public class Interface2 extends BorderPane{

    private Vertical top;
    private Vertical center;
    private Vertical left;
    private Vertical right;
    
    public Interface2(Vertical top, Vertical center, Vertical left, Vertical right) {
        this.top = top;
        this.center = center;
        this.left = left;
        this.right = right;


        this.setTop(top);
        this.setCenter(center);
        this.setLeft(left);
        this.setRight(right);
    }

    
    
}

class Horizontal extends HBox{
   
    private Label titleLabel;

    public Horizontal(double arg0, String title) {
        super(arg0);
        this.titleLabel = new Label(title);

        this.getChildren().addAll(titleLabel);
    }

    public void setHBoxTitleLabel(String labelText) {
        titleLabel.setText(labelText);
    }
}

class HorizontalInputBox extends HBox{


}

class HorizontalMenu extends Horizontal{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Label info;
    
    public HorizontalMenu(double arg0, String title, String b1text, String b2text, String b3text, String b4text, String b5text, String menuInfo) {
        super(arg0, title);
        this.button1 = new Button(b1text);
        this.button2 = new Button(b2text);
        this.button3 = new Button(b3text);
        this.button4 = new Button(b4text);
        this.button5 = new Button(b5text);
        this.info = new Label(menuInfo);

        this.getChildren().addAll(info, button1,button2,button3,button4,button5);
    }

    public HorizontalMenu(double arg0, String title, String b1text, String b2text, String b3text) {
        super(arg0, title);
        this.button1 = new Button(b1text);
        this.button2 = new Button(b2text);
        this.button3 = new Button(b3text);

        this.getChildren().addAll(button1,button2,button3);
    }

    public HorizontalMenu(double arg0, String title, String b1text, String b2text) {
        super(arg0, title);
        this.button1 = new Button(b1text);
        this.button2 = new Button(b2text);

        this.getChildren().addAll(button1,button2);
    }

    public void setMenuInfo(String menuInfo) {
        info.setText(menuInfo);
    }
    public Button getButton1() {
        return button1;
    }
    public Button getButton2() {
        return button2;
    }
    public Button getButton3() {
        return button3;
    }
    public Button getButton4() {
        return button4;
    }
    public Button getButton5() {
        return button5;
    }

}


class Vertical extends VBox{

    private Label titleLabel;

    public Vertical(double arg0, String title) {
        super(arg0);
        this.titleLabel = new Label(title);

        this.getChildren().addAll(titleLabel);
    }

    public void setVBoxTitleLabel(String labelText) {
        titleLabel.setText(labelText);
    }

}

class VerticalInputBox extends Vertical{

    private TextArea textArea;
    private Button button;
    
    public VerticalInputBox(double arg0, String titleLabel, String buttonLabel) {
        super(arg0, titleLabel);
        this.textArea = new TextArea();
        this.button = new Button(buttonLabel);

        this.getChildren().addAll(textArea, button);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Button getButton() {
        return button;
    }

}

class VerticalInputBox2 extends VerticalInputBox{

    private ChoiceBox<String> choices;

    public VerticalInputBox2(double arg0, String titleLabel, String buttonLabel) {
        super(arg0, titleLabel, buttonLabel);
        this.choices = new ChoiceBox<>();

        this.choices.getItems().addAll("Monthly", "Yearly");
        this.getChildren().add(choices);
    }

    public ChoiceBox<String> getChoices() {
        return choices;
    }
}