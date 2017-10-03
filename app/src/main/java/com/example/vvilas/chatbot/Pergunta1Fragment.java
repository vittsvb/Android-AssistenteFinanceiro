package com.example.vvilas.chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vvilas on 28/09/2017.
 */

public class Pergunta1Fragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pergunta1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        Button resposta1 = view.findViewById(R.id.resposta1);
        Button resposta2 = view.findViewById(R.id.resposta2);
        resposta1.setOnClickListener(this);
        resposta2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resposta1:
                getFragmentManager().beginTransaction().replace(R.id.main, new Pergunta2Fragment()).addToBackStack(null).commit();
                break;
            case R.id.resposta2:
                Intent intent = new Intent(getActivity(), InvestimentoActivity.class);
                getActivity().finish();
                startActivity(intent);
                break;
        }
    }
}
