
package aplikasilaundry.dao;

//Mengimpor class koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor model transaksi
import aplikasilaundry.model.Transaksi;

//Mengimpor class JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Mengimpor collection
import java.util.ArrayList;
import java.util.List;

//Class untuk mengelola data transaksi
public class TransaksiDAO {
    //Menyimpan koneksi database
    private Connection conn;

    //Constructor
    public TransaksiDAO() {

        //Mengambil koneksi dari class Koneksi
        conn = Koneksi.getKoneksi();

    }

    //Method untuk mengambil seluruh data transaksi
    public List<Transaksi> getAll() {

        //Membuat list transaksi
        List<Transaksi> list = new ArrayList<>();

        try {

            //Query mengambil seluruh data transaksi
            String sql =
                    "SELECT " +
                    "t.no_nota, " +
                    "p.nama_pelanggan, " +
                    "t.jam_masuk, " +
                    "l.nama_layanan, " +
                    "t.total_harga, " +
                    "st.nama_status " +
                    "FROM transaksi t " +
                    "JOIN pelanggan p ON t.id_pelanggan = p.id_pelanggan " +
                    "JOIN status_transaksi st ON t.id_status = st.id_status " +
                    "JOIN detail_transaksi dt ON t.id_transaksi = dt.id_transaksi " +
                    "JOIN layanan l ON dt.id_layanan = l.id_layanan " +
                    "ORDER BY t.id_transaksi DESC";

            //Menyiapkan query
            PreparedStatement ps = conn.prepareStatement(sql);

            //Menjalankan query
            ResultSet rs = ps.executeQuery();

            //Membaca seluruh data
            while (rs.next()) {

                //Membuat objek transaksi
                Transaksi transaksi = new Transaksi();

                //Mengisi data transaksi
                transaksi.setNoNota(rs.getString("no_nota"));
                transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
                transaksi.setJamMasuk(rs.getString("jam_masuk"));
                transaksi.setJenis(rs.getString("nama_layanan"));
                transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
                transaksi.setStatus(rs.getString("nama_status"));

                //Menambahkan ke dalam list
                list.add(transaksi);

            }

        } catch (SQLException e) {

            //Menampilkan pesan error
            System.out.println(e.getMessage());

        }

        //Mengembalikan list transaksi
        return list;

    }
    
    //Method untuk mengambil data laundry dengan status Baru Masuk
public List<Transaksi> getLaundryMasuk() {

    //Membuat list transaksi
    List<Transaksi> list = new ArrayList<>();

    try {

        //Query mengambil data laundry dengan status Baru Masuk
        String sql =
                "SELECT " +
                "t.no_nota, " +
                "p.nama_pelanggan, " +
                "t.jam_masuk, " +
                "l.nama_layanan, " +
                "t.total_harga, " +
                "st.nama_status " +
                "FROM transaksi t " +
                "JOIN pelanggan p ON t.id_pelanggan = p.id_pelanggan " +
                "JOIN status_transaksi st ON t.id_status = st.id_status " +
                "JOIN detail_transaksi dt ON t.id_transaksi = dt.id_transaksi " +
                "JOIN layanan l ON dt.id_layanan = l.id_layanan " +
                "WHERE st.nama_status = 'Baru Masuk' " +
                "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps = conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs = ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi data transaksi
            transaksi.setNoNota(rs.getString("no_nota"));
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
            transaksi.setJamMasuk(rs.getString("jam_masuk"));
            transaksi.setJenis(rs.getString("nama_layanan"));
            transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
            transaksi.setStatus(rs.getString("nama_status"));

            //Menambahkan ke dalam list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan pesan error
        System.out.println(e.getMessage());

    }

    //Mengembalikan list transaksi
    return list;

}
    
    //Method untuk mengambil data laundry dengan status Diproses
public List<Transaksi> getDiproses() {

    //Membuat list transaksi
    List<Transaksi> list = new ArrayList<>();

    try {

        //Query mengambil data laundry dengan status Diproses
        String sql =
                "SELECT " +
                "t.no_nota, " +
                "p.nama_pelanggan, " +
                "t.jam_masuk, " +
                "l.nama_layanan, " +
                "t.total_harga, " +
                "st.nama_status " +
                "FROM transaksi t " +
                "JOIN pelanggan p ON t.id_pelanggan = p.id_pelanggan " +
                "JOIN status_transaksi st ON t.id_status = st.id_status " +
                "JOIN detail_transaksi dt ON t.id_transaksi = dt.id_transaksi " +
                "JOIN layanan l ON dt.id_layanan = l.id_layanan " +
                "WHERE st.nama_status = 'Diproses' " +
                "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps = conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs = ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi data transaksi
            transaksi.setNoNota(rs.getString("no_nota"));
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
            transaksi.setJamMasuk(rs.getString("jam_masuk"));
            transaksi.setJenis(rs.getString("nama_layanan"));
            transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
            transaksi.setStatus(rs.getString("nama_status"));

            //Menambahkan ke dalam list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan pesan error
        System.out.println(e.getMessage());

    }

    //Mengembalikan list transaksi
    return list;

}
    //Method untuk mengambil data laundry dengan status Selesai
public List<Transaksi> getSelesai() {

    //Membuat list transaksi
    List<Transaksi> list = new ArrayList<>();

    try {

        //Query mengambil data laundry dengan status Selesai
        String sql =
                "SELECT " +
                "t.no_nota, " +
                "p.nama_pelanggan, " +
                "t.jam_masuk, " +
                "l.nama_layanan, " +
                "t.total_harga, " +
                "st.nama_status " +
                "FROM transaksi t " +
                "JOIN pelanggan p ON t.id_pelanggan = p.id_pelanggan " +
                "JOIN status_transaksi st ON t.id_status = st.id_status " +
                "JOIN detail_transaksi dt ON t.id_transaksi = dt.id_transaksi " +
                "JOIN layanan l ON dt.id_layanan = l.id_layanan " +
                "WHERE st.nama_status = 'Selesai' " +
                "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps = conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs = ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi data transaksi
            transaksi.setNoNota(rs.getString("no_nota"));
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
            transaksi.setJamMasuk(rs.getString("jam_masuk"));
            transaksi.setJenis(rs.getString("nama_layanan"));
            transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
            transaksi.setStatus(rs.getString("nama_status"));

            //Menambahkan ke dalam list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan pesan error
        System.out.println(e.getMessage());

    }

    //Mengembalikan list transaksi
    return list;

}

//Method untuk mengambil data laundry dengan status Sudah Diambil
public List<Transaksi> getSudahDiambil() {

    //Membuat list transaksi
    List<Transaksi> list = new ArrayList<>();

    try {

        //Query mengambil data laundry dengan status Sudah Diambil
        String sql =
                "SELECT " +
                "t.no_nota, " +
                "p.nama_pelanggan, " +
                "t.jam_masuk, " +
                "CONCAT(DATE_FORMAT(t.tanggal_ambil,'%d-%m-%Y'),' ',TIME_FORMAT(t.jam_ambil,'%H:%i:%s')) AS jam_ambil, "  +
                "l.nama_layanan, " +
                "t.total_harga, " +
                "st.nama_status " +
                "FROM transaksi t " +
                "JOIN pelanggan p ON t.id_pelanggan = p.id_pelanggan " +
                "JOIN status_transaksi st ON t.id_status = st.id_status " +
                "JOIN detail_transaksi dt ON t.id_transaksi = dt.id_transaksi " +
                "JOIN layanan l ON dt.id_layanan = l.id_layanan " +
                "WHERE st.nama_status = 'Sudah Diambil' " +
                "ORDER BY t.id_transaksi DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Transaksi transaksi = new Transaksi();

            transaksi.setNoNota(rs.getString("no_nota"));
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
            transaksi.setJamMasuk(rs.getString("jam_masuk"));
            transaksi.setJamAmbil(rs.getString("jam_ambil"));
            transaksi.setJenis(rs.getString("nama_layanan"));
            transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
            transaksi.setStatus(rs.getString("nama_status"));

            list.add(transaksi);

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return list;

}


}
