package com.the360company.sistempakarkerusakankomputer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.squareup.picasso.Picasso;
import com.the360company.sistempakarkerusakankomputer.models.Kerusakan;

public class DialogHasilDiagnosa extends DialogFragment {

    private static final String ARG_KODE_KERUSAKAN = "argKodeKerusakan";
    private static final String ARG_NAMA_KERUSAKAN = "argNamaKerusakan";
    private static final String ARG_IMAGE_KERUSAKAN = "argImageKerusakan";

    private TextView textNamaKerusakan;
    private ImageView imageKerusakan;
    private Button buttonDetail;

    private int kodeKerusakan;
    private String namaKerusakan;
    private int imageResKerusakan;

    private OnFragmentInteraction mListener;

    public static DialogHasilDiagnosa newInstance(Kerusakan kerusakan){
        DialogHasilDiagnosa fragment = new DialogHasilDiagnosa();
        Bundle args = new Bundle();
        args.putInt(ARG_KODE_KERUSAKAN, kerusakan.getKodeKerusakan());
        args.putString(ARG_NAMA_KERUSAKAN, kerusakan.getNamaKerusakan());
        args.putInt(ARG_IMAGE_KERUSAKAN, kerusakan.getImageKerusakan());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            kodeKerusakan = getArguments().getInt(ARG_KODE_KERUSAKAN);
            namaKerusakan = getArguments().getString(ARG_NAMA_KERUSAKAN);
            imageResKerusakan = getArguments().getInt(ARG_IMAGE_KERUSAKAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_hasil_diagnosa, container, false);
        textNamaKerusakan = view.findViewById(R.id.text_dialog_jenis_kerusakan);
        imageKerusakan = view.findViewById(R.id.image_dialog_jenis_kerusakan);
        buttonDetail = view.findViewById(R.id.button_detail_kerusakan);

        textNamaKerusakan.setText(namaKerusakan);

        Picasso.with(getActivity()).load(imageResKerusakan).resizeDimen(R.dimen.image_dialog_w, R.dimen.image_dialog_h).into(imageKerusakan);
        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPress(kodeKerusakan);
            }
        });

        return view;
    }

    public void onButtonPress(int kodeKerusakan){
        if(mListener != null){
            mListener.fragmentInteraction(kodeKerusakan);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteraction){
            mListener = (OnFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteraction{
        void fragmentInteraction(int kodeKerusakan);
    }
}
