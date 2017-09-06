package com.example.vvilas.chatbot;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class EmprestimoActivity extends AppCompatActivity {

    private SeekBar seekBarValor;
    private TextView textViewValor;

    private SeekBar seekBarParcelas;
    private TextView textViewParcelas;

    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimo);

        this.seekBarValor = (SeekBar) findViewById(R.id.seekBarValor);
        this.textViewValor = (TextView) findViewById(R.id.textViewValor);

        this.seekBarParcelas = (SeekBar) findViewById(R.id.seekBarParcelas);
        this.textViewParcelas = (TextView) findViewById(R.id.textViewParcelas);

        this.textViewValor.setText("R$ " + seekBarValor.getProgress());
        this.textViewParcelas.setText(seekBarParcelas.getProgress() + " meses");

        emprestimoJust();
        emprestimoLendico();
        emprestimoBC();
        emprestimoSimplic();
        emprestimoGeru();

        this.seekBarValor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 10000;

            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBarValor, int progressValue, boolean fromUser) {
                progress = progressValue;
                int stepSize = 500;

                int minimo = stepSize;
                if (progress < minimo) {
                    progress = minimo;
                    seekBarValor.setProgress(progress);
                } else {
                    progress = (progress / stepSize) * stepSize;
                    seekBarValor.setProgress(progress);
                }
                textViewValor.setText("R$ " + seekBarValor.getProgress());

                emprestimoJust();
                emprestimoLendico();
                emprestimoBC();
                emprestimoSimplic();
                emprestimoGeru();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarValor) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarValor) {
            }
        });

        this.seekBarParcelas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 2;

            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBarParcelas, int progressValue, boolean fromUser) {
                progress = progressValue;
                int stepSize = 1;

                int minimo = 2;
                if (progress < minimo) {
                    progress = minimo;
                    seekBarParcelas.setProgress(progress);
                } else {
                    progress = (progress / stepSize) * stepSize;
                    seekBarParcelas.setProgress(progress);
                }
                textViewParcelas.setText(seekBarParcelas.getProgress() + " meses");

                emprestimoJust();
                emprestimoLendico();
                emprestimoBC();
                emprestimoSimplic();
                emprestimoGeru();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarValor) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarValor) {
            }
        });
    }

    public void emprestimoJust() {

        CardView card_viewJust = (CardView) findViewById(R.id.card_viewJust);

        if (seekBarValor.getProgress() < 1000 || seekBarValor.getProgress() > 35000 || seekBarParcelas.getProgress() > 24) {
            card_viewJust.setVisibility(View.GONE);
        } else {
            card_viewJust.setVisibility(View.VISIBLE);
        }

        double juros = 0.028;
        TextView totalJust;
        TextView valorMesJust;
        totalJust = (TextView) findViewById(R.id.totalJust);
        valorMesJust = (TextView) findViewById(R.id.valorMesJust);
        juros = (juros * seekBarParcelas.getProgress()) + 1;

        valorMesJust.setText("R$ " + String.format("%.2f", (seekBarValor.getProgress() * juros) / seekBarParcelas.getProgress()) + "/mês");

        totalJust.setText("Total a pagar: R$ " + String.format("%.2f", seekBarValor.getProgress() * juros));


    }

    public void infoJust(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.info_dialog);
        TextView textPros = (TextView) dialog.findViewById(R.id.textPros);

        textPros.setText("– Todo o processo de pedido e contratação de empréstimo é online;\n" +
                "\n" +
                "– A liberação do dinheiro para a conta do cliente é feita em até um dia útil;\n" +
                "\n" +
                "– É solicitado apenas documento de identidade e a digitalização é feita pela internet;\n" +
                "\n" +
                "– Empréstimos de até R$ 35 mil e pagamento em até 24 meses.");

        TextView textContra = (TextView) dialog.findViewById(R.id.textContra);

        textContra.setText("– Pode pedir outros documentos no processo de aprovação, como comprovante de residência.");


        dialog.show();
    }

    public void emprestimoLendico() {
        CardView card_viewLendico = (CardView) findViewById(R.id.card_viewLendico);

        if (seekBarValor.getProgress() < 2500 || seekBarValor.getProgress() > 50000 || seekBarParcelas.getProgress() > 36) {
            card_viewLendico.setVisibility(View.GONE);
        } else {
            card_viewLendico.setVisibility(View.VISIBLE);
        }

        double juros = 0.024;
        TextView totalLendico;
        TextView valorMesLendico;
        totalLendico = (TextView) findViewById(R.id.totalLendico);
        valorMesLendico = (TextView) findViewById(R.id.valorMesLendico);
        juros = (juros * seekBarParcelas.getProgress()) + 1;

        valorMesLendico.setText("R$ " + String.format("%.2f", (seekBarValor.getProgress() * juros) / seekBarParcelas.getProgress()) + "/mês");

        totalLendico.setText("Total a pagar: R$ " + String.format("%.2f", seekBarValor.getProgress() * juros));


    }

    public void infoLendico(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.info_dialog);
        TextView textPros = (TextView) dialog.findViewById(R.id.textPros);

        textPros.setText("– Simulador mostra detalhes sobre parcelas e juros cobrados pela instituição;");

        TextView textContra = (TextView) dialog.findViewById(R.id.textContra);

        textContra.setText("– Não deixa claro se é vinculada a alguma instituição financeira;\n" +
                "\n" +
                "– É preciso imprimir formulários e digitaliza-los posteriormente para enviar.");


        dialog.show();
    }

    public void emprestimoBC() {
        CardView card_viewBC = (CardView) findViewById(R.id.card_viewBC);

        if (seekBarValor.getProgress() < 2000 || seekBarValor.getProgress() > 35000 || seekBarParcelas.getProgress() > 36) {
            card_viewBC.setVisibility(View.GONE);
        } else {
            card_viewBC.setVisibility(View.VISIBLE);
        }

        double juros = 0.02;
        TextView totalBC;
        TextView valorMesBC;
        totalBC = (TextView) findViewById(R.id.totalBC);
        valorMesBC = (TextView) findViewById(R.id.valorMesBC);
        juros = (juros * seekBarParcelas.getProgress()) + 1;

        valorMesBC.setText("R$ " + String.format("%.2f", (seekBarValor.getProgress() * juros) / seekBarParcelas.getProgress()) + "/mês");

        totalBC.setText("Total a pagar: R$ " + String.format("%.2f", seekBarValor.getProgress() * juros));


    }

    public void infoBC(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.info_dialog);
        TextView textPros = (TextView) dialog.findViewById(R.id.textPros);

        textPros.setText("– Sugere opções de empréstimos de diversas instituições.");

        TextView textContra = (TextView) dialog.findViewById(R.id.textContra);

        textContra.setText("– Para fazer a busca pelas opções de empréstimo é preciso preencher vários dados pessoais;\n" +
                "\n" +
                "– A finalização do empréstimo precisa ser feita pessoalmente na instituição escolhida.");


        dialog.show();
    }

    public void emprestimoSimplic() {
        CardView card_viewSimplic = (CardView) findViewById(R.id.card_viewSimplic);

        if (seekBarValor.getProgress() > 3500 || seekBarParcelas.getProgress() > 12) {
            card_viewSimplic.setVisibility(View.GONE);
        } else {
            card_viewSimplic.setVisibility(View.VISIBLE);
        }

        double juros = 0.158;
        TextView totalSimplic;
        TextView valorMesSimplic;
        totalSimplic = (TextView) findViewById(R.id.totalSimplic);
        valorMesSimplic = (TextView) findViewById(R.id.valorMesSimplic);
        juros = (juros * seekBarParcelas.getProgress()) + 1;

        valorMesSimplic.setText("R$ " + String.format("%.2f", (seekBarValor.getProgress() * juros) / seekBarParcelas.getProgress()) + "/mês");

        totalSimplic.setText("Total a pagar: R$ " + String.format("%.2f", seekBarValor.getProgress() * juros));

    }

    public void infoSimplic(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.info_dialog);
        TextView textPros = (TextView) dialog.findViewById(R.id.textPros);

        textPros.setText("– Se o contrato for fechado até 16h, o dinheiro é liberado no mesmo dia;\n" +
                "\n" +
                "– Todo o processo é feito online.");

        TextView textContra = (TextView) dialog.findViewById(R.id.textContra);

        textContra.setText("– Site com poucas informações e que não permite fazer simulações;\n" +
                "\n" +
                "– Cobra a maior taxa de juros entre as quatro empresas;\n" +
                "\n" +
                "– Limite de crédito de até R$ 3,5 mil;\n" +
                "\n" +
                "– Só aceita clientes dos bancos Bradesco, Caixa Econômica Federal, Itaú, Santander.");


        dialog.show();
    }

    public void emprestimoGeru() {
        CardView card_viewGeru = (CardView) findViewById(R.id.card_viewGeru);

        if (seekBarValor.getProgress() < 5000 || seekBarParcelas.getProgress() > 36) {
            card_viewGeru.setVisibility(View.GONE);
        } else {
            card_viewGeru.setVisibility(View.VISIBLE);
        }

        double juros = 0.023;
        TextView totalGeru;
        TextView valorMesGeru;
        totalGeru = (TextView) findViewById(R.id.totalGeru);
        valorMesGeru = (TextView) findViewById(R.id.valorMesGeru);
        juros = (juros * seekBarParcelas.getProgress()) + 1;

        valorMesGeru.setText("R$ " + String.format("%.2f", (seekBarValor.getProgress() * juros) / seekBarParcelas.getProgress()) + "/mês");

        totalGeru.setText("Total a pagar: R$ " + String.format("%.2f", seekBarValor.getProgress() * juros));

    }

    public void infoGeru(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.info_dialog);
        TextView textPros = (TextView) dialog.findViewById(R.id.textPros);

        textPros.setText("– Site útil, com simulador de juros;\n" +
                "\n" +
                "– Deixa explícito que a taxa de juros efetiva é maior, pois incorpora outros encargos;\n" +
                "\n" +
                "– Todo o processo é online.");

        TextView textContra = (TextView) dialog.findViewById(R.id.textContra);

        textContra.setText("– Só oferece empréstimo de uma instituição financeira. É vinculada ao Banco AndBank (Brasil) S.A.");


        dialog.show();
    }
}
