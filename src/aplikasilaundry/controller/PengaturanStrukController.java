package aplikasilaundry.controller;

//Mengimpor DAO pengaturan
import aplikasilaundry.dao.PengaturanStrukDAO;

//Mengimpor model pengaturan
import aplikasilaundry.model.PengaturanStruk;

//Class controller pengaturan struk
public class PengaturanStrukController {

    //Membuat objek DAO
    private PengaturanStrukDAO dao;

    //Constructor
    public PengaturanStrukController(){

        //Membuat DAO
        dao = new PengaturanStrukDAO();

    }

    //Method mengambil data pengaturan
    public PengaturanStruk getPengaturan(){

        //Mengembalikan data dari DAO
        return dao.getPengaturan();

    }

    //Method memperbarui pengaturan
    public void updatePengaturan(
            PengaturanStruk pengaturan){

        //Mengirim data ke DAO
        dao.updatePengaturan(pengaturan);

    }

}