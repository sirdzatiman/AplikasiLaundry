/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP 14s Ryzen
 */
public class Koneksi {

    private static Connection mysqlconfig;

    public static Connection konek() {

        try {
            // URL koneksi ke database: jdbc:mysql://[host]:[port]/[nama_database]
            String url = "jdbc:mysql://localhost:3306/mojosari_laundry1";

            // Username database
            String user = "root";

            // Password database
            String pass = "";

            // Membuka koneksi ke database dan menyimpannya di mysqlconfig
            mysqlconfig = DriverManager.getConnection(url, user, pass);

        } catch (SQLException SQLException) {
            // Menampilkan pesan error jika koneksi gagal
            System.err.println(SQLException.getMessage());
        }

        // Mengembalikan objek koneksi (bisa null jika gagal)
        return mysqlconfig;
    }

}
