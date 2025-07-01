-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jul 2025 pada 05.43
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `d'rapz_dimsum`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_menu` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `subtotal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail`, `id_transaksi`, `id_menu`, `jumlah`, `subtotal`) VALUES
(1, 1, 21111, 2, 50000),
(2, 2, 4001, 4, 120000),
(3, 3, 4001, 4, 20000),
(4, 4, 4001, 1, 10000),
(5, 5, 4001, 1, 6000),
(6, 6, 4001, 2, 10000),
(7, 10, 4001, 1, 6000),
(8, 11, 4001, 1, 10000),
(9, 11, 4002, 1, 25000),
(10, 11, 4003, 2, 10000),
(11, 11, 4004, 2, 10000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `kode_menu` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`kode_menu`, `nama_menu`, `harga`, `stok`) VALUES
(4001, 'Mineral Water', 6000, 50),
(4002, 'Teh Botol', 5000, 50),
(4003, 'Orange Juice', 8000, 50),
(4004, 'Es LemonTea', 5000, 50),
(21111, '- Dimsum kukus\r\nIsi perbox(4)', 10000, 15),
(21112, '- Dimsum Bakar\r\nIsi perbox(4)', 15000, 20),
(21113, '- Dimsum Mentai\r\nIsi perbox(4)', 25000, 15),
(21114, '- Dimsum Original\r\nIsi perbox(4)', 10000, 20),
(21115, '- Dimsum BBQ\r\nIsi perbox(4)', 25000, 15),
(21116, '- Dimsum Chessy\r\nIsi perbox(4)', 22000, 15),
(21117, '- Dimsum mix\r\nIsi perbox(4)', 30000, 10),
(21118, '- Dimsum Sauce Mashroom\r\nIsi perbox(4)', 30000, 10),
(21119, '- Dimsum chili oil\r\nIsi Perbox(5)', 20000, 20),
(30111, 'Add on perpcs Dimsum original', 3000, 100),
(30112, 'Add on Sauce atau Topping', 5000, 1000),
(211110, '- Dimsum mozzacheesy\r\nisi perbox(4)', 30000, 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `nama_user` varchar(100) NOT NULL,
  `tanggal` datetime DEFAULT current_timestamp(),
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `nama_user`, `tanggal`, `total`) VALUES
(1, 'admin', '2025-06-29 01:55:14', 50000),
(2, 'kasir123456', '2025-06-29 02:20:03', 120000),
(3, 'kasir123456', '2025-06-29 02:20:14', 20000),
(4, 'kasir123456', '2025-06-29 02:20:26', 10000),
(5, 'admin', '2025-06-29 12:55:31', 6000),
(6, 'admin', '2025-06-29 13:06:20', 10000),
(10, 'dindapermatasari', '2025-06-30 22:38:12', 6000),
(11, 'dindapermatasari', '2025-07-01 10:29:36', 55000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_login2`
--

CREATE TABLE `user_login2` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user_login2`
--

INSERT INTO `user_login2` (`id`, `username`, `password`, `role`) VALUES
(1, 'unpam', 'unpam123', 'kasir'),
(2, 'Panca Dipa', 'panca123', 'admin'),
(3, 'Rizki Andika pratama', 'iki12345', 'admin'),
(4, 'dindapermatasari', 'dinda123', 'admin'),
(5, 'ali albanteni', 'alial123', 'admin'),
(6, 'admin', 'admin123', 'admin'),
(7, 'kasir', 'admingokil123456', 'kasir'),
(8, 'kasir123', 'admingokil123456', 'kasir'),
(23, 'kasir12345', 'admingokil123456', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`kode_menu`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `user_login2`
--
ALTER TABLE `user_login2`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `kode_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211111;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `user_login2`
--
ALTER TABLE `user_login2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`kode_menu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
