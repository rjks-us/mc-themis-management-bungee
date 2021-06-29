package us.rjks.sql;

import org.json.simple.JSONObject;

import java.util.HashMap;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 29.06.2021 / 15:30
 *
 **************************************************************************/

public class Field {

    public HashMap<String, Object> object = new HashMap<>();

    public Field(String json) {

    }

    public Field(JSONObject jsonObject) {

    }

    public void addString(String key, String value) {
        this.object.put(key, value);
    }

    public void addInteger(String key, String value) {
        this.object.put(key, value);
    }

    public void addFloat(String key, float value) {
        this.object.put(key, value);
    }

    public void addDouble(String key, Double value) {
        this.object.put(key, value);
    }

    public void addStringArray(String key, String[] value) {
        this.object.put(key, value);
    }

    public void addIntArray(String key, int[] value) {
        this.object.put(key, value);
    }

    public void addField(String key, Field value) {
        this.object.put(key, value);
    }

    public void add(String key, Object object) {
        this.object.put(key, object);
    }

    public String getString(String key) {
        return object.get(key).toString();
    }

    public float getFloat(String key) {
        return Float.parseFloat(object.get(key).toString());
    }

    public Double getDouble(String key) {
        return Double.parseDouble(object.get(key).toString());
    }

    public String[] getStringArray(String key) {
        return (String[]) object.get(key);
    }

    public int[] getIntArray(String key) {
        return (int[]) object.get(key);
    }

    public Field getField(String key) {
        return (Field) object.get(key);
    }

    public Object get(String key) {
        return object.get(key);
    }

    public boolean exists(String key) {
        return object.containsKey(key);
    }

    public HashMap<String, Object> getObjects() {
        return object;
    }
}
