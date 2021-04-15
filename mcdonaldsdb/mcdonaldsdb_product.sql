-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mcdonaldsdb
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ProductId` int NOT NULL AUTO_INCREMENT COMMENT 'Identification of record product',
  `Name` varchar(100) NOT NULL COMMENT 'Name of the product',
  `Price` decimal(5,2) NOT NULL COMMENT 'Price of product before tax',
  `Type` int NOT NULL COMMENT 'Type of the product',
  PRIMARY KEY (`ProductId`),
  KEY `Product_Type_FK_idx` (`Type`),
  CONSTRAINT `Product_Type_FK` FOREIGN KEY (`Type`) REFERENCES `type` (`TypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'McChicken',8.10,2),(2,'Ayam Goreng McD Spicy (9ps)',51.90,2),(3,'Big Mac',10.40,2),(4,'McChicken Meal (large)',15.10,3),(5,'GCB Meal (medium)',16.85,3),(6,'Salted Egg Yolk Loaded Fries',6.70,4),(7,'Chicken McNuggets 20pcs',23.50,4),(8,'Bubur Ayam McD Happy Meal',10.30,7),(9,'Cheeseburger Happy Meal',10.15,7),(10,'Oreo McFlurry',6.60,5),(11,'Chocolate Sundae',4.15,5),(12,'Apple Pie',3.75,5),(13,'Cappuccino (large)',9.40,8),(14,'Iced Latte (medium)',8.95,8),(15,'Milo (medium)',6.90,6),(16,'Soft Drink (medium)',3.85,6),(17,'Chicken Bundle A - Regular (4-5pax)',65.00,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-13 23:33:55
