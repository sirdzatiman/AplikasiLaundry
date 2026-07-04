
package aplikasilaundry.config;

public class Session {
     //Menyimpan ID pengguna yang sedang login
    private static int idPengguna;

    //Menyimpan nama pengguna
    private static String namaPengguna;

    //Menyimpan username
    private static String username;

    //Menyimpan role pengguna (Owner/Kasir)
    private static String role;

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

        //Kosongkan ID
        idPengguna = 0;

        //Kosongkan nama
        namaPengguna = null;

        //Kosongkan username
        username = null;

        //Kosongkan role
        role = null;
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

}
