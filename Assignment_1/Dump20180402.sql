-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tournament
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `gameId` int(11) NOT NULL AUTO_INCREMENT,
  `score1` int(11) DEFAULT NULL,
  `score2` int(11) DEFAULT NULL,
  `game_matchID` int(11) DEFAULT NULL,
  PRIMARY KEY (`gameId`),
  KEY `game_idx` (`game_matchID`),
  CONSTRAINT `game` FOREIGN KEY (`game_matchID`) REFERENCES `match` (`matchID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,0,0,12),(2,0,0,12),(3,0,0,12),(4,0,0,12),(5,0,0,12),(6,0,0,13),(7,0,0,13),(8,0,0,13),(9,0,0,13),(10,0,0,13),(11,0,0,14),(12,0,0,14),(13,0,0,14),(14,0,0,14),(15,0,0,14),(16,0,0,15),(17,0,0,15),(18,0,0,15),(19,0,0,15),(20,0,0,15),(21,0,0,16),(22,0,0,16),(23,0,0,16),(24,0,0,16),(25,0,0,16),(26,0,0,17),(27,0,0,17),(28,0,0,17),(29,0,0,17),(30,0,0,17),(31,0,0,18),(32,0,0,18),(33,0,0,18),(34,0,0,18),(35,0,0,18),(36,0,0,19),(37,0,0,19),(38,0,0,19),(39,0,0,19),(40,0,0,19),(41,0,0,20),(42,0,0,20),(43,0,0,20),(44,0,0,20),(45,0,0,20),(46,0,0,21),(47,0,0,21),(48,0,0,21),(49,0,0,21),(50,0,0,21),(51,0,0,22),(52,0,0,22),(53,0,0,22),(54,0,0,22),(55,0,0,22),(56,0,0,23),(57,0,0,23),(58,0,0,23),(59,0,0,23),(60,0,0,23),(61,0,0,24),(62,0,0,24),(63,0,0,24),(64,0,0,24),(65,0,0,24),(66,0,0,25),(67,0,0,25),(68,0,0,25),(69,0,0,25),(70,0,0,25),(71,0,0,26),(72,0,0,26),(73,0,0,26),(74,0,0,26),(75,0,0,26),(76,0,0,27),(77,0,0,27),(78,0,0,27),(79,0,0,27),(80,0,0,27);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match` (
  `matchID` int(11) NOT NULL AUTO_INCREMENT,
  `match_playerOneID` int(11) NOT NULL,
  `match_playerTwoID` int(11) NOT NULL,
  `match_tournamentId` int(11) NOT NULL,
  `p1Score` int(11) DEFAULT NULL,
  `p2Score` int(11) DEFAULT NULL,
  `tourPlace` int(11) DEFAULT NULL,
  PRIMARY KEY (`matchID`),
  KEY `fk_match_player1_idx` (`match_playerOneID`),
  KEY `fk_match_player2_idx` (`match_playerTwoID`),
  KEY `tournament_idx` (`match_tournamentId`),
  CONSTRAINT `fk_match_player1` FOREIGN KEY (`match_playerOneID`) REFERENCES `player` (`playerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_match_player2` FOREIGN KEY (`match_playerTwoID`) REFERENCES `player` (`playerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tournament` FOREIGN KEY (`match_tournamentId`) REFERENCES `tournament` (`tournamentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
INSERT INTO `match` VALUES (1,1,2,1,5,5,4),(2,3,4,1,5,5,4),(3,5,6,1,6,4,4),(4,7,8,1,5,6,4),(5,2,3,1,3,3,2),(6,5,7,1,2,2,2),(7,2,5,1,1,1,1),(12,1,3,23,0,0,4),(13,5,8,23,0,0,4),(14,14,4,23,0,0,4),(15,6,7,23,0,0,4),(16,1,3,24,0,0,4),(17,5,4,24,0,0,4),(18,14,7,24,0,0,4),(19,6,8,24,0,0,4),(20,15,16,25,0,0,4),(21,1,14,25,0,0,4),(22,5,4,25,0,0,4),(23,3,8,25,0,0,4),(24,1,15,26,0,0,4),(25,16,14,26,0,0,4),(26,8,5,26,0,0,4),(27,3,6,26,0,0,4);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `playerID` int(11) NOT NULL AUTO_INCREMENT,
  `playerName` varchar(255) NOT NULL,
  `playerEmail` varchar(255) NOT NULL,
  `playerPassword` varchar(255) NOT NULL,
  `admin` tinyint(3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`playerID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'Vlad Pantis','pantisvlad@bucuresti.ro','dGlldGF0YQ==',000),(2,'admin','a@a.a','YWFhYWFh',001),(3,'Player3','p@p.p','YWFhYWFh',000),(4,'Player4','salam@florin.com','YWFhYWFh',000),(5,'Player55','noisia@noisia.ro','MTIzNDU2',000),(6,'Player6','geo@geo.com','YWFhYWFh',000),(7,'Player7','ion@ionion.com','YWFhYWFh',000),(8,'Player8','email@email.com','YWFhYWFh',000),(14,'Roger Federer','roger@federer.com','ZmVkZXJlcg==',000),(15,'Andrei Gog','andrei@gog.com','Z29nYWll',000),(16,'Coman Nicu','nicu@coman.com','YWFhYWFh',000),(17,'adsfds','aaa@aa.aab','YWFhYWFh',000);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament` (
  `tournamentID` int(11) NOT NULL AUTO_INCREMENT,
  `tournamentName` varchar(255) DEFAULT NULL,
  `tournamentStatus` varchar(45) DEFAULT NULL,
  `winnerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`tournamentID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES (1,'First Tournament','Ended',5),(2,'Cluj Napoca Open 2018','Ended',2),(3,'Cluj Napoca Autumn 2018','Not Started',NULL),(5,'Wimbledon 2012','Not started',NULL),(6,'Wimbledon 2014','Not started',NULL),(20,'Oradea Open 2019','Not started',0),(23,'Piatra Neamt Open 2018','Not started',NULL),(24,'Bucharest PPCup 2018','Not started',NULL),(25,'Laboratoare PingPong','Not started',NULL),(26,'Falticeni Open 2018','Not started',NULL);
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 19:24:20
