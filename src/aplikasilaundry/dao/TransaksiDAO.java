package aplikasilaundry.dao;
//Mengimpor class koneksi database
import aplikasilaundry.config.Koneksi;

//Mengimpor model transaksi
import aplikasilaundry.model.Transaksi;

//Mengimpor model transaksi simpan
import aplikasilaundry.model.TransaksiSimpan;

//Mengimpor Statement
import java.sql.Statement;

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

    //Query mengambil seluruh transaksi
String sql =
        "SELECT "
        + "t.no_nota, "
        + "p.nama_pelanggan, "
        + "t.jam_masuk, "
        + "MIN(l.nama_layanan) AS jenis, "
        + "COUNT(dt.id_detail) AS jumlah_item, "
        + "t.total_harga, "
        + "st.nama_status "
        + "FROM transaksi t "
        + "JOIN pelanggan p "
        + "ON t.id_pelanggan = p.id_pelanggan "
        + "JOIN status_transaksi st "
        + "ON t.id_status = st.id_status "
        + "JOIN detail_transaksi dt "
        + "ON t.id_transaksi = dt.id_transaksi "
        + "JOIN layanan l "
        + "ON dt.id_layanan = l.id_layanan "
        + "GROUP BY "
        + "t.id_transaksi, "
        + "t.no_nota, "
        + "p.nama_pelanggan, "
        + "t.jam_masuk, "
        + "t.total_harga, "
        + "st.nama_status "
        + "ORDER BY t.id_transaksi DESC";
            //Menyiapkan query
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //Menampilkan query yang dijalankan
System.out.println(sql);

            //Menjalankan query
            ResultSet rs = ps.executeQuery();
            
            //Menghitung jumlah data yang ditemukan
int jumlahData = 0;

           //Membaca seluruh data transaksi
while (rs.next()) {

    //Menambah jumlah data
    jumlahData++;

    //Membuat objek transaksi
    Transaksi transaksi = new Transaksi();

    //Mengisi nomor nota
    transaksi.setNoNota(rs.getString("no_nota"));

    //Mengisi nama pelanggan
    transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));

    //Mengisi jam masuk
    transaksi.setJamMasuk(rs.getString("jam_masuk"));

    //Mengisi jenis layanan
    transaksi.setJenis(rs.getString("jenis"));

    //Mengisi jumlah item
    transaksi.setJumlahItem(rs.getInt("jumlah_item"));

    //Mengisi total harga
    transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));

    //Mengisi status
    transaksi.setStatus(rs.getString("nama_status"));

    //Menambahkan ke list
    list.add(transaksi);

}
//Menampilkan jumlah data yang berhasil diambil
System.out.println("Jumlah Data = " + jumlahData);

        } catch (SQLException e) {

    //Menampilkan detail error
    e.printStackTrace();
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
                "SELECT "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "CONCAT("
                + "DATE_FORMAT(t.tanggal_ambil,'%d-%m-%Y'),' ',"
                + "TIME_FORMAT(t.jam_ambil,'%H:%i:%s')) AS jam_ambil, "
                + "MIN(l.nama_layanan) AS jenis, "
                + "COUNT(dt.id_detail) AS jumlah_item, "
                + "t.total_harga, "
                + "st.nama_status "
                + "FROM transaksi t "
                + "JOIN pelanggan p "
                + "ON t.id_pelanggan = p.id_pelanggan "
                + "JOIN status_transaksi st "
                + "ON t.id_status = st.id_status "
                + "JOIN detail_transaksi dt "
                + "ON t.id_transaksi = dt.id_transaksi "
                + "JOIN layanan l "
                + "ON dt.id_layanan = l.id_layanan "
                + "WHERE st.nama_status = 'Sudah Diambil' "
                + "GROUP BY "
                + "t.id_transaksi, "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "t.tanggal_ambil, "
                + "t.jam_ambil, "
                + "t.total_harga, "
                + "st.nama_status "
                + "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi nomor nota
            transaksi.setNoNota(
                    rs.getString("no_nota"));

            //Mengisi nama pelanggan
            transaksi.setNamaPelanggan(
                    rs.getString("nama_pelanggan"));

            //Mengisi jam masuk
            transaksi.setJamMasuk(
                    rs.getString("jam_masuk"));

            //Mengisi jam ambil
            transaksi.setJamAmbil(
                    rs.getString("jam_ambil"));

            //Mengisi jenis layanan
            transaksi.setJenis(
                    rs.getString("jenis"));

            //Mengisi jumlah item laundry
            transaksi.setJumlahItem(
                    rs.getInt("jumlah_item"));

            //Mengisi total harga
            transaksi.setTotalHarga(
                    rs.getBigDecimal("total_harga"));

            //Mengisi status transaksi
            transaksi.setStatus(
                    rs.getString("nama_status"));

            //Menambahkan transaksi ke dalam list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan detail error
        e.printStackTrace();

    }

    //Mengembalikan seluruh data
    return list;

}
    
 //Method mengambil transaksi terbaru
public List<Transaksi> getTransaksiTerbaru() {

    //Membuat list transaksi
    List<Transaksi> list = new ArrayList<>();

    try {

        //Query mengambil transaksi terbaru
        String sql =
                "SELECT "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "MIN(l.nama_layanan) AS jenis, "
                + "COUNT(dt.id_detail) AS jumlah_item, "
                + "t.total_harga, "
                + "st.nama_status "
                + "FROM transaksi t "
                + "JOIN pelanggan p "
                + "ON t.id_pelanggan = p.id_pelanggan "
                + "JOIN status_transaksi st "
                + "ON t.id_status = st.id_status "
                + "JOIN detail_transaksi dt "
                + "ON t.id_transaksi = dt.id_transaksi "
                + "JOIN layanan l "
                + "ON dt.id_layanan = l.id_layanan "
                + "GROUP BY "
                + "t.id_transaksi, "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "t.total_harga, "
                + "st.nama_status "
                + "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi =
                    new Transaksi();

            //Mengisi nomor nota
            transaksi.setNoNota(
                    rs.getString("no_nota"));

            //Mengisi nama pelanggan
            transaksi.setNamaPelanggan(
                    rs.getString("nama_pelanggan"));

            //Mengisi jam masuk
            transaksi.setJamMasuk(
                    rs.getString("jam_masuk"));

            //Mengisi jenis layanan
            transaksi.setJenis(
                    rs.getString("jenis"));

            //Mengisi jumlah item
            transaksi.setJumlahItem(
                    rs.getInt("jumlah_item"));

            //Mengisi total harga
            transaksi.setTotalHarga(
                    rs.getBigDecimal("total_harga"));

            //Mengisi status
            transaksi.setStatus(
                    rs.getString("nama_status"));

            //Menambahkan ke list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan detail error
        e.printStackTrace();

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
                "SELECT "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "MIN(l.nama_layanan) AS jenis, "
                + "COUNT(dt.id_detail) AS jumlah_item, "
                + "t.total_harga, "
                + "st.nama_status "
                + "FROM transaksi t "
                + "JOIN pelanggan p "
                + "ON t.id_pelanggan = p.id_pelanggan "
                + "JOIN status_transaksi st "
                + "ON t.id_status = st.id_status "
                + "JOIN detail_transaksi dt "
                + "ON t.id_transaksi = dt.id_transaksi "
                + "JOIN layanan l "
                + "ON dt.id_layanan = l.id_layanan "
                + "WHERE st.nama_status = 'Baru Masuk' "
                + "GROUP BY "
                + "t.id_transaksi, "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "t.total_harga, "
                + "st.nama_status "
                + "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi nomor nota
            transaksi.setNoNota(rs.getString("no_nota"));

            //Mengisi nama pelanggan
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));

            //Mengisi jam masuk
            transaksi.setJamMasuk(rs.getString("jam_masuk"));

            //Mengisi jenis layanan
            transaksi.setJenis(rs.getString("jenis"));

            //Mengisi jumlah item
            transaksi.setJumlahItem(rs.getInt("jumlah_item"));

            //Mengisi total harga
            transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));

            //Mengisi status
            transaksi.setStatus(rs.getString("nama_status"));

            //Menambahkan ke list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan error
        e.printStackTrace();

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
                "SELECT "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "MIN(l.nama_layanan) AS jenis, "
                + "COUNT(dt.id_detail) AS jumlah_item, "
                + "t.total_harga, "
                + "st.nama_status "
                + "FROM transaksi t "
                + "JOIN pelanggan p "
                + "ON t.id_pelanggan = p.id_pelanggan "
                + "JOIN status_transaksi st "
                + "ON t.id_status = st.id_status "
                + "JOIN detail_transaksi dt "
                + "ON t.id_transaksi = dt.id_transaksi "
                + "JOIN layanan l "
                + "ON dt.id_layanan = l.id_layanan "
                + "WHERE st.nama_status = 'Diproses' "
                + "GROUP BY "
                + "t.id_transaksi, "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "t.total_harga, "
                + "st.nama_status "
                + "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi nomor nota
            transaksi.setNoNota(rs.getString("no_nota"));

            //Mengisi nama pelanggan
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));

            //Mengisi jam masuk
            transaksi.setJamMasuk(rs.getString("jam_masuk"));

            //Mengisi jenis layanan
            transaksi.setJenis(rs.getString("jenis"));

            //Mengisi jumlah item laundry
            transaksi.setJumlahItem(rs.getInt("jumlah_item"));

            //Mengisi total harga
            transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));

            //Mengisi status transaksi
            transaksi.setStatus(rs.getString("nama_status"));

            //Menambahkan transaksi ke dalam list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan error
        e.printStackTrace();

    }

    //Mengembalikan seluruh data
    return list;

}
    //Method untuk mengambil data laundry dengan status Selesai

   //Method untuk mengambil data laundry dengan status Selesai
public List<Transaksi> getSelesai() {

    //Membuat list transaksi
    List<Transaksi> list = new ArrayList<>();

    try {

        //Query mengambil data laundry dengan status Selesai
        String sql =
                "SELECT "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "MIN(l.nama_layanan) AS jenis, "
                + "COUNT(dt.id_detail) AS jumlah_item, "
                + "t.total_harga, "
                + "st.nama_status "
                + "FROM transaksi t "
                + "JOIN pelanggan p "
                + "ON t.id_pelanggan = p.id_pelanggan "
                + "JOIN status_transaksi st "
                + "ON t.id_status = st.id_status "
                + "JOIN detail_transaksi dt "
                + "ON t.id_transaksi = dt.id_transaksi "
                + "JOIN layanan l "
                + "ON dt.id_layanan = l.id_layanan "
                + "WHERE st.nama_status = 'Selesai' "
                + "GROUP BY "
                + "t.id_transaksi, "
                + "t.no_nota, "
                + "p.nama_pelanggan, "
                + "t.jam_masuk, "
                + "t.total_harga, "
                + "st.nama_status "
                + "ORDER BY t.id_transaksi DESC";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Membaca seluruh data
        while (rs.next()) {

            //Membuat objek transaksi
            Transaksi transaksi = new Transaksi();

            //Mengisi nomor nota
            transaksi.setNoNota(
                    rs.getString("no_nota"));

            //Mengisi nama pelanggan
            transaksi.setNamaPelanggan(
                    rs.getString("nama_pelanggan"));

            //Mengisi jam masuk
            transaksi.setJamMasuk(
                    rs.getString("jam_masuk"));

            //Mengisi jenis layanan
            transaksi.setJenis(
                    rs.getString("jenis"));

            //Mengisi jumlah item laundry
            transaksi.setJumlahItem(
                    rs.getInt("jumlah_item"));

            //Mengisi total harga
            transaksi.setTotalHarga(
                    rs.getBigDecimal("total_harga"));

            //Mengisi status transaksi
            transaksi.setStatus(
                    rs.getString("nama_status"));

            //Menambahkan transaksi ke dalam list
            list.add(transaksi);

        }

    } catch (SQLException e) {

        //Menampilkan detail error
        e.printStackTrace();

    }

    //Mengembalikan seluruh data
    return list;

}

//Method menyimpan transaksi
    public int simpan(TransaksiSimpan transaksi) {

        //ID transaksi yang akan dikembalikan
        int idTransaksi = 0;

        try {

            //Query menyimpan transaksi
String sql =
        "INSERT INTO transaksi "
        + "(no_nota,"
        + "id_pengguna,"
        + "id_pelanggan,"
        + "id_status,"
        + "tanggal_masuk,"
        + "jam_masuk,"
        + "catatan,"
        + "total_harga) "
        + "VALUES (?,?,?,?,?,?,?,?)";

            //Menyiapkan query
            PreparedStatement ps
                    = conn.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS);

           //Mengisi nomor nota otomatis
ps.setString(1,
        generateNoNota());

            //ID pengguna
            ps.setInt(2,
                    transaksi.getIdPengguna());

            //ID pelanggan
            ps.setInt(3,
                    transaksi.getIdPelanggan());

            //Status
            ps.setInt(4,
                    transaksi.getIdStatus());

            //Tanggal masuk
            ps.setDate(5,
                    java.sql.Date.valueOf(
                            transaksi.getTanggalMasuk()));

            //Jam masuk
            ps.setTime(6,
                    java.sql.Time.valueOf(
                            transaksi.getJamMasuk()));

            //Catatan
            ps.setString(7,
                    transaksi.getCatatan());

            //Total harga
            ps.setBigDecimal(8,
                    transaksi.getTotalHarga());

            //Menjalankan query
            ps.executeUpdate();

            //Mengambil ID transaksi
            ResultSet rs
                    = ps.getGeneratedKeys();

            //Jika berhasil
            if (rs.next()) {

                //Menyimpan ID transaksi
                idTransaksi = rs.getInt(1);

            }

        } catch (SQLException e) {

            //Menampilkan error
            System.out.println(e.getMessage());

        }

        //Mengembalikan ID transaksi
        return idTransaksi;

    }
    //Method untuk membuat nomor nota otomatis
private String generateNoNota(){

    try{

        //Mengambil tanggal hari ini dengan format yyMMdd
        java.time.LocalDate hariIni =
                java.time.LocalDate.now();

        //Formatter tanggal
        java.time.format.DateTimeFormatter format =
                java.time.format.DateTimeFormatter.ofPattern("yyMMdd");

        //Mengubah tanggal menjadi String
        String tanggal =
                hariIni.format(format);

        //Query mengambil nomor nota terakhir hari ini
        String sql =
                "SELECT no_nota "
                + "FROM transaksi "
                + "WHERE no_nota LIKE ? "
                + "ORDER BY no_nota DESC "
                + "LIMIT 1";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mencari nota dengan tanggal hari ini
        ps.setString(1,
                "INV-" + tanggal + "-%");

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Nomor urut pertama
        int urut = 1;

        //Jika ada transaksi hari ini
        if(rs.next()){

            //Mengambil nomor nota terakhir
            String nota =
                    rs.getString("no_nota");

            //Mengambil 3 digit terakhir
            String angka =
                    nota.substring(
                            nota.length() - 3);

            //Menambah nomor urut
            urut =
                    Integer.parseInt(angka) + 1;

        }

        //Menampilkan nomor nota yang dibuat
System.out.println(
        "Nomor Nota = INV-" 
        + tanggal 
        + "-" 
        + String.format("%03d", urut));
        //Mengembalikan nomor nota baru
        return String.format(
                "INV-%s-%03d",
                tanggal,
                urut);

    }catch(Exception e){

        //Menampilkan error
        System.out.println(e.getMessage());

    }

    //Jika gagal
    return null;

}
//Method menghapus transaksi berdasarkan nomor nota
public void hapusTransaksi(String noNota){

    try{

        //Mengambil ID transaksi berdasarkan nomor nota
        String sqlCari =
                "SELECT id_transaksi "
                + "FROM transaksi "
                + "WHERE no_nota = ?";

        PreparedStatement psCari =
                conn.prepareStatement(sqlCari);

        psCari.setString(1, noNota);

        ResultSet rs =
                psCari.executeQuery();

        //Jika transaksi ditemukan
        if(rs.next()){

            //Mengambil ID transaksi
            int idTransaksi =
                    rs.getInt("id_transaksi");

            //Menghapus seluruh detail transaksi
            String sqlDetail =
                    "DELETE FROM detail_transaksi "
                    + "WHERE id_transaksi = ?";

            PreparedStatement psDetail =
                    conn.prepareStatement(sqlDetail);

            psDetail.setInt(1, idTransaksi);

            psDetail.executeUpdate();

            //Menghapus transaksi
            String sqlTransaksi =
                    "DELETE FROM transaksi "
                    + "WHERE id_transaksi = ?";

            PreparedStatement psTransaksi =
                    conn.prepareStatement(sqlTransaksi);

            psTransaksi.setInt(1, idTransaksi);

            psTransaksi.executeUpdate();

        }

    }catch(SQLException e){

        e.printStackTrace();

    }

}

    //Method mengambil satu transaksi berdasarkan nomor nota
public Transaksi getByNoNota(String noNota){

    //Membuat objek transaksi
    Transaksi transaksi = null;

    try{

        //Query mengambil data transaksi
       String sql =
        "SELECT "
        + "t.no_nota, "
        + "t.tanggal_masuk, "
        + "t.jam_masuk, "
        + "t.jam_ambil, "
        + "p.nama_pelanggan, "
        + "p.no_hp, "
        + "p.alamat, "
        + "st.nama_status "
        + "FROM transaksi t "
        + "JOIN pelanggan p "
        + "ON t.id_pelanggan = p.id_pelanggan "
        + "JOIN status_transaksi st "
        + "ON t.id_status = st.id_status "
        + "WHERE t.no_nota = ?";

        //Menyiapkan query
        PreparedStatement ps =
                conn.prepareStatement(sql);

        //Mengisi parameter nomor nota
        ps.setString(1, noNota);

        //Menjalankan query
        ResultSet rs =
                ps.executeQuery();

        //Jika data ditemukan
        if(rs.next()){

            //Membuat objek transaksi
            transaksi = new Transaksi();

            //Mengisi nomor nota
            transaksi.setNoNota(
                    rs.getString("no_nota"));

            //Mengisi nama pelanggan
            transaksi.setNamaPelanggan(
                    rs.getString("nama_pelanggan"));

            //Mengisi status
            transaksi.setStatus(
                    rs.getString("nama_status"));

            //Mengisi jam masuk
            transaksi.setJamMasuk(
                    rs.getString("jam_masuk"));

            //Mengisi jam ambil
            transaksi.setJamAmbil(
                    rs.getString("jam_ambil"));
            
            //Mengisi tanggal masuk
transaksi.setTanggalMasuk(
        rs.getString("tanggal_masuk"));

//Mengisi nomor HP
transaksi.setNoHp(
        rs.getString("no_hp"));

//Mengisi alamat pelanggan
transaksi.setAlamat(
        rs.getString("alamat"));

        }

    }catch(SQLException e){

        e.printStackTrace();

    }

    return transaksi;

}
//Method mengubah data transaksi
public void updateTransaksi(String noNota,
                            String namaPelanggan,
                            String status){

    try{

        //Mengubah nama pelanggan
        String sqlPelanggan =
                "UPDATE pelanggan p "
                + "JOIN transaksi t "
                + "ON p.id_pelanggan = t.id_pelanggan "
                + "SET p.nama_pelanggan = ? "
                + "WHERE t.no_nota = ?";

        PreparedStatement psPelanggan =
                conn.prepareStatement(sqlPelanggan);

        psPelanggan.setString(1, namaPelanggan);
        psPelanggan.setString(2, noNota);

        psPelanggan.executeUpdate();

        //Mengubah status transaksi
        String sqlStatus =
                "UPDATE transaksi "
                + "SET id_status = "
                + "(SELECT id_status "
                + "FROM status_transaksi "
                + "WHERE nama_status = ?) "
                + "WHERE no_nota = ?";

        PreparedStatement psStatus =
                conn.prepareStatement(sqlStatus);

        psStatus.setString(1, status);
        psStatus.setString(2, noNota);

        psStatus.executeUpdate();

        //Jika status sudah diambil
        if(status.equals("Sudah Diambil")){

            String sqlAmbil =
                    "UPDATE transaksi "
                    + "SET tanggal_ambil = CURDATE(), "
                    + "jam_ambil = CURTIME() "
                    + "WHERE no_nota = ?";

            PreparedStatement psAmbil =
                    conn.prepareStatement(sqlAmbil);

            psAmbil.setString(1, noNota);

            psAmbil.executeUpdate();

        }

    }catch(SQLException e){

        e.printStackTrace();

    }

}
//Method mengambil laporan pemasukan harian
//public List<DetailTransaksi> getLaporanHarian(Date tanggal){
//
//    //Membuat list laporan
//    List<DetailTransaksi> list =
//            new ArrayList<>();
//
//    try{
//
//        //Query laporan
//        String sql =
//                "SELECT "
//                + "l.nama_layanan, "
//                + "l.proses, "
//                + "SUM(dt.qty) AS jumlah, "
//                + "SUM(dt.subtotal) AS subtotal "
//                + "FROM transaksi t "
//                + "JOIN detail_transaksi dt "
//                + "ON t.id_transaksi = dt.id_transaksi "
//                + "JOIN layanan l "
//                + "ON dt.id_layanan = l.id_layanan "
//                + "JOIN status_transaksi st "
//                + "ON t.id_status = st.id_status "
//                + "WHERE st.nama_status = 'Sudah Diambil' "
//                + "AND t.tanggal_ambil = ? "
//                + "GROUP BY "
//                + "l.nama_layanan, "
//                + "l.proses "
//                + "ORDER BY l.nama_layanan";
//
//        PreparedStatement ps =
//                conn.prepareStatement(sql);
//
//        ps.setDate(
//                1,
//                new java.sql.Date(
//                        tanggal.getTime()));
//
//        ResultSet rs =
//                ps.executeQuery();
//
//        while(rs.next()){
//
//            DetailTransaksi detail =
//                    new DetailTransaksi();
//
//            detail.setNamaLayanan(
//                    rs.getString("nama_layanan"));
//
//            detail.setProses(
//                    rs.getString("proses"));
//
//            detail.setQty(
//                    rs.getDouble("jumlah"));
//
//            detail.setSubtotal(
//                    rs.getBigDecimal("subtotal"));
//
//            list.add(detail);
//
//        }
//
//    }catch(SQLException e){
//
//        e.printStackTrace();
//
//    }
//
//    return list;
//
//}
}
