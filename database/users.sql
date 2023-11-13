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
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
