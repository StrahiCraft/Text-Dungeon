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
                fileContents.add(fileReader.nextLine());
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(Color.getColor("bright red") +  filePath + " not found!" + Color.resetColor());
            e.printStackTrace();
        }

        return fileContents;
    }
}
