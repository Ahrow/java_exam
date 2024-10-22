package Logic.UserOperations;

import Utillity.BCrypt;
import DAO.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUser extends BaseDAO {
    public CreateUser(String dbName) {
        super(dbName);
    }

    public void createNewUser(String username, String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        String insertUserQuery = "INSERT INTO users (user_username, user_password) VALUES (?, ?)";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertUserQuery);
            statement.setString(1, username);
            statement.setString(2,hashedPassword);
            int rowsUpdated = statement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
