package com.example.vvilas.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

public class QuizActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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
                            Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item2) {
                            Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item3) {
                            Intent intent = new Intent(QuizActivity.this, InvestimentoActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        if (drawerItem == item4) {
                            Intent intent = new Intent(QuizActivity.this, EmprestimoActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();

        getSupportFragmentManager().beginTransaction().replace(R.id.main, new Pergunta1Fragment()).addToBackStack(null).commit();
    }
}
