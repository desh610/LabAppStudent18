-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: labtest1
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_username` varchar(45) DEFAULT NULL,
  `appointment_type` varchar(100) DEFAULT NULL,
  `appointment_description` varchar(350) DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `technician_id` int DEFAULT NULL,
  `created_date_time` varchar(45) DEFAULT NULL,
  `checkup_date` varchar(45) DEFAULT NULL,
  `checkup_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `doctor_feedback` varchar(350) DEFAULT NULL,
  `test_results` varchar(3000) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `issued_date_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (21,'27','Kidney Function Tests','test',3,4,'2024-03-16 21:57:48','2024-03-18','10:30 AM','Completed',NULL,'[{\"ingradientName\":\"Blood Urea Nitrogen (BUN)\",\"hint\":\"Normal: 6-20 mg/dL\",\"value\":\"15\"},{\"ingradientName\":\"Creatinine\",\"hint\":\"Normal: 0.7-1.3 mg/dL\",\"value\":\"1.1\"},{\"ingradientName\":\"Estimated Glomerular Filtration Rate (eGFR)\",\"hint\":\"Normal: >60 mL/min/1.73m^2\",\"value\":\"50\"}]','paid','2024-03-16 22:34:21'),(22,'28','Complete Blood Count (CBC)','test',5,4,'2024-03-16 22:03:26','2024-03-18','10:30 AM','Completed',NULL,'[{\"ingradientName\":\"White Blood Cells (WBC)\",\"hint\":\"Normal: 4.0-11.0 x 10^9/L\",\"value\":\"7\"},{\"ingradientName\":\"Red Blood Cells (RBC)\",\"hint\":\"Normal: 4.5-5.9 x 10^12/L\",\"value\":\"4.8\"},{\"ingradientName\":\"Hemoglobin (Hgb)\",\"hint\":\"Normal: 13.5-17.5 g/dL\",\"value\":\"15\"},{\"ingradientName\":\"Hematocrit (Hct)\",\"hint\":\"Normal: 38.3-48.6%\",\"value\":\"41\"},{\"ingradientName\":\"Platelets\",\"hint\":\"Normal: 150-450 x 10^9/L\",\"value\":\"165\"}]','paid','2024-03-16 22:35:37');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `nic` varchar(45) DEFAULT NULL,
  `medical_category` varchar(45) DEFAULT NULL,
  `registered_date_time` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (3,'Dr Sarah','Jones','1234567','deshan.biss@gmail.com','101 Maple Ave','female','4325627v','cardiology','2024-03-16 19:54:41','skwo'),(4,'Dr Michael','Brown','1234567','michael@gmail.com','246 Pine St','male','54235768v','dermatology','2024-03-16 19:58:28','lcof'),(5,'Dr Emily','Davis','1234567','emily@gmail.com','369 Cedar Ave','female','1235647v','cardiology','2024-03-16 20:00:43','4564');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_receptionists`
--

DROP TABLE IF EXISTS `lab_receptionists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lab_receptionists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `nic` varchar(45) DEFAULT NULL,
  `registered_date_time` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_receptionists`
--

LOCK TABLES `lab_receptionists` WRITE;
/*!40000 ALTER TABLE `lab_receptionists` DISABLE KEYS */;
INSERT INTO `lab_receptionists` VALUES (1,'Luna','Jenkins','0771111111','luna@gmail.com','73 HOLBURN STREET, ABERDEEN','Female','123456',NULL,'1111');
/*!40000 ALTER TABLE `lab_receptionists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `nic` varchar(45) DEFAULT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  `registered_date_time` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (27,'James','Miller','1234567','deshan.biss@gmail.com','369 Cedar Ave','male','4326577v','1996-11-20','2024-03-16 21:33:46','ixtt'),(28,'Jessica','Wilson','1234567','deshan.biss@gmail.com','73 Walnut Ave','female','6564655v','1998-02-26','2024-03-16 21:38:19','cj9n'),(29,'William','Moore','1234567','william@gmail.com','698 Spruce St','male','2344334v','2000-10-19','2024-03-16 21:40:52','xcr3');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technicians`
--

DROP TABLE IF EXISTS `technicians`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technicians` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `nic` varchar(45) DEFAULT NULL,
  `registered_date_time` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technicians`
--

LOCK TABLES `technicians` WRITE;
/*!40000 ALTER TABLE `technicians` DISABLE KEYS */;
INSERT INTO `technicians` VALUES (4,'John','Smith','1234567','deshan.biss@gmail.com','123 Main St','male','1234567v','2024-03-16 19:38:06','24rk'),(5,'Mary','Johnson','1234567','mary@gmail.com','456 Elm St','female','7659873v','2024-03-16 19:47:16','ojju'),(6,'David','Williams','1234567','david@gmail.com','789 Oak St','male','1327637v','2024-03-16 19:48:28','lemm');
/*!40000 ALTER TABLE `technicians` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-17 19:18:56
