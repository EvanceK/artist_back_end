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
  `status` varchar(255) DEFAULT NULL,
  `bid_time` datetime NOT NULL,
  `bid_amount` double NOT NULL,
  `is_winning_bid` bit(1) NOT NULL,
  `deposit` double DEFAULT NULL,
  `deposit_status` varchar(255) DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  `refund_amount` double DEFAULT NULL,
  PRIMARY KEY (`bid_id`),
  KEY `fk_painting_idx` (`painting_id`),
  KEY `fk_customer_idx` (`bidder_id`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`bidder_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `fk_painting` FOREIGN KEY (`painting_id`) REFERENCES `paintings` (`painting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidrecord`
--

LOCK TABLES `bidrecord` WRITE;
/*!40000 ALTER TABLE `bidrecord` DISABLE KEYS */;
INSERT INTO `bidrecord` VALUES (1,'PT0001','CU0004','In Bidding','2024-10-13 00:03:08',1100,_binary '\0',110,'refunded','2024-10-13 00:04:24',110),(2,'PT0002','CU0004','In Bidding','2024-10-13 00:03:13',1100,_binary '\0',110,'refunded','2024-10-13 00:04:27',110),(3,'PT0005','CU0004','In Bidding','2024-10-13 00:03:19',1500,_binary '\0',150,'refunded','2024-10-13 00:04:38',150),(4,'PT0004','CU0004','In Bidding','2024-10-13 00:03:22',1500,_binary '\0',150,'refunded','2024-10-13 00:04:29',150),(5,'PT0003','CU0004','In Bidding','2024-10-13 00:03:24',1500,_binary '\0',150,'refunded','2024-10-13 00:04:20',150),(6,'PT0003','CU0006','In Bidding','2024-10-13 00:04:20',2000,_binary '',200,'pending',NULL,0),(7,'PT0001','CU0006','In Bidding','2024-10-13 00:04:24',2000,_binary '\0',200,'refunded','2024-10-13 00:06:45',200),(8,'PT0002','CU0006','In Bidding','2024-10-13 00:04:27',2000,_binary '',200,'pending',NULL,0),(9,'PT0004','CU0006','In Bidding','2024-10-13 00:04:29',2000,_binary '',200,'pending',NULL,0),(10,'PT0005','CU0006','In Bidding','2024-10-13 00:04:38',2000,_binary '\0',200,'refunded','2024-10-13 00:04:59',200),(11,'PT0005','CU0006','In Bidding','2024-10-13 00:04:59',2200,_binary '',220,'pending',NULL,0),(12,'PT0001','CU0004','In Bidding','2024-10-13 00:06:45',2200,_binary '\0',220,'refunded','2024-10-13 00:07:08',220),(13,'PT0001','CU0006','In Bidding','2024-10-13 00:07:08',2400,_binary '\0',240,'refunded','2024-10-13 00:08:09',240),(14,'PT0001','CU0004','In Bidding','2024-10-13 00:08:09',3000,_binary '\0',300,'refunded','2024-10-13 00:15:25',300),(15,'PT0001','CU0006','In Bidding','2024-10-13 00:15:25',3500,_binary '',350,'pending',NULL,0);
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

-- Dump completed on 2024-10-13  2:12:26
