package io.github.Leonardo0013YT.UltraBreezly.managers;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.utils.Utils;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class ConfigManager {

    private Location mainLobby;
    private int betweenIslands;
    private Main plugin;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload(){
        this.betweenIslands = plugin.getConfig().getInt("betweenIslands");
        this.mainLobby = Utils.getStringLocation(plugin.getConfig().getString("mainLobby"));
    }

}