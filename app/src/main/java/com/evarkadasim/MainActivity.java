package com.evarkadasim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.evarkadasim.model.PagerAdapter1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import static com.evarkadasim.login_fragment.login_name;


public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager MyViewPager;
    private TabLayout MyTabLayout;
    private PagerAdapter1 pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_settings_black_24dp));

        MyViewPager = findViewById(R.id.viewpager_message);
        pagerAdapter = new PagerAdapter1(getSupportFragmentManager());
        MyViewPager.setAdapter(pagerAdapter);

        MyTabLayout = findViewById(R.id.tablayout);
        MyTabLayout.setupWithViewPager(MyViewPager);
        MyTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        /*
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
Date d = new Date();
String dayOfTheWeek = sdf.format(d);
         */


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_name == null) {

                    Intent intent = new Intent(MainActivity.this, ilan_secimi.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Yeni bir ilan oluşturmadan önce mevcut bulunan ilanınızı silmeniz gerekiyor", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ilanım:
                if (login_name == null) {
                    Toast.makeText(this, "İlanınız bulunmamaktadır. Lütfen bir ilan oluşturun", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ilan.class);
                    startActivity(intent);
                }
                break;
            case R.id.profilim:
                Intent intent1 = new Intent(MainActivity.this, profil.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
