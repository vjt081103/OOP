package com.example.demo3.DictionaryApp.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Questions {
    private List<AQues> qaa = new ArrayList<>();

    public Questions() throws IOException {
        qaa = new ArrayList<>();
        loadData();
    }

    public List<AQues> getQaa(){
        return this.qaa;
    }
    private void loadData() throws IOException{
        File file = new File("src/main/java/com/example/demo3/questions.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while(true) {
            String line = br.readLine();
            if(line == null || line.isEmpty()) {
                break;
            }
            String[] words = line.split("\t");
            String que = words[0];
            String ans = words[2];
            qaa.add(new AQues(que,ans));
        }
        br.close();
        fr.close();
    }

}