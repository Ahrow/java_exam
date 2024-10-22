package DAO;

import Database.Database;
import java.util.Properties;
import static Utillity.ConfigLoader.loadProperties;

public class BaseDAO {
    protected Database database;

    public BaseDAO(String dbName) {
        Properties properties = loadProperties();
        String dbUsername = properties.getProperty("db.administrator.username");
        String dbPassword = properties.getProperty("db.administrator.password");
        this.database = new Database(dbName, dbUsername, dbPassword);
    }
}
