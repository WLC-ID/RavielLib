package me.raviel.lib;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.raviel.cache.CacheData;
import me.raviel.cache.CacheListener;
import me.raviel.cache.CacheStorage;
import me.raviel.cache.migration._1_InitialMigration;
import me.raviel.configuration.Config;
import me.raviel.database.DataMigrationManager;
import me.raviel.database.DatabaseConnector;
import me.raviel.database.MySQLConnector;
import me.raviel.database.SQLiteConnector;
import me.raviel.lib.Settings.Settings;
import me.raviel.plugin.PluginInfo;

public class RavielLib extends JavaPlugin {

    private static RavielLib instance;
    private static int libversion;
    private static Set<PluginInfo> rplugins = new HashSet<>();

    private CacheStorage cacheStorage;
    private Config config = new Config(this);
    
    private CacheData cacheData;
    private DataMigrationManager dataMigrationManager;

    private DatabaseConnector databaseConnector;

    @Override
    public void onEnable(){
        instance = this;
        parseLibVersion();
        Settings.setupConfig();

        this.cacheStorage = new CacheStorage();
        try {
            if (Settings.MYSQL_ENABLED.getBoolean()) {
                String hostname = Settings.MYSQL_HOSTNAME.getString();
                int port = Settings.MYSQL_PORT.getInt();
                String database = Settings.MYSQL_DATABASE.getString();
                String username = Settings.MYSQL_USERNAME.getString();
                String password = Settings.MYSQL_PASSWORD.getString();
                boolean useSSL = Settings.MYSQL_USE_SSL.getBoolean();

                this.databaseConnector = new MySQLConnector(this, hostname, port, database, username, password, useSSL);
                this.getLogger().info("UserCacheData connected using MySQL.");
            } else {
                this.databaseConnector = new SQLiteConnector(this);
                this.getLogger().info("UserCacheData connected using SQLite.");
            }
        } catch (Exception ex) {
            getLogger().severe("Fatal error trying to connect to database. Please make sure all your connection settings are correct and try again. Plugin has been disabled.");
            setEnabled(false);
            getLogger().severe("RavielLib Version Error, Disabling...");
            return;
        }

        this.cacheData = new CacheData(databaseConnector, instance);
        this.dataMigrationManager = new DataMigrationManager(this.databaseConnector, this.cacheData,
                new _1_InitialMigration());
        this.dataMigrationManager.runMigrations();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            this.cacheData.getData(data -> {
                cacheStorage.setStorage(data);
            });
        }, 20L);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new CacheListener(this), this);
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

        this.cacheData.bulkUpdateCache();
        this.databaseConnector.closeConnection();
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

    public CacheStorage getCacheStorage(){
        return cacheStorage;
    }

    public Config getCoreConfig(){
        return config;
    }

    public CacheData getCacheData(){
        return cacheData;
    }
    
}