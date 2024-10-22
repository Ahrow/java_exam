package Utillity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static Utillity.ConfigLoader.loadProperties;

public class SQLFileRunner {
    public void runSQLFile(){
        Properties properties = loadProperties();
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");

        String fileName = "src/main/resources/01_initialize_databases.sql"; // Path to sql file
        String jdbcUrl = "jdbc:mysql://localhost/?user=" + dbUsername + "&password=" + dbPassword;
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                queryBuilder.append(line);
                if (line.endsWith(";")) {
                    String query = queryBuilder.toString();
                    statement.executeUpdate(query);
                    queryBuilder.setLength(0); // Reset the query builder
                }
            }

            System.out.println("SQL file execution completed.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}