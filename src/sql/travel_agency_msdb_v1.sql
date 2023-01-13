-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema travel_agency_db1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel_agency_db1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel_agency_db1` DEFAULT CHARACTER SET utf8 ;
USE `travel_agency_db1` ;

-- -----------------------------------------------------
-- Table `travel_agency_db1`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`role` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO role (role) values ('admin');
INSERT INTO role (role) values ('manager');
INSERT INTO role (role) values ('client');


-- -----------------------------------------------------
-- Table `travel_agency_db1`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`user` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 0,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `travel_agency_db1`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO user (first_name, last_name, email, password, phone, active, role_id)
            VALUES ('admin', 'admin', 'admin@mail.com', 'adminPassword', '+380111111111', 1,
            (SELECT id FROM role where role = 'admin'));


-- -----------------------------------------------------
-- Table `travel_agency_db1`.`tour_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`tour_type` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`tour_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tour_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tour_type_UNIQUE` (`tour_type` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO tour_type (tour_type) values ('rest');
INSERT INTO tour_type (tour_type) values ('excursion');
INSERT INTO tour_type (tour_type) values ('shopping');

-- -----------------------------------------------------
-- Table `travel_agency_db1`.`hotel_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`hotel_type` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`hotel_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `star_rate` INT NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `star_rate_UNIQUE` (`star_rate` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO hotel_type (star_rate, `desc`) VALUES (1, '1-Star Hotel: A hotel with basic amenities, perhaps with shared bathrooms or no dining options.');
INSERT INTO hotel_type (star_rate, `desc`) VALUES (2, '2-Star Hotel: A hotel with basic amenities and slightly elevated decor, breakfast service, and/or public areas like a lobby or gym.');
INSERT INTO hotel_type (star_rate, `desc`) VALUES (3, '3-Star Hotel: A full-service hotel with a restaurant, front desk, intentional decor, and housekeeping service.');
INSERT INTO hotel_type (star_rate, `desc`) VALUES (4, '4-Star Hotel: An upscale hotel with on-site dining, premium amenities, and a signature look and feel.');
INSERT INTO hotel_type (star_rate, `desc`) VALUES (5, '5-Star Hotel: A luxurious hotel with personalized service, high-end dining venues, wellness facilities, and elegant design.');

-- -----------------------------------------------------
-- Table `travel_agency_db1`.`tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`tour` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`tour` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `description` TEXT(65535) NULL,
  `persons_number` INT UNSIGNED NULL,
  `price` INT UNSIGNED NOT NULL,
  `hot` TINYINT NOT NULL,
  `tour_type_id` INT NOT NULL,
  `hotel_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_tour_tour_type1_idx` (`tour_type_id` ASC) VISIBLE,
  INDEX `fk_tour_hotel_type1_idx` (`hotel_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_tour_tour_type1`
    FOREIGN KEY (`tour_type_id`)
    REFERENCES `travel_agency_db1`.`tour_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tour_hotel_type1`
    FOREIGN KEY (`hotel_type_id`)
    REFERENCES `travel_agency_db1`.`hotel_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agency_db1`.`receipt_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`receipt_status` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`receipt_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `receipt_status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agency_db1`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel_agency_db1`.`receipt` ;

CREATE TABLE IF NOT EXISTS `travel_agency_db1`.`receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tour_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `discount` INT UNSIGNED NOT NULL,
  `amount` INT UNSIGNED NOT NULL,
  `order_status_id` INT NOT NULL,
  `datetime` DATETIME NOT NULL,
  PRIMARY KEY (`id`, `tour_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_order_status1_idx` (`order_status_id` ASC) VISIBLE,
  INDEX `fk_receipt_tour1_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_receipt_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `travel_agency_db1`.`receipt_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_tour1`
    FOREIGN KEY (`tour_id`)
    REFERENCES `travel_agency_db1`.`tour` (`id`)
    ON DELETE NO CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_agency_db1`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

ALTER TABLE tour ADD max_discount INT UNSIGNED DEFAULT 0 AFTER price;
ALTER TABLE tour ADD CHECK (max_discount<100);