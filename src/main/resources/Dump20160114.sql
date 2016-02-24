-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.27

DROP DATABASE IF EXISTS `mydb`;
CREATE DATABASE `mydb`
  CHARACTER SET 'utf8mb4'
  COLLATE 'utf8mb4_general_ci';

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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `mydb`.`Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mydb`.`Address` (
  `idAddress` int(10) NOT NULL AUTO_INCREMENT,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `index` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `mydb`.`Address` WRITE;
/*!40000 ALTER TABLE `mydb`.`Address` DISABLE KEYS */;
INSERT INTO `mydb`.`Address` VALUES (1,'Россия','Китай','',''),(2,NULL,'Grodno',NULL,NULL),(3,'','','',''),(4,'','','',''),(5,'','','',''),(6,'','','',''),(7,'','','',''),(8,'','','',''),(9,'','','',''),(10,'','','',''),(11,'','','',''),(12,'','','',''),(13,'','','',''),(14,'','','',''),(15,'','','',''),(16,'','','',''),(17,'','','',''),(18,'Belarus','Grodno','str. Lenina 5',''),(19,'','','',''),(20,'','','',''),(21,'','','',''),(22,'','','',''),(23,'','','',''),(24,'','','',''),(25,'','','',''),(26,'','','',''),(27,'','','',''),(28,'','','',''),(29,'','','',''),(30,'','','',''),(31,'','','',''),(32,'','','',''),(33,'','','',''),(34,'','','',''),(35,'','','',''),(36,'','','',''),(37,'','','',''),(38,'','','',''),(39,'','','',''),(40,'','','',''),(41,'','','',''),(42,'','','',''),(43,'','','',''),(44,'','','',''),(45,'','','',''),(46,'','','',''),(47,'','','',''),(48,'','','',''),(49,'','','',''),(50,'','','',''),(51,'','','',''),(52,'','','',''),(53,'','','',''),(54,'','','',''),(55,'','','',''),(56,'','','',''),(57,'Belarus','minsk','','223131'),(58,'Belarus','minsk','','223131'),(59,'Belarus','minsk','','223131'),(60,'','test','',''),(61,'','test','',''),(62,'','test','',''),(63,'','','',''),(64,'','','',''),(65,'','','',''),(66,'','','',''),(67,'','','',''),(68,'','','',''),(69,'Китай','','',''),(70,'Беларусь','Мозырь','Б Юности',''),(71,'Russia','','',''),(72,'','','',''),(73,'','','',''),(74,'','','',''),(75,'','','',''),(76,'','','',''),(77,'','','',''),(78,'','','',''),(79,'','','',''),(80,'','','',''),(81,'Беларусь','','',''),(82,'','','',''),(83,'','','',''),(84,'','','',''),(85,'','','',''),(86,'','','',''),(87,'','','',''),(88,'','','',''),(89,'','','',''),(90,'','','',''),(91,'','','',''),(92,'','','',''),(93,'','','',''),(94,'','','',''),(95,'','','',''),(96,'Belarus','minsk','',''),(97,'','','',''),(98,'Украина','Киев','Крещатик 7','221 223 '),(99,'','','',''),(100,'','','',''),(101,'','','',''),(102,'','','',''),(103,'','','',''),(104,'','','',''),(105,'','','',''),(106,'','','',''),(107,'','','',''),(108,'','','',''),(109,'','','',''),(110,'','','',''),(111,'','','',''),(112,'','','',''),(113,'','','',''),(114,'','','',''),(115,'','','',''),(116,'','','',''),(117,'','','',''),(118,'','','',''),(119,'','','',''),(120,'','','',''),(121,'','','',''),(122,'','','',''),(123,'','','',''),(124,'','','',''),(125,'','','',''),(126,'','','',''),(127,'','','',''),(128,'','','',''),(129,'','','',''),(130,'','','',''),(131,'','','',''),(132,'','','',''),(133,'','','',''),(134,'','','',''),(135,'','','',''),(136,'','','',''),(137,'','','',''),(138,'','','',''),(139,'','','',''),(140,'','','',''),(141,'','','',''),(142,'','','',''),(143,'','','',''),(144,'','','',''),(145,'','','',''),(146,'','','',''),(147,'','','',''),(148,'','','',''),(149,'','','',''),(150,'','','',''),(151,'','','',''),(152,'','','',''),(153,'','','',''),(154,'','','',''),(155,'','','',''),(156,'','','',''),(157,'','','',''),(158,'','','',''),(159,'','','',''),(160,'','','',''),(161,'','','',''),(162,'','','',''),(163,'','','',''),(164,'','','',''),(165,'','','',''),(166,'','','',''),(167,'','','',''),(168,'','','',''),(169,'','','',''),(170,'','','',''),(171,'','','',''),(172,'','','',''),(173,'','','',''),(174,'','','',''),(175,'','','',''),(176,'','','',''),(177,'','','',''),(178,'','','',''),(179,'','','',''),(180,'','','',''),(181,'','','',''),(182,'','','',''),(183,'','','',''),(184,'','','',''),(185,'','','',''),(186,'','','',''),(187,'','','',''),(188,'','','',''),(189,'','','',''),(190,'','','',''),(191,'','','',''),(192,'','','',''),(193,'','','',''),(194,'','','',''),(195,'','','',''),(196,'','','',''),(197,'','','',''),(198,'','','',''),(199,'','','',''),(200,'','','',''),(201,'','','',''),(202,'','','',''),(203,'','','',''),(204,'','','',''),(205,'','','',''),(206,'','','',''),(207,'','','',''),(208,'','','',''),(209,'','','',''),(210,'','','',''),(211,'','','',''),(212,'','','',''),(213,'','','',''),(214,'','','',''),(215,'','','',''),(216,'','','',''),(217,'','','',''),(218,'','','',''),(219,'','','',''),(220,'','','',''),(221,'','','',''),(222,'','','',''),(223,'','','',''),(224,'','','',''),(225,'','','',''),(226,'','','',''),(227,'','','',''),(228,'','','',''),(229,'','','',''),(230,'','','',''),(231,'','','',''),(232,'','','',''),(233,'','','',''),(234,'','','',''),(235,'','','',''),(236,'','','',''),(237,'','','',''),(238,'','','',''),(239,'','','',''),(240,'','','',''),(241,'','','',''),(242,'','','',''),(243,'','','',''),(244,'','','',''),(245,'','','',''),(246,'','','',''),(247,'','','',''),(248,'','','',''),(249,'','','',''),(250,'','','',''),(251,'','','',''),(252,'','','',''),(253,'','','',''),(254,'','','',''),(255,'','','',''),(256,'','','',''),(257,'','','',''),(258,'','','',''),(259,'','','',''),(260,'','','',''),(261,'','','',''),(262,'','','',''),(263,'','','',''),(264,'','','',''),(265,'','','',''),(266,'','','',''),(267,'','','',''),(268,'','','',''),(269,'','','',''),(270,'','','',''),(271,'','','',''),(272,'','','',''),(273,'','','',''),(274,'','','',''),(275,'','','',''),(276,'','','',''),(277,'','','',''),(278,'','','',''),(279,'','','',''),(280,'','','',''),(281,'','','',''),(282,'','','',''),(283,'','','',''),(284,'','','',''),(285,'','','',''),(286,'','','',''),(287,'','','',''),(288,'','','',''),(289,'','','',''),(290,'','','',''),(291,'','','',''),(292,'','','',''),(293,'','','',''),(294,'','','',''),(295,'','','',''),(296,'','','',''),(297,'','','',''),(298,'','','',''),(299,'','','',''),(300,'','','',''),(301,'','','',''),(302,'','','',''),(303,'','','',''),(304,'','','',''),(305,'','','',''),(306,'','','',''),(307,'','','',''),(308,'','','',''),(309,'','','',''),(310,'','','',''),(311,'','','',''),(312,'','','',''),(313,'','','',''),(314,'','','',''),(315,'','','',''),(316,'','','',''),(317,'','','',''),(318,'','','',''),(319,'','','',''),(320,'','','',''),(321,'','','',''),(322,'','','',''),(323,'','','',''),(324,'','','',''),(325,'','','',''),(326,'','','',''),(327,'','','',''),(328,'','','',''),(329,'','','',''),(330,'','','',''),(331,'','','',''),(332,'','','',''),(333,'','','',''),(334,'','','',''),(335,'','','',''),(336,'','','',''),(337,'','','',''),(338,'','','',''),(339,'','','',''),(340,'','','',''),(341,'Беларусь','Мозырь','Б Юности','122313'),(342,'','','',''),(343,'','','',''),(344,'','','',''),(345,'','','',''),(346,'','','',''),(347,'','','',''),(348,'','','',''),(349,'','','',''),(350,'','','',''),(351,'','','',''),(352,'','','',''),(353,'','','',''),(354,'','','',''),(355,'','','',''),(356,'','','',''),(357,'','','',''),(358,'','','',''),(359,'','','',''),(360,'','','',''),(361,'','','',''),(362,'','','',''),(363,'','','',''),(364,'','','',''),(365,'','','',''),(366,'','','',''),(367,'','','',''),(368,'','','',''),(369,'','','','');
/*!40000 ALTER TABLE `mydb`.`Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Attachment`
--

DROP TABLE IF EXISTS `mydb`.`Attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mydb`.`Attachment` (
  `idAttach` int(10) NOT NULL AUTO_INCREMENT,
  `path` varchar(21000) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `idContact` int(10) DEFAULT NULL,
  PRIMARY KEY (`idAttach`),
  UNIQUE KEY `idAttach_UNIQUE` (`idAttach`),
  KEY `AttachFK_idx` (`idContact`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attachment`
--

LOCK TABLES `mydb`.`Attachment` WRITE;
/*!40000 ALTER TABLE `mydb`.`Attachment` DISABLE KEYS */;
INSERT INTO `mydb`.`Attachment` VALUES (6,'/Users/zhenya/attach/398/jvdJEqfktTs.jpg','jvdJEqfktTs.jpg','2016-01-12','goose',398),(7,'/Users/zhenya/attach/398/картаэкология.jpg','картаэкология.jpg','2016-01-12','good',398),(52,'/Users/zhenya/attach/399/1d81cf68285887.mp3','1d81cf68285887.mp3','2016-01-12','music',399),(53,'/Users/zhenya/attach/399/картаэкология.jpg','картаэкология.jpg','2016-01-12','карта экология',399),(54,'/Users/zhenya/attach/399/scheme_of_database_abapcourse5_2.PNG','scheme_of_database_abapcourse5_2.PNG','2016-01-13','пр',399),(55,'/Users/zhenya/attach/403/4358159410128796501Резюме_Пархоменко_Евгений.pdf','Резюме_Пархоменко_Евгений.pdf','2016-01-13','nm',403),(56,'/Users/zhenya/attach/404/4988804410631474759Резюме_Пархоменко_Евгений.pdf','Резюме_Пархоменко_Евгений.pdf','2016-01-13','hjj',404),(57,'/Users/zhenya/attach/405/20130618_213911.jpg','20130618_213911.jpg','2016-01-13','fdf',405),(58,'/Users/zhenya/attach/407/37028460529867276Резюме_Пархоменко_Евгений.pdf','37028460529867276Резюме_Пархоменко_Евгений.pdf','2016-01-13','fd',407),(59,'/Users/zhenya/attach/408/5178543093055709921Резюме_Пархоменко_Евгений.pdf','5178543093055709921Резюме_Пархоменко_Евгений.pdf','2016-01-13','gff',408),(61,'/Users/zhenya/attach/409/1867159776023398331ico.zip','1867159776023398331ico.zip','2016-01-13','m',409),(75,'/Users/zhenya/attach/415/589587743042067208820130618_213911.jpg','589587743042067208820130618_213911.jpg','2016-01-14','',415),(76,'/Users/zhenya/attach/415/19109554079361164014486211972760.webm','19109554079361164014486211972760.webm','2016-01-14','hello \r\nmy \r\nfriend',415),(77,'/Users/zhenya/attach/418/497831992092806891LnDLOSPdtGY.jpg','497831992092806891LnDLOSPdtGY.jpg','2016-01-14','fd',418),(78,'/Users/zhenya/attach/421/5731769902243972391LnDLOSPdtGY.jpg','5731769902243972391LnDLOSPdtGY.jpg','2016-01-14','fsd',421),(83,'/Users/zhenya/attach/1/6094137606265605293Мой первый сайт.png','6094137606265605293Мой первый сайт.png','2016-01-14','rer',1),(84,'/Users/zhenya/attach/1/2586154470266765405картаэкология.jpg','2586154470266765405картаэкология.jpg','2016-01-14','ав',1),(85,'/Users/zhenya/attach/1/541730516026018491LzqBTpLbJ4Q.jpg','541730516026018491LzqBTpLbJ4Q.jpg','2016-01-14','k',1),(86,'/Users/zhenya/attach/1/352317157188439851GyjnzkSQabg.jpg','352317157188439851GyjnzkSQabg.jpg','2016-01-14','аыы',1),(87,'/Users/zhenya/attach/426/2853775102284848459LnDLOSPdtGY.jpg','2853775102284848459LnDLOSPdtGY.jpg','2016-01-14','fd',426),(88,'/Users/zhenya/attach/426/4352017469669621302rZlNL1zC6cI.jpg','4352017469669621302rZlNL1zC6cI.jpg','2016-01-14','f',426),(89,'/Users/zhenya/attach/427/1973898246775328169rZlNL1zC6cI.jpg','1973898246775328169rZlNL1zC6cI.jpg','2016-01-14','k',427),(90,'/Users/zhenya/attach/427/1379055259235274274LnDLOSPdtGY.jpg','1379055259235274274LnDLOSPdtGY.jpg','2016-01-14','',427);
/*!40000 ALTER TABLE `mydb`.`Attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contact`
--

DROP TABLE IF EXISTS `mydb`.`Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mydb`.`Contact` (
  `idContact` int(10) NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `middName` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(90) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `maritStatus` varchar(45) DEFAULT NULL,
  `national` varchar(45) DEFAULT NULL,
  `photo` varchar(21386) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `idAddress` int(10) DEFAULT NULL,
  PRIMARY KEY (`idContact`),
  UNIQUE KEY `idContact_UNIQUE` (`idContact`),
  KEY `fk_Contact_Address1_idx` (`idAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=428 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contact`
--

LOCK TABLES `mydb`.`Contact` WRITE;
/*!40000 ALTER TABLE `mydb`.`Contact` DISABLE KEYS */;
INSERT INTO `mydb`.`Contact` VALUES (1,'Жилунов','Максим','Павлович',NULL,'','Male','холост','gf',NULL,'www.gmail.com','itechart',96),(2,'Залесин','Петр','Михалыч',NULL,'','Male','','','/Users/zhenya/avatars/2/2.jpeg','','',0),(3,'Рысин','Игорь','Федорович',NULL,'egenpark@gmail.com','Male','','',NULL,'','',76),(134,'Пархоменко','Евгений','Владимирович','1994-04-09','','Male','Холост','',NULL,'','',70),(140,'Пархоменко','Евгений','Владимирович',NULL,'','Male','','',NULL,'','',80),(141,'test','test','',NULL,'teeeeeeeest__teest@gmail.com','Male','','',NULL,'','',81),(142,'test','test','test',NULL,'teeeeeeeest__teest@gmail.com','Male','','',NULL,'','IBA',82),(143,'test','Василий','',NULL,'test33333@email.com','Male','','',NULL,'','',83),(146,'test','test','',NULL,'teeeeeeeest__teest@gmail.com','Male','','',NULL,'','',86),(235,'21','12','',NULL,'','Male','','','/Users/zhenya/avatars/5/3/2/235.jpeg','','',177),(237,'test','test','test',NULL,'teeeest__teest@gmail.com','Male','','',NULL,'','',179),(239,'test','test','',NULL,'','Male','','',NULL,'','',181),(263,'Власов','Женя','fdsfds',NULL,'tes3333t@email.com','Male','','',NULL,'','',205),(305,'test','test','',NULL,'test@email.com','Male','','',NULL,'','',247),(306,'test','test','',NULL,'tut@mail.ru','Male','','',NULL,'','',248),(399,'Ежков','Василий','Сергееевич','1967-12-09','jekapson94@gmail.com','Male','холост','белорус',NULL,'','IBA',341),(408,'net','da','',NULL,'egenpark@gmail.com','Male','','',NULL,'','',350),(410,'test','test','',NULL,'test@email.com','Male','','',NULL,'','',352),(411,'test','test','',NULL,'test@email.com','Male','','',NULL,'','',353),(412,'test','tes','',NULL,'test@email.com','Male','','',NULL,'','',354),(418,'test','test','',NULL,'','Female','','',NULL,'','',360),(419,'test','test','test',NULL,'','Male','','',NULL,'','',361),(420,'test','test','',NULL,'','Male','','',NULL,'','',362),(422,'test','test','',NULL,'','Male','','',NULL,'','',364),(423,'test','test','e',NULL,'','Male','','',NULL,'','',365),(424,'test','test','',NULL,'','Male','','',NULL,'','',366),(425,'r','r','',NULL,'','Male','RRR','',NULL,'','',367),(426,'d','d','',NULL,'','Male','','',NULL,'','',368),(427,'g','g','',NULL,'','Male','','','/Users/zhenya/avatars/7/2/4/LzqBTpLbJ4Q.jpg','','',369);
/*!40000 ALTER TABLE `mydb`.`Contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Telephone`
--

DROP TABLE IF EXISTS `mydb`.`Telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mydb`.`Telephone` (
  `idPhone` int(10) NOT NULL AUTO_INCREMENT,
  `countryCode` varchar(3) DEFAULT NULL,
  `operatorCode` varchar(45) DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL,
  `kind` varchar(45) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `idContact` int(10) DEFAULT NULL,
  PRIMARY KEY (`idPhone`),
  UNIQUE KEY `idPhone_UNIQUE` (`idPhone`),
  KEY `PhoneFk_idx` (`idContact`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Telephone`
--

LOCK TABLES `mydb`.`Telephone` WRITE;
/*!40000 ALTER TABLE `mydb`.`Telephone` DISABLE KEYS */;
INSERT INTO `mydb`.`Telephone` VALUES (1,'375','29','237 29 38','Mobile','null0',116),(2,'375','29','237 29 38','Home','mine',119),(3,'375','29','237 29 38','Home','eee',120),(4,'234','','','Home','',123),(5,'375','20','2233332','Home','',135),(6,'375','29','3123','Home','',135),(76,'22','23','432','Home','456',139),(79,'1','2','','Home','',134),(80,'2','','','Home','',134),(82,'375','29','2372938','Home','',144),(83,'375','29','2372938','Mobile','МамаМылаРаму',377),(105,'375','29','2372938','Mobile','привет',399),(109,'375','29','2372938','Home','375',409),(111,'375','29','2372938','Mobile','3333313',418);
/*!40000 ALTER TABLE `mydb`.`Telephone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-14 16:00:51
