package app.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadInputUtility {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static String getUserInput(String message) throws IOException {
        System.out.println(message);
        String input = reader.readLine();
        return input;
    }

    public static void closeReader() throws IOException{
        reader.close();
    }
}