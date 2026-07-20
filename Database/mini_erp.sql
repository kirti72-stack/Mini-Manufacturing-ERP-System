-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: mini_erp
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `audit_logs`
--

DROP TABLE IF EXISTS `audit_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_logs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_logs`
--

LOCK TABLES `audit_logs` WRITE;
/*!40000 ALTER TABLE `audit_logs` DISABLE KEYS */;
INSERT INTO `audit_logs` VALUES (1,'CREATE PRODUCT','Cement','2026-03-13 20:06:17.756079','ADMIN'),(2,'CREATE PRODUCT','Laptop','2026-03-22 13:21:14.009675','ADMIN'),(3,'CREATE PRODUCT','Laptop','2026-03-22 13:21:16.620684','ADMIN'),(4,'CREATE PRODUCT','Motor','2026-03-22 17:49:07.629385','ADMIN'),(5,'CREATE PRODUCT','Test','2026-03-26 22:23:23.317731','ADMIN'),(6,'CREATE PRODUCT','xy','2026-03-27 13:41:01.030389','ADMIN'),(7,'DEACTIVATE PRODUCT','xy','2026-03-27 13:41:31.482253','ADMIN'),(8,'CREATE PRODUCT','Wood','2026-03-28 00:05:37.952348','ADMIN'),(9,'CREATE PRODUCT','paint','2026-03-30 20:06:59.261592','ADMIN'),(10,'DEACTIVATE PRODUCT','paint','2026-03-30 20:07:13.561985','ADMIN'),(11,'CREATE PRODUCT','pot','2026-03-30 20:14:40.669631','ADMIN'),(12,'DEACTIVATE PRODUCT','pot','2026-03-30 20:57:22.605883','ADMIN'),(13,'CREATE_PURCHASE','PURCHASE','2026-04-12 12:49:24.494021','admin'),(14,'CREATE_SALE','SALES','2026-04-12 12:50:18.998541','admin'),(15,'CREATE_PURCHASE','PURCHASE','2026-04-27 11:37:53.122634','ADMIN'),(16,'CREATE PRODUCT','Books','2026-04-27 11:53:27.529674','ADMIN'),(17,'CREATE_PURCHASE','PURCHASE','2026-04-27 11:55:19.438618','ADMIN'),(18,'CREATE_SALE','SALES','2026-04-27 11:56:05.194784','ADMIN'),(19,'CREATE PRODUCT','Water colours','2026-04-27 11:58:29.562910','ADMIN'),(20,'CREATE_PURCHASE','PURCHASE','2026-04-27 11:59:44.453948','ADMIN'),(21,'CREATE_PURCHASE','PURCHASE','2026-04-27 19:50:50.557045','ADMIN'),(22,'CREATE_PRODUCT','PRODUCT','2026-04-27 21:54:33.308777','admin'),(23,'UPDATE_PRODUCT','PRODUCT','2026-04-27 21:55:00.438621','admin'),(24,'UPDATE_PRODUCT','PRODUCT','2026-04-27 21:56:47.588804','manager'),(25,'CREATE_SALE','SALES','2026-04-27 23:07:46.563922','admin'),(26,'CREATE_SALE','SALES','2026-04-27 23:08:43.772916','manager'),(27,'CREATE_PURCHASE','PURCHASE','2026-04-27 23:27:13.953091','ADMIN'),(28,'CREATE_PURCHASE','PURCHASE','2026-04-27 23:28:46.525069','ADMIN'),(29,'CREATE_PRODUCT','PRODUCT','2026-06-11 20:30:58.684164','manager'),(30,'UPDATE_PRODUCT','PRODUCT','2026-06-11 20:33:51.418479','manager'),(31,'CREATE_PURCHASE','PURCHASE','2026-06-11 20:44:16.042853','ADMIN'),(32,'CREATE_PURCHASE','PURCHASE','2026-06-11 20:49:29.600546','ADMIN'),(33,'CREATE_SALE','SALES','2026-06-11 20:50:11.758817','manager');
/*!40000 ALTER TABLE `audit_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (2,'2026-03-10 22:21:56.195656','2026-04-27 22:10:55.289204','Mumbai','rahul@gmail.com','Rahul','9876543202',NULL),(4,'2026-03-22 01:05:26.882497','2026-03-22 13:27:35.799072','Delhi','priya@gmail.com','Priya','8547129657',NULL),(5,'2026-03-22 12:39:20.855805','2026-03-22 12:39:50.883720','Pune','sameer@gmail.com','Sameer','9678571230',NULL),(7,'2026-04-27 11:50:01.320658','2026-04-27 11:50:01.320658','Agra','mansi@gmail.com','Mansi','9137506527',_binary ''),(8,'2026-04-27 11:51:04.443208','2026-04-27 11:51:39.002567','Pune','tina@gmail.com','Tina','8591621010',_binary ''),(18,'2026-04-27 23:27:55.304317','2026-04-27 23:27:55.304317','Delhi','roshni@gmail.com','Roshni','9934752901',_binary '');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `warehouse_location` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ce3rbi3bfstbvvyne34c1dvyv` (`product_id`),
  CONSTRAINT `FKq2yge7ebtfuvwufr6lwfwqy9l` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `inventory_chk_1` CHECK ((`quantity` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (2,8,'Main Warehouse',1,NULL,'2026-04-11 23:29:00.179411',NULL),(3,11,'Main Warehouse',7,'2026-03-22 17:49:07.566191','2026-04-12 12:49:24.076404',NULL),(4,90,'A1',2,NULL,'2026-03-27 22:40:12.013971',NULL),(5,15,'A1',3,NULL,'2026-06-11 20:50:11.681481',NULL),(6,98,'A1',4,NULL,'2026-04-27 11:37:52.102986',NULL),(7,30,'A1',5,NULL,'2026-03-27 23:02:27.151457',NULL),(8,12,NULL,8,'2026-03-26 22:23:23.152671','2026-04-27 19:50:50.145582',NULL),(10,33,NULL,10,'2026-03-28 00:05:37.777090','2026-04-27 23:08:43.721158',NULL),(11,0,NULL,11,'2026-03-30 20:06:59.214063','2026-03-30 20:06:59.214063',NULL),(12,0,NULL,12,'2026-03-30 20:14:40.638263','2026-03-30 20:14:40.638263',NULL),(13,3,NULL,13,'2026-04-27 11:53:27.493747','2026-04-27 23:07:46.362025',_binary ''),(14,10,NULL,14,'2026-04-27 11:58:29.549196','2026-04-27 11:59:44.404732',_binary ''),(15,5,NULL,15,'2026-04-27 21:54:33.239117','2026-04-27 23:28:46.420308',_binary ''),(16,50,NULL,16,'2026-06-11 20:30:58.655249','2026-06-11 20:44:15.853954',_binary '');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Construction Material','Steel Rod',500,100,NULL,NULL,1),(2,'Industrial Motor','Electric Motor',7000,20,NULL,NULL,1),(3,'Industrial Gear','Gear',1200,50,NULL,NULL,1),(4,'Construction material','Cement',400,50,'2026-03-13 20:06:17.476532','2026-03-13 20:06:17.478680',1),(5,'Dell','Laptop',50000,10,'2026-03-22 13:21:13.834404','2026-03-22 15:44:58.212108',1),(7,'Motor','Motor',5000,20,'2026-03-22 17:49:07.198484','2026-04-26 19:21:53.695093',1),(8,'Sample','Test',500,25,'2026-03-26 22:23:22.804278','2026-03-26 22:24:11.398244',1),(9,'Dell','xy',8500,6,'2026-03-27 13:41:00.173221','2026-03-27 13:41:31.418846',0),(10,'Construction Material','Wood',6000,50,'2026-03-28 00:05:37.197776','2026-03-28 00:05:37.207579',1),(11,'Construction Material','paint',6000,50,'2026-03-30 20:06:58.926877','2026-03-30 20:07:13.477527',0),(12,'hngn','pot',202,2,'2026-03-30 20:14:40.511693','2026-03-30 20:57:22.578052',0),(13,'Stationary','Books',100,60,'2026-04-27 11:53:27.462338','2026-04-27 11:53:27.462338',1),(14,'Stationary','Water colours',80,100,'2026-04-27 11:58:29.436087','2026-04-27 11:58:29.452101',1),(15,'Lenovo','Laptop',75000,30,'2026-04-27 21:54:33.118636','2026-04-27 21:56:47.599934',1),(16,'Cloths','Uniform',500,300,'2026-06-11 20:30:58.343938','2026-06-11 20:33:51.575541',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `product_id` bigint NOT NULL,
  `supplier_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsfqpk5xjv93po29vn4fmy5exq` (`product_id`),
  KEY `FK8omm6fki86s9oqk0o9s6w43h5` (`supplier_id`),
  CONSTRAINT `FK8omm6fki86s9oqk0o9s6w43h5` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FKsfqpk5xjv93po29vn4fmy5exq` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (3,500,5,1,1,NULL,NULL,NULL),(4,500,5,1,1,NULL,NULL,NULL),(5,500,5,1,1,'2026-03-10 19:22:51.290991','2026-04-11 23:28:59.997358',_binary '\0'),(6,500,4,1,1,'2026-03-11 21:52:54.034415','2026-03-11 21:52:54.034415',NULL),(7,1000,10,1,1,'2026-03-22 16:32:13.416126','2026-03-22 16:32:13.416126',NULL),(9,500,5,4,3,'2026-03-27 22:00:05.458721','2026-03-27 22:00:05.458721',NULL),(10,1000,10,8,3,'2026-03-27 22:00:30.108168','2026-03-27 22:00:30.108168',NULL),(11,6000,50,10,3,'2026-03-28 20:59:46.133786','2026-03-28 20:59:46.133786',NULL),(12,2000,5,8,3,'2026-04-11 23:14:17.533821','2026-04-11 23:14:47.934342',_binary '\0'),(13,5000,10,7,12,'2026-04-12 12:49:24.347291','2026-04-12 12:49:24.347291',_binary ''),(14,2000,10,4,13,'2026-04-27 11:37:52.831320','2026-04-27 11:37:52.831320',_binary ''),(15,55,10,13,13,'2026-04-27 11:55:19.398938','2026-04-27 11:55:19.398938',_binary ''),(16,70,10,14,13,'2026-04-27 11:59:44.426571','2026-04-27 11:59:44.426571',_binary ''),(17,300,2,8,1,'2026-04-27 19:50:50.430344','2026-04-27 19:50:50.430344',_binary ''),(18,65000,5,15,14,'2026-04-27 23:27:13.791975','2026-04-27 23:27:28.545135',_binary '\0'),(19,65000,5,15,14,'2026-04-27 23:28:46.475811','2026-04-27 23:28:46.475811',_binary ''),(20,450,50,16,14,'2026-06-11 20:44:15.933968','2026-06-11 20:44:15.933968',_binary ''),(21,600,20,3,12,'2026-06-11 20:49:29.562792','2026-06-11 20:49:29.562792',_binary '');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `product_id` bigint NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7psqmmgealuglinly88ceyy7w` (`product_id`),
  KEY `FKgu5m08xwl91yvewpb6d327nj6` (`customer_id`),
  CONSTRAINT `FK7psqmmgealuglinly88ceyy7w` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKgu5m08xwl91yvewpb6d327nj6` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,'2026-03-10 14:52:34.587586','2026-03-10 14:52:34.587586',500,2,1,NULL,NULL),(2,'2026-03-10 15:43:32.321776','2026-03-10 15:43:32.321776',500,2,1,NULL,NULL),(3,'2026-03-10 22:11:25.716969','2026-03-10 22:11:25.716969',500,2,1,NULL,NULL),(4,'2026-03-10 22:41:56.129044','2026-03-10 22:41:56.129044',500,3,1,NULL,NULL),(5,'2026-03-10 22:49:18.692239','2026-03-10 22:49:18.692239',500,3,1,2,NULL),(6,'2026-03-11 21:57:06.532756','2026-03-11 21:57:06.532756',500,2,1,2,NULL),(7,'2026-03-22 16:58:46.050656','2026-03-22 16:58:46.050656',500,2,1,2,NULL),(8,'2026-03-22 17:36:24.154748','2026-03-22 17:36:24.154748',200,2,1,4,NULL),(9,'2026-03-23 21:11:55.024176','2026-03-23 21:11:55.024176',2500,5,1,2,NULL),(10,'2026-03-23 21:14:56.996905','2026-03-23 21:14:56.996905',1500,2,7,5,NULL),(11,'2026-03-23 21:43:17.151104','2026-03-23 21:43:17.151104',6000,12,4,4,NULL),(12,'2026-03-23 21:43:39.850477','2026-03-23 21:43:39.850477',1000,5,4,4,NULL),(13,'2026-03-27 22:40:12.234626','2026-03-27 22:40:12.234626',7000,10,2,5,NULL),(14,'2026-03-27 22:41:10.160305','2026-03-27 22:41:10.160305',50000,20,5,4,NULL),(15,'2026-03-27 23:02:27.278302','2026-03-27 23:02:27.278302',50000,50,5,2,NULL),(16,'2026-03-27 23:03:06.941475','2026-03-27 23:03:06.941475',1200,100,3,4,NULL),(17,'2026-03-27 23:48:02.565704','2026-03-27 23:48:02.565704',5000,2,7,5,NULL),(18,'2026-03-28 21:00:46.684487','2026-03-28 21:00:46.684487',6000,10,10,5,NULL),(19,'2026-04-12 12:50:18.904650','2026-04-12 12:50:18.904650',6000,5,10,5,_binary ''),(20,'2026-04-27 11:56:05.143833','2026-04-27 11:56:05.143833',100,5,13,8,_binary ''),(21,'2026-04-27 23:07:46.476008','2026-04-27 23:07:46.476008',100,2,13,8,_binary ''),(22,'2026-04-27 23:08:43.740297','2026-04-27 23:08:43.740297',6000,2,10,7,_binary ''),(23,'2026-06-11 20:50:11.731790','2026-06-11 20:50:11.731790',1200,5,3,8,_binary '');
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_movement`
--

DROP TABLE IF EXISTS `stock_movement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_movement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhj6nusfm483wy67vt7v7hjdma` (`product_id`),
  CONSTRAINT `FKhj6nusfm483wy67vt7v7hjdma` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_movement`
--

LOCK TABLES `stock_movement` WRITE;
/*!40000 ALTER TABLE `stock_movement` DISABLE KEYS */;
INSERT INTO `stock_movement` VALUES (1,4,'PURCHASE',1),(2,2,'SALE',1),(3,10,'PURCHASE',1),(4,2,'SALE',1),(5,2,'SALE',1),(6,5,'SALE',1),(7,2,'SALE',7),(8,12,'SALE',4),(9,5,'SALE',4),(10,2,'PURCHASE',1),(11,5,'PURCHASE',4),(12,10,'PURCHASE',8),(13,10,'SALE',2),(14,20,'SALE',5),(15,50,'SALE',5),(16,100,'SALE',3),(17,2,'SALE',7),(18,50,'PURCHASE',10),(19,10,'SALE',10),(20,5,'PURCHASE',8),(21,5,'PURCHASE_CANCEL',8),(22,5,'PURCHASE_CANCEL',1),(23,10,'PURCHASE',7),(24,5,'SALE',10),(25,10,'PURCHASE',4),(26,10,'PURCHASE',13),(27,5,'SALE',13),(28,10,'PURCHASE',14),(29,2,'PURCHASE',8),(30,2,'SALE',13),(31,2,'SALE',10),(32,5,'PURCHASE',15),(33,5,'PURCHASE_CANCEL',15),(34,5,'PURCHASE',15),(35,50,'PURCHASE',16),(36,20,'PURCHASE',3),(37,5,'SALE',3);
/*!40000 ALTER TABLE `stock_movement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g7qiwwu4vpciysmeeyme9gg1d` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Pune','ABC Traders','rahul@gmail.com','Rahul Sharma','9876543210',NULL,NULL,_binary ''),(3,'Delhi','XZY Trading','vicky@gmail.com','Vicky Singh','7598431602','2026-03-22 16:18:56.268709','2026-03-22 16:18:56.268709',_binary ''),(4,'HJBJK55','Textiles','abc@gmail.com','ABC','7452196850','2026-03-31 19:07:02.538567','2026-03-31 19:08:13.614323',_binary '\0'),(10,'Mumbai','PQR Traders','karan@gmail.com','Karan Patil','9573284913','2026-03-31 20:34:04.530519','2026-03-31 20:55:50.354356',_binary '\0'),(11,'Banglore','RST','sayali123@gmail.com','Sayali Bhosale','7538169028','2026-03-31 20:48:13.189898','2026-03-31 20:49:42.600899',_binary '\0'),(12,'Mumbai','MNQ Textiles','ashi08@gmail.com','Ashi Singh','7318642809','2026-03-31 20:59:24.837945','2026-04-27 21:55:40.243470',_binary ''),(13,'Pune','Verma Textiles','pooja@gmail.com','Pooja Verma','9437821506','2026-04-13 11:22:27.002162','2026-04-13 11:22:27.002162',_binary ''),(14,'Pune','Joshi Company','mona@gmail.com','Mona Joshi','7314698260','2026-04-27 21:58:24.069215','2026-04-27 21:58:24.069215',_binary '');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MANAGER','STAFF') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$xTHmsLk3XXDjVZw91ekeEepWpWQZ6SaRRnDiyhLCYBjvdp/x9x2C2','admin','ADMIN'),(2,'$2a$10$HrjNl7VYgR7kQGoSjVbaf.92koj.6ALaaZvnbJ0xmZl.XtL18t/s6','manager','MANAGER'),(3,'$2a$10$8Ms23rLkIFNCz2srS23RDu6gdmTlzFEcv5keX6GnMDnxt8zemTNdu','staff','STAFF');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-19 21:02:23
