-- Creation of database test
IF DB_ID('test') IS NOT NULL
    print 'Database already created'
ELSE
    CREATE DATABASE test
GO
-- Selecting database test
USE test
GO

-- Creation of table users
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'users')
    print 'Table users already created'
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

CREATE UNIQUE INDEX UQ_Email_Index ON users (email);

END

-- Creation of table groups
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_CATALOG = 'test'
             AND TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'groups')
        print 'Table groups already exists'
ELSE
CREATE TABLE groups (
                        id   uniqueidentifier NOT NULL,
                        group_name varchar(255),
                        code varchar(8),
                        CONSTRAINT pk_groups PRIMARY KEY (id)
)

-- Creation of table memberships
    IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_CATALOG = 'test'
             AND TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'memberships')
        print 'Table memberships already created'
ELSE
CREATE TABLE memberships
(
    user_id  uniqueidentifier NOT NULL,
    group_id uniqueidentifier NOT NULL,
    role     smallint,
    CONSTRAINT pk_memberships PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);

-- Creation of table events
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_CATALOG = 'test'
             AND TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'events')
        print 'Table events already created'
ELSE
    CREATE TABLE events
    (
        event_id uniqueidentifier NOT NULL,
        group_id uniqueidentifier NOT NULL,
        start_time DATETIME NOT NULL,
        end_time DATETIME NOT NULL,
        name VARCHAR(100) NOT NULL,
        description VARCHAR(200),
        creator UNIQUEIDENTIFIER NOT NULL,
        color VARCHAR(50),

        CONSTRAINT pk_events PRIMARY KEY (event_id),
        FOREIGN KEY (creator) REFERENCES users(id),
        FOREIGN KEY (group_id) REFERENCES groups(id)
    );


-- Creation of table activityType
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_CATALOG = 'test'
             AND TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'activity_types')
    print 'Table activity_types already created'
ELSE
    CREATE TABLE activity_types
    (
        id            uniqueidentifier NOT NULL,
        group_id      uniqueidentifier,
        activity_name varchar(50),
        state         bit,
        CONSTRAINT pk_activity_types PRIMARY KEY (id),
        FOREIGN KEY (group_id) REFERENCES groups(id)
    )


-- Creation of table propositions
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
           WHERE TABLE_CATALOG = 'test'
             AND TABLE_SCHEMA = 'dbo'
             AND TABLE_NAME = 'propositions')
    print 'Table propositions already created'
ELSE
    CREATE TABLE propositions
    (
        id             uniqueidentifier NOT NULL,
        user_id        uniqueidentifier,
        group_id       uniqueidentifier,
        activity_type  uniqueidentifier,
        start_datetime datetime,
        end_datetime   datetime,

        CONSTRAINT pk_propositions PRIMARY KEY (id),
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (group_id) REFERENCES groups(id),
        FOREIGN KEY (activity_type) REFERENCES activity_types(id),
    );


-- Insert into users table
IF((SELECT COUNT(*) FROM dbo.users) = 0)
INSERT INTO users (group_count, id, first_name, last_name, email, password, role)
VALUES
    (2, '4541ee81-b8f5-4ab6-b3b6-5f1f7072df0a', N'Michał', 'Skoryk', 'michal.skoryk@gmail.com', '$2a$10$YZqjzbcvcD5xnHtBuvZ.Quw4yI78YGccluMJzLf8JuLLxkXvQ3EdS','USER'), -- Michals2002
    (0, '9b991bc5-acb9-4a24-81ce-c3a952d15f6f', 'tester1first', 'tester1last', 'test1@tester.com', '$2a$10$WaYM4Euzk3Hjh9md24rwou.Y9F0y.89MH8.PFVwmb/nklQT0RpxKS','USER'); -- tester1

-- Insert into groups table
IF((SELECT COUNT(*) FROM dbo.groups) = 0)
INSERT INTO groups (id, group_name, code)
VALUES
    ('6957eaba-ffac-436a-aafb-08398474c440','Test','12345678'),
    ('37f82009-714a-499c-85e0-1ab8684b6463', 'Test1', '87654321');

-- Insert into memberships
IF((SELECT COUNT(*) FROM dbo.memberships) = 0)
INSERT INTO memberships (user_id, group_id, role)
VALUES
    ('4541ee81-b8f5-4ab6-b3b6-5f1f7072df0a','6957eaba-ffac-436a-aafb-08398474c440','0'),
    ('9b991bc5-acb9-4a24-81ce-c3a952d15f6f', '37f82009-714a-499c-85e0-1ab8684b6463', '1');

-- Insert into activity_types
IF((SELECT COUNT(*) FROM dbo.activity_types) = 0)
    INSERT INTO activity_types (id, group_id, activity_name, state)
    VALUES
        ('93432aa9-07a0-4e45-b87e-50cb257fb8bf','6957eaba-ffac-436a-aafb-08398474c440', 'TEST Activity', true);
