package me.msicraft.ctplayerdata.PlayerData.DataFile;

import me.msicraft.ctplayerdata.CTPlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class PlayerDataFile {

    public static final String FOLDER_NAME = "PlayerData";

    private final Player player;
    private File file;
    private FileConfiguration config;

    public PlayerDataFile(Player player) {
        this.player = player;
        String fileS = player.getUniqueId() + ".yml";
        this.file = new File(CTPlayerData.getPlugin().getDataFolder() + File.separator + FOLDER_NAME, fileS);
        if (!file.exists()) {
            createFile(player);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void createFile(Player player) {
        if(!this.file.exists()) {
            try {
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
                yamlConfiguration.set("Name", player.getName());
                yamlConfiguration.save(this.file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void reloadConfig() {
        if(this.config == null) {
            return;
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(CTPlayerData.getPlugin().getResource(this.file.getName()), StandardCharsets.UTF_8);
            if(defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            }
        }catch(NullPointerException ex) {
            //ex.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            getConfig().save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig(Player player) {
        String fileS = player.getUniqueId() + ".yml";
        if(this.file == null) {
            this.file = new File(CTPlayerData.getPlugin().getDataFolder() + File.separator + FOLDER_NAME, fileS);
        }
        if(!this.file.exists()) {
            CTPlayerData.getPlugin().saveResource(fileS, false);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public File getFile() {
        return file;
    }
}
