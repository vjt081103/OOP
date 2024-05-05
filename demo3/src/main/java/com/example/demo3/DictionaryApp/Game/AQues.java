package com.example.demo3.DictionaryApp.Game;

public class AQues {
    private String ques;
    private String ans;

    public AQues(String ques, String ans) {
        this.ques= ques;
        this.ans = ans;
    }

    public String getQues(){
        return this.ques;
    }

    public String getAns() {
        return this.ans;
    }

}
