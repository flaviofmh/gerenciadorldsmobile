package br.com.gerenciadorlds.hinos.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciadorlds.hinos.modelo.Hino;

/**
 * Created by FlávioMonteiro on 26/01/2016.
 */
public class HinoDAO extends SQLiteOpenHelper {

    public HinoDAO(Context context) {
        super(context, "gerenciadorlds", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE hino (id INTEGER PRIMARY KEY, titulo TEXT, numero INTEGER, dataUsado TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST hino";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Hino hino) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesHino(hino);

        db.insert("hino", null, dados);
    }

    @NonNull
    public ContentValues getContentValuesHino(Hino hino) {
        ContentValues dados = new ContentValues();
        dados.put("titulo", hino.getTitulo());
        dados.put("numero", hino.getNumero());
        dados.put("dataUsado", hino.getDataUsado());
        return dados;
    }

    public List<Hino> buscarHinos() {
        String sql = "SELECT * FROM hino";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Hino> hinoList = new ArrayList<Hino>();
        while (c.moveToNext()) {
            Hino hino = new Hino();
            hino.setTitulo(c.getString(c.getColumnIndex("titulo")));
            hino.setNumero(c.getInt(c.getColumnIndex("numero")));
            hino.setDataUsado(c.getString(c.getColumnIndex("dataUsado")));
            hino.setId(c.getLong(c.getColumnIndex("id")));
            hinoList.add(hino);
        }
        c.close();
        return hinoList;
    }


    public void deletar(Hino hino) {
        SQLiteDatabase sq = getWritableDatabase();
        String[] params = {hino.getId().toString()};
        sq.delete("Hino", "id = ?", params);
    }

    public void alterar(Hino hino) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues cv = getContentValuesHino(hino);
        String[] params = {hino.getId().toString()};
        sq.update("hino", cv, "id = ?", params);
    }
}
