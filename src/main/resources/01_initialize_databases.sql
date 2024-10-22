CREATE DATABASE IF NOT EXISTS userDB;
CREATE DATABASE IF NOT EXISTS eventDB;
CREATE DATABASE IF NOT EXISTS universityDB;
CREATE USER IF NOT EXISTS 'admin22' IDENTIFIED BY '1234';
GRANT ALL ON userDB.* TO 'admin22';
GRANT ALL ON eventDB.* TO 'admin22';
GRANT ALL ON universityDB.* TO 'admin22';

CREATE TABLE IF NOT EXISTS userDB.users (user_id INT AUTO_INCREMENT PRIMARY KEY, user_username VARCHAR(100), user_password VARCHAR(100),CONSTRAINT uc_users_username UNIQUE (user_username));
CREATE TABLE IF NOT EXISTS eventDB.event (event_id INT AUTO_INCREMENT PRIMARY KEY, event_name VARCHAR(100), event_responsible VARCHAR(100), event_date DATE, event_time DECIMAL(5,2), event_duration INT, CONSTRAINT uc_event_name UNIQUE (event_name));
CREATE TABLE IF NOT EXISTS eventDB.participant (participant_id INT AUTO_INCREMENT PRIMARY KEY, participant_name VARCHAR(100), participant_type VARCHAR(100), event_id INT, FOREIGN KEY (event_id) REFERENCES eventDB.event(event_id));
CREATE TABLE IF NOT EXISTS universityDB.student (student_id INT AUTO_INCREMENT PRIMARY KEY, student_name VARCHAR(100), student_username VARCHAR(100), student_email VARCHAR(100), FOREIGN KEY (student_username) REFERENCES userDB.users(user_username), CONSTRAINT uc_student_email UNIQUE (student_email));


INSERT IGNORE INTO eventDB.event (event_name, event_responsible, event_date, event_time, event_duration) VALUES ("OOP Programming", "Senpai Markus", '2023-06-15', 17.30, 300);
INSERT IGNORE INTO eventDB.event (event_name, event_responsible, event_date, event_time, event_duration) VALUES ("Webdevolpment", "Rolando", '2023-06-18', 19.30, 300);
INSERT IGNORE INTO eventDB.event (event_name, event_responsible, event_date, event_time, event_duration) VALUES ("System architecture", "bob the builder", '2023-08-20', 18.30, 300);
INSERT IGNORE INTO eventDB.event (event_name, event_responsible, event_date, event_time, event_duration) VALUES ("React or vue", "Mace Windu", '2023-08-25', 18.00, 300);
INSERT IGNORE INTO eventDB.event (event_name, event_responsible, event_date, event_time, event_duration) VALUES ("I hate programming", "john everyone", '2023-09-02', 18.00, 300);

INSERT IGNORE INTO universityDB.student (student_name, student_email) VALUES ("Bob","bob@gmail.com");
INSERT IGNORE INTO universityDB.student (student_name, student_email) VALUES ("Alice","alice@gmail.com");
INSERT IGNORE INTO universityDB.student (student_name, student_email) VALUES ("Jack", "jack@gmail.com");
INSERT IGNORE INTO universityDB.student (student_name, student_email) VALUES ("Mary", "mary@gmail.com");
INSERT IGNORE INTO universityDB.student (student_name, student_email) VALUES ("Eric", "eric@gmail.com");
INSERT IGNORE INTO universityDB.student (student_name, student_email) VALUES ("James", "james@gmail.com");