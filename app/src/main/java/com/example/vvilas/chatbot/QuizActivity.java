package com.example.vvilas.chatbot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class QuizActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportFragmentManager().beginTransaction().replace(R.id.main, new Pergunta1Fragment()).addToBackStack(null).commit();
    }
}
