-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Czas generowania: 07 Sty 2019, 13:52
-- Wersja serwera: 5.7.19
-- Wersja PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `sm`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `breakfasts`
--

DROP TABLE IF EXISTS `breakfasts`;
CREATE TABLE IF NOT EXISTS `breakfasts` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_users` int(8) NOT NULL,
  `id_products` int(8) NOT NULL,
  `portion` double NOT NULL,
  `favourite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userr` (`id_users`),
  KEY `productt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `breakfasts`
--

INSERT INTO `breakfasts` (`id`, `date`, `id_users`, `id_products`, `portion`, `favourite`) VALUES
(65, '2018-11-06', 10, 18, 2, 0),
(66, '2018-11-06', 10, 12, 2, 0),
(67, '2018-11-06', 10, 12, 2, 0),
(68, '2018-11-06', 10, 18, 2, 0),
(69, '2018-11-20', 10, 1, 1, 0),
(70, '2018-11-20', 10, 1, 2, 0),
(72, '2018-11-21', 4, 18, 2, 0),
(73, '2018-11-26', 10, 12, 1, 0),
(74, '2018-11-26', 10, 2, 1, 0),
(75, '2018-11-26', 10, 1, 1, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dinners`
--

DROP TABLE IF EXISTS `dinners`;
CREATE TABLE IF NOT EXISTS `dinners` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_users` int(8) NOT NULL,
  `id_products` int(8) NOT NULL,
  `portion` double NOT NULL,
  `favourite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userrr` (`id_users`),
  KEY `producttt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dinners`
--

INSERT INTO `dinners` (`id`, `date`, `id_users`, `id_products`, `portion`, `favourite`) VALUES
(11, '2018-10-29', 14, 4, 3, 0),
(12, '2018-01-14', 14, 1, 4, 0),
(13, '2018-10-29', 10, 21, 2, 1),
(16, '2018-10-29', 10, 1, 1, 0),
(17, '2018-10-29', 10, 8, 1, 1),
(18, '2018-11-20', 10, 1, 3, 0),
(19, '2018-11-21', 10, 2, 3, 0),
(20, '2018-11-21', 10, 20, 3, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `gyms`
--

DROP TABLE IF EXISTS `gyms`;
CREATE TABLE IF NOT EXISTS `gyms` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `id_users` int(8) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_users_fk_gyms` (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `gyms`
--

INSERT INTO `gyms` (`id`, `id_users`, `name`, `date`) VALUES
(1, 10, 'qwe', '2018-01-01'),
(2, 10, 'Calypso Fitness Club Białystok', '2018-01-02'),
(3, 10, 'SiÅ‚ownia i fitness klub - ATLETA BiaÅ‚ystok', '2018-01-03'),
(4, 10, 'Queensland', '2018-01-04'),
(5, 10, 'Calypso Theme Waterpark', '2018-01-10'),
(6, 10, 'BraÅ„ska 15', '2018-01-05'),
(7, 14, 'SiÅ‚ownia i fitness klub - ATLETA BiaÅ‚ystok', '2018-01-14'),
(8, 10, 'fitness club Maniac Gym BiaÅ‚ystok', '2018-01-28'),
(9, 10, 'Calypso Fitness Club BiaÅ‚ystok', '2018-05-04'),
(10, 10, 'Calypso Fitness Club BiaÅ‚ystok', '2018-01-06'),
(11, 15, 'Calypso Fitness Club BiaÅ‚ystok', '2018-10-07'),
(12, 10, 'Atleta. Miejski Klub Sportowy', '2018-11-21'),
(13, 10, 'Atleta. Miejski Klub Sportowy', '2018-11-27');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lunchs`
--

DROP TABLE IF EXISTS `lunchs`;
CREATE TABLE IF NOT EXISTS `lunchs` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_users` int(8) NOT NULL,
  `id_products` int(8) NOT NULL,
  `portion` double NOT NULL,
  `favourite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userr` (`id_users`),
  KEY `productt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `lunchs`
--

INSERT INTO `lunchs` (`id`, `date`, `id_users`, `id_products`, `portion`, `favourite`) VALUES
(48, '2018-05-04', 10, 18, 3, 0),
(50, '2018-05-04', 10, 2, 3, 0),
(52, '2018-10-07', 15, 20, 2, 0),
(53, '2018-10-07', 15, 22, 66, 0),
(54, '2018-10-07', 15, 21, 1, 0),
(58, '2018-10-29', 10, 18, 1, 1),
(60, '2018-10-29', 10, 2, 1, 0),
(61, '2018-10-29', 10, 12, 1, 1),
(62, '2018-10-29', 10, 13, 1, 1),
(63, '2018-11-06', 10, 12, 2, 0),
(64, '2018-11-06', 10, 2, 3, 0),
(65, '2018-11-06', 10, 1, 5, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `meals`
--

DROP TABLE IF EXISTS `meals`;
CREATE TABLE IF NOT EXISTS `meals` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `products` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `id_users` int(4) NOT NULL,
  `name` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_userspkmeals` (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `meals`
--

INSERT INTO `meals` (`id`, `products`, `id_users`, `name`) VALUES
(8, '18,1,2', 10, 'sniadanko'),
(10, '1,2,12', 10, 'qwe');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE IF NOT EXISTS `posts` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) CHARACTER SET latin1 NOT NULL,
  `date` date NOT NULL,
  `content` varchar(250) CHARACTER SET latin1 NOT NULL,
  `id_users` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `con_users` (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `posts`
--

INSERT INTO `posts` (`id`, `title`, `date`, `content`, `id_users`) VALUES
(38, 'vxzfffs', '2017-12-29', 'vx', 4),
(39, 'sss', '2017-12-29', 'sssss', 10),
(45, 'z', '2017-12-31', 'z', 10),
(101, 'acz', '2018-01-15', 'asd', 14),
(102, 'q', '2018-05-04', 'qqqqq', 10),
(106, 'Gratulacje', '2018-05-05', 'SchudÅ‚eÅ› 0.5kg od ostatniego tygodnia. Trzymaj tak dalej!', 10),
(107, 'Dziennik treningowy', '2018-05-05', 'ZrobiÅ‚em dziÅ› plecy z barkami', 10),
(108, 'Å›niadanie', '2018-10-07', '2 szklanki wody', 15),
(109, 'Przypomnienie', '2018-11-21', 'UgotowaÄ‡ jajka na obiad', 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `protein` double(7,2) NOT NULL,
  `carbohydrates` double(7,2) NOT NULL,
  `fiber` double(7,2) NOT NULL,
  `fat` double(7,2) NOT NULL,
  `saturated_fat` double(7,2) NOT NULL,
  `polyunsaturated_fat` double(7,2) NOT NULL,
  `monounsaturated_fat` double(7,2) NOT NULL,
  `sugar` double(7,2) NOT NULL,
  `kcal` double(7,2) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `barcode` int(13) DEFAULT NULL,
  `vitaminA` double(7,2) DEFAULT NULL,
  `vitaminD` double(7,2) DEFAULT NULL,
  `vitaminE` double(7,2) DEFAULT NULL,
  `vitaminK` double(7,2) DEFAULT NULL,
  `vitaminB6` double(7,2) DEFAULT NULL,
  `calcium` double(7,2) DEFAULT NULL,
  `iron` double(7,2) DEFAULT NULL,
  `magnesium` double(7,2) DEFAULT NULL,
  `phosphorus` double(7,2) DEFAULT NULL,
  `zinc` double(7,2) DEFAULT NULL,
  `copper` double(7,2) DEFAULT NULL,
  `chromium` double(7,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `products`
--

INSERT INTO `products` (`id`, `protein`, `carbohydrates`, `fiber`, `fat`, `saturated_fat`, `polyunsaturated_fat`, `monounsaturated_fat`, `sugar`, `kcal`, `name`, `barcode`, `vitaminA`, `vitaminD`, `vitaminE`, `vitaminK`, `vitaminB6`, `calcium`, `iron`, `magnesium`, `phosphorus`, `zinc`, `copper`, `chromium`) VALUES
(1, 12.50, 1.00, 1.00, 9.70, 3.00, 1.00, 1.00, 1.00, 140.00, 'jajka', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 21.50, 0.00, 0.00, 1.30, 0.30, 0.00, 0.00, 0.00, 99.00, 'kurczak - filet', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 0.00, 0.00, 0.00, 99.00, 56.00, 0.00, 0.00, 0.00, 798.00, 'oil', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 11.00, 0.00, 0.00, 10.00, 0.00, 0.00, 0.00, 0.00, 150.00, 'egg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 'qwe', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 'qw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.00, 1.00, 'qw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.00, 123.00, 'qw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.00, 1234.00, 'qw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 195.00, 31.00, 0.00, 41.00, 0.00, 0.00, 0.00, 0.00, 1214.00, 'w', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 1111.00, 184.00, 0.00, 125.00, 0.00, 0.00, 0.00, 0.00, 2000.00, 'wqw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 12.00, 12.00, 0.00, 12.00, 0.00, 0.00, 0.00, 0.00, 12.00, 'jak', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, 12.00, 11.00, 0.00, 11.00, 0.00, 0.00, 0.00, 0.00, 111.00, '11111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(16, 2.00, 2.00, 0.00, 2.00, 1.00, 0.00, 0.00, 0.00, 3.00, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(17, 12.00, 122.50, 12.00, 25.00, 7.20, 3.15, 1.00, 34.30, 143.53, 'nowy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(18, 7.00, 41.00, 7.00, 3.40, 0.70, 0.60, 1.60, 6.00, 247.00, 'chleb razowy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, 9.30, 51.60, 2.70, 1.40, 0.00, 0.00, 0.00, 0.00, 258.00, 'chleb pszenny', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(21, 7.10, 53.00, 3.10, 1.10, 0.00, 0.00, 0.00, 0.00, 258.00, 'chleb rycerski', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 'chleb test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, 23.60, 22.00, 0.00, 63.00, 0.00, 0.00, 0.00, 0.00, 211.00, '58.5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `suppers`
--

DROP TABLE IF EXISTS `suppers`;
CREATE TABLE IF NOT EXISTS `suppers` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_users` int(8) NOT NULL,
  `id_products` int(8) NOT NULL,
  `portion` double NOT NULL,
  `favourite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userrrr` (`id_users`),
  KEY `productttt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `suppers`
--

INSERT INTO `suppers` (`id`, `date`, `id_users`, `id_products`, `portion`, `favourite`) VALUES
(2, '2017-12-28', 10, 1, 1, 0),
(7, '2017-12-28', 10, 8, 1, 0),
(11, '2018-01-14', 14, 2, 15, 0),
(14, '2018-10-29', 10, 1, 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `trainings`
--

DROP TABLE IF EXISTS `trainings`;
CREATE TABLE IF NOT EXISTS `trainings` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `id_users` int(8) NOT NULL,
  `reps` int(6) NOT NULL,
  `series` int(6) NOT NULL,
  `date` date NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_users_fk_trainings` (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `trainings`
--

INSERT INTO `trainings` (`id`, `id_users`, `reps`, `series`, `date`, `name`) VALUES
(7, 10, 1, 1, '2018-01-02', 'zxc'),
(10, 10, 4, 10, '2018-01-03', 'klatka'),
(11, 10, 42, 42, '2018-01-04', '1'),
(12, 10, 5, 1, '2018-01-03', 'plecy'),
(13, 10, 2, 1, '2018-01-06', 'fds'),
(14, 10, 6, 5, '2018-05-06', 'Martwy ciÄ…g'),
(16, 15, 2, 6, '2018-10-07', 'hhh'),
(17, 10, 7, 4, '2018-11-21', 'plecy'),
(18, 10, 12, 4, '2018-12-11', 'Martwy ciÄ…g');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) COLLATE utf8_polish_ci NOT NULL,
  `password` text COLLATE utf8_polish_ci NOT NULL,
  `email` varchar(25) COLLATE utf8_polish_ci NOT NULL,
  `sex` varchar(10) COLLATE utf8_polish_ci NOT NULL,
  `height` int(3) NOT NULL,
  `weight` int(3) NOT NULL,
  `target_weight` int(3) NOT NULL,
  `weight_lose` varchar(10) COLLATE utf8_polish_ci DEFAULT NULL,
  `daily_kcal` double(7,2) NOT NULL,
  `kcals_left` double(7,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `sex`, `height`, `weight`, `target_weight`, `weight_lose`, `daily_kcal`, `kcals_left`) VALUES
(4, 'Milewski', 'b2ca678b4c936f905fb82f2733f5297f', 'Milewski@gmail.com', 'Male', 178, 76, 80, '0.75', 3000.00, 0.00),
(5, 'zzz', 'f3abb86bd34cf4d52698f14c0da1dc60', 'zzz@gmail.com', 'Female', 11, 22, 33, '0.75', 3000.00, 3000.00),
(6, 'Kamil', '7815696ecbf1c96e6894b779456d330e', 'kamil@gmail.com', 'Male', 184, 86, 79, '0.50', 3000.00, 0.00),
(7, '123', '76d80224611fc919a5d54f0ff9fba446', 'qwe@gmail.com', 'Male', 1, 2, 3, '0.25', 3000.00, 0.00),
(8, 'eee', 'd2f2297d6e829cd3493aa7de4416a18f', 'eee@gmail.com', 'Male', 1, 2, 3, '0.25', 3000.00, 0.00),
(9, 'zxc', '5fa72358f0b4fb4f2c5d7de8c9a41846', 'zxc@gmail.com', 'Male', 11, 22, 33, '0.25', 3000.00, 2000.00),
(10, 'Kamil1234', 'b2ca678b4c936f905fb82f2733f5297f', 'kamil@gmail.com', 'Male', 184, 86, 79, '1', 2365.00, 2365.00),
(12, 'kamil2', '21083778ff8086f7be4a2c4db54e75ce', 'kamil2@gmail.com', 'Male', 184, 87, 80, '0', 2918.40, NULL),
(13, 'wwww', 'e34a8899ef6468b74f8a1048419ccc8b', 'www@gmail.com', 'MÄ™Å¼czyna', 111, 222, 333, '0.5', 4102.90, NULL),
(14, 'zxcv', 'fd2cc6c54239c40495a0d3a93b6380eb', 'zxcv@gmail.com', 'MÄ™Å¼czyna', 178, 93, 79, '0.75', 2520.60, -114.40),
(15, 'Qqq', 'b2ca678b4c936f905fb82f2733f5297f', 'ziomek4800@gmail.com', 'Male', 183, 84, 78, '0.5', 2572.30, 2572.30);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `breakfasts`
--
ALTER TABLE `breakfasts`
  ADD CONSTRAINT `productt` FOREIGN KEY (`id_products`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `userr` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `dinners`
--
ALTER TABLE `dinners`
  ADD CONSTRAINT `producttt` FOREIGN KEY (`id_products`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `userrr` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `gyms`
--
ALTER TABLE `gyms`
  ADD CONSTRAINT `id_users_fk_gyms` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `lunchs`
--
ALTER TABLE `lunchs`
  ADD CONSTRAINT `producttl` FOREIGN KEY (`id_products`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `userrl` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `meals`
--
ALTER TABLE `meals`
  ADD CONSTRAINT `id_userspkmeals` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `con_users` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `suppers`
--
ALTER TABLE `suppers`
  ADD CONSTRAINT `productttt` FOREIGN KEY (`id_products`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `userrrr` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `trainings`
--
ALTER TABLE `trainings`
  ADD CONSTRAINT `id_users_fk_trainings` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
