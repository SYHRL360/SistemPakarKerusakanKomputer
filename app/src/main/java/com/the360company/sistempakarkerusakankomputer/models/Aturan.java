package com.the360company.sistempakarkerusakankomputer.models;

public class Aturan {

    private Gejala gejalaAturan;
    private int aturanKodeKerusakan;
    private int aturanKodeGejala;
    private int level;

    public Aturan() {
    }

    public Aturan(int aturanKodeKerusakan, int aturanKodeGejala, int level) {
        this.aturanKodeKerusakan = aturanKodeKerusakan;
        this.aturanKodeGejala = aturanKodeGejala;
        this.level = level;

    }

    public Gejala getGejalaAturan() {
        return gejalaAturan;
    }

    public void setGejalaAturan(Gejala gejalaAturan) {
        this.gejalaAturan = gejalaAturan;
    }

    public int getAturanKodeGejala() {
        return aturanKodeGejala;
    }

    public void setAturanKodeGejala(int aturanKodeGejala) {
        this.aturanKodeGejala = aturanKodeGejala;
    }

    public int getAturanKodeKerusakan() {
        return aturanKodeKerusakan;
    }

    public void setAturanKodeKerusakan(int aturanKodeKerusakan) {
        this.aturanKodeKerusakan = aturanKodeKerusakan;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
