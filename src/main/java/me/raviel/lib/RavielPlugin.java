package me.raviel.lib;

import me.raviel.configuration.Config;
import me.raviel.locale.Locale;
import me.raviel.plugin.PluginInfo;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class RavielPlugin extends JavaPlugin {
    protected Locale locale;
    protected Config config = new Config(this);

    protected ConsoleCommandSender console = Bukkit.getConsoleSender();
    private boolean emergencyStop = false;

    public abstract void onPluginLoad();

    public abstract void onPluginEnable();

    public abstract void onPluginDisable();

    /**
     * Called after reloadConfig​() is called
     */
    public abstract void onConfigReload();

    /**
     * Any other plugin configuration files used by the plugin.
     * 
     * @return a list of Configs that are used in addition to the main config.
     */
    public abstract List<Config> getExtraConfig();

    @Override
    public FileConfiguration getConfig() {
        return config.getFileConfig();
    }

    public Config getCoreConfig() {
        return config;
    }

    @Override
    public void reloadConfig() {
        config.load();
        onConfigReload();
    }

    @Override
    public void saveConfig(){
        config.save();
    }

    @Override
    public final void onLoad() {
        try {
            onPluginLoad();
        } catch (final Throwable t) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "Unexpected error while loading " + getDescription().getName()
                    + " v" + getDescription().getVersion()
                    + " c" + RavielLib.getVersion()
                    + ": Disabling plugin!", t);
            emergencyStop = true;
        }
    }

    @Override
    public final void onEnable() {
        if(emergencyStop) {
            setEnabled(false);
            return;
        }
        Optional<PluginInfo> pl = RavielLib.getPlugins().stream().filter(
            plugin -> plugin.getPlugin().getName().equalsIgnoreCase(this.getName())).findFirst();
        if (pl.isPresent()){
            if (pl.get().getMinimumVersion() > RavielLib.getVersion()){
                Bukkit.getLogger().log(Level.SEVERE, "Plugin " + pl.get().getPlugin().getName() + " needs at least RavielLib v" + pl.get().getMinimumVersionString());
                setEnabled(false);
                return;
            }
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "Plugin " + this.getName() + " is not registered on RavielLib.");
            setEnabled(false);
            return;
        }

        console.sendMessage(" "); // blank line to separate chatter
        console.sendMessage(ChatColor.GREEN + "=============================");
        console.sendMessage(String.format("%s%s %s by %sRaviel !", ChatColor.GRAY.toString(),
                getDescription().getName(), getDescription().getVersion(), ChatColor.GOLD.toString()));
        console.sendMessage(String.format("%sAction: %s%s%s...", ChatColor.GRAY.toString(),
                ChatColor.GREEN.toString(), "Enabling", ChatColor.GRAY.toString()));

        try {
            locale = Locale.loadDefaultLocale(this, "en_US");
            // plugin setup
            onPluginEnable();
            if(emergencyStop) {
                console.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                console.sendMessage(" ");
                return;
            }
        } catch (final Throwable t) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "Unexpected error while loading " + getDescription().getName()
                    + " v" + getDescription().getVersion()
                    + " c" + RavielLib.getVersion()
                    + ": Disabling plugin!", t);
            emergencyStop();
            console.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            console.sendMessage(" ");
            return;
        }

        console.sendMessage(ChatColor.GREEN + "=============================");
        console.sendMessage(" "); // blank line to separate chatter
    }

    protected void emergencyStop() {
        emergencyStop = true;
        Bukkit.getPluginManager().disablePlugin(this);
    }

    @Override
    public final void onDisable() {
        if (emergencyStop) {
            return;
        }
        console.sendMessage(" "); // blank line to speparate chatter
        console.sendMessage(ChatColor.GREEN + "=============================");
        console.sendMessage(String.format("%s%s %s by %sRaviel !", ChatColor.GRAY.toString(),
                getDescription().getName(), getDescription().getVersion(), ChatColor.GOLD.toString()));
        console.sendMessage(String.format("%sAction: %s%s%s...", ChatColor.GRAY.toString(),
                ChatColor.RED.toString(), "Disabling", ChatColor.GRAY.toString()));
        onPluginDisable();
        console.sendMessage(ChatColor.GREEN + "=============================");
        console.sendMessage(" "); // blank line to speparate chatter
    }

    public ConsoleCommandSender getConsole() {
        return console;
    }

    public Locale getLocale() {
        return locale;
    }

    /**
     * Set the plugin's locale to a specific language
     *
     * @param localeName locale to use, eg "en_US"
     * @param reload optionally reload the loaded locale if the locale didn't
     * change
     * @return true if the locale exists and was loaded successfully
     */
    public boolean setLocale(final String localeName, final boolean reload) {
        if (locale != null && locale.getName().equals(localeName)) {
            return !reload || locale.reloadMessages();
        } else {
            final Locale l = Locale.loadLocale(this, localeName);
            if (l != null) {
                locale = l;
                return true;
            } else {
                return false;
            }
        }
    }
}