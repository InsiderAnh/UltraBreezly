package io.github.Leonardo0013YT.UltraBreezly.managers;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.setup.SetupGame;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SetupManager {

    private Main plugin;
    private HashMap<Player, SetupGame> setup = new HashMap<>();

    public SetupManager(Main plugin) {
        this.plugin = plugin;
    }

    public void setSetup(Player p, SetupGame sg){
        setup.put(p, sg);
    }

    public SetupGame getSetup(Player p){
        return setup.get(p);
    }

    public boolean isSetup(Player p){
        return setup.containsKey(p);
    }

    public void removeSetup(Player p){
        setup.remove(p);
    }

}