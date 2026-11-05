-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-11-2026 a las 03:12:14
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
-- Base de datos: `addcars`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cat_vehiculos`
--

CREATE TABLE `cat_vehiculos` (
  `id_categoria` varchar(5) NOT NULL,
  `nombre_categoria` varchar(20) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `capacidad_personas` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cat_vehiculos`
--

INSERT INTO `cat_vehiculos` (`id_categoria`, `nombre_categoria`, `descripcion`, `capacidad_personas`) VALUES
('CAT01', 'Citycar', 'Vehículo compacto y económico, ideal para la ciuda...', 4),
('CAT02', 'Sedán', 'Automóvil espacioso con maletero separado, perfect...', 5),
('CAT03', 'SUV', 'Vehículo utilitario deportivo, robusto y versátil....', 5),
('CAT04', 'Camioneta', 'Pick-up con batea de carga, ideal para trabajo pes...', 4),
('CAT05', 'Deportivo', 'Automóvil de altas prestaciones y diseño aerodinám...', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `id_ciudad` varchar(8) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `cod_postal` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`id_ciudad`, `nombre`, `cod_postal`) VALUES
('C01', 'Santiago', 8320000),
('C02', 'Valparaiso', 2340006),
('C03', 'Viña del mar', 2200055),
('C04', 'Rancagua', 2820011);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `id_contrato` varchar(5) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `condiciones` mediumtext NOT NULL,
  `estado_contrato` varchar(10) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `patente` varchar(6) NOT NULL,
  `id_rol` varchar(5) NOT NULL,
  `id_modelo` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contratos`
--

INSERT INTO `contratos` (`id_contrato`, `fecha_inicio`, `fecha_fin`, `condiciones`, `estado_contrato`, `dni`, `telefono`, `patente`, `id_rol`, `id_modelo`) VALUES
('CON01', '2025-09-10', '2025-09-15', 'El vehiculo se entrega con estanque lleno y debe s...', 'Activo', '11.111.111-1', '555333333', 'RTTL85', 'R02', 'MOD001'),
('CON02', '2025-09-20', '2025-09-22', 'Uso exclusivo en territorio nacional. Prohibido ll...', 'Finalizado', '12.101.101-1', '55514444444', 'SWPL15', 'R02', 'MOD002'),
('CON03', '2025-10-01', '2025-10-04', 'Arriendo de fin de semana. Incluye 500 Kms libres', 'Finalizado', '12.202.202-2', '55588888888', 'PTTR45', 'R02', 'MOD003'),
('CON04', '2025-10-05', '2025-10-15', 'Se requiere garantia en tarjeta de credito por ull...', 'Activo', '12.303.303-3', '55599999999', 'LKJH99', 'R02', 'MOD010'),
('CON05', '2025-10-11', '2025-10-18', 'Requiere licencia con más de 5 años de antigüedad...', 'Activo', '13.111.111-1', '55511111111', 'ZXCV12', 'R02', 'MOD011'),
('CON06', '2025-08-15', '2025-08-18', 'Incluye silla de bebe sin costo adicional.', 'Finalizado', '13.222.222-2', '55512222222', 'QWTY34', 'R02', 'MOD026'),
('CON07', '2025-10-12', '2025-10-19', 'El vehiculo debe ser devuelto limpio, sino se apli...', 'Activo', '13.333.333-3', '55513333333', 'MNBV67', 'R02', 'MOD004'),
('CON08', '2025-10-01', '2025-10-31', 'Arriendo mensual, plan empresa. Incluye mantencion...', 'Activo', '13.444.444-4', '55513444444', 'ASDF80', 'R02', 'MOD006'),
('CON09', '2025-09-01', '2025-09-02', 'Contrato anulado por el cliente antes de la fecha...', 'Anulado', '13.555.555-5', '55513555555', 'HJKL21', 'R02', 'MOD012'),
('CON10', '2025-10-08', '2025-10-22', 'El segundo conductor no tiene costo adicional.', 'Activo', '13.666.666-6', '55513666666', 'POIU76', 'R02', 'MOD030'),
('CON11', '2025-07-01', '2025-07-15', 'Arriendo de vacaciones de invierno.', 'Finalizado', '13.777.777-7', '55513777777', 'FGTR54', 'R02', 'MOD005'),
('CON12', '2025-10-10', '2025-10-17', 'Arriendo por una semana para evento corporativo.', 'Activo', '13.888.888-8', '55513888888', 'CVBN39', 'R02', 'MOD007'),
('CON13', '2025-09-18', '2025-09-25', 'Semana de fiestas patrias. Se prohibe el transport...', 'Finalizado', '13.999.999-9', '55513999999', 'TYUI11', 'R02', 'MOD009'),
('CON14', '2025-10-13', '2025-10-14', 'Arriendo por 24 horas.', 'Activo', '14.101.010-1', '55514101010', 'GHJK23', 'R02', 'MOD013'),
('CON15', '2025-10-02', '2025-10-12', 'Contrato de 10 dias, finaliza hoy.', 'Activo', '14.202.020-2', '55514202020', 'BNNM45', 'R02', 'MOD014'),
('CON16', '2025-05-20', '2025-05-28', 'Contrato por viaje familiar.', 'Finalizado', '14.303.030-3', '55514303030', 'ERTY89', 'R02', 'MOD016'),
('CON17', '2025-05-10', '2025-05-12', 'Arriendo de fin de semana largo.', 'Finalizado', '14.404.040-4', '55514404040', 'DFGH14', 'R02', 'MOD017'),
('CON18', '2025-09-30', '2025-10-01', 'Arriendo de 24 horas para matrimonio.', 'Finalizado', '14.505.050-5', '55514505050', 'VBNM37', 'R02', 'MOD018'),
('CON19', '2025-10-09', '2025-10-16', 'Arriendo por 7 dias.', 'Activo', '14.606.060-6', '55514606060', 'SDFG88', 'R02', 'MOD019'),
('CON20', '2025-10-11', '2025-10-18', 'Arriendo semanal.', 'Activo', '14.707.070-7', '55514707070', 'XCVB51', 'R02', 'MOD020'),
('CON21', '2025-04-15', '2025-04-22', 'Arriendo por semana santa.', 'Finalizado', '14.808.080-8', '55514808080', 'WEQR25', 'R02', 'MOD021'),
('CON22', '2025-10-01', '2025-10-12', 'Contrato cancelado, no se retiró el vehiculo.', 'Anulado', '14.808.080-9', '55533333333', 'HGFJ48', 'R02', 'MOD022'),
('CON23', '2025-08-01', '2025-08-10', 'Arriendo por 10 dias.', 'Finalizado', '22.222.222-2', '55544444444', 'LKJH71', 'R02', 'MOD023'),
('CON24', '2025-10-18', '2025-10-24', 'Arriendo por 14 dias.', 'Activo', '33.333.333-3', '55588888888', 'POIU94', 'R02', 'MOD024'),
('CON25', '2025-09-15', '2025-09-30', 'Arriendo segunda quincena de Septiembre.', 'Finalizado', '44.444.444-4', '55599999999', 'MNBV17', 'R02', 'MOD025'),
('CON26', '2025-07-20', '2025-07-27', 'Arriendo por una semana.', 'Finalizado', '55.555.555-5', '55511111111', 'ZKCV40', 'R02', 'MOD026'),
('CON27', '2025-10-12', '2025-10-26', 'Arriendo por 14 dias, inicia hoy.', 'Activo', '66.666.666-6', '55512222222', 'ASDF63', 'R02', 'MOD029'),
('CON28', '2025-09-20', '2025-10-05', 'Arriendo de vehiculo deportivo para evento.', 'Finalizado', '77.777.777-7', '55513333333', 'QWER06', 'R02', 'MOD008'),
('CON29', '2025-10-11', '2025-10-18', 'Arriendo semanal de vehiculo de lujo.', 'Activo', '88.888.888-8', '55513444444', 'TYUI09', 'R02', 'MOD015'),
('CON30', '2025-10-01', '2025-11-01', 'Arriendo mensual.', 'Activo', '99.999.999-9', '55513555555', 'FGHJ32', 'R02', 'MOD025');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id_factura` varchar(5) NOT NULL,
  `id_reserva` varchar(5) NOT NULL,
  `fecha_facturacion` date NOT NULL,
  `monto` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id_factura`, `id_reserva`, `fecha_facturacion`, `monto`) VALUES
('FAC01', 'RES01', '2025-10-12', 116000),
('FAC02', 'RES02', '2025-10-12', 160000),
('FAC03', 'RES03', '2025-10-11', 105000),
('FAC04', 'RES04', '2025-10-11', 93000),
('FAC05', 'RES05', '2025-10-10', 483000),
('FAC06', 'RES06', '2025-10-10', 468000),
('FAC07', 'RES07', '2025-10-09', 276000),
('FAC08', 'RES08', '2025-10-09', 525000),
('FAC09', 'RES09', '2025-10-08', 770000),
('FAC10', 'RES10', '2025-10-08', 900000),
('FAC11', 'RES11', '2025-10-07', 188000),
('FAC12', 'RES12', '2025-10-07', 116000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimientos`
--

CREATE TABLE `mantenimientos` (
  `id_mantenimiento` varchar(5) NOT NULL,
  `id_vehiculo` varchar(5) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `detalle` varchar(100) NOT NULL,
  `costo` int(8) NOT NULL,
  `id_rol` varchar(5) NOT NULL,
  `km_registro` double NOT NULL,
  `proxima_fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mantenimientos`
--

INSERT INTO `mantenimientos` (`id_mantenimiento`, `id_vehiculo`, `fecha`, `tipo`, `detalle`, `costo`, `id_rol`, `km_registro`, `proxima_fecha`) VALUES
('MAN01', 'VH001', '2025-08-15', 'Preventivo', 'Cambio de aceite sintético y filtro de motor.', 95000, 'R01', 28500, '2026-02-15'),
('MAN02', 'VH002', '2025-04-18', 'Diagnóstico', 'Inspección diagnóstica con scanner por luz de check engine.', 75000, 'R01', 33400, '2025-10-18'),
('MAN03', 'VH003', '2025-06-10', 'Preventivo', 'Revisión y recarga de aire acondicionado.', 60000, 'R01', 15100, '2026-06-10'),
('MAN04', 'VH004', '2025-07-22', 'Correctivo', 'Reemplazo de bujías y cables.', 110000, 'R01', 20100, '2025-07-22'),
('MAN05', 'VH005', '2025-10-01', 'Preventivo', 'Mantención de 20.000 Kms según pauta de la marca.', 280000, 'R01', 20300, '2026-10-01'),
('MAN06', 'VH006', '2025-06-05', 'Inspección', 'Inspección de frenos y suspensión.', 55000, 'R01', 35200, '2025-12-05'),
('MAN07', 'VH007', '2025-09-02', 'Correctivo', 'Reemplazo de pastillas de freno delanteras.', 145000, 'R01', 45100, '2026-03-02'),
('MAN08', 'VH008', '2025-08-20', 'Preventivo', 'Cambio de filtro de polen y limpieza de ductos de...', 70000, 'R01', 25700, '2026-02-20'),
('MAN09', 'VH009', '2025-05-15', 'Correctivo', 'Cambio de neumáticos traseros.', 320000, 'R01', 40500, '2025-11-15'),
('MAN10', 'VH010', '2025-08-10', 'Preventivo', 'Engrase de chasis y revisión de niveles.', 85000, 'R01', 18300, '2026-02-10'),
('MAN11', 'VH011', '2025-07-26', 'Preventivo', 'Alineación, balanceo y rotación de neumáticos.', 65000, 'R01', 31300, '2026-01-26'),
('MAN12', 'VH012', '2025-09-25', 'Preventivo', 'Mantención de los 30.000 Kms según pauta del fabri...', 220000, 'R01', 30450, '2026-09-25'),
('MAN13', 'VH013', '2025-08-08', 'Correctivo', 'Reparación de fuga en sistema de refrigeración.', 130000, 'R01', 29900, '2026-02-08'),
('MAN14', 'VH014', '2025-06-12', 'Inspección', 'Revisión de sistema eléctrico y luces.', 45000, 'R01', 42100, '2025-12-12'),
('MAN15', 'VH015', '2025-10-05', 'Correctivo', 'Cambio de aceite de diferencial.', 150000, 'R01', 19800, '2026-10-05'),
('MAN16', 'VH016', '2025-10-10', 'Correctivo', 'Reemplazo de amortiguadores traseros.', 280000, 'R01', 35600, '2026-04-10'),
('MAN17', 'VH017', '2025-09-14', 'Preventivo', 'Limpieza y ajuste de inyectores.', 175000, 'R01', 41200, '2026-09-14'),
('MAN18', 'VH018', '2025-10-01', 'Inspección', 'Revisión pre-viaje de 25 puntos de seguridad.', 50000, 'R01', 8500, '2026-04-01'),
('MAN19', 'VH019', '2025-08-11', 'Correctivo', 'Reemplazo de correa de accesorios.', 90000, 'R01', 36400, '2026-02-11'),
('MAN20', 'VH020', '2025-07-07', 'Preventivo', 'Mantención de los 40.000 Kms, cambio de aceite y f...', 240000, 'R01', 40800, '2026-07-07'),
('MAN21', 'VH021', '2025-06-21', 'Correctivo', 'Reemplazo de terminales de dirección.', 150000, 'R01', 32100, '2025-12-21'),
('MAN22', 'VH022', '2025-08-05', 'Preventivo', 'Cambio de aceite de caja de transmisión automática.', 185000, 'R01', 40150, '2027-08-05'),
('MAN23', 'VH023', '2025-09-28', 'Inspección', 'Inspección visual de bujías y tubo de escape.', 40000, 'R01', 25600, '2026-03-28'),
('MAN24', 'VH024', '2025-07-16', 'Correctivo', 'Cambio de disco de embrague.', 450000, 'R01', 50300, '2026-01-16'),
('MAN25', 'VH025', '2025-10-03', 'Preventivo', 'Mantención de 30.000 Kms para vehículo de alta gam...', 400000, 'R01', 30900, '2026-10-03'),
('MAN26', 'VH026', '2025-08-19', 'Correctivo', 'Reemplazo de sensor de oxígeno.', 185000, 'R01', 52300, '2026-02-19'),
('MAN27', 'VH027', '2025-06-30', 'Preventivo', 'Cambio de líquido refrigerante.', 75000, 'R01', 17800, '2027-06-30'),
('MAN28', 'VH028', '2025-08-11', 'Correctivo', 'Cambio de batería por una de mayor amperaje.', 180000, 'R01', 55300, '2028-08-11'),
('MAN29', 'VH029', '2025-08-09', 'Inspección', 'Revisión y ajuste de sistema de frenos de alta ren...', 250000, 'R01', 12300, '2026-02-09'),
('MAN30', 'VH030', '2025-09-30', 'Correctivo', 'Reparación del sistema de aire acondicionado.', 165000, 'R01', 22800, '2026-03-30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id_marca` varchar(5) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id_marca`, `nombre`) VALUES
('M01', 'Volkswagen'),
('M02', 'Nissan'),
('M03', 'Toyota'),
('M04', 'Mitsubishi'),
('M05', 'Dodge'),
('M07', 'Suzuki'),
('M09', 'Ford'),
('M11', 'Kia'),
('M12', 'Chevrolet'),
('M13', 'Toyota'),
('M14', 'Lexus'),
('M16', 'Aston Martin'),
('M19', 'Ford'),
('M21', 'Cadillac'),
('M22', 'Lamborghini'),
('M25', 'Subaru'),
('M26', 'Hyundai'),
('M29', 'Honda'),
('M31', 'Chrysler'),
('M34', 'Mazda'),
('M36', 'Mitsubishi'),
('M37', 'Dodge'),
('M39', 'BMW'),
('M40', 'GMC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

CREATE TABLE `modelos` (
  `id_modelo` varchar(8) NOT NULL,
  `nombre_modelo` varchar(20) NOT NULL,
  `id_marca` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modelos`
--

INSERT INTO `modelos` (`id_modelo`, `nombre_modelo`, `id_marca`) VALUES
('MOD001', 'Golf', 'M01'),
('MOD002', 'Versa', 'M02'),
('MOD003', 'Yaris', 'M03'),
('MOD004', 'L200', 'M04'),
('MOD005', 'Durango', 'M05'),
('MOD006', 'F-150', 'M09'),
('MOD007', 'Sportage', 'M11'),
('MOD008', 'Corvette', 'M12'),
('MOD009', 'Tucson', 'M26'),
('MOD010', 'Civic', 'M29'),
('MOD011', '3 Series', 'M39'),
('MOD012', 'Sierra', 'M40'),
('MOD013', 'RAV4', 'M13'),
('MOD014', 'RX 350', 'M14'),
('MOD015', 'DB11', 'M16'),
('MOD016', 'Explorer', 'M19'),
('MOD017', 'Escalade', 'M21'),
('MOD018', 'Urus', 'M22'),
('MOD019', 'Outback', 'M25'),
('MOD020', 'Santa Fe', 'M26'),
('MOD021', 'CR-V', 'M29'),
('MOD022', 'Pacifica', 'M31'),
('MOD023', 'CX-5', 'M34'),
('MOD024', 'Outlander', 'M36'),
('MOD025', 'Challenger', 'M37'),
('MOD026', 'X5', 'M39'),
('MOD027', 'Yukon', 'M40'),
('MOD028', 'T-Cross', 'M01'),
('MOD029', 'Qashqai', 'M02'),
('MOD030', 'Hilux', 'M03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_pago` varchar(5) NOT NULL,
  `id_rol` varchar(5) NOT NULL,
  `id_factura` varchar(5) NOT NULL,
  `id_contrato` varchar(5) NOT NULL,
  `metodo_pago` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_pago`, `id_rol`, `id_factura`, `id_contrato`, `metodo_pago`) VALUES
('PAG01', 'R02', 'FAC01', 'CON01', 'Credito'),
('PAG02', 'R02', 'FAC02', 'CON02', 'Efectivo'),
('PAG03', 'R02', 'FAC03', 'CON03', 'Credito'),
('PAG04', 'R02', 'FAC04', 'CON04', 'Credito'),
('PAG05', 'R02', 'FAC05', 'CON05', 'Efectivo'),
('PAG06', 'R02', 'FAC06', 'CON06', 'Credito'),
('PAG07', 'R02', 'FAC07', 'CON07', 'Efectivo'),
('PAG08', 'R02', 'FAC08', 'CON08', 'Credito'),
('PAG09', 'R02', 'FAC09', 'CON09', 'Efectivo'),
('PAG10', 'R02', 'FAC10', 'CON10', 'Credito'),
('PAG11', 'R02', 'FAC11', 'CON11', 'Efectivo'),
('PAG12', 'R02', 'FAC12', 'CON12', 'Credito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `dni` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `direccion` varchar(20) NOT NULL,
  `edad` int(2) NOT NULL,
  `id_rol` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`dni`, `telefono`, `email`, `direccion`, `edad`, `id_rol`) VALUES
('11.111.111-1', '5551111111', 'ana.lopez@addcars.com', 'Calle Falsa 123', 40, 'ADM'),
('12.101.101-1', '5551210101', 'javier.munoz@addcars.com', 'Gran Avenida 101', 26, 'R01'),
('12.202.202-2', '5551220202', 'valentina.diaz@addcars.com', 'Los Leones 202', 31, 'R01'),
('12.303.303-3', '5551230303', 'matias.rojas@addcars.com', 'Apoquindo 303', 24, 'R01'),
('13.111.111-1', '5551311111', 'sofia.soto@email.com', 'Calle Larga 111', 29, 'R02'),
('13.222.222-2', '5551322222', 'benjamin.contreras@email.com', 'Pasaje Angosto 222', 42, 'R02'),
('13.333.333-3', '5551333333', 'isidora.morales@email.com', 'Avenida Principal 33', 35, 'R02'),
('13.444.444-4', '5551344444', 'lucas.castro@email.com', 'Boulevard Central 44', 22, 'R02'),
('13.555.555-5', '5551355555', 'emilia.ortiz@email.com', 'Plaza de Armas 555', 51, 'R02'),
('13.666.666-6', '5551366666', 'agustin.jimenez@email.com', 'Calle del Sol 666', 28, 'R02'),
('13.777.777-7', '5551377777', 'florencia.ruiz@email.com', 'Avenida del Mar 777', 33, 'R02'),
('13.888.888-8', '5551388888', 'gaspar.herrera@email.com', 'Callejón Oscuro 888', 46, 'R02'),
('13.999.999-9', '5551399999', 'julieta.flores@email.com', 'Camino Real 999', 27, 'R02'),
('14.101.010-1', '5551410101', 'facundo.nunez@email.com', 'Ruta 5 Sur Km 10', 39, 'R02'),
('14.202.020-2', '5551420202', 'renata.tapia@email.com', 'Los Conquistadores 2', 25, 'R02'),
('14.303.030-3', '5551430303', 'maximiliano.reyes@email.com', 'El Golf 303', 48, 'R02'),
('14.404.040-4', '5551440404', 'amanda.campos@email.com', 'Costanera Norte 404', 30, 'R02'),
('14.505.050-5', '5551450505', 'cristobal.vega@email.com', 'Manquehue Sur 505', 21, 'R02'),
('14.606.060-6', '5551460606', 'antonella.rios@email.com', 'Parque Arauco 606', 36, 'R02'),
('14.707.070-7', '5551470707', 'alonso.bravo@email.com', 'Estación Central 707', 55, 'R02'),
('14.808.080-8', '5551480808', 'trinidad.molina@email.com', 'Bellavista 808', 29, 'R02'),
('14.808.080-9', '5551480809', 'javier.munoz2@addcars.com', 'Gran Avenida 102', 27, 'R01'),
('22.222.222-2', '5552222222', 'carlos.vera@addcars.com', 'Av. Siempre Viva 742', 32, 'R01'),
('33.333.333-3', '5553333333', 'juan.perez@email.com', 'Pasaje Los Aromos 32', 28, 'R02'),
('44.444.444-4', '5554444444', 'maria.soto@email.com', 'Av. del Mar 555', 35, 'R02'),
('55.555.555-5', '5555555555', 'roberto.silva@addcars.com', 'Av. Vitacura 123', 45, 'ADM'),
('66.666.666-6', '5556666666', 'luisa.martinez@addcars.com', 'Calle El Roble 456', 29, 'R01'),
('77.777.777-7', '5557777777', 'pedro.pascal@addcars.com', 'Pasaje Central 789', 38, 'R01'),
('88.888.888-8', '5558888888', 'fernanda.rojas@email.com', 'Av. La Dehesa 987', 25, 'R02'),
('99.999.999-9', '5559999999', 'diego.acosta@email.com', 'Calle Norte Sur 654', 31, 'R02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` varchar(5) NOT NULL,
  `id_rol` varchar(5) NOT NULL,
  `id_vehiculo` varchar(5) NOT NULL,
  `tipo_de_reserva` varchar(20) NOT NULL,
  `id_pago` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id_reserva`, `id_rol`, `id_vehiculo`, `tipo_de_reserva`, `id_pago`) VALUES
('RES01', 'R02', 'VH001', 'Online', 'PAG01'),
('RES02', 'R02', 'VH002', 'Telefónica', 'PAG02'),
('RES03', 'R02', 'VH003', 'En Sucursal', 'PAG03'),
('RES04', 'R02', 'VH006', 'Online', 'PAG04'),
('RES05', 'R02', 'VH009', 'Online', 'PAG05'),
('RES06', 'R02', 'VH011', 'Telefónica', 'PAG06'),
('RES07', 'R02', 'VH013', 'Online', 'PAG07'),
('RES08', 'R02', 'VH016', 'En Sucursal', 'PAG08'),
('RES09', 'R02', 'VH017', 'Online', 'PAG09'),
('RES10', 'R02', 'VH018', 'Telefónica', 'PAG10'),
('RES11', 'R02', 'VH021', 'Online', 'PAG11'),
('RES12', 'R02', 'VH022', 'En Sucursal', 'PAG12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` varchar(5) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre`) VALUES
('ADM', 'Admin'),
('R01', 'Empleado'),
('R02', 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguros`
--

CREATE TABLE `seguros` (
  `id_seguro` varchar(8) NOT NULL,
  `id_vehiculo` varchar(5) NOT NULL,
  `compania` varchar(20) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `cobertura` varchar(20) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `costo_anual` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seguros`
--

INSERT INTO `seguros` (`id_seguro`, `id_vehiculo`, `compania`, `descripcion`, `cobertura`, `fecha_inicio`, `fecha_fin`, `costo_anual`) VALUES
('SEG001', 'VH001', 'Consorcio', 'Plan Full Cobertura con deducible de 3 UF.', 'Total', '2025-01-20', '2026-01-20', 380000),
('SEG002', 'VH002', 'BCI Seguros', 'Seguro por kilómetro, plan estándar.', 'Resp. Civil', '2025-02-15', '2026-02-15', 310000),
('SEG003', 'VH003', 'Sura', 'Cobertura total más auto de reemplazo por 15 días.', 'Total', '2025-03-10', '2026-03-10', 410000),
('SEG004', 'VH004', 'Mapfre', 'Seguro para vehículos sedán, cobertura de granizo.', 'Total', '2025-04-05', '2026-04-05', 450000),
('SEG005', 'VH005', 'Liberty', 'Plan premium para vehículos de alta gama.', 'Total', '2025-05-01', '2026-05-01', 920000),
('SEG006', 'VH006', 'HDI Seguros', 'Seguro básico de Responsabilidad Civil.', 'Resp. Civil', '2025-05-22', '2026-05-22', 290000),
('SEG007', 'VH007', 'Consorcio', 'Plan para camionetas, cobertura en caminos no pavi...', 'Pérdida Total', '2025-07-18', '2026-07-18', 560000),
('SEG008', 'VH008', 'Sura', 'Plan para camionetas de trabajo pesado.', 'Total', '2025-08-30', '2026-08-30', 750000),
('SEG009', 'VH009', 'BCI Seguros', 'Seguro Full Cobertura sin deducible.', 'Total', '2025-09-11', '2026-09-11', 710000),
('SEG010', 'VH010', 'Mapfre', 'Plan de Pérdida Total por robo o accidente.', 'Pérdida Total', '2025-10-01', '2026-10-01', 590000),
('SEG011', 'VH011', 'Liberty', 'Seguro para SUV de lujo, cobertura internacional.', 'Total', '2025-01-25', '2026-01-25', 880000),
('SEG012', 'VH012', 'HDI Seguros', 'Seguro para vehículos comerciales, asistencia 24/7...', 'Pérdida Total', '2025-02-20', '2026-02-20', 750000),
('SEG013', 'VH013', 'Sura', 'Plan familiar, cubre sillas de niños y equipaje.', 'Total', '2025-03-14', '2026-03-14', 530000),
('SEG014', 'VH014', 'Consorcio', 'Cobertura de daños por actos maliciosos.', 'Total', '2025-04-20', '2026-04-20', 1150000),
('SEG015', 'VH015', 'BCI Seguros', 'Seguro de lujo, servicio de conductor de reemplazo...', 'Total', '2025-05-16', '2026-05-16', 1450000),
('SEG016', 'VH016', 'Mapfre', 'Seguro para SUV 4x4, incluye rescate en ruta.', 'Total', '2025-06-19', '2026-06-19', 820000),
('SEG017', 'VH017', 'Liberty', 'Plan de alta gama para vehículos de lujo, sin dedu...', 'Total', '2025-07-21', '2026-07-21', 2300000),
('SEG018', 'VH018', 'Sura', 'Póliza especial para súper deportivos.', 'Total', '2025-08-13', '2026-08-13', 3500000),
('SEG019', 'VH019', 'HDI Seguros', 'Cobertura para vehículos familiares, asistencia en...', 'Total', '2025-09-02', '2026-09-02', 510000),
('SEG020', 'VH020', 'Consorcio', 'Seguro de flota para vehículos de trabajo.', 'Pérdida Total', '2025-10-08', '2026-10-08', 610000),
('SEG021', 'VH021', 'BCI Seguros', 'Seguro para SUV compactos, plan urbano.', 'Total', '2025-01-30', '2026-01-30', 490000),
('SEG022', 'VH022', 'Mapfre', 'Plan familiar premium, auto de reemplazo superior.', 'Total', '2025-02-18', '2026-02-18', 660000),
('SEG023', 'VH023', 'Sura', 'Seguro para crossovers, deducible 4 UF.', 'Total', '2025-03-25', '2026-03-25', 480000),
('SEG024', 'VH024', 'Liberty', 'Seguro de pérdida total para vehículos familiares.', 'Pérdida Total', '2025-04-17', '2026-04-17', 560000),
('SEG025', 'VH025', 'HDI Seguros', 'Plan full para SUV de alta gama.', 'Total', '2025-05-29', '2026-05-29', 980000),
('SEG026', 'VH026', 'Consorcio', 'Póliza para vehículos de lujo de gran tamaño.', 'Total', '2025-06-14', '2026-06-14', 1250000),
('SEG027', 'VH027', 'BCI Seguros', 'Seguro para Crossover, plan full cobertura.', 'Total', '2025-07-11', '2026-07-11', 460000),
('SEG028', 'VH028', 'Mapfre', 'Póliza de alto valor para vehículos deportivos.', 'Total', '2025-08-09', '2026-08-09', 1800000),
('SEG029', 'VH029', 'Sura', 'Seguro exclusivo para vehículos de colección y luj...', 'Total', '2025-09-23', '2026-09-23', 4500000),
('SEG030', 'VH030', 'Liberty', 'Seguro para muscle cars, cobertura especial.', 'Total', '2025-10-07', '2026-10-07', 910000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `id_sucursal` varchar(5) NOT NULL,
  `nombre_sucursal` varchar(20) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `id_ciudad` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`id_sucursal`, `nombre_sucursal`, `direccion`, `telefono`, `id_ciudad`) VALUES
('SUC01', 'Santiago Centro', 'Av. Libertador 1234', '+56223456789', 'C01'),
('SUC02', 'Valparaiso Puerto', 'Av. Errázuriz 567', '+56321234567', 'C02'),
('SUC03', 'Viña del Mar Costa', 'Av. Marina 890', '+56327654321', 'C03'),
('SUC04', 'Rancagua Centro', 'Calle Independencia 012', '+56729876543', 'C04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` varchar(5) NOT NULL,
  `id_rol` varchar(5) NOT NULL,
  `nom_usuario` varchar(20) NOT NULL,
  `contrasenia` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `id_rol`, `nom_usuario`, `contrasenia`, `email`) VALUES
('USR01', 'ADM', 'ana.lopez', 'adminpass123', 'ana.lopez@addcars.com'),
('USR02', 'ADM', 'roberto.silva', 'adminpass789', 'roberto.silva@addcars.com'),
('USR03', 'R02', 'juan.perez', 'clientepass789', 'juan.perez@email.com'),
('USR04', 'R02', 'maria.soto', 'clientepass101', 'maria.soto@email.com'),
('USR05', 'R01', 'carlos.vera', 'empleadopass456', 'carlos.vera@addcars.com'),
('USR06', 'R01', 'luisa.martinez', 'empleadopass121', 'luisa.martinez@addcars.com'),
('USR07', 'R01', 'pedro.pascal', 'empleadopass314', 'pedro.pascal@addcars.com'),
('USR08', 'R02', 'fernanda.rojas', 'clientepass159', 'fernanda.rojas@email.com'),
('USR09', 'R02', 'diego.acosta', 'clientepass264', 'diego.acosta@email.com'),
('USR10', 'R01', 'javier.munoz', 'passJmN34!', 'javier.munoz@addcars.com'),
('USR11', 'R01', 'valentina.diaz', 'passVd09!@', 'valentina.diaz@addcars.com'),
('USR12', 'R01', 'matias.rojas', 'passMr21#', 'matias.rojas@addcars.com'),
('USR13', 'R02', 'sofia.soto', 'passSs55$', 'sofia.soto@email.com'),
('USR14', 'R02', 'benja.contreras', 'passBc78!%', 'benjamin.contreras@email.com'),
('USR15', 'R02', 'isidora.morales', 'passIm90*', 'isidora.morales@email.com'),
('USR16', 'R02', 'lucas.castro', 'passLc12&', 'lucas.castro@email.com'),
('USR17', 'R02', 'emilia.ortiz', 'passEo43^', 'emilia.ortiz@email.com'),
('USR18', 'R02', 'agustin.jimenez', 'passAj67!', 'agustin.jimenez@email.com'),
('USR19', 'R02', 'florencia.ruiz', 'passFr68!', 'florencia.ruiz@email.com'),
('USR20', 'R02', 'gaspar.herrera', 'passGh01-', 'gaspar.herrera@email.com'),
('USR21', 'R02', 'julieta.flores', 'passJf23=', 'julieta.flores@email.com'),
('USR22', 'R02', 'facundo.nunez', 'passFn45+', 'facundo.nunez@email.com'),
('USR23', 'R02', 'renata.tapia', 'passRt67_', 'renata.tapia@email.com'),
('USR24', 'R02', 'maxi.reyes', 'passMr89!', 'maximiliano.reyes@email.com'),
('USR25', 'R02', 'amanda.campos', 'passAc10!@', 'amanda.campos@email.com'),
('USR26', 'R02', 'cristobal.vega', 'passCv32#', 'cristobal.vega@email.com'),
('USR27', 'R02', 'anto.rios', 'passAr54!$', 'antonella.rios@email.com'),
('USR28', 'R02', 'alonso.bravo', 'passAb76!%', 'alonso.bravo@email.com'),
('USR29', 'R02', 'trini.molina', 'passTm98!', 'trinidad.molina@email.com'),
('USR30', 'R01', 'javier.munoz.2', 'passJm2_12', 'javier.munoz2@addcars.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `id_vehiculo` varchar(5) NOT NULL,
  `anio` int(6) NOT NULL,
  `patente` varchar(10) NOT NULL,
  `id_categoria` varchar(5) NOT NULL,
  `id_sucursal` varchar(5) NOT NULL,
  `precio_base_dia` int(8) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `id_modelo` varchar(8) NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id_vehiculo`, `anio`, `patente`, `id_categoria`, `id_sucursal`, `precio_base_dia`, `descripcion`, `id_modelo`, `imagen_url`) VALUES
('VH001', 2023, 'RTTL-85', 'CAT01', 'SUC01', 12345, 'Volkswagen Golf, color blanco, transmisión automáti...', 'MOD001', 'Imagenes/golf.jpg'),
('VH002', 2022, 'SWPL-15', 'CAT02', 'SUC02', 32000, 'Nissan Versa sedán, color gris plata, full equipo', 'MOD002', 'Imagenes/versa.jpg'),
('VH003', 2024, 'PTTR-45', 'CAT02', 'SUC01', 35000, 'Toyota Yaris nuevo, color rojo, muy económico.', 'MOD003', 'Imagenes/yaris.jpg'),
('VH004', 2023, 'LKJH-99', 'CAT02', 'SUC03', 38000, 'Honda Civic version sport, color negro.', 'MOD010', 'Imagenes/hondacivic.jpg'),
('VH005', 2024, 'ZXCV-12', 'CAT02', 'SUC04', 78000, 'BMW 3 Series sedán, interior de cuero, máxima eleg...', 'MOD011', 'Imagenes/bmw.jpg'),
('VH006', 2022, 'QWTY-34', 'CAT01', 'SUC01', 31000, 'Volkswagen T-Cross, citycar espacioso, color azul.', 'MOD028', 'Imagenes/cross.jpg'),
('VH007', 2021, 'MNBV-67', 'CAT04', 'SUC02', 55000, 'Mitsubishi L200 4x4, color negro, diésel.', 'MOD004', 'Imagenes/l-200.jpg'),
('VH008', 2023, 'ASDF-80', 'CAT04', 'SUC03', 68000, 'Ford F-150 doble cabina, bencinera, color grafito.', 'MOD006', 'Imagenes/f-150.jpg'),
('VH009', 2022, 'HJKL-21', 'CAT04', 'SUC04', 69000, 'GMC Sierra 4x4, full equipada, color blanco.', 'MOD012', 'Imagenes/gmc.jpg'),
('VH010', 2024, 'POIU-76', 'CAT04', 'SUC01', 62000, 'Toyota Hilux nueva, 4x4, la más confiable.', 'MOD030', 'Imagenes/hilux.jpg'),
('VH011', 2023, 'FGTR-54', 'CAT03', 'SUC02', 60000, 'Dodge Durango con tres filas de asientos, ideal pa...', 'MOD005', 'Imagenes/dodge.jpg'),
('VH012', 2024, 'CVBN-39', 'CAT03', 'SUC03', 48000, 'Kia Sportage nueva, automática, con sunroof.', 'MOD007', 'Imagenes/sportage.jpg'),
('VH013', 2023, 'TYUI-11', 'CAT03', 'SUC04', 45000, 'Hyundai Tucson híbrida, excelente rendimiento.', 'MOD009', 'Imagenes/tucson.jpg'),
('VH014', 2022, 'GHJK-23', 'CAT03', 'SUC01', 49000, 'Toyota RAV4, color gris, automática.', 'MOD013', 'Imagenes/rav.jpg'),
('VH015', 2024, 'BNNM-45', 'CAT03', 'SUC02', 92000, 'Lexus RX 350, SUV de lujo, máximo confort.', 'MOD014', 'Imagenes/lexus.jpg'),
('VH016', 2023, 'ERTY-89', 'CAT03', 'SUC03', 75000, 'Ford Explorer XLT, 3 filas de asientos, 4x4.', 'MOD016', 'Imagenes/fordexplore.jpg'),
('VH017', 2022, 'DFGH-14', 'CAT03', 'SUC04', 110000, 'Cadillac Escalade, lujo y espacio sin comparación.', 'MOD017', 'Imagenes/cadillac.jpg'),
('VH018', 2024, 'VBNM-37', 'CAT03', 'SUC01', 180000, 'Lamborghini Urus, el super deportivo de los SUV.', 'MOD018', 'Imagenes/urus.jpg'),
('VH019', 2023, 'SDFG-88', 'CAT03', 'SUC02', 48000, 'Subaru Outback, AWD, listo para la aventura.', 'MOD019', 'Imagenes/subaru.jpg'),
('VH020', 2022, 'XCVB-51', 'CAT03', 'SUC03', 51000, 'Hyundai Santa Fe, diésel, muy espaciosa.', 'MOD020', 'Imagenes/santafe.jpg'),
('VH021', 2023, 'WEQR-25', 'CAT03', 'SUC04', 47000, 'Honda CR-V bencinera, automática.', 'MOD021', 'Imagenes/cr-v.jpg'),
('VH022', 2024, 'HGFJ-48', 'CAT03', 'SUC01', 58000, 'Chrysler Pacifica, minivan familiar de lujo.', 'MOD022', 'Imagenes/pacifica.jpg'),
('VH023', 2023, 'LKJH-71', 'CAT03', 'SUC02', 45000, 'Mazda CX-5, diseño y calidad superior.', 'MOD023', 'Imagenes/mazda.jpg'),
('VH024', 2022, 'POIU-94', 'CAT03', 'SUC03', 46000, 'Mitsubishi Outlander, 3 filas de asientos.', 'MOD024', 'Imagenes/mitsu.jpg'),
('VH025', 2023, 'MNBV-17', 'CAT03', 'SUC04', 85000, 'BMW X5, SUV deportivo de alta gama, color azul.', 'MOD026', 'Imagenes/BMWX5.jpg'),
('VH026', 2022, 'ZKCV-40', 'CAT03', 'SUC01', 95000, 'GMC Yukon, SUV de gran tamaño y potencia.', 'MOD027', 'Imagenes/gmcyukon.jpg'),
('VH027', 2024, 'ASDF-63', 'CAT03', 'SUC02', 42000, 'Nissan Qashqai, Crossover urbano y moderno.', 'MOD029', 'Imagenes/nissan.jpg'),
('VH028', 2021, 'QWER-06', 'CAT05', 'SUC03', 98000, 'Chevrolet Corvette Stingray, color amarillo.', 'MOD008', 'Imagenes/corvette.jpg'),
('VH029', 2024, 'TYUI-09', 'CAT05', 'SUC04', 250000, 'Aston Martin DB11, lujo y poder británico.', 'MOD015', 'Imagenes/aston.jpg'),
('VH030', 2023, 'FGHJ-32', 'CAT05', 'SUC01', 82000, 'Dodge Challenger, muscle car americano, motor V8.', 'MOD025', 'Imagenes/charger.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cat_vehiculos`
--
ALTER TABLE `cat_vehiculos`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`id_ciudad`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`id_contrato`),
  ADD KEY `dni_fk` (`dni`),
  ADD KEY `id_roles_fk` (`id_rol`),
  ADD KEY `id_modelos_fk` (`id_modelo`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_reservas_fk` (`id_reserva`);

--
-- Indices de la tabla `mantenimientos`
--
ALTER TABLE `mantenimientos`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `id_vehiculo_fk` (`id_vehiculo`),
  ADD KEY `id_rol_fk` (`id_rol`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id_marca`);

--
-- Indices de la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD PRIMARY KEY (`id_modelo`),
  ADD KEY `id_marcas_fk` (`id_marca`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `id_rol_fk2` (`id_rol`),
  ADD KEY `id_factura_fk2` (`id_factura`),
  ADD KEY `id_contrato_fk` (`id_contrato`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `id_role_fk` (`id_rol`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_rol_fk4` (`id_rol`),
  ADD KEY `id_vehiculo_fk2` (`id_vehiculo`),
  ADD KEY `id_pago_fk` (`id_pago`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `seguros`
--
ALTER TABLE `seguros`
  ADD PRIMARY KEY (`id_seguro`),
  ADD KEY `id_vehiculo_fk2_` (`id_vehiculo`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`id_sucursal`),
  ADD KEY `id_ciudad_fk` (`id_ciudad`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_rol_fk5` (`id_rol`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`id_vehiculo`),
  ADD KEY `id_categoria_fk` (`id_categoria`),
  ADD KEY `id_sucursales_fk` (`id_sucursal`),
  ADD KEY `id_modelo_fk` (`id_modelo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD CONSTRAINT `dni_fk` FOREIGN KEY (`dni`) REFERENCES `personas` (`dni`),
  ADD CONSTRAINT `id_modelo_fk` FOREIGN KEY (`id_modelo`) REFERENCES `modelos` (`id_modelo`),
  ADD CONSTRAINT `id_roles_fk` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `id_reservas_fk` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`);

--
-- Filtros para la tabla `mantenimientos`
--
ALTER TABLE `mantenimientos`
  ADD CONSTRAINT `id_rol_fk` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`),
  ADD CONSTRAINT `id_vehiculo_fk` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`);

--
-- Filtros para la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD CONSTRAINT `id_marcas_fk` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id_marca`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `id_contrato_fk` FOREIGN KEY (`id_contrato`) REFERENCES `contratos` (`id_contrato`),
  ADD CONSTRAINT `id_factura_fk2` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id_factura`),
  ADD CONSTRAINT `id_rol_fk2` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `id_role_fk` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `id_pago_fk` FOREIGN KEY (`id_pago`) REFERENCES `pagos` (`id_pago`),
  ADD CONSTRAINT `id_rol_fk4` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`),
  ADD CONSTRAINT `id_vehiculo_fk2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`);

--
-- Filtros para la tabla `seguros`
--
ALTER TABLE `seguros`
  ADD CONSTRAINT `id_vehiculo_fk2_` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`);

--
-- Filtros para la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD CONSTRAINT `id_ciudad_fk` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudades` (`id_ciudad`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `id_rol_fk5` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD CONSTRAINT `id_categoria_fk` FOREIGN KEY (`id_categoria`) REFERENCES `cat_vehiculos` (`id_categoria`),
  ADD CONSTRAINT `id_modelos_fk` FOREIGN KEY (`id_modelo`) REFERENCES `modelos` (`id_modelo`),
  ADD CONSTRAINT `id_sucursales_fk` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
