package us.rjks.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import us.rjks.module.BungeeModule;
import us.rjks.module.ModuleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 11:58
 *
 **************************************************************************/

public class MuteManager extends BungeeModule {

    private boolean mutePoints;
    private ArrayList<PunishReasons> reasons = new ArrayList<>();
    private ArrayList<String> ignoredServers = new ArrayList<>();
    private HashMap<Integer, Integer> rules = new HashMap<>();

    public MuteManager(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public void loadMuteReasons() {
        for (String key : getConfig().getKeys()) {
            if (key.equals("mute-points-active")) mutePoints = getConfig().getBoolean("mute-points-active");
            if (key.equals("reasons") && getConfig().get(key) instanceof Configuration) {
                Configuration configuration = getConfig().getSection(key);
                configuration.getKeys().forEach(s -> {
                    PunishReasons banReasons = new PunishReasons(
                            configuration.getString("full-name"),
                            configuration.getString("permission"),
                            configuration.getInt("id"), configuration.getInt("duration"),
                            configuration.getInt("ban-points"));
                    reasons.add(banReasons);
                });
            }
            if (key.equals("mute-points") && mutePoints) {
                Configuration configuration = getConfig().getSection(key);
                configuration.getKeys().forEach(s -> {
                    rules.put(Integer.parseInt(s), configuration.getInt(s));
                });
            }
            if (key.equals("ignored-servers")) {
                getConfig().getStringList(key).forEach(s -> {
                    ignoredServers.add(s);
                });
            }
        }
        getPlugin().getLogger().log(Level.INFO, "[Themis] Loaded " + reasons.size() + " mute reasons");
        getPlugin().getLogger().log(Level.INFO, "[Themis] Loaded " + rules.size() + " mute point rules");
        getPlugin().getLogger().log(Level.INFO, "[Themis] Loaded " + ignoredServers.size() + " ignored servers");
    }

    public ArrayList<PunishReasons> getReasonsFromPlayer(ProxiedPlayer player) {
        ArrayList<PunishReasons> reasonsArrayList = new ArrayList<>();
        for (PunishReasons reason : reasons) {
            if (player.hasPermission(reason.getPermission())) {
                reasonsArrayList.add(reason);
            }
        }
        return reasonsArrayList;
    }

    public PunishReasons getReasonByID(int id) {
        for (PunishReasons reason : reasons) {
            if (reason.getId() == id) {
                return reason;
            }
        }
        return null;
    }
}
