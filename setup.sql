-- Tworzenie bazy danych
CREATE DATABASE test;
GO
-- Użycie bazy danych test
USE test;
GO
-- Tworzenie tabeli users
CREATE TABLE users (
                       id INT PRIMARY KEY IDENTITY(1,1),
                       first_name NVARCHAR(50) NOT NULL,
                       last_name NVARCHAR(50) NOT NULL,
                       email NVARCHAR(100) NOT NULL UNIQUE,
                       password NVARCHAR(100) NOT NULL,
                       group_count INT DEFAULT 0
);
GO
-- Tworzenie tabeli groups
CREATE TABLE groups (
                        id INT PRIMARY KEY IDENTITY(1,1),
                        group_name NVARCHAR(100) NOT NULL,
                        image NVARCHAR(MAX),
                        code CHAR(8) NOT NULL
);
GO
-- Tworzenie tabeli memberships
CREATE TABLE memberships (
                             membership_id INT PRIMARY KEY IDENTITY(1,1),
                             user_id INT NOT NULL,
                             group_id INT NOT NULL,
                             role NVARCHAR(50) NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (group_id) REFERENCES groups(id)
);
GO
-- Tworzenie tabeli events
CREATE TABLE events (
                        id INT PRIMARY KEY IDENTITY(1,1),
                        group_id INT NOT NULL,
                        title NVARCHAR(100) NOT NULL,
                        description NVARCHAR(MAX) NOT NULL,
                        event_date DATE NOT NULL,
                        event_time TIME NOT NULL,
                        location NVARCHAR(100) NOT NULL,
                        FOREIGN KEY (group_id) REFERENCES groups(id)
);
GO
-- Tworzenie tabeli attendance
CREATE TABLE attendance (
                            id INT PRIMARY KEY IDENTITY(1,1),
                            event_id INT NOT NULL,
                            user_id INT NOT NULL,
                            status NVARCHAR(50) NOT NULL,
                            FOREIGN KEY (event_id) REFERENCES events(id),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);
GO
-- Wstawianie przykładowych danych do tabeli users
INSERT INTO users (first_name, last_name, email, password, group_count)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'password123', 2),
    ('Jane', 'Smith', 'jane.smith@example.com', 'password123', 1);

-- Wstawianie przykładowych danych do tabeli groups
INSERT INTO groups (group_name, image, code)
VALUES
    ('Group A', NULL, 'AB12CD34'),
    ('Group B', NULL, 'EF56GH78');

-- Wstawianie przykładowych danych do tabeli memberships
INSERT INTO memberships (user_id, group_id, role)
VALUES
    (1, 1, 'Admin'),
    (1, 2, 'Member'),
    (2, 1, 'Member');

-- Wstawianie przykładowych danych do tabeli events
INSERT INTO events (group_id, title, description, event_date, event_time, location)
VALUES
    (1, 'Event 1', 'Description for Event 1', '2024-08-01', '18:00:00', 'Location 1'),
    (2, 'Event 2', 'Description for Event 2', '2024-08-15', '19:00:00', 'Location 2');

-- Wstawianie przykładowych danych do tabeli attendance
INSERT INTO attendance (event_id, user_id, status)
VALUES
    (1, 1, 'present'),
    (1, 2, 'absent'),
    (2, 1, 'tentative');
GO