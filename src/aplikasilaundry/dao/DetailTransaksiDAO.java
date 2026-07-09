
package aplikasilaundry.dao;

//Mengimpor class koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor class JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Mengimpor model DetailTransaksi
import aplikasilaundry.model.DetailTransaksi;
//Mengimpor PreparedStatement
import java.sql.PreparedStatement;

//Mengimpor SQLException
import java.sql.SQLException;

//Mengimpor collection
import java.util.List;
import java.util.ArrayList;
//Mengimpor ResultSet
import java.sql.ResultSet;

//Class untuk mengelola data detail transaksi
public class DetailTransaksiDAO {
     //Menyimpan koneksi database
    private Connection conn;

    //Constructor
    public DetailTransaksiDAO() {

        //Mengambil koneksi database
        conn = Koneksi.getKoneksi();

    }

    //Method untuk menyimpan detail transaksi
public void simpan(DetailTransaksi detail){

    try{

        //Query menyimpan detail transaksi
        String sql =
                "INSERT INTO detail_transaksi "
                + "(id_transaksi,id_layanan,qty,subtotal) "
                + "VALUES (?,?,?,?)";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi id transaksi
        ps.setInt(1,
                detail.getIdTransaksi());

        //Mengisi id layanan
        ps.setInt(2,
                detail.getIdLayanan());

        //Mengisi jumlah
        ps.setDouble(3,
                detail.getQty());

        //Mengisi subtotal
        ps.setBigDecimal(4,
                detail.getSubtotal());

        //Menjalankan query
        ps.executeUpdate();

    }catch(SQLException e){

        //Menampilkan error
        System.out.println(e.getMessage());

    }

}
//Method mengambil seluruh detail transaksi berdasarkan nomor nota
public List<DetailTransaksi> getDetailByNota(String noNota){

    //Membuat list detail transaksi
    List<DetailTransaksi> list = new ArrayList<>();

    try{

        //Query mengambil detail transaksi
        String sql =
    "SELECT "
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

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi nomor nota
        ps.setString(1, noNota);

        //Menjalankan query
        ResultSet rs = ps.executeQuery();

        //Membaca seluruh data
        while(rs.next()){

            //Membuat objek detail
            DetailTransaksi detail =
                    new DetailTransaksi();

            //Nama layanan
            detail.setNamaLayanan(
                    rs.getString("nama_layanan"));

            //Nama proses
            detail.setNamaProses(
        rs.getString("proses"));
//Mengisi satuan layanan
detail.setSatuan(
        rs.getString("satuan"));
            //Qty
            detail.setQty(
                    rs.getDouble("qty"));

            //Harga
            detail.setHarga(
                    rs.getBigDecimal("harga"));

            //Subtotal
            detail.setSubtotal(
                    rs.getBigDecimal("subtotal"));

            //Menambahkan ke list
            list.add(detail);

        }

    }catch(SQLException e){

    System.out.println("ERROR Detail DAO");
    e.printStackTrace();

}

    //Mengembalikan list
    return list;

}
}
