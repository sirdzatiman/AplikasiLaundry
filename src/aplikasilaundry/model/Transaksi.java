package aplikasilaundry.model;

//Mengimpor BigDecimal untuk menyimpan nilai uang
import java.math.BigDecimal;
//Class model untuk menampung data transaksi

public class Transaksi {
    
    //Menyimpan nomor nota
    private String noNota;
    //Menyimpan nama pelanggan
    private String namaPelanggan;
    //Menyimpan jam masuk laundry
    private String jamMasuk;
    //Menyimpan jam ambil laundry
    private String jamAmbil;
    //Menyimpan jenis layanan
    private String jenis;
    //Menyimpan jumlah item laundry
    private int jumlahItem;
    //Menyimpan total harga
    private BigDecimal totalHarga;
    //Menyimpan status transaksi
    private String status;
    //Tanggal masuk
    private String tanggalMasuk;

    //Tanggal ambil
    private String tanggalAmbil;

    //Nomor HP pelanggan
    private String noHp;

    //Alamat pelanggan
    private String alamat;

    //Method untuk memaca/ mengambil nomor nota
    public String getNoNota() {
        return noNota;
    }

    //Method untuk mengisi nomor nota
    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    //Method untuk mengambil nama pelanggan
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    //Method untuk mengisi nama pelanggan
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    //Method untuk mengambil jam masuk
    public String getJamMasuk() {
        return jamMasuk;
    }

    //Method untuk mengisi jam masuk
    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    //Method untuk mengambil jam ambil
    public String getJamAmbil() {
        return jamAmbil;
    }

    //Method untuk mengisi jam ambil
    public void setJamAmbil(String jamAmbil) {
        this.jamAmbil = jamAmbil;
    }

    //Method untuk mengambil jenis layanan
    public String getJenis() {
        return jenis;
    }

    //Method untuk mengisi jenis layanan
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    //Method untuk mengambil jumlah item laundry
    public int getJumlahItem() {
        return jumlahItem;
    }

//Method untuk mengisi jumlah item laundry
    public void setJumlahItem(int jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    //Method untuk mengambil total harga
    public BigDecimal getTotalHarga() {
        return totalHarga;
    }

    //Method untuk mengisi total harga
    public void setTotalHarga(BigDecimal totalHarga) {
        this.totalHarga = totalHarga;
    }

    //Method untuk mengambil status transaksi
    public String getStatus() {
        return status;
    }

    //Method untuk mengisi status transaksi
    public void setStatus(String status) {
        this.status = status;
    }
    //Method mengambil tanggal masuk

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    //Method mengambil tanggal ambil
    public String getTanggalAmbil() {
        return tanggalAmbil;
    }

    //Method mengisi tanggal ambil
    public void setTanggalAmbil(String tanggalAmbil) {
        this.tanggalAmbil = tanggalAmbil;
    }

    //Method mengisi tanggal masuk
    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    //Method mengambil nomor HP
    public String getNoHp() {
        return noHp;
    }

    //Method mengisi nomor HP
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    //Method mengambil alamat pelanggan
    public String getAlamat() {
        return alamat;
    }

    //Method mengisi alamat pelanggan
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
