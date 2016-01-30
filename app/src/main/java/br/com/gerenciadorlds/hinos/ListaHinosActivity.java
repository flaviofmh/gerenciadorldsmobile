package br.com.gerenciadorlds.hinos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.gerenciadorlds.hinos.daos.HinoDAO;
import br.com.gerenciadorlds.hinos.modelo.Hino;
import br.com.gerenciadorlds.webclient.ReceberHinosTask;

public class ListaHinosActivity extends AppCompatActivity {

    private ListView listViewHinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hinos);

        listViewHinos = (ListView) findViewById(R.id.listar_hinos);
        listViewHinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Hino hino = (Hino) listViewHinos.getItemAtPosition(position);
                Intent intentFormularioHino = new Intent(ListaHinosActivity.this, FormularioHinoActivity.class);
                intentFormularioHino.putExtra("hino",hino);
                startActivity(intentFormularioHino);
            }
        });

        Button novoHino = (Button) findViewById(R.id.novo_hino);
        novoHino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFormularioHino = new Intent(ListaHinosActivity.this, FormularioHinoActivity.class);
                startActivity(intentFormularioHino);
            }
        });
        registerForContextMenu(listViewHinos);
    }

    public void carregarHinosList() {
        HinoDAO hinoDAO = new HinoDAO(this);
        List<Hino> hinosList = hinoDAO.buscarHinos();
        hinoDAO.close();

        ArrayAdapter<Hino> adpterHino = new ArrayAdapter<Hino>(this, android.R.layout.simple_list_item_1, hinosList);
        listViewHinos.setAdapter(adpterHino);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarHinosList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Hino hino = (Hino) listViewHinos.getItemAtPosition(info.position);

                HinoDAO hinoDAO = new HinoDAO(ListaHinosActivity.this);
                hinoDAO.deletar(hino);
                hinoDAO.close();

                Toast.makeText(ListaHinosActivity.this,"Hino: " + hino.getTitulo() +" deletado.", Toast.LENGTH_SHORT).show();
                carregarHinosList();
                return false;
            }
        });
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_lista_hinos, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_baixar_dados:
                new ReceberHinosTask(this).execute();
        }

        return super.onOptionsItemSelected(item);
    }
}
