package io.github.Leonardo0013YT.UltraBreezly.managers;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import lombok.Getter;

@Getter
public class ConfigManager {

    private int betweenIslands;

    public ConfigManager(Main plugin) {
        this.betweenIslands = plugin.getConfig().getInt("betweenIslands");
    }

}