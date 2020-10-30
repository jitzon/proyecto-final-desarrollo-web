-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.14-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para inventario
CREATE DATABASE IF NOT EXISTS `inventario` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `inventario`;

-- Volcando estructura para tabla inventario.egresos
CREATE TABLE IF NOT EXISTS `egresos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `idproducto` varchar(50) DEFAULT NULL,
  `idreceta` varchar(50) DEFAULT NULL,
  `cantidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.empleados
CREATE TABLE IF NOT EXISTS `empleados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `id_tienda` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_tienda` (`id_tienda`),
  CONSTRAINT `fk_id_tienda` FOREIGN KEY (`id_tienda`) REFERENCES `tiendas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.ingresos
CREATE TABLE IF NOT EXISTS `ingresos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `idproducto` bigint(20) DEFAULT NULL,
  `idtienda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(60) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.recetas
CREATE TABLE IF NOT EXISTS `recetas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `fecha` date DEFAULT NULL,
  `idProducto1` int(11) NOT NULL,
  `cantidad1` int(11) NOT NULL,
  `idProducto2` int(11) NOT NULL,
  `cantidad2` int(11) DEFAULT NULL,
  `idProducto3` int(11) DEFAULT NULL,
  `cantidad3` int(11) DEFAULT NULL,
  `idProducto4` int(11) DEFAULT NULL,
  `cantidad4` int(11) DEFAULT NULL,
  `idProducto5` int(11) DEFAULT NULL,
  `cantidad5` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg0eqh2y8iay967uw0etlvtqhr` (`idProducto1`),
  KEY `FK468r1uuqma9jn27giyh8pcrr7` (`idProducto2`),
  KEY `FKli3278s2ug0jj3mg3rdx8sljr` (`idProducto3`),
  KEY `FKed9ld3ld0m30edjfg428xvdtq` (`idProducto4`),
  KEY `FKqsv2xt4qlgs8uyjqiq5vm1d4n` (`idProducto5`),
  CONSTRAINT `FK468r1uuqma9jn27giyh8pcrr7` FOREIGN KEY (`idProducto2`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_Producto1` FOREIGN KEY (`idProducto1`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_Producto2` FOREIGN KEY (`idProducto2`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_Producto3` FOREIGN KEY (`idProducto3`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_Producto4` FOREIGN KEY (`idProducto4`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_Producto5` FOREIGN KEY (`idProducto5`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKed9ld3ld0m30edjfg428xvdtq` FOREIGN KEY (`idProducto4`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKg0eqh2y8iay967uw0etlvtqhr` FOREIGN KEY (`idProducto1`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKli3278s2ug0jj3mg3rdx8sljr` FOREIGN KEY (`idProducto3`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKqsv2xt4qlgs8uyjqiq5vm1d4n` FOREIGN KEY (`idProducto5`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `rol` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_rol_user` (`user_id`),
  CONSTRAINT `fk_rol_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.tiendas
CREATE TABLE IF NOT EXISTS `tiendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) DEFAULT NULL,
  `direccion` varchar(60) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inventario.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(60) NOT NULL DEFAULT '',
  `enabled` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para disparador inventario.set_fecha_ingreso
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER set_fecha_ingreso BEFORE INSERT ON ingresos FOR EACH ROW 
BEGIN
    SET NEW.fecha = CAST(NOW() AS DATE);
	 END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador inventario.set_fecha_recetas
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER set_fecha_recetas BEFORE INSERT ON recetas FOR EACH ROW 
BEGIN
    SET NEW.fecha = CAST(NOW() AS DATE);
	 END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador inventario.set_producto_cantidad
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER set_producto_cantidad AFTER INSERT ON ingresos FOR EACH ROW 
BEGIN
    

    	UPDATE productos SET cantidad = (SELECT SUM(cantidad) FROM ingresos WHERE idProducto =NEW.idProducto GROUP BY idProducto ) WHERE  id= NEW.idProducto; 
    
	 END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador inventario.set_producto_egreso
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER set_producto_egreso AFTER INSERT ON egresos FOR EACH ROW 
BEGIN    
	 IF NEW.idreceta IS NULL THEN 

    	UPDATE productos SET cantidad = cantidad - (SELECT cantidad FROM egresos WHERE id = NEW.id) 
		 WHERE  id= NEW.idProducto; 
		 END IF;
    
	 END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador inventario.set_receta_egreso
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER set_receta_egreso AFTER INSERT ON egresos FOR EACH ROW 
BEGIN    
	 IF NEW.idproducto IS NULL THEN 

    	 UPDATE productos SET cantidad = cantidad - (SELECT cantidad1 * NEW.cantidad FROM recetas WHERE id = NEW.idreceta) 
		 WHERE  id= (SELECT idProducto1 FROM recetas WHERE id = NEW.idreceta) ; 
		 
		 UPDATE productos SET cantidad = cantidad - (SELECT cantidad2 * NEW.cantidad FROM recetas WHERE id = NEW.idreceta) 
		 WHERE  id= (SELECT idProducto2 FROM recetas WHERE id = NEW.idreceta) ; 
		 
		 UPDATE productos SET cantidad = cantidad - (SELECT cantidad3 * NEW.cantidad  FROM recetas WHERE id = NEW.idreceta) 
		 WHERE  id= (SELECT idProducto3 FROM recetas WHERE id = NEW.idreceta) ; 
		 
		 UPDATE productos SET cantidad = cantidad - (SELECT cantidad4 * NEW.cantidad  FROM recetas WHERE id = NEW.idreceta) 
		 WHERE  id= (SELECT idProducto4 FROM recetas WHERE id = NEW.idreceta) ; 
		 
		 UPDATE productos SET cantidad = cantidad - (SELECT cantidad5 * NEW.cantidad  FROM recetas WHERE id = NEW.idreceta) 
		 WHERE  id= (SELECT idProducto5 FROM recetas WHERE id = NEW.idreceta) ; 
		 END IF;
    
	 END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador inventario.UPDATE_fecha_recetas
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER UPDATE_fecha_recetas BEFORE UPDATE ON recetas FOR EACH ROW 
BEGIN
    SET NEW.fecha = CAST(NOW() AS DATE);
	 END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
