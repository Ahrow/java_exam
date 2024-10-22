package UserHashExample.CreateUser;

import Utillity.BCrypt;
import Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CreateNewUser {
    private final Database database;

    public CreateNewUser() {
        this.database = new Database("userDB", "admin22", "1234");
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

