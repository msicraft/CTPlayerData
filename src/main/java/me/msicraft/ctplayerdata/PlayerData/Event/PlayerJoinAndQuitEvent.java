package me.msicraft.ctplayerdata.PlayerData.Event;

import me.msicraft.ctplayerdata.CTPlayerData;
import me.msicraft.ctplayerdata.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinAndQuitEvent implements Listener {

    private final CTPlayerData plugin;

    public PlayerJoinAndQuitEvent(CTPlayerData plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) {
            plugin.getPlayerDataManager().registerPlayerData(player);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        playerData.savePlayerData();

        plugin.getPlayerDataManager().unregisterPlayerData(player);
    }

}
