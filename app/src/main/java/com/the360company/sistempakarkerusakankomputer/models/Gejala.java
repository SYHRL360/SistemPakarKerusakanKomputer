package com.the360company.sistempakarkerusakankomputer.models;

public class Gejala {
    private int kodeGejala;
    private String namaGejala;
    private int imageKerusakan;

    public Gejala() {
    }

    public Gejala(int kodeGejala, String namaGejala, int imageUrl) {
        this.kodeGejala = kodeGejala;
        this.namaGejala = namaGejala;
        this.imageKerusakan = imageUrl;
    }

    public int getKodeGejala() {
        return kodeGejala;
    }

    public void setKodeGejala(int kodeGejala) {
        this.kodeGejala = kodeGejala;
    }

    public String getNamaGejala() {
        return namaGejala;
    }

    public void setNamaGejala(String namaGejala) {
        this.namaGejala = namaGejala;
    }

    public int getImageKerusakan() {
        return imageKerusakan;
    }

    public void setImageKerusakan(int imageKerusakan) {
        this.imageKerusakan = imageKerusakan;
    }
}
