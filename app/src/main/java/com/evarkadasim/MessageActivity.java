package com.evarkadasim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evarkadasim.model.MessageAdapter;
import com.evarkadasim.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


import static com.evarkadasim.ilan_ekrani1.a;
import static com.evarkadasim.login_fragment.login_user_name;
import static com.evarkadasim.ui.chats.chats_fragment.gonderen_profil;
import static com.evarkadasim.ui.chats.chats_fragment.mesaj_gonderen;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.image;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.names;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.image1;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.names1;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    ImageButton btn_send;
    EditText text_send;

    MessageAdapter messageAdapter;
    List<MessageModel> model = new ArrayList<>();
    DBHandler dbHandler;

    RecyclerView recyclerView;
    String isim;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        dbHandler = new DBHandler(this);
        dbHandler.open();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);


        if (a == 1) {
            isim = names;
            profile_image.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        }
        if (a == 2) {
            isim = names1;
            profile_image.setImageBitmap(BitmapFactory.decodeByteArray(image1, 0, image1.length));
        }
        if (a == 3) {
            isim = mesaj_gonderen;
            profile_image.setImageBitmap(BitmapFactory.decodeByteArray(gonderen_profil, 0, gonderen_profil.length));
        }


        username.setText(isim);
        cursor = dbHandler.fetchMessage();
        dbHandler.storeMessage(new MessageModel("test", "test", "test mesajı"));

        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                String gonderen = cursor.getString(cursor.getColumnIndexOrThrow("gonderen"));

                String alici = cursor.getString(cursor.getColumnIndexOrThrow("alici"));

                String mesaj = cursor.getString(cursor.getColumnIndexOrThrow("mesaj"));
                if (alici.equals(isim) && gonderen.equals(login_user_name) || alici.equals(login_user_name) && gonderen.equals(isim)) {
                    model.add(new MessageModel(alici, gonderen, mesaj));
                }
            }


        }

        messageAdapter = new MessageAdapter(MessageActivity.this, model, profile_image);
        recyclerView.setAdapter(messageAdapter);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = text_send.getText().toString();
                if (!msg.equals("")) {
                    dbHandler.storeMessage(new MessageModel(isim, login_user_name, msg));
                    model.add(new MessageModel(isim, login_user_name, msg));
                    messageAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
                } else {
                    Toast.makeText(MessageActivity.this, "Boş mesaj gönderemezsin", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call:
                Uri uri = Uri.parse("tel:" + ilan_ekrani.telefon_num);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
        }
        return true;
    }
}
