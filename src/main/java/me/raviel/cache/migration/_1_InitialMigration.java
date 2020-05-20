package me.raviel.cache.migration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import me.raviel.database.DataMigration;

public class _1_InitialMigration extends DataMigration {

	public _1_InitialMigration() {
		super(1);
	}

	@Override
	public void migrate(Connection connection, String tablePrefix) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE usercachedata (" +
                    "uuid VARCHAR(36) NOT NULL," +
                    "name TEXT NOT NULL " +
                    ")");
        }

		
	}
    
}