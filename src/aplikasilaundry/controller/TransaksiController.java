
package aplikasilaundry.controller;

//Mengimpor model data transaksi sementara
import aplikasilaundry.model.DataTransaksi;
import aplikasilaundry.model.ItemLaundry;
import aplikasilaundry.model.Transaksi;
//Mengimpor model pelanggan
import aplikasilaundry.model.Pelanggan;
//Mengimpor panel Semua
import aplikasilaundry.view.panel.Semua;

//Mengimpor DAO transaksi
import aplikasilaundry.dao.TransaksiDAO;

//Mengimpor model transaksi
import aplikasilaundry.model.Transaksi;

//Mengimpor collection
import java.util.List;

import aplikasilaundry.dao.LayananDAO;
import java.math.BigDecimal;

//Mengimpor DAO pelanggan
import aplikasilaundry.dao.PelangganDAO;

//Mengimpor DAO detail transaksi
import aplikasilaundry.dao.DetailTransaksiDAO;

//Mengimpor model transaksi simpan
import aplikasilaundry.model.TransaksiSimpan;

//Mengimpor model detail transaksi
import aplikasilaundry.model.DetailTransaksi;

//Mengimpor LocalDate
import java.time.LocalDate;

//Mengimpor LocalTime
import java.time.LocalTime;

//Class controller transaksi
public class TransaksiController {
    //Menyimpan objek DAO
    private TransaksiDAO dao;
    
    //DAO layanan
private LayananDAO layananDAO;

//DAO pelanggan
private PelangganDAO pelangganDAO;

//DAO detail transaksi
private DetailTransaksiDAO detailDAO;

    //Menyimpan data transaksi sementara
private DataTransaksi dataTransaksi;

    //Constructor
    public TransaksiController() {

       //Membuat DAO transaksi
dao = new TransaksiDAO();

//Membuat DAO pelanggan
pelangganDAO = new PelangganDAO();

//Membuat DAO detail transaksi
detailDAO = new DetailTransaksiDAO();

//Membuat DAO layanan
layananDAO = new LayananDAO();
        
    //Mengambil DataTransaksi yang sama
dataTransaksi = DataTransaksi.getInstance();

    }

    //Method untuk mengambil seluruh data transaksi
    public List<Transaksi> getAll() {

        //Mengembalikan data dari DAO
        return dao.getAll();

    }
    
    //Method mengambil transaksi berdasarkan nomor nota
public Transaksi getByNoNota(String noNota){

    //Mengembalikan data transaksi dari DAO
    return dao.getByNoNota(noNota);

}
//Method mengambil seluruh detail transaksi berdasarkan nomor nota
public List<DetailTransaksi> getDetailByNota(String noNota){

    //Mengembalikan daftar detail transaksi dari DAO
    return detailDAO.getDetailByNota(noNota);

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

//Method untuk menyimpan data pelanggan sementara
public void simpanPelanggan(String nama,
                            String hp,
                            String alamat,
                            String catatan){

    //Membuat objek pelanggan baru
    Pelanggan pelanggan = new Pelanggan();

    //Menyimpan nama pelanggan
    pelanggan.setNamaPelanggan(nama);

    //Menyimpan nomor HP pelanggan
    pelanggan.setNoHp(hp);

    //Menyimpan alamat pelanggan
    pelanggan.setAlamat(alamat);

    //Menyimpan catatan pelanggan
    pelanggan.setCatatan(catatan);

    //Menyimpan objek pelanggan ke transaksi sementara
    dataTransaksi.setPelanggan(pelanggan);

}
//Method untuk mengambil data pelanggan sementara
public Pelanggan getPelangganSementara() {

    //Mengembalikan data pelanggan
    return dataTransaksi.getPelanggan();

}
//Method mengambil harga layanan
public BigDecimal getHargaLayanan(String layanan,
                                  String proses){

    return layananDAO.getHarga(layanan, proses);
    
    
}
//Method mengambil ID layanan
public int getIdLayanan(String layanan,
                        String proses){

    //Mengembalikan ID layanan dari DAO
    return layananDAO.getIdLayanan(
            layanan,
            proses);

}
//Method menyimpan item laundry sementara
public void tambahItem(ItemLaundry item){

    //Menyimpan item ke transaksi sementara
    dataTransaksi.tambahItem(item);

}
//Method mengubah data transaksi
public void updateTransaksi(String noNota,
                            String namaPelanggan,
                            String status){

    //Mengirim data ke DAO
    dao.updateTransaksi(
            noNota,
            namaPelanggan,
            status);

}
//Method mengambil seluruh item laundry sementara
public List<ItemLaundry> getDaftarItem(){

    //Mengembalikan daftar item
    return dataTransaksi.getDaftarItem();

}
//Method menghitung total harga seluruh item laundry
private BigDecimal hitungTotalHarga(){

    //Nilai awal total
    BigDecimal total = BigDecimal.ZERO;

    //Mengulang seluruh item laundry
    for(ItemLaundry item : dataTransaksi.getDaftarItem()){

        //Menambahkan subtotal ke total
        total = total.add(item.getSubtotal());

    }

    //Mengembalikan total harga
    return total;

}
//Method menyimpan seluruh transaksi ke database
public void simpanTransaksi(){
    

    //Mengambil data pelanggan sementara
    Pelanggan pelanggan =
            dataTransaksi.getPelanggan();

    //Menyimpan pelanggan ke database
    int idPelanggan =
            pelangganDAO.simpan(pelanggan);

    //Membuat objek transaksi
    TransaksiSimpan transaksi =
            new TransaksiSimpan();

    //Mengisi id pelanggan
    transaksi.setIdPelanggan(idPelanggan);

    //Sementara menggunakan id pengguna = 1
    transaksi.setIdPengguna(1);

    //Status Laundry Masuk = 1
    transaksi.setIdStatus(1);

    //Tanggal masuk hari ini
    transaksi.setTanggalMasuk(LocalDate.now());

    //Jam masuk sekarang
    transaksi.setJamMasuk(LocalTime.now());

    //Catatan transaksi
    transaksi.setCatatan(
            pelanggan.getCatatan());

    //Total harga
    transaksi.setTotalHarga(
            hitungTotalHarga());

    //Menyimpan transaksi
    int idTransaksi =
            dao.simpan(transaksi);

    System.out.println("===== DEBUG =====");
System.out.println("Jumlah item : " + dataTransaksi.getDaftarItem().size());
    //Menyimpan seluruh item laundry
    for(ItemLaundry item :
            dataTransaksi.getDaftarItem()){
        System.out.println(
        "ID Layanan = " + item.getIdLayanan()
        + ", Layanan = " + item.getLayanan()
        + ", Qty = " + item.getQty());

        DetailTransaksi detail =
                new DetailTransaksi();
        

        detail.setIdTransaksi(idTransaksi);

        detail.setIdLayanan(
                item.getIdLayanan());

        detail.setQty(
                item.getQty());

        detail.setSubtotal(
                item.getSubtotal());

        detailDAO.simpan(detail);

    }

}
//Method untuk mengosongkan transaksi sementara
public void resetTransaksi(){

    //Mengosongkan seluruh data transaksi sementara
    dataTransaksi.clear();

}

}
