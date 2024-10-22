package Services;

import DAO.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityUpdateService extends BaseDAO {
    public EntityUpdateService(String dbName) {
        super(dbName);
    }
    public void updateValuesInColumns(String tableName, Map<String, Object> columnValueMap, String conditionColumn, Object conditionValue) {
        String updates = columnValueMap.keySet().stream()
                .map(column -> column + " = ?")
                .collect(Collectors.joining(", "));

        String updateQuery = "UPDATE " + tableName + " SET " + updates + " WHERE " + conditionColumn + " = ?";

        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);

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

            if (conditionValue instanceof String) {
                statement.setString(index, (String) conditionValue);
            } else if (conditionValue instanceof Integer) {
                statement.setInt(index, (Integer) conditionValue);
            } else {
                throw new IllegalArgumentException("Unsupported condition value type: " + conditionValue.getClass());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
