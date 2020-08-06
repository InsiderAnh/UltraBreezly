package io.github.Leonardo0013YT.UltraBreezly.game;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;

@Getter
public class GameBlock {

    private Location location;
    private long placed;
    private boolean removed;

    public GameBlock(Location location, long placed) {
        this.location = location;
        this.placed = placed;
        this.removed = false;
    }

    public void remove(){
        long passed = System.currentTimeMillis() - placed;
        if (passed >= 5 * 1000){
            location.getBlock().setType(Material.AIR);
            removed = true;
        }
    }

}