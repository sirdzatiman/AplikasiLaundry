
package aplikasilaundry.util;

//Mengimpor model Pengguna
import aplikasilaundry.model.Pengguna;
//Class untuk menyimpan data pengguna yang sedang login
public class Session {
     //Menyimpan data pengguna yang sedang login
    private static Pengguna penggunaLogin;

    //Method untuk menyimpan data pengguna
    public static void setPengguna(Pengguna pengguna) {

        //Menyimpan data pengguna ke session
        penggunaLogin = pengguna;

    }

    //Method untuk mengambil data pengguna
    public static Pengguna getPengguna() {

        //Mengembalikan data pengguna yang sedang login
        return penggunaLogin;

    }

    //Method untuk menghapus session saat logout
    public static void logout() {

        //Mengosongkan data pengguna
        penggunaLogin = null;

    }
}
