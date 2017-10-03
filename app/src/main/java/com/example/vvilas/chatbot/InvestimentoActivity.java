package com.example.vvilas.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InvestimentoActivity extends Activity {

    private SeekBar seekBarInvestimento;
    private TextView textViewInvestimento;

    private SeekBar seekBarAnos;
    private TextView textViewAnos;

    private List<Investimento> investimentos = new ArrayList<Investimento>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimento);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.color.md_orange_A200)
                .addProfiles(
                        new ProfileDrawerItem().withName("Usuario").withEmail("user@user.com").withIcon(getResources().getDrawable(R.drawable.bg_bubble_self))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIcon(FontAwesome.Icon.faw_comment).withIdentifier(1).withName("Assitente Virtual");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIcon(FontAwesome.Icon.faw_comment).withIdentifier(1).withName("Quiz");
        SectionDrawerItem titulo1 = new SectionDrawerItem().withName("Calculadoras");
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
                        item1,
                        item2,
                        titulo1,
                        item3,
                        item4


                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem == item1) {
                            Intent intent = new Intent(InvestimentoActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item2) {
                            Intent intent = new Intent(InvestimentoActivity.this, QuizActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item3) {
                            Intent intent = new Intent(InvestimentoActivity.this, InvestimentoActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item4) {
                            Intent intent = new Intent(InvestimentoActivity.this, EmprestimoActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_investimento);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        this.seekBarInvestimento = (SeekBar) findViewById(R.id.seekBarInvestimento);
        this.textViewInvestimento = (TextView) findViewById(R.id.textViewInvestimento);

        this.seekBarAnos = (SeekBar) findViewById(R.id.seekBarAnos);
        this.textViewAnos = (TextView) findViewById(R.id.textViewAnos);

        this.textViewInvestimento.setText("R$ " + seekBarInvestimento.getProgress());
        this.textViewAnos.setText(seekBarAnos.getProgress() + " anos");

        investimento();
        adapter = new InvestimentoListAdapter(investimentos);
        recyclerView.setAdapter(adapter);

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

                investimentos.clear();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);

                investimento();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);
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

                investimentos.clear();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);

                investimento();
                adapter = new InvestimentoListAdapter(investimentos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarValor) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarValor) {
            }
        });

    }

    public long convertDate(int day, int month, int year) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, 1);
        today.set(Calendar.MONTH, 0); // 0-11 so 1 less

        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH, day);
        thatDay.set(Calendar.MONTH, month); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);

        long diff = thatDay.getTimeInMillis() - today.getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }

    public void investimento() {
        Investimento investimento;
        double juros;
        double ganho = 0;
        double ipca;
        if (seekBarAnos.getProgress() == 1) {
            ipca = 4.12 / 100;
        } else if (seekBarAnos.getProgress() == 2) {
            ipca = 4.25 / 100;
        } else {
            ipca = 4.00 / 100;
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(1, 0, 2020)) {
            investimento = new Investimento();
            juros = 0.0802;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += +ganho;
            }
            investimento.setNome("Tesouro Prefixado 2020 (LTN)");
            investimento.setJuros("Rendimento: 8.02% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(1, 2, 2023)) {
            investimento = new Investimento();
            juros = 0.0825;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += +ganho;
            }
            investimento.setNome("Tesouro Selic 2023 (LFT)");
            investimento.setJuros("Rendimento: 0.01% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }


        if (seekBarAnos.getProgress() * 365 <= convertDate(1, 0, 2023)) {
            investimento = new Investimento();
            juros = 0.0932;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += +ganho;
            }

            investimento.setNome("Tesouro Prefixado 2023 (LTN)");
            investimento.setJuros("Rendimento: 9.32% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(15, 7, 2024)) {
            investimento = new Investimento();
            juros = 0.0467 + ipca;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += +ganho;
            }

            investimento.setNome("Tesouro IPCA+ 2024 (NTNB Princ)");
            investimento.setJuros("Rendimento: 4.74% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 < convertDate(15, 7, 2026)) {
            investimento = new Investimento();
            juros = 0.0479 + ipca;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += ganho;
            }
            investimento.setNome("Tesouro IPCA+ com Juros Semestrais 2026 (NTNB)");
            investimento.setJuros("Rendimento: 4.79% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }


        if (seekBarAnos.getProgress() * 365 <= convertDate(1, 0, 2027)) {
            investimento = new Investimento();
            juros = 0.0960;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * (juros - 0.003);
                valor += ganho;
            }
            investimento.setNome("Tesouro Prefixado com Juros Semestrais 2027 (NTNF)");
            investimento.setJuros("Rendimento: 9.60% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(15, 4, 2035)) {
            investimento = new Investimento();
            juros = 0.0503 + ipca;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += ganho;
            }
            investimento.setNome("Tesouro IPCA+ 2035 (NTNB Princ)");
            investimento.setJuros("Rendimento: 5.03% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(15, 4, 2035)) {
            investimento = new Investimento();
            juros = 0.0494 + ipca;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += ganho;
            }
            investimento.setNome("Tesouro IPCA+ com Juros Semestrais 2035 (NTNB)");
            investimento.setJuros("Rendimento: 4.94% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(15, 4, 2045)) {
            investimento = new Investimento();
            juros = 0.0503 + ipca;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += ganho;
            }
            investimento.setNome("Tesouro IPCA+ 2045 (NTNB Princ)");
            investimento.setJuros("Rendimento: 5.03% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }

        if (seekBarAnos.getProgress() * 365 <= convertDate(15, 7, 2050)) {
            investimento = new Investimento();
            juros = 0.0505 + ipca;
            double valor = seekBarInvestimento.getProgress();

            for (int i = 0; i < seekBarAnos.getProgress(); i++) {
                ganho = valor * juros;
                valor += ganho;
            }
            investimento.setNome("Tesouro IPCA+ com Juros Semestrais 2050 (NTNB)");
            investimento.setJuros("Rendimento: 5.05% a.a.");
            investimento.setTotal("Total Resgatado: R$ " + String.format("%.2f", valor));
            investimento.setGanho("Ganho de + R$ " + String.format("%.2f", valor - seekBarInvestimento.getProgress()));
            investimentos.add(investimento);
        }
    }
}
