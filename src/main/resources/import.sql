INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('José Andres', 'Guzmán', 'jose@correo.com', now(), now(), '44a45311-5307-418d-b55f-f794a4e9760eJCGG.png')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('Jonathan Cristobal', 'Guzmán Guadarrama', 'jonathan@correo.com', now(), now(), '44a45311-5307-418d-b55f-f794a4e9760eJCGG.png')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('José Andres', 'Guzmán', 'jose@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('Jonathan Cristobal', 'Guzmán Guadarrama', 'jonathan@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('José Andres', 'Guzmán', 'jose@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('Jonathan Cristobal', 'Guzmán Guadarrama', 'jonathan@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('José Andres', 'Guzmán', 'jose@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('Jonathan Cristobal', 'Guzmán Guadarrama', 'jonathan@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('José Andres', 'Guzmán', 'jose@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('Jonathan Cristobal', 'Guzmán Guadarrama', 'jonathan@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('José Andres', 'Guzmán', 'jose@correo.com', now(), now(), '')
INSERT INTO clientes (nombre, apellido, correo, created_at, updated_at, foto) VALUES ('Jonathan Cristobal', 'Guzmán Guadarrama', 'jonathan@correo.com', now(), now(), '')

/* productos */
INSERT INTO productos (nombre, precio, created_at) VALUES ('teclado mecanico', '1250', now())
INSERT INTO productos (nombre, precio, created_at) VALUES ('Honda Shadow 750cc', '65250', now())
INSERT INTO productos (nombre, precio, created_at) VALUES ('Laptop Lenovo Legion Y720', '16500', now())
INSERT INTO productos (nombre, precio, created_at) VALUES ('Ebook Clean Code & Clean coder', '980', now())
INSERT INTO productos (nombre, precio, created_at) VALUES ('Cristal templado redmi6', '120', now())
INSERT INTO productos (nombre, precio, created_at) VALUES ('cafe punta del cielo', '360', now())

/* facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, created_at) VALUES ('pack de bienvenida', 'NA', 2, now())
INSERT INTO facturas_items (cantidad, factura_id, producto_id, created_at) VALUES (2,1,1,now())
INSERT INTO facturas_items (cantidad, factura_id, producto_id, created_at) VALUES (1,1,4,now())
INSERT INTO facturas_items (cantidad, factura_id, producto_id, created_at) VALUES (6,1,6,now())
INSERT INTO facturas_items (cantidad, factura_id, producto_id, created_at) VALUES (1,1,3,now())
INSERT INTO facturas (descripcion, observacion, cliente_id, created_at) VALUES ('chopper team', 'asingacion de vehiculo', 2, now())
INSERT INTO facturas_items (cantidad, factura_id, producto_id, created_at) VALUES (1,2,2,now())

/* usuarios */
INSERT INTO users (username, password, enabled) VALUES ('su', '$2a$10$SpHOTKKamngOVCiJaV.qo.LH67ufe7dc5oMUJAHN1I2tSF4QNycwq', 1);
INSERT INTO users (username, password, enabled) VALUES ('conta', '$2a$10$sR3toBfQGXawSnKQC9liEe8ah/lIuOGzWfBBmSOjd.PRcbnjPnEnq', 1);
INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$6RbFmKZ7ifWpxH3rX2CNweTHJL2alGK26gEqmuU9aYxNhV9iJVoxi', 1);

/* roles */
INSERT INTO roles (role, user_id) VALUES ('ROLE_ADMIN', 1)
INSERT INTO roles (role, user_id) VALUES ('ROLE_ADMIN',2)
INSERT INTO roles (role, user_id) VALUES ('ROLE_USER',2)
INSERT INTO roles (role, user_id) VALUES ('ROLE_USER',3)