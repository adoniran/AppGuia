package model;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import model.Atividade;

/**
 * Created by adoniran on 29/11/17.
 */

public class LocalizaHTTP extends AsyncTask<Void, Void, Atividade> {

    private String cep;

    public LocalizaHTTP(String cep) {
        this.cep = cep;
    }

    @Override
    protected Atividade doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        if (this.cep != null && this.cep.length() == 8) {
            try {
                URL url = new URL("https://viacep.com.br/ws/"+this.cep+"/json/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(), Atividade.class);
    }
}
