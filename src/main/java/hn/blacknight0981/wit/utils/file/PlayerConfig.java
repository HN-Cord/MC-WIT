package hn.blacknight0981.wit.utils.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hn.blacknight0981.wit.api.manager.PlayerConfigManager;
import hn.blacknight0981.wit.api.utils.PlayerData;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {
    private final File playerDataFolder;
    private final Gson gson;
    private final JavaPlugin plugin;

    public PlayerConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        playerDataFolder = new File(plugin.getDataFolder(), "playerdata");

        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }
    }

    public File getPlayerDataFolder() {
        return playerDataFolder;
    }

    public void loadPlayerData(UUID uuid) {
        File playerConfigFile = new File(playerDataFolder, uuid.toString() + ".json");
        PlayerData playerData;

        if (playerConfigFile.exists()) {
            try(FileReader reader = new FileReader(playerConfigFile)) {
                playerData = gson.fromJson(reader, PlayerData.class);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            playerData = new PlayerData();
        }

        savePlayerData(playerData, uuid);

        PlayerConfigManager.put(uuid, playerConfigFile, playerData);
    }

    public void savePlayerData(PlayerData playerData, UUID uuid) {
        File playerConfigFile = new File(playerDataFolder, uuid.toString() + ".json");

        try (FileWriter writer = new FileWriter(playerConfigFile)){
            gson.toJson(playerData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
