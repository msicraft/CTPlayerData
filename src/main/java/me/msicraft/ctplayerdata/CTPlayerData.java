package me.msicraft.ctplayerdata;

import me.msicraft.ctplayerdata.PlayerData.DataFile.PlayerDataFile;
import me.msicraft.ctplayerdata.PlayerData.Event.PlayerJoinAndQuitEvent;
import me.msicraft.ctplayerdata.PlayerData.PlayerData;
import me.msicraft.ctplayerdata.PlayerData.PlayerDataManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class CTPlayerData extends JavaPlugin {

    private static CTPlayerData plugin;

    public static CTPlayerData getPlugin() {
        return plugin;
    }

    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        plugin = this;
        createConfigFiles();

        File dataFolder = new File(getDataFolder() + File.separator + PlayerDataFile.FOLDER_NAME);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        playerDataManager = new PlayerDataManager(this);

        eventRegister();
    }

    @Override
    public void onDisable() {
        for (UUID uuid : playerDataManager.getUUIDSets()) {
            PlayerData playerData = playerDataManager.getPlayerData(uuid);
            playerData.savePlayerData();
        }
    }

    private void eventRegister() {
        getServer().getPluginManager().registerEvents(new PlayerJoinAndQuitEvent(this), this);
    }

    private void createConfigFiles() {
        File configf = new File(getDataFolder(), "config.yml");
        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

}
