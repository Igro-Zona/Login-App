# Login-App

A modern JavaFX desktop application for user login management, featuring:

- Modular Java 21 codebase
- SQLite database for persistent user storage
- Light and dark theme support
- User-friendly error dialogs
- Clean, maintainable Maven build with native packaging via jpackage

## Features

- User registration and login with password
- Theme switching (light/dark) per user
- Tracks total logins per user
- Error handling with JavaFX alerts
- Native Windows app packaging (with JavaFX runtime and SQLite driver)

## Build & Run

### Prerequisites

- Java 21+
- Maven 3.6+
- JavaFX SDK 21.0.7 (path configured in `pom.xml`)
- JDK 21 with jmods (path configured in `pom.xml`)

### Build and Package

mvn clean verify

### This will:

- Compile the code
- Copy dependencies and resources
- Package the app as a native image using jpackage
- Copy JavaFX DLLs and the SQLite database to the app folder

### Run (for development)

mvn javafx:run

## Usage

- On first run, enter a new username and password to register.
- Existing users can log in with their credentials.
- Switch between light and dark mode using the toggle button.
- The app tracks and displays the total number of logins for each user.

## Troubleshooting

- If you see a "Database Error" alert, ensure settings.db is present next to the executable.
- For fatal errors, check the integrity of your application files.
