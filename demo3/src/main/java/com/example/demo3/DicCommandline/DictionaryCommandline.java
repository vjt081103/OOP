package com.example.demo3.DicCommandline;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        this.dictionaryManagement = new DictionaryManagement();
    }

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("NO\t\t" + "|ENGLISH|t\t" + "|VIETNAMES");
        for (int i = 0;i < dictionaryManagement.getDictionary().getWordsList().size();i++) {
            System.out.println((i + "\t\t" + dictionaryManagement.getDictionary().showWordAt(i)));
        }
    }

    public void dictionarySearcher() {
        System.out.println("Nhap tu ban muon tim kiem: ");
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        for (int i = 0;i < dictionaryManagement.getDictionary().getWordsList().size(); i++) {
            if(dictionaryManagement.getDictionary().getWordsList().get(i).getWordTarget().indexOf(word) == 0) {
                System.out.println(dictionaryManagement.getDictionary().getWordsList().get(i).getWordTarget());
            }
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    public void play() throws IOException {
        Scanner scan = new Scanner(System.in);
        int num;
        while (true) {
            System.out.println("Lua chon :");
            System.out.println("1 : Them tu vao danh sach tu dien.");
            System.out.println("2 : Tim kiem tu trong tu dien.");
            System.out.println("3 : In danh sach tu dien.");
            System.out.println("4 : Xoa tu trong tu dien.");
            System.out.println("5 : Sua tu trong tu dien.");
            System.out.println("6 : Tim kiem tu");
            System.out.println("7 : Xuat du lieu ra file");
            System.out.println("8 : Thoat\n");

            num = scan.nextInt();
            if (num == 1) {
                dictionaryManagement.insertFromCommandline();
            } else if (num == 2) {
                dictionaryManagement.dictionaryLookup();
            } else if (num == 3) {
                showAllWords();
            } else if (num == 4) {
                dictionaryManagement.removeWord();
            } else if (num == 5) {
                dictionaryManagement.changeWordFromCommandLine();
            } else if (num == 6) {
                dictionarySearcher();
            } else if (num == 7) {
                dictionaryManagement.dictionaryExportToFile();
            }
            else if (num == 8) {
                break;
            }
        }
    }
}
