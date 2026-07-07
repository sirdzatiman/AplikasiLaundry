
package aplikasilaundry.model;
//Class untuk menyimpan data pelanggan
public class Pelanggan {
    //Menyimpan ID pelanggan
    private int idPelanggan;

    //Menyimpan nama pelanggan
    private String namaPelanggan;

    //Menyimpan nomor HP pelanggan
    private String noHp;

    //Menyimpan alamat pelanggan
    private String alamat;
    
    private String catatan;

    //Mengambil ID pelanggan
    public int getIdPelanggan() {
        return idPelanggan;
    }

    //Mengisi ID pelanggan
    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    //Mengambil nama pelanggan
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    //Mengisi nama pelanggan
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    //Mengambil nomor HP
    public String getNoHp() {
        return noHp;
    }

    //Mengisi nomor HP
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    //Mengambil alamat
    public String getAlamat() {
        return alamat;
    }

    //Mengisi alamat
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    //Mengambil catatan
public String getCatatan() {
    return catatan;
}

//Mengisi catatan
public void setCatatan(String catatan) {
    this.catatan = catatan;
}
}

