package us.rjks.utils;

import net.md_5.bungee.api.plugin.Plugin;
import us.rjks.core.ProxyManager;
import us.rjks.module.BungeeModule;
import us.rjks.module.ModuleType;

import java.util.ArrayList;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 12:00
 *
 **************************************************************************/

public class Config extends BungeeModule {

    public Config(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public String getSting(String path) {
        if (getCache().containsKey(path)) return getCache().get(path).toString();
        Object ob = getConfig().get(path);
        if (ob != null) {
            getCache().put(path, ob);
            return ob.toString();
        } else {
            return "";
        }
    }

    public String getPermission(String path) {
        if (getCache().containsKey("permission." + path)) return getCache().get("permission." + path).toString();
        Object ob = getConfig().get("permission." + path);
        if (ob != null) {
            getCache().put("permission." + path, ob);
            return ob.toString();
        } else {
            return "";
        }
    }

    public Integer getInteger(String path) {
        if (getCache().containsKey(path)) return Integer.getInteger(getCache().get(path).toString());
        Object ob = getConfig().get(path);
        if (ob != null) {
            getCache().put(path, ob);
            return Integer.getInteger(ob.toString());
        } else {
            return 0;
        }
    }

    public Double getDouble(String path) {
        if (getCache().containsKey(path)) return Double.parseDouble(getCache().get(path).toString());
        Object ob = getConfig().get(path);
        if (ob != null) {
            getCache().put(path, ob);
            return Double.parseDouble(ob.toString());
        } else {
            return 0.0;
        }
    }

    public Boolean getBoolean(String path) {
        if (getCache().containsKey(path)) return Boolean.parseBoolean(getCache().get(path).toString());
        Object ob = getConfig().get(path);
        if (ob != null) {
            getCache().put(path, ob);
            return Boolean.parseBoolean(ob.toString());
        } else {
            return false;
        }
    }

    public ArrayList<String> getStringList(String path) {
        if (getCache().containsKey(path)) return (ArrayList<String>) getCache().get(path);
        Object ob = getConfig().get(path);
        if (ob != null) {
            getCache().put(path, ob);
            return (ArrayList<String>) getCache().get(path);
        } else {
            return new ArrayList<>();
        }
    }
}
