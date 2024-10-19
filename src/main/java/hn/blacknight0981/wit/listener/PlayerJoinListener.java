package hn.blacknight0981.wit.listener;

import hn.blacknight0981.wit.Wit;
import hn.blacknight0981.wit.api.event.BossBarEvent;
import hn.blacknight0981.wit.api.manager.PlayerConfigManager;
import hn.blacknight0981.wit.api.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {
    private final Wit plugin;

    public PlayerJoinListener(Wit plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getPlayerData().loadPlayerData(player.getUniqueId());

        BossBar bossBar = Bukkit.createBossBar("404", BarColor.RED, BarStyle.SOLID);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                // 玩家自行配置
                PlayerData playerData = PlayerConfigManager.getData(player.getUniqueId());
                if (!playerData.isEnable()) {
                    bossBar.removePlayer(player);
                    return;
                }

                int distance = plugin.getConfig().getInt("distance", 5);
                BossBarEvent bossBarEvent = new BossBarEvent(player, bossBar, distance);

                plugin.getServer().getPluginManager().callEvent(bossBarEvent);
            }
        }.runTaskTimer(Wit.getInstance(), 0L, 5L);
    }
}
