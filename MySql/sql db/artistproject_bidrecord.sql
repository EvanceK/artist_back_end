CREATE DATABASE  IF NOT EXISTS `artistproject` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `artistproject`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: artistproject
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bidrecord`
--

DROP TABLE IF EXISTS `bidrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bidrecord` (
  `bid_id` bigint NOT NULL AUTO_INCREMENT,
  `painting_id` varchar(45) NOT NULL,
  `bidder_id` varchar(45) NOT NULL,
  `bid_time` datetime NOT NULL,
  `bid_amount` double NOT NULL,
  `is_winning_bid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`bid_id`),
  KEY `fk_painting_idx` (`painting_id`),
  KEY `fk_customer_idx` (`bidder_id`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`bidder_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `fk_painting` FOREIGN KEY (`painting_id`) REFERENCES `paintings` (`painting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidrecord`
--

LOCK TABLES `bidrecord` WRITE;
/*!40000 ALTER TABLE `bidrecord` DISABLE KEYS */;
INSERT INTO `bidrecord` VALUES (2,'PT0001','CU0006','2024-10-10 00:32:59',100,_binary '\0'),(3,'PT0001','CU0004','2024-10-10 00:35:08',110,_binary '\0'),(4,'PT0001','CU0006','2024-10-10 00:36:04',300,_binary '');
/*!40000 ALTER TABLE `bidrecord` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-10  1:05:01
