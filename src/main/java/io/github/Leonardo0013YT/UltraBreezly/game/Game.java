package io.github.Leonardo0013YT.UltraBreezly.game;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.utils.ItemUtils;
import io.github.Leonardo0013YT.UltraBreezly.utils.Utils;
import io.github.Leonardo0013YT.UltraBreezly.xseries.XMaterial;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class Game {

    private Main plugin;
    private int id;
    private String schematic, name;
    private Location spawn;
    private HashMap<Player, GamePlayer> players = new HashMap<>();
    private ArrayList<GameBlock> blocks = new ArrayList<>();

    public Game(Main plugin, String path, int id) {
        this.plugin = plugin;
        this.id = id;
        this.name = plugin.getConfig().getString(path + ".name");
        World w = plugin.getWc().createEmptyWorld(name);
        this.schematic = plugin.getConfig().getString(path + ".schematic");
        this.spawn = Utils.getStringLocation(plugin.getConfig().getString(path + ".spawn"));
        plugin.getGm().paste(new Location(w, 0, 75, 0), schematic);
    }

    public void addPlayer(Player p){
        Location s = getSpawn();
        p.teleport(s);
        GamePlayer g = new GamePlayer(p);
        players.put(p, g);
        g.setSpawn(s);
        p.getInventory().addItem(new ItemUtils(XMaterial.SANDSTONE, 64).build());
        sendGameMessage(plugin.getLang().get("messages.join").replace("<player>", p.getName()));
    }

    public void removePlayer(Player p){
        GamePlayer gp = players.get(p);
        gp.reset();
        players.remove(p);
        if (plugin.getCm().getMainLobby() != null){
            p.teleport(plugin.getCm().getMainLobby());
        }
    }

    public void sendGameMessage(String msg){
        for (Player p : players.keySet()){
            p.sendMessage(msg);
        }
    }

    public Location getSpawn(){
        int amount = players.size();
        Location now = spawn.clone();
        now.setX(amount * plugin.getCm().getBetweenIslands());
        return now;
    }

}