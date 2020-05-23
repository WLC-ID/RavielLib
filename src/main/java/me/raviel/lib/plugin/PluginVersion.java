package me.raviel.lib.plugin;

import java.util.List;

import com.google.common.base.Splitter;

public class PluginVersion {

    public static int fromString(String ver){
        try{
            String v = ver.replaceAll("[^0-9.]", "");
            List<String> vers = Splitter.on(".").limit(3).splitToList(v);
            if (vers.size() == 1) return Integer.parseInt(vers.get(0));
            if (vers.size() == 2) return (Integer.parseInt(vers.get(0))*100)+Integer.parseInt(vers.get(1));
            if (vers.size() == 3) return (Integer.parseInt(vers.get(0))*10000)+(Integer.parseInt(vers.get(1))*100)+Integer.parseInt(vers.get(2));
        } catch (NumberFormatException e){ }
        return -1;
    }
    
}