package com.example.demo3.DicCommandline;

public class Word {
    private String wordTarget;

    private String wordExplain;

    private String wordPronounce;

    /**
     * Constructors
     */
    public Word() {
        this.wordTarget = null;
        this.wordExplain = null;
        this.wordPronounce = null;
    }

    public Word(String wordTarget, String wordExplain, String wordPronounce) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordPronounce = wordPronounce;
    }

    /**
     * Setters
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setWordPronounce(String wordPronounce) {
        this.wordPronounce = wordPronounce;
    }

    /**
     * Getters
     */
    public String getWordTarget() {
        return wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public String getWordPronounce() {
        return wordPronounce;
    }
}
