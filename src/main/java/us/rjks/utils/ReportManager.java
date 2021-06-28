package us.rjks.utils;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import us.rjks.module.BungeeModule;
import us.rjks.module.ModuleType;

import java.util.ArrayList;
import java.util.logging.Level;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 28.06.2021 / 08:53
 *
 **************************************************************************/

public class ReportManager extends BungeeModule {

    private ArrayList<ReportReasons> reportReasons = new ArrayList<>();

    public ReportManager(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        super(plugin, directory, name, type, autoCreate);
    }

    public void loadReports() {
        for (String key : getConfig().getKeys()) {
            if (key.equals("reasons")) {
                Configuration configuration = getConfig().getSection(key);
                configuration.getKeys().forEach(s -> {
                    ReportReasons reasons = new ReportReasons(
                            configuration.getString("name"),
                            configuration.getString("type"),
                            configuration.getInt("id"),
                            configuration.getInt("delay"),
                            configuration.getInt("icon"));
                    reportReasons.add(reasons);
                });
            }
        }
        getPlugin().getLogger().log(Level.INFO, "[Themis] Loaded " + reportReasons.size() + " report reasons");
    }

    public ArrayList<ReportReasons> getReportReasons() {
        return reportReasons;
    }

    public ReportReasons getReasonByID(int id ) {
        for (ReportReasons reportReason : reportReasons) {
            if (reportReason.getId() == id) {
                return reportReason;
            }
        }
        return null;
    }
}
