-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema springmvc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema springmvc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `springmvc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `springmvc` ;

-- -----------------------------------------------------
-- Table `springmvc`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springmvc`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `isDeleted` TINYINT NULL DEFAULT '0',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `springmvc`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springmvc`.`orders` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `userID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_orders_user1_idx` (`userID` ASC) VISIBLE,
  CONSTRAINT `fk_orders_user1`
    FOREIGN KEY (`userID`)
    REFERENCES `springmvc`.`user` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `springmvc`.`orderitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springmvc`.`orderitem` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ordersItemID` INT NOT NULL,
  `items` VARCHAR(45) NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  `isDeleted` TINYINT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  INDEX `fk_orderItem_orders_idx` (`ordersItemID` ASC) VISIBLE,
  CONSTRAINT `fk_orderItem_orders`
    FOREIGN KEY (`ordersItemID`)
    REFERENCES `springmvc`.`orders` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
