package Logic.UserOperations;

import Utillity.BCrypt;
import DAO.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateUser extends BaseDAO {
    public ValidateUser(String dbName) {
        super(dbName);
    }

    public boolean validateUser(String username, String password) {
        try (Connection connection = database.getConnection()) {
            String selectPasswordQuery = "SELECT user_password FROM users WHERE user_username = ?";
            PreparedStatement statement = connection.prepareStatement(selectPasswordQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("user_password");
                return BCrypt.checkpw(password, hashedPassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
