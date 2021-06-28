package us.rjks.utils;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 28.06.2021 / 09:16
 *
 **************************************************************************/

public class ReportReasons {

    private String name, type;
    private int id, delay, icon;

    public ReportReasons(String name, String type, int id, int delay, int icon) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.delay = delay;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDelay() {
        return delay;
    }

    public int getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }
}
