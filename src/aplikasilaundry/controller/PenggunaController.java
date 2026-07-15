package aplikasilaundry.controller;

import aplikasilaundry.dao.PenggunaDAO;     //Mengimpor DAO pengguna
import aplikasilaundry.model.Pengguna;      //Mengimpor model pengguna
import java.util.List;                      //Mengimpor collection

//Class controller pengguna
public class PenggunaController {

    //Membuat objek DAO
    private PenggunaDAO dao;

    //Constructor
    public PenggunaController() {
        //Membuat DAO pengguna
        dao = new PenggunaDAO();
    }

    //Method mengambil seluruh data pengguna
    public List<Pengguna> getAll() {
        //Mengembalikan data dari DAO
        return dao.getAll();
    }
    
    //Method mengambil pengguna berdasarkan ID
    public Pengguna getById(int idPengguna) {
        //Mengembalikan data dari DAO
        return dao.getById(idPengguna);
    }
    
    //Method mengubah data pengguna
    public boolean update(Pengguna pengguna) {
        //Mengirim data ke DAO
        return dao.update(pengguna);
    }
    
    //Method menghapus pengguna
    public boolean hapus(int idPengguna) {
        //Mengirim ID ke DAO
        return dao.hapus(idPengguna);
    }
    
    //Method menyimpan pengguna baru
    public boolean simpan(Pengguna pengguna) {
        //Mengirim data ke DAO
        return dao.simpan(pengguna);

    }

}
