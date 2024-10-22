package GenericExistanceChecker;

import Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static Utillity.ConfigLoader.loadProperties;

public class EntityExistenceService {
    protected Database database;

    public EntityExistenceService(String dbName) {
        Properties properties = loadProperties();
        String dbUsername = properties.getProperty("db.administrator.username");
        String dbPassword = properties.getProperty("db.administrator.password");
        this.database = new Database(dbName, dbUsername, dbPassword);
    }

    public boolean checkIfEntityExists(String tableName, String columnName, String value) {
        try (Connection connection = database.getConnection()) {
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean checkIfUserExists(String username) {
        return checkIfEntityExists("users", "user_username", username);
    }

    public boolean checkIfStudentExists(String name) {
        return checkIfEntityExists("student", "student_name", name);
    }

    public boolean checkIfEventExists(String event) {
        return checkIfEntityExists("event", "event_name", event);
    }
}
