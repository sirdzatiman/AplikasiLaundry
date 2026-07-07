package aplikasilaundry.model;

//Mengimpor BigDecimal
import java.math.BigDecimal;

//Mengimpor LocalDate
import java.time.LocalDate;

//Mengimpor LocalTime
import java.time.LocalTime;

//Class untuk menyimpan transaksi ke database
public class TransaksiSimpan {

    //ID transaksi
    private int idTransaksi;

    //Nomor nota
    private String noNota;

    //ID pengguna
    private int idPengguna;

    //ID pelanggan
    private int idPelanggan;

    //ID status
    private int idStatus;

    //Tanggal masuk
    private LocalDate tanggalMasuk;

    //Jam masuk
    private LocalTime jamMasuk;

    //Tanggal ambil
    private LocalDate tanggalAmbil;

    //Jam ambil
    private LocalTime jamAmbil;

    //Catatan
    private String catatan;

    //Total harga
    private BigDecimal totalHarga;

    //Mengambil ID transaksi
    public int getIdTransaksi() {
        return idTransaksi;
    }

    //Mengisi ID transaksi
    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    //Mengambil nomor nota
    public String getNoNota() {
        return noNota;
    }

    //Mengisi nomor nota
    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    //Mengambil ID pengguna
    public int getIdPengguna() {
        return idPengguna;
    }

    //Mengisi ID pengguna
    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    //Mengambil ID pelanggan
    public int getIdPelanggan() {
        return idPelanggan;
    }

    //Mengisi ID pelanggan
    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    //Mengambil ID status
    public int getIdStatus() {
        return idStatus;
    }

    //Mengisi ID status
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    //Mengambil tanggal masuk
    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    //Mengisi tanggal masuk
    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    //Mengambil jam masuk
    public LocalTime getJamMasuk() {
        return jamMasuk;
    }

    //Mengisi jam masuk
    public void setJamMasuk(LocalTime jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    //Mengambil tanggal ambil
    public LocalDate getTanggalAmbil() {
        return tanggalAmbil;
    }

    //Mengisi tanggal ambil
    public void setTanggalAmbil(LocalDate tanggalAmbil) {
        this.tanggalAmbil = tanggalAmbil;
    }

    //Mengambil jam ambil
    public LocalTime getJamAmbil() {
        return jamAmbil;
    }

    //Mengisi jam ambil
    public void setJamAmbil(LocalTime jamAmbil) {
        this.jamAmbil = jamAmbil;
    }

    //Mengambil catatan
    public String getCatatan() {
        return catatan;
    }

    //Mengisi catatan
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    //Mengambil total harga
    public BigDecimal getTotalHarga() {
        return totalHarga;
    }

    //Mengisi total harga
    public void setTotalHarga(BigDecimal totalHarga) {
        this.totalHarga = totalHarga;
    }

}