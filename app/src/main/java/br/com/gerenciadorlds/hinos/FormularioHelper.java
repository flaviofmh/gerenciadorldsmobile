package br.com.gerenciadorlds.hinos;

import android.text.TextUtils;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.gerenciadorlds.hinos.modelo.Hino;

/**
 * Created by FlávioMonteiro on 25/01/2016.
 */
public class FormularioHelper {

    private final EditText titulo;
    private final EditText numero;
    private final EditText data;

    private Hino hino;

    public FormularioHelper(FormularioHinoActivity activity) {
        titulo = (EditText) activity.findViewById(R.id.nome);
        numero = (EditText) activity.findViewById(R.id.numero);
        data = (EditText) activity.findViewById(R.id.data_cantado);
        hino = new Hino();
    }

    public Hino montarHino() {
        hino.setTitulo(titulo.getText().toString());
        if(numero.getText().toString().length() > 0) {
            hino.setNumero(Integer.parseInt(numero.getText().toString()));
        }
        hino.setDataUsado(data.getText().toString());
//        if(data.getText().toString().length() > 0) {
//            String[] partesData = data.getText().toString().split("/");
//            if(partesData.length == 3) {
//                Calendar dataCalendar = new GregorianCalendar(new Integer(partesData[2]),new Integer(partesData[1]),new Integer(partesData[0]));
//                hino.setDataUsado(dataCalendar.getTime());
//            }
//        }
        return hino;
    }

    public void popularFormulario(Hino hino) {
        titulo.setText(hino.getTitulo());
        data.setText(hino.getDataUsado());
        numero.setText(hino.getNumero().toString());
        this.hino = hino;
    }
}
