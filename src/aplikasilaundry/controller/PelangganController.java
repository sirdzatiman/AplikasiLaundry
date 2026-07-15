package aplikasilaundry.controller;

//Mengimpor DAO pelanggan
import aplikasilaundry.dao.PelangganDAO;
//Mengimpor model Pelanggan
import aplikasilaundry.model.Pelanggan;
//Mengimpor List untuk menampung data pelanggan
import java.util.List;

//Class yang menghubungkan tampilan dengan DAO pada data pelanggan
public class PelangganController {
    //Objek DAO untuk mengelola data pelanggan
    private PelangganDAO dao;
    //Constructor untuk menginisialisasi objek PelangganDAO
    public PelangganController() {
        dao = new PelangganDAO();
    }

    //Mengambil seluruh data pelanggan dari database
    public List<Pelanggan> getAll() {
        return dao.getAll();
    }

}