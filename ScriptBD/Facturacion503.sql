-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: facturacion503
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`codCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (5,'MARIA ','ARTERO GOMEZ','SAN SALVADOR'),(6,'Mario Alberto','Lopez Galvez','Sonsonate'),(7,'Clara Marlene','flores','Acajutla'),(8,'Maira Areli','Meza','Puerto el triunfo'),(9,'Liliana','Blanco','Usulutan'),(10,'Mirna','Soriano','La Union'),(11,'Danilo','Pleitez','Cabañas');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallefactura` (
  `codDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `codFactura` int(11) NOT NULL,
  `codProducto` int(11) NOT NULL,
  `codBarra` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `nombreProducto` varchar(75) COLLATE utf8_spanish_ci NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioVenta` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codDetalle`),
  KEY `FK_detallefactura_factura` (`codFactura`),
  KEY `FK_detallefactura_producto` (`codBarra`),
  KEY `FK_detallefactura_prodcto` (`codProducto`),
  CONSTRAINT `FK_detallefactura_factura` FOREIGN KEY (`codFactura`) REFERENCES `factura` (`codFactura`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_detallefactura_prodcto` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallefactura`
--

LOCK TABLES `detallefactura` WRITE;
/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
INSERT INTO `detallefactura` VALUES (1,1,6,'10101015','MEMORIA RAM DDR3',2,20.00,40.00),(2,1,7,'10101016','DICO DURO 300GB',1,50.00,50.00),(3,2,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(4,3,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(5,4,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(6,4,4,'10101013','CASE ATX',4,20.50,82.00),(7,5,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(8,6,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(9,7,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(10,8,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(11,9,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(12,10,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(13,11,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(14,12,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(15,13,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(16,14,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(17,15,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(18,16,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(19,17,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(20,18,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(21,19,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(22,20,3,'10101012','PANTALLA LED 24 °',3,250.30,750.90),(23,21,9,'10101018','MEMORIA USB 3.0',1,8.75,8.75),(24,22,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(25,23,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(26,24,9,'10101018','MEMORIA USB 3.0',1,8.75,8.75),(27,25,8,'10101017','UPS',1,25.95,25.95),(28,26,7,'10101016','DICO DURO 300GB',1,50.00,50.00),(29,27,7,'10101016','DICO DURO 300GB',2,50.00,100.00),(30,28,8,'10101017','UPS',1,25.95,25.95),(31,29,9,'10101018','MEMORIA USB 3.0',2,8.75,17.50),(32,29,8,'10101017','UPS',1,25.95,25.95),(33,29,4,'10101013','CASE ATX',2,20.50,41.00),(34,30,8,'10101017','UPS',1,25.95,25.95),(35,30,6,'10101015','MEMORIA RAM DDR3',1,20.00,20.00);
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger trgUpdateStock AFTER insert on detallefactura
for each row
BEGIN
  update producto set stockActual=stockActual-NEW.cantidad where codProducto=NEW.codProducto;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `codFactura` int(11) NOT NULL AUTO_INCREMENT,
  `numeroFactura` int(11) NOT NULL,
  `codVendedor` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `totalVenta` decimal(10,2) NOT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codFactura`),
  KEY `FK_factura_vendedor` (`codVendedor`),
  KEY `FK_factura_cliente` (`codCliente`),
  CONSTRAINT `FK_factura_cliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_factura_vendedor` FOREIGN KEY (`codVendedor`) REFERENCES `vendedor` (`codVendedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,1,4,6,90.00,'2019-03-08 22:38:09'),(2,2,4,11,250.30,'2019-03-09 01:48:49'),(3,3,4,8,250.30,'2019-03-10 01:24:17'),(4,4,4,5,332.30,'2019-03-10 02:02:09'),(5,5,5,11,500.60,'2019-03-14 23:25:34'),(6,6,5,7,250.30,'2019-03-18 20:30:42'),(7,7,5,11,250.30,'2019-03-18 20:42:13'),(8,8,5,9,500.60,'2019-03-18 20:46:06'),(9,9,5,10,250.30,'2019-03-18 20:48:09'),(10,10,5,9,250.30,'2019-03-18 20:57:15'),(11,11,5,6,250.30,'2019-03-18 20:59:41'),(12,12,5,10,250.30,'2019-03-18 21:15:29'),(13,13,5,5,250.30,'2019-03-18 21:26:07'),(14,14,5,7,250.30,'2019-03-18 21:46:16'),(15,15,5,11,250.30,'2019-03-18 21:55:52'),(16,16,5,7,250.30,'2019-03-18 22:08:35'),(17,17,5,10,500.60,'2019-03-18 22:24:37'),(18,18,5,9,250.30,'2019-03-18 22:28:13'),(19,19,5,7,250.30,'2019-03-18 22:32:21'),(20,20,5,9,750.90,'2019-03-18 22:36:25'),(21,21,5,11,8.75,'2019-03-18 22:37:30'),(22,22,5,9,250.30,'2019-03-18 23:07:44'),(23,23,5,5,500.60,'2019-03-18 23:10:33'),(24,24,5,11,8.75,'2019-03-18 23:13:14'),(25,25,5,5,25.95,'2019-03-18 23:57:01'),(26,26,5,6,50.00,'2019-03-19 00:12:10'),(27,27,5,6,100.00,'2019-03-19 00:17:19'),(28,28,5,8,25.95,'2019-03-19 00:38:38'),(29,29,4,10,84.45,'2019-03-19 01:58:46'),(30,30,5,8,45.95,'2019-03-19 18:22:50');
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `codProducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombreProducto` varchar(75) COLLATE utf8_spanish_ci NOT NULL,
  `precioVenta` decimal(10,2) NOT NULL,
  `stockMinimo` int(11) NOT NULL,
  `stockActual` int(11) NOT NULL,
  `codBarra` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`codProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (3,'PANTALLA LED 24 °',250.30,10,64,'10101012'),(4,'CASE ATX',20.50,10,38,'10101013'),(5,'LAMPARA CF',75.00,5,2,'10101014'),(6,'MEMORIA RAM DDR3',20.00,15,32,'10101015'),(7,'DICO DURO 300GB',50.00,25,119,'10101016'),(8,'UPS',25.95,5,26,'10101017'),(9,'MEMORIA USB 3.0',8.75,5,36,'10101018');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `codVendedor` int(11) NOT NULL,
  PRIMARY KEY (`codUsuario`),
  KEY `FK_usuario_vendedor` (`codVendedor`),
  CONSTRAINT `FK_usuario_vendedor` FOREIGN KEY (`codVendedor`) REFERENCES `vendedor` (`codVendedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,'CEAGUIRRE','e16d6b316f3bef1794c548b7a98b969a6aacb02f6ae5138efc1c443ae6643a6a77d92a0e33e382d6cbb7758f9ab25ab0f97504554d1904620a41fed463796fc2',3),(4,'MGUARDADO','c70b5dd9ebfb6f51d09d4132b7170c9d20750a7852f00680f65658f0310e810056e6763c34c9a00b0e940076f54495c169fc2302cceb312039271c43469507dc',4),(5,'AGARCIA','3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2',5);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `codVendedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `dui` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `celular` varchar(8) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`codVendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (3,'CECILIA CAROLINA','AGUIRRE','15231315','7894566','SAN MIGUEL'),(4,'Marcos Antonio','Guardado','1234567890','22345672','San Salvador'),(5,'Alvaro Manuel','Garcia Morales','0987654321','87564323','Santa Ana');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-21 16:32:59
