
package aplikasilaundry.dao;

//Mengimpor koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor model pengaturan
import aplikasilaundry.model.PengaturanStruk;

//Mengimpor JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PengaturanStrukDAO {
    //Menyimpan koneksi database
    private Connection conn;

    //Constructor
    public PengaturanStrukDAO(){

        conn = Koneksi.getKoneksi();
        
}
    //Method mengambil data pengaturan
public PengaturanStruk getPengaturan(){

    //Membuat objek pengaturan
    PengaturanStruk pengaturan =
            new PengaturanStruk();

    try{

        //Query mengambil data
        String sql =
                "SELECT * "
                + "FROM pengaturan_struk "
                + "WHERE id_struk = 1";

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        //Jika data ditemukan
        if(rs.next()){

            //Mengisi ID
            pengaturan.setIdStruk(
                    rs.getInt("id_struk"));

            //Mengisi nama bisnis
            pengaturan.setNamaBisnis(
                    rs.getString("nama_bisnis"));

            //Mengisi alamat
            pengaturan.setAlamat(
                    rs.getString("alamat"));

            //Mengisi nomor HP
            pengaturan.setNoHp(
                    rs.getString("no_hp"));

            //Mengisi jam operasional
            pengaturan.setJamOperasional(
                    rs.getString("jam_operasional"));

            //Mengisi layanan
            pengaturan.setLayanan(
                    rs.getString("layanan"));
            
            //Mengisi layanan laundry
pengaturan.setLayanan(
        rs.getString("layanan"));

//Mengisi slogan laundry
pengaturan.setKeterangan(
        rs.getString("keterangan"));

//Mengisi footer struk
pengaturan.setFooterStruk(
        rs.getString("footer_struk"));

            //Mengisi footer
            pengaturan.setFooterStruk(
                    rs.getString("footer_struk"));
            
            

        }

    }catch(SQLException e){

        e.printStackTrace();

    }

    return pengaturan;

}
//Method memperbarui pengaturan
public void updatePengaturan(
        PengaturanStruk pengaturan){

    try{

        //Query update
        String sql =
                "UPDATE pengaturan_struk SET "
                + "nama_bisnis=?, "
                + "alamat=?, "
                + "no_hp=?, "
                + "jam_operasional=?, "
                + "layanan=?, "
                + "footer_struk=? "
                + "WHERE id_struk=?";

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(
                1,
                pengaturan.getNamaBisnis());

        ps.setString(
                2,
                pengaturan.getAlamat());

        ps.setString(
                3,
                pengaturan.getNoHp());

        ps.setString(
                4,
                pengaturan.getJamOperasional());

        ps.setString(
                5,
                pengaturan.getLayanan());

        ps.setString(
                6,
                pengaturan.getFooterStruk());

        ps.setInt(
                7,
                pengaturan.getIdStruk());

        //Menjalankan update
        ps.executeUpdate();

    }catch(SQLException e){

        e.printStackTrace();

    }

}
}
