package com.the360company.sistempakarkerusakankomputer.models;

public final class PakarContract {

    private PakarContract(){
    }

    public static class GejalaTable {
        public static final String TABLE_NAME = "tabel_gejala";
        public static final String COLUMN_CODE = "kode_gejala";
        public static final String COLUMN_NAME = "nama_gejala";
        public static final String COLUMN_IMAGE_URL = "image_gejala";
    }

    public static class KerusakanTable {
        public static final String TABLE_NAME = "tabel_kerusakan";
        public static final String COLUMN_CODE = "kode_kerusakan";
        public static final String COLUMN_NAME = "nama_kerusakan";
        public static final String COLUMN_DESKRIPSI = "deskripsi";
        public static final String COLUMN_GEJALA_KERUSAKAN = "gejala_kerusakan";
        public static final String COLUMN_SOLUSI = "solusi";
        public static final String COLUMN_IMAGE_URL = "image_kerusakan";
    }

    public static class AturanTabel{
        public static final String TABLE_NAME = "tabel_aturan";
        public static final String COLUMN_KERUSAKAN_CODE = "aturan_kode_kerusakan";
        public static final String COLUMN_GEJALA_CODE = "aturan_kode_gejala";
        public static final String COLUMN_LEVEL = "level";
    }
}
