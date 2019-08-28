-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Couch
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Couch
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Couch` DEFAULT CHARACTER SET utf8 ;
USE `Couch` ;

-- -----------------------------------------------------
-- Table `Couch`.`Bankaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Bankaccount` (
  `IBAN` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NOT NULL,
  `interestRate` DOUBLE NOT NULL,
  `Bankaccount_IBAN` INT(11) NOT NULL,
  PRIMARY KEY (`IBAN`, `Bankaccount_IBAN`),
  INDEX `fk_Bankaccount_Bankaccount1_idx` (`Bankaccount_IBAN` ASC) VISIBLE,
  CONSTRAINT `fk_Bankaccount_Bankaccount1`
    FOREIGN KEY (`Bankaccount_IBAN`)
    REFERENCES `Couch`.`Bankaccount` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Couch`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`User` (
  `userName` VARCHAR(45) NULL,
  `userPassword` VARCHAR(45) NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Couch`.`Retail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Retail` (
  `bsn` INT NULL,
  `adress` VARCHAR(45) NULL,
  `housenumber` VARCHAR(45) NULL,
  `postalcode` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `phonenumber` VARCHAR(45) NULL,
  `emailadress` VARCHAR(45) NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `dateOfBirth` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `middleName` VARCHAR(45) NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`User_idUser`),
  CONSTRAINT `fk_Retail_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Couch`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Couch`.`SME`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`SME` (
  `KvK` INT NULL,
  `companyName` VARCHAR(45) NULL,
  `roleEmployee` VARCHAR(45) NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`User_idUser`),
  CONSTRAINT `fk_SME_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Couch`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Couch`.`Loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Loan` (
  `idLoan` INT NOT NULL AUTO_INCREMENT,
  `endDate` DATETIME NOT NULL,
  `Retail_User_idUser` INT NOT NULL,
  `SME_User_idUser` INT NOT NULL,
  `initialDept` VARCHAR(45) NULL,
  PRIMARY KEY (`idLoan`),
  INDEX `fk_Loan_Retail1_idx` (`Retail_User_idUser` ASC) VISIBLE,
  INDEX `fk_Loan_SME1_idx` (`SME_User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Loan_Retail1`
    FOREIGN KEY (`Retail_User_idUser`)
    REFERENCES `Couch`.`Retail` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Loan_SME1`
    FOREIGN KEY (`SME_User_idUser`)
    REFERENCES `Couch`.`SME` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Couch`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Transaction` (
  `idTransaction` INT(11) NOT NULL,
  `ibanVoor` VARCHAR(45) NOT NULL,
  `ibanTegen` VARCHAR(45) NOT NULL,
  `amount` DECIMAL(10,0) NOT NULL,
  `timeStamp` TIMESTAMP(5) NOT NULL,
  `description` VARCHAR(45) NULL,
  `Bankaccount_IBAN` INT(11) NOT NULL,
  `Loan_idLoan` INT NOT NULL,
  PRIMARY KEY (`idTransaction`, `Bankaccount_IBAN`, `Loan_idLoan`),
  INDEX `fk_Transaction_Bankaccount1_idx` (`Bankaccount_IBAN` ASC) VISIBLE,
  INDEX `fk_Transaction_Loan1_idx` (`Loan_idLoan` ASC) VISIBLE,
  CONSTRAINT `fk_Transaction_Bankaccount1`
    FOREIGN KEY (`Bankaccount_IBAN`)
    REFERENCES `Couch`.`Bankaccount` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Loan1`
    FOREIGN KEY (`Loan_idLoan`)
    REFERENCES `Couch`.`Loan` (`idLoan`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Couch`.`Bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Bank` (
  `roleEmployee` VARCHAR(45) NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`User_idUser`),
  CONSTRAINT `fk_Bank_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Couch`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Couch`.`Bankaccount_has_Retail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Bankaccount_has_Retail` (
  `Bankaccount_IBAN` INT(11) NOT NULL,
  `Retail_User_idUser` INT NOT NULL,
  PRIMARY KEY (`Bankaccount_IBAN`, `Retail_User_idUser`),
  INDEX `fk_Bankaccount_has_Retail_Retail1_idx` (`Retail_User_idUser` ASC) VISIBLE,
  INDEX `fk_Bankaccount_has_Retail_Bankaccount1_idx` (`Bankaccount_IBAN` ASC) VISIBLE,
  CONSTRAINT `fk_Bankaccount_has_Retail_Bankaccount1`
    FOREIGN KEY (`Bankaccount_IBAN`)
    REFERENCES `Couch`.`Bankaccount` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bankaccount_has_Retail_Retail1`
    FOREIGN KEY (`Retail_User_idUser`)
    REFERENCES `Couch`.`Retail` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Couch`.`SME_has_Bankaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`SME_has_Bankaccount` (
  `SME_User_idUser` INT NOT NULL,
  `Bankaccount_IBAN` INT(11) NOT NULL,
  PRIMARY KEY (`SME_User_idUser`, `Bankaccount_IBAN`),
  INDEX `fk_SME_has_Bankaccount_Bankaccount1_idx` (`Bankaccount_IBAN` ASC) VISIBLE,
  INDEX `fk_SME_has_Bankaccount_SME1_idx` (`SME_User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_SME_has_Bankaccount_SME1`
    FOREIGN KEY (`SME_User_idUser`)
    REFERENCES `Couch`.`SME` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SME_has_Bankaccount_Bankaccount1`
    FOREIGN KEY (`Bankaccount_IBAN`)
    REFERENCES `Couch`.`Bankaccount` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Couch`.`Bank_has_Bankaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Couch`.`Bank_has_Bankaccount` (
  `Bank_User_idUser` INT NOT NULL,
  `Bankaccount_IBAN` INT(11) NOT NULL,
  PRIMARY KEY (`Bank_User_idUser`, `Bankaccount_IBAN`),
  INDEX `fk_Bank_has_Bankaccount_Bankaccount1_idx` (`Bankaccount_IBAN` ASC) VISIBLE,
  INDEX `fk_Bank_has_Bankaccount_Bank1_idx` (`Bank_User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Bank_has_Bankaccount_Bank1`
    FOREIGN KEY (`Bank_User_idUser`)
    REFERENCES `Couch`.`Bank` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bank_has_Bankaccount_Bankaccount1`
    FOREIGN KEY (`Bankaccount_IBAN`)
    REFERENCES `Couch`.`Bankaccount` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
