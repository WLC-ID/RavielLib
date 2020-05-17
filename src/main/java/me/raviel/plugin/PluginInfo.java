package me.raviel.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import me.raviel.compatibility.CompatibleMaterial;

public class PluginInfo {

    private final JavaPlugin plugin;
    private int minimumCoreVersion;
    private String minimumCoreVersionSTR;
    private final CompatibleMaterial icon;

    public PluginInfo(JavaPlugin p, String minimumLibVersion, CompatibleMaterial icon){
        this.plugin = p;
        this.minimumCoreVersionSTR = minimumLibVersion;
        try{
            this.minimumCoreVersion = Integer.parseInt(minimumLibVersion.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e){
            this.minimumCoreVersion = 100;
        }
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