package aplikasilaundry.controller;

//Mengimpor DAO proses
import aplikasilaundry.dao.ProsesDAO;
//Mengimpor model proses
import aplikasilaundry.model.Proses;
//Mengimpor collection
import java.util.List;

//Controller proses
public class ProsesController {
    //Membuat objek DAO
    private ProsesDAO dao;
    //Constructor
    public ProsesController() {
        //Membuat DAO
        dao = new ProsesDAO();
    }

    //Method mengambil seluruh proses
    public List<Proses> getAll() {
        //Mengembalikan data dari DAO
        return dao.getAll();
    }

    //Method menyimpan proses
    public boolean simpan(Proses proses) {
        System.out.println("MASUK CONTROLLER");
        //Mengirim data ke DAO
        return dao.simpan(proses);
    }
    
    //Method mengubah proses
    public boolean update(Proses proses) {
        //Mengirim data ke DAO
        return dao.update(proses);
    }
    
    //Method menghapus proses
    public boolean hapus(int idProses) {
        //Mengirim ID ke DAO
        return dao.hapus(idProses);

    }
}
