package com.example.demo3.DictionaryApp.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class QuestionsController {


    private String question = "day la question";

    private String trueAns = "day la answer";

    private String[] falseAns = {"day la false1", "day la false2", "day la false3"};

    private int trueNumber = 1;

    @FXML
    Text quesTxt = new Text();


    @FXML
    Button button1 = new Button();

    @FXML
    Button button2 = new Button();

    @FXML
    Button button3 = new Button();

    @FXML
    Button button4 = new Button();

    @FXML
    Text result = new Text();

    @FXML
    Button backHome = new Button();

    Button restart = new Button();

    @FXML
    AnchorPane apQues = new AnchorPane();

    @FXML
    AnchorPane apResult = new AnchorPane();

    private int cauhoihientai = 1;


    public QuestionsController() {
        apResult.setVisible(false);
    }


    public void setQuestion(String question) {
        this.question = question;
        quesTxt.setText("Nghĩa của từ " + this.question + " là");
    }



    public void setButtons(String[] ans) {
        this.button1.setText(ans[0]);
        this.button2.setText(ans[1]);
        this.button3.setText(ans[2]);
        this.button4.setText(ans[3]);
    }



    public void setResult(String strResult) {
        result.setText(strResult);
    }

    public void setTrueNumber(int trueNumber) {
        this.trueNumber=trueNumber;
    }

    public Button trueAns() {
        switch(this.trueNumber) {
            case 1: return button1;
            case 2: return button2;
            case 3: return button3;
            case 4: return button4;
            default:return button1;
        }
    }

    public String getQuestion() {
        return this.question;
    }


    public String getTrueAns() {
        return this.trueAns;
    }

    public String[] getFalseAns() {
        return this.falseAns;
    }

    public void goToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo3/DictionaryApp/home.fxml"));
        Parent goHome = loader.load();
        Scene scene = new Scene(goHome);
        stage.setScene(scene);
    }
    public void gotoGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo3/DictionaryApp/Game/game.fxml"));
        Parent game = loader.load();
        Scene scene = new Scene(game);
        stage.setScene(scene);
    }




}
