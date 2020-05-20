package me.raviel.cache;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.raviel.lib.RavielLib;

public class CacheListener implements Listener {

    private final RavielLib instance;

    public CacheListener(RavielLib lib){
        this.instance = lib;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        CacheStorage cacheStorage = instance.getCacheStorage();
        CacheData cacheData = instance.getCacheData();
        Player p = e.getPlayer();
        if (!cacheStorage.hasUUID(p.getUniqueId())){
            cacheData.createData(p);
            cacheStorage.addPlayer(p);
        } else if (!cacheStorage.getPlayer(p.getUniqueId()).equals(p.getName())){
            cacheData.updateData(p);
            cacheStorage.update(p.getUniqueId(), p.getName());
        }
    }
    
}