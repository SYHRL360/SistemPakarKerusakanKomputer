package com.the360company.sistempakarkerusakankomputer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewInfoKerusakan;
    private CardView cardViewKonsultasi;
    private CardView cardViewBantuan;
    private CardView cardViewKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardViewInfoKerusakan = findViewById(R.id.info_kerusakan_card_view);
        cardViewKonsultasi = findViewById(R.id.konsultasi_card_view);
        cardViewBantuan = findViewById(R.id.bantuan_card_view);
        cardViewKeluar = findViewById(R.id.keluar_card_view);

        cardViewInfoKerusakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InformasiKerusakanActivity.class);
                startActivity(intent);
            }
        });

        cardViewKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PertanyaanGejalaActivity.class);
                startActivity(intent);
            }
        });

        cardViewBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BantuanAplikasiActivity.class);
                startActivity(intent);
            }
        });

        cardViewKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogExit();
            }
        });
    }


    public void alertDialogExit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Keluar")
                .setMessage("Apakah anda ingin keluar dari apalikasi?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        alertDialogExit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tentang_aplikasi, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tentang_app_menu) {
            Intent intent = new Intent(MainActivity.this, TentangAppActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
