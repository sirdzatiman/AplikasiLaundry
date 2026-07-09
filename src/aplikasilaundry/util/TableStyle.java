/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
/**
 *
 * @author Sirdzat
 */
public class TableStyle {
    public static void style(JTable table) {

        //Font isi tabel
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        //Tinggi baris
        table.setRowHeight(38);

        //Background tabel
        table.setBackground(Color.WHITE);

        //Warna teks
        table.setForeground(Color.BLACK);

        // Warna saat baris dipilih
        table.setSelectionBackground(new Color(43, 87, 214));
        table.setSelectionForeground(Color.WHITE);

        //Grid
        table.setGridColor(new Color(230,230,230));

        //Jarak antar sel
        table.setIntercellSpacing(new Dimension(0,1));

        //Menampilkan garis horizontal
        table.setShowHorizontalLines(true);

        //Menyembunyikan garis vertikal
        table.setShowVerticalLines(false);
        
        
    }
}
