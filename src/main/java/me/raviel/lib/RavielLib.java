package me.raviel.lib;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;
import me.raviel.lib.plugin.PluginInfo;
import me.raviel.lib.plugin.PluginVersion;

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
        libversion = PluginVersion.fromString(getDescription().getVersion());
        if (libversion == -1){
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