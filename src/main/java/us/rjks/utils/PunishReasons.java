package us.rjks.utils;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 13:03
 *
 **************************************************************************/

public class PunishReasons {

    private String name, permission;
    private int id, duration, points;

    public PunishReasons(String name, String permission, int id, int duration, int points) {
        this.name = name;
        this.id = id;
        this.permission = permission;
        this.duration = duration;
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getPermission() {
        return permission;
    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }
}
