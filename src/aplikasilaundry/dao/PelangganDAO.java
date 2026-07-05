
package aplikasilaundry.dao;

//Mengimpor class koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor model Pelanggan
import aplikasilaundry.model.Pelanggan;

//Mengimpor class JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Mengimpor collection
import java.util.ArrayList;
import java.util.List;

//Class untuk mengelola data pelanggan
public class PelangganDAO {
     //Menyimpan koneksi database
    private Connection conn;

    //Constructor
    public PelangganDAO() {

        //Mengambil koneksi dari class Koneksi
        conn = Koneksi.getKoneksi();

    }
    //Method untuk mengambil seluruh data pelanggan
    public List<Pelanggan> getAll() {

        //Membuat list pelanggan
        List<Pelanggan> list = new ArrayList<>();

        try {

            //Query mengambil seluruh data pelanggan
            String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan ASC";

            //Menyiapkan query
            PreparedStatement ps = conn.prepareStatement(sql);

            //Menjalankan query
            ResultSet rs = ps.executeQuery();

            //Membaca seluruh data
            while (rs.next()) {

                //Membuat objek pelanggan
                Pelanggan pelanggan = new Pelanggan();

                //Mengisi data pelanggan
                pelanggan.setIdPelanggan(rs.getInt("id_pelanggan"));
                pelanggan.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setNoHp(rs.getString("no_hp"));
                pelanggan.setAlamat(rs.getString("alamat"));

                //Menambahkan ke dalam list
                list.add(pelanggan);

            }

        } catch (SQLException e) {

            //Menampilkan pesan error
            System.out.println(e.getMessage());

        }

        //Mengembalikan list pelanggan
        return list;
    }
}
