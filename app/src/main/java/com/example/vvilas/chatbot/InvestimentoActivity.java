package com.example.vvilas.chatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;
import android.widget.TextView;

public class InvestimentoActivity extends AppCompatActivity {

    private SeekBar seekBarInvestimento;
    private TextView textViewInvestimento;

    private SeekBar seekBarAnos;
    private TextView textViewAnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimento);

        this.seekBarInvestimento = (SeekBar) findViewById(R.id.seekBarInvestimento);
        this.textViewInvestimento = (TextView) findViewById(R.id.textViewInvestimento);

        this.seekBarAnos = (SeekBar) findViewById(R.id.seekBarAnos);
        this.textViewAnos = (TextView) findViewById(R.id.textViewAnos);

        this.textViewInvestimento.setText("R$ " + seekBarInvestimento.getProgress());
        this.textViewAnos.setText(seekBarAnos.getProgress() + " anos");

        investimentoPoupanca();

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

                investimentoPoupanca();
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

                int minimo = stepSize;
                if (progress < minimo) {
                    progress = minimo;
                    seekBarAnos.setProgress(progress);
                } else {
                    progress = (progress / stepSize) * stepSize;
                    seekBarAnos.setProgress(progress);
                }
                textViewAnos.setText(seekBarAnos.getProgress() + " anos");

                investimentoPoupanca();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarValor) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarValor) {
            }
        });

    }

    public void investimentoPoupanca() {
        CardView card_viewPoupanca = (CardView) findViewById(R.id.card_viewPoupanca);

        double juros = 0.06;

        TextView totalPoupanca;
        TextView ganhoPoupanca;
        totalPoupanca = (TextView) findViewById(R.id.totalPoupanca);
        ganhoPoupanca = (TextView) findViewById(R.id.ganhoPoupanca);
        juros = (juros * seekBarAnos.getProgress()) + 1;

        totalPoupanca.setText("Total Resgatado: R$ " + String.format("%.2f", juros * seekBarInvestimento.getProgress()));

        ganhoPoupanca.setText("Ganho de + R$ " + String.format("%.2f", (juros - 1) * seekBarInvestimento.getProgress()));


    }
}
