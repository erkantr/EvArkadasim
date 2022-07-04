package com.evarkadasim;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.evarkadasim.model.DBModel2;
import com.evarkadasim.model.DBModel3;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.login_fragment.login_detaylar;
import static com.evarkadasim.login_fragment.login_email;
import static com.evarkadasim.login_fragment.login_evdetay;
import static com.evarkadasim.login_fragment.login_id;
import static com.evarkadasim.login_fragment.login_il;
import static com.evarkadasim.login_fragment.login_ilce;
import static com.evarkadasim.login_fragment.login_image;
import static com.evarkadasim.login_fragment.login_kira;
import static com.evarkadasim.login_fragment.login_konutturu;
import static com.evarkadasim.login_fragment.login_name;
import static com.evarkadasim.login_fragment.login_telefon;
import static com.evarkadasim.login_fragment.login_user_email;
import static com.evarkadasim.login_fragment.login_user_id;
import static com.evarkadasim.login_fragment.login_user_image;
import static com.evarkadasim.login_fragment.login_user_name;
import static com.evarkadasim.login_fragment.login_user_pass;
import static com.evarkadasim.login_fragment.login_user_telefon;

public class kalacak_yer_kayit extends AppCompatActivity {

    Button kayit;

    MaterialEditText username, email, password, telefon, il, ilce, kira, detaylar, konutturu;
    DBHandler dbHandler;
    String user;
    String eposta;
    String pass;
    String tel;
    String ill;
    String illce;
    String kiraa;
    String detayy;
    String konutturuu;
    Bitmap imageBitmap;
    Bitmap imageToStore;
    CircleImageView imageView;
    final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayit2);
        kayit = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        telefon = findViewById(R.id.telefon);
        il = findViewById(R.id.il);
        ilce = findViewById(R.id.ilce);
        kira = findViewById(R.id.kira);
        detaylar = findViewById(R.id.detaylar);
        imageView = findViewById(R.id.photo1);
        konutturu = findViewById(R.id.konutturu);
        dbHandler = new DBHandler(this);
        dbHandler.open();
        this.setTitle("Kalacak Yer Arıyorum");
        login_fragment.ilan_turu = 1;

        if (ilan_secimi.ilan_secim_sinifi != 1) {

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent objectIntent = new Intent();
                    objectIntent.setType("image/*");

                    objectIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);
                }
            });
        } else {
            imageToStore = BitmapFactory.decodeByteArray(login_user_image, 0, login_user_image.length);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(login_user_image, 0, login_user_image.length));

            username.setText(login_user_name);
            email.setText(login_user_email);
            password.setText(login_user_pass);
            telefon.setText(login_user_telefon);

            username.setEnabled(false);
            email.setEnabled(false);
            password.setEnabled(false);
            telefon.setEnabled(false);
        }

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString().trim();
                eposta = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                tel = telefon.getText().toString().trim();
                ill = il.getText().toString().trim();
                illce = ilce.getText().toString().trim();
                kiraa = kira.getText().toString().trim();
                detayy = detaylar.getText().toString().trim();
                konutturuu = konutturu.getText().toString().trim();
                if (user.equals("") || eposta.equals("") || pass.equals("") || tel.equals("") || ill.equals("") || illce.equals("") || kiraa.equals("") || detayy.equals("") || konutturuu.equals(""))
                    Toast.makeText(kalacak_yer_kayit.this, "Tüm alanlar zorunludur", Toast.LENGTH_SHORT).show();
                else {
                    if (imageView.getDrawable() == null || imageToStore == null) {
                        Toast.makeText(kalacak_yer_kayit.this, "Lütfen Profil Fotoğrafı Seçin", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!dbHandler.checkEmail(eposta)) {
                            if (!dbHandler.checkUserName(user)) {
                                dbHandler.store2(new DBModel2(user, eposta, pass, tel, ill, illce, kiraa, detayy, konutturuu, imageToStore));
                                dbHandler.storeUser(new DBModel3(user, eposta, pass, tel, imageToStore));

                                Cursor cursor = dbHandler.GetUser1(eposta);

                                login_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                                login_image = cursor.getBlob(cursor.getColumnIndexOrThrow("profil_fotografi"));
                                login_user_image = cursor.getBlob(cursor.getColumnIndexOrThrow("profil_fotografi"));
                                login_user_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                                login_il = ill;
                                login_ilce = illce;
                                login_kira = kiraa;
                                login_email = eposta;
                                login_detaylar = detayy;
                                login_konutturu = konutturuu;
                                login_name = user;
                                login_telefon = tel;
                                login_user_name = user;
                                login_user_pass = pass;
                                login_user_email = eposta;
                                login_evdetay = null;
                                login_user_telefon = tel;

                                Intent intent = new Intent(kalacak_yer_kayit.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(kalacak_yer_kayit.this, "Bu kullanıcı adını kullanamazsınız", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(kalacak_yer_kayit.this, "Kullanıcının zaten bir hesabı bulunuyor", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                imageView.setImageBitmap(imageToStore);
                imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            }
        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
