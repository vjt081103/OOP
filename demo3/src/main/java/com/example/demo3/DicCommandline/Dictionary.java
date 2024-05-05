package com.example.demo3.DicCommandline;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private final List<Word> wordsList = new ArrayList<>();

    public List<Word> getWordsList() {
        return wordsList;
    }

    public String showWordAt(int i) {
        return wordsList.get(i).getWordTarget() + "\t\t| " +
                wordsList.get(i).getWordExplain();
    }
}
