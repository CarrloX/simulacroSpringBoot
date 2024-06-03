-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: simulacrospringboot
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activityes`
--

DROP TABLE IF EXISTS `activityes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activityes` (
  `due_date` date DEFAULT NULL,
  `activity_title` varchar(100) NOT NULL,
  `activity_id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `lesson_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `FKrk5gwf7nymfqf9vfcckhs2r5c` (`lesson_id`),
  CONSTRAINT `FKrk5gwf7nymfqf9vfcckhs2r5c` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityes`
--

LOCK TABLES `activityes` WRITE;
/*!40000 ALTER TABLE `activityes` DISABLE KEYS */;
INSERT INTO `activityes` VALUES ('2026-12-25','frases en inglesx','6182bea0-249b-4d30-999d-b6428cd433ea','escribir 10 frases en ingles','7be1fe6e-272d-4fff-a4b8-425f4e9354d3'),('2026-12-25','frases en ingles','7b22a84e-d355-4418-8f21-2620ddb5aea6','escribir 10 frases en ingles','7be1fe6e-272d-4fff-a4b8-425f4e9354d3'),('2026-12-25','frases en frances','b20cbc56-ccea-47b4-987e-c74dd29156f0','escribir 10 frases en frases','78c5b93c-3233-48f6-a1b2-7bfaba8e80ba');
/*!40000 ALTER TABLE `activityes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_name` varchar(100) NOT NULL,
  `course_id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `instructor_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `UKe6rl70tjjnfr7cpqd6koh0abk` (`course_name`),
  KEY `FKcyfum8goa6q5u13uog0563gyp` (`instructor_id`),
  CONSTRAINT `FKcyfum8goa6q5u13uog0563gyp` FOREIGN KEY (`instructor_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('ingles','32815089-1f08-4f9f-b80f-cc8f5d774df3','aprender ingles C1','b3b85685-62fd-4729-8116-2c4537c0875c'),('francesx','93c960ba-4652-4e77-a12d-ce44d40b2f89','aprender frances C1','82fa3798-9920-4da1-9262-fa29570afa46'),('frances','fd15b95c-4cae-4714-9a5b-60cace857fa2','aprender frances C1','82fa3798-9920-4da1-9262-fa29570afa46');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `enrollment_date` date NOT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `enrollment_id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enrollment_id`),
  KEY `FK7ofybdo2o0ngc4de3uvx4dxqv` (`course_id`),
  KEY `FK4x08no2mpupkr616h50w3aksx` (`user_id`),
  CONSTRAINT `FK4x08no2mpupkr616h50w3aksx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK7ofybdo2o0ngc4de3uvx4dxqv` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES ('2025-12-28','32815089-1f08-4f9f-b80f-cc8f5d774df3','5ba01636-eeb0-477e-98b2-f0f7dfe6a76e','f33ac8c9-afc1-451f-89c8-63baef48fb83'),('2025-12-12','32815089-1f08-4f9f-b80f-cc8f5d774df3','67a3a9e2-e767-4a71-b534-6761b0bcc8ff','7bf0e9e8-389e-4dad-8929-02ddac959af3'),('2025-12-13','fd15b95c-4cae-4714-9a5b-60cace857fa2','7138b70e-3f6b-409b-9160-b170abe63448','f33ac8c9-afc1-451f-89c8-63baef48fb83');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `lesson_title` varchar(100) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `lesson_id` varchar(255) NOT NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `FK17ucc7gjfjddsyi0gvstkqeat` (`course_id`),
  CONSTRAINT `FK17ucc7gjfjddsyi0gvstkqeat` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES ('sustantivos en ingles','5 capitulos del libro ingles xxii','32815089-1f08-4f9f-b80f-cc8f5d774df3','1b91a9b1-5b94-4c14-acad-75b52f240365'),('abecedario en frances','20 minutos aprendiendo el abecedario','fd15b95c-4cae-4714-9a5b-60cace857fa2','2d455250-60e3-4f4d-8b57-70a76eb78304'),('practica de frances','5 minutos face to face con la profe','32815089-1f08-4f9f-b80f-cc8f5d774df3','78c5b93c-3233-48f6-a1b2-7bfaba8e80ba'),('abecedario en ingles','20 minutos aprendiendo el abecedario','32815089-1f08-4f9f-b80f-cc8f5d774df3','7be1fe6e-272d-4fff-a4b8-425f4e9354d3'),('abecedario en inglesx','20 minutos aprendiendo el abecedario','32815089-1f08-4f9f-b80f-cc8f5d774df3','89422518-0553-4abc-8fb1-32721b33bb83');
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `send_date` date DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `message_content` varchar(255) DEFAULT NULL,
  `message_id` varchar(255) NOT NULL,
  `receiver_id` varchar(255) DEFAULT NULL,
  `sender_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK64amm5xxcsy969lnd3r80qgkn` (`course_id`),
  KEY `FKt05r0b6n0iis8u7dfna4xdh73` (`receiver_id`),
  KEY `FK4ui4nnwntodh6wjvck53dbk9m` (`sender_id`),
  CONSTRAINT `FK4ui4nnwntodh6wjvck53dbk9m` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK64amm5xxcsy969lnd3r80qgkn` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FKt05r0b6n0iis8u7dfna4xdh73` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('2042-12-12','32815089-1f08-4f9f-b80f-cc8f5d774df3','profe POPO','c4d53316-2ce6-4f88-84e4-3512df9a0423','b3b85685-62fd-4729-8116-2c4537c0875c','7bf0e9e8-389e-4dad-8929-02ddac959af3'),('2042-12-12','32815089-1f08-4f9f-b80f-cc8f5d774df3','profe hola','e3dae06e-45c3-4876-b0fa-10a86390a557','b3b85685-62fd-4729-8116-2c4537c0875c','7bf0e9e8-389e-4dad-8929-02ddac959af3'),('2042-12-12','32815089-1f08-4f9f-b80f-cc8f5d774df3','profe POPO','fc26b0bd-a995-4ab5-8226-a82ff6626a26','b3b85685-62fd-4729-8116-2c4537c0875c','7bf0e9e8-389e-4dad-8929-02ddac959af3');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submissions` (
  `grade` double NOT NULL,
  `submission_date` date DEFAULT NULL,
  `activity_id` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `submission_id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`submission_id`),
  KEY `FKpp22h150oh0xhe6d6wi8qo7lh` (`activity_id`),
  KEY `FK760bgu69957phd7hax608jdms` (`user_id`),
  CONSTRAINT `FK760bgu69957phd7hax608jdms` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKpp22h150oh0xhe6d6wi8qo7lh` FOREIGN KEY (`activity_id`) REFERENCES `activityes` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submissions`
--

LOCK TABLES `submissions` WRITE;
/*!40000 ALTER TABLE `submissions` DISABLE KEYS */;
INSERT INTO `submissions` VALUES (0,'2026-12-12','7b22a84e-d355-4418-8f21-2620ddb5aea6','hola','2498f35b-15ad-49aa-9e4e-9ced93d00a54','f33ac8c9-afc1-451f-89c8-63baef48fb83'),(5,'2026-12-12','7b22a84e-d355-4418-8f21-2620ddb5aea6','holax','5dcaaeaf-80bd-4677-86c5-30ffcff9e960','f33ac8c9-afc1-451f-89c8-63baef48fb83'),(5,'2026-12-12','7b22a84e-d355-4418-8f21-2620ddb5aea6','hola','7de7f899-b926-40a6-a654-9dc72174c224','f33ac8c9-afc1-451f-89c8-63baef48fb83'),(0,'2026-12-12','7b22a84e-d355-4418-8f21-2620ddb5aea6','hola','dea720d3-d1f2-4d30-a5cd-5b495fc32659','f33ac8c9-afc1-451f-89c8-63baef48fb83');
/*!40000 ALTER TABLE `submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `full_name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `role` enum('DIRECTOR','INSTRUCTOR','STUDENT') DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK94cc4mtujq4nljbmtf8ijqf4r` (`course_id`),
  CONSTRAINT `FK94cc4mtujq4nljbmtf8ijqf4r` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('aquileZZ','pepe@gmail.com','aquileZZ brinco','123456789','7bf0e9e8-389e-4dad-8929-02ddac959af3','STUDENT',NULL),('ximena','pepe@gmail.com','ximena echeverra','123456789','82fa3798-9920-4da1-9262-fa29570afa46','INSTRUCTOR',NULL),('jacobo','pepe@gmail.com','jacobo gutierrez','123456789','b3b85685-62fd-4729-8116-2c4537c0875c','INSTRUCTOR',NULL),('andreax','pepe@gmail.com','andrea saldarriaga','123456789','b70693e7-8e92-47b2-8f36-3072bf36f143','STUDENT',NULL),('andrea','pepe@gmail.com','andrea saldarriaga','123456789','f33ac8c9-afc1-451f-89c8-63baef48fb83','STUDENT',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'simulacrospringboot'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-03 17:37:44
