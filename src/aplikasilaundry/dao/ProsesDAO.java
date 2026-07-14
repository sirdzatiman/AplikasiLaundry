package aplikasilaundry.dao;

//Mengimpor koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor model proses
import aplikasilaundry.model.Proses;

//Mengimpor JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Mengimpor collection
import java.util.ArrayList;
import java.util.List;

//DAO tabel proses
public class ProsesDAO {

    //Menyimpan koneksi
    private Connection conn;

    //Constructor
    public ProsesDAO() {

        //Mengambil koneksi database
        conn = Koneksi.getKoneksi();

    }

    //Method mengambil seluruh proses
    public List<Proses> getAll() {

        //Menyimpan daftar proses
        List<Proses> daftar
                = new ArrayList<>();

        try {

            //Query mengambil seluruh proses
            String sql
                    = "SELECT * FROM proses "
                    + "ORDER BY nama_proses";
            System.out.println("BUTTON SIMPAN PROSES DIKLIK");

            //Menyiapkan query
            PreparedStatement ps
                    = conn.prepareStatement(sql);

            //Menjalankan query
            ResultSet rs
                    = ps.executeQuery();

            //Mengambil seluruh data
            while (rs.next()) {

                //Membuat objek proses
                Proses proses
                        = new Proses();

                //Mengisi ID proses
                proses.setIdProses(
                        rs.getInt("id_proses"));

                //Mengisi nama proses
                proses.setNamaProses(
                        rs.getString("nama_proses"));

                //Menambahkan ke list
                daftar.add(proses);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        //Mengembalikan daftar proses
        return daftar;

    }

    //Method menyimpan proses
    public boolean simpan(Proses proses) {
        System.out.println("MASUK DAO");
        try {

            //Query menyimpan proses
            String sql
                    = "INSERT INTO proses(nama_proses) "
                    + "VALUES(?)";

            //Menyiapkan query
            PreparedStatement ps
                    = conn.prepareStatement(sql);

            System.out.println("SQL SIAP");

            //Mengisi nama proses
            ps.setString(
                    1,
                    proses.getNamaProses());

            System.out.println("PARAMETER = " + proses.getNamaProses());

//Menjalankan query
            int hasil = ps.executeUpdate();

            System.out.println("HASIL INSERT = " + hasil);

            return hasil > 0;
        } catch (Exception e) {

            e.printStackTrace();

            System.out.println("ERROR = " + e.getMessage());

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());

        }

        return false;

    }
//Method mengubah proses

    public boolean update(Proses proses) {

        try {

            //Query mengubah proses
            String sql
                    = "UPDATE proses "
                    + "SET nama_proses=? "
                    + "WHERE id_proses=?";

            //Menyiapkan query
            PreparedStatement ps
                    = conn.prepareStatement(sql);

            //Mengisi nama proses
            ps.setString(
                    1,
                    proses.getNamaProses());

            //Mengisi ID proses
            ps.setInt(
                    2,
                    proses.getIdProses());

            //Menjalankan query
            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }
//Method menghapus proses

    public boolean hapus(int idProses) {

        try {

            //Query menghapus proses
            String sql
                    = "DELETE FROM proses "
                    + "WHERE id_proses=?";

            //Menyiapkan query
            PreparedStatement ps
                    = conn.prepareStatement(sql);

            //Mengisi ID proses
            ps.setInt(
                    1,
                    idProses);

            //Menjalankan query
            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }
}
