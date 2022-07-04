package com.evarkadasim;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.login_fragment.login_evdetay;
import static com.evarkadasim.login_fragment.login_name;
import static com.evarkadasim.login_fragment.login_user_email;
import static com.evarkadasim.login_fragment.login_user_image;
import static com.evarkadasim.login_fragment.login_user_name;
import static com.evarkadasim.login_fragment.login_user_pass;
import static com.evarkadasim.login_fragment.login_user_telefon;

public class profil extends AppCompatActivity {

    DBHandler dbHandler;

    CircleImageView imageView;
    final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    Bitmap imageToStore;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        dbHandler = new DBHandler(this);
        dbHandler.open();

        imageView = findViewById(R.id.profile_image);
        final TextView kullanici_adi = findViewById(R.id.fullname);
        final TextView email = findViewById(R.id.email);
        final TextInputEditText kullanici_adi_edit = findViewById(R.id.kullanici_adi_edit);
        final TextInputEditText emaiL_edit = findViewById(R.id.email_edit);
        final TextInputEditText telefon_edit = findViewById(R.id.telefon_edit);
        final TextInputEditText sifre_edit = findViewById(R.id.sifre_edit);
        Button guncelle = findViewById(R.id.guncelle);

        imageView.setImageBitmap(BitmapFactory.decodeByteArray(login_user_image, 0, login_user_image.length));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objectIntent = new Intent();
                objectIntent.setType("image/*");

                objectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);
            }
        });
        kullanici_adi.setText(login_user_name);
        email.setText(login_user_email);
        kullanici_adi_edit.setText(login_user_name);
        emaiL_edit.setText(login_user_email);
        telefon_edit.setText(login_user_telefon);
        sifre_edit.setText(login_user_pass);

        ImageView cikis = findViewById(R.id.logout);

        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(profil.this, StartActivity.class);
                startActivity(intent);
            }
        });

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(profil.this);
            }
        });

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHandler.GetUser3(login_user_email);
                cursor.moveToFirst();
                final int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                AlertDialog.Builder builder = new AlertDialog.Builder(profil.this);
                builder.setTitle("Profilinizi Güncellemek İstediğinize Emin misiniz?");
                builder.setMessage("Kullanıcı adınızı değiştirirseniz, eski mesaj bildirimleriniz silinecektir.");
                builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (imageToStore == null) {
                            imageToStore = BitmapFactory.decodeByteArray(login_user_image, 0, login_user_image.length);
                        }

                        if (kullanici_adi_edit.getText().toString().trim().equals("") || emaiL_edit.getText().toString().trim().equals("") || sifre_edit.getText().toString().trim().equals("") || telefon_edit.getText().toString().trim().equals("")) {
                            Toast.makeText(profil.this, "Tüm Alanlar Zorunludur", Toast.LENGTH_SHORT).show();
                        } else {

                            if (!kullanici_adi_edit.getText().toString().trim().equals(login_user_name) && dbHandler.checkUserName(kullanici_adi_edit.getText().toString().trim()) || !emaiL_edit.getText().toString().trim().equals(login_user_email) && dbHandler.checkEmail(emaiL_edit.getText().toString().trim())) {
                                Toast.makeText(profil.this, "Bu kullanıcı adı veya e-posta adresini kullanamazsınız", Toast.LENGTH_SHORT).show();

                            } else {

                                dbHandler.editUser(id, kullanici_adi_edit.getText().toString().trim(), emaiL_edit.getText().toString().trim(), sifre_edit.getText().toString().trim(), telefon_edit.getText().toString().trim(), imageToStore);

                                if (login_evdetay == null) {
                                    Cursor cursor1 = dbHandler.GetUser1(login_user_email);

                                    int id1 = cursor1.getInt(cursor1.getColumnIndexOrThrow("_id"));

                                    dbHandler.edit1(id1, kullanici_adi_edit.getText().toString().trim(), emaiL_edit.getText().toString().trim(), sifre_edit.getText().toString().trim(), telefon_edit.getText().toString().trim(), imageToStore);

                                    Toast.makeText(profil.this, "Bilgileriniz Güncellendi", Toast.LENGTH_SHORT).show();
                                } else {
                                    Cursor cursor2 = dbHandler.GetUser2(login_user_email);

                                    int id1 = cursor2.getInt(cursor2.getColumnIndexOrThrow("_id"));

                                    dbHandler.edit2(id1, kullanici_adi_edit.getText().toString().trim(), emaiL_edit.getText().toString().trim(), sifre_edit.getText().toString().trim(), telefon_edit.getText().toString().trim(), imageToStore);
                                }

                                Toast.makeText(profil.this, "Bilgileriniz Güncellendi", Toast.LENGTH_SHORT).show();


                                login_user_name = kullanici_adi_edit.getText().toString();
                                login_user_email = emaiL_edit.getText().toString();
                                login_user_pass = sifre_edit.getText().toString();
                                login_user_telefon = telefon_edit.getText().toString();
                                login_name = kullanici_adi_edit.getText().toString();

                            }
                        }
                    }
                });
                builder.setPositiveButton("Hayır", null);
                builder.show();


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
