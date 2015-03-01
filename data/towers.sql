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
  `towerId` int(11) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `contactNumber` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `facebookPage` varchar(50) DEFAULT NULL,
  `twitterPage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`towerId`)
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
-- Table structure for table `peals`
--

DROP TABLE IF EXISTS `peals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peals` (
  `pealId` int(11) NOT NULL,
  `towerId` int(11) NOT NULL,
  `dedication` varchar(20) DEFAULT NULL,
  `dateRung` date DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
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
-- Dumping data for table `peals`
--

LOCK TABLES `peals` WRITE;
/*!40000 ALTER TABLE `peals` DISABLE KEYS */;
/*!40000 ALTER TABLE `peals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `PersonID` int(11) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'MacLeod','Jamie',NULL,NULL);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
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
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
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
  `ringable` tinyint(1) DEFAULT NULL,
  `gridReference` varchar(12) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `postCode` varchar(8) DEFAULT NULL,
  `satNavLatitude` float DEFAULT NULL,
  `satNavLongitude` float DEFAULT NULL,
  `countyId` int(3) DEFAULT NULL,
  `countryId` int(3) DEFAULT NULL,
  `guildId` int(3) DEFAULT NULL,
  `dedication` varchar(100) DEFAULT NULL,
  `listedGrade` varchar(4) DEFAULT NULL,
  `groundFloorRing` tinyint(1) DEFAULT NULL,
  `simulator` tinyint(1) DEFAULT NULL,
  `toilet` tinyint(1) DEFAULT NULL,
  `extraInfo` varchar(250) DEFAULT NULL,
  `buildingId` int(3) DEFAULT NULL,
  `affiliation` varchar(10) DEFAULT NULL,
  `accessDetails` varchar(100) DEFAULT NULL,
  `towerCaptain` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`towerId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `towers`
--

LOCK TABLES `towers` WRITE;
/*!40000 ALTER TABLE `towers` DISABLE KEYS */;
INSERT INTO `towers` VALUES (1,'',342432432,'','','',0,'',0,0,'',0,0,0,0,0,'','',0,0,0,'',0,'','','');
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

-- Dump completed on 2015-03-01 18:46:28
