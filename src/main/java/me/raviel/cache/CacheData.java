package me.raviel.cache;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.entity.Player;

import me.raviel.database.DataManagerAbstract;
import me.raviel.database.DatabaseConnector;
import me.raviel.lib.RavielLib;

public class CacheData extends DataManagerAbstract {

	public CacheData(DatabaseConnector databaseConnector, RavielLib plugin) {
        super(databaseConnector, plugin);
	}

    public void bulkUpdateCache(){
        CacheStorage cs = ((RavielLib) this.plugin).getCacheStorage();
        this.databaseConnector.connect(connection ->{
            String updateData = "UPDATE usercachedata SET name = ? WHERE uuid = ?";
            try (PreparedStatement stm = connection.prepareStatement(updateData)){
                for (UUID uuid : cs.getStorage().keySet()){
                    if (uuid == null) continue;
                    if (cs.getPlayer(uuid) == null) continue;
                    
                    stm.setString(1, cs.getPlayer(uuid));
                    stm.setString(2, uuid.toString());

                    stm.addBatch();
                }

                stm.executeBatch();
            }
        });
    }

    public void getData(Consumer<Map<UUID,String>> callback){

        this.async(() -> this.databaseConnector.connect(connection -> {
            String selectData = "SELECT * FROM usercachedata";
            Map<UUID,String> map = new HashMap<>();
            try (Statement stm = connection.createStatement()){
                ResultSet result = stm.executeQuery(selectData);
                while (result.next()){

                    map.put(UUID.fromString(result.getString("uuid")), result.getString("name"));
                }
                result.close();
            }

            this.sync(() -> callback.accept(map));
        })
        );

    }

    public void createData(Player player){
        if (player == null) return;

        this.async(() -> this.databaseConnector.connect(connection -> {
            String createData ="INSERT INTO usercachedata (uuid, name) VALUES (?, ?)";
            try (PreparedStatement stm = connection.prepareStatement(createData)) {

                stm.setString(1, player.getUniqueId().toString());
                stm.setString(2, player.getName());

                stm.executeUpdate();
            }
        }));

    }

    public void updateData(Player player){
        if (player == null) return;

        this.async(() -> this.databaseConnector.connect(connection -> {
            String updateData = "UPDATE usercachedata SET name = ? WHERE uuid = ?";
            try (PreparedStatement stm = connection.prepareStatement(updateData)) {

                stm.setString(1, player.getName());
                stm.setString(2, player.getUniqueId().toString());

                stm.executeUpdate();
            }
        }));
    }
    
}