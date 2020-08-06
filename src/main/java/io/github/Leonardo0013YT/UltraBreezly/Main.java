package io.github.Leonardo0013YT.UltraBreezly;

import io.github.Leonardo0013YT.UltraBreezly.cmds.BreezlyCMD;
import io.github.Leonardo0013YT.UltraBreezly.cmds.SetupCMD;
import io.github.Leonardo0013YT.UltraBreezly.config.Settings;
import io.github.Leonardo0013YT.UltraBreezly.controllers.WorldController;
import io.github.Leonardo0013YT.UltraBreezly.game.GameBlock;
import io.github.Leonardo0013YT.UltraBreezly.listeners.PlayerListener;
import io.github.Leonardo0013YT.UltraBreezly.managers.ConfigManager;
import io.github.Leonardo0013YT.UltraBreezly.managers.GameManager;
import io.github.Leonardo0013YT.UltraBreezly.managers.SetupManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class Main extends JavaPlugin {

    private Settings lang;
    private WorldController wc;
    private GameManager gm;
    private SetupManager sm;
    private ConfigManager cm;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        this.lang = new Settings(this, "lang", false, false);
        this.cm = new ConfigManager(this);
        this.wc = new WorldController(this);
        this.sm = new SetupManager(this);
        this.gm = new GameManager(this);
        gm.loadGames();
        getCommand("breezly").setExecutor(new BreezlyCMD(this));
        getCommand("breezlys").setExecutor(new SetupCMD(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        new BukkitRunnable(){
            @Override
            public void run() {
                getGm().getGame().getBlocks().removeIf(GameBlock::isRemoved);
                getGm().getGame().getBlocks().forEach(GameBlock::remove);
            }
        }.runTaskTimer(this, 5, 5);
    }

    @Override
    public void onDisable() {

    }
}