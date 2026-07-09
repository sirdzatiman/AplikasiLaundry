
package aplikasilaundry.model;

import java.math.BigDecimal;


//Class untuk menyimpan data item laundry
public class DetailTransaksi {
    //ID transaksi
private int idTransaksi;

    //ID layanan
    private int idLayanan;

    //Nama layanan
    private String namaLayanan;
    
    //Nama proses laundry
private String namaProses;
private String satuan;

    //Jumlah Kg/Biji
    private double qty;

    //Harga
    private BigDecimal harga;

    //Subtotal
    private BigDecimal subtotal;
    
    //Mengambil ID transaksi
public int getIdTransaksi() {
    return idTransaksi;
}

//Menyimpan ID transaksi
public void setIdTransaksi(int idTransaksi) {
    this.idTransaksi = idTransaksi;
}

    public int getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(int idLayanan) {
        this.idLayanan = idLayanan;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
//Mengambil nama proses
public String getNamaProses() {
    return namaProses;
}

//Mengubah nama proses
public void setNamaProses(String namaProses) {
    this.namaProses = namaProses;
}
//Mengambil satuan layanan
public String getSatuan() {

    return satuan;

}

//Mengubah satuan layanan
public void setSatuan(String satuan) {

    this.satuan = satuan;

}
}
