-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 01 Jun 2022 pada 13.03
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aps`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `carts`
--

CREATE TABLE `carts` (
  `idOrder` int(11) DEFAULT NULL,
  `idMenu` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `carts`
--

INSERT INTO `carts` (`idOrder`, `idMenu`, `jumlah`) VALUES
(14, 1, 1),
(14, 4, 1),
(15, 4, 2),
(16, 3, 2),
(17, 6, 2),
(17, 1, 1),
(17, 4, 1),
(17, 3, 1),
(19, 1, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `noTelp` varchar(20) CHARACTER SET utf32 COLLATE utf32_polish_ci DEFAULT NULL,
  `pwd` varchar(20) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `saldo` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `customers`
--

INSERT INTO `customers` (`id`, `nama`, `noTelp`, `pwd`, `username`, `saldo`) VALUES
(1, 'Vict', '081111111', 'huwant', 'victbetva', 160000),
(2, 'Alf', '082123894', 'iwant', 'alf1511', 100000),
(5, 'Lvict', '08527839', 'jjjjj', 'lvict777', 190000),
(6, 'Hwang In', '087187432', 'kkkkk', 'hiy08', 4000),
(7, 'Asep', '0894132412', 'asepso', 'asepso', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `meja`
--

CREATE TABLE `meja` (
  `idMeja` int(11) NOT NULL,
  `status` varchar(200) DEFAULT 'tersedia'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `meja`
--

INSERT INTO `meja` (`idMeja`, `status`) VALUES
(1, 'tersedia'),
(2, 'tersedia'),
(3, 'tersedia'),
(4, 'tersedia'),
(5, 'tersedia');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `idMenu` int(11) NOT NULL,
  `idTenant` int(11) DEFAULT NULL,
  `namaMenu` varchar(200) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`idMenu`, `idTenant`, `namaMenu`, `harga`) VALUES
(1, 1, 'tteokbokki', 10000),
(2, 1, 'jjampong', 15000),
(3, 2, 'Pecel', 8000),
(4, 2, 'Gado - gado', 10000),
(5, 2, 'Nasi Campur', 7000),
(6, 1, 'Kimchi', 9000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `ordersrev`
--

CREATE TABLE `ordersrev` (
  `id` int(11) NOT NULL,
  `idCustomer` int(11) NOT NULL,
  `meja` int(11) DEFAULT NULL,
  `totalHarga` int(11) DEFAULT NULL,
  `status` varchar(200) DEFAULT 'Dalam proses',
  `tanggal` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `ordersrev`
--

INSERT INTO `ordersrev` (`id`, `idCustomer`, `meja`, `totalHarga`, `status`, `tanggal`) VALUES
(14, 1, 1, 20000, 'Dalam proses', '2022-05-31 20:51:51'),
(15, 2, 1, 20000, 'Dalam proses', '2022-05-31 20:52:17'),
(16, 5, 3, 16000, 'Dalam proses', '2022-05-31 20:52:46'),
(17, 6, 5, 46000, 'Done', '2022-06-01 00:04:34'),
(19, 6, 3, 10000, 'Done', '2022-06-01 17:56:48');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tenantordersrev`
--

CREATE TABLE `tenantordersrev` (
  `idTenant` int(11) DEFAULT NULL,
  `idOrder` int(11) DEFAULT NULL,
  `status` varchar(200) DEFAULT 'Terima / Tolak'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tenantordersrev`
--

INSERT INTO `tenantordersrev` (`idTenant`, `idOrder`, `status`) VALUES
(1, 14, 'Terima / Tolak'),
(2, 14, 'Terima / Tolak'),
(2, 15, 'Terima / Tolak'),
(2, 16, 'Terima / Tolak'),
(1, 17, 'Terima'),
(2, 17, 'Terima'),
(1, 19, 'Terima');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tenants`
--

CREATE TABLE `tenants` (
  `id` int(11) NOT NULL,
  `namaTenant` varchar(200) DEFAULT NULL,
  `noTelp` varchar(200) DEFAULT NULL,
  `pwd` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tenants`
--

INSERT INTO `tenants` (`id`, `namaTenant`, `noTelp`, `pwd`) VALUES
(1, 'KFoods', '08687972318', 'kfood'),
(2, 'Warnak', '0812232424432', 'warnak');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `carts`
--
ALTER TABLE `carts`
  ADD KEY `idMenu` (`idMenu`),
  ADD KEY `idOrder` (`idOrder`);

--
-- Indeks untuk tabel `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `noTelp` (`noTelp`);

--
-- Indeks untuk tabel `meja`
--
ALTER TABLE `meja`
  ADD PRIMARY KEY (`idMeja`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`idMenu`),
  ADD KEY `menu_ibfk_1` (`idTenant`);

--
-- Indeks untuk tabel `ordersrev`
--
ALTER TABLE `ordersrev`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCustomer` (`idCustomer`),
  ADD KEY `meja` (`meja`);

--
-- Indeks untuk tabel `tenantordersrev`
--
ALTER TABLE `tenantordersrev`
  ADD KEY `idTenant` (`idTenant`),
  ADD KEY `idOrder` (`idOrder`);

--
-- Indeks untuk tabel `tenants`
--
ALTER TABLE `tenants`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `noTelp` (`noTelp`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `meja`
--
ALTER TABLE `meja`
  MODIFY `idMeja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `idMenu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `ordersrev`
--
ALTER TABLE `ordersrev`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `tenants`
--
ALTER TABLE `tenants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`idOrder`) REFERENCES `ordersrev` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`idTenant`) REFERENCES `tenants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `ordersrev`
--
ALTER TABLE `ordersrev`
  ADD CONSTRAINT `ordersrev_ibfk_1` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ordersrev_ibfk_2` FOREIGN KEY (`meja`) REFERENCES `meja` (`idMeja`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tenantordersrev`
--
ALTER TABLE `tenantordersrev`
  ADD CONSTRAINT `tenantordersrev_ibfk_1` FOREIGN KEY (`idTenant`) REFERENCES `tenants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tenantordersrev_ibfk_2` FOREIGN KEY (`idOrder`) REFERENCES `ordersrev` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
