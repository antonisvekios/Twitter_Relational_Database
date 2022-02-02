-- -----------------------------------------------------
-- Schema twitterRDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS twitterRDB;

-- -----------------------------------------------------
-- Schema doctorpatient
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS twitterRDB DEFAULT CHARACTER SET utf8 ;
USE twitterRDB;

-- -----------------------------------------------------
-- Table `tweet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS tweet ;

CREATE TABLE IF NOT EXISTS tweet (
  tweet_id INT AUTO_INCREMENT,
  user_id INT NOT NULL,
  tweet_ts DATETIME(4) NOT NULL,
  tweet_text VARCHAR(140) NOT NULL,
  PRIMARY KEY (tweet_id),
  KEY SECONDARY (user_id)
);

-- -----------------------------------------------------
-- Table `follows`
-- -----------------------------------------------------
DROP TABLE IF EXISTS follows ;

CREATE TABLE IF NOT EXISTS follows (
  user_id INT NOT NULL,
  follows_id INT NOT NULL,
  KEY SECONDARY (user_id)
);