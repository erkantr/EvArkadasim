package com.evarkadasim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.evarkadasim.model.LoginAdapter;
import com.google.android.material.tabs.TabLayout;

public class StartActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Giriş Yap"));
        tabLayout.addTab(tabLayout.newTab().setText("Kaydol"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager());
        viewPager.setAdapter(loginAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);

        tabLayout.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
    }
}
