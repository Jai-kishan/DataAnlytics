-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2023 at 10:54 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentdata`
--

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `author` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `pub_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL DEFAULT current_timestamp(),
  `image` varchar(255) DEFAULT NULL,
  `likes` int(255) DEFAULT NULL,
  `share` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `post_id`, `author`, `content`, `pub_date`, `updated_date`, `image`, `likes`, `share`) VALUES
(36, 12, 'aut', 'no', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 55, 13),
(37, 3, 'autfgfg', 'no', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 55, 134),
(38, 6, 'autfgfg', 'no', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 5, 34),
(39, 78, 'test', 'noyyyyyy', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 5, 12),
(40, 121, 'Vadita S', 'Hi.....', '2023-10-22 00:00:00', '2023-10-17 00:28:44', NULL, 34, 6777),
(41, 122, 'Vadita SS', 'Hi.....Hi', '2023-10-22 00:00:00', '2023-10-17 00:28:44', NULL, 34, 67771),
(42, 788, 'tesq', 'no', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 5, 121),
(43, 331, 'autfgfg', 'no', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 555, 311),
(44, 332, 'autfgfg', 'nottt', '2023-10-02 00:00:00', '2023-10-17 00:28:44', NULL, 555, 31),
(45, 61, 'autfgfg', 'no', '2023-10-02 00:00:00', '2023-10-17 00:00:00', 'C:\\\\Users\\\\jaiki\\\\Pictures\\\\Screenshots\\\\Screenshot 2023-10-15 114616.png', 5, 34),
(46, 1221, 'Vadita SS', 'Hi.....Hi', '2023-10-22 00:00:00', '2023-10-17 00:00:00', 'C:\\\\Users\\\\jaiki\\\\Pictures\\\\Screenshots\\\\download.jpg', 34, 67771);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_date` datetime NOT NULL DEFAULT current_timestamp(),
  `password` varchar(255) NOT NULL,
  `is_VIP` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `email_id`, `created_date`, `updated_date`, `password`, `is_VIP`) VALUES
(1, 'jai', 'dev', 'q', 'jai@gmail.com', '2023-10-14 00:00:00', '2023-10-16 00:00:00', 'q', 1),
(2, 'qwe', 'qwqe', 'sfdsf', 'ewe', '2023-10-15 00:00:00', '2023-10-15 13:33:41', 'qweasdzx', 0),
(3, 'qweasd', 'qwqew', 'wewqe', 'eewqew', '2023-10-15 00:00:00', '2023-10-15 13:33:55', 'qwe', 0),
(4, 'fn', 'ln', 'username', 'email', '2023-10-15 00:00:00', '2023-10-15 13:43:29', 'qweasdzx', 0),
(5, 'jai', 'dev', 'jai', 'asdsa', '2023-10-15 00:00:00', '2023-10-16 00:00:00', 'q', 1),
(6, 'v', 'venu', 'v', 'v', '2023-10-16 00:00:00', '2023-10-16 00:00:00', 'v', 1),
(7, 'r', 'r', 'r', 'r', '2023-10-16 00:00:00', '2023-10-16 00:00:00', 'qazwsxed', 0),
(8, 'w', 'w', 'w', 'w', '2023-10-17 00:00:00', '2023-10-17 00:24:33', 'qweasdzx', 0),
(9, 't', 't', 't', 't', '2023-10-17 00:00:00', '2023-10-17 00:00:00', 'qweasdzx', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `post_id` (`post_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email_id` (`email_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
