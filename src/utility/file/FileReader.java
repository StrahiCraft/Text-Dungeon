package utility.file;

import graphics.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<String> readFile(File file) {
        return readFile(file.getPath());
    }

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> fileContents = new ArrayList<String>();

        try {
            File file = new File(filePath);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String fileLine = fileReader.nextLine();
                fileContents.add(unFormatString(fileLine));
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(Color.getColor("bright red") +  filePath + " not found!" + Color.resetColor());
            e.printStackTrace();
        }

        return fileContents;
    }

    private static String unFormatString(String formatedString){
        String[] unformatedStringArray = formatedString.split("=");
        return unformatedStringArray[unformatedStringArray.length - 1];
    }
}
