package hn.blacknight0981.wit.listener;

import hn.blacknight0981.wit.api.event.BossBarEvent;
import hn.blacknight0981.wit.api.utils.Materials;
import hn.blacknight0981.wit.utils.LanguageUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BossBarListener implements Listener {

    public BossBarListener(JavaPlugin plugin) {
        LanguageUtil.loadLanguage(
                Bukkit.getVersion(),
                plugin.getConfig().getString("language")
        );
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBossBar(BossBarEvent event) {
        BossBar bossBar = event.getBossBar();
        String key;
        String name;

        Block block = event.getTargetBlock();
        if (Materials.contains(block.getType())) {
            event.show(false);
            return;
        } else {
            key =  ("block.minecraft." + block.getType().name()).toLowerCase();
            name = LanguageUtil.getTranslation(key);
            bossBar.setColor(BarColor.GREEN);
            bossBar.setProgress(1);
        }

        Entity entity = event.getTargetEntity();
        if (entity != null) {
            key = ("entity.minecraft." + entity.getType().name()).toLowerCase();
            name = LanguageUtil.getTranslation(key);
            bossBar.setColor(BarColor.RED);
            bossBar.setProgress(1);
        }


        bossBar.setTitle(name);
        event.show(true);
    }
}
