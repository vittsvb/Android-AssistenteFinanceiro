package com.example.vvilas.chatbot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends Activity {

    public ImageView gif;
    public EditText message;

    //public TextView watsonRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gif = (ImageView) findViewById(R.id.gif);
        message = (EditText) findViewById(R.id.message);

        Glide.with(this)
                .load(R.mipmap.dots_idle)
                .asGif()
                .into(gif);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.color.colorAccent)
                .addProfiles(
                        new ProfileDrawerItem().withName("Vitor Vilas Boas").withEmail("vvilas@gmail.com").withIcon(getResources().getDrawable(R.drawable.user))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        SectionDrawerItem titulo1 = new SectionDrawerItem().withName("Assistentes");
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIcon(FontAwesome.Icon.faw_comment).withIdentifier(1).withName("Assitente Virtual");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIcon(FontAwesome.Icon.faw_comment).withIdentifier(1).withName("Análise Perfil do Investidor");
        SectionDrawerItem titulo2 = new SectionDrawerItem().withName("Simuladores");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIcon(FontAwesome.Icon.faw_calculator).withIdentifier(2).withName("Tesouro Direto");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIcon(FontAwesome.Icon.faw_calculator).withIdentifier(3).withName("Empréstimo Pessoal");

        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withSelectedItem(-1)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withToolbar(toolbar)
                .addDrawerItems(
                        titulo1,
                        item1,
                        item2,
                        titulo2,
                        item3,
                        item4


                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem == item1) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item2) {
                            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item3) {
                            Intent intent = new Intent(MainActivity.this, InvestimentoActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item4) {
                            Intent intent = new Intent(MainActivity.this, EmprestimoActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
    }


    public void enviarMsg(View view) {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String messageTrim = message.getText().toString().trim();

        if (!messageTrim.equals("")) {
            conversaWatson task = new conversaWatson();

            task.execute(message.getText().toString());
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
            Glide.with(MainActivity.this)
                    .load(R.mipmap.dots_think)
                    .asGif()
                    .into(gif);

            message.setText("");
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

//                    watsonRes.setText(text);

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


                                    Glide.with(MainActivity.this)
                                            .load(R.mipmap.dots_speak)
                                            .asGif()
                                            .into(gif);


                                    new CountDownTimer(text.length() * 75, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        public void onFinish() {
                                            Glide.with(MainActivity.this)
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
                Toast.makeText(MainActivity.this, "Erro ao realizar consulta", Toast.LENGTH_LONG).show();

            }
        }
    }
}
