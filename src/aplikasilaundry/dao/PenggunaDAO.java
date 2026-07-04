
package aplikasilaundry.dao;
//Mengimpor class koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor model Pengguna
import aplikasilaundry.model.Pengguna;

//Mengimpor class JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Class untuk mengelola data pengguna pada database
public class PenggunaDAO {
     //Menyimpan koneksi database
    private Connection conn;

    //Constructor
    public PenggunaDAO() {

        //Mengambil koneksi dari class Koneksi
        conn = Koneksi.getKoneksi();

    }

    //Method untuk login
    public Pengguna login(String username, String password) {

        //Membuat objek pengguna
        Pengguna pengguna = null;

        try {

            //Query mencari username dan password
            // Query mencari username dan password yang dienkripsi MD5
String sql = "SELECT * FROM pengguna WHERE username=? AND password=MD5(?)";

            //Menyiapkan query
            PreparedStatement ps = conn.prepareStatement(sql);

            //Mengisi parameter username
            ps.setString(1, username);

            //Mengisi parameter password
            ps.setString(2, password);

            //Menjalankan query
            ResultSet rs = ps.executeQuery();

            //Jika data ditemukan
            if (rs.next()) {

                //Membuat objek pengguna
                pengguna = new Pengguna();

                //Mengisi id pengguna
                pengguna.setIdPengguna(rs.getInt("id_pengguna"));

                //Mengisi nama pengguna
                pengguna.setNamaPengguna(rs.getString("nama_pengguna"));

                //Mengisi username
                pengguna.setUsername(rs.getString("username"));

                //Mengisi password
                pengguna.setPassword(rs.getString("password"));

                //Mengisi role
                pengguna.setRole(rs.getString("role"));

            }

        } catch (SQLException e) {

            //Menampilkan error
            System.out.println(e.getMessage());

        }

        //Mengembalikan objek pengguna
        return pengguna;

    }
}
