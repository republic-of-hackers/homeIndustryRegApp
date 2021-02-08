/*
-- Query: select * from inspection_info
LIMIT 0, 1000

-- Date: 2021-01-29 23:11
*/
SET Foreign_KEY_CHECKS = 0;

INSERT INTO `inspection_info` (`id`,`available_date`,`city`) VALUES (1,'2021-02-02','delhi');
INSERT INTO `inspection_info` (`id`,`available_date`,`city`) VALUES (2,'2021-02-03','delhi');
INSERT INTO `inspection_info` (`id`,`available_date`,`city`) VALUES (3,'2021-02-04','mumbai');
INSERT INTO `inspection_info` (`id`,`available_date`,`city`) VALUES (4,'2021-02-05','surat');

INSERT INTO `inspection_time_slot` (`id`,`slot1`,`slot2`,`slot3`,`insp_info_id`,`slot1book_by_id`,`slot2book_by_id`,`slot3book_by_id`) VALUES (1,'09:00:00','12:00:00','15:00:00',1,NULL,NULL,NULL);
INSERT INTO `inspection_time_slot` (`id`,`slot1`,`slot2`,`slot3`,`insp_info_id`,`slot1book_by_id`,`slot2book_by_id`,`slot3book_by_id`) VALUES (2,'10:00:00','13:00:00','17:00:00',2,NULL,NULL,NULL);
INSERT INTO `inspection_time_slot` (`id`,`slot1`,`slot2`,`slot3`,`insp_info_id`,`slot1book_by_id`,`slot2book_by_id`,`slot3book_by_id`) VALUES (3,'08:00:00','11:00:00','15:00:00',3,NULL,NULL,NULL);
INSERT INTO `inspection_time_slot` (`id`,`slot1`,`slot2`,`slot3`,`insp_info_id`,`slot1book_by_id`,`slot2book_by_id`,`slot3book_by_id`) VALUES (4,'09:00:00','11:00:00','16:00:00',4,NULL,NULL,NULL);


INSERT INTO `inspection_info_time_slots` (`inspection_information_id`,`time_slots_id`) VALUES (1,1);
INSERT INTO `inspection_info_time_slots` (`inspection_information_id`,`time_slots_id`) VALUES (2,2);
INSERT INTO `inspection_info_time_slots` (`inspection_information_id`,`time_slots_id`) VALUES (3,3);
INSERT INTO `inspection_info_time_slots` (`inspection_information_id`,`time_slots_id`) VALUES (4,4);

SET Foriegn_KEY_CHECKS = 1;