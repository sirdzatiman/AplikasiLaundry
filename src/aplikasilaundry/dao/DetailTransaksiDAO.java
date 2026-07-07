
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
}
