package com.example.vvilas.chatbot;

import android.widget.ImageView;

/**
 * Created by vvilas on 24/09/2017.
 */

public class Emprestimo {
    private String valorMes;
    private String juros;
    private String total;
    private String nome;

    public Emprestimo() {
        this.valorMes = valorMes;
        this.juros = juros;
        this.total = total;
        this.nome = nome;
    }

    public String getValorMes() {
        return valorMes;
    }

    public void setValorMes(String valorMes) {
        this.valorMes = valorMes;
    }

    public String getJuros() {
        return juros;
    }

    public void setJuros(String juros) {
        this.juros = juros;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "valorMes='" + valorMes + '\'' +
                ", juros='" + juros + '\'' +
                ", total='" + total + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
