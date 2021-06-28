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
 *  Erstellt: 27.06.2021 / 11:57
 *
 **************************************************************************/

public class BanManager extends BungeeModule {

    private boolean banPoints;
    private ArrayList<PunishReasons> reasons = new ArrayList<>();
    private HashMap<Integer, Integer> rules = new HashMap<>();

    public BanManager(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public void loadBanReasons() {
        for (String key : getConfig().getKeys()) {
            if (key.equals("ban-points-active")) banPoints = getConfig().getBoolean("ban-points-active");
            if (key.equals("reasons") && getConfig().get(key) instanceof Configuration) {
                Configuration configuration = getConfig().getSection(key);
                configuration.getKeys().forEach(s -> {
                    String path = key + "." + s + ".";
                    PunishReasons banReasons = new PunishReasons(
                            getConfig().getString(path + "full-name"),
                            getConfig().getString(path + "permission"),
                            getConfig().getInt(path + "id"),
                            getConfig().getInt(path + "duration"),
                            getConfig().getInt(path + "ban-points"));
                    reasons.add(banReasons);
                });
            }
            if (key.equals("ban-points") && banPoints) {
                Configuration configuration = getConfig().getSection(key);
                configuration.getKeys().forEach(s -> {
                    rules.put(Integer.parseInt(s), getConfig().getInt(key + "." + s));
                });
            }
        }
        getPlugin().getLogger().log(Level.INFO, "[Themis] Loaded " + reasons.size() + " ban reasons");
        getPlugin().getLogger().log(Level.INFO, "[Themis] Loaded " + rules.size() + " ban point rules");
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

    public ArrayList<PunishReasons> getReasons() {
        return reasons;
    }
}
