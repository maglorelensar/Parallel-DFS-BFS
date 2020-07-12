-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.4.12-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- dfs_bfs için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `dfs_bfs` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `dfs_bfs`;

-- tablo yapısı dökülüyor dfs_bfs.testler
CREATE TABLE IF NOT EXISTS `testler` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Kul_Ad` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `Algo_Adi` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Dugum_S` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Core_S` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `P_Zaman` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `S_Zaman` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Daha_Hizli` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Fark` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Tarih/Saat` datetime NOT NULL DEFAULT curtime(),
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- dfs_bfs.testler: ~15 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `testler` DISABLE KEYS */;
INSERT INTO `testler` (`Id`, `Kul_Ad`, `Algo_Adi`, `Dugum_S`, `Core_S`, `P_Zaman`, `S_Zaman`, `Daha_Hizli`, `Fark`, `Tarih/Saat`) VALUES
	(1, 'barkin', 'DFS', '4000', '8', '4256 ms', '3789 ms', 'seri', '500 ms', '2020-06-24 16:39:05'),
	(12, 'merve', 'BFS', '2200', '4', '559 ms', '705 ms', 'Paralel', '146 ms', '2020-06-24 18:05:42'),
	(13, 'selim', 'BFS', '1500', '6', '223 ms', '272 ms', 'Paralel', '49 ms', '2020-06-24 18:06:07'),
	(14, 'burak', 'DFS', '2700', '3', '337 ms', '402 ms', 'Paralel', '65 ms', '2020-06-24 18:06:32'),
	(18, 'ferhat', 'BFS', '5000', '8', '5297 ms', '7417 ms', 'Paralel', '2120 ms', '2020-06-24 18:37:10'),
	(20, 'necmettin', 'BFS', '4000', '8', '5607 ms', '6591 ms', 'Paralel', '984 ms', '2020-06-24 20:04:12'),
	(21, 'gizem', 'DFS', '3000', '5', '936 ms', '987 ms', 'Paralel', '51 ms', '2020-06-24 20:05:17'),
	(24, 'yilmaz', 'DFS', '100', '8', '5 ms', '2 ms', 'Seri', '3 ms', '2020-06-24 20:06:10'),
	(25, 'kerim', 'BFS', '100', '8', '15 ms', '9 ms', 'Seri', '6 ms', '2020-06-24 21:20:25'),
	(26, 'hakan', 'DFS', '1500', '8', '376 ms', '263 ms', 'Seri', '113 ms', '2020-06-24 21:21:18'),
	(28, 'selda', 'DFS', '4000', '8', '1574 ms', '2161 ms', 'Paralel', '587 ms', '2020-06-24 21:21:47'),
	(29, 'Ahmet Arif', 'BFS', '100', '8', '8 ms', '3 ms', 'Seri', '5 ms', '2020-06-24 23:01:27'),
	(30, 'hikmet', 'BFS', '2000', '8', '1083 ms', '1264 ms', 'Paralel', '181 ms', '2020-06-24 23:02:21'),
	(31, 'merve', 'DFS', '4000', '8', '1535 ms', '2187 ms', 'Paralel', '652 ms', '2020-06-24 23:03:48');
/*!40000 ALTER TABLE `testler` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
