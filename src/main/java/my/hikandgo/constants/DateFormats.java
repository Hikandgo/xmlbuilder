package my.hikandgo.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum DateFormats {
    MAIN_DATE_FORMAT ("dd/MM/yyyy"),

    FILENAME_FORMAT ("yyyyMMdd"),
    DIRECTORY_NAME_FORMAT ("dd_MM_yyyy");

    private String format;


    DateFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static String currentDateGenerator(DateFormats dateFormat) {
        Date currentDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat(dateFormat.getFormat());
        String newDate = dateForm.format(currentDate);
        return newDate;
    }}
