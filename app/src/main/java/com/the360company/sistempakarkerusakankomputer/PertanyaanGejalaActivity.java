package com.the360company.sistempakarkerusakankomputer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.the360company.sistempakarkerusakankomputer.models.Aturan;
import com.the360company.sistempakarkerusakankomputer.models.Kerusakan;
import com.the360company.sistempakarkerusakankomputer.models.SispakDBHelper;

import java.util.ArrayList;

public class PertanyaanGejalaActivity extends AppCompatActivity implements DialogHasilDiagnosa.OnFragmentInteraction {

    private TextView textViewKodeGejala;
    private TextView textViewPertanyaan;
    private ImageView imageViewGejala;
    private Button buttonYa;
    private Button buttonTidak;

    private SispakDBHelper db;
    private Aturan lastAturan;

    private ArrayList<Aturan> currentAturan;
    private int currentLevel;
    private int currentIndeksAturan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan_gejala);

        textViewKodeGejala = findViewById(R.id.text_view_kode_gejala);
        textViewPertanyaan = findViewById(R.id.text_view_pertanyaan);
        imageViewGejala = findViewById(R.id.image_view_gejala);
        buttonYa = findViewById(R.id.button_ya);
        buttonTidak = findViewById(R.id.button_tidak);

        currentIndeksAturan = 0;
        currentLevel = 1;

        db = SispakDBHelper.getInstance(this);
        currentAturan = db.getAturanWhereLevel(currentLevel);       // filter berdasarkan level
        loadQuestion();


        buttonYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawabYa();
            }
        });

        buttonTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawabTidak();
            }
        });
    }



    private void loadQuestion(){
        lastAturan = currentAturan.get(currentIndeksAturan);
        String kode_gejala = "G" + currentAturan.get(currentIndeksAturan).getGejalaAturan().getKodeGejala();
        textViewKodeGejala.setText(kode_gejala);
        int imageResource = currentAturan.get(currentIndeksAturan).getGejalaAturan().getImageKerusakan();
        Picasso.with(this).load(imageResource).resizeDimen(R.dimen.image_picasso_w, R.dimen.image_picasso_h).into(imageViewGejala);
        textViewPertanyaan.setText(currentAturan.get(currentIndeksAturan).getGejalaAturan().getNamaGejala());
    }


    private void jawabYa(){
        db.setCandidate(currentAturan.get(currentIndeksAturan).getAturanKodeGejala(), currentLevel);
        currentIndeksAturan = 0;
        ++currentLevel;
        currentAturan = db.newAturan(currentLevel);

        if(currentAturan.size() > 0){
            loadQuestion();
            return;
        }
        hasilDiagnosa(lastAturan.getAturanKodeKerusakan());
    }

    private void jawabTidak(){
        if(currentIndeksAturan != currentAturan.size() - 1){
            ++currentIndeksAturan;
            loadQuestion();
            return;
        }

        if(currentAturan.size() != 1){
            dialogTidakDitemukan();
            return;
        }

        db.setCandidate(currentAturan.get(currentIndeksAturan).getAturanKodeGejala(), currentLevel);
        currentIndeksAturan = 0;
        ++currentLevel;
        currentAturan = db.newAturan(currentLevel);
        if(currentAturan.size() > 0){
            loadQuestion();
            return;
        }
        hasilDiagnosa(lastAturan.getAturanKodeKerusakan());
    }


    private void hasilDiagnosa(int lastKodeKerusakan){

        Kerusakan lastKerusakan = db.getKerusakanWhereKode(lastKodeKerusakan);
        DialogHasilDiagnosa fragment = DialogHasilDiagnosa.newInstance(lastKerusakan);
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), "dialog");
    }

    private void dialogTidakDitemukan(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Pesan")
                .setMessage("Maaf, data kerusakan tidak ditemukan")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        dialog.show();
    }

    @Override
    public void fragmentInteraction(int kodeKerusakan) {
        Intent intent = new Intent(PertanyaanGejalaActivity.this, DetailKerusakanActivity.class);
        intent.putExtra("EXTRA_KODE_KERUSAKAN", kodeKerusakan);
        startActivity(intent);
        finish();
    }
}
