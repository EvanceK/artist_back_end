<<<<<<< HEAD
-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (arm64)
=======
CREATE DATABASE  IF NOT EXISTS `artistproject` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `artistproject`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
>>>>>>> ab7c5e934acc07c728e2f3d13b174fa4c661bec3
--
-- Host: localhost    Database: artistproject
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
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `staff_name` varchar(45) NOT NULL,
  `staff_department` varchar(45) DEFAULT NULL,
  `staff_username` varchar(45) DEFAULT NULL,
<<<<<<< HEAD:MySql/sqldb1021bychenchen/artistproject_staff.sql
  `staff_passdword` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_username_UNIQUE` (`staff_username`)
=======
<<<<<<< HEAD
  `staff_password` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_username_UNIQUE` (`staff_username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
=======
  `staff_passdword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
>>>>>>> e0f80717c220e3f071c01ad29f5cbd0e52242cf2:MySql/sqldb1021/artistproject_staff.sql
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
>>>>>>> ab7c5e934acc07c728e2f3d13b174fa4c661bec3
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
<<<<<<< HEAD:MySql/sqldb1021bychenchen/artistproject_staff.sql
INSERT INTO `staff` VALUES (1,'Orange Chen','Managerment','3060301','3060301',NULL),(2,'Danny','Managerment','3060302','3060302',NULL),(3,'Evance','Managerment','3060303','3060303',NULL),(4,'Jack','Managerment','3060304','3060304',NULL),(5,'Wenyang','Managerment','3060305','3060305',NULL),(6,'Marry','Packing','3060306','3060306',NULL),(7,'John','Packing','3060307','3060307',NULL),(8,'Vicky','Packing','3060308','3060308',NULL),(9,'Allen','Delivery','3060309','3060309',NULL),(10,'Tom','Delivery','3060310','3060310',NULL);
=======
<<<<<<< HEAD
INSERT INTO `staff` VALUES (1,'Orange Chen','Managerment','3060301','3060301',1),(2,'Danny','Managerment','3060302','3060302',1),(3,'Evance','Managerment','3060303','3060303',1),(4,'Jack','Managerment','3060304','3060304',1),(5,'Wenyang','Managerment','3060305','3060305',2),(6,'Marry','Packing','3060306','3060306',2),(7,'John','Packing','3060307','3060307',2),(8,'Vicky','Packing','3060308','3060308',3),(9,'Allen','Delivery','3060309','3060309',3),(10,'Tom','Delivery','3060310','3060310',4),(11,'12','123',NULL,NULL,4),(12,'635463','4564356',NULL,NULL,5),(15,'Newstaff','NewDef','Newbie','$2a$10$NGdL3Qjslj6ZyIcUeDaMe.zEpPJhVxVUQEp35JQDwdpUMSzQpGgLa',5);
=======
INSERT INTO `staff` VALUES (1,'Orange Chen','Managerment','3060301','3060301'),(2,'Danny','Managerment','3060302','3060302'),(3,'Evance','Managerment','3060303','3060303'),(4,'Jack','Managerment','3060304','3060304'),(5,'Wenyang','Managerment','3060305','3060305'),(6,'Marry','Packing','3060306','3060306'),(7,'John','Packing','3060307','3060307'),(8,'Vicky','Packing','3060308','3060308'),(9,'Allen','Delivery','3060309','3060309'),(10,'Tom','Delivery','3060310','3060310');
>>>>>>> ab7c5e934acc07c728e2f3d13b174fa4c661bec3
>>>>>>> e0f80717c220e3f071c01ad29f5cbd0e52242cf2:MySql/sqldb1021/artistproject_staff.sql
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

<<<<<<< HEAD:MySql/sqldb1021bychenchen/artistproject_staff.sql
-- Dump completed on 2024-10-21 20:41:10
=======
<<<<<<< HEAD
-- Dump completed on 2024-10-21 15:39:10
=======
-- Dump completed on 2024-10-21 13:13:00
>>>>>>> ab7c5e934acc07c728e2f3d13b174fa4c661bec3
>>>>>>> e0f80717c220e3f071c01ad29f5cbd0e52242cf2:MySql/sqldb1021/artistproject_staff.sql
