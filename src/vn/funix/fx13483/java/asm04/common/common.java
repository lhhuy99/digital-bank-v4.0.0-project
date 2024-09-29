package vn.funix.fx13483.java.asm04.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class common {
    public static DecimalFormat formatter = new DecimalFormat("###,###đ");

    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    public static String formatBalance(double balance) {
        return formatter.format(balance);
    }
}
