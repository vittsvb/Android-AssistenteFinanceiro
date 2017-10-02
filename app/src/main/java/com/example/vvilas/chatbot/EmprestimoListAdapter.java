package com.example.vvilas.chatbot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vvilas on 24/09/2017.
 */

public class EmprestimoListAdapter extends RecyclerView.Adapter<EmprestimoListAdapter.ViewHolder> {
    private List<Emprestimo> emprestimos;

    public EmprestimoListAdapter(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public EmprestimoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_emprestimo, parent, false);

        EmprestimoListAdapter.ViewHolder viewHolder = new EmprestimoListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmprestimoListAdapter.ViewHolder holder, int position) {
        Emprestimo emprestimo = emprestimos.get(position);
        holder.jurosEmprestimo.setText(emprestimo.getJuros());
        holder.valorMesEmprestimo.setText(emprestimo.getValorMes());
        holder.totalEmprestimo.setText(emprestimo.getTotal());

        String nome = emprestimo.getNome();

        if (nome.equalsIgnoreCase("just")) {
            holder.imageView.setImageResource(R.mipmap.just_online);
        }else if (nome.equalsIgnoreCase("lendico")){
            holder.imageView.setImageResource(R.mipmap.lendico_logo);
        }else if (nome.equalsIgnoreCase("bompracredito")){
            holder.imageView.setImageResource(R.mipmap.bompracredito);
        }else if (nome.equalsIgnoreCase("Simplic")){
            holder.imageView.setImageResource(R.mipmap.simplic_logo);
        }else if (nome.equalsIgnoreCase("geru")){
            holder.imageView.setImageResource(R.mipmap.geru);
        }

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

        public ViewHolder(View view) {
            super(view);
            jurosEmprestimo = (TextView) view.findViewById(R.id.juros);
            totalEmprestimo = (TextView) view.findViewById(R.id.total);
            valorMesEmprestimo = (TextView) view.findViewById(R.id.valorMes);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
