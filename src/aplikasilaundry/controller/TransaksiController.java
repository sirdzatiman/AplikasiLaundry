
package aplikasilaundry.controller;

//Mengimpor panel Semua
import aplikasilaundry.view.panel.Semua;

//Mengimpor JTable
import javax.swing.table.DefaultTableModel;

//Mengimpor DAO transaksi
import aplikasilaundry.dao.TransaksiDAO;

//Mengimpor model transaksi
import aplikasilaundry.model.Transaksi;

//Mengimpor collection
import java.util.List;

//Class controller transaksi
public class TransaksiController {
    //Menyimpan objek DAO
    private TransaksiDAO dao;

    //Constructor
    public TransaksiController() {

        //Membuat objek DAO
        dao = new TransaksiDAO();

    }

    //Method untuk mengambil seluruh data transaksi
    public List<Transaksi> getAll() {

        //Mengembalikan data dari DAO
        return dao.getAll();

    }
    
    //Method untuk mengambil data laundry dengan status Selesai
public List<Transaksi> getSelesai() {

    //Mengembalikan data dari DAO
    return dao.getSelesai();

}
//Method untuk mengambil data laundry dengan status Baru Masuk
public List<Transaksi> getLaundryMasuk() {

    //Mengembalikan data dari DAO
    return dao.getLaundryMasuk();

}
//Method untuk mengambil data laundry dengan status Diproses
public List<Transaksi> getDiproses() {

    //Mengembalikan data dari DAO
    return dao.getDiproses();

}
//Method untuk mengambil data laundry dengan status Sudah Diambil
public List<Transaksi> getSudahDiambil() {

    //Mengembalikan data dari DAO
    return dao.getSudahDiambil();

}
}
