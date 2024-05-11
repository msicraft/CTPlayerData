package me.msicraft.ctplayerdata.PlayerData.CustomEvent;

import me.msicraft.ctplayerdata.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDataLoadEvent extends Event {

    private final static HandlerList handlers = new HandlerList();

    private final Player player;
    private final PlayerData playerData;

    public PlayerDataLoadEvent(Player player, PlayerData playerData) {
        this.player = player;
        this.playerData = playerData;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
