-- MySQL dump 10.13  Distrib 5.6.22, for Win64 (x86_64)
--
-- Host: localhost    Database: towers
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `contactdetails`
--

DROP TABLE IF EXISTS `contactdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactdetails` (
  `towerRId` int(11) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `contactNumber` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `facebookPage` varchar(50) DEFAULT NULL,
  `twitterPage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`towerRId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactdetails`
--

LOCK TABLES `contactdetails` WRITE;
/*!40000 ALTER TABLE `contactdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peal`
--

DROP TABLE IF EXISTS `peal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peal` (
  `pealId` int(11) NOT NULL,
  `towerId` int(11) NOT NULL,
  `dedication` varchar(20) DEFAULT NULL,
  `dateRung` date DEFAULT NULL,
  `timeRung` time DEFAULT NULL,
  `tenor` varchar(5) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `methodDetails` varchar(20) DEFAULT NULL,
  `changes` int(3) DEFAULT NULL,
  `leader` varchar(50) DEFAULT NULL,
  `composer` varchar(50) DEFAULT NULL,
  `footnotes` varchar(50) DEFAULT NULL,
  `composition` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pealId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peal`
--

LOCK TABLES `peal` WRITE;
/*!40000 ALTER TABLE `peal` DISABLE KEYS */;
/*!40000 ALTER TABLE `peal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice` (
  `towerId` int(11) NOT NULL,
  `day` varchar(10) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `regularity` varchar(20) DEFAULT NULL,
  `visitorsWelcome` varchar(20) DEFAULT NULL,
  `practicecol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`towerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
/*!40000 ALTER TABLE `practice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `towers`
--

DROP TABLE IF EXISTS `towers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `towers` (
  `towerId` int(11) NOT NULL AUTO_INCREMENT,
  `doveId` varchar(12) NOT NULL,
  `towerbaseId` int(5) DEFAULT NULL,
  `placeName` varchar(50) DEFAULT NULL,
  `placeName2` varchar(50) DEFAULT NULL,
  `placeNameCL` varchar(50) DEFAULT NULL,
  `associatedChurch` varchar(50) DEFAULT NULL,
  `gridReference` varchar(12) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `postCode` varchar(8) DEFAULT NULL,
  `satNavLatitude` float DEFAULT NULL,
  `satNavLongitude` float DEFAULT NULL,
  `countyId` int(3) DEFAULT NULL,
  `countryId` int(3) DEFAULT NULL,
  `guildId` int(3) DEFAULT NULL,
  `dedication` varchar(50) DEFAULT NULL,
  `listedGrade` varchar(4) DEFAULT NULL,
  `groundFloorRing` tinyint(1) DEFAULT NULL,
  `simulator` tinyint(1) DEFAULT NULL,
  `toilet` tinyint(1) DEFAULT NULL,
  `extraInfo` varchar(100) DEFAULT NULL,
  `buildingId` int(10) DEFAULT NULL,
  `affiliation` varchar(10) DEFAULT NULL,
  `accessDetails` varchar(100) DEFAULT NULL,
  `towerCaptain` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`towerId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `towers`
--

LOCK TABLES `towers` WRITE;
/*!40000 ALTER TABLE `towers` DISABLE KEYS */;
INSERT INTO `towers` VALUES (25,'12',NULL,NULL,NULL,NULL,NULL,NULL,12,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `towers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-17 20:11:45
