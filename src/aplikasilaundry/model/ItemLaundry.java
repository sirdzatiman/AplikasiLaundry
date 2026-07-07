
package aplikasilaundry.model;

import java.math.BigDecimal;

//Class untuk menyimpan satu item laundry
public class ItemLaundry {
   //Nama layanan
    private String layanan;
    
    //ID layanan dari database
private int idLayanan;
    
    //Menyimpan proses laundry
private String proses;

    //Jumlah (kg/biji)
    private double qty;

    //Harga per satuan
    private BigDecimal harga;

    //Subtotal
    private BigDecimal subtotal;

    //Mengambil layanan
    public String getLayanan() {
        return layanan;
    }

    //Mengisi layanan
    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }
    
    //Mengambil ID layanan
public int getIdLayanan() {
    return idLayanan;
}

//Mengisi ID layanan
public void setIdLayanan(int idLayanan) {
    this.idLayanan = idLayanan;
}

    //Mengambil qty
    public double getQty() {
        return qty;
    }

    //Mengisi qty
    public void setQty(double qty) {
        this.qty = qty;
    }

    //Mengambil harga
    public BigDecimal getHarga() {
        return harga;
    }

    //Mengisi harga
    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    //Mengambil subtotal
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    //Mengisi subtotal
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    } 
    //Mengambil proses
public String getProses() {
    return proses;
}

//Mengisi proses
public void setProses(String proses) {
    this.proses = proses;
}
}
