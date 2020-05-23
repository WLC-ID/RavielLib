package me.raviel.lib.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import me.raviel.lib.compatibility.CompatibleMaterial;

public class PluginInfo {

    private final JavaPlugin plugin;
    private int minimumCoreVersion;
    private String minimumCoreVersionSTR;
    private final CompatibleMaterial icon;

    public PluginInfo(JavaPlugin p, String minimumLibVersion, CompatibleMaterial icon){
        this.plugin = p;
        this.minimumCoreVersionSTR = minimumLibVersion;
        this.minimumCoreVersion = (PluginVersion.fromString(minimumLibVersion) > 0) ? PluginVersion.fromString(minimumLibVersion) : 100;
        this.icon = icon;
    }

    public JavaPlugin getPlugin(){
        return plugin;
    }

    public int getMinimumVersion(){
        return minimumCoreVersion;
    }

    public String getMinimumVersionString(){
        return minimumCoreVersionSTR;
    }

    public CompatibleMaterial getIcon(){
        return icon;
    }
    
}