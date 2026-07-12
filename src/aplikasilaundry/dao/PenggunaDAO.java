
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
//Mengimpor collection
import java.util.ArrayList;
import java.util.List;
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
    //Method mengambil seluruh data pengguna
public List<Pengguna> getAll(){

    //Membuat list pengguna
    List<Pengguna> list =
            new ArrayList<>();

    try{

        //Query mengambil seluruh pengguna
        String sql =
                "SELECT * FROM pengguna "
                + "ORDER BY id_pengguna DESC";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Mengulang seluruh data
        while(rs.next()){

            //Membuat objek pengguna
            Pengguna pengguna =
                    new Pengguna();

            //Mengisi ID pengguna
            pengguna.setIdPengguna(
                    rs.getInt("id_pengguna"));

            //Mengisi nama pengguna
            pengguna.setNamaPengguna(
                    rs.getString("nama_pengguna"));

            //Mengisi username
            pengguna.setUsername(
                    rs.getString("username"));

            //Mengisi role
            pengguna.setRole(
                    rs.getString("role"));

            //Menambahkan ke list
            list.add(pengguna);

        }

    }catch(SQLException e){

        //Menampilkan error
        e.printStackTrace();

    }

    //Mengembalikan seluruh data
    return list;

}

//Method mengambil data pengguna berdasarkan ID
public Pengguna getById(int idPengguna){

    //Membuat objek pengguna
    Pengguna pengguna = null;

    try{

        //Query mengambil pengguna berdasarkan ID
        String sql =
                "SELECT * FROM pengguna "
                + "WHERE id_pengguna=?";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi parameter ID
        ps.setInt(1, idPengguna);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Jika data ditemukan
        if(rs.next()){

            //Membuat objek pengguna
            pengguna = new Pengguna();

            //Mengisi ID
            pengguna.setIdPengguna(
                    rs.getInt("id_pengguna"));

            //Mengisi nama
            pengguna.setNamaPengguna(
                    rs.getString("nama_pengguna"));

            //Mengisi username
            pengguna.setUsername(
                    rs.getString("username"));

            //Mengisi password
            pengguna.setPassword(
                    rs.getString("password"));

            //Mengisi role
            pengguna.setRole(
                    rs.getString("role"));

        }

    }catch(SQLException e){

        e.printStackTrace();

    }

    return pengguna;

}
//Method mengubah data pengguna
public boolean update(Pengguna pengguna){

    try{

        PreparedStatement ps;

        //Jika password tidak diubah
        if(pengguna.getPassword() == null
                || pengguna.getPassword().trim().isEmpty()){

            //Query tanpa mengubah password
            String sql =
                    "UPDATE pengguna SET "
                    + "nama_pengguna=?, "
                    + "username=?, "
                    + "role=? "
                    + "WHERE id_pengguna=?";

            ps = conn.prepareStatement(sql);

            //Mengisi nama pengguna
            ps.setString(1,
                    pengguna.getNamaPengguna());

            //Mengisi username
            ps.setString(2,
                    pengguna.getUsername());

            //Mengisi role
            ps.setString(3,
                    pengguna.getRole());

            //Mengisi ID pengguna
            ps.setInt(4,
                    pengguna.getIdPengguna());

        }else{

            //Query beserta password
            String sql =
                    "UPDATE pengguna SET "
                    + "nama_pengguna=?, "
                    + "username=?, "
                    + "password=MD5(?), "
                    + "role=? "
                    + "WHERE id_pengguna=?";

            ps = conn.prepareStatement(sql);

            //Mengisi nama pengguna
            ps.setString(1,
                    pengguna.getNamaPengguna());

            //Mengisi username
            ps.setString(2,
                    pengguna.getUsername());

            //Mengisi password
            ps.setString(3,
                    pengguna.getPassword());

            //Mengisi role
            ps.setString(4,
                    pengguna.getRole());

            //Mengisi ID pengguna
            ps.setInt(5,
                    pengguna.getIdPengguna());

        }

        //Menjalankan query
        return ps.executeUpdate() > 0;

    }catch(SQLException e){

        System.out.println("===== ERROR UPDATE PENGGUNA =====");
        e.printStackTrace();

    }

    return false;

}
    //Method menyimpan data pengguna
public boolean simpan(Pengguna pengguna){

    try{

        //Query menyimpan pengguna
        String sql =
                "INSERT INTO pengguna "
                + "(nama_pengguna,username,password,role) "
                + "VALUES(?,?,MD5(?),?)";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi nama pengguna
        ps.setString(
                1,
                pengguna.getNamaPengguna());

        //Mengisi username
        ps.setString(
                2,
                pengguna.getUsername());

        //Mengisi password
        ps.setString(
                3,
                pengguna.getPassword());

        //Mengisi role
        ps.setString(
                4,
                pengguna.getRole());

        //Menjalankan query
        ps.executeUpdate();

        //Berhasil
        return true;

   }catch(SQLException e){

    System.out.println("===== ERROR DAO PENGGUNA =====");
    System.out.println(e.getMessage());
    e.printStackTrace();

}

    //Gagal
    return false;

}
//Method menghapus pengguna
public boolean hapus(int idPengguna){

    try{

        //Query menghapus pengguna
        String sql =
                "DELETE FROM pengguna "
                + "WHERE id_pengguna=?";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi ID pengguna
        ps.setInt(1, idPengguna);

        //Menjalankan query
        return ps.executeUpdate() > 0;

    }catch(SQLException e){

        System.out.println("===== ERROR HAPUS PENGGUNA =====");
        e.printStackTrace();

    }
    return false;
}

}
