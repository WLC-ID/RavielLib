package me.raviel.lib.support;

import me.raviel.lib.RavielLib;

public abstract class Support {

    protected Support supportInstance = null;

    public Support(RavielLib plugin, String pluginName){
        if (plugin.getServer().getPluginManager().getPlugin(pluginName) == null) return;

        this.supportInstance = this;
    }

    public boolean isAvailable(){
        return this.supportInstance != null;
    }
    
}
