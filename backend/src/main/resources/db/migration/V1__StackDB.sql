CREATE  TABLE IF NOT EXISTS `sover`.`User` (
  `Username` VARCHAR(45) NOT NULL ,
  `Password` VARCHAR(45) NOT NULL ,
  `Score` INT NULL ,
  `Email` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Username`) ,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) )
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `sover`.`Question` (
  `idQuestion` INT NOT NULL AUTO_INCREMENT ,
  `Title` VARCHAR(45) NOT NULL ,
  `Content` VARCHAR(45) NOT NULL ,
  `Creation` DATETIME NOT NULL ,
  `Author` VARCHAR(45) NOT NULL ,
  `Score` INT NULL ,
  PRIMARY KEY (`idQuestion`, `Author`) ,
  INDEX `fk_Question_User1_idx` (`Author` ASC) ,
  CONSTRAINT `fk_Question_User1`
    FOREIGN KEY (`Author` )
    REFERENCES `sover`.`User` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `sover`.`Answer` (
  `idAnswer` INT NOT NULL AUTO_INCREMENT ,
  `Text` VARCHAR(45) NOT NULL ,
  `Creation` DATETIME NOT NULL ,
  `idQuestion` INT NOT NULL ,
  `Author` VARCHAR(45) NOT NULL ,
  `Score` INTEGER NULL ,
  PRIMARY KEY (`idAnswer`, `idQuestion`, `Author`) ,
  INDEX `fk_Answer_Question_idx` (`idQuestion` ASC) ,
  INDEX `fk_Answer_User1_idx` (`Author` ASC) ,
  CONSTRAINT `fk_Answer_Question`
    FOREIGN KEY (`idQuestion` )
    REFERENCES `sover`.`Question` (`idQuestion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_User1`
    FOREIGN KEY (`Author` )
    REFERENCES `sover`.`User` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `sover`.`Tags` (
  `Name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Name`) )
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `sover`.`Tags_has_Question` (
  `idTagsQuestion` INT NOT NULL AUTO_INCREMENT,
  `Tags_Name` VARCHAR(45) NOT NULL ,
  `Question_idQuestion` INT NOT NULL ,
  PRIMARY KEY (`idTagsQuestion`, `Tags_Name`, `Question_idQuestion`) ,
  INDEX `fk_Tags_has_Question_Question1_idx` (`Question_idQuestion` ASC) ,
  INDEX `fk_Tags_has_Question_Tags1_idx` (`Tags_Name` ASC) ,
  CONSTRAINT `fk_Tags_has_Question_Tags1`
    FOREIGN KEY (`Tags_Name` )
    REFERENCES `sover`.`Tags` (`Name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tags_has_Question_Question1`
    FOREIGN KEY (`Question_idQuestion` )
    REFERENCES `sover`.`Question` (`idQuestion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `sover`.`VoteQuestion` (
  `idVoteQuestion` INT NOT NULL AUTO_INCREMENT ,
  `Question_idQuestion` INT NOT NULL ,
  `Question_Author` VARCHAR(45) NOT NULL ,
  `User_Username` VARCHAR(45) NOT NULL ,
  `VoteType` TINYINT(1) NULL ,
  PRIMARY KEY (`idVoteQuestion`, `Question_idQuestion`, `Question_Author`, `User_Username`) ,
  INDEX `fk_Question_has_User_User1_idx` (`User_Username` ASC) ,
  INDEX `fk_Question_has_User_Question1_idx` (`Question_idQuestion` ASC, `Question_Author` ASC) ,
  UNIQUE INDEX `idVoteQuestion_UNIQUE` (`idVoteQuestion` ASC) ,
  CONSTRAINT `fk_Question_has_User_Question1`
    FOREIGN KEY (`Question_idQuestion` , `Question_Author` )
    REFERENCES `sover`.`Question` (`idQuestion` , `Author` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Question_has_User_User1`
    FOREIGN KEY (`User_Username` )
    REFERENCES `sover`.`User` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

CREATE  TABLE IF NOT EXISTS `sover`.`VoteAnswer` (
  `idVoteAnswer` INT NOT NULL AUTO_INCREMENT ,
  `Answer_idAnswer` INT NOT NULL ,
  `User_Username` VARCHAR(45) NOT NULL ,
  `VoteType` TINYINT(1) NULL ,
  PRIMARY KEY (`idVoteAnswer`, `Answer_idAnswer`, `User_Username`) ,
  INDEX `fk_Answer_has_User_User1_idx` (`User_Username` ASC) ,
  INDEX `fk_Answer_has_User_Answer1_idx` (`Answer_idAnswer` ASC, `User_Username` ASC) ,
  UNIQUE INDEX `idVoteAnswer_UNIQUE` (`idVoteAnswer` ASC) ,
  CONSTRAINT `fk_Answer_has_User_Answer1`
    FOREIGN KEY (`Answer_idAnswer` , `User_Username` )
    REFERENCES `sover`.`Answer` (`idAnswer` , `Author` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_has_User_User1`
    FOREIGN KEY (`User_Username` )
    REFERENCES `sover`.`User` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB