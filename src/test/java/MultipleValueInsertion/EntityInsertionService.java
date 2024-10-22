package MultipleValueInsertion;

import DAO.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class EntityInsertionService extends BaseDAO {
    public EntityInsertionService(String dbName) {
        super(dbName);
    }

    public void insertValuesIntoColumns(String tableName, Map<String, Object> columnValueMap) {
        String columns = String.join(", ", columnValueMap.keySet());
        String placeholders = String.join(", ", new String[columnValueMap.size()]);
        String insertQuery = "INSERT IGNORE INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            int index = 1;
            for (Object value : columnValueMap.values()) {
                if (value instanceof String) {
                    statement.setString(index, (String) value);
                } else if (value instanceof Integer) {
                    statement.setInt(index, (Integer) value);
                } else {
                    throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
                }
                index++;
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

