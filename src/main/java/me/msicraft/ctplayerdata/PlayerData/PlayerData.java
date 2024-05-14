package me.msicraft.ctplayerdata.PlayerData;

import me.msicraft.ctplayerdata.PlayerData.CustomEvent.PlayerDataLoadEvent;
import me.msicraft.ctplayerdata.PlayerData.DataFile.PlayerDataFile;
import me.msicraft.ctplayerdata.PlayerData.aCommon.TagData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerData {

    private final Player player;
    private final PlayerDataFile playerDataFile;

    private final Map<String, TagData> tagDataMap = new HashMap<>();

    public PlayerData(Player player) {
        this.player = player;
        this.playerDataFile = new PlayerDataFile(player);

        Bukkit.getPluginManager().callEvent(new PlayerDataLoadEvent(player, this));
    }

    public boolean loadTagData(String sectionPath) {
        ConfigurationSection section = playerDataFile.getConfig().getConfigurationSection(sectionPath);
        if (section != null) {
            Set<String> sets = section.getKeys(false);
            for (String key : sets) {
                String path = sectionPath + "." + key;
                Object object = playerDataFile.getConfig().get(path);
                tagDataMap.put(key, new TagData(sectionPath, object));
            }
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerDataFile getPlayerDataFile() {
        return playerDataFile;
    }

    public void savePlayerData() {
        for (String tag : tagDataMap.keySet()) {
            TagData tagData = tagDataMap.get(tag);
            String path = tagData.getSectionPath();
            if (path != null) {
                Object object = tagData.getValue();
                playerDataFile.getConfig().set(path, object);
            }
        }
        playerDataFile.saveConfig();
    }

    public boolean hasTagData(String tag) {
        return tagDataMap.containsKey(tag);
    }

    public TagData getTagData(String tag) {
        return tagDataMap.getOrDefault(tag, null);
    }

    public void setTagData(String tag, TagData tagData) {
        tagDataMap.put(tag, tagData);
    }

    public void removeTagData(String tag) {
        tagDataMap.remove(tag);
    }

}
