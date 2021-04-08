package com.the360company.sistempakarkerusakankomputer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.the360company.sistempakarkerusakankomputer.models.Kerusakan;

import java.util.ArrayList;

public class ListInfoKerusakanAdapter extends RecyclerView.Adapter<ListInfoKerusakanAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Kerusakan> kerusakansList;

    public ListInfoKerusakanAdapter(Context mContext, ArrayList<Kerusakan> kerusakansList) {
        this.mContext = mContext;
        this.kerusakansList = kerusakansList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_kerusakan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        int imageResource = kerusakansList.get(position).getImageKerusakan();
        Picasso.with(mContext)
                .load(imageResource)
                .fit()
                .centerCrop()
                .into(holder.imageViewKerusakan);

        holder.textViewKerusakan.setText(kerusakansList.get(position).getNamaKerusakan());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailKerusakanActivity.class);
                intent.putExtra("EXTRA_KODE_KERUSAKAN", kerusakansList.get(position).getKodeKerusakan());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kerusakansList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentLayout;
        ImageView imageViewKerusakan;
        TextView textViewKerusakan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            imageViewKerusakan = itemView.findViewById(R.id.image_list_kerusakan);
            textViewKerusakan = itemView.findViewById(R.id.text_list_kerusakan);

        }
    }
}
