/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.controller;

//Mengimpor DAO
import aplikasilaundry.dao.PelangganDAO;

//Mengimpor model
import aplikasilaundry.model.Pelanggan;

//Mengimpor collection
import java.util.List;

//Class controller pelanggan
public class PelangganController {
    //Menyimpan objek DAO
    private PelangganDAO dao;

    //Constructor
    public PelangganController() {

        //Membuat objek DAO
        dao = new PelangganDAO();

    }

    //Mengambil seluruh data pelanggan
    public List<Pelanggan> getAll() {

        //Mengembalikan data dari DAO
        return dao.getAll();

    }

}
