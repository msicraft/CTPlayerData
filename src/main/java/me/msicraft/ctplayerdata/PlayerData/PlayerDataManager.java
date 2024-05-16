package me.msicraft.ctplayerdata.PlayerData;

import me.msicraft.ctplayerdata.CTPlayerData;
import me.msicraft.ctplayerdata.PlayerData.CustomEvent.PlayerDataLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerDataManager {

    private final CTPlayerData plugin;
    private final Map<UUID, PlayerData> registeredPlayerDataMap = new HashMap<>();

    public PlayerDataManager(CTPlayerData plugin) {
        this.plugin = plugin;
    }

    public void registerPlayerData(Player player) {
        PlayerData playerData = new PlayerData(player);
        registeredPlayerDataMap.put(player.getUniqueId(), playerData);

        Bukkit.getPluginManager().callEvent(new PlayerDataLoadEvent(player, playerData));
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

    public Set<UUID> getUUIDSets() {
        return registeredPlayerDataMap.keySet();
    }

}
