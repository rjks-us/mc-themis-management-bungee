package us.rjks.listener;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 13:23
 *
 **************************************************************************/

public class PlayerConnectEvent implements Listener {

    private Plugin plugin;

    public PlayerConnectEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(PostLoginEvent event) {

    }

}
