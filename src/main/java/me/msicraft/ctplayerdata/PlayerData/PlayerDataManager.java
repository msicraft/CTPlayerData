package me.msicraft.ctplayerdata.PlayerData;

import me.msicraft.ctplayerdata.CTPlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    private final CTPlayerData plugin;
    private final Map<UUID, PlayerData> registeredPlayerDataMap = new HashMap<>();

    public PlayerDataManager(CTPlayerData plugin) {
        this.plugin = plugin;
    }

    public void registerPlayerData(Player player) {
        registeredPlayerDataMap.put(player.getUniqueId(), new PlayerData(player));
    }

    public void unregisterPlayerData(Player player) {
        registeredPlayerDataMap.remove(player.getUniqueId());
    }

    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId());
    }

    public PlayerData getPlayerData(UUID uuid) {
        return registeredPlayerDataMap.getOrDefault(uuid, null);
    }

}
