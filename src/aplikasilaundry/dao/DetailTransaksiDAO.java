package aplikasilaundry.dao;

//Mengimpor class koneksi database
import aplikasilaundry.config.Koneksi;
//Mengimpor model DetailTransaksi
import aplikasilaundry.model.DetailTransaksi;
//Mengimpor Connection untuk koneksi database
import java.sql.Connection;
//Mengimpor PreparedStatement untuk menjalankan query SQL
import java.sql.PreparedStatement;
//Mengimpor ResultSet untuk membaca hasil query
import java.sql.ResultSet;
//Mengimpor SQLException untuk menangani kesalahan database
import java.sql.SQLException;
//Mengimpor List dan ArrayList untuk menampung data detail transaksi
import java.util.List;
import java.util.ArrayList;

//Class untuk mengelola data detail transaksi pada database
public class DetailTransaksiDAO {
    //Objek koneksi database
    private Connection conn;
    //Constructor untuk menginisialisasi koneksi database
    public DetailTransaksiDAO() {
        conn = Koneksi.getKoneksi();
    }

    //Menyimpan data detail transaksi ke database
    public void simpan(DetailTransaksi detail) {
        try {
            //Query untuk menyimpan detail transaksi
            String sql
                    = "INSERT INTO detail_transaksi "
                    + "(id_transaksi,id_layanan,qty,subtotal) "
                    + "VALUES (?,?,?,?)";
            //Menyiapkan query SQL
            PreparedStatement ps
                    = conn.prepareStatement(sql);
            //Mengisi parameter query
            ps.setInt(1, detail.getIdTransaksi());
            ps.setInt(2, detail.getIdLayanan());
            ps.setDouble(3, detail.getQty());
            ps.setBigDecimal(4, detail.getSubtotal());

            //Menjalankan proses penyimpanan data
            ps.executeUpdate();

        } catch (SQLException e) {
            //Menampilkan pesan kesalahan jika proses gagal
            System.out.println(e.getMessage());

        }
    }

    //Mengambil seluruh detail transaksi berdasarkan nomor nota
    public List<DetailTransaksi> getDetailByNota(String noNota) {
        //Menampung seluruh data detail transaksi
        List<DetailTransaksi> list = new ArrayList<>();
        try {
            //Query untuk mengambil detail transaksi berdasarkan nomor nota
            String sql
                    = "SELECT "
                    + "l.nama_layanan, "
                    + "l.proses, "
                    + "l.satuan, "
                    + "dt.qty, "
                    + "l.harga, "
                    + "dt.subtotal "
                    + "FROM detail_transaksi dt "
                    + "JOIN transaksi t "
                    + "ON dt.id_transaksi = t.id_transaksi "
                    + "JOIN layanan l "
                    + "ON dt.id_layanan = l.id_layanan "
                    + "WHERE t.no_nota = ?";

            //Menyiapkan query SQL
            PreparedStatement ps
                    = conn.prepareStatement(sql);
            //Mengisi parameter nomor nota
            ps.setString(1, noNota);
            //Menjalankan query
            ResultSet rs = ps.executeQuery();
            //Membaca seluruh data hasil query
            while (rs.next()) {
                //Membuat objek detail transaksi
                DetailTransaksi detail
                        = new DetailTransaksi();
                //Mengisi data detail transaksi dari hasil query
                detail.setNamaLayanan(
                        rs.getString("nama_layanan"));
                detail.setNamaProses(
                        rs.getString("proses"));
                detail.setSatuan(
                        rs.getString("satuan"));
                detail.setQty(
                        rs.getDouble("qty"));
                detail.setHarga(
                        rs.getBigDecimal("harga"));
                detail.setSubtotal(
                        rs.getBigDecimal("subtotal"));
                //Menambahkan data ke dalam list
                list.add(detail);
            }

        } catch (SQLException e) {
            //Menampilkan informasi kesalahan jika proses gagal
            System.out.println("ERROR Detail DAO");
            e.printStackTrace();

        }
        //Mengembalikan daftar detail transaksi
        return list;

    }
}
