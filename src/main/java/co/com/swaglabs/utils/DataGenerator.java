package co.com.swaglabs.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    public static String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
