package br.com.gerenciadorlds.webclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

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
        return media;
    }

    @Override
    protected void onPostExecute(String media) {
        progress.dismiss();
        Toast.makeText(ctx, media, Toast.LENGTH_LONG).show();
    }
}
