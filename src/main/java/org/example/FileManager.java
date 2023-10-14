package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    StringBuilder acepted,moviments;
    BufferedWriter bufferMoviments,
        bufferAccepted;


    public void createFile(){
        try{
            acepted = new StringBuilder();
            moviments = new StringBuilder();
            bufferMoviments = new BufferedWriter(new FileWriter("moviments.txt"));
            bufferMoviments.write("");
            bufferAccepted = new BufferedWriter(new FileWriter("accepted.txt"));
            bufferAccepted.write("");
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void appendMoviment(String text){
            moviments.append(text);
            //System.out.print(text);
    }

    public void appendAccepted(String text){
        acepted.append(text);
    }

    public void closeFiles(){
        try{
            bufferMoviments.append(moviments);
            bufferAccepted.append(acepted);

            bufferMoviments.close();
            bufferAccepted.close();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }


    private static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static String SearchFile(String dir){
        Scanner readText = new Scanner(System.in);
        List<String> fileList = new ArrayList<>();
        Set<String> files = listFilesUsingJavaIO(dir);
        int e ,i = 0;
        if(files.isEmpty()){
            System.out.println("You don't have files here.\nTry adding files in the \"files\" directory...");
            System.exit(-1);
        }
        System.out.println("Select the file you need: ");
        for (String file : files) {
            fileList.add(i,file);
            System.out.println(i+1 + ". " + file);
            i++;
        }

        do {
            System.out.print("\nSelect option: ");
            e = readText.nextInt();
            System.out.println();
        } while(e == 0);

        try{
            return fileList.get(e-1);
        } catch(IndexOutOfBoundsException err){
            System.out.println(err.getMessage() + ". Try again.");
        }

        return SearchFile(dir);  //Evil recursive
    }

    public static String readTextFromFile(String file, String path){
        try{
            StringBuilder text = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(path+"/"+file));

            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }

            reader.close();
            return text.toString();
        } catch (IOException err){
            System.out.println(err.getMessage());
            System.exit(2);
        }
        return " ";
    }
}