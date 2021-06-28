package us.rjks.utils;

import net.md_5.bungee.api.ChatColor;
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

public class Messages extends BungeeModule {

    public Messages(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public String getSting(String path) {
        if (getCache().containsKey(path)) return getCache().get(path).toString();
        String ob = ChatColor.translateAlternateColorCodes('&', getConfig().getString(path));
        if (ob != null) {
            getCache().put(path, ChatColor.translateAlternateColorCodes('&', ob));
            return ChatColor.translateAlternateColorCodes('&', ob);
        } else {
            return "";
        }
    }

    public ArrayList<String> getStringList(String path) {
        if (getCache().containsKey(path)) return (ArrayList<String>) getCache().get(path);
        ArrayList<String> ob = (ArrayList<String>) getConfig().get(path);
        if (ob != null) {
            getCache().put(path, ob);
            ArrayList<String> list = new ArrayList<>();
            for (String s : ob) {
                list.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            return list;
        } else {
            return new ArrayList<String>();
        }
    }
}
