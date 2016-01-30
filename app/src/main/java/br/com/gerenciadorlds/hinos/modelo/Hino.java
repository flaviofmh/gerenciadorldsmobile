package br.com.gerenciadorlds.hinos.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by FlávioMonteiro on 25/01/2016.
 */
public class Hino implements Serializable {

    private Long id;

    private String titulo;

    private Integer numero;

    private String dataUsado;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDataUsado() {
        return dataUsado;
    }

    public void setDataUsado(String dataUsado) {
        this.dataUsado = dataUsado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hino hino = (Hino) o;

        if (!id.equals(hino.id)) return false;
        if (!titulo.equals(hino.titulo)) return false;
        if (!numero.equals(hino.numero)) return false;
        return dataUsado.equals(hino.dataUsado);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + titulo.hashCode();
        result = 31 * result + numero.hashCode();
        result = 31 * result + dataUsado.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getTitulo() + ", " +getDataUsado();
    }
}
