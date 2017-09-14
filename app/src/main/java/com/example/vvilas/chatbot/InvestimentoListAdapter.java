package com.example.vvilas.chatbot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vvilas on 11/09/2017.
 */

public class InvestimentoListAdapter extends RecyclerView.Adapter<InvestimentoListAdapter.ViewHolder> {

    private List<Investimento> investimentos;

    public InvestimentoListAdapter(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    @Override
    public InvestimentoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_investimentos, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Investimento investimento = investimentos.get(position);
        holder.nomeInvestimento.setText(investimento.getNome());
        holder.jurosInvestimento.setText(investimento.getJuros());
        holder.totalInvestimento.setText(investimento.getTotal());
        holder.ganhoInvestimento.setText(investimento.getGanho());
    }

    @Override
    public int getItemCount() {
        return investimentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomeInvestimento;
        public TextView jurosInvestimento;
        public TextView totalInvestimento;
        public TextView ganhoInvestimento;

        public ViewHolder(View view) {
            super(view);
            nomeInvestimento = (TextView) view.findViewById(R.id.nome);
            jurosInvestimento = (TextView) view.findViewById(R.id.juros);
            totalInvestimento = (TextView) view.findViewById(R.id.total);
            ganhoInvestimento = (TextView) view.findViewById(R.id.ganho);
        }
    }
}
