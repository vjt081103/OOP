package com.example.demo3.DictionaryApp.Game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class QuestionsController {
    private String question;

    private String trueAns;

    private String[] falseAns;

    @FXML
    Button trueButton = new Button();

    @FXML
    Text quesTxt = new Text();

    @FXML
    Button false1 = new Button();

    @FXML
    Button false2 = new Button();
    @FXML
    Button false3 = new Button();


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTrueAns(String trueAns) {
        this.trueAns = trueAns;
    }

    public void setFalseAns(String[] falseAns) {
        System.arraycopy(falseAns, 0, this.falseAns, 0, 3);
    }


}
