-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2021 at 06:54 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oneclickcomputers`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `email`, `username`, `password`) VALUES
(1, 'admin@yahoo.com', 'Admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `pid` int(11) NOT NULL,
  `pname` varchar(100) NOT NULL,
  `category` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`pid`, `pname`, `category`, `quantity`, `price`) VALUES
(1001001, 'ASRock Z590 Steel Legend 10th and 11th Gen ATX', 'Motherboard', 3, 23000),
(1001002, 'MSI MAG X570 TOMAHAWK WiFi AMD Motherboard', 'Motherboard', 1, 24900),
(1001003, 'Gigabyte Z590 UD Intel 10th and 11th Gen ATX', 'Motherboard', 3, 20200),
(1001004, 'Gigabyte Z590 AORUS PRO AX Intel 10th and 11th Gen ATX ', 'Motherboard', 2, 34000),
(1001005, 'Asus ROG Maximus XIII Hero', 'Motherboard', 5, 47500),
(1001006, 'MSI MEG B550 UNIFY AM4 ATX AMD Motherboard', 'Motherboard', 5, 28500),
(1001007, 'MSI MPG Z590 GAMING EDGE WIFI M-ATX Motherboard', 'Motherboard', 2, 37000),
(1002001, 'Intel 8th Generation Core i5-8400 Processor', 'Processor', 8, 15700),
(1002002, 'Intel 8th Generation Core i7-8700 Processor', 'Processor', 4, 28500),
(1002003, 'Intel 9th Gen Core i5-9400 Processor', 'Processor', 7, 16800),
(1002004, 'Intel 9th Generation Core i5-9600K Processor', 'Processor', 8, 17300),
(1002005, 'Intel 9th Generation Core i7-9700K Processor', 'Processor', 11, 30500),
(1002006, 'Intel 10th Gen Core i3 10100 Processor', 'Processor', 10, 11450),
(1002007, 'AMD Ryzen 3 3200G Processor with Radeon RX Vega 8 Graphics', 'Processor', 12, 15450),
(1002008, 'AMD Ryzen 5 3400G Processor with Radeon RX Vega 11 Graphics', 'Processor', 4, 22000),
(1002009, 'AMD RYZEN 5 3500 Processor', 'Processor', 8, 12900),
(1002010, 'AMD Ryzen 5 3600 Processor', 'Processor', 7, 16950),
(1002011, 'AMD Ryzen 5 3600X Processor', 'Processor', 18, 20500),
(1002012, 'AMD Ryzen 5 3600XT Processor', 'Processor', 19, 20800),
(1003001, 'Colorful GeForce GTX 1660 Super NB 6GB-V Graphics Card', 'Graphics Card', 2, 48925),
(1003002, 'Colorful iGame GeForce RTX 3070 Advanced OC-V 8GB Graphics Card', 'Graphics Card', 3, 95000),
(1003003, 'Colorful iGame GeForce RTX 3070 Ultra W OC-V 8GB GDDR6 Graphics Card', 'Graphics Card', 3, 99000),
(1003004, 'Gigabyte GeForce GTX 1650 D6 EAGLE OC 4GB GDDR6 Graphics Card', 'Graphics Card', 2, 37000),
(1003005, 'Gigabyte GeForce GTX 1650 D6 OC 4GB Graphics Card', 'Graphics Card', 2, 34000),
(1003006, 'Gigabyte GeForce RTX 3070 TI EAGLE 8GB GDDR6X Graphics Card', 'Graphics Card', 1, 110000),
(1003007, 'Gigabyte GT 1030 2GB OC Graphics card', 'Graphics Card', 1, 11200),
(1003008, 'MSI GeForce RTX 3090 SUPRIM X 24GB Graphics Card', 'Graphics Card', 1, 245000),
(1003009, 'SAPPHIRE NITRO+ AMD Radeon RX 6900 XT 6GB GDDR6 Graphics Card', 'Graphics Card', 1, 163900),
(1003010, 'ZOTAC GAMING GeForce GTX 1660 Ti 6GB GDDR6 Graphics Card', 'Graphics Card', 2, 55000),
(1004001, 'Corsair Dominator Platinum RGB 16GB 4000MHz DDR4', 'Ram', 3, 23900),
(1004002, 'Corsair Vengeance LPX 16GB DDR4 DRAM 3200MHz', 'Ram', 4, 9000),
(1004003, 'Corsair Vengeance RGB Pro 8GB DDR4 4000MHz', 'Ram', 5, 9900),
(1004004, 'G.SKILL Trident Z Royal RGB 8GB DDR4 4266MHz', 'Ram', 5, 9800),
(1004005, 'Gigabyte AORUS RGB 16GB (2x8GB) DDR4 4400MHz', 'Ram', 4, 21000),
(1004006, 'Adata XPG SPECTRIX D50 8GB DDR4 3200MHz RGB Gaming RAM', 'Ram', 5, 5300),
(1004007, 'Corsair Vengeance RGB Pro 8GB DDR4 3200MHz Ram', 'Ram', 6, 5000),
(1004008, 'Thermaltake TOUGHRAM 8GB 3200MHz DDR4 Desktop RAM', 'Ram', 5, 4200),
(1004009, 'Thermaltake TOUGHRAM RGB 32GB 3600MHz DDR4 Desktop RAM', 'Ram', 4, 16000),
(1004010, 'Team Delta RGB White 16GB 3600MHz DDR4 Desktop RAM', 'Ram', 2, 9500),
(1005001, 'Gigabyte 120GB Solid State Drive (SSD)', 'SSD', 4, 2450),
(1005002, 'Corsair Force MP600 1TB Gen.4 PCIe NVMe M.2 SSD', 'SSD', 3, 23900),
(1005003, 'Gigabyte M.2 PCIe SSD 128GB', 'SSD', 4, 3050),
(1005004, 'Samsung 870 EVO 500GB 2.5 Inch SATA III Internal SSD', 'SSD', 3, 7000),
(1005005, 'Samsung 970 EVO Plus 1TB NVMe M.2 SSD', 'SSD', 3, 18500),
(1005006, 'Samsung 980 250GB PCIe 3.0 M.2 NVMe SSD', 'SSD', 4, 6200),
(1005007, 'Samsung 980 500GB PCIe 3.0 M.2 NVMe SSD', 'SSD', 3, 7600),
(1005008, 'Samsung 980 Pro 250GB PCIe 4.0 M.2 NVMe SSD', 'SSD', 3, 8000),
(1005009, 'Transcend 110S 1TB NVMe M.2 2280 PCle SSD', 'SSD', 4, 13500),
(1005010, 'Transcend 110S 256GB M.2 (M-Key) PCIe SSD Drive', 'SSD', 3, 4150),
(1006001, 'Seagate Barracuda 4TB SATA 3.5 inch HDD', 'HDD', 3, 9875),
(1006002, 'Seagate Exos X18 18TB 7200rpm SATA III HDD', 'HDD', 4, 85700),
(1006003, 'Seagate Internal 1TB SATA Barracuda HDD', 'HDD', 3, 3650),
(1006004, 'Seagate Ironwolf 10TB Home, SOHO and Small Business NAS HDD', 'HDD', 4, 32500),
(1006005, 'Seagate SkyHawk 8TB 3.5 inch SATA 7200RPM Surveillance HDD', 'HDD', 5, 21500),
(1006006, 'Toshiba MG07ACA Enterprise 14TB 3.5 Inch SATA 7200RPM HDD', 'HDD', 4, 39450),
(1006007, 'TOSHIBA Tomcat Nearline 4TB 3.5 Inch 7200RPM SATA NAS HDD', 'HDD', 3, 14000),
(1006008, 'Western Digital 2TB Purple Surveillance HDD', 'HDD', 4, 5400),
(1006009, 'Western Digital 4TB Purple Surveillance HDD', 'HDD', 4, 8500),
(1006010, 'Western Digital 10TB Purple Surveillance HDD', 'HDD', 5, 27000),
(1007001, 'Cooler Master MWE 450W V2 Non-Modular 80 Plus Bronze Power Supply', 'Power Supply', 3, 3700),
(1007002, 'Cooler Master MWE Gold 750 V2 Full Modular 750W 80 Plus Gold Power Supply', 'Power Supply', 5, 10450),
(1007003, 'Corsair CV550 550Watt 80 Plus Bronze Certified Power Supply', 'Power Supply', 2, 4480),
(1007004, 'Corsair CV650 650Watt 80 Plus Bronze Certified Power Supply', 'Power Supply', 3, 5550),
(1007005, 'Corsair CX650F RGB 80 Plus Bronze 650 Watt Fully Modular Power Supply', 'Power Supply', 2, 7800),
(1007006, 'Gigabyte P650B 650W 80 Plus Bronze Certified Non-Modular Power Supply', 'Power Supply', 4, 5080),
(1007007, 'Thermaltake Litepower 450W Non Modular Power Supply', 'Power Supply', 3, 3250),
(1007008, 'Thermaltake Toughpower PF1 ARGB 1050W 80 Plus Power Supply', 'Power Supply', 3, 24500),
(1008001, 'MaxGreen A366BK ATX', 'Casing', 3, 4700),
(1008002, 'Lian Li O11D O11 Dynamic Razer Edition ATX', 'Casing', 4, 14800),
(1008003, 'Lian Li LANCOOL II Mesh RGB Gaming Case (White)', 'Casing', 3, 9200),
(1008004, 'Gigabyte C200 Glass RGB LED Casing', 'Casing', 3, 4100),
(1008005, 'Gamdias MARS E2 Micro Tower Casing White', 'Casing', 4, 5300),
(1008006, 'Gamdias ATHENA M1 Elite RGB Mid Tower Gaming Case', 'Casing', 3, 6700),
(1008007, 'Corsair Obsidian 1000D ATX', 'Casing', 3, 48000),
(1008008, 'Asus TUF Gaming GT501 White Edition Mid Tower Gaming Casing', 'Casing', 3, 13900),
(1008009, 'Antec Striker ITX open Gaming Casing', 'Casing', 4, 18200),
(1008010, 'Antec NX500 Mid-Tower Gaming Case', 'Casing', 3, 4400),
(1009001, 'ASUS BE24AQLB IPS Business Monitor', 'Monitor', 3, 22500),
(1009002, 'ASUS MB169B+ FHD IPS USB Portable Monitor', 'Monitor', 4, 23500),
(1009003, 'ASUS TUF VG328H1B 32 Inch FHD 165Hz Curved Gaming Monitor', 'Monitor', 3, 38500),
(1009004, 'GIGABYTE G27FC 165Hz Full HD Curved Gaming Monitor', 'Monitor', 3, 29000),
(1009005, 'HP P27V G4 IPS LED Monitor', 'Monitor', 4, 24500),
(1009006, 'LG 22MK600M 21.5 inch IPS Full HD LED Monitor', 'Monitor', 2, 15500),
(1009007, 'LG UltraGear 27GN800-B QHD IPS 1ms 144Hz HDR Gaming Monitor', 'Monitor', 4, 51000),
(1009008, 'Samsung 21.5 Inch S22F350F LED FULL HD Monitor', 'Monitor', 4, 12200),
(1010001, 'Corsair H100 RGB 240mm Liquid CPU Cooler', 'Cpu Cooler', 3, 10000),
(1010002, 'Corsair Hydro Series H100X High Performance Liquid CPU Cooler', 'Cpu Cooler', 2, 7500),
(1010003, 'Corsair Hydro Series H150i PRO RGB 360mm Liquid CPU Cooler', 'Cpu Cooler', 3, 13500),
(1010004, 'Corsair iCUE H150i 360mm Elite Capellix Liquid CPU Cooler', 'Cpu Cooler', 3, 17500),
(1010005, 'Deepcool GAMMAXX L240T Red All In One Liquid CPU Cooler', 'Cpu Cooler', 6, 5250),
(1010006, 'EKWB EK-AIO Elite Aurum 360 D-RGB CPU Cooler', 'Cpu Cooler', 4, 26000),
(1010007, 'NZXT Kraken X73 RGB 360mm All-in-One Liquid CPU Cooler', 'Cpu Cooler', 3, 22000),
(1011001, 'Asus TUF Gaming K1 RGB Keyboard', 'Keyboard', 3, 4000),
(1011002, 'Fantech FTK-801 USB Numeric Keypad', 'Keyboard', 10, 350),
(1011003, 'Havit KB510L Multi Function Backlit Gaming Keyboard', 'Keyboard', 10, 870),
(1011004, 'Logitech G310 Mechanical Gaming Keyboard', 'Keyboard', 3, 9550),
(1011005, 'Logitech G413 Mechanical Gaming Keyboard', 'Keyboard', 4, 7500),
(1011006, 'Rapoo V52 Backlit Gaming Keyboard', 'Keyboard', 4, 1500),
(1011007, 'Rapoo V500 SE USB Mix-Colored Backlit Mechanical Gaming Keyboard', 'Keyboard', 4, 2550),
(1011008, 'azer Huntsman Elite Opto-Mechanical Switch Gaming Keyboard', 'Keyboard', 4, 16800),
(1012001, 'A4TECH OP-620D 2X Click Optical Mouse', 'Mouse', 10, 320),
(1012002, 'Fantech Helios UX3 Space Edition RGB Gaming Mouse', 'Mouse', 1, 4500),
(1012003, 'Havit HV-MS814 RGB Backlit Programmable Gaming Mouse', 'Mouse', 4, 1150),
(1012004, 'Havit MS1008 RGB Backlit Optical Gaming Mouse', 'Mouse', 10, 590),
(1012005, 'Logitech G502 Hero Gaming Mouse', 'Mouse', 3, 6800),
(1012006, 'Logitech G903 Lightspeed HERO RGB Wireless Gaming Mouse', 'Mouse', 3, 11500),
(1012007, 'Rapoo VT300 IR Optical Gaming Mouse', 'Mouse', 5, 2420),
(1012008, 'Razer DeathAdder Essential Gaming Mouse', 'Mouse', 4, 1900),
(1012009, 'XIAOMI XMWS001TM Fashion Wireless Mouse Black', 'Mouse', 4, 1650);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `trxnid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `pname` varchar(100) NOT NULL,
  `cusname` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `bkashno` varchar(14) NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`trxnid`, `pid`, `pname`, `cusname`, `quantity`, `bkashno`, `amount`) VALUES
(10005, 1002001, 'Intel 8th Generation Core i5-8400 Processor', 'Azmain11', 5, '01852341413', 78500),
(10006, 1001004, 'Gigabyte Z590 AORUS PRO AX Intel 10th and 11th Gen ATX', 'asif11', 2, '01828483283', 68000),
(10009, 1001002, 'MSI MAG X570 TOMAHAWK WiFi AMD Motherboard', 'asif11', 3, '01324252432', 74700),
(10010, 1001002, 'MSI MAG X570 TOMAHAWK WiFi AMD Motherboard', 'azmain11', 3, '01832454345', 74700),
(10011, 1002001, 'Intel 8th Generation Core i5-8400 Processor', 'azmain11', 1, '01533543534', 15700),
(10012, 1001002, 'MSI MAG X570 TOMAHAWK WiFi AMD Motherboard', 'Asif11', 1, '01923432345', 24900),
(10013, 1002011, 'AMD Ryzen 5 3600X Processor', 'Abrar0911', 5, '01852341416', 102500),
(10014, 1002008, 'AMD Ryzen 5 3400G Processor with Radeon RX Vega 11 Graphics', 'Asif11', 5, '01823456432', 110000),
(10015, 1002006, 'Intel 10th Gen Core i3 10100 Processor', 'Azmain11', 3, '01852341413', 34350),
(10016, 1002001, 'Intel 8th Generation Core i5-8400 Processor', 'Asif11', 2, '01843525254', 31400),
(10017, 1012002, 'Fantech Helios UX3 Space Edition RGB Gaming Mouse', 'asif11', 3, '01852345654', 13500),
(10018, 1010002, 'Corsair Hydro Series H100X High Performance Liquid CPU Cooler', 'Azmain11', 3, '01834543576', 22500);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `mobile` varchar(14) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `email`, `username`, `password`, `mobile`, `address`) VALUES
(16, 'Omar ', 'Faruk', 'asifbroh@gmail.com', 'Asif11', '827ccb0eea8a706c4c34a16891f84e7b', '01832547573', 'Dhaka'),
(21, 'Azmain', 'Mahtab', 'azmainmah@gmail.com', 'Azmain11', '81dc9bdb52d04dc20036dbd8313ed055', '01852341413', 'Jamalkhan, Chittagong.'),
(22, 'Rahim', 'Hasan', 'wda@wdawd.com', 'Rahim11', '763f64124bcc7d232efafb5e1cbaa3b7', '13244235456', 'Noakhali'),
(23, 'wadwadwa', 'dawdadwa', 'awdawdwad', 'wada', '0ca2c6a00ba25c5c88dbd43cd9aa9995', '32415234321', 'dwadawda'),
(24, 'Abrar', 'Aaif', 'abrar0911@gmail.com', 'Abrar0911', '98a7eaf885b49a67d5e23aee679330d2', '01852341413', 'Halishohor, Chittagong.'),
(25, 'awdwad', 'wadwadaw', 'wadawd@dwad.com', 'wadawda', '47334bf2dd5d50cda10c99f50e46cbde', '01734567876', 'awdwadwad'),
(26, 'sefesfes', 'fesfesfes', 'awfafwfaw', 'fsefesfes', 'afwafwa', '42424242', 'wafwafwa'),
(27, 'Bablu', 'Molla', 'bablu@yahoo.com', 'Bablu88', 'e2f5b18328f3e2811337090b466e2703', '01743524324', 'Dhaka');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD UNIQUE KEY `pid` (`pid`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`trxnid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `trxnid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10019;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
