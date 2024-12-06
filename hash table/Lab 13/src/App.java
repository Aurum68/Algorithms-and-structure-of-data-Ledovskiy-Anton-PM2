import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("files/input.txt");
        inputFile.createNewFile();
        try {
            String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            String russian = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя";
            FileWriter writer = new FileWriter(inputFile);
            Random random1 = new Random();
            Random random2 = new Random();
            Random random3 = new Random();
            for (int index = 0; index < Math.abs(random1.nextInt()); index++) {
                int i = random2.nextInt(10)+1;
                char[] str = new char[i+1];
                for (int index2 = 0; index2 < i; index2++) {
                    str[index2] = english.charAt(random3.nextInt(52));
                }
                str[i] = ' ';
                writer.write(str);
                str = new char[i+1];
                for (int index2 = 0; index2 < i; index2++) {
                    str[index2] = russian.charAt(random3.nextInt(64));
                }
                str[i] = ' ';
                writer.write(str);
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(inputFile);
        MyHashTable hashTable = new MyHashTable(scanner);
        String[] res = hashTable.createHashTable();
        File outputFile = new File("files/output.txt");
        outputFile.createNewFile();
        try {
            FileWriter writer = new FileWriter(outputFile);
            for (int index = 0; index < res.length; index++) {
                if (res[index] != null) {
                    writer.write(Integer.toString(index) + ". " + res[index] + "\n");
                }
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
