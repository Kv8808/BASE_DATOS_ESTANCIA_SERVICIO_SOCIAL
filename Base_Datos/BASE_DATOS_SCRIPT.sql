-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: estancia_lupita
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `cuidadores`
--

DROP TABLE IF EXISTS `cuidadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuidadores` (
  `id_cuidador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `edad` int(11) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `estado_civil` enum('SOLTERO','CASADO','UNION LIBRE','OTRO') DEFAULT NULL,
  `num_hijos` int(11) DEFAULT NULL,
  `ocupacion` varchar(100) DEFAULT NULL,
  `colonia` varchar(100) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `parentesco` varchar(50) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `calle_numero` varchar(255) DEFAULT NULL,
  `codigo_postal` varchar(20) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  `id_paciente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cuidador`),
  KEY `fk_paciente_cuidador` (`id_paciente`),
  CONSTRAINT `fk_paciente_cuidador` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuidadores`
--

LOCK TABLES `cuidadores` WRITE;
/*!40000 ALTER TABLE `cuidadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuidadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentos`
--

DROP TABLE IF EXISTS `documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documentos` (
  `id_documento` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) NOT NULL,
  `id_cuidador` int(11) DEFAULT NULL,
  `id_reingreso` int(11) DEFAULT NULL,
  `nombre_original` varchar(255) DEFAULT NULL,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `mime` varchar(100) DEFAULT NULL,
  `contenido` longblob DEFAULT NULL,
  `tamanio` bigint(20) DEFAULT NULL,
  `referencia_nombre` varchar(255) DEFAULT NULL,
  `fecha_subida` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_documento`),
  KEY `id_paciente` (`id_paciente`),
  KEY `id_cuidador` (`id_cuidador`),
  KEY `id_reingreso` (`id_reingreso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentos`
--

LOCK TABLES `documentos` WRITE;
/*!40000 ALTER TABLE `documentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `documentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresos_diarios`
--

DROP TABLE IF EXISTS `ingresos_diarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresos_diarios` (
  `id_ingreso` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) NOT NULL,
  `id_cuidador` int(11) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  PRIMARY KEY (`id_ingreso`),
  KEY `id_paciente` (`id_paciente`),
  KEY `id_cuidador` (`id_cuidador`),
  CONSTRAINT `ingresos_diarios_ibfk_1` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ingresos_diarios_ibfk_2` FOREIGN KEY (`id_cuidador`) REFERENCES `cuidadores` (`id_cuidador`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresos_diarios`
--

LOCK TABLES `ingresos_diarios` WRITE;
/*!40000 ALTER TABLE `ingresos_diarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingresos_diarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `id_paciente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `edad` int(11) DEFAULT NULL,
  `sexo` enum('FEMENINO','MASCULINO','OTRO') DEFAULT NULL,
  `estado_civil` enum('SOLTERO','CASADO','UNION LIBRE','OTRO') DEFAULT NULL,
  `num_hijos` int(11) DEFAULT NULL,
  `ocupacion` varchar(100) DEFAULT NULL,
  `colonia` varchar(100) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `diagnostico` varchar(255) DEFAULT NULL,
  `tratamiento` varchar(255) DEFAULT NULL,
  `duracion` varchar(50) DEFAULT NULL,
  `hospital` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `codigo_postal` varchar(20) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reingresos`
--

DROP TABLE IF EXISTS `reingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reingresos` (
  `id_reingreso` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) NOT NULL,
  `id_cuidador` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_reingreso`),
  KEY `id_paciente` (`id_paciente`),
  KEY `id_cuidador` (`id_cuidador`),
  CONSTRAINT `reingresos_ibfk_1` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reingresos_ibfk_2` FOREIGN KEY (`id_cuidador`) REFERENCES `cuidadores` (`id_cuidador`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reingresos`
--

LOCK TABLES `reingresos` WRITE;
/*!40000 ALTER TABLE `reingresos` DISABLE KEYS */;
/*!40000 ALTER TABLE `reingresos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-27 19:51:14
