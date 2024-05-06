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

    private int numQue = 5;
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

    private int cauhoihientai = 0;

    private int trueNumber = 1;

    private int goal = 0;


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

    private QuestionsController qc = new QuestionsController();
    Button trueAns = new Button();

    public void start(ActionEvent event) throws IOException {
        initQues();
        Random random = new Random();
        trueNumber = random.nextInt(4) + 1;
        String[] ans = new String[4];
        ans[trueNumber - 1] = this.questions.get(0).getAns();
        String[] falseAns = initFalseAns(0);
        for(int i = 0; i < 3; i++) {
            int n = (trueNumber + i) % 4;
            ans[n] = falseAns[i];
        }
        AQues cauhoidautien = this.questions.get(0);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo3/DictionaryApp/Game/questions.fxml"));
        Parent play = loader.load();
        qc = loader.getController();
        qc.setQuestion(cauhoidautien.getQues());
        qc.setButtons(ans);
        qc.setTrueNumber(trueNumber);
        Scene scene = new Scene(play);
        stage.setScene(scene);
        qc.button1.setOnAction(event1 -> next(qc.button1));
        qc.button2.setOnAction(event1 -> next(qc.button2));
        qc.button3.setOnAction(event1 -> next(qc.button3));
        qc.button4.setOnAction(event1 -> next(qc.button4));
    }

    public void next(Button button) {
        if(button.equals(qc.trueAns())){
            goal++;
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        if(cauhoihientai < numQue - 1)
        {
            cauhoihientai++;
            Random random = new Random();
            int m = random.nextInt(4) + 1;
            qc.setTrueNumber(m);
            qc.setQuestion(questions.get(cauhoihientai).getQues());
            String[] ans = new String[4];
            ans[m - 1] = this.questions.get(cauhoihientai).getAns();
            String[] falseAns = initFalseAns(cauhoihientai);
            for(int i = 0; i < 3; i++) {
                int n = (m + i) % 4;
                ans[n] = falseAns[i];
            }
            qc.setButtons(ans);
        } else {
            qc.apQues.setVisible(false);
            qc.apResult.setVisible(true);
            qc.setResult(goal + "/" + numQue);
        }
    }

    public String[] initFalseAns(int n) {
        Random random = new Random();
        int[] nums = new int[3];
        String[] falses = new String[3];
        for(int i = 0; i < 3; i++) {
            int m = random.nextInt(questionslist.getQaa().size());
            while(m == n) {
                m = random.nextInt(questionslist.getQaa().size());
            }
            while(true) {
                int count = 0;
                for(int j = 0; j < i; j++) {
                    if(nums[j] == m) {
                        count = 1;
                        m = random.nextInt(questionslist.getQaa().size());
                        break;
                    }
                }
                if (count == 0) {
                    nums[i] = m;
                    falses[i] = questionslist.getQaa().get(m).getAns();
                    break;
                }
            }
        }
        return falses;
    }

    public void initQues() {
        questions = new ArrayList<>();
        Random random = new Random();
        int[] nums = new int[numQue];
        for (int i = 0; i < numQue; i++) {
            int n = random.nextInt(questionslist.getQaa().size());
            while (true) {
                int count = 0;
                for(int j = 0; j < i; j++) {
                    if(nums[j] == n) {
                        count = 1;
                        n = random.nextInt(numQue);
                        break;
                    }
                }
                if(count == 0) {
                    break;
                }

            }
            nums[i] = n;
            questions.add(questionslist.getQaa().get(n));
        }
    }

    public Questions getQuestionslist() {
        return this.questionslist;
    }
}
