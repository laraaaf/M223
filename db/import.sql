/* 
Author: Lara Felix
Titel: M223 - FoldingPaperStory
*/


USE FoldingPaperStory;

DROP TABLE IF EXISTS tbl_user;

/* 
writer
*/

INSERT INTO `tbl_user` (`ID_User`, `Mail`, `Password`, `Rolle`)
VALUES (
 NULL, 'testing@user.ch', '745064826', '0'
);

/* 
gamemaster
*/

INSERT INTO `tbl_user` (`ID_User`, `Mail`, `Password`, `Rolle`)
VALUES (
 NULL, 'game@master.ch', '-1888845021', '1'
);