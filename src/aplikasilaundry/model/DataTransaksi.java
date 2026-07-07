
package aplikasilaundry.model;

import java.util.ArrayList;
import java.util.List;
//Mengimpor model item laundry
import aplikasilaundry.model.ItemLaundry;

//Mengimpor BigDecimal
//import java.math.BigDecimal;

//Class untuk menyimpan data transaksi sementara
public class DataTransaksi {
    //Objek tunggal DataTransaksi
private static DataTransaksi instance;
    //Data pelanggan
    private Pelanggan pelanggan;

    //Daftar item laundry
    private List<DetailTransaksi> detailList = new ArrayList<>();
    
    //Menyimpan daftar item laundry
private List<ItemLaundry> daftarItem = new ArrayList<>();

//Method untuk mengambil satu-satunya objek DataTransaksi
public static DataTransaksi getInstance(){

    //Jika belum pernah dibuat
    if(instance == null){

        //Membuat objek baru
        instance = new DataTransaksi();

    }

    //Mengembalikan objek yang sama
    return instance;

}

    public Pelanggan getPelanggan() {
        return pelanggan;
    }
    //Mengambil seluruh item laundry
public List<ItemLaundry> getDaftarItem() {
    return daftarItem;
}

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public List<DetailTransaksi> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailTransaksi> detailList) {
        this.detailList = detailList;
    }
//Menambah item laundry
public void tambahItem(ItemLaundry item) {
    daftarItem.add(item);
}

}
