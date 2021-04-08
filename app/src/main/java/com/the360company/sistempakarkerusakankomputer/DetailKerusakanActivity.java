package com.the360company.sistempakarkerusakankomputer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.the360company.sistempakarkerusakankomputer.models.Kerusakan;
import com.the360company.sistempakarkerusakankomputer.models.SispakDBHelper;

public class DetailKerusakanActivity extends AppCompatActivity {

    private ImageView imageViewKerusakan;
    private TextView textViewNamaKerusakan;
    private TextView textViewDeskripsi;
    private TextView textViewGejalaKerusakan;
    private TextView textViewSolusi;

    private Kerusakan kerusakan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kerusakan);

        imageViewKerusakan = findViewById(R.id.image_view_kerusakan);
        textViewNamaKerusakan = findViewById(R.id.text_view_nama_kerusakan);
        textViewDeskripsi = findViewById(R.id.text_view_deskripsi);
        textViewGejalaKerusakan = findViewById(R.id.text_view_gejala_kerusakan);
        textViewSolusi = findViewById(R.id.text_view_solusi);

        Intent result = getIntent();
        int extraKodeKerusakan = result.getIntExtra("EXTRA_KODE_KERUSAKAN", 0);

        SispakDBHelper dbHelper = SispakDBHelper.getInstance(this);
        kerusakan = dbHelper.getKerusakanWhereKode(extraKodeKerusakan);

        Picasso.with(this).load(kerusakan.getImageKerusakan()).resizeDimen(R.dimen.image_picasso_w,R.dimen.image_picasso_h).into(imageViewKerusakan);
        textViewNamaKerusakan.setText(kerusakan.getNamaKerusakan());
        textViewDeskripsi.setText(kerusakan.getDeskripsi());
        textViewGejalaKerusakan.setText(kerusakan.getGejalaKerusakan());
        textViewSolusi.setText(kerusakan.getSolusi());
    }
}
