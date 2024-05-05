package me.msicraft.ctplayerdata.PlayerData;

import me.msicraft.ctplayerdata.PlayerData.DataFile.PlayerDataFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerData {

    private final Player player;
    private final PlayerDataFile playerDataFile;

    private final Map<String, Object> tagDataMap = new HashMap<>(); //저장되는 데이터 [태그, 데이터] (Object 의 데이터 타입이 String(base64 data) 일경우에만 데이터 저장

    public PlayerData(Player player) {
        this.player = player;
        this.playerDataFile = new PlayerDataFile(player);

        FileConfiguration config = playerDataFile.getConfig();
        ConfigurationSection section = config.getConfigurationSection("TagData");
        if (section != null) {
            Set<String> sets = section.getKeys(false);
            for (String tag : sets) {
                tagDataMap.put(tag, config.getString("TagData." + tag));
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerDataFile getPlayerDataFile() {
        return playerDataFile;
    }

    public void savePlayerData() {
        for (String tag : tagDataMap.keySet()) {
            Object object = tagDataMap.get(tag);
            if (object instanceof String s) {
                playerDataFile.getConfig().set("TagData." + tag, s);
            }
        }
        playerDataFile.saveConfig();
    }

    public boolean hasTagData(String tag) {
        return tagDataMap.containsKey(tag);
    }

    public Object getTagData(String tag, Object defaultValue) {
        return tagDataMap.getOrDefault(tag, defaultValue);
    }

    public void setTagData(String tag, Object data) {
        tagDataMap.put(tag, data);
    }

    public void removeTagData(String tag) {
        tagDataMap.remove(tag);
    }

}
