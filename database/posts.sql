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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
