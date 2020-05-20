package me.raviel.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CacheStorage {

    private Map<UUID,String> storage = new HashMap<>();
    private Set<String> namelist = new HashSet<>();

    protected void addPlayer(Player p){
        storage.put(p.getUniqueId(), p.getName());
        namelist.add(p.getName());
    }

    public String getPlayer(UUID uuid){
        return storage.get(uuid);
    }

    public String getPlayer(String uuid){
        return getPlayer(UUID.fromString(uuid));
    }

    public boolean hasUUID(String uuid){
        return hasUUID(UUID.fromString(uuid));
    }

    public boolean hasUUID(UUID uuid){
        return storage.containsKey(uuid);
    }

    public void update(UUID uuid, String name){
        storage.put(uuid, name);
    }

    public boolean hasPlayer(String name){
        return namelist.contains(name);
    }

    public boolean hasPlayer(Player p){
        return hasPlayer(p.getName());
    }

    protected Map<UUID,String> getStorage(){
        return storage;
    }

    public void setStorage(Map<UUID,String> s){
        this.storage.clear();
        this.storage = s;
        this.namelist.clear();
        s.forEach((uuid, name) -> this.namelist.add(name));
    }
}