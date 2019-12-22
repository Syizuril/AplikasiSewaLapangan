-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22 Des 2019 pada 06.34
-- Versi Server: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sewa_lapangan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_account`
--

CREATE TABLE `tb_account` (
  `id_account` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `create_date` date NOT NULL,
  `no_telp` varchar(255) NOT NULL,
  `alamat` text NOT NULL,
  `status_account` int(3) NOT NULL,
  `last_login` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_account`
--

INSERT INTO `tb_account` (`id_account`, `username`, `password`, `create_date`, `no_telp`, `alamat`, `status_account`, `last_login`) VALUES
('AD001', 'Admin', '7873007e525c87b6848d28d6dadd047c54e239a9fe4df2aa763d3d925958239de414e88ce2dc7f18f72655a39afa49a3d0487e136ab6d706e2cafc1b3ef1fc0b', '2019-12-18', '-', '-', 0, '2019-12-21'),
('AD002', 'yan', '85d198cbd36c70d8859a494c4080a90ae9ad1ae877bc7cd99a65fd8cc72c1a7dd7dfd8f8d7df37b92ae633f69de80a3666a00f5567db31532ade700323d7fb8f', '2019-12-20', '088888', 'krw', 0, '2019-12-20'),
('AD003', 'Syihab', '418b5a9afd761e752a75c7ecf64ea3b9f029383a870a27525acb84a33e9a527a0cef5820c33559763f9e9a396462f273581f30a304365fc4d6d1415484bdfb3a', '2019-12-21', '12345', 'Karawang', 0, '2019-12-21'),
('PG001', 'Leo Mau Engga ?', '999222e4683af2e168b0fbf491d962ffbbcd5ac03634b6166d96de2b6c57ca71efca30a672a49ac0bcf96437b1dce834c6d7cf76dbdc86300a2d413009e40711', '2019-12-21', '1234567', 'Karawang', 1, '2019-12-21');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_member`
--

CREATE TABLE `tb_member` (
  `id_member` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `no_ktp` varchar(255) NOT NULL,
  `nama_tim` varchar(255) NOT NULL,
  `no_telp` varchar(255) NOT NULL,
  `alamat` text NOT NULL,
  `create_date` date NOT NULL,
  `last_trancsaction` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_member`
--

INSERT INTO `tb_member` (`id_member`, `nama`, `no_ktp`, `nama_tim`, `no_telp`, `alamat`, `create_date`, `last_trancsaction`) VALUES
('MB001', 'Ryandi', '1234567890123456', 'PBD', '123', 'Karawang', '2019-12-21', '2019-12-21');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pesan`
--

CREATE TABLE `tb_pesan` (
  `id_pesan` varchar(255) NOT NULL,
  `id_member` varchar(255) DEFAULT NULL,
  `tgl_booking` date NOT NULL,
  `waktu_awal` int(4) NOT NULL,
  `waktu_akhir` int(4) NOT NULL,
  `lapangan` int(11) NOT NULL,
  `lama_booking` int(4) NOT NULL,
  `nama_tim` varchar(255) NOT NULL,
  `no_telp` varchar(255) NOT NULL,
  `harga_booking` int(11) NOT NULL,
  `tagihan` int(11) NOT NULL,
  `sisa` int(11) NOT NULL,
  `ket` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pesan`
--

INSERT INTO `tb_pesan` (`id_pesan`, `id_member`, `tgl_booking`, `waktu_awal`, `waktu_akhir`, `lapangan`, `lama_booking`, `nama_tim`, `no_telp`, `harga_booking`, `tagihan`, `sisa`, `ket`) VALUES
('BF001', 'MB001', '2019-12-21', 7, 10, 3, 3, 'PBD', '123', 189000, 200000, 11000, 'LUNAS, Kembalian sebanyak Rp.11000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_account`
--
ALTER TABLE `tb_account`
  ADD PRIMARY KEY (`id_account`);

--
-- Indexes for table `tb_member`
--
ALTER TABLE `tb_member`
  ADD PRIMARY KEY (`id_member`);

--
-- Indexes for table `tb_pesan`
--
ALTER TABLE `tb_pesan`
  ADD PRIMARY KEY (`id_pesan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
