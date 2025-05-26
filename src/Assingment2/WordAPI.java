package Assingment2;

import java.io.*;
import java.net.*;

public class APIClient {
    public static String getRandomWord() {
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word?number=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = reader.readLine(); // e.g., ["hangman"]
            reader.close();

            return line.substring(2, line.length() - 2); // Extract word from ["word"]
        } catch (Exception e) {
            e.printStackTrace();
            return "default";
        }
    }
}
