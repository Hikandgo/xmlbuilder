package my.hikandgo.constants;

public enum Tegs407 {

    DATE_MSG ("<ДатаСооб>", "</ДатаСооб>");

    private final String tegOpen;
    private final String tegClose;

    Tegs407(String tegOpen, String tegClose) {
        this.tegOpen = tegOpen;
        this.tegClose = tegClose;
    }

    public String getTegOpen() {
        return tegOpen;
    }

    public String getTegClose() {
        return tegClose;
    }
}


