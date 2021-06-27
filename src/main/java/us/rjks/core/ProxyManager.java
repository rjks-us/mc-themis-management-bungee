package us.rjks.core;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.md_5.bungee.api.plugin.Plugin;
import us.rjks.module.ModuleType;
import us.rjks.utils.BanManager;
import us.rjks.utils.Config;
import us.rjks.utils.Messages;
import us.rjks.utils.MuteManager;

import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 11:56
 *
 **************************************************************************/

public class ProxyManager {

    private Config config;
    private Messages messages;
    private MySQL mySQL;

    private BanManager banManager;
    private MuteManager muteManager;

    private Plugin plugin;

    public ProxyManager(Plugin plugin) throws Exception {
        this.plugin = plugin;

        this.mySQL = new MySQL(plugin, plugin.getDataFolder() + "/", "mysql", ModuleType.YML, false);
        this.mySQL.loadTemplate("mysql.yml");
        this.mySQL.loadFile();
        try { this.mySQL.connect();} catch (Exception exception) {getPlugin().getLogger().log(Level.WARNING, "[DB] Could not connect to database due of an fatal error, check credentials: "); getPlugin().getLogger().log(Level.WARNING, exception.toString());}

        this.config = new Config(plugin, plugin.getDataFolder() + "/", "config", ModuleType.YML, false);
        this.config.loadTemplate("config.yml");
        this.config.loadFile();

        this.messages = new Messages(plugin, plugin.getDataFolder() + "/", "messages", ModuleType.YML, false);
        this.messages.loadTemplate("messages.yml");
        this.messages.loadFile();

        this.banManager = new BanManager(plugin, plugin.getDataFolder() + "/", "ban", ModuleType.YML, false);
        this.banManager.loadTemplate("ban.yml");
        this.banManager.loadFile();
        this.banManager.loadBanReasons();

        this.muteManager = new MuteManager(plugin, plugin.getDataFolder() + "/", "mute", ModuleType.YML, false);
        this.muteManager.loadTemplate("mute.yml");
        this.muteManager.loadFile();
        this.muteManager.loadMuteReasons();
    }

    public void loadListeners() {

    }

    public void disablePlugin() {

    }

    public void reload() throws Exception {
        this.config.reloadFile();
        this.messages.reloadFile();
        this.banManager.reloadFile();
        this.muteManager.reloadFile();

    }

    public Config getConfig() {
        return config;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Messages getMessages() {
        return messages;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public MuteManager getMuteManager() {
        return muteManager;
    }
}
