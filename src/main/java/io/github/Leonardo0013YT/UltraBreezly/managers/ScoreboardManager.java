package io.github.Leonardo0013YT.UltraBreezly.managers;

import io.github.Leonardo0013YT.UltraBreezly.Main;
import io.github.Leonardo0013YT.UltraBreezly.utils.ScoreboardUtil;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;

public class ScoreboardManager {

    Main plugin;
    private HashMap<Player, String> sb = new HashMap<>();
    private HashMap<Player, ScoreboardUtil> score = new HashMap<>();

    public ScoreboardManager(Main plugin) {
        this.plugin = plugin;
    }

    private void createMainBoard(Player p) {
        if (p == null || !p.isOnline()) {
            return;
        }
        sb.put(p, "main");
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        ScoreboardUtil scoreboardUtil = new ScoreboardUtil("main", "main");
        scoreboardUtil.setName(plugin.getLang().get(p, "scoreboards.main-title"));
        String titulo = plugin.getLang().get(p, "scoreboards.main");
        String[] title = titulo.split("\\n");
        for (int n = 1, n2 = title.length - 1; n < title.length + 1; ++n, --n2) {
            scoreboardUtil.lines(n, title[n2]);
        }
        scoreboardUtil.build(p);
        score.put(p, scoreboardUtil);
    }

    public void update(Player p) {
        if (p == null || !p.isOnline()) {
            return;
        }
        if (score.containsKey(p)) {
            ScoreboardUtil scoreboardUtil = score.get(p);
            if (sb.get(p).equals("main")) {
                String titulo = plugin.getLang().get(p, "scoreboards.main");
                String[] title = titulo.split("\\n");
                for (int n = 1, n2 = title.length - 1; n < title.length + 1; ++n, --n2) {
                    scoreboardUtil.lines(n, title[n2]);
                }
            } else {
                createMainBoard(p);
            }
        } else {
            createMainBoard(p);
        }
    }

    public void remove(Player p) {
        sb.remove(p);
        score.remove(p);
    }

}