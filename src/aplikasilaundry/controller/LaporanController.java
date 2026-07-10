/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.controller;

import aplikasilaundry.dao.LaporanDAO;
import aplikasilaundry.model.Laporan;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Sirdzat
 */
public class LaporanController {

    private LaporanDAO dao;

    public LaporanController() {
        dao = new LaporanDAO();
    }

    public List<Laporan> getRincianPemasukan(String periode, Date tanggal) {
        return dao.getRincianPemasukan(periode, tanggal);
    }

    public Laporan getRingkasan(String periode, Date tanggal) {
        return dao.getRingkasan(periode, tanggal);
    }
    
}
