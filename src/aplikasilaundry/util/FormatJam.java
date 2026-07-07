/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Sirdzat
 */
public class FormatJam {
     private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("HH:mm");

    public static String format(String jam) {

        if (jam == null || jam.isEmpty()) {
            return "";
        }

        try {
            LocalTime waktu = LocalTime.parse(jam);
            return waktu.format(FORMAT);
        } catch (Exception e) {
            return jam;
        }
    }
}
