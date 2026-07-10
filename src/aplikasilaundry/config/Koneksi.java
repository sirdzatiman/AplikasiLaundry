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

    //Menyimpan objek koneksi
    private static Connection koneksi;

    //Method untuk mendapatkan koneksi database
    public static Connection getKoneksi() {

        try {

            if (koneksi == null || koneksi.isClosed()) {

                String url = "jdbc:mysql://localhost:3306/mojosari_laundry1";
                String user = "root";
                String password = "";

                koneksi = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi database berhasil.");
            }

        } catch (SQLException e) {

            System.out.println("Koneksi database gagal!");
            System.out.println(e.getMessage());

        }

        return koneksi;
    }

}
