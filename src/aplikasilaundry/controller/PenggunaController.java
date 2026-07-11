package aplikasilaundry.controller;

//Mengimpor DAO pengguna
import aplikasilaundry.dao.PenggunaDAO;

//Mengimpor model pengguna
import aplikasilaundry.model.Pengguna;

//Mengimpor collection
import java.util.List;

//Class controller pengguna
public class PenggunaController {

    //Membuat objek DAO
    private PenggunaDAO dao;

    //Constructor
    public PenggunaController(){

        //Membuat DAO pengguna
        dao = new PenggunaDAO();

    }

    //Method mengambil seluruh data pengguna
    public List<Pengguna> getAll(){

        //Mengembalikan data dari DAO
        return dao.getAll();

    }
//Method menyimpan pengguna baru
public boolean simpan(Pengguna pengguna){

    //Mengirim data ke DAO
    return dao.simpan(pengguna);

}
}