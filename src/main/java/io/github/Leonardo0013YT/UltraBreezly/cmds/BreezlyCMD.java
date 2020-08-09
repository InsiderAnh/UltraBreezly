package io.github.Leonardo0013YT.UltraBreezly.cmds;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.game.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BreezlyCMD implements CommandExecutor {

    private Main plugin;

    public BreezlyCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length < 1){
                sendHelp(sender);
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "join":
                    Game game = plugin.getGm().getGame();
                    if (game == null) {
                        p.sendMessage(plugin.getLang().get("messages.noGame"));
                        return true;
                    }
                    if (plugin.getGm().isPlayerGame(p)){
                        p.sendMessage(plugin.getLang().get("messages.noGame"));
                        return true;
                    }
                    game.addPlayer(p);
                    plugin.getGm().addPlayerGame(p, game.getId());
                    break;
                case "leave":

                    break;
                default:
                    sendHelp(sender);
                    break;
            }
        }
        return false;
    }

    private void sendHelp(CommandSender s){
        s.sendMessage("§7§m--------------------------------");
        s.sendMessage("§e/breezly create <name> <schematic> §a- §bCreate new arena.");
        s.sendMessage("§e/breezly setspawn §a- §bSet spawn on arena.");
        s.sendMessage("§e/breezly save §a- §bSave the arenas..");
        s.sendMessage("§7§m--------------------------------");
    }

}