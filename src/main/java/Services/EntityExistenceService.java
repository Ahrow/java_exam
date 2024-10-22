package Services;

import DAO.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityExistenceService extends BaseDAO {
    public EntityExistenceService(String dbName) {
        super(dbName);
    }

    public boolean checkIfEntityExist(String tableName, String columnName, Object value) {
        try (Connection connection = database.getConnection()) {
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            if (value instanceof String) {
                statement.setString(1, (String) value);
            } else if (value instanceof Integer) {
                statement.setInt(1, (Integer) value);
            } else {
                throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
            }

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
    public boolean checkIfUserExist(String username) {
        return checkIfEntityExist("users", "user_username", username);
    }
    public boolean checkIfStudentExist(String studentName) {
        return checkIfEntityExist("student", "student_name", studentName);
    }

    public boolean checkIfEventExist(Integer eventID) {
        return checkIfEntityExist("event", "event_id", eventID);
    }
    public boolean checkIfGuestExist(String guestName) {
        return checkIfEntityExist("participant", "participant_name", guestName);
    }
}
