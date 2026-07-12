
package aplikasilaundry.dao;
//Mengimpor koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Mengimpor BigDecimal
import java.math.BigDecimal;

//Mengimpor model layanan
import aplikasilaundry.model.Layanan;

//Mengimpor collection
import java.util.ArrayList;
import java.util.List;

public class LayananDAO {
    //Menyimpan koneksi
    private Connection conn;

    //Constructor
    public LayananDAO() {

        conn = Koneksi.getKoneksi();

    }
    //Method mengambil seluruh layanan
public List<Layanan> getAll(){

    //Menyimpan daftar layanan
    List<Layanan> daftar =
            new ArrayList<>();

    try{

        //Query mengambil seluruh layanan
        String sql =
                "SELECT * FROM layanan "
                + "ORDER BY nama_layanan, proses";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Mengambil seluruh data
        while(rs.next()){

            //Membuat objek layanan
            Layanan layanan =
                    new Layanan();

            layanan.setIdLayanan(
                    rs.getInt("id_layanan"));

            layanan.setNamaLayanan(
                    rs.getString("nama_layanan"));

            layanan.setPakaiProses(
                    rs.getString("pakai_proses"));

            layanan.setProses(
                    rs.getString("proses"));

            layanan.setSatuan(
                    rs.getString("satuan"));

            layanan.setHarga(
                    rs.getBigDecimal("harga"));

            layanan.setKeterangan(
                    rs.getString("keterangan"));

            //Menambahkan ke list
            daftar.add(layanan);

        }

    }catch(Exception e){

        e.printStackTrace();

    }

    return daftar;

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
   
    System.out.println("Layanan dipilih : " + layanan);
    System.out.println("Proses dipilih  : " + proses);


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
System.out.println("DATA TIDAK DITEMUKAN DI TABEL LAYANAN");
    //Jika tidak ditemukan
    return 0;

}
//Method mengambil layanan berdasarkan ID
public Layanan getById(int idLayanan){

    try{

        //Query mengambil satu layanan
        String sql =
                "SELECT * FROM layanan "
                + "WHERE id_layanan=?";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi ID
        ps.setInt(1, idLayanan);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Jika data ditemukan
        if(rs.next()){

            //Membuat objek layanan
            Layanan layanan =
                    new Layanan();

            layanan.setIdLayanan(
                    rs.getInt("id_layanan"));

            layanan.setNamaLayanan(
                    rs.getString("nama_layanan"));

            layanan.setPakaiProses(
                    rs.getString("pakai_proses"));

            layanan.setProses(
                    rs.getString("proses"));

            layanan.setSatuan(
                    rs.getString("satuan"));

            layanan.setHarga(
                    rs.getBigDecimal("harga"));

            layanan.setKeterangan(
                    rs.getString("keterangan"));

            return layanan;

        }

    }catch(Exception e){

        e.printStackTrace();

    }

    return null;

}
//Method menyimpan layanan
public boolean simpan(Layanan layanan){

    try{

        //Query menyimpan layanan
        String sql =
                "INSERT INTO layanan("
                + "nama_layanan,"
                + "pakai_proses,"
                + "proses,"
                + "satuan,"
                + "harga,"
                + "keterangan)"
                + " VALUES(?,?,?,?,?,?)";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi parameter
        ps.setString(1,
                layanan.getNamaLayanan());

        ps.setString(2,
                layanan.getPakaiProses());

        ps.setString(3,
                layanan.getProses());

        ps.setString(4,
                layanan.getSatuan());

        ps.setBigDecimal(5,
                layanan.getHarga());

        ps.setString(6,
                layanan.getKeterangan());

        //Menjalankan query
        return ps.executeUpdate() > 0;

    }catch(Exception e){

        e.printStackTrace();

    }

    return false;

}
//Method mengubah layanan
public boolean update(Layanan layanan){

    try{

        //Query mengubah layanan
        String sql =
                "UPDATE layanan SET "
                + "nama_layanan=?, "
                + "pakai_proses=?, "
                + "proses=?, "
                + "satuan=?, "
                + "harga=?, "
                + "keterangan=? "
                + "WHERE id_layanan=?";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi parameter
        ps.setString(1,
                layanan.getNamaLayanan());

        ps.setString(2,
                layanan.getPakaiProses());

        ps.setString(3,
                layanan.getProses());

        ps.setString(4,
                layanan.getSatuan());

        ps.setBigDecimal(5,
                layanan.getHarga());

        ps.setString(6,
                layanan.getKeterangan());

        ps.setInt(7,
                layanan.getIdLayanan());

        //Menjalankan query
        return ps.executeUpdate() > 0;

    }catch(Exception e){

        e.printStackTrace();

    }

    return false;

}
//Method menghapus layanan
public boolean hapus(int idLayanan){

    try{

        //Query menghapus layanan
        String sql =
                "DELETE FROM layanan "
                + "WHERE id_layanan=?";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi ID layanan
        ps.setInt(1, idLayanan);

        //Menjalankan query
        return ps.executeUpdate() > 0;

    }catch(Exception e){

        e.printStackTrace();

    }

    return false;

}
}
