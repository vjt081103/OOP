package com.example.demo3.DictionaryApp;

import com.example.demo3.DicCommandline.DictionaryManagement;
import com.example.demo3.DictionaryApp.Game.gameController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML
    private TextField text;

    @FXML
    private Text engLabel;

    @FXML
    private Text vietLabel;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button changeButton = new Button();

    @FXML
    private Button changeButton2 = new Button();

    @FXML
    private Button changeButton3 = new Button();



    private ArrayList<String> listEng = new ArrayList<>();

    private ArrayList<String> listViet = new ArrayList<>();

    private DictionaryManagement dic = new DictionaryManagement();

    private String currentWord;

    private VoiceGG voiceGG = new VoiceGG("kevin16");

    public void initDataHome(DictionaryManagement dic) {
        this.dic = dic;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeButton.setVisible(false);
        changeButton2.setVisible(false);
        changeButton3.setVisible(false);
        dic.insertTxt();
        listEng = getAllWord();
        listView.getItems().setAll(listEng);

        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() != 0) {
                listView.getItems().clear();
                vietLabel.setText("");
                engLabel.setText("");
                listView.getItems().setAll(lookUpWord(newValue));
            } else {
                listView.getItems().setAll(listEng);
            }
            changeButton.setVisible(false);
            changeButton2.setVisible(false);
            changeButton3.setVisible(false);
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    currentWord = listView.getSelectionModel().getSelectedItem();
                    if (currentWord.equals("Not Found")) {
                        vietLabel.setText("Không có thông tin");
                        engLabel.setText("Not Found");
                    } else {
                        ArrayList<String> wordInfo = getExplain(currentWord);
                        engLabel.setText(currentWord + " " + wordInfo.get(1));
                        vietLabel.setText(wordInfo.get(0));
                    }
                    changeButton.setVisible(true);
                    changeButton2.setVisible(true);
                    changeButton3.setVisible(true);
                }
            }
        });
    }

    public ArrayList<String> getExplain (String target) {
        ArrayList<String> newList = new ArrayList<>();
        if(target == null || target.length() == 0) {
            return newList;
        }
        for (int i = 0; i < dic.getDictionary().getWordsList().size(); i++) {
            if (dic.getDictionary().getWordsList().get(i).getWordTarget().equals(target)) {
                newList.add(dic.getDictionary().getWordsList().get(i).getWordExplain());
                newList.add(dic.getDictionary().getWordsList().get(i).getWordPronounce());
                return newList;
            }
        }
        return newList;
    }

    public ArrayList<String> getAllWord() {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < dic.getDictionary().getWordsList().size(); i++) {
            newList.add(dic.getDictionary().getWordsList().get(i).getWordTarget());
        }
        return newList;
    }

    public ArrayList<String> lookUpWord (String word) {
        ArrayList<String> listWord = new ArrayList<>();

        if (word == null) {
            return listWord;
        }

        boolean check = false;
        for (int i = 0 ;i < dic.getDictionary().getWordsList().size(); i++) {
            if (dic.getDictionary().getWordsList().get(i).getWordTarget().indexOf(word) == 0) {
                listWord.add(dic.getDictionary().getWordsList().get(i).getWordTarget());
                check = true;
            }
        }
        if (!check) {
            listWord.add("Not Found");
        }
        return listWord;
    }

    public void gototoAddWord(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addWord.fxml"));
        Parent addWord = loader.load();
        Scene addScene = new Scene(addWord);
        addWordController addController = loader.getController();
        addController.initData(dic);
        stage.setScene(addScene);
    }

    public void gotoGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game/game.fxml"));
        Parent game = loader.load();
        gameController gameController = loader.getController();
        Scene scene = new Scene(game);
        stage.setScene(scene);
    }

    public void gotoTextTranslation(ActionEvent event1) throws IOException {
        Stage stage1 = (Stage) ((Node) event1.getSource()).getScene().getWindow();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("GGTranslate.fxml"));
        Parent trans = loader1.load();
        Scene TransScene = new Scene(trans);
        stage1.setScene(TransScene);
    }

    public void changeExplain() throws IOException {
        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Sửa nghĩa từ");
        dialog.setHeaderText("Sửa nghĩa từ: " + engLabel.getText());
        dialog.setContentText("Nghĩa");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (name == null || name.length() == 0) {
                showAlert("Nghĩa mới không hợp lệ");
                return;
            }
            boolean check = dic.changeWordExplain(name,this.currentWord);
            vietLabel.setText(name);
            showAlert("Đổi nghĩa thành công");
            if (check) {
                try {
                    dic.dictionaryExportToFile();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        });
    }

    public void showAlert(String newAlert) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText(newAlert);
        alert.showAndWait();
    }

    public void deleteWord(ActionEvent e) throws IOException {
        boolean check = dic.removeCurrentWord(currentWord);
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        showAlert(("xóa thành công"));
        if (check) {
            listEng = getAllWord();
            try {
                dic.dictionaryExportToFile();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void speak(ActionEvent event) {
        String target = listView.getSelectionModel().getSelectedItem();
        String targetRes = target.replace('-',' ');
        voiceGG.say(targetRes);
    }
}
