package com.the360company.sistempakarkerusakankomputer.models;

public class Kerusakan {

    private int kodeKerusakan;
    private String namaKerusakan;
    private String deskripsi;
    private String gejalaKerusakan;
    private String solusi;
    private int imageKerusakan;

    public Kerusakan() {
    }

    public Kerusakan(int kodeKerusakan, String namaKerusakan, String deskripsi, String gejalaKerusakan, String solusi, int imageKerusakan) {
        this.kodeKerusakan = kodeKerusakan;
        this.namaKerusakan = namaKerusakan;
        this.deskripsi = deskripsi;
        this.gejalaKerusakan = gejalaKerusakan;
        this.solusi = solusi;
        this.imageKerusakan = imageKerusakan;
    }

    public int getKodeKerusakan() {
        return kodeKerusakan;
    }

    public void setKodeKerusakan(int kodeKerusakan) {
        this.kodeKerusakan = kodeKerusakan;
    }

    public String getNamaKerusakan() {
        return namaKerusakan;
    }

    public void setNamaKerusakan(String namaKerusakan) {
        this.namaKerusakan = namaKerusakan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGejalaKerusakan() {
        return gejalaKerusakan;
    }

    public void setGejalaKerusakan(String gejalaKerusakan) {
        this.gejalaKerusakan = gejalaKerusakan;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public int getImageKerusakan() {
        return imageKerusakan;
    }

    public void setImageKerusakan(int imageKerusakan) {
        this.imageKerusakan = imageKerusakan;
    }
}
