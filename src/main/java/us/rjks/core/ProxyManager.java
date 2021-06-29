package us.rjks.core;

import net.md_5.bungee.api.plugin.Plugin;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import us.rjks.module.ModuleType;
import us.rjks.sql.MySQL;
import us.rjks.utils.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
    private ReportManager reportManager;

    private Plugin plugin;

    public ProxyManager(Plugin plugin) throws Exception {
        this.plugin = plugin;

        this.config = new Config(plugin, plugin.getDataFolder() + "/", "config", ModuleType.YML, false);
        this.config.loadTemplate("config.yml");
        this.config.loadFile();

        this.mySQL = new MySQL(plugin, plugin.getDataFolder() + "/", "mysql", ModuleType.YML, false);
        this.mySQL.loadTemplate("mysql.yml");
        this.mySQL.loadFile();
        if (config.getBoolean("database")) {
            try { this.mySQL.connect();} catch (Exception exception) {
                getPlugin().getLogger().log(Level.WARNING, "[DB] Could not connect to database due of an fatal error, check credentials: ");
                getPlugin().getLogger().log(Level.WARNING, exception.toString());
            }
        } else {
            getPlugin().getLogger().log(Level.INFO, "[Themis] Database disabled due configuration");
        }

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

        this.reportManager = new ReportManager(plugin, plugin.getDataFolder() + "/", "report", ModuleType.YML, false);
        this.reportManager.loadTemplate("report.yml");
        this.reportManager.loadFile();
        this.reportManager.loadReports();
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

    public String getUUIDFromMojang(String name) throws Exception{
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            @SuppressWarnings("deprecation")
            String uuid = IOUtils.toString(new URL(url));
            if(uuid.isEmpty()) return "Invalid name";
            return ((JSONObject) JSONValue.parseWithException(uuid)).get("id").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public MySQL getMySQL() {
        return mySQL;
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
