
package aplikasilaundry.dao;
//Mengimpor koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Mengimpor BigDecimal
import java.math.BigDecimal;

public class LayananDAO {
    //Menyimpan koneksi
    private Connection conn;

    //Constructor
    public LayananDAO() {

        conn = Koneksi.getKoneksi();

    }

    //Method mengambil harga layanan
    public BigDecimal getHarga(String layanan,
                               String proses) {

        try {

            String sql;

            PreparedStatement ps;

            //Jika memakai proses
            if (proses != null) {

                sql =
                "SELECT harga " +
                "FROM layanan " +
                "WHERE nama_layanan=? " +
                "AND proses=?";

                ps = conn.prepareStatement(sql);

                ps.setString(1, layanan);
                ps.setString(2, proses);

            }

            //Jika tidak memakai proses
            else {

                sql =
                "SELECT harga " +
                "FROM layanan " +
                "WHERE nama_layanan=?";

                ps = conn.prepareStatement(sql);

                ps.setString(1, layanan);

            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getBigDecimal("harga");

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        

        return BigDecimal.ZERO;

    }
    //Method mengambil ID layanan
public int getIdLayanan(String layanan,
                        String proses){

    try{

        String sql;

        PreparedStatement ps;

        //Jika memakai proses
        if(proses != null){

            sql =
            "SELECT id_layanan " +
            "FROM layanan " +
            "WHERE nama_layanan=? " +
            "AND proses=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, layanan);
            ps.setString(2, proses);

        }

        //Jika tidak memakai proses
        else{

            sql =
            "SELECT id_layanan " +
            "FROM layanan " +
            "WHERE nama_layanan=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, layanan);

        }

        //Menjalankan query
        ResultSet rs = ps.executeQuery();

        //Jika data ditemukan
        if(rs.next()){

            //Mengembalikan ID layanan
            return rs.getInt("id_layanan");

        }

    }catch(Exception e){

        System.out.println(e.getMessage());

    }

    //Jika tidak ditemukan
    return 0;

}
}
