package com.example.demo3.DictionaryApp;

import com.example.demo3.DicCommandline.DictionaryManagement;
import com.example.demo3.DicCommandline.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class addWordController {

    private DictionaryManagement dic;

    @FXML
    private TextField engText;

    @FXML
    private TextField vietText;

    @FXML
    private TextField proText;

    public void initData(DictionaryManagement dic) {
        this.dic = dic;
    }

    public void goHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo3/DictionaryApp/home.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        homeController home = loader.getController();
        home.initDataHome(dic);
        stage.setScene(homeScene);
    }

    public void addWo(ActionEvent event) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        String eng = engText.getText();
        String viet = vietText.getText();
        String pronounce = proText.getText();
        Alert alertFalse = new Alert(Alert.AlertType.WARNING);
        alertFalse.setTitle("Không thành công");
        alertFalse.setHeaderText("Từ đã có hoặc từ không hợp lệ");
        boolean check = false;
        for (int i =0 ;i < dic.getDictionary().getWordsList().size(); i++) {
            if (dic.getDictionary().getWordsList().get(i).getWordTarget().equals(eng)) {
                check = true;
                break;
            }
        }

        if (check) {
            alertFalse.show();
            return;
        } else if (eng != null && viet != null && eng.length() * viet.length() != 0) {
            if (pronounce == null) {
                pronounce = "";
            }
            pronounce = "/" + pronounce + "/";
            String s = "\n@" + eng + " " + pronounce + "\n";
            try {
                fw = new FileWriter("src/main/java/com/example/demo3/AnhViet.txt", true);
                bw = new BufferedWriter(fw);
                bw.write(s);
                bw.write(viet);
                bw.write("\n");
                bw.close();
                fw.close();
            } catch (Exception ex) {

            }
            dic.getDictionary().getWordsList().add(new Word(eng,viet,pronounce));
            Alert alertTrue = new Alert(Alert.AlertType.INFORMATION);
            alertTrue.setTitle("Thông tin");
            alertTrue.setHeaderText("Thêm thành công");
            alertTrue.show();
            return;
        }
        alertFalse.show();
    }

}
