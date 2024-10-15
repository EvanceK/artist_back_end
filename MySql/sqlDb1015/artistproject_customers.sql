CREATE DATABASE  IF NOT EXISTS `artistproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` varchar(10) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `credit_card_no` varchar(255) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL,
  `bank_balance` double DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('CU0001','aaa','aaa','aaa','aaa','aaa','aaa','aaa','823 Nextbank',0),('CU0002','bbb','bbb','bbb','bbb','aaa','bbb','aaa','823 Nextbank',0),('CU0003','John Doe','Johnny','123-456-7890','test@test.com','123 Main St','$2a$10$QmHgGxTdfV/c0vLA1eE4..MLD1RiJN/eCv90hBmz0iDG34tlXERhi','4111111111111111','823 Nextbank',0),('CU0004','tester1','tester1','0909999999','tester1@email.com','no where','$2a$10$yTLSDDXOeUTPZeeMOqV4UuNRGu23cCz3A8ch5h8lFquuc3HjEtYxm',NULL,'823 Nextbank',12175),('CU0005','Allen Doe','Allen Allen','+1234567890','Allen.test@example.com','123  St, Cityville, Country','$2a$10$inlt/9Mh6zr1m528KpzV.OAp0K2N3MCkjTCKkqFIQF16dsBNAj2km','4111111111111111','823 Nextbank',0),('CU0006','Emily Davis','Em','+2233445566','emily.davis@example.com','1010 Road, Villagetown, Country','$2a$10$tP2sPWjGSq5LoXM0Ux8./ukCumt1VYbKxSbPl0n9fPZeHhiMedcHm','123','823 Nextbank',640),('CU0007','E.K','EK','+88633331111','tiredevance.k@gmail.com','1010 Road, Villagetown, Country','$2a$10$gyIcWFt8xfun0dYmDT6X..bKJkp5fudPBI7FmUbscNAR0/nDBvyNO','1234','823 Nextbank',0);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-15 14:16:37
