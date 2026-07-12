package aplikasilaundry.model;

import java.math.BigDecimal;

//Model tabel layanan
public class Layanan {

    //Field
    private int idLayanan;
    private String namaLayanan;
    private String pakaiProses;
    private String proses;
    private String satuan;
    private BigDecimal harga;
    private String keterangan;

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

    public String getPakaiProses() {
        return pakaiProses;
    }

    public void setPakaiProses(String pakaiProses) {
        this.pakaiProses = pakaiProses;
    }

    public String getProses() {
        return proses;
    }

    public void setProses(String proses) {
        this.proses = proses;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}