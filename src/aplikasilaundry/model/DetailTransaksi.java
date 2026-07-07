
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

}
