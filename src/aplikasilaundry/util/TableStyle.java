
package aplikasilaundry.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;


public class TableStyle {

    public static void TableStyle(JTable table) {

        // Font isi tabel
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Tinggi setiap baris
        table.setRowHeight(38);

        // Warna background tabel
        table.setBackground(Color.WHITE);

        // Warna tulisan
        table.setForeground(Color.BLACK);

        // Warna saat baris dipilih
        table.setSelectionBackground(new Color(43, 87, 214));
        table.setSelectionForeground(Color.WHITE);

        // Warna garis tabel
        table.setGridColor(new Color(225, 225, 225));

        // Menampilkan semua garis tabel
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        // Ketebalan garis
        table.setIntercellSpacing(new Dimension(1, 1));

        // Border luar tabel
        table.setBorder(javax.swing.BorderFactory.createLineBorder(
                new Color(225, 225, 225)));

        // Header tidak bisa dipindahkan
        table.getTableHeader().setReorderingAllowed(false);

        // Header tidak bisa diubah ukurannya
        table.getTableHeader().setResizingAllowed(false);

        // Tinggi header
        table.getTableHeader().setPreferredSize(
                new Dimension(
                        table.getTableHeader().getPreferredSize().width,
                        35));
    }
}
