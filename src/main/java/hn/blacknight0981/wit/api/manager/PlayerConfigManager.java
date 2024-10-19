package hn.blacknight0981.wit.api.manager;

import hn.blacknight0981.wit.api.utils.PlayerData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerConfigManager {
    private static final Map<UUID, File> playerFileMap = new HashMap<>();
    private static final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public static void put(UUID uuid, File file, PlayerData playerData) {
        playerFileMap.put(uuid, file);
        playerDataMap.put(uuid, playerData);
    }

    public static File getFile(UUID uuid) {
        return playerFileMap.get(uuid);
    }

    public static PlayerData getData(UUID uuid) {
        return playerDataMap.get(uuid);
    }
}
