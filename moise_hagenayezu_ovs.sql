-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 12:43 PM
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
-- Database: `moise_hagenayezu_ovs`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`admin_id`, `name`, `username`, `password`) VALUES
(1, 'Admin User', 'admin', 'password'),
(2, 'lolo', 'llol', 'tftftft'),
(3, 'HAGENAYEZU', 'mosesmomo', 'mosesmomo1');

-- --------------------------------------------------------

--
-- Table structure for table `candidates`
--

CREATE TABLE `candidates` (
  `candidate_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `party_id` int(11) NOT NULL,
  `election_id` int(11) NOT NULL,
  `platform` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `candidates`
--

INSERT INTO `candidates` (`candidate_id`, `name`, `party_id`, `election_id`, `platform`) VALUES
(2, 'NIYOMURENGEZI Faustin', 2, 4, 'rere'),
(6, 'BIZIMANA', 2, 4, 'HEALTH CARE IMPOREMENT');

-- --------------------------------------------------------

--
-- Table structure for table `elections`
--

CREATE TABLE `elections` (
  `election_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` text NOT NULL,
  `end_date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `elections`
--

INSERT INTO `elections` (`election_id`, `name`, `start_date`, `end_date`) VALUES
(1, '2024 Presidential Election', '2024-11-05', '2024-11-05'),
(4, 'District leader', '05/05/2024', '05/03/2024'),
(5, 'guild council', '18/03/2024', '18/03/2024'),
(6, '', '', ''),
(7, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `election_admin`
--

CREATE TABLE `election_admin` (
  `election_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `election_admin`
--

INSERT INTO `election_admin` (`election_id`, `admin_id`) VALUES
(1, 1),
(5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `e_insert_elections`
--

CREATE TABLE `e_insert_elections` (
  `election_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` text DEFAULT NULL,
  `end_date` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `parties`
--

CREATE TABLE `parties` (
  `party_id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `abbreviation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parties`
--

INSERT INTO `parties` (`party_id`, `Name`, `abbreviation`) VALUES
(1, 'Democratic Party', 'D'),
(2, 'GREEN PARTY', 'GP'),
(3, 'FPR', 'FPR');

-- --------------------------------------------------------

--
-- Table structure for table `voters`
--

CREATE TABLE `voters` (
  `voter_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `DateOfBirth` text NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `PhoneNumber` varchar(255) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voters`
--

INSERT INTO `voters` (`voter_id`, `name`, `DateOfBirth`, `address`, `email`, `PhoneNumber`, `Password`) VALUES
(1, 'New Name', '1980-01-01', '123 Main Street', 'john.doe@example.com', '123-456-7890', ''),
(2, 'moise', '18/03/2000', 'ruhango', 'moses@gmail.com', '0789773232', 'mosesmomo'),
(3, 'belyse', '18/03/2003', 'kicukiro', 'belyse@gmail.com', '0787103047', 'belysebebe'),
(4, 'MUKASHEMA', '26/12/1964', 'byimana', 'aurea@gmail.com', '0785429036', 'aurea'),
(5, 'laurier', '30/11/2000', 'rubavu', 'laurier@gmail.com', '0735597312', 'dj greens'),
(6, 'deborah', '06/08/2022', 'muhanga', 'deborah@gmail.com', '0722334455', 'dede');

-- --------------------------------------------------------

--
-- Table structure for table `voting`
--

CREATE TABLE `voting` (
  `voting_id` int(10) NOT NULL,
  `voter_id` text NOT NULL,
  `candidate_id` text NOT NULL,
  `party_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voting`
--

INSERT INTO `voting` (`voting_id`, `voter_id`, `candidate_id`, `party_id`) VALUES
(3, '1', '1', '1'),
(4, '6', '6', '2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
