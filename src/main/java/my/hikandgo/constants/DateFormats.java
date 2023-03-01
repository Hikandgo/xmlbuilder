package my.hikandgo.constants;

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
}
