package com.login;

import java.io.IOException;

public class Theme {
    public String theme = "light";

    public void saveThemeToFile(String mode) {
        try (java.io.FileWriter writer = new java.io.FileWriter("theme.txt")) {
            writer.write(mode);
        } catch (IOException e) {
        }
    }

    public String loadModeFromFile() {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("theme.txt"))) {
            String line = reader.readLine();
            if (line != null && (line.equals("light") || line.equals("dark"))) {
                return line;
            }
        } catch (IOException e) {
        }
        return "light";
    }
}
