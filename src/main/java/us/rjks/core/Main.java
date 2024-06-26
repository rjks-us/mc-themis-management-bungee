package us.rjks.core;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import us.rjks.cmd.Ban;

import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 11:44
 *
 **************************************************************************/

public class Main extends Plugin {

    private static ProxyManager proxyManager;
    private static Main instance;

    @Override
    public void onEnable() {
        this.instance = this;

        getLogger().log(Level.INFO, "[Themis] Server Management System starting");
        try {
            proxyManager = new ProxyManager(instance);
            proxyManager.loadListeners();

            /**
             * Commands are relying on the GameManager so it has to be registered after the GameManager
             * */

            ProxyServer.getInstance().getPluginManager().registerCommand(this, new Ban("ban"));
            //TODO: init listeners
        } catch (Exception e) {
            getLogger().log(Level.INFO, "[Themis] Fatal error while loading the plugin");
            e.printStackTrace();
            getLogger().log(Level.INFO, "[Themis] Plugin disabling due of the fatal error");
            proxyManager.disablePlugin();
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "[Themis] Server Management System disabled");
        super.onDisable();
    }

    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "[Themis] Server Management System booting up");
        super.onLoad();
    }

    public static Main getInstance() {
        return instance;
    }

    public static ProxyManager getProxyManager() {
        return proxyManager;
    }
}
