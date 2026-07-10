/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.dao;

import aplikasilaundry.config.Koneksi;
import aplikasilaundry.model.Laporan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Sirdzat
 */
public class LaporanDAO {

    public List<Laporan> getRincianPemasukan() {

        List<Laporan> list = new ArrayList<>();

        String sql = """
        SELECT
            l.nama_layanan,
            l.proses,
            SUM(dt.qty) AS jumlah,
            SUM(dt.subtotal) AS subtotal
        FROM transaksi t
        INNER JOIN detail_transaksi dt
            ON t.id_transaksi = dt.id_transaksi
        INNER JOIN layanan l
            ON dt.id_layanan = l.id_layanan
        WHERE t.id_status = 4
        GROUP BY
            l.nama_layanan,
            l.proses
        ORDER BY
            l.nama_layanan,
            l.proses
        """;

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Laporan laporan = new Laporan();

                laporan.setNamaLayanan(rs.getString("nama_layanan"));
                laporan.setProses(rs.getString("proses"));
                laporan.setJumlah(rs.getDouble("jumlah"));
                laporan.setSubtotal(rs.getBigDecimal("subtotal"));

                list.add(laporan);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Laporan getRingkasan() {

        Laporan laporan = new Laporan();

        try {

            Connection conn = Koneksi.getKoneksi();

            // Ringkasan transaksi
            String sql1 = """
            SELECT
                COUNT(*) AS total_transaksi,
                SUM(total_harga) AS total_pemasukan,
                AVG(total_harga) AS rata_rata
            FROM transaksi
            WHERE id_status = 4
            """;

            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {

                laporan.setTotalTransaksi(rs1.getInt("total_transaksi"));
                laporan.setTotalPemasukan(rs1.getBigDecimal("total_pemasukan"));
                laporan.setRataRata(rs1.getBigDecimal("rata_rata"));

            }

            // Total item
            String sql2 = """
            SELECT
                SUM(dt.qty) AS total_item
            FROM detail_transaksi dt
            JOIN transaksi t
                ON dt.id_transaksi = t.id_transaksi
            WHERE t.id_status = 4
            """;

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {

                laporan.setTotalItem(rs2.getDouble("total_item"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return laporan;
    }

    public List<Laporan> getRincianPemasukan(String periode, java.util.Date tanggal) {

        if (tanggal == null) {
            return getRincianPemasukan();
        }

        List<Laporan> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("""
        SELECT
            l.nama_layanan,
            l.proses,
            SUM(dt.qty) AS jumlah,
            SUM(dt.subtotal) AS subtotal
        FROM transaksi t
        INNER JOIN detail_transaksi dt
            ON t.id_transaksi = dt.id_transaksi
        INNER JOIN layanan l
            ON dt.id_layanan = l.id_layanan
        WHERE t.id_status = 4
    """);

        if ("Harian".equals(periode)) {

            sql.append(" AND t.tanggal_ambil = ? ");

        } else {

            sql.append(" AND t.tanggal_ambil BETWEEN ? AND ? ");

        }

        sql.append("""
        GROUP BY
            l.nama_layanan,
            l.proses
        ORDER BY
            l.nama_layanan,
            l.proses
    """);

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            Calendar cal = Calendar.getInstance();
            cal.setTime(tanggal);

            if ("Harian".equals(periode)) {

                ps.setDate(1, new java.sql.Date(cal.getTimeInMillis()));

            } else if ("Mingguan".equals(periode)) {

                Calendar awalCal = (Calendar) cal.clone();

                int hari = awalCal.get(Calendar.DAY_OF_WEEK);

                int selisih;

                if (hari == Calendar.SUNDAY) {
                    selisih = -6;
                } else {
                    selisih = Calendar.MONDAY - hari;
                }

                awalCal.add(Calendar.DAY_OF_MONTH, selisih);

                java.sql.Date awal
                        = new java.sql.Date(awalCal.getTimeInMillis());

                Calendar akhirCal
                        = (Calendar) awalCal.clone();

                akhirCal.add(Calendar.DAY_OF_MONTH, 6);

                java.sql.Date akhir
                        = new java.sql.Date(akhirCal.getTimeInMillis());

                ps.setDate(1, awal);
                ps.setDate(2, akhir);

            } else if ("Bulanan".equals(periode)) {

                cal.set(Calendar.DAY_OF_MONTH, 1);
                java.sql.Date awal = new java.sql.Date(cal.getTimeInMillis());

                cal.set(Calendar.DAY_OF_MONTH,
                        cal.getActualMaximum(Calendar.DAY_OF_MONTH));

                java.sql.Date akhir = new java.sql.Date(cal.getTimeInMillis());

                ps.setDate(1, awal);
                ps.setDate(2, akhir);

            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Laporan laporan = new Laporan();

                laporan.setNamaLayanan(rs.getString("nama_layanan"));
                laporan.setProses(rs.getString("proses"));
                laporan.setJumlah(rs.getDouble("jumlah"));
                laporan.setSubtotal(rs.getBigDecimal("subtotal"));

                list.add(laporan);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Laporan getRingkasan(String periode, java.util.Date tanggal) {

        if (tanggal == null) {
            return getRingkasan();
        }

        Laporan laporan = new Laporan();

        StringBuilder sql = new StringBuilder();

        sql.append("""
        SELECT
            COUNT(*) AS total_transaksi,
            SUM(total_harga) AS total_pemasukan,
            AVG(total_harga) AS rata_rata
        FROM transaksi
        WHERE id_status = 4
    """);
        if ("Harian".equals(periode)) {

            sql.append(" AND tanggal_ambil = ? ");

        } else {

            sql.append(" AND tanggal_ambil BETWEEN ? AND ? ");

        }
        StringBuilder sqlItem = new StringBuilder();

        sqlItem.append("""
    SELECT
        SUM(dt.qty) AS total_item
    FROM detail_transaksi dt
    JOIN transaksi t
        ON dt.id_transaksi = t.id_transaksi
    WHERE t.id_status = 4
                       """);
        if ("Harian".equals(periode)) {

            sqlItem.append(" AND t.tanggal_ambil = ? ");

        } else {

            sqlItem.append(" AND t.tanggal_ambil BETWEEN ? AND ? ");

        }

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            PreparedStatement psItem = conn.prepareStatement(sqlItem.toString());

            Calendar cal = Calendar.getInstance();
            cal.setTime(tanggal);

            java.sql.Date awal;
            java.sql.Date akhir;

            if ("Harian".equals(periode)) {

                awal = new java.sql.Date(cal.getTimeInMillis());

                ps.setDate(1, awal);
                psItem.setDate(1, awal);

            } else if ("Mingguan".equals(periode)) {

                Calendar awalCal = (Calendar) cal.clone();

                int hari = awalCal.get(Calendar.DAY_OF_WEEK);

                int selisih;

                if (hari == Calendar.SUNDAY) {
                    selisih = -6;
                } else {
                    selisih = Calendar.MONDAY - hari;
                }

                awalCal.add(Calendar.DAY_OF_MONTH, selisih);

                awal = new java.sql.Date(awalCal.getTimeInMillis());

                Calendar akhirCal = (Calendar) awalCal.clone();
                akhirCal.add(Calendar.DAY_OF_MONTH, 6);

                akhir = new java.sql.Date(akhirCal.getTimeInMillis());

                ps.setDate(1, awal);
                ps.setDate(2, akhir);

                psItem.setDate(1, awal);
                psItem.setDate(2, akhir);

            } else {

                cal.set(Calendar.DAY_OF_MONTH, 1);
                awal = new java.sql.Date(cal.getTimeInMillis());

                cal.set(Calendar.DAY_OF_MONTH,
                        cal.getActualMaximum(Calendar.DAY_OF_MONTH));

                akhir = new java.sql.Date(cal.getTimeInMillis());

                ps.setDate(1, awal);
                ps.setDate(2, akhir);

                psItem.setDate(1, awal);
                psItem.setDate(2, akhir);

            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                laporan.setTotalTransaksi(rs.getInt("total_transaksi"));
                laporan.setTotalPemasukan(rs.getBigDecimal("total_pemasukan"));
                laporan.setRataRata(rs.getBigDecimal("rata_rata"));

            }

            ResultSet rsItem = psItem.executeQuery();

            if (rsItem.next()) {

                laporan.setTotalItem(rsItem.getDouble("total_item"));

            }

            rs.close();
            rsItem.close();

            ps.close();
            psItem.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return laporan;
    
    }
}
