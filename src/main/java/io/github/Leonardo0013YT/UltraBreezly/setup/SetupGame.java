package io.github.Leonardo0013YT.UltraBreezly.setup;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter@Setter
public class SetupGame {

    private Main plugin;
    private String name;
    private String schematic;
    private Location spawn;

    public SetupGame(Main plugin, String name, String schematic) {
        this.plugin = plugin;
        this.name = name;
        this.schematic = schematic;
    }

    public void save(){
        String path = "arenas." + name;
        plugin.getConfig().set(path + ".name", name);
        plugin.getConfig().set(path + ".schematic", schematic);
        plugin.getConfig().set(path + ".spawn", Utils.getLocationString(spawn));
        plugin.saveConfig();
    }

}