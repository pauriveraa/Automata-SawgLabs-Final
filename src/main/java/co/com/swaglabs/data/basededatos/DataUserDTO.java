package co.com.swaglabs.data.basededatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataUserDTO {
    private final Connection connection;

    public DataUserDTO() {
        this.connection = new ConnectionMysql("localhost", "3306", "swaglabspau",
                "root", "").getMySQLConnection();
    }

    public Map<Integer, DataUser> getUsers() {

        Map<Integer, DataUser> userMap = new HashMap<>();

        try {
            String sqlQuery = "Select * From users";

            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                DataUser user = new DataUser(id, username, password);

                userMap.put(id, user);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return userMap;
    }
}
