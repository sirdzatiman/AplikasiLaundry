package aplikasilaundry.controller;

//Mengimpor DAO untuk mengakses data laporan dari database
import aplikasilaundry.dao.LaporanDAO;
//Mengimpor model Laporan
import aplikasilaundry.model.Laporan;
//Mengimpor class Date untuk menerima parameter tanggal
import java.util.Date;
//Mengimpor List untuk menampung kumpulan data laporan
import java.util.List;

//Class yang menghubungkan tampilan dengan DAO pada fitur laporan pemasukan
public class LaporanController {
    //Objek DAO untuk mengelola proses pengambilan data laporan
    private LaporanDAO dao;

    //Constructor untuk menginisialisasi objek LaporanDAO
    public LaporanController() {
        dao = new LaporanDAO();
    }

    //Mengambil data rincian pemasukan berdasarkan periode dan tanggal
    public List<Laporan> getRincianPemasukan(String periode, Date tanggal) {
        return dao.getRincianPemasukan(periode, tanggal);
    }

    //Mengambil data ringkasan pemasukan berdasarkan periode dan tanggal
    public Laporan getRingkasan(String periode, Date tanggal) {
        return dao.getRingkasan(periode, tanggal);
    }

}
