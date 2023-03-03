package my.hikandgo.constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    public static String currentDateGenerator(DateFormats dateFormat, int differenceDay) {
        differenceDay = 0;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, differenceDay);
        SimpleDateFormat dateForm = new SimpleDateFormat(dateFormat.getFormat());
        String newDate = dateForm.format(cal.getTime());
        return newDate;
    }}
