package me.raviel.lib.support;

import org.bukkit.plugin.RegisteredServiceProvider;

import me.raviel.lib.RavielLib;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Vault extends Support{

    private Economy econ = null;
    private Chat chat = null;
    private Permission permission = null;

    public Vault(RavielLib plugin){
        super(plugin, "Vault");
        if (!this.isAvailable()){
            return;
        }

        RegisteredServiceProvider<Economy> rspe = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        RegisteredServiceProvider<Chat> rspc = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        RegisteredServiceProvider<Permission> rspp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        if (rspe != null) {
            this.supportInstance = this;
            plugin.getLogger().info("Vault Economy successfully hooked.");
            econ = rspe.getProvider();
        } else {
            this.supportInstance = null;
        }
        
        if (rspc != null) {
            plugin.getLogger().info("Vault Chat successfully hooked.");
            chat = rspc.getProvider();
        }
        if (rspp != null) {
            plugin.getLogger().info("Vault Permission successfully hooked.");
            permission = rspp.getProvider();
        }
    }

    public Economy getEconomy() {
        return this.econ;
    }
    
    public Permission getPermissions() {
        return this.permission;
    }
    
    public Chat getChat() {
        return this.chat;
    }
    
}
