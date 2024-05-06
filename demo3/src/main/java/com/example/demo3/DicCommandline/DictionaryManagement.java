package com.example.demo3.DicCommandline;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    private final Dictionary dictionary;

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        FileWriter fw = null;
        BufferedWriter bw = null;
        System.out.println("Nhap so luong tu muon them : ");
        int num = Integer.parseInt(scanner.next());
        String wordTarget;
        String wordExplain;
        scanner.nextLine();
        for (int i = 0; i < num; i++) {
            System.out.println("Nhap tu tieng anh: ");
            wordTarget = scanner.nextLine();
            System.out.println("Nhap nghia: ");
            wordExplain = scanner.nextLine();
            Word word = new Word(wordTarget, wordExplain, "");
            dictionary.getWordsList().add(word);
            String s = wordTarget + "\t" + wordExplain;
            try {
                fw = new FileWriter("src/main/java/com/example/demo3/AnhViet.txt", true);
                bw = new BufferedWriter(fw);
                bw.write(s);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException ex) {
                System.err.println("Khong ton tai file");
            }
        }
    }

    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu tieng anh muon tim : ");
        String str = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWordTarget().equals(str)) {
                System.out.println(dictionary.showWordAt(i));
                check = true;
            }
        }
        if (!check) {
            System.out.println("Khong tim thay!");
        }
    }

    public void removeWord() {
        Scanner scanner = new Scanner(System.in);
        int choose;
        do {
            System.out.println("Lua chon tim kiem tu muon xoa : ");
            System.out.println("1 : Tieng Anh");
            System.out.println("2 : Tieng Viet");
            choose = scanner.nextInt();
        } while (choose != 1 && choose != 2);
        scanner.nextLine();
        boolean check = false;
        if (choose == 1) {
            System.out.print("Nhap tu tieng anh can xoa: ");
            String wordRemove = scanner.nextLine();
            for (int i = 0; i < dictionary.getWordsList().size(); i++) {
                if (dictionary.getWordsList().get(i).getWordTarget().equals(wordRemove)) {
                    dictionary.getWordsList().remove(i);
                    check = true;
                }
            }
        } else if (choose == 2) {
            System.out.print("Nhap tu tieng Viet can xoa: ");
            String wordRemove = scanner.nextLine();
            for (int i = 0; i < dictionary.getWordsList().size(); i++) {
                if (dictionary.getWordsList().get(i).getWordExplain().equals(wordRemove)) {
                    dictionary.getWordsList().remove(i);
                    check = true;
                }
            }
        }
        if (!check) {
            System.out.println("Khong tim thay tu can xoa!");
        }
    }

    public void insertFromFile() {
        File file = new File("src/main/java/com/example/demo3/AnhViet.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Khong tim thay file");
        }
        String str;
        String eng;
        String viet;
        int numOfTab;
        while (scan.hasNext()) {
            str = scan.nextLine();
            numOfTab = str.indexOf('\t');
            eng = str.substring(0, numOfTab);
            viet = str.substring(numOfTab + 1);
            Word word = new Word(eng, viet, "");
            dictionary.getWordsList().add(word);
        }

        scan.close();
    }

    private String getWord(String line) {
        if (line.indexOf('/') == -1) {
            return line;
        }
        return line.substring(1, line.indexOf('/') - 1);
    }

    private String getPronounce(String line) {
        if (line.indexOf('/') == -1) {
            return "";
        }
        return line.substring(line.indexOf('/'));
    }

    public void insertTxt() {
        String line;
        boolean check = false;
        String target = "";
        String explain = "";
        String pronounce = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/example/demo3/AnhViet.txt"));
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0 && target.length() != 0) {
                    dictionary.getWordsList().add(new Word(target, explain, pronounce));
                    target = "";
                    explain = "";
                    pronounce = "";
                    check = false;
                }
                if (check && line.length() != 0) {
                    explain = explain.concat(line + "\n");
                }
                if (line.length() > 1 && line.charAt(0) == '@') {
                    target = getWord(line);
                    pronounce = getPronounce(line);
                    check = true;
                }
            }
            if (target.length() != 0) {
                dictionary.getWordsList().add(new Word(target, explain, pronounce));
            }
        } catch (Exception e) {
            System.err.println("Can't read file " + e);
        }
    }

    public void changeWordFromCommandLine() {
        System.out.println("Nhap tu ban muon sua nghia: ");
        Scanner scan = new Scanner(System.in);
        String target = scan.nextLine();
        System.out.println("Nhap nghia cua tu ban muon sua");
        String explain = scan.nextLine();
        boolean check = false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWordTarget().equals(target)) {
                check = true;
                dictionary.getWordsList().get(i).setWordExplain(explain);
            }
        }
        if (!check) {
            System.out.println("Tu cua ban chua co trong tu dien");
        }
    }

    public void dictionaryExportToFile() throws IOException {
        File file = new File("/com/example/demo3/DictionaryApp/AnhViet.txt");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            String wordLabel = "\n" + "@" + dictionary.getWordsList().get(i).getWordTarget() + " " + dictionary.getWordsList().get(i).getWordPronounce() + "\n";
            outputStreamWriter.write(wordLabel);
            String wordExplain = dictionary.getWordsList().get(i).getWordExplain() + "\n";
            outputStreamWriter.write(wordExplain);
        }
        outputStreamWriter.flush();
    }

    public boolean changeWordExplain(String newExplain, String target) {
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWordTarget().equals(target)) {
                dictionary.getWordsList().get(i).setWordExplain(newExplain);
                return true;
            }
        }
        return false;
    }

    public boolean removeCurrentWord(String wordRemove) {
        if (wordRemove == null) return false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWordTarget().equals(wordRemove)) {
                dictionary.getWordsList().remove(i);
                return true;
            }
        }
        return false;
    }
}
