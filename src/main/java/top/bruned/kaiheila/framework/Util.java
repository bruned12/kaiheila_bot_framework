package top.bruned.kaiheila.framework;

import java.io.*;

public class Util {
    public static String readFileWithString(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder tempstring = new StringBuilder(1024);
            int x;
            while ((x = reader.read()) != -1) {
                tempstring.append((char) x);
            }
            return tempstring.toString();
        }
    }

    public static void writerFileWithString(File file, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
            writer.flush();
        } catch (IOException ignored) {

        }
    }
}
