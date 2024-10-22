package Logic.EventOperations;

import DAO.BaseDAO;

import java.sql.*;

public class ShowEvent extends BaseDAO {
    public ShowEvent(String dbName) {
        super(dbName);
    }
    public void showAllEvents() {
        try(Connection connection = database.getConnection()) {
            String query = "SELECT event_id, event_name, event_date, event_time FROM event";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int eventID = resultSet.getInt("event_id");
                String eventName = resultSet.getString("event_name");
                Date eventDate = resultSet.getDate("event_date");
                double eventTime = resultSet.getInt("event_time");
                String formattedEventTime = String.format("%.2f", eventTime);

                System.out.println(eventID+ "." + eventName + " " + " " + "Date: "+ eventDate + " " + "at: " +  formattedEventTime + " " + "o'clock");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
