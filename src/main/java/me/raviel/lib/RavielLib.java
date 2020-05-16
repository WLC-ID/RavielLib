package me.raviel.lib;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class RavielLib extends JavaPlugin {

    @Getter
    private static RavielLib instance;

    @Override
    public void onEnable(){
        instance = this;
        return;
    }

    @Override
    public void onDisable(){
        instance = null;
        return;
    }
    
}