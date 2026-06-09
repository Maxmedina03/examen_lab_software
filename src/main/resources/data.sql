-- Insertar Clientes de prueba (Ajustá los nombres de las columnas a como los tengas en tu clase Cliente)
-- Asumo que tu entidad tiene id, nombre, apellido y dnicuit (o dni)
INSERT INTO clientes (id_cliente, nombre, apellido, dni_cuit) 
VALUES (1, 'Maxi', 'Perez', '35123456');
-- Nota: Si usás una estrategia de generación de IDs autoincremental (como GenerationType.IDENTITY),
-- H2 se encarga solo. Estos inserts fuerzan los IDs 1, 2 y 3 para que los puedas usar de referencia.