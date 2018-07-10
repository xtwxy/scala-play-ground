CREATE DATABASE musicdb DEFAULT CHARACTER SET 'UTF8' DEFAULT COLLATE utf8_unicode_ci;
CREATE USER 'music'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'music'@'localhost' WITH GRANT OPTION;

USE musicdb;

CREATE TABLE music_type(type_id TINYINT, type_name VARCHAR(128)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE music(music_id BIGINT, music_type TINYINT, music_name VARCHAR(128), music_composer VARCHAR(128)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO music_type(type_id, type_name) values(0, 'CLASSICAL');
INSERT INTO music_type(type_id, type_name) values(1, 'JAZZ');
INSERT INTO music_type(type_id, type_name) values(2, 'POP');

ALTER TABLE music_type ADD CONSTRAINT music_type_pk PRIMARY KEY(type_id);
ALTER TABLE music ADD CONSTRAINT music_pk PRIMARY KEY(music_id);
ALTER TABLE music ADD CONSTRAINT music_music_type_fk FOREIGN KEY (music_type) REFERENCES music_type(type_id);

INSERT INTO music(music_id, music_type, music_name, music_composer) values(1, 0, 'Fur Elise', 'Ludwig van Beethoven');
INSERT INTO music(music_id, music_type, music_name, music_composer) values(2, 0, 'Symphony in C', 'Georges Bizet');

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `nodes`
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parentId` bigint DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `nodes`
-- ----------------------------
BEGIN;
INSERT INTO `tree` VALUES ('1', '0', 'Node 1'), ('2', '0', 'Node 2'), ('3', '0', 'Node 3'), ('4', '0', 'Node 4'), ('5', '1', 'Node 1.1'), ('6', '1', 'Node 1.2'), ('7', '5', 'Node 1.1.1'), ('8', '5', 'Node 1.1.2'), ('9', '5', 'Node 1.1.3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
