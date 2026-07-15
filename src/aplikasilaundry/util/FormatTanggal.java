/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author Sirdzat
 */
public class FormatTanggal {

    //Method mengubah format tanggal database menjadi dd-MM-yyyy
    public static String format(String tanggal) {
        try {

            java.sql.Date tgl = java.sql.Date.valueOf(tanggal);

            return new SimpleDateFormat("dd-MM-yyyy")
                    .format(tgl);

        } catch (Exception e) {

            return "-";

        }

    }
}
