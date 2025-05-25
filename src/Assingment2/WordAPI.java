package Assingment2;

import java.io.*;
import java.net.*;

class WordAPIClient {
    public static String getRandomWord() throws IOException {
        URL url = new URL("https://random-word-api.herokuapp.com/word");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        String s=line.replaceAll ( "[\\[]\"]", "" );
        return s;
    }
}
