package aplikasilaundry.config;

//Mengimpor class Connection untuk membuat koneksi ke database
import java.sql.Connection;
//Mengimpor class DriverManager untuk menghubungkan aplikasi dengan database
import java.sql.DriverManager;
//Mengimpor class SQLException untuk menangani kesalahan saat proses database
import java.sql.SQLException;

public class Koneksi {

    //Menyimpan objek koneksi
    private static Connection koneksi;
    //Method untuk mendapatkan koneksi database

    //Method untuk membuat dan mengembalikan koneksi ke database
    public static Connection getKoneksi() {

        try {
            //Membuat koneksi baru jika koneksi belum ada atau sudah tertutup
            if (koneksi == null || koneksi.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/mojosari_laundry1";
                String user = "root";
                String password = "";
                //Menghubungkan aplikasi dengan database MySQL
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi database berhasil.");
            }
        } catch (SQLException e) {

            //Menampilkan pesan jika terjadi kesalahan saat koneksi database
            System.out.println("Koneksi database gagal!");
            System.out.println(e.getMessage());

        }
        //Mengembalikan objek koneksi yang telah dibuat
        return koneksi;
    }
}
