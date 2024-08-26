-- Tworzenie bazy danych
IF DB_ID('test') IS NOT NULL
    print 'database already created'
ELSE
    CREATE DATABASE test
GO
-- Użycie bazy danych test
USE test
GO

-- Tworzenie tabeli users
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'users')
    print 'table users already exists'
ELSE
    BEGIN
        CREATE TABLE users
        (
            id          uniqueidentifier NOT NULL,
            first_name  varchar(255),
            last_name   varchar(255),
            email       varchar(255)     NOT NULL,
            password    varchar(255),
            group_count int              NOT NULL,
            role        varchar(255),
            CONSTRAINT pk_users PRIMARY KEY (id)
        )

        ALTER TABLE users
            ADD CONSTRAINT uc_users_email UNIQUE (email)

        ALTER TABLE users
            ADD CONSTRAINT positive_group_count CHECK (group_count > -1)

        INSERT INTO users (group_count, id, first_name, last_name, email, password, role)
        VALUES
            (2, '4541ee81-b8f5-4ab6-b3b6-5f1f7072df0a', N'Michał', 'Skoryk', 'michal.skoryk@gmail.com', '$2a$10$YZqjzbcvcD5xnHtBuvZ.Quw4yI78YGccluMJzLf8JuLLxkXvQ3EdS','USER'), -- Michals2002
            (0, '9b991bc5-acb9-4a24-81ce-c3a952d15f6f', 'tester1first', 'tester1last', 'test1@tester.com', '$2a$10$WaYM4Euzk3Hjh9md24rwou.Y9F0y.89MH8.PFVwmb/nklQT0RpxKS','USER'); -- tester1
    END

print 'database ready for tests'

-- Tworzenie tabeli groups
-- CREATE TABLE groups (
--                         id INT PRIMARY KEY IDENTITY(1,1),
--                         group_name NVARCHAR(100) NOT NULL,
--                         image NVARCHAR(MAX),
--                         code CHAR(8) NOT NULL
-- );
-- GO
-- -- Tworzenie tabeli memberships
-- CREATE TABLE memberships (
--                              membership_id INT PRIMARY KEY IDENTITY(1,1),
--                              user_id INT NOT NULL,
--                              group_id INT NOT NULL,
--                              role NVARCHAR(50) NOT NULL,
--                              FOREIGN KEY (user_id) REFERENCES users(id),
--                              FOREIGN KEY (group_id) REFERENCES groups(id)
-- );
-- GO
-- -- Tworzenie tabeli events
-- CREATE TABLE events (
--                         id INT PRIMARY KEY IDENTITY(1,1),
--                         group_id INT NOT NULL,
--                         title NVARCHAR(100) NOT NULL,
--                         description NVARCHAR(MAX) NOT NULL,
--                         event_date DATE NOT NULL,
--                         event_time TIME NOT NULL,
--                         location NVARCHAR(100) NOT NULL,
--                         FOREIGN KEY (group_id) REFERENCES groups(id)
-- );
-- GO
-- -- Tworzenie tabeli attendance
-- CREATE TABLE attendance (
--                             id INT PRIMARY KEY IDENTITY(1,1),
--                             event_id INT NOT NULL,
--                             user_id INT NOT NULL,
--                             status NVARCHAR(50) NOT NULL,
--                             FOREIGN KEY (event_id) REFERENCES events(id),
--                             FOREIGN KEY (user_id) REFERENCES users(id)
-- );
-- GO
-- Wstawianie przykładowych danych do tabeli users

-- Wstawianie przykładowych danych do tabeli groups
-- INSERT INTO groups (group_name, image, code)
-- VALUES
--     ('Group A', NULL, 'AB12CD34'),
--     ('Group B', NULL, 'EF56GH78');
--
-- -- Wstawianie przykładowych danych do tabeli memberships
-- INSERT INTO memberships (user_id, group_id, role)
-- VALUES
--     (1, 1, 'Admin'),
--     (1, 2, 'Member'),
--     (2, 1, 'Member');
--
-- -- Wstawianie przykładowych danych do tabeli events
-- INSERT INTO events (group_id, title, description, event_date, event_time, location)
-- VALUES
--     (1, 'Event 1', 'Description for Event 1', '2024-08-01', '18:00:00', 'Location 1'),
--     (2, 'Event 2', 'Description for Event 2', '2024-08-15', '19:00:00', 'Location 2');
--
-- -- Wstawianie przykładowych danych do tabeli attendance
-- INSERT INTO attendance (event_id, user_id, status)
-- VALUES
--     (1, 1, 'present'),
--     (1, 2, 'absent'),
--     (2, 1, 'tentative');
-- GO