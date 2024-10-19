package hn.blacknight0981.wit;

import hn.blacknight0981.wit.utils.file.PlayerConfig;
import hn.blacknight0981.wit.listener.BossBarListener;
import hn.blacknight0981.wit.listener.PlayerJoinListener;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wit extends JavaPlugin {
    private static Wit instance;
    private static PlayerConfig playerConfig;


    @Override
    public void onEnable() {
        instance = this;
        playerConfig = new PlayerConfig(this);
        saveDefaultConfig();

        Server server = getServer();
        PluginManager pluginManager = server.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(this), this);

        // 原版顯示
        if (getConfig().getBoolean("vanilla")) pluginManager.registerEvents(new BossBarListener(this), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        // Plugin shutdown logic
    }

    public PlayerConfig getPlayerData() {
        return playerConfig;
    }

    public static Wit getInstance() {
        return instance;
    }
}
