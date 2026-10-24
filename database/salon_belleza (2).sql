-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-10-2026 a las 05:09:52
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `salon_belleza`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `fecha_registro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `observacion`, `fecha_registro`) VALUES
(1, 'Prefiere tintes sin amoníaco', '2026-10-23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `dni` char(8) NOT NULL,
  `telefono` char(9) NOT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `nombre`, `dni`, `telefono`, `correo`) VALUES
(1, 'Maria Gomez', '72839401', '987654321', 'maria@gmail.com'),
(2, 'Lucia Fernandez', '61524387', '912345678', 'lucia@salon.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo_producto` varchar(50) NOT NULL,
  `precio_compra` decimal(10,2) NOT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `stock_actual` int(11) NOT NULL,
  `stock_minimo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `tipo_producto`, `precio_compra`, `precio_venta`, `stock_actual`, `stock_minimo`) VALUES
(1, 'Esmalte Gel Gelish', 'Insumo', 15.00, 25.00, 20, 5),
(2, 'Shampoo Reconstructivo', 'Venta', 30.00, 50.00, 10, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajadora`
--

CREATE TABLE `trabajadora` (
  `id_trabajadora` int(11) NOT NULL,
  `especialidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trabajadora`
--

INSERT INTO `trabajadora` (`id_trabajadora`, `especialidad`) VALUES
(2, 'Manicure y Pedicure');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `trabajadora`
--
ALTER TABLE `trabajadora`
  ADD PRIMARY KEY (`id_trabajadora`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_persona` FOREIGN KEY (`id_cliente`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE;

--
-- Filtros para la tabla `trabajadora`
--
ALTER TABLE `trabajadora`
  ADD CONSTRAINT `fk_trabajadora_persona` FOREIGN KEY (`id_trabajadora`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
