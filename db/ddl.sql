/* 
Author: Lara Felix
Titel: M223 - FoldingPaperStory
*/

-- DATABASE
DROP DATABASE IF EXISTS FoldingPaperStory;
CREATE DATABASE FoldingPaperStory;
USE FoldingPaperStory;

-- CREATE TABLES
CREATE TABLE tbl_User (
  ID_User INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  Mail VARCHAR(50),
  Password VARCHAR(50),
  Rolle Int
);

CREATE TABLE tbl_Story (
  ID_StoryLine INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  sentence1 VARCHAR(100),
  sentence2 VARCHAR(100)
);


CREATE TABLE tbl_UserStory (
  ID_StoryLine INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  timestamp DATE NOT NULL,
  FK_User INT UNSIGNED,
  FK_StoryLine INT UNSIGNED,
  FOREIGN KEY (FK_User) REFERENCES tbl_User(ID_User),
  FOREIGN KEY (FK_StoryLine) REFERENCES tbl_Story(ID_StoryLine)
);
