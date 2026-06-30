package aplikasilaundry.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Koneksi {


    private static Connection mysqlkonek;
    
    public static Connection konek() {
        if (mysqlkonek == null) {
            try {
                // Menuju ke database mojosari_laundry yang ada di XAMPP
                String url = "jdbc:mysql://localhost:3306/mojosari_laundry"; 
                String user = "root"; // Username bawaan XAMPP
                String pass = "";     // Password bawaan XAMPP biasanya kosong
                
                // Mendaftarkan driver MySQL
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                // Menghubungkan program ke database
                mysqlkonek = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi ke Database Mojosari Laundry Berhasil!");
                
            } catch (SQLException e) {
                // Menampilkan pesan error jika XAMPP belum dinyalakan
                JOptionPane.showMessageDialog(null, "Koneksi Gagal! Pastikan Apache & MySQL di XAMPP sudah ON.\n" 
                        + e.getMessage(), "Error Koneksi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return mysqlkonek;
    }
    // KODE BARU UNTUK TES LANGSUNG:
    public static void main(String[] args) {
        konek();
    }
}

