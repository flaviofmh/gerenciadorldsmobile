package br.com.gerenciadorlds.webclient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by FlávioMonteiro on 04/10/2015.
 */
public class WebClient {

    private String url;

    public WebClient(String url) {
        this.url = url;
    }

    public String get() {
        try {
            URL urlAcesso = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlAcesso.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            String resposta = new String();
            if (urlConnection.getResponseCode() != 200) {
                resposta = "resposta nao foi " + urlConnection.getResponseCode();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //String line = reader.readLine();
            resposta = reader.readLine();
//            while (line != null) {
//                System.out.println(line);
//                line = reader.readLine();
//            }
            urlConnection.disconnect();
            return resposta;
        } catch (IOException e) {
            e.printStackTrace();
            String resposta = e.getMessage();
            return resposta;
        }
    }
}
