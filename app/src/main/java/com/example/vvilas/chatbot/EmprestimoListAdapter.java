package com.example.vvilas.chatbot;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vvilas on 24/09/2017.
 */


public class EmprestimoListAdapter extends RecyclerView.Adapter<EmprestimoListAdapter.ViewHolder> {
    private List<Emprestimo> emprestimos;
    private Dialog dialog;
    private Context context;

    public EmprestimoListAdapter(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public EmprestimoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_emprestimo, parent, false);
        EmprestimoListAdapter.ViewHolder viewHolder = new EmprestimoListAdapter.ViewHolder(view);

        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmprestimoListAdapter.ViewHolder holder, final int position) {

        final Emprestimo emprestimo = emprestimos.get(position);
        holder.jurosEmprestimo.setText(emprestimo.getJuros());
        holder.valorMesEmprestimo.setText(emprestimo.getValorMes());
        holder.totalEmprestimo.setText(emprestimo.getTotal());

        final String nome = emprestimo.getNome();

        if (nome.equalsIgnoreCase("just")) {
            holder.imageView.setImageResource(R.mipmap.just_online);
        } else if (nome.equalsIgnoreCase("lendico")) {
            holder.imageView.setImageResource(R.mipmap.lendico_logo);
        } else if (nome.equalsIgnoreCase("bompracredito")) {
            holder.imageView.setImageResource(R.mipmap.bompracredito);
        } else if (nome.equalsIgnoreCase("Simplic")) {
            holder.imageView.setImageResource(R.mipmap.simplic_logo);
        } else if (nome.equalsIgnoreCase("geru")) {
            holder.imageView.setImageResource(R.mipmap.geru);
        } else if (nome.equalsIgnoreCase("noverde")) {
            holder.imageView.setImageResource(R.mipmap.noverde);
        }

        ((ViewHolder) holder).maisinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.info_dialog);

                if (nome.equalsIgnoreCase("just")) {
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
                } else if (nome.equalsIgnoreCase("lendico")) {
                    TextView textPros = (TextView) dialog.findViewById(R.id.textPros);
                    textPros.setText("– Simulador mostra detalhes sobre parcelas e juros cobrados pela instituição;");
                    TextView textContra = (TextView) dialog.findViewById(R.id.textContra);
                    textContra.setText("– Não deixa claro se é vinculada a alguma instituição financeira;\n" +
                            "\n" +
                            "– É preciso imprimir formulários e digitaliza-los posteriormente para enviar.");
                    dialog.show();
                } else if (nome.equalsIgnoreCase("bompracredito")) {
                    TextView textPros = (TextView) dialog.findViewById(R.id.textPros);
                    textPros.setText("– Sugere opções de empréstimos de diversas instituições.");
                    TextView textContra = (TextView) dialog.findViewById(R.id.textContra);
                    textContra.setText("– Para fazer a busca pelas opções de empréstimo é preciso preencher vários dados pessoais;\n" +
                            "\n" +
                            "– A finalização do empréstimo precisa ser feita pessoalmente na instituição escolhida.");
                    dialog.show();
                } else if (nome.equalsIgnoreCase("Simplic")) {
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
                } else if (nome.equalsIgnoreCase("geru")) {
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
        });

    }

    @Override
    public int getItemCount() {
        return emprestimos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView jurosEmprestimo;
        public TextView totalEmprestimo;
        public TextView valorMesEmprestimo;
        public ImageView imageView;
        public Button maisinfo;

        public ViewHolder(View view) {
            super(view);
            jurosEmprestimo = (TextView) view.findViewById(R.id.juros);
            totalEmprestimo = (TextView) view.findViewById(R.id.total);
            valorMesEmprestimo = (TextView) view.findViewById(R.id.valorMes);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            maisinfo = (Button) view.findViewById(R.id.maisinfo);
        }
    }
}
