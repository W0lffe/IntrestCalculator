import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class Interface extends VBox {
    
    private Label title;

    public Interface(double arg0, String titleText) {
        super(arg0);
        this.title = new Label(titleText);

        this.getChildren().add(title);
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(String text) {
        title.setText(text);
    

}
}


class Container1 extends Interface{

    private Button button1;
    private Button button2;
    private Button button3;
    
    public Container1(double arg0, String titleText, String b1text, String b2text, String b3text) {
        super(arg0, titleText);
        this.button1 = new Button(b1text);
        this.button2 = new Button(b2text);
        this.button3 = new Button(b3text);

        this.getChildren().addAll(button1, button2, button3);
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
    
}

class Container2 extends Container1{

    private Button button4;
    private Button button5;
    
    public Container2(double arg0, String titleText, String b1text, String b2text, String b3text, String b4text,  String b5text) {
        super(arg0, titleText, b1text, b2text, b3text);
        this.button4 = new Button(b4text);
        this.button5 = new Button(b5text);

        this.getChildren().addAll(button4, button5);
    }

    public Button getButton4() {
        return button4;
    }

    public Button getButton5() {
        return button5;
    }

    
}

class Container3 extends Container2{

    private Label info;

    public Container3(double arg0, String titleText, String b1text, String b2text, String b3text, String b4text, String b5text, String infoText) {
        super(arg0, titleText, b1text, b2text, b3text, b4text, b5text);
        this.info = new Label(infoText);

        this.getChildren().addAll(info);

    }

    public void setInfo(String text) {
        info.setText(text);
    }

    

}

class inputBox extends Interface{

    private TextArea textArea;
    private Button button;
    
    public inputBox(double arg0, String infoText, String buttonLabel) {
        super(arg0, infoText);
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

class inputBox1 extends inputBox{

    private Label text;

    public inputBox1(double arg0, String infoText, String buttonLabel, String infotext) {
        super(arg0, infoText, buttonLabel);
        this.text = new Label(infotext);

        this.getChildren().addAll(text);
    }

    public void setText(String info) {
        text.setText(info);
    }

    
    
}

class inputBox2 extends inputBox{

    private Label text1;
    private Label text2;
    private CheckBox cb1;
    private CheckBox cb2;

    public inputBox2(double arg0, String infoText, String buttonLabel, String labelText1, String labelText2) {
        super(arg0, infoText, buttonLabel);
        this.text1 = new Label(labelText1);
        this.text2 = new Label(labelText2);
        this.cb1 = new CheckBox();
        this.cb2 = new CheckBox();

        this.getChildren().addAll(text1,cb1, text2, cb2);
    }

    public CheckBox getCb1() {
        return cb1;
    }

    public CheckBox getCb2() {
        return cb2;
    }
}
