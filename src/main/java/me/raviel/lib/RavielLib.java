package me.raviel.lib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RavielLib extends JavaPlugin {

    private static RavielLib instance;
    private static int libversion;

    @Override
    public void onEnable(){
        instance = this;
        parseLibVersion();
        return;
    }

    public void parseLibVersion(){
        try{
            libversion = Integer.parseInt(getDescription().getVersion().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e){
            libversion = -1;
            instance = null;
            Bukkit.getPluginManager().disablePlugin(this);
            getLogger().severe("RavielLib Version Error, Disabling...");
        }
    }

    @Override
    public void onDisable(){
        instance = null;
        return;
    }

    public static RavielLib getLib(){
        return instance;
    }

    public static int getVersion(){
        return libversion;
    }
    
}