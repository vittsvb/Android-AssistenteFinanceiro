package com.example.vvilas.chatbot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vvilas on 02/10/2017.
 */

public class Pergunta4Fragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pergunta4, container, false);
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
                getFragmentManager().beginTransaction().replace(R.id.main, new Resposta2Fragment()).addToBackStack(null).commit();
                break;
            case R.id.resposta2:
//                getFragmentManager().beginTransaction().replace(R.id.main, new Resposta3Fragment()).addToBackStack(null).commit();
                break;
        }

    }
}
