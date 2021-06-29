package us.rjks.sql;

import com.google.gson.JsonParser;
import net.md_5.bungee.api.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import us.rjks.module.BungeeModule;
import us.rjks.module.ModuleType;
import us.rjks.utils.Config;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 13:27
 *
 **************************************************************************/

public class MySQL extends BungeeModule {

    private Connection connection;

    public MySQL(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public void connect() throws Exception {
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + getConfig().getString("host") +":" + getConfig().getString("port") + "/" + getConfig().getString("database") + "?autoReconnect=true", getConfig().getString("username"), getConfig().getString("password"));
            getPlugin().getLogger().log(Level.INFO, "[Themis] Connected successfully to SQL Database");
        }
    }

    public void loadCollections() {

    }

    public void disconnect() throws Exception {
        if (isConnected()) {
            connection.close();
            connection = null;
            getPlugin().getLogger().log(Level.INFO, "[Themis] Disconnected successfully to SQL Database");
        }
    }

    private void update(String query) {
        if(isConnected()) {
            try {
                connection.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private ResultSet getResult(String query) {
        if(isConnected()) {
            try {
                return connection.createStatement().executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void createTables() {
        update("CREATE TABLE IF NOT EXISTS global_ban (id VARCHAR(255), uuid VARCHAR(255), duration VARCHAR(255), reason VARCHAR(255), banner VARCHAR(255), ip VARCHAR(255), ipbanned VARCHAR(255), date VARCHAR(255))");
        update("CREATE TABLE IF NOT EXISTS global_mute (id VARCHAR(255), uuid VARCHAR(255), duration VARCHAR(255), reason VARCHAR(255), banner VARCHAR(255), date VARCHAR(255))");
        update("CREATE TABLE IF NOT EXISTS global_report (id VARCHAR(255), uuid VARCHAR(255), targetServer VARCHAR(255), duration VARCHAR(255), reason VARCHAR(255), reporter VARCHAR(255), reporterServer VARCHAR(255), date VARCHAR(255))");
    }
}