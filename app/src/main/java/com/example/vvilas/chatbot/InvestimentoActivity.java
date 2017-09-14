package com.example.vvilas.chatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InvestimentoActivity extends AppCompatActivity {

    private SeekBar seekBarInvestimento;
    private TextView textViewInvestimento;

    private SeekBar seekBarAnos;
    private TextView textViewAnos;

    private List<Investimento> investimentos = new ArrayList<Investimento>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimento);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_investimento);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        this.seekBarInvestimento = (SeekBar) findViewById(R.id.seekBarInvestimento);
        this.textViewInvestimento = (TextView) findViewById(R.id.textViewInvestimento);

        this.seekBarAnos = (SeekBar) findViewById(R.id.seekBarAnos);
        this.textViewAnos = (TextView) findViewById(R.id.textViewAnos);

        this.textViewInvestimento.setText("R$ " + seekBarInvestimento.getProgress());
        this.textViewAnos.setText(seekBarAnos.getProgress() + " anos");

        investimento();
        adapter = new InvestimentoListAdapter(investimentos);
        recyclerView.setAdapter(adapter);

        this.seekBarInvestimento.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 10000;

            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBarInvestimento, int progressValue, boolean fromUser) {
                progress = progressValue;
                int stepSize = 1000;

                int minimo = stepSize;
                if (progress < minimo) {
                    progress = minimo;
                    seekBarInvestimento.setProgress(progress);
                } else {
                    progress = (progress / stepSize) * stepSize;
                    seekBarInvestimento.setProgress(progress);
                }
                textViewInvestimento.setText("R$ " + seekBarInvestimento.getProgress());

                investimentos.clear();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);

                investimento();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarValor) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarValor) {
            }
        });

        this.seekBarAnos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 2;

            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBarAnos, int progressValue, boolean fromUser) {
                progress = progressValue;
                int stepSize = 1;

                int minimo = 2;
                if (progress < minimo) {
                    progress = minimo;
                    seekBarAnos.setProgress(progress);
                } else {
                    progress = (progress / stepSize) * stepSize;
                    seekBarAnos.setProgress(progress);
                }
                textViewAnos.setText(seekBarAnos.getProgress() + " anos");

                investimentos.clear();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);

                investimento();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarValor) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarValor) {
            }
        });

    }

    public void investimento() {

        Investimento investimento = new Investimento();
        double juros;

        juros = 0.06;
        juros = (juros * seekBarAnos.getProgress()) + 1;

        investimento.setNome("Poupança");
        investimento.setJuros("Rendimento: 6.0% a.a.");
        investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
        investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
        investimentos.add(investimento);

        if (seekBarAnos.getProgress() <= 2) {

            investimento = new Investimento();
            juros = 0.0832;
            juros = (juros * seekBarAnos.getProgress()) + 1;
            investimento.setNome("Tesouro Prefixado 2020 (LTN)");
            investimento.setJuros("Rendimento: 8.32% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() <= 5) {

            investimento = new Investimento();
            juros = 0.0815;
            juros = (juros * seekBarAnos.getProgress()) + 1;
            investimento.setNome("Tesouro Selic 2023 (LFT)");
            investimento.setJuros("Rendimento: Indexado à Selic (~ 8.15 a.a.)");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }


        if (seekBarAnos.getProgress() <= 5) {
            investimento = new Investimento();
            juros = 0.0954;
            juros = (juros * seekBarAnos.getProgress()) + 1;
            investimento.setNome("Tesouro Prefixado 2023 (LTN)");
            investimento.setJuros("Rendimento: 9.54% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() == 3) {
            investimento = new Investimento();
            juros = 0.0469;
            juros = (juros * seekBarAnos.getProgress()) + 1;
            investimento.setNome("Tesouro IPCA+ 2024 (NTNB Princ)");
            investimento.setJuros("Rendimento: 4.69% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() <= 10 && seekBarAnos.getProgress() >= 4) {
            investimento = new Investimento();
            juros = 0.0501;
            juros = (juros * seekBarAnos.getProgress()) + 1;
            investimento.setNome("Tesouro IPCA+ 2035 (NTNB Princ)");
            investimento.setJuros("Rendimento: 5.01% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() <= 10 && seekBarAnos.getProgress() >= 6) {
            investimento = new Investimento();
            juros = 0.0501;
            juros = (juros * seekBarAnos.getProgress()) + 1;
            investimento.setNome("Tesouro IPCA+ 2045 (NTNB Princ)");
            investimento.setJuros("Rendimento: 5.01% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

    }
}
