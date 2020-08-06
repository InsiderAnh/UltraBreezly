package io.github.Leonardo0013YT.UltraBreezly.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.text.DecimalFormat;

public class Utils {

    private static DecimalFormat df = new DecimalFormat("##.#");

    public static String getFormatedLocation(Location loc) {
        if (loc == null) {
            return "§cNot set!";
        }
        return loc.getWorld().getName() + ", " + df.format(loc.getX()) + ", " + df.format(loc.getY()) + ", " + df.format(loc.getZ());
    }

    public static Location getStringLocation(String location) {
        if (location == null) return null;
        String[] l = location.split(";");
        if (l.length < 6) return null;
        World world = Bukkit.getWorld(l[0]);
        double x = Double.parseDouble(l[1]);
        double y = Double.parseDouble(l[2]);
        double z = Double.parseDouble(l[3]);
        float yaw = Float.parseFloat(l[4]);
        float pitch = Float.parseFloat(l[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String getLocationString(Location loc) {
        return loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
    }

    public static boolean existsFile(String schematic) {
        String s = schematic + ".schematic";
        String s2 = schematic + ".schem";
        File file = new File(Bukkit.getWorldContainer() + "/plugins/WorldEdit/schematics", s);
        File file2 = new File(Bukkit.getWorldContainer() + "/plugins/FastAsyncWorldEdit/schematics", s2);
        /*if (Main.get().getVc().is1_13to16()) {
            return file2.exists();
        }*/
        return file.exists();
    }

}