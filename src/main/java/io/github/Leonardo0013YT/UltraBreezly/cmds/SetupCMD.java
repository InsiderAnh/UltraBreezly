package io.github.Leonardo0013YT.UltraBreezly.cmds;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.setup.SetupGame;
import io.github.Leonardo0013YT.UltraBreezly.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCMD implements CommandExecutor {

    private Main plugin;

    public SetupCMD(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length < 1){
                sendHelp(sender);
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "create":
                    if (args.length < 3){
                        sendHelp(sender);
                        return true;
                    }
                    if (plugin.getSm().isSetup(p)){
                        p.sendMessage("§cYou already creating arena.");
                        return true;
                    }
                    String name = args[1];
                    String schematic = args[2];
                    if (!Utils.existsFile(schematic)){
                        p.sendMessage("§cThis schematic file not exists.");
                        return true;
                    }
                    World w = plugin.getWc().createEmptyWorld(name);
                    SetupGame sg = new SetupGame(plugin, name, schematic);
                    plugin.getSm().setSetup(p, sg);
                    w.getBlockAt(0, 74, 0).setType(Material.STONE);
                    Location l = new Location(w, 0, 75, 0);
                    p.teleport(l);
                    plugin.getGm().paste(l, schematic);
                    p.sendMessage("§aYou have created new §e" + name + "§a.");
                    break;
                case "setspawn":
                    if (!plugin.getSm().isSetup(p)){
                        p.sendMessage("§cYou dont stay creating arena.");
                        return true;
                    }
                    SetupGame sg1 = plugin.getSm().getSetup(p);
                    sg1.setSpawn(p.getLocation());
                    p.sendMessage("§aSpawn set §e" + Utils.getFormatedLocation(p.getLocation()));
                    break;
                case "save":
                    if (!plugin.getSm().isSetup(p)){
                        p.sendMessage("§cYou dont stay creating arena.");
                        return true;
                    }
                    SetupGame sg2 = plugin.getSm().getSetup(p);
                    sg2.save();
                    p.sendMessage("§aThe arena has been saved.");
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
        s.sendMessage("§e/breezlys create <name> <schematic> §a- §bCreate new arena.");
        s.sendMessage("§e/breezlys setspawn §a- §bSet spawn on arena.");
        s.sendMessage("§e/breezlys save §a- §bSave the arenas..");
        s.sendMessage("§7§m--------------------------------");
    }

}