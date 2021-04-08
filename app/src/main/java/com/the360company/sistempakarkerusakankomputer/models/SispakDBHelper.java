package com.the360company.sistempakarkerusakankomputer.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.the360company.sistempakarkerusakankomputer.R;
import com.the360company.sistempakarkerusakankomputer.models.PakarContract.AturanTabel;
import com.the360company.sistempakarkerusakankomputer.models.PakarContract.GejalaTable;
import com.the360company.sistempakarkerusakankomputer.models.PakarContract.KerusakanTable;

import java.util.ArrayList;

public class SispakDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SistemPakarKomputer.db";
    private static final int DATABASE_VERSION = 2;


    private static final String TEMP_KERUSAKAN = "temp_kerusakan";

    private SQLiteDatabase db;

    private static SispakDBHelper instance;


    private SispakDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized SispakDBHelper getInstance(Context context){
        if(instance == null){
            instance = new SispakDBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_GEJALA_TABLE = "CREATE TABLE " +
                GejalaTable.TABLE_NAME + "( " +
                GejalaTable.COLUMN_CODE + " INTEGER NOT NULL UNIQUE, " +
                GejalaTable.COLUMN_NAME + " TEXT, " +
                GejalaTable.COLUMN_IMAGE_URL + " INTEGER NOT NULL " +
                ")";

        final String SQL_CREATE_KERUSKAN_TABLE = "CREATE TABLE " +
                KerusakanTable.TABLE_NAME + "( " +
                KerusakanTable.COLUMN_CODE + " INTEGER NOT NULL UNIQUE, " +
                KerusakanTable.COLUMN_NAME + " TEXT, " +
                KerusakanTable.COLUMN_DESKRIPSI + " TEXT, " +
                KerusakanTable.COLUMN_GEJALA_KERUSAKAN + " TEXT, " +
                KerusakanTable.COLUMN_SOLUSI + " TEXT, " +
                KerusakanTable.COLUMN_IMAGE_URL + " INTEGER NOT NULL " +
                ")";

       final String SQL_CREATE_ATURAN_TABLE = "CREATE TABLE " +
               AturanTabel.TABLE_NAME + "( " +
               AturanTabel.COLUMN_KERUSAKAN_CODE + " INTEGER, " +
               AturanTabel.COLUMN_GEJALA_CODE + " INTEGER, " +
               AturanTabel.COLUMN_LEVEL + " INTEGER " +
               ")";

       final String SQL_CREATE_TEMP_KERUSAKAN_TABLE = "CREATE TABLE " +
               TEMP_KERUSAKAN + "( " +
               KerusakanTable.COLUMN_CODE + " INTEGER NOT NULL " +
               ")";

        db.execSQL(SQL_CREATE_GEJALA_TABLE);
        db.execSQL(SQL_CREATE_KERUSKAN_TABLE);
        db.execSQL(SQL_CREATE_ATURAN_TABLE);
        db.execSQL(SQL_CREATE_TEMP_KERUSAKAN_TABLE);

        fillGejalaTable();
        fillKerusakanTable();
        fillAturanTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GejalaTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + KerusakanTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AturanTabel.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TEMP_KERUSAKAN);
        onCreate(db);
    }

    private void fillGejalaTable(){
        insertGejala(new Gejala(1, "Suhu prosesor cepat panas", R.drawable.suhu_prosesor_panas));
        insertGejala(new Gejala(2, "Komputer sering restart sendiri", R.drawable.restart_sendiri));
        insertGejala(new Gejala(3, "Komputer menyala, tapi tidak tampil dimonitor", R.drawable.tidak_tampil_dimonitor));
        insertGejala(new Gejala(4, "Muncul pesan error pada BIOS", R.drawable.error_bios));
        insertGejala(new Gejala(5, "Gagal menjalankan software atau aplikasi", R.drawable.gagal_menjalankan_app));

        insertGejala(new Gejala(6, "Tampilan layar komputer stuck di boot screen", R.drawable.stuck_di_boot_screen));
        insertGejala(new Gejala(7, "Komputer sulit untuk dihidupkan", R.drawable.komputer_sulit_dihidupkan));
        insertGejala(new Gejala(8, "Komputer mati total", R.drawable.komputer_mati_total));
        insertGejala(new Gejala(9, "Lampu indikator pada monitor berkedip-kedip", R.drawable.indikator_monitor_berkedip2));

        insertGejala(new Gejala(10, "Komputer terasa lambat atau berat", R.drawable.komputer_terasa_lambat));
        insertGejala(new Gejala(11, "Terjadi blue screen pada monitor", R.drawable.terjadi_bluescreen));
        insertGejala(new Gejala(12, "Muncul bunyi beep beberapa kali", R.drawable.bunyi_beep));

        insertGejala(new Gejala(13, "Terdengar suara berisik pada bagian Harddisk", R.drawable.berisik_pada_hardisk));
        insertGejala(new Gejala(14, "Data file mengalami kerusakan atau hilang", R.drawable.file_rusak));
        insertGejala(new Gejala(15, "Selalu muncul scandisk saat booting", R.drawable.scandisk_saat_booting));

        insertGejala(new Gejala(16, "Gambar tidak lancar saat menjalankan game atau animasi", R.drawable.game_atau_animasi));
        insertGejala(new Gejala(17, "Tampilan layar terlihat putih", R.drawable.layar_putih));
        insertGejala(new Gejala(18, "Gambar terlihat pecah-pecah", R.drawable.gambar_pecah2));

        insertGejala(new Gejala(19, "Komputer tidak mengeluarkan suara apapun", R.drawable.tidak_keluar_suara));
        insertGejala(new Gejala(20, "Suara yang dihasilkan tidak jelas", R.drawable.suara_tidak_jelas));
        insertGejala(new Gejala(21, "Driver sound card tidak dikenali komputer", R.drawable.driver_soundcard_tidak_dikenali));

        insertGejala(new Gejala(22, "Komputer sering mati secara tiba-tiba", R.drawable.komputer_mati_tiba2));
        insertGejala(new Gejala(23, "Kipas power supply tidak berputar", R.drawable.kipas_power_supply));
        insertGejala(new Gejala(24, "Hanya sebagian perangkat yang bekerja", R.drawable.hanya_sebagian_perangkat_yang_bekerja));

        insertGejala(new Gejala(25, "Muncul garis vertikal/horizontal ditengah monitor", R.drawable.muncul_garis_vertikal_atau_horizontal));
        insertGejala(new Gejala(26, "Tampilan layar monitor jadi gelap", R.drawable.monitor_gelap));
        insertGejala(new Gejala(27, "Tampilan warna tidak lengkap", R.drawable.tampilan_warna_tidak_lengkap));
        insertGejala(new Gejala(28, "Tampak blok hitam dan gambar tidak simetris", R.drawable.tampak_blok_hitam_tidak_simetris));

        insertGejala(new Gejala(29, "Tombol keyboard tidak berfungsi sama sekali", R.drawable.keyboard_tidak_berfungsi));
        insertGejala(new Gejala(30, "Keyboard tidak terdeteksi", R.drawable.keyboard_tidak_terdeteksi));
        insertGejala(new Gejala(31, "Sebagian tombol karakter tidak muncul dilayar", R.drawable.sebagian_tombol_tidak_muncul));

        insertGejala(new Gejala(32, "Tombol klik pada mouse tidak berfungsi", R.drawable.tombol_click_tidak_berfungsi));
        insertGejala(new Gejala(33, "Pointer pada mouse tidak bergerak sama sekali", R.drawable.pointer_tidak_bergerak));
        insertGejala(new Gejala(34, "Tidak bisa melakukan double-click", R.drawable.tidak_bisa_doubleclick));

        insertGejala(new Gejala(35, "CD/DVD ROM macet dan tidak bisa dibuka", R.drawable.cdrom_tidak_bisa_dibuka));
        insertGejala(new Gejala(36, "CD/DVD ROM tidak bisa digunakan untuk burning", R.drawable.tidak_bisa_untuk_burning));
        insertGejala(new Gejala(37, "Tidak bisa membaca dan menulis CD/DVD", R.drawable.tidak_bisa_membaca_dan_menulis_csrom));

        insertGejala(new Gejala(38, "Tampilan layar blank hitam setelah masuk OS/Windows", R.drawable.blank_hitam_setelah_masuk_windows));
        insertGejala(new Gejala(39, "Windows explorer tidak bisa dijalankan", R.drawable.windows_exploler_tidak_bekerja));
        insertGejala(new Gejala(40, "Tidak bisa melakukan shutdown", R.drawable.tidak_bisa_shutdown));
    }


    private void insertGejala(Gejala gejala){
        ContentValues cv = new ContentValues();
        cv.put(GejalaTable.COLUMN_CODE, gejala.getKodeGejala());
        cv.put(GejalaTable.COLUMN_NAME, gejala.getNamaGejala());
        cv.put(GejalaTable.COLUMN_IMAGE_URL, gejala.getImageKerusakan());
        db.insert(GejalaTable.TABLE_NAME, null, cv);
    }

    private void fillKerusakanTable(){

       Kerusakan k1 = new Kerusakan(
               1,
               "Kerusakan Prosesor",
               "Kerusakan pada prosesor bisa disebabkan karena prosesor mengalami panas yang berlebihan atau sering juga disebut overheating, sehingga dapat menyebabkan IC nya terbakar dan kemudian prosesor tidak dapat digunakan lagi. Kerusakan ini juga bisa terjadi karena banyak debu yang menempel pada kipas prosesor yang menyebabkan kinerja dari kipas pendingin prosesor terganggu",
               "1. Suhu prosesor cepat panas. \n2. Komputer sering restart sendiri. \n3. Komputer menyala, tapi tidak tampil di monitor. \n4. Muncul pesan error pada BIOS. \n5. Gagal menjalankan software atau aplikasi.",
               "- Bersihkan kipas prosesor dari debu-debu yang menempel agar kinerja kipas prosesor kembali normal \n\n- Gunakan kipas tambahan untuk membantu menurunkan suhu prosesor \n\n- Berikan Thermal Paste pada permukaan prosesor, karena Thermal Paste berguna menyerap suhu panas yang dihasilkan prosesor \n\n- Jangan paksakan komputer untuk menjalankan aplikasi yang cukup berat \n\n- Atur sirkulasi udara dalam ruangan agar tetap sejuk karena secara tidak langsung mempengaruhi suhu dari prosesor dan komputer.",
               R.drawable.processor);
       insertKerusakan(k1);

       Kerusakan k2 = new Kerusakan(
               2,
               "Kerusakan Motherboard",
               "Kerusakan motherboard bisa terjadi karena terjadi kerusakan chipset yang menyebabkan pembacaan perangkat keras yang terhubung menjadi lambat, bahkan terjadi kegagalan dalam hubungan (interkoneksi) antar komponen dalam motherboard dan akibatnya komputer stuck pada tampilan tertentu. Kerusakan motherboard juga bisa terjadi karena adanya tegangan listrik dan juga suplai listrik yang tidak stabil yang bisa menyebabkan motherboard terbakar.",
               "1. Tampilan layar komputer stuck di boot screen. \n2. Komputer sulit untuk dihidupkan. \n3. Komputer mati total. \n4. Lampu indikator pada monitor berkedip-kedip.",
               "- Jauhkan kompenen motherboard dari lokasi berdebu yang bisa menghambat proses pendinginan \n\n- Jangan menjatuhkan motherboard dengan keras \n\n- Selalu periksa apakah setiap komponen sudah tersambung dengan baik pada socketnya \n\n- Gunakan volt stabilizer untuk menstabilkan daya listrik dan juga tegangan listrik agar tidak kurang atau kelebihan.",
               R.drawable.motherboard);
       insertKerusakan(k2);

       Kerusakan k3 = new Kerusakan(
               3,
               "Kerusakan Memori (RAM)",
               "Kerusakan memori RAM bisa disebabkan karena pemakaian yang berlebihan, seperti RAM dengan kapasitas hanya 1 GB dipaksa untuk menjalankan aplikasi yang membutuhkan kapasitas RAM yang besar. Ini bisa menyebabkan RAM dapat mengalami malfungsi dan mengalami kerusakan. Hal lainnya yang dapat menyebabkan kerusakan memori RAM adalah slot RAM tertutup debu atau lembab karena air.",
               "1. Komputer terasa lambat atau berat. \n2. Komputer sering restart sendiri. \n3. Terjadi blue screen pada monitor. \n4. Gagal menjalankan software atau aplikasi. \n5. Muncul bunyi beep beberapa kali.",
               "- Upgrade kapasitas RAM sehingga tidak membebani kerja RAM terlalu berat \n\n- Bersihkan slot RAM dari debu-debu yang menempel sampai bersih \n\n- Bersihkan bagian pin RAM menggunakan penghapus karet dengan cara mengosok satu arah pada bagian tembaga/kuningan pada RAM \n\n- Membeli RAM dalam kondisi baru dari merek yang sudah terpercaya.",
               R.drawable.memori_ram);
       insertKerusakan(k3);

       Kerusakan k4 = new Kerusakan(
               4,
               "Kerusakan Hardisk",
               "Kerusakan pada hardisk bisa terjadi karena tidak sengaja terjatuh atau terbentur karena hardisk merupakan komponen hardware yang berisikan disk/cakram. Benda tersebut memiliki sifat sensitif jika terjatuh atau terbentur karena berpotensi mengalami kerusakan. Instalasi software atau sistem operasi yang berlebihan juga berpotensi mendatangkan kerusakan hardisk. Karena hardisk akan bekerja ekstra bahkan cakram akan berputar cepat saat melakukan instalasi secara berlebihan.",
               "1. Terdengar suara berisik pada bagian Harddisk. \n2. Data file mengalami kerusakan atau hilang. \n3. Terjadi blue screen pada monitor \n4. Selalu muncul scandisk saat booting.",
               "- Melakukan Disk Defragment untuk mengatur seluruh file dan data pada hardisk menjadi berurutan, mengurangi kinerja perputaran disk/cakram didalam hardisk agar lebih awet \n\n- Menggunakan software bernama Software Recovery yang bisa mengembalikan seluruh data pada hardisk yang sebelumnya hilang, terhapus atau terformat \n\n- Lakukan pengecekan ulang pada posisi hardisk. Lalu betulkan hardisk pada posisi yang benar. \n\n- Ganti dengan hardisk yang baru apabila hardisk sudah cukup lama digunakan.",
               R.drawable.harddisk);
       insertKerusakan(k4);

       Kerusakan k5 = new Kerusakan(
               5,
               "Kerusakan VGA",
               "Kerusakan VGA dapat terjadi karena overheating, suhu yang ada pada VGA sebenarnya merupakan respon dari jenis program apa yang dijalankan komputer. Temperatur yang dihasilkan VGA cukup normal ketika bekerja dengan program 2D. Tapi ketika menjalankan program 3D yang memerlukan tampilan visual yang cukup tunggi, ini dapat membuat VGA menghasilkan temperatur yang cukup tinggi yang bisa menyebabkan overheating. pengaturan driver untuk monitor yang tidak tepat juga bisa menyebabkan kerusakan pada VGA.",
               "1. Komputer menyala, tapi tidak tampil di monitor. \n2. Gambar tidak lancar saat menjalankan game atau animasi. \n3. Tampilan layar terlihat putih. \n4. Gambar terlihat pecah-pecah.",
               "- Jangan memaksakan komputer untuk menjalankan program 3D yang memerlukan tampilan visual yang cukup tinggi. \n\n- Melakukan penurunan clockspeed agar VGA selalu berjalan dibawah performa normal \n\n- Install ulang driver VGA \n\n- Bersihkan papan VGA dari debu yang menempel dengan kuas pembersih \n\n- Clear CMOS dengan cara pindah jumper keposisi off lalu lepas baterai CMOS, tunggu 5 menit lalu pasang kembali baterai CMOS",
               R.drawable.vga_card);
       insertKerusakan(k5);

       Kerusakan k6 = new Kerusakan(
               6,
               "Kerusakan Sound Card",
               "Kerusakan Sound Card penyebab yang paling umum adalah driver sound card tidak diinstall dengan benar atau crash dengan sound card yang lama saat mencoba menginstall ulang driver sound card. Penyebab yang lain bisa karena usia komponen sound card yang sudah tua dan harus diganti.",
               "1. Komputer tidak mengeluarkan suara apapun. \n2. Suara yang dihasilkan tidak jelas. \n3. Driver sound card tidak dikenali oleh komputer.",
               "- Cek kondisi default speaker dengan membuka pengaturan Control Panel > Hardware and Sound > Sound > klik kanan speaker yang dipakai > lalu klik Set as default device untuk mengaktifkan speaker \n\n- Pastikan speaker tidak terdisable dengan membuka pengaturan Control Panel > Hardware and Sound > Sound > klik kanan pada speaker yang dipakai > kemudian klik Show Disable Device > lalu Enable Speaker yang terdisable. \n\n- Install ulang driver sound card audio pada komputer. \n\n- Melakukan pengecekan apakah sound on board perlu dimatikan atau tidak saat menginstall ulang sound card lewat pengaturan BIOS. \n\n- Menganti sound card komputer dengan yang baru.",
               R.drawable.sound_card);
       insertKerusakan(k6);

       Kerusakan k7 = new Kerusakan(
               7,
               "Kerusakan Power Supply",
               "Kerusakan Power Supply bisa disebabkan karena daya power supply sudah tidak kuat untuk memberikan suplai sehingga komputer sering mati sendiri saat digunakan. Penyebab lain adalah kompoenen elektronika power supply ada yang rusak. Bisa juga disebabkan karena sekring (fuse) meledak atau kabel yang longar.",
               "1. Komputer mati total. \n2. Komputer sering mati secara tiba-tiba. \n3. Kipas power supply tidak berputar. \n4. Hanya sebagian perangkat yang bekerja.",
               "- Mengecek apakah sekring (fuse) dalam keadaan baik atau tidak \n\n- Cek posisi kabel power supply yang menuju ke CPU apakah ada yang salah atau tidak \n\n- Cek pada fisik komponen-komponen mungkin saja ditemukan ada yang terbakar \n\n- Ganti komponen transistor atau kapasitor yang rusak dengan yang baru \n\n- Menganti power supply dengan yang baru.",
               R.drawable.power_supply);
       insertKerusakan(k7);

       Kerusakan k8 = new Kerusakan(
               8,
               "Kerusakan Monitor",
               "Kerusakan Monitor biasanya disebabkan oleh kerusakan pada bagian panel screen LCD atau bisa juga terjadi karena permasalahan pada kabel LCD, RAM, VGA maupun hardware komputer lainnya yang dapat mempengaruhi LCD monitor. Penggunaan monitor yang sudah bertahun-tahun juga dapat menyebabkan tampilan monitor menjadi gelap. Penyebab lainnya bisa karena pengaturan RGB pada monitor yang berubah.",
               "1. Muncul garis vertikal/horizontal ditengah monitor. \n2. Tampilan layar monitor mejadi gelap. \n3. Tampilan warna tidak lengkap. \n4. Tampak blok hitam dan gambar tidak simetris. \n5. Komputer menyala tapi tidak tampil di monitor.",
               "- Gunakan aplikasi PixelRepair yang berfungsi untuk mengecek keadaan LCD dengan beberapa cara antara lain garis dan warna untuk mengetahui adanya dead pixel. \n\n- Mengecek kabel konektor LCD monitor yang bermasalah lalu menganti kabel konektor yang bermasalah tersebut. \n\n- Sesuaikan pengaturan RGB melalui tombol menu yang terdapat pada monitor komputer. \n\n- Menganti layar monitor dengan yang baru.",
               R.drawable.monitor);
       insertKerusakan(k8);

       Kerusakan k9 = new Kerusakan(
               9,
               "Kerusakan Keyboard",
               "Kerusakan Keyboard terjadi karena sering digunakan untuk mengetik dengan keras yang menyebabkan sebagian tombol tidak berfungsi. Selain itu bisa juga karena ada debu atau kotoran yang menumpuk hingga menghambat respon pada keyboard. Kerusakan pada keyboard bisa juga karena driver keyboard yang tidak diupdate atau ada aplikasi yang menyebabkan keyboard menjadi kurang responsif.",
               "1. Tombol keyboard tidak berfungsi sama sekali. \n2. Keyboard tidak terdeteksi. \n3. Sebagian tombol karakter tidak muncul di layar.",
               "- Membersihkan keyboard dari debu-debu yang menghambat tombol pada keyboard \n\n- Periksa driver keyboard dengan pengaturan Control Panel > System and Security > pilih Device Manager > pilih Keyboard dan pilih menu driver lalu update driver keyboard. \n\n- Cek konektor port pada keyboard dengan melepaskannya lalu memasangkanya kembali dengan benar. \n\n- Membuka tombol keyboard yang bermasalah membersihkannya lalu memasangkanya kembali. \n\n- Menganti keyboard dengan yang baru.",
               R.drawable.keyboard);
       insertKerusakan(k9);

       Kerusakan k10 = new Kerusakan(
               10,
               "Kerusakan Mouse",
               "Kerusakan Mouse ada dua jenis yaitu kerusakan mouse wired (dengan kabel) dan mouse wireless (tanpa kabel) keduanya memiliki perbedaan. Penyebab kerusakan mouse wired bisa karena ada kabel mouse yang putus sedangkan pada mouse wireless bisa karena receiver bluetooth mengalami kerusakan atau baterai pada mouse sudah habis. Kerusakan mouse biasanya juga terjadi karena penggunaan tombol mouse yang berlebihan atau memang karena sudah cukup lama digunakan atau bisa juga terjadi karena mouse tidak sengaja terjatuh dan terbanting sehingga mouse jadi tidak berfungsi.",
               "1. Tombol klik pada mouse tidak berfungsi. \n2. Pointer pada mouse tidak bergerak sama sekali. \n3. Tidak bisa melakukan double-click.",
               "- Memeriksa dan memperbaiki kabel mouse apakah ada yang longar atau putus apabila menggunakan mouse wired sedangkan untuk mouse wireless coba untuk menganti baterai mouse dengan yang baru. \n\n- Mencabut port usb mouse yang kursornya tidak bergerak kemudian mencolokannya lagi ke posisi yang benar. \n\n- Cek driver mouse yang digunakan dengan cara klik Start > ketik Device Manager > klik pada menu Mice and Other Pointing Device > klik kanan lalu update Driver mouse yang digunakan > lalu restart komputer. \n\n- Membongkar mouse lalu memperbaiki kompenen yang ada didalam mouse seperti optical mouse, tombol atau scroll pada mouse. \n\n- Menganti mouse dengan yang masih baru.",
               R.drawable.mouse);
       insertKerusakan(k10);

       Kerusakan k11 = new Kerusakan(
               11,
               "Kerusakan CD/DVD ROM",
               "Kerusakan CD/DVD ROM terjadi karena rel tray sudah aus karena CD/DVD ROM sudah lama atau tidak sengaja tray nya tersenggol pada saat kondisi tray sedang terbuka. Bisa juga karena driver yang digunaan tidak sesuai. Head yang kotor juga bisa menyebabkan CD/DVD ROM tidak bisa membaca karena sinar laser yang dihasilkan head terhalang oleh debu yang mengakibatkan pembacaan CD/DVD jadi tidak lancar.",
               "1. CD/DVD ROM macet dan tidak bisa dibuka. \n2. CD/DVD ROM tidak bisa digunakan untuk burning. \n3. Tidak bisa membaca dan menulis CD/DVD.",
               "- Gunakan kawat atau jarum lalu masukan ke dalam lubang kecil yang ada di CD/DVD ROM, kemudian coba gerakan sampai mengenai kunci agar CD/DVD ROM dapat terbuka. \n\n- Mengecek driver CD/DVD ROM yang digunakan dengan klik Start > ketik Device Manager > klik pada menu DVD/CD ROM driver > klik kanan lalu update DRiver yang digunakan > lalu restart komputer. \n\n- Bersihkan dengan mengunakan kompresor udara ke dalam CD/DVD ROM maka otomatis debu dan kotoran akan keluar semua. \n\n- Menganti CD/DVD ROM atau membeli CD/DVD ROM eksternal sebagai gantinya.",
               R.drawable.cd_dvd_rom);
       insertKerusakan(k11);

       Kerusakan k12 = new Kerusakan(
               12,
               "Kerusakan OS/Windows",
               "Kerusakan pada OS/Windows biasanya terjadi karena kesalahan atau terjadi error pada saat proses instalasi atau update sistem operasi yang belum sempurna. Kerusakan bisa juga terjadi karena komputer terkena virus, spyware atau trojan yang menyebabkan muncul beberapa file dengan nama aneh dan banyak aplikasi yang tidak bisa dibuka. Penyebab lain mungkin karena ada file sistem pada komputer yang rusak atau korup atau tidak cocok dengan file lain. Bisa juga karena ada masalah pada file registry fast shutdown.",
               "1. Tampilan layar blank hitam setelah masuk OS/Windows. \n2. Windows explorer tidak bisa dijalankan. \n3. Tidak bisa melakukan shutdown.",
               "- Jalankan windows explorer secara manual, tekan tombol CTRL + ALT + Delete, lalu pilih Task Manager masuk ke tab Process klik kanan pada Windows Explorer selanjutnya pilih Restart. \n\n- Masuk ke safe mode kemudian melakukan perbaikan disana. Seperti melakukan uninstall aplikasi penyebab masalah, mengatasi update yang tidak sempurna atau memperbaiki masalah driver yang rusak. \n\n- Buka Command Prompt as administrator > Jalankan system file checker dengan mengetik sfc/scannow > tunggu hingga proses selesai > selanjutnya restart komputer. \n\n- Menginstall antivirus lalu memulai proses scanning pada komputer untuk membersihkan virus yang ada. Setelah selesai restart komputer untuk memastika proses pembersih komputer benar-benar selesai. \n\n- Perbaiki file registry dengan menggunakan aplikasi CCleaner > pilih Registry > lalu klik Scan for Issue > setelah proses selesai pilih Fix Selected Issue > Jika ada pesan Do you want to backup the registry before making change? Pilih Yes > tunggu proses selesai. \n\n- Install ulang sistem operasi windows.",
               R.drawable.os_windows);
       insertKerusakan(k12);

    }

    private void insertKerusakan(Kerusakan kerusakan){
        ContentValues cv = new ContentValues();
        cv.put(KerusakanTable.COLUMN_CODE, kerusakan.getKodeKerusakan());
        cv.put(KerusakanTable.COLUMN_NAME, kerusakan.getNamaKerusakan());
        cv.put(KerusakanTable.COLUMN_DESKRIPSI, kerusakan.getDeskripsi());
        cv.put(KerusakanTable.COLUMN_GEJALA_KERUSAKAN, kerusakan.getGejalaKerusakan());
        cv.put(KerusakanTable.COLUMN_SOLUSI, kerusakan.getSolusi());
        cv.put(KerusakanTable.COLUMN_IMAGE_URL, kerusakan.getImageKerusakan());
        db.insert(KerusakanTable.TABLE_NAME, null, cv);
    }

    private void fillAturanTable(){
        insertAturan(new Aturan(1,1,1));
        insertAturan(new Aturan(1,2,2));
        insertAturan(new Aturan(1,3,3));
        insertAturan(new Aturan(1,4,4));
        insertAturan(new Aturan(1,5,5));

        insertAturan(new Aturan(2,6,1));
        insertAturan(new Aturan(2,7,2));
        insertAturan(new Aturan(2,8,3));
        insertAturan(new Aturan(2,9,4));

        insertAturan(new Aturan(3,10,1));
        insertAturan(new Aturan(3,12,2));   // Swap
        insertAturan(new Aturan(3,11,3));
        insertAturan(new Aturan(3,5,4));
        insertAturan(new Aturan(3, 2,5));   // Swap

        insertAturan(new Aturan(4,13,1));
        insertAturan(new Aturan(4,14,2));
        insertAturan(new Aturan(4,11,3));
        insertAturan(new Aturan(4,15,4));

        insertAturan(new Aturan(5,3,1));
        insertAturan(new Aturan(5,16,2));
        insertAturan(new Aturan(5,17,3));
        insertAturan(new Aturan(5, 18,4));

        insertAturan(new Aturan(6,19,1));
        insertAturan(new Aturan(6, 20,2));
        insertAturan(new Aturan(6,21,3));

        insertAturan(new Aturan(7,8,1));
        insertAturan(new Aturan(7,22,2));
        insertAturan(new Aturan(7,23,3));
        insertAturan(new Aturan(7,24,4));

        insertAturan(new Aturan(8,25,1));
        insertAturan(new Aturan(8,26,2));
        insertAturan(new Aturan(8,27,3));
        insertAturan(new Aturan(8,28,4));
        insertAturan(new Aturan(8,3,5));

        insertAturan(new Aturan(9,29,1));
        insertAturan(new Aturan(9,30,2));
        insertAturan(new Aturan(9,31,3));

        insertAturan(new Aturan(10,32,1));
        insertAturan(new Aturan(10,33,2));
        insertAturan(new Aturan(10,34,3));

        insertAturan(new Aturan(11,35,1));
        insertAturan(new Aturan(11,36,2));
        insertAturan(new Aturan(11,37,3));

        insertAturan(new Aturan(12,38,1));
        insertAturan(new Aturan(12,39,2));
        insertAturan(new Aturan(12,40,3));

    }

    private void insertAturan(Aturan aturan){
        ContentValues cv = new ContentValues();
        cv.put(AturanTabel.COLUMN_KERUSAKAN_CODE, aturan.getAturanKodeKerusakan());
        cv.put(AturanTabel.COLUMN_GEJALA_CODE, aturan.getAturanKodeGejala());
        cv.put(AturanTabel.COLUMN_LEVEL, aturan.getLevel());
        db.insert(AturanTabel.TABLE_NAME, null, cv);
    }


    public Gejala getGejalaWhereKode(int kodeGejala){
        Gejala gejala = new Gejala();
        db = getReadableDatabase();

        String[] selectionArgs = {String.valueOf(kodeGejala)};
        Cursor c = db.rawQuery("SELECT * FROM " + GejalaTable.TABLE_NAME +
                                " WHERE " + GejalaTable.COLUMN_CODE + " = ?",
                                selectionArgs);
        if(c.moveToFirst()){
            gejala.setKodeGejala(c.getInt(c.getColumnIndex(GejalaTable.COLUMN_CODE)));
            gejala.setNamaGejala(c.getString(c.getColumnIndex(GejalaTable.COLUMN_NAME)));
            gejala.setImageKerusakan(c.getInt(c.getColumnIndex(GejalaTable.COLUMN_IMAGE_URL)));
        }

        c.close();
        return gejala;
    }

    public Kerusakan getKerusakanWhereKode(int kodeKerusakan){
        Kerusakan kerusakan = new Kerusakan();
        db = getReadableDatabase();

        String[] selectionArgs = {String.valueOf(kodeKerusakan)};
        Cursor c = db.rawQuery("SELECT * FROM " + KerusakanTable.TABLE_NAME +
                                    " WHERE " + KerusakanTable.COLUMN_CODE +
                                    " = ?", selectionArgs);

        if(c.moveToFirst()){
            kerusakan.setKodeKerusakan(c.getInt(c.getColumnIndex(KerusakanTable.COLUMN_CODE)));
            kerusakan.setNamaKerusakan(c.getString(c.getColumnIndex(KerusakanTable.COLUMN_NAME)));
            kerusakan.setDeskripsi(c.getString(c.getColumnIndex(KerusakanTable.COLUMN_DESKRIPSI)));
            kerusakan.setGejalaKerusakan(c.getString(c.getColumnIndex(KerusakanTable.COLUMN_GEJALA_KERUSAKAN)));
            kerusakan.setSolusi(c.getString(c.getColumnIndex(KerusakanTable.COLUMN_SOLUSI)));
            kerusakan.setImageKerusakan(c.getInt(c.getColumnIndex(KerusakanTable.COLUMN_IMAGE_URL)));
        }

        c.close();
        return kerusakan;
    }

    public ArrayList<Kerusakan> getListKerusakan(){
        ArrayList<Kerusakan> kerusakanList = new ArrayList<>();
        db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + KerusakanTable.TABLE_NAME, null);
        String[] columns = {KerusakanTable.COLUMN_CODE, KerusakanTable.COLUMN_NAME, KerusakanTable.COLUMN_IMAGE_URL};

        Cursor c = db.query(
                KerusakanTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                KerusakanTable.COLUMN_CODE + " ASC"
        );

        if(c.moveToFirst()){
            do{
                Kerusakan kerusakan = new Kerusakan();
                kerusakan.setKodeKerusakan(c.getInt(c.getColumnIndex(KerusakanTable.COLUMN_CODE)));
                kerusakan.setNamaKerusakan(c.getString(c.getColumnIndex(KerusakanTable.COLUMN_NAME)));
                kerusakan.setImageKerusakan(c.getInt(c.getColumnIndex(KerusakanTable.COLUMN_IMAGE_URL)));
                kerusakanList.add(kerusakan);
            }while (c.moveToNext());
        }

        c.close();
        return kerusakanList;
    }

    public ArrayList<Aturan> getAturanWhereLevel(int level){
        ArrayList<Aturan> aturanList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = AturanTabel.COLUMN_LEVEL + " = ?";
        String[] selectionArgs = {String.valueOf(level)};

        Cursor c = db.query(
                AturanTabel.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                AturanTabel.COLUMN_GEJALA_CODE + " ASC"
        );

        if(c.moveToFirst()){
            do {
                Aturan aturan = new Aturan();
                aturan.setAturanKodeGejala(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_GEJALA_CODE)));
                aturan.setAturanKodeKerusakan(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_KERUSAKAN_CODE)));
                aturan.setLevel(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_LEVEL)));
                aturan.setGejalaAturan(this.getGejalaWhereKode(aturan.getAturanKodeGejala()));
                aturanList.add(aturan);
            }while (c.moveToNext());
        }

        c.close();
        return aturanList;
    }

    // "SELECT * FROM rule WHERE level = " + n + " AND id_penyakit
    // IN (" + "SELECT id_penyakit FROM identifikasi_tmp" + ") GROUP BY id_gejala ORDER BY id_gejala ASC;";

    public ArrayList<Aturan> newAturan(int level){
        ArrayList<Aturan> aturanList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = AturanTabel.COLUMN_LEVEL + " = ?" +
                " AND " + AturanTabel.COLUMN_KERUSAKAN_CODE + " IN (" +
                "SELECT " + KerusakanTable.COLUMN_CODE + " FROM " + TEMP_KERUSAKAN + ")";
        String[] selectionArgs = new String[]{String.valueOf(level)};

        Cursor c = db.query(
                AturanTabel.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                AturanTabel.COLUMN_GEJALA_CODE,
                null,
                AturanTabel.COLUMN_GEJALA_CODE + " ASC"
        );

        if(c.moveToFirst()){
            do{
                Aturan aturan = new Aturan();
                aturan.setAturanKodeGejala(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_GEJALA_CODE)));
                aturan.setAturanKodeKerusakan(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_KERUSAKAN_CODE)));
                aturan.setLevel(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_LEVEL)));
                aturan.setGejalaAturan(this.getGejalaWhereKode(aturan.getAturanKodeGejala()));
                aturanList.add(aturan);
            }while (c.moveToNext());
        }

        c.close();
        return aturanList;
    }

    public void setCandidate(int kodeGejala, int level){
        String statement;
        if(level == 1){
            db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TEMP_KERUSAKAN);

            statement = "INSERT INTO " + TEMP_KERUSAKAN + "(" + KerusakanTable.COLUMN_CODE + ") " +
                        "SELECT " + AturanTabel.COLUMN_KERUSAKAN_CODE + " FROM " + AturanTabel.TABLE_NAME + " WHERE " +
                        AturanTabel.COLUMN_GEJALA_CODE + " = " + kodeGejala + " AND " +
                        AturanTabel.COLUMN_LEVEL + " = " + level;

            db = getWritableDatabase();
            db.execSQL(statement);
        } else {

            statement = "SELECT " + AturanTabel.COLUMN_KERUSAKAN_CODE + " FROM " + AturanTabel.TABLE_NAME + " WHERE " +
                    AturanTabel.COLUMN_GEJALA_CODE + " = " + kodeGejala + " AND " +
                    AturanTabel.COLUMN_LEVEL + " = " + level;
            statement = "DELETE FROM " + TEMP_KERUSAKAN + " WHERE " +
                    KerusakanTable.COLUMN_CODE + " NOT IN " +
                    "(" + statement + ")";

            db = getWritableDatabase();
            db.execSQL(statement);
        }
    }



}
