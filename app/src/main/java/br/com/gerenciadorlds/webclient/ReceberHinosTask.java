package br.com.gerenciadorlds.webclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import br.com.gerenciadorlds.hinos.daos.HinoDAO;
import br.com.gerenciadorlds.hinos.modelo.Hino;

/**
 * Created by FlávioMonteiro on 06/10/2015.
 */
public class ReceberHinosTask extends AsyncTask<Object, Object, String> {

    private Context ctx;
    private ProgressDialog progress;


    public ReceberHinosTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Aguarde", "Sincronizando dados.");
    }

    @Override
    protected String doInBackground(Object... params) {
        String media = new WebClient("http://(IP MAQUINA):8080/gerenciadorlds/lds/hino/todoshinos").get();
        if(media != null) {
            Gson gson = new Gson();
            TypeToken<List<Hino>> hinoToken = new TypeToken<List<Hino>>(){};
            List<Hino> hinoList = gson.fromJson(media, hinoToken.getType());
            HinoDAO hinoDAO = new HinoDAO(ctx);
            for (Hino hino : hinoList) {
                if(hinoDAO.temHino(hino.getId())) {
                    hinoDAO.alterar(hino);
                } else {
                    hinoDAO.inserir(hino);
                }
            }
        } else {
            media = "Error ao sincronizar!";
        }
        return media;
    }

    @Override
    protected void onPostExecute(String media) {
        progress.dismiss();
        Toast.makeText(ctx, media, Toast.LENGTH_LONG).show();
    }
}
