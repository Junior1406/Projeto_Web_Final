SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gerenciamento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gerenciamento` DEFAULT CHARACTER SET latin1 ;
USE `gerenciamento` ;

-- -----------------------------------------------------
-- Table `gerenciamento`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento`.`administrador` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `senha` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `gerenciamento`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento`.`funcionario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(16) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `gerenciamento`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento`.`produto` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_prod` VARCHAR(45) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `estoque` INT(11) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into administrador (senha, login) values ("e10adc3949ba59abbe56e057f20f883e" , "admin");

-- -----------------
-- login = admin  --
-- senha = 123456 --
-- -----------------

select * from administrador;
