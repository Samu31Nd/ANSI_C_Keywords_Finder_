package org.example;

public class Main {
    public static void main(String[] args) {
        Function func = new Function();
        String directory = "./files";
        String file = FileManager.SearchFile(directory);
        String text = FileManager.readTextFromFile(file, directory);
        char []datatoChar = text.toCharArray();

        for (char c : datatoChar) {
            func.recorrido(c);
        }
        func.fm.closeFiles();
    }
}