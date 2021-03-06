package com.weffle;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@ApplicationPath("restful")
public class WebApplication extends Application {
    /**
     * Database.
     */
    private static Database database;

    /**
     * Initialize method.
     */
    public WebApplication() {
        super();
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(
                    "/properties/database.properties"));
            database = Database.createFromProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set database.
     * @param database Database.
     */
    public static void setDatabase(Database database) {
        WebApplication.database = database;
    }

    /**
     * Get database.
     * @return Database.
     * @throws SQLException Failed to clone database.
     */
    public static Database getDatabase() throws SQLException {
        try {
            return (Database) database.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new SQLException("Could not get extension of the database.");
        }
    }
}
