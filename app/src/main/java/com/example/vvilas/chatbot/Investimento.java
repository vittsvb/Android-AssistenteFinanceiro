package com.example.vvilas.chatbot;

/**
 * Created by vvilas on 11/09/2017.
 */

public class Investimento {
    private String nome;
    private String juros;
    private String total;
    private String ganho;

    public Investimento() {
        this.nome = nome;
        this.juros = juros;
        this.total = total;
        this.ganho = ganho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getGanho() {
        return ganho;
    }

    public void setGanho(String ganho) {
        this.ganho = ganho;
    }

    @Override
    public String toString() {
        return "Investimento{" +
                "nome='" + nome + '\'' +
                ", juros='" + juros + '\'' +
                ", total='" + total + '\'' +
                ", ganho='" + ganho + '\'' +
                '}';
    }
}
