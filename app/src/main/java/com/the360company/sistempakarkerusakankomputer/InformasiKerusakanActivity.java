package com.the360company.sistempakarkerusakankomputer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.the360company.sistempakarkerusakankomputer.models.Kerusakan;
import com.the360company.sistempakarkerusakankomputer.models.SispakDBHelper;

import java.util.ArrayList;

public class InformasiKerusakanActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_kerusakan);

        SispakDBHelper db = SispakDBHelper.getInstance(this);

        ArrayList<Kerusakan> kerusakanArrayList = db.getListKerusakan();

        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        ListInfoKerusakanAdapter adapter = new ListInfoKerusakanAdapter(this, kerusakanArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerView.setAdapter(adapter);


    }
}
