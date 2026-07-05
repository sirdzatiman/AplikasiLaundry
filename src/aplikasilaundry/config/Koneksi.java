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

        //Jika koneksi belum dibuat
        if (koneksi == null) {

            try {

                //URL database
                String url = "jdbc:mysql://localhost:3306/mojosari_laundry1";

                //Username database
                String user = "root";

                //Password database
                String password = "";

                //Membuat koneksi
                koneksi = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi database berhasil.");

            } catch (SQLException e) {

                System.out.println("Koneksi database gagal!");
                System.out.println(e.getMessage());

            }

        }

        //Mengembalikan koneksi
        return koneksi;
    }

}
