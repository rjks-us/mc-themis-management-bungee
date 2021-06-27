package us.rjks.utils;

import net.md_5.bungee.api.plugin.Plugin;
import us.rjks.module.BungeeModule;
import us.rjks.module.ModuleType;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 11:58
 *
 **************************************************************************/

public class MuteManager extends BungeeModule {

    public MuteManager(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public void loadMuteReasons() {

    }
}
