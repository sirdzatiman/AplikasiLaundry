package aplikasilaundry.config;

import java.util.prefs.Preferences;

public class Session {
    //Menyimpan ID pengguna yang sedang login

    private static int idPengguna;

    //Menyimpan nama pengguna
    private static String namaPengguna;

    //Menyimpan username
    private static String username;

    //Menyimpan role pengguna (Owner/Kasir)
    private static String role;

    //Preferences penyimpanan sesi login
    private static final Preferences PREF
            = Preferences.userNodeForPackage(Session.class);

    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ROLE = "role";

    //Mengisi data session setelah login berhasil
    public static void login(int id, String nama, String user, String hakAkses) {

        //Simpan ID pengguna
        idPengguna = id;

        //Simpan nama pengguna
        namaPengguna = nama;

        //Simpan username
        username = user;

        //Simpan role
        role = hakAkses;
    }

    //Menghapus seluruh session ketika logout
    public static void logout() {

        idPengguna = 0;
        namaPengguna = null;
        username = null;
        role = null;

        //Menghapus session yang tersimpan
        hapusSesi();

    }

    //Mengambil ID pengguna
    public static int getIdPengguna() {
        return idPengguna;
    }

    //Mengambil nama pengguna
    public static String getNamaPengguna() {
        return namaPengguna;
    }

    //Mengambil username
    public static String getUsername() {
        return username;
    }

    //Mengambil role pengguna
    public static String getRole() {
        return role;
    }
    //Method menyimpan session ke Preferences

    public static void simpanSesi() {

        PREF.putInt(KEY_ID, idPengguna);
        PREF.put(KEY_NAMA, namaPengguna);
        PREF.put(KEY_USERNAME, username);
        PREF.put(KEY_ROLE, role);

    }
    //Method memuat session dari Preferences

    public static boolean muatSesi() {

        idPengguna = PREF.getInt(KEY_ID, 0);
        namaPengguna = PREF.get(KEY_NAMA, null);
        username = PREF.get(KEY_USERNAME, null);
        role = PREF.get(KEY_ROLE, null);

        return idPengguna != 0;

    }
    //Method menghapus session yang tersimpan

    public static void hapusSesi() {

        PREF.remove(KEY_ID);
        PREF.remove(KEY_NAMA);
        PREF.remove(KEY_USERNAME);
        PREF.remove(KEY_ROLE);

    }

}
