package com.example.demo3.DictionaryApp.Game;

import com.example.demo3.DictionaryApp.homeController;
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
import java.util.*;


public class gameController implements Initializable {

    private Questions questionslist = new Questions();

    private List<AQues> questions = new ArrayList<>();

    private int numQue = 10;
    @FXML
    AnchorPane ap1 = new AnchorPane();

    @FXML
    Button choose = new Button();

    @FXML
    Button start = new Button();

    @FXML
    Text numQueTxt = new Text();

    @FXML
    Button button5 = new Button();

    @FXML
    Button button10 = new Button();

    @FXML
    Button button20 = new Button();

    @FXML
    Button back = new Button();


    public gameController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ap1.setVisible(false);
        start.setVisible(true);
        choose.setVisible(true);
        back.setVisible(true);

        choose.setOnAction(event ->
            chooseNumQue()
        );
        numQueTxt.setText(Integer.toString(numQue));
    }

    public void chooseNumQue() {
        ap1.setVisible(true);
        button5.setOnAction(event -> {
            numQue = 5;
            ap1.setVisible(false);
            numQueTxt.setText(Integer.toString(numQue));
        });

        button10.setOnAction(event -> {
            numQue = 10;
            ap1.setVisible(false);
            numQueTxt.setText(Integer.toString(numQue));
        });

        button20.setOnAction(event -> {
            numQue = 20;
            ap1.setVisible(false);
            numQueTxt.setText(Integer.toString(numQue));
        });
    }

    public void goToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo3/DictionaryApp/home.fxml"));
        Parent goHome = loader.load();
        Scene scene = new Scene(goHome);
        stage.setScene(scene);
    }

    public void start(ActionEvent event) throws IOException{
        initQues();
        for(int i = 0; i < numQue; i++) {
            String[] falseQues = new String[3];
            Random random = new Random();
            for(int j = 0; j < 3 ;j++) {
                int n = random.nextInt(numQue);
                if(n == i) {
                    n = random.nextInt(numQue);
                }
                falseQues[j] = questions.get(n).getAns();
            }
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo3/DictionaryApp/Game/questions.fxml"));
            Parent play = loader.load();
            QuestionsController qc = loader.getController();
            qc.setQuestion(questions.get(i).getQues());
            //qc.setFalseAns(falseQues);
            qc.setTrueAns(questions.get(i).getAns());
            Scene scene = new Scene(play);
            stage.setScene(scene);
        }
    }

    public void initQues() {
        questions = new ArrayList<>();
        Random random = new Random();
        for (int i =0;i < numQue; i++) {
            int n = random.nextInt(questionslist.getQaa().size());
            questions.add(questionslist.getQaa().get(n));
        }
    }
}
