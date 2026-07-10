/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Sirdzat
 */
public class FormatRupiah {

    private static final DecimalFormat formatter;

    static {

        DecimalFormatSymbols simbol = new DecimalFormatSymbols();

        simbol.setCurrencySymbol("Rp");
        simbol.setGroupingSeparator('.');
        simbol.setMonetaryDecimalSeparator(',');

        formatter = new DecimalFormat("'Rp ' #,##0", simbol);

    }

    public static String format(BigDecimal nominal) {

        if (nominal == null) {
            return "Rp0";
        }

        return formatter.format(nominal);

    }

    public static String format(double nominal) {
        return formatter.format(nominal);
    }
}
