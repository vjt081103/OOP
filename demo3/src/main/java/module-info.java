module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;
    requires google.api.translate.java;
    requires java.google.speech.api;


    opens com.example.demo3.DictionaryApp to javafx.fxml;
    exports com.example.demo3.DicCommandline;
    exports com.example.demo3.DictionaryApp;
    exports com.example.demo3.DictionaryApp.Game;
    opens com.example.demo3.DictionaryApp.Game to javafx.fxml;
}