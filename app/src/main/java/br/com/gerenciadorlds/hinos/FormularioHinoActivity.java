package br.com.gerenciadorlds.hinos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;

import br.com.gerenciadorlds.hinos.daos.HinoDAO;
import br.com.gerenciadorlds.hinos.modelo.Hino;

public class FormularioHinoActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_hino);
        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Hino hino = (Hino) intent.getSerializableExtra("hino");
        if(hino != null) {
            helper.popularFormulario(hino);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario_hino, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Hino hino = helper.montarHino();
                HinoDAO hinoDAO = new HinoDAO(this);
                if(hino.getId() != null) {
                    hinoDAO.alterar(hino);
                } else {
                    hinoDAO.inserir(hino);
                    Toast.makeText(FormularioHinoActivity.this, "Hino " + hino.getTitulo() + " salvo!", Toast.LENGTH_SHORT).show();
                }
                hinoDAO.close();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
