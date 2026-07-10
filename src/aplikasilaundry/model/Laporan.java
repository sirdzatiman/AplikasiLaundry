/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.model;

import java.math.BigDecimal;

/**
 *
 * @author Sirdzat
 */
public class Laporan {

    private String namaLayanan;
    private String proses;
    private double jumlah;
    private BigDecimal subtotal;

    private int totalTransaksi;
    private BigDecimal totalPemasukan;
    private BigDecimal rataRata;
    private double totalItem;

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public String getProses() {
        return proses;
    }

    public void setProses(String proses) {
        this.proses = proses;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotalTransaksi() {
        return totalTransaksi;
    }

    public void setTotalTransaksi(int totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }

    public BigDecimal getTotalPemasukan() {
        return totalPemasukan;
    }

    public void setTotalPemasukan(BigDecimal totalPemasukan) {
        this.totalPemasukan = totalPemasukan;
    }

    public BigDecimal getRataRata() {
        return rataRata;
    }

    public void setRataRata(BigDecimal rataRata) {
        this.rataRata = rataRata;
    }

    public double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }
}
