package com.login;

import java.sql.*;

public class DatabaseController {

    private Connection connection;

    public DatabaseController() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:./settings.db");
    }

    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO users (username, password, theme) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, "light");
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public String getUserPassword(String username) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getString("password");
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public String getUserTheme(String username) {
        String sql = "SELECT theme FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getString("theme");
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean setUserTheme(String username, String newTheme) {
        String sql = "UPDATE users SET theme = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newTheme);
            statement.setString(2, username);
            int updated = statement.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

}
