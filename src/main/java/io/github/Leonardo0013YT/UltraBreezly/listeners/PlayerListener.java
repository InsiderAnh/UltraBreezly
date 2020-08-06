package io.github.Leonardo0013YT.UltraBreezly.listeners;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.game.Game;
import io.github.Leonardo0013YT.UltraBreezly.game.GameBlock;
import io.github.Leonardo0013YT.UltraBreezly.game.GamePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    private Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location l = p.getLocation();
        if (l.getBlockY() > 10) return;
        if (plugin.getGm().isPlayerGame(p)){
            Game game = plugin.getGm().getGameByPlayer(p);
            GamePlayer gp = game.getPlayers().get(p);
            p.teleport(gp.getSpawn());
            p.sendMessage(plugin.getLang().get("messages.death"));
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if (plugin.getGm().isPlayerGame(p)) {
            Game game = plugin.getGm().getGameByPlayer(p);
            game.getBlocks().add(new GameBlock(e.getBlockPlaced().getLocation(), System.currentTimeMillis()));
        }
    }

}