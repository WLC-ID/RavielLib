package me.raviel.lib;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

import me.raviel.plugin.PluginInfo;

public class RavielLib extends JavaPlugin {

    private static RavielLib instance;
    private static int libversion;
    private static Set<PluginInfo> rplugins = new HashSet<>();

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
            setEnabled(false);
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

    public static Set<PluginInfo> getPlugins(){
        return rplugins;
    }

    public static void register(PluginInfo plugininfo){
        rplugins.add(plugininfo);
    }
    
}