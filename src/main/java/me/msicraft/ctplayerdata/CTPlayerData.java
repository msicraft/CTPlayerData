package me.msicraft.ctplayerdata;

import me.msicraft.ctplayerdata.PlayerData.DataFile.PlayerDataFile;
import me.msicraft.ctplayerdata.PlayerData.Event.PlayerJoinAndQuitEvent;
import me.msicraft.ctplayerdata.PlayerData.PlayerData;
import me.msicraft.ctplayerdata.PlayerData.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CTPlayerData extends JavaPlugin {

    private static CTPlayerData plugin;

    public static CTPlayerData getPlugin() {
        return plugin;
    }

    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        plugin = this;

        File dataFolder = new File(getDataFolder() + File.separator + PlayerDataFile.FOLDER_NAME);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        playerDataManager = new PlayerDataManager(this);

        eventRegister();
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = playerDataManager.getPlayerData(player);
            playerData.savePlayerData();
        }
    }

    private void eventRegister() {
        getServer().getPluginManager().registerEvents(new PlayerJoinAndQuitEvent(this), this);
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

}
