CREATE TABLE IF NOT EXISTS egresos (
  id SERIAL  NOT NULL ,
  fecha date DEFAULT NULL,
  idproducto varchar(50) DEFAULT NULL,
  idreceta varchar(50) DEFAULT NULL,
  cantidad varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ingresos (
  id SERIAL NOT NULL,
  cantidad int DEFAULT NULL,
  fecha date DEFAULT NULL,
  observacion varchar(255) DEFAULT NULL,
  idproducto int DEFAULT NULL,
  idtienda int DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS productos (
  id SERIAL NOT NULL,
  descripcion varchar(60) DEFAULT NULL,
  cantidad int DEFAULT NULL,
  fecha date DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS recetas (
  id SERIAL NOT NULL,
  nombre varchar(50) NOT NULL,
  fecha date DEFAULT NULL,
  idProducto1 int NOT NULL,
  cantidad1 int NOT NULL,
  idProducto2 int NOT NULL,
  cantidad2 int DEFAULT NULL,
  idProducto3 int DEFAULT NULL,
  cantidad3 int DEFAULT NULL,
  idProducto4 int DEFAULT NULL,
  cantidad4 int DEFAULT NULL,
  idProducto5 int DEFAULT NULL,
  cantidad5 int DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK468r1uuqma9jn27giyh8pcrr7 FOREIGN KEY (idProducto2) REFERENCES productos (id),
  CONSTRAINT FKed9ld3ld0m30edjfg428xvdtq FOREIGN KEY (idProducto4) REFERENCES productos (id),
  CONSTRAINT FKg0eqh2y8iay967uw0etlvtqhr FOREIGN KEY (idProducto1) REFERENCES productos (id),
  CONSTRAINT FKli3278s2ug0jj3mg3rdx8sljr FOREIGN KEY (idProducto3) REFERENCES productos (id),
  CONSTRAINT FKqsv2xt4qlgs8uyjqiq5vm1d4n FOREIGN KEY (idProducto5) REFERENCES productos (id)
);

CREATE TABLE IF NOT EXISTS tiendas (
  id SERIAL NOT NULL,
  nombre varchar(60) DEFAULT NULL,
  direccion varchar(60) DEFAULT NULL,
  telefono int DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS empleados (
  id SERIAL NOT NULL,
  nombre varchar(60) NOT NULL,
  apellido varchar(60) NOT NULL,
  id_tienda int DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_id_tienda FOREIGN KEY (id_tienda) REFERENCES tiendas (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL NOT NULL,
  username varchar(50) NOT NULL DEFAULT '' UNIQUE,
  password varchar(60) NOT NULL DEFAULT '',
  enabled INT NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
  id serial NOT NULL,
  user_id int NOT NULL,
  rol varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (id),
  CONSTRAINT fk_rol_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

//TRIGGER FECHA INGRESO
CREATE OR REPLACE FUNCTION set_fecha_ingreso() RETURNS TRIGGER AS $set_fecha_ingreso$
  DECLARE
  BEGIN
	
    SET NEW.fecha = timestamp;
  END;
$set_fecha_ingreso$ LANGUAGE plpgsql;

CREATE TRIGGER set_fecha_ingreso BEFORE INSERT OR UPDATE
    ON ingresos FOR EACH ROW 
    EXECUTE PROCEDURE set_fecha_ingreso();

//TRIGGER FECHA recetas

CREATE OR REPLACE FUNCTION set_fecha_recetas() RETURNS TRIGGER AS $set_fecha_recetas$
  DECLARE
  BEGIN
	
    SET NEW.fecha = timestamp;
  END;
$set_fecha_recetas$ LANGUAGE plpgsql;

CREATE TRIGGER set_fecha_recetas BEFORE INSERT OR UPDATE
    ON recetas FOR EACH ROW 
    EXECUTE PROCEDURE set_fecha_recetas();

//TRIGGER CANTIDAD INGRESOS
CREATE OR REPLACE FUNCTION set_producto_cantidad() RETURNS TRIGGER AS $set_producto_cantidad$
  DECLARE
  BEGIN
	
    UPDATE productos SET cantidad = (SELECT SUM(cantidad) FROM ingresos 
    WHERE idProducto =NEW.idProducto GROUP BY idProducto ) WHERE  id= NEW.idProducto;
  END;
$set_producto_cantidad$ LANGUAGE plpgsql;

CREATE TRIGGER set_producto_cantidad BEFORE INSERT OR UPDATE
    ON ingresos FOR EACH ROW 
    EXECUTE PROCEDURE set_producto_cantidad();

//TRIGGER PRODUCTO EGRESO

CREATE OR REPLACE FUNCTION set_producto_egreso() RETURNS TRIGGER AS $set_producto_egreso$
  DECLARE
  BEGIN
	
    IF NEW.idreceta IS NULL THEN 

    	UPDATE productos SET cantidad = cantidad - (SELECT cantidad FROM egresos WHERE id = NEW.id) 
		 WHERE  id= NEW.idProducto; 
		 END IF;
  END;
$set_producto_egreso$ LANGUAGE plpgsql;

CREATE TRIGGER set_producto_egreso BEFORE INSERT OR UPDATE
    ON egresos FOR EACH ROW 
    EXECUTE PROCEDURE set_producto_egreso();

//TRIGGER RECETA EGRESO
CREATE OR REPLACE FUNCTION set_receta_egreso() RETURNS TRIGGER AS $set_receta_egreso$
  DECLARE
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
  END;
$set_receta_egreso$ LANGUAGE plpgsql;

CREATE TRIGGER set_receta_egreso BEFORE INSERT OR UPDATE
    ON egresos FOR EACH ROW 
    EXECUTE PROCEDURE set_receta_egreso();


//INSERT USERS Y roles
insert into users(id, username, password, enabled) VALUES(1,'user','$2a$10$i.0.s2kItduSxedICHeqnOuLPB8K8/CNlVIb.auHPg85OKUhDljeq',1);
insert into users(id, username, password, enabled) VALUES(2,'admin','$2a$10$dzWvx/BBx597CuyQxj//..jsdwSLTYlQrTEwvGPtMtMX88zOUHROq',1);


--Comando 4

insert into roles(id, user_id, rol) VALUES(1, 1, 'ROLE_USER');
insert into roles(id, user_id, rol) VALUES(2, 2, 'ROLE_USER');
insert into roles(id, user_id, rol) VALUES(3, 2, 'ROLE_ADMIN');