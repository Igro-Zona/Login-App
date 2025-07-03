package com.login;

import java.sql.*;

public class DatabaseController {

    private Connection connection;

    public DatabaseController() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:./settings.db");
    }

    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE NOT NULL,  password TEXT NOT NULL,  theme TEXT CHECK(theme IN ('light', 'dark'))), total INTEGER NOT NULL";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO users (username, password, theme, total) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, "light");
            statement.setString(4, "0");
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
            App.alertFactory.createDBAlert();
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

    public int updateTotal(String username) {
        String sql1 = "SELECT total FROM users WHERE username = ?";
        try (PreparedStatement statement1 = connection.prepareStatement(sql1)) {
            statement1.setString(1, username);
            ResultSet set = statement1.executeQuery();
            if (set.next()) {
                int total = set.getInt("total");
                total++;
                String sql2 = "UPDATE users SET total = ? WHERE username = ?";
                try (PreparedStatement statement2 = connection.prepareStatement(sql2)) {
                    statement2.setInt(1, total);
                    statement2.setString(2, username);
                    int updated = statement2.executeUpdate();
                    if (updated > 0) {
                        return total;
                    }
                }

            }
        } catch (SQLException e) {
        }
        return -999;
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
