package us.rjks.cmd;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import us.rjks.core.Main;
import us.rjks.utils.BanManager;
import us.rjks.utils.Config;
import us.rjks.utils.Messages;
import us.rjks.utils.PunishReasons;

import java.util.ArrayList;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 27.06.2021 / 11:45
 *
 **************************************************************************/

public class Ban extends Command {

    public Ban(String command) {
        super(command);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Messages messages = Main.getProxyManager().getMessages();
        Config config = Main.getProxyManager().getConfig();
        BanManager banManager = Main.getProxyManager().getBanManager();

        if (!commandSender.hasPermission(config.getPermission("ban"))) {
            commandSender.sendMessage(messages.getSting("nopermission"));
            return;
        }

        if (args.length == 0) {
            for (String s : messages.getStringList("cmd-ban-help")) {
                if (s.contains("%ban-reason-line%")) {
                    ArrayList<PunishReasons> reasons;
                    if (commandSender instanceof ProxiedPlayer) {
                        reasons = banManager.getReasonsFromPlayer(((ProxiedPlayer) commandSender));
                    } else {
                        reasons = banManager.getReasons();
                    }
                    for (PunishReasons reason : reasons) {
                        String template = messages.getSting("ban-reason-line")
                                .replaceAll("%id%", reason.getId() + "")
                                .replaceAll("%duration%", reason.getDuration() + "")
                                .replaceAll("%permission%", reason.getPermission() + "")
                                .replaceAll("%points%", reason.getPoints() + "")
                                .replaceAll("%name%", reason.getName());
                        commandSender.sendMessage(template);
                    }
                } else {
                    commandSender.sendMessage(s);
                }
            }
        }
        if (args.length >= 2) {
            if (!args[0].isEmpty()) {
                try {
                    String uuid = Main.getProxyManager().getUUIDFromMojang(args[0]);
                    try {
                        if (banManager.getReasonByID(Integer.parseInt(args[1])) != null) {
                            PunishReasons reasons = banManager.getReasonByID(Integer.parseInt(args[1]));
                            if (commandSender.hasPermission(reasons.getPermission())) {
                                boolean ipBan = false;
                                if (args[2] != null && args[2].equals("-ip")) {
                                    ipBan = true;
                                }
                                //TODO: BAN UUID & KICK PLAYER WITH reason
                            } else {
                                commandSender.sendMessage(messages.getSting("cmd-ban-no-permission"));
                            }
                        }
                    } catch (Exception e) {
                        commandSender.sendMessage(messages.getSting("cmd-ban-invalid-ban-id"));
                    }
                } catch (Exception e) {
                    commandSender.sendMessage(messages.getSting("cmd-ban-invalid-username"));
                }
            }
        }
    }
}
