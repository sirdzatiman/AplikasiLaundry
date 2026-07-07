
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
//Mengimpor Statement
import java.sql.Statement;
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
    //Method menyimpan pelanggan ke database
public int simpan(Pelanggan pelanggan){

    //ID pelanggan yang akan dikembalikan
    int idPelanggan = 0;

    try{

        //Query simpan pelanggan
        String sql =
                "INSERT INTO pelanggan "
                + "(nama_pelanggan,no_hp,alamat) "
                + "VALUES (?,?,?)";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS);

        //Nama pelanggan
        ps.setString(1,
                pelanggan.getNamaPelanggan());

        //Nomor HP
        ps.setString(2,
                pelanggan.getNoHp());

        //Alamat
        ps.setString(3,
                pelanggan.getAlamat());

        //Menjalankan query
        ps.executeUpdate();

        //Mengambil ID yang baru dibuat
        ResultSet rs =
                ps.getGeneratedKeys();

        //Jika ada ID
        if(rs.next()){

            //Menyimpan ID pelanggan
            idPelanggan =
                    rs.getInt(1);

        }

    }catch(SQLException e){

        //Menampilkan error
        System.out.println(e.getMessage());

    }

    //Mengembalikan ID pelanggan
    return idPelanggan;

}
}
