
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

    //Menyimpan total harga
    private BigDecimal totalHarga;

    //Menyimpan status transaksi
    private String status;
    
    

    //Method untuk mengambil nomor nota
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
}
