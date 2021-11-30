/* 
Author: Lara Felix
Titel: M223
*/

-- DATABASE
DROP DATABASE IF EXISTS DataWarehouse;
CREATE DATABASE DataWarehouse;
USE DataWarehouse;

-- CREATE TABLES
CREATE TABLE tbl_filiale (
  ID_Filiale INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  FilialenName VARCHAR(50)
);