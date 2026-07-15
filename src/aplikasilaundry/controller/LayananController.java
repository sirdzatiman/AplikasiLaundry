package aplikasilaundry.controller;

//Mengimpor DAO layanan
import aplikasilaundry.dao.LayananDAO;
//Mengimpor model layanan
import aplikasilaundry.model.Layanan;
//Mengimpor collection
import java.util.List;

//Controller layanan
public class LayananController {
    //Membuat objek DAO
    private LayananDAO dao;
    //Constructor
    
    public LayananController() {
        //Membuat DAO
        dao = new LayananDAO();
    }

    //Method mengambil seluruh layanan
    public List<Layanan> getAll() {
        //Mengembalikan data dari DAO
        return dao.getAll();
    }

    //Method menyimpan layanan
    public boolean simpan(Layanan layanan) {
        //Mengirim data ke DAO
        return dao.simpan(layanan);
    }
    
    //Method mengubah layanan
    public boolean update(Layanan layanan) {
        //Mengirim data ke DAO
        return dao.update(layanan);
    }
    
    //Method menghapus layanan
    public boolean hapus(int idLayanan) {
        //Mengirim ID ke DAO
        return dao.hapus(idLayanan);
    }
}
