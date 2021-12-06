/* 
Author: Lara Felix
Titel: M223 - FoldingPaperStory
*/

USE FoldingPaperStory;

DROP TRIGGER IF EXISTS after_sentence_insert;

DELIMITER $$

   CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    TRIGGER `FoldingPaperStory`.`after_sentence_insert` AFTER INSERT
    ON `FoldingPaperStory`.`tbl_Story`
    FOR EACH ROW BEGIN
	INSERT INTO tab_UserStory
	SET FK_User = user,
	FK_StoryLine = new.ID_StoryLine,
	timestamp = NOW();
    END$$

DELIMITER ;
