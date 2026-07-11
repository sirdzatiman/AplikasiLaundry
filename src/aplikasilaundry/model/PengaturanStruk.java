package aplikasilaundry.model;

//Class model pengaturan struk
public class PengaturanStruk {

    //ID pengaturan
    private int idStruk;

    //Nama bisnis
    private String namaBisnis;

    //Alamat laundry
    private String alamat;

    //Nomor HP
    private String noHp;

    //Jam operasional
    private String jamOperasional;

    //Layanan
private String layanan;

//Slogan / keterangan laundry
private String keterangan;

//Footer struk
private String footerStruk;
    //==========================
    // Getter dan Setter
    //==========================

    public int getIdStruk() {
        return idStruk;
    }

    public void setIdStruk(int idStruk) {
        this.idStruk = idStruk;
    }

    public String getNamaBisnis() {
        return namaBisnis;
    }

    public void setNamaBisnis(String namaBisnis) {
        this.namaBisnis = namaBisnis;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }

    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getFooterStruk() {
        return footerStruk;
    }

    public void setFooterStruk(String footerStruk) {
        this.footerStruk = footerStruk;
    }
//Mengambil slogan laundry
public String getKeterangan() {
    return keterangan;
}
//Mengubah slogan laundry
public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
}
}