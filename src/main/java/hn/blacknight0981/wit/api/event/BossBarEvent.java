package hn.blacknight0981.wit.api.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Set;

public class BossBarEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final BossBar bossBar;
    private final int distance;

    public BossBarEvent(Player player, BossBar bossBar, int distance) {
        this.player = player;
        this.bossBar = bossBar;
        this.distance = distance;
    }

    public void show(boolean show) {
        if (show) {
            bossBar.addPlayer(player);
        } else {
            bossBar.removePlayer(player);
        }
    }

    public Block getTargetBlock() {
        return player.getTargetBlock(null, distance);
    }

    public Block getTargetBlock(Set<Material> materials) {
        return player.getTargetBlock(materials, distance);
    }

    public Entity getTargetEntity() {
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        RayTraceResult result = player.getWorld().rayTraceEntities(
                eyeLocation,  // 射線的起點
                direction,    // 射線方向
                distance * 1.2,      // 射線檢測距離
                entity -> entity != player
        );

        if (result != null && result.getHitEntity() != null) {
            return result.getHitEntity();
        }

        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public int getDistance() {return distance;}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
