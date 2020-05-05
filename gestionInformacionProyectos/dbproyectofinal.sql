-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-11-2019 a las 05:31:02
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbproyectofinal`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Consulta_Registros` ()  NO SQL
    COMMENT 'vamos a ver'
SELECT		responsable.codigo as COD_RESP, responsable.nombre as NOMBRE_RESP, 
			actividad.codigo as COD_ACTIV, actividad.descripcion as NOMBRE_ACTIV,
			proyecto.codigo as COD_PROY, proyecto.titulo as NOMBRE_PROY,
			resp_act_proy.fecha_asig as FECHA_ASIG
FROM		responsable INNER JOIN resp_act_proy
ON			responsable.codigo = resp_act_proy.codresp
INNER JOIN	actividad
ON			actividad.codigo = resp_act_proy.codactiv
INNER JOIN	proyecto
ON			proyecto.codigo = resp_act_proy.codproy
ORDER BY	NOMBRE_PROY, NOMBRE_ACTIV, NOMBRE_RESP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Consulta_x_Registro` (IN `codResp` INT, IN `codActiv` INT, IN `codProy` INT)  NO SQL
SELECT		responsable.codigo as COD_RESP, responsable.nombre as NOMBRE_RESP, 
			actividad.codigo as COD_ACTIV, actividad.descripcion as NOMBRE_ACTIV,
			proyecto.codigo as COD_PROY, proyecto.titulo as NOMBRE_PROY,
			resp_act_proy.fecha_asig as FECHA_ASIG
FROM		responsable INNER JOIN resp_act_proy
ON			responsable.codigo = resp_act_proy.codresp
INNER JOIN	actividad
ON			actividad.codigo = resp_act_proy.codactiv
INNER JOIN	proyecto
ON			proyecto.codigo = resp_act_proy.codproy
WHERE 		responsable.codigo = codResp
AND 		actividad.codigo = codActiv
AND			proyecto.codigo = codProy$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listadoProyectosConActividades` ()  NO SQL
SELECT			proyecto.codigo as COD_PROY,
				proyecto.titulo as NOMBRE_PROY,
				proyecto.fechaini as FI_PROY,
				proyecto.fechafin as FF_PROY,
				proyecto.costo as COSTO,
				actividad.codigo as COD_ACTIV,
				actividad.descripcion as NOMBRE_ACTIV,
				actividad.fechaini as FI_ACTIV,
				actividad.fechafin as FF_ACTIV
FROM			proyecto INNER JOIN resp_act_proy
ON				proyecto.codigo = resp_act_proy.codproy
INNER JOIN		actividad
ON				actividad.codigo = resp_act_proy.codactiv
ORDER BY		COD_PROY, NOMBRE_PROY, COD_ACTIV, NOMBRE_ACTIV$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listadoResponsablesConActividades` ()  NO SQL
SELECT			responsable.codigo as COD_RESP,
				responsable.nombre as NOMBRE_RESP,
				responsable.telfijo as TELFIJO,
				responsable.telmovil as TELMOVIL,
				actividad.codigo as COD_ACTIV,
				actividad.descripcion as NOMBRE_ACTIV,
				actividad.fechaini as FI_ACTIV,
				actividad.fechafin as FF_ACTIV
FROM			responsable INNER JOIN resp_act_proy
ON				responsable.codigo = resp_act_proy.codproy
INNER JOIN		actividad
ON				actividad.codigo = resp_act_proy.codactiv
ORDER BY		COD_RESP, NOMBRE_RESP, COD_ACTIV, NOMBRE_ACTIV$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`codigo`, `descripcion`, `fechaini`, `fechafin`) VALUES
(1, 'Seleccionar proveedores', '2019-11-12', '2019-11-16'),
(2, 'Seleccionar Cotizaciones', '2019-11-14', '2019-11-23'),
(3, 'Especificar requistos', '2020-02-01', '2020-03-01'),
(4, 'Diseño de Interfaz gráfica', '2020-04-01', '2020-04-30'),
(5, 'Refactorización de código', '2019-11-17', '2019-11-20'),
(6, 'Validaciones campos', '2019-11-17', '2019-11-20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `codigo` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL,
  `costo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`codigo`, `titulo`, `fechaini`, `fechafin`, `costo`) VALUES
(1, 'Título proyecto 1', '2019-11-12', '2019-12-12', 85000000),
(2, 'Título proyecto 2 - Fase 2', '2020-01-01', '2020-08-28', 150000000),
(3, 'Testeo proyecto', '2019-11-20', '2019-11-27', 37900000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsable`
--

CREATE TABLE `responsable` (
  `codigo` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telfijo` varchar(15) NOT NULL,
  `telmovil` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `responsable`
--

INSERT INTO `responsable` (`codigo`, `nombre`, `telfijo`, `telmovil`) VALUES
('1', 'Hugo', '411', '301'),
('2', 'Paco', '411', '305'),
('3', 'Luis', '4137869', '3114658902'),
('5', 'Santiago', '5555555', '3022657717'),
('8', 'andrei', '2727775', '3053501725');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resp_act_proy`
--

CREATE TABLE `resp_act_proy` (
  `codresp` varchar(15) NOT NULL,
  `codactiv` int(11) NOT NULL,
  `codproy` int(11) NOT NULL,
  `fecha_asig` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `resp_act_proy`
--

INSERT INTO `resp_act_proy` (`codresp`, `codactiv`, `codproy`, `fecha_asig`) VALUES
('1', 1, 1, '2019-11-12'),
('1', 2, 1, '2019-11-12'),
('1', 3, 2, '2019-11-12'),
('2', 1, 1, '2019-11-12'),
('3', 3, 2, '2019-11-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `contraseña` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre`, `apellido`, `cedula`, `correo`, `contraseña`) VALUES
('admin', 'admin', '1', 'admin', 'admin'),
('Santiago', 'Restrepo', '1036642436', 'santirpo115@gmail.com', '123456*'),
('Juan', 'Perez', '2', 'admin', '12345');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `responsable`
--
ALTER TABLE `responsable`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `resp_act_proy`
--
ALTER TABLE `resp_act_proy`
  ADD PRIMARY KEY (`codresp`,`codactiv`,`codproy`),
  ADD KEY `codproy` (`codproy`),
  ADD KEY `codactiv` (`codactiv`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cedula`),
  ADD UNIQUE KEY `cedula` (`cedula`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `resp_act_proy`
--
ALTER TABLE `resp_act_proy`
  ADD CONSTRAINT `resp_act_proy_ibfk_1` FOREIGN KEY (`codresp`) REFERENCES `responsable` (`codigo`),
  ADD CONSTRAINT `resp_act_proy_ibfk_2` FOREIGN KEY (`codproy`) REFERENCES `proyecto` (`codigo`),
  ADD CONSTRAINT `resp_act_proy_ibfk_3` FOREIGN KEY (`codactiv`) REFERENCES `actividad` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
