package io.github.Leonardo0013YT.UltraBreezly.controllers;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WorldController {

    private Main plugin;

    public WorldController(Main plugin) {
        this.plugin = plugin;
    }

    public World createEmptyWorld(String name) {
        WorldCreator wc = new WorldCreator(name);
        wc.environment(World.Environment.NORMAL);
        wc.generateStructures(false);
        wc.generator(getChunkGenerator());
        World w = wc.createWorld();
        w.setDifficulty(Difficulty.NORMAL);
        w.setSpawnFlags(true, true);
        w.setPVP(true);
        w.setStorm(false);
        w.setThundering(false);
        w.setWeatherDuration(Integer.MAX_VALUE);
        w.setTicksPerAnimalSpawns(1);
        w.setTicksPerMonsterSpawns(1);
        w.setAutoSave(false);
        w.setGameRuleValue("mobGriefing", "true");
        w.setGameRuleValue("doFireTick", "false");
        w.setGameRuleValue("showDeathMessages", "false");
        w.setGameRuleValue("doDaylightCycle", "false");
        w.setSpawnLocation(0, 75, 0);
        w.getWorldBorder().reset();
        return w;
    }

    public World deleteWorld(World w, boolean recreate) {
        String name = w.getName();
        File path = new File(Bukkit.getWorldContainer(), w.getName());
        Bukkit.unloadWorld(w, false);
        deleteDirectory(path);
        if (recreate) {
            return createEmptyWorld(name);
        }
        return null;
    }

    public void deleteWorld(String name) {
        File path = new File(Bukkit.getWorldContainer(), name);
        deleteDirectory(path);
    }

    private void deleteDirectory(File source) {
        ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
        if (!source.exists()) return;
        for (File f : source.listFiles()){
            if (!ignore.contains(f.getName())) {
                if (f.isDirectory()) {
                    try {
                        FileUtils.deleteDirectory(f);
                    } catch (IOException ignored) {
                    }
                } else {
                    f.delete();
                }
            }
        }
    }

    public ChunkGenerator getChunkGenerator() {
        return new ChunkGenerator() {
            @Override
            public List<BlockPopulator> getDefaultPopulators(World world) {
                return Collections.emptyList();
            }

            @Override
            public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
                return Bukkit.createChunkData(world);
            }

            @Override
            public boolean canSpawn(World world, int x, int z) {
                return true;
            }

            public byte[] generate(World world, Random random, int x, int z) {
                return new byte[32768];
            }

            @Override
            public Location getFixedSpawnLocation(World world, Random random) {
                return new Location(world, 0.0D, 75, 0.0D);
            }
        };
    }

}