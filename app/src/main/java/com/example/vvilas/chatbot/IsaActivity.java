package com.example.vvilas.chatbot;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class IsaActivity extends Activity {

    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isa);

        gif = (ImageView) findViewById(R.id.gif);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);


        Glide.with(this)
                .load(R.mipmap.dots_idle)
                .asGif()
                .into(gif);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    conversaWatson task = new conversaWatson();
                    task.execute(result.get(0));
                }
                break;
            }

        }
    }

    private class SynthesisTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String TTS_username = getString(R.string.text_speech_username);
            String TTS_password = getString(R.string.text_speech_password);
            com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech textToSpeech = new com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech();
            textToSpeech.setUsernameAndPassword(TTS_username, TTS_password);

            StreamPlayer streamPlayer = new StreamPlayer();
            streamPlayer.playStream(textToSpeech.synthesize(params[0], Voice.PT_ISABELA).execute());
            return "synthesize";
        }
    }

    private class conversaWatson extends AsyncTask<String, Object, String> {

        @Override
        protected void onPreExecute() {
            Glide.with(IsaActivity.this)
                    .load(R.mipmap.dots_think)
                    .asGif()
                    .into(gif);

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                //Criar a URL (localhost é 10.0.2.2)
                URL url = new URL("https://gateway.watsonplatform.net/conversation/api/v1/workspaces/1758047a-5adf-413a-8d05-a367cf729fcb/message?version=2017-05-26");
                //Obter uma conexão
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                //Configurar a requisição
                connection.setRequestMethod("POST");
                //Tipo de dado que será devolvido pelo webservice (JSON)
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", "Basic ZGNiZjNhM2YtMmUwYi00ZWY1LTk0M2YtNTkyM2I5OGRhYTM0OlJDS3Z5R2tCa2tqMQ==");

                JSONObject json = new JSONObject();
                JSONObject textJson = new JSONObject();
                textJson.put("text", params[0]);
                json.put("input", textJson);

                OutputStreamWriter stream = new OutputStreamWriter(connection.getOutputStream());
                stream.write(json.toString());
                stream.close();

                if (connection.getResponseCode() == 200) {
                    //Ler a resposta enviada pelo webservice
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuilder outputJson = new StringBuilder();
                    String linha;
                    //Ler todas as linhas retornadas pelo ws
                    while ((linha = reader.readLine()) != null) {
                        //Adiciona cada linha no builder
                        outputJson.append(linha);
                    }
                    connection.disconnect();
                    return outputJson.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            if (s != null) {

                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject output = json.getJSONObject("output");

                    JSONArray jsonText = output.getJSONArray("text");
                    final String text = jsonText.getString(0);

                    SynthesisTask synthesisTask = new SynthesisTask();
                    synthesisTask.execute(text);

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    Typewriter writer = (Typewriter) findViewById(R.id.typewriter);

                                    if (text.length() > 200) {
                                        writer.setTextSize(18);
                                    } else {
                                        writer.setTextSize(24);
                                    }

                                    writer.setCharacterDelay(60);
                                    writer.animateText(text);


                                    Glide.with(IsaActivity.this)
                                            .load(R.mipmap.dots_speak)
                                            .asGif()
                                            .into(gif);


                                    new CountDownTimer(text.length() * 75, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        public void onFinish() {
                                            Glide.with(IsaActivity.this)
                                                    .load(R.mipmap.dots_idle)
                                                    .asGif()
                                                    .into(gif);
                                        }
                                    }.start();
                                }
                            },
                            3000);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(IsaActivity.this, "Erro ao realizar consulta", Toast.LENGTH_LONG).show();

            }
        }
    }


}


