package me.raviel.lib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class RavielLib extends JavaPlugin {

    @Getter
    private static RavielLib instance;
    @Getter
    private int libversion = -1;

    @Override
    public void onEnable(){
        instance = this;
        parseLibVersion();
        return;
    }

    public void parseLibVersion(){
        try{
            this.libversion = Integer.parseInt(getDescription().getVersion().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e){
            this.libversion = -1;
            instance = null;
            Bukkit.getPluginManager().disablePlugin(this);
            getLogger().warning("RavielLib Version Error, Disabling...");
        }
    }

    @Override
    public void onDisable(){
        instance = null;
        return;
    }
    
}