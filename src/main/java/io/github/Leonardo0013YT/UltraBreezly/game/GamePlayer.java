package io.github.Leonardo0013YT.UltraBreezly.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;

public class GamePlayer {

    private Player p;
    private ItemStack[] inv, armor;
    private Collection<PotionEffect> effects;
    private Scoreboard scoreboard;
    private boolean allowFly, flying, reset;
    private float walkSpeed, flySpeed;
    private int food;
    private double maxHealth, health;
    private GameMode mode;
    @Setter@Getter
    private Location spawn;

    public GamePlayer(Player p) {
        this.p = p;
        this.inv = p.getInventory().getContents();
        this.armor = p.getInventory().getArmorContents();
        this.effects = p.getActivePotionEffects();
        this.allowFly = p.getAllowFlight();
        this.flying = p.isFlying();
        this.walkSpeed = p.getWalkSpeed();
        this.flySpeed = p.getFlySpeed();
        this.food = p.getFoodLevel();
        this.health = p.getHealth();
        this.maxHealth = p.getMaxHealth();
        this.mode = p.getGameMode();
        this.scoreboard = p.getScoreboard();
        this.reset = false;
    }

    public void reset() {
        if (reset) return;
        p.getActivePotionEffects().forEach(e -> p.removePotionEffect(e.getType()));
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().setContents(inv);
        p.getInventory().setArmorContents(armor);
        effects.forEach(e -> p.addPotionEffect(e));
        p.setAllowFlight(allowFly);
        p.setFlying(flying);
        p.setWalkSpeed(walkSpeed);
        p.setFlySpeed(flySpeed);
        p.setFoodLevel(food);
        p.setMaxHealth(maxHealth);
        p.setHealth(health);
        p.setFireTicks(0);
        p.setGameMode(mode);
        p.setScoreboard(scoreboard);
        p.updateInventory();
        reset = true;
    }

}