-- phpMyAdmin SQL Dump
-- version 2.11.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 27-11-2019 a las 18:04:05
-- Versión del servidor: 5.0.45
-- Versión de PHP: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `dbproyectofinal`
--
CREATE DATABASE dbproyectofinal

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE IF NOT EXISTS `actividad` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `actividad`
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

CREATE TABLE IF NOT EXISTS `proyecto` (
  `codigo` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL,
  `costo` double NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`codigo`, `titulo`, `fechaini`, `fechafin`, `costo`) VALUES
(1, 'Título proyecto 1', '2019-11-12', '2019-12-12', 85000000),
(2, 'Título proyecto 2 - Fase 2', '2020-01-01', '2020-08-28', 150000000),
(3, 'Testeo proyecto', '2019-11-20', '2019-11-27', 37900000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsable`
--

CREATE TABLE IF NOT EXISTS `responsable` (
  `codigo` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telfijo` varchar(15) NOT NULL,
  `telmovil` varchar(15) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `responsable`
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

CREATE TABLE IF NOT EXISTS `resp_act_proy` (
  `codresp` varchar(15) NOT NULL,
  `codactiv` int(11) NOT NULL,
  `codproy` int(11) NOT NULL,
  `fecha_asig` date NOT NULL,
  PRIMARY KEY  (`codresp`,`codactiv`,`codproy`),
  KEY `codproy` (`codproy`),
  KEY `codactiv` (`codactiv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `resp_act_proy`
--

INSERT INTO `resp_act_proy` (`codresp`, `codactiv`, `codproy`, `fecha_asig`) VALUES
('1', 1, 1, '2019-11-12'),
('1', 2, 1, '2019-11-12'),
('1', 3, 2, '2019-11-12'),
('2', 1, 1, '2019-11-12'),
('3', 3, 2, '2019-11-12'),
('8', 1, 3, '2019-11-20'),
('8', 4, 2, '2019-11-26'),
('8', 5, 3, '2019-11-26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  PRIMARY KEY  (`cedula`),
  UNIQUE KEY `cedula` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre`, `apellido`, `cedula`, `correo`, `contraseña`) VALUES
('admin', 'admin', '1', 'admin', 'admin'),
('Santiago', 'Restrepo', '1036642436', 'santirpo115@gmail.com', '123456*'),
('Juan', 'Perez', '2', 'admin', '12345');

--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `resp_act_proy`
--
ALTER TABLE `resp_act_proy`
  ADD CONSTRAINT `resp_act_proy_ibfk_1` FOREIGN KEY (`codresp`) REFERENCES `responsable` (`codigo`),
  ADD CONSTRAINT `resp_act_proy_ibfk_2` FOREIGN KEY (`codproy`) REFERENCES `proyecto` (`codigo`),
  ADD CONSTRAINT `resp_act_proy_ibfk_3` FOREIGN KEY (`codactiv`) REFERENCES `actividad` (`codigo`);
