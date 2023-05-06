-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 06, 2023 at 02:54 AM
-- Server version: 5.7.39
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `krb`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `kategori` varchar(25) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `hargaBeli` int(11) DEFAULT NULL,
  `hargaJual` int(11) DEFAULT NULL,
  `idVendor` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detailtransaksi`
--

CREATE TABLE `detailtransaksi` (
  `idTransaksi` varchar(10) NOT NULL,
  `idBarang` varchar(10) NOT NULL,
  `hargaJual` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id` varchar(10) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `noHp` varchar(16) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id`, `role`, `nama`, `alamat`, `noHp`, `email`, `password`, `isDeleted`) VALUES
('k1', 'admin', 'admin krb', 'bogor', '085155333194', 'krb@email.com', 'admin', 0);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `noHp` varchar(16) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `penerimaanbarang`
--

CREATE TABLE `penerimaanbarang` (
  `id` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `hargaBeli` int(11) DEFAULT NULL,
  `idBarang` varchar(10) NOT NULL,
  `idVendor` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `idMember` varchar(10) NOT NULL,
  `idKaryawan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `noHp` varchar(16) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idVendor` (`idVendor`);

--
-- Indexes for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  ADD KEY `idTransaksi` (`idTransaksi`),
  ADD KEY `idBarang` (`idBarang`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penerimaanbarang`
--
ALTER TABLE `penerimaanbarang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idVendor` (`idVendor`),
  ADD KEY `idBarang` (`idBarang`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idMember` (`idMember`),
  ADD KEY `idKaryawan` (`idKaryawan`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`idVendor`) REFERENCES `vendor` (`id`);

--
-- Constraints for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  ADD CONSTRAINT `detailtransaksi_ibfk_1` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`id`),
  ADD CONSTRAINT `detailtransaksi_ibfk_2` FOREIGN KEY (`idBarang`) REFERENCES `barang` (`id`);

--
-- Constraints for table `penerimaanbarang`
--
ALTER TABLE `penerimaanbarang`
  ADD CONSTRAINT `penerimaanbarang_ibfk_1` FOREIGN KEY (`idVendor`) REFERENCES `vendor` (`id`),
  ADD CONSTRAINT `penerimaanbarang_ibfk_2` FOREIGN KEY (`idBarang`) REFERENCES `barang` (`id`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`idMember`) REFERENCES `member` (`id`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`idKaryawan`) REFERENCES `karyawan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
