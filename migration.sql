DROP DATABASE task_for_svyaznoy;
CREATE DATABASE task_for_svyaznoy;
USE task_for_svyaznoy;
CREATE TABLE Sms (
guid CHAR(38) NOT NULL,
phone CHAR(11) NOT NULL, 
message TEXT(2048) NOT NULL,
sent_date DATETIME NOT NULL,
sent_status CHAR(13) NOT NULL);