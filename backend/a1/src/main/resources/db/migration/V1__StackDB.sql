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
  `Name` VARCHAR(45) NOT NULL,
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

CREATE TABLE IF NOT EXISTS `votequestion` (
  `idVoteQuestion` int(11) NOT NULL AUTO_INCREMENT,
  `Question_idQuestion` int(11) NOT NULL,
  `User_Username` varchar(45) NOT NULL,
  `VoteType` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idVoteQuestion`,`Question_idQuestion`,`User_Username`),
  UNIQUE KEY `idVoteQuestion_UNIQUE` (`idVoteQuestion`),
  KEY `fk_Question_has_User_User1_idx` (`User_Username`),
  KEY `fk_Question_has_User_Question1_idx` (`Question_idQuestion`,`User_Username`),
  KEY `fk_Question_has_User_Question1_idx1` (`Question_idQuestion`),
  CONSTRAINT `fk_Question_has_User_Question1` FOREIGN KEY (`Question_idQuestion`) REFERENCES `question` (`idQuestion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Question_has_User_User1` FOREIGN KEY (`User_Username`) REFERENCES `user` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB

CREATE  TABLE `sover`.`voteanswer` (
  `idVoteAnswer` INT NOT NULL AUTO_INCREMENT ,
  `Answer_idAnswer` INT NOT NULL ,
  `User_Username` VARCHAR(45) NOT NULL ,
  `VoteType` tinyint(1) DEFAULT NULL ,
  PRIMARY KEY (`idVoteAnswer`, `Answer_idAnswer`, `User_Username`) ,
  INDEX `_idx` (`Answer_idAnswer` ASC) ,
  INDEX `fk_Answer_has_User_User1_idx` (`User_Username` ASC) ,
  CONSTRAINT `fk_Answer_has_User_Answer1`
    FOREIGN KEY (`Answer_idAnswer` )
    REFERENCES `sover`.`answer` (`idAnswer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_has_User_User1`
    FOREIGN KEY (`User_Username` )
    REFERENCES `sover`.`user` (`Username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB