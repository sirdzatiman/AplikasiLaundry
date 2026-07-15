package aplikasilaundry.controller;

//Mengimpor model transaksi sementara
import aplikasilaundry.model.DataTransaksi;
//Mengimpor model item laundry
import aplikasilaundry.model.ItemLaundry;
//Mengimpor model transaksi
import aplikasilaundry.model.Transaksi;
//Mengimpor model pelanggan
import aplikasilaundry.model.Pelanggan;
//Mengimpor panel Semua
import aplikasilaundry.view.panel.Semua;
//Mengimpor DAO transaksi
import aplikasilaundry.dao.TransaksiDAO;
//Mengimpor List untuk menampung kumpulan data
import java.util.List;
//Mengimpor DAO layanan
import aplikasilaundry.dao.LayananDAO;
//Mengimpor BigDecimal untuk menyimpan nilai mata uang
import java.math.BigDecimal;
//Mengimpor DAO pelanggan
import aplikasilaundry.dao.PelangganDAO;
//Mengimpor DAO detail transaksi
import aplikasilaundry.dao.DetailTransaksiDAO;
//Mengimpor model transaksi yang akan disimpan
import aplikasilaundry.model.TransaksiSimpan;
//Mengimpor model detail transaksi
import aplikasilaundry.model.DetailTransaksi;
//Mengimpor LocalDate untuk menyimpan tanggal
import java.time.LocalDate;
//Mengimpor LocalTime untuk menyimpan waktu
import java.time.LocalTime;

//Class yang menghubungkan tampilan dengan DAO pada proses transaksi laundry
public class TransaksiController {

    //Objek DAO untuk mengelola data transaksi
    private TransaksiDAO dao;
    //Objek DAO untuk mengelola data layanan
    private LayananDAO layananDAO;
    //Objek DAO untuk mengelola data pelanggan
    private PelangganDAO pelangganDAO;
    //Objek DAO untuk mengelola detail transaksi
    private DetailTransaksiDAO detailDAO;
    //Menyimpan data transaksi sementara selama proses input
    private DataTransaksi dataTransaksi;

    //Constructor untuk menginisialisasi seluruh objek DAO dan transaksi sementara
    public TransaksiController() {
        //Menginisialisasi DAO transaksi
        dao = new TransaksiDAO();
        //Menginisialisasi DAO pelanggan
        pelangganDAO = new PelangganDAO();
        //Menginisialisasi DAO detail transaksi
        detailDAO = new DetailTransaksiDAO();
        //Menginisialisasi DAO layanan
        layananDAO = new LayananDAO();
        //Mengambil objek transaksi sementara
        dataTransaksi = DataTransaksi.getInstance();
    }

    //Mengambil seluruh data transaksi berdasarkan kata kunci dan tanggal
    public List<Transaksi> getAll(String keyword,
            java.util.Date tanggal) {
        return dao.getAll(keyword, tanggal);
    }

    //Mengambil daftar transaksi terbaru
    public List<Transaksi> getTransaksiTerbaru() {
        return dao.getTransaksiTerbaru();
    }

    //Mengambil data transaksi berdasarkan nomor nota
    public Transaksi getByNoNota(String noNota) {
        return dao.getByNoNota(noNota);
    }

    //Mengambil data transaksi berdasarkan nomor nota
    public Transaksi getTransaksiByNota(String noNota) {
        return dao.getByNoNota(noNota);
    }

    //Mengambil seluruh detail transaksi berdasarkan nomor nota
    public List<DetailTransaksi> getDetailByNota(String noNota) {
        return detailDAO.getDetailByNota(noNota);
    }

    //Mengambil seluruh data laundry dengan status Selesai
    public List<Transaksi> getSelesai() {
        return dao.getSelesai();
    }

    //Mengambil data laundry berstatus Selesai berdasarkan kata kunci dan tanggal
    public List<Transaksi> getSelesai(String keyword,
            java.util.Date tanggal) {
        return dao.getSelesai(keyword, tanggal);
    }

    //Mengambil seluruh data laundry dengan status Baru Masuk
    public List<Transaksi> getLaundryMasuk() {
        return dao.getLaundryMasuk();
    }

    //Mengambil data laundry berstatus Baru Masuk berdasarkan kata kunci dan tanggal
    public List<Transaksi> getLaundryMasuk(String keyword,
            java.util.Date tanggal) {
        return dao.getLaundryMasuk(keyword, tanggal);

    }

    //Mengambil seluruh data laundry dengan status Diproses
    public List<Transaksi> getDiproses() {
        return dao.getDiproses();

    }

    //Mengambil data laundry berstatus Diproses berdasarkan kata kunci dan tanggal
    public List<Transaksi> getDiproses(String keyword,
            java.util.Date tanggal) {
        return dao.getDiProses(keyword, tanggal);

    }

    //Mengambil seluruh data laundry dengan status Sudah Diambil
    public List<Transaksi> getSudahDiambil() {
        return dao.getSudahDiambil();

    }

    //Mengambil data riwayat laundry berdasarkan kata kunci dan tanggal
    public List<Transaksi> getRiwayat(String keyword,
            java.util.Date tanggal) {
        return dao.getRiwayat(keyword, tanggal);

    }

    //Menyimpan data pelanggan sementara sebelum transaksi diproses
    public void simpanPelanggan(String nama,
            String hp,
            String alamat,
            String catatan) {

        //Membuat objek pelanggan baru
        Pelanggan pelanggan = new Pelanggan();
        //Mengisi data pelanggan
        pelanggan.setNamaPelanggan(nama);
        pelanggan.setNoHp(hp);
        pelanggan.setAlamat(alamat);
        pelanggan.setCatatan(catatan);
        //Menyimpan pelanggan ke transaksi sementara
        dataTransaksi.setPelanggan(pelanggan);
        
        System.out.println("===== SIMPAN PELANGGAN =====");
        System.out.println("DataTransaksi : " + dataTransaksi);
        System.out.println("Pelanggan : " + dataTransaksi.getPelanggan());
        System.out.println("Nama : " + dataTransaksi.getPelanggan().getNamaPelanggan());
    }

    //Mengambil data pelanggan sementara
    public Pelanggan getPelangganSementara() {
        return dataTransaksi.getPelanggan();
    }

    //Mengambil seluruh item laundry sementara
    public List<ItemLaundry> getDaftarItemSementara() {
        return dataTransaksi.getDaftarItem();
    }

    //Menghitung total berat laundry sementara
    public double getTotalBeratSementara() {
        double total = 0;
        //Menjumlahkan seluruh item dengan satuan kilogram
        for (ItemLaundry item : dataTransaksi.getDaftarItem()) {
            if (item.getSatuan().equalsIgnoreCase("Kg")) {
                total += item.getQty();
            }
        }
        return total;
    }

    //Mengambil harga layanan berdasarkan jenis layanan dan proses
    public BigDecimal getHargaLayanan(String layanan,
            String proses) {
        return layananDAO.getHarga(layanan, proses);
    }

    //Menghitung total jumlah item dengan satuan biji
    public int getTotalBijiSementara() {
        int total = 0;
        //Menjumlahkan seluruh item dengan satuan biji
        for (ItemLaundry item : dataTransaksi.getDaftarItem()) {
            if (item.getSatuan().equalsIgnoreCase("Biji")) {
                total += (int) item.getQty();
            }
        }
        return total;
    }

    //Mengambil total harga seluruh item laundry sementara
    public BigDecimal getTotalHargaSementara() {
        return hitungTotalHarga();
    }

    //Mengambil ID layanan berdasarkan jenis layanan dan proses
    public int getIdLayanan(String layanan,
            String proses) {
        return layananDAO.getIdLayanan(
                layanan,
                proses);
    }

    //Menambahkan item laundry ke transaksi sementara
    public void tambahItem(ItemLaundry item) {
        dataTransaksi.tambahItem(item);
    }

    //Memperbarui data transaksi
    public void updateTransaksi(String noNota,
            String namaPelanggan,
            String status) {

        dao.updateTransaksi(
                noNota,
                namaPelanggan,
                status);
    }

    //Mengambil daftar item laundry sementara
    public List<ItemLaundry> getDaftarItem() {
        return dataTransaksi.getDaftarItem();
    }

    //Menghitung total harga seluruh item laundry
    private BigDecimal hitungTotalHarga() {
        BigDecimal total = BigDecimal.ZERO;
        //Menjumlahkan subtotal seluruh item laundry
        for (ItemLaundry item : dataTransaksi.getDaftarItem()) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }

    //Menyimpan seluruh transaksi beserta detailnya ke database
    public void simpanTransaksi() {
        //Mengambil data pelanggan sementara
        Pelanggan pelanggan = dataTransaksi.getPelanggan();
        //Menyimpan pelanggan ke database
        int idPelanggan = pelangganDAO.simpan(pelanggan);
        //Membuat objek transaksi
        TransaksiSimpan transaksi = new TransaksiSimpan();
        //Mengisi data transaksi
        transaksi.setIdPelanggan(idPelanggan);
        //Menggunakan ID pengguna sementara sebelum login diintegrasikan
        transaksi.setIdPengguna(1);
        //Status awal laundry adalah Baru Masuk
        transaksi.setIdStatus(1);
        //Mengisi tanggal dan jam transaksi
        transaksi.setTanggalMasuk(LocalDate.now());
        transaksi.setJamMasuk(LocalTime.now());
        //Mengisi catatan pelanggan
        transaksi.setCatatan(pelanggan.getCatatan());
        //Mengisi total harga transaksi
        transaksi.setTotalHarga(hitungTotalHarga());
        //Menyimpan transaksi dan mendapatkan ID transaksi
        int idTransaksi = dao.simpan(transaksi);
        
        //Menyimpan seluruh detail item laundry
        for (ItemLaundry item : dataTransaksi.getDaftarItem()) {

            DetailTransaksi detail = new DetailTransaksi();

            detail.setIdTransaksi(idTransaksi);
            detail.setIdLayanan(item.getIdLayanan());
            detail.setQty(item.getQty());
            detail.setSubtotal(item.getSubtotal());
            detailDAO.simpan(detail);
        }
    }

    //Mengosongkan seluruh data transaksi sementara
    public void resetTransaksi() {
        dataTransaksi.clear();
    }

    //Menghapus transaksi berdasarkan nomor nota
    public void hapusTransaksi(String noNota) {
        dao.hapusTransaksi(noNota);
    }

    //Mengambil detail transaksi berdasarkan nomor nota
    public List<Object[]> getDetailByNoNota(String noNota) {
        return dao.getDetailByNoNota(noNota);
    }
}
