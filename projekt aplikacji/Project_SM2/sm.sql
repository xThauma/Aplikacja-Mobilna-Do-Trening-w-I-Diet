-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Czas generowania: 14 Sty 2018, 21:52
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
  PRIMARY KEY (`id`),
  KEY `userr` (`id_users`),
  KEY `productt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `breakfasts`
--

INSERT INTO `breakfasts` (`id`, `date`, `id_users`, `id_products`, `portion`) VALUES
(11, '2017-12-29', 10, 2, 4),
(16, '2017-12-12', 10, 1, 2),
(17, '2017-12-30', 10, 6, 1),
(19, '2017-12-30', 10, 6, 1),
(21, '2017-12-30', 10, 13, 1),
(24, '2017-12-31', 10, 2, 1),
(36, '2018-01-04', 10, 6, 50),
(37, '2018-01-04', 10, 1, 15),
(38, '2018-01-04', 10, 1, 1),
(39, '2018-01-14', 14, 1, 1);

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
  PRIMARY KEY (`id`),
  KEY `userrr` (`id_users`),
  KEY `producttt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dinners`
--

INSERT INTO `dinners` (`id`, `date`, `id_users`, `id_products`, `portion`) VALUES
(1, '2017-12-29', 10, 1, 4),
(3, '2017-12-28', 10, 1, 1),
(4, '2018-01-01', 10, 1, 1),
(7, '2018-01-02', 10, 14, 1),
(8, '2018-01-04', 10, 1, 3),
(11, '2018-01-14', 14, 4, 3),
(12, '2018-01-14', 14, 1, 4);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

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
(7, 14, 'SiÅ‚ownia i fitness klub - ATLETA BiaÅ‚ystok', '2018-01-14');

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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `posts`
--

INSERT INTO `posts` (`id`, `title`, `date`, `content`, `id_users`) VALUES
(2, 'Start', '2017-12-27', 'Nice start', 10),
(17, 'e', '2017-12-28', 'e', 10),
(18, 'r', '2017-12-28', 'r', 10),
(19, 'c', '2017-12-28', 'c', 10),
(20, 'zxc', '2017-12-28', 'zxc', 5),
(21, 'zxc', '2017-12-28', 'zxcdfgfgdfgdfgfdfgfdgfdgfdgfdgfdgfdgfdgfdgfdgfdgfdgfdgfd', 5),
(22, 'q', '2017-12-29', 'q', 11),
(23, 'q', '2017-12-29', 'q', 11),
(24, 'w', '2017-12-29', 'w', 11),
(25, 'c', '2017-12-29', 'c', 11),
(26, 'r', '2017-12-29', 'r', 11),
(27, 'q', '2017-12-29', 'q', 11),
(28, 'w', '2017-12-29', 'w', 11),
(29, 'zxc', '2017-12-29', 'zxc', 11),
(30, 'xxx', '2017-12-29', 'xxx', 11),
(31, 'xxx', '2017-12-29', 'xxx', 11),
(32, 'xxxz', '2017-12-29', 'xxxc', 11),
(33, 'xxxza', '2017-12-29', 'xxxc', 11),
(34, 'xxxza', '2017-12-29', 'xxxc', 11),
(35, 'v', '2017-12-29', 'v', 11),
(36, 'vx', '2017-12-29', 'vx', 11),
(37, 'vxz', '2017-12-29', 'vx', 11),
(38, 'vxzfffs', '2017-12-29', 'vx', 11),
(39, 'sss', '2017-12-29', 'sssss', 10),
(45, 'z', '2017-12-31', 'z', 10),
(46, 'x', '2017-12-31', 'x', 10),
(47, 'v', '2017-12-31', 'v', 10),
(48, 'b', '2017-12-31', 'b', 10),
(49, 'z', '2017-12-31', 'z', 10),
(50, 'zc', '2017-12-31', 'zc', 10),
(51, 'zca', '2017-12-31', 'zca', 10),
(77, 'q', '2018-01-03', 'q', 10),
(78, 'qw', '2018-01-03', 'qw', 10),
(81, 'qw', '2018-01-04', 'qw', 10),
(82, 'qw', '2018-01-04', 'qw', 10),
(91, 'q', '2018-01-05', 'qww', 10),
(92, 'q', '2018-01-05', 'qww', 10),
(93, 'hehe', '2017-12-27', 'hehee', 10),
(94, 'asd', '2018-01-03', 'asd', 10),
(95, 'asd', '2018-01-03', 'asd', 10),
(96, 'asd', '2018-01-03', 'asd', 10),
(98, 'a', '2018-01-04', 'sd', 10),
(99, 't', '2018-01-04', 'rttr', 10),
(100, 'a', '2018-01-14', 'asd', 14),
(101, 'acz', '2018-01-15', 'asd', 14);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `products`
--

INSERT INTO `products` (`id`, `protein`, `carbohydrates`, `fiber`, `fat`, `saturated_fat`, `polyunsaturated_fat`, `monounsaturated_fat`, `sugar`, `kcal`, `name`, `barcode`) VALUES
(1, 12.50, 1.00, 1.00, 9.70, 3.00, 1.00, 1.00, 1.00, 140.00, 'eggs', 1),
(2, 21.50, 0.00, 0.00, 1.30, 0.30, 0.00, 0.00, 0.00, 99.00, 'chicken fillet', NULL),
(3, 0.00, 0.00, 0.00, 99.00, 56.00, 0.00, 0.00, 0.00, 798.00, 'oil', NULL),
(4, 11.00, 0.00, 0.00, 10.00, 0.00, 0.00, 0.00, 0.00, 150.00, 'egg', NULL),
(6, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 'qwe', NULL),
(7, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 'qw', NULL),
(8, 1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.00, 1.00, 'qw', NULL),
(9, 1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.00, 123.00, 'qw', NULL),
(10, 1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.00, 1234.00, 'qw', NULL),
(11, 195.00, 31.00, 0.00, 41.00, 0.00, 0.00, 0.00, 0.00, 1214.00, 'w', NULL),
(12, 1111.00, 184.00, 0.00, 125.00, 0.00, 0.00, 0.00, 0.00, 2000.00, 'wqw', NULL),
(13, 12.00, 12.00, 0.00, 12.00, 0.00, 0.00, 0.00, 0.00, 12.00, 'jak', NULL),
(14, 12.00, 11.00, 0.00, 11.00, 0.00, 0.00, 0.00, 0.00, 111.00, '11111', NULL),
(16, 2.00, 2.00, 0.00, 2.00, 1.00, 0.00, 0.00, 0.00, 3.00, '1', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `steps`
--

DROP TABLE IF EXISTS `steps`;
CREATE TABLE IF NOT EXISTS `steps` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `id_users` int(11) NOT NULL,
  `steps` int(6) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_steps_id_users` (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `steps`
--

INSERT INTO `steps` (`id`, `id_users`, `steps`, `date`) VALUES
(7, 10, 116, '2018-01-04');

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
  PRIMARY KEY (`id`),
  KEY `userrrr` (`id_users`),
  KEY `productttt` (`id_products`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `suppers`
--

INSERT INTO `suppers` (`id`, `date`, `id_users`, `id_products`, `portion`) VALUES
(2, '2017-12-28', 10, 1, 1),
(7, '2017-12-28', 10, 8, 1),
(11, '2018-01-14', 14, 2, 15);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `trainings`
--

INSERT INTO `trainings` (`id`, `id_users`, `reps`, `series`, `date`, `name`) VALUES
(7, 10, 1, 1, '2018-01-02', 'zxc'),
(10, 10, 4, 10, '2018-01-03', 'klatka'),
(11, 10, 42, 42, '2018-01-04', '1'),
(12, 10, 5, 1, '2018-01-03', 'plecy');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `sex`, `height`, `weight`, `target_weight`, `weight_lose`, `daily_kcal`, `kcals_left`) VALUES
(5, 'zzz', 'f3abb86bd34cf4d52698f14c0da1dc60', 'zzz@gmail.com', 'Female', 11, 22, 33, '0.75', 3000.00, 3000.00),
(6, 'Kamil', '7815696ecbf1c96e6894b779456d330e', 'kamil@gmail.com', 'Male', 184, 86, 79, '0.50', 3000.00, 0.00),
(7, '123', '76d80224611fc919a5d54f0ff9fba446', 'qwe@gmail.com', 'Male', 1, 2, 3, '0.25', 3000.00, 0.00),
(8, 'eee', 'd2f2297d6e829cd3493aa7de4416a18f', 'eee@gmail.com', 'Male', 1, 2, 3, '0.25', 3000.00, 0.00),
(9, 'zxc', '5fa72358f0b4fb4f2c5d7de8c9a41846', 'zxc@gmail.com', 'Male', 11, 22, 33, '0.25', 3000.00, 2000.00),
(10, 'qqq', 'b2ca678b4c936f905fb82f2733f5297f', 'asd@gmail.com', 'Male', 11, 22, 333, '1.00', 3000.00, 3000.00),
(11, 'Milewski', 'b2ca678b4c936f905fb82f2733f5297f', 'Milewski@gmail.com', 'Male', 178, 76, 80, '0.75', 3000.00, 0.00),
(12, 'kamil2', '21083778ff8086f7be4a2c4db54e75ce', 'kamil2@gmail.com', 'Male', 184, 87, 80, '0', 2918.40, NULL),
(13, 'wwww', 'e34a8899ef6468b74f8a1048419ccc8b', 'www@gmail.com', 'MÄ™Å¼czyna', 111, 222, 333, '0.5', 4102.90, NULL),
(14, 'zxcv', 'fd2cc6c54239c40495a0d3a93b6380eb', 'zxcv@gmail.com', 'MÄ™Å¼czyna', 178, 93, 79, '0.75', 2520.60, -114.40);

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
-- Ograniczenia dla tabeli `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `con_users` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`);

--
-- Ograniczenia dla tabeli `steps`
--
ALTER TABLE `steps`
  ADD CONSTRAINT `fk_steps_id_users` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

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
