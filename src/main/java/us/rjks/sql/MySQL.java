package us.rjks.sql;

import net.md_5.bungee.api.plugin.Plugin;
import us.rjks.module.BungeeModule;
import us.rjks.module.ModuleType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void disconnect() throws Exception {
        if (isConnected()) {
            connection.close();
            connection = null;
            getPlugin().getLogger().log(Level.INFO, "[Themis] Disconnected successfully to SQL Database");
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void update(String query) {
        if(isConnected()) {
            try {
                connection.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResult(String query) {
        if(isConnected()) {
            try {
                return connection.createStatement().executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
