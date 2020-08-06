package io.github.Leonardo0013YT.UltraBreezly.managers;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.world.World;
import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GameManager {

    private Main plugin;
    private HashMap<String, Schematic> cache = new HashMap<>();
    private String path;
    private HashMap<Integer, Game> game = new HashMap<>();
    private HashMap<Player, Integer> players = new HashMap<>();

    public GameManager(Main plugin) {
        this.plugin = plugin;
        this.path = Bukkit.getWorldContainer() + "/plugins/WorldEdit/schematics";
    }

    public void loadGames(){
        if (plugin.getConfig().isSet("arenas")){
            for (String s : plugin.getConfig().getConfigurationSection("arenas").getKeys(false)){
                int id = game.size();
                game.put(id, new Game(plugin, "arenas." + s, id));
            }
        }
    }

    public boolean isPlayerGame(Player p){
        return players.containsKey(p);
    }

    public void addPlayerGame(Player p, int id){
        players.put(p, id);
    }

    public void removePlayerGame(Player p){
        players.remove(p);
    }

    public Game getGameByPlayer(Player p){
        return game.get(players.get(p));
    }

    public Game getGame(){
        return game.get(0);
    }

    public void setGame(Game game){
        this.game.put(0, game);
    }

    public void paste(Location loc, String schematic){
        String s = schematic.replaceAll(".schematic", "");
        Vector v = new Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        World w = FaweAPI.getWorld(loc.getWorld().getName());
        if (!cache.containsKey(s)) {
            File file = new File(path, s + ".schematic");
            ClipboardFormat cf = ClipboardFormat.findByFile(file);
            try {
                if (cf != null) {
                    cache.put(s, cf.load(file));
                }
            } catch (IOException ignored) {
            }
        }
        if (cache.containsKey(s)){
            Schematic sh = cache.get(s);
            EditSession editSession = sh.paste(w, v, false, true, null);
            editSession.flushQueue();
        }
    }

}