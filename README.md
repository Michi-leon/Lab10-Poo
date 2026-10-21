# LABORATORIO 10: Introducción a JDBC

## 1. Descripción del proyecto
El proyecto consiste en desarrollar un programa de consola para administrar la información de una **tienda y salón de belleza**, utilizando **Java**, **MySQL** y **JDBC**.

Se reutilizarán las clases desarrolladas en los laboratorios anteriores y se incorporará la conexión con la base de datos para realizar operaciones **CRUD** (*Create, Read, Update, Delete*):
* **Registrar** nuevos elementos.
* **Listar** todos los registros.
* **Buscar** un registro por su ID.
* **Actualizar** datos existentes.
* **Eliminar** registros.

---

## 2. Clases del proyecto

### Clases heredadas del laboratorio anterior
* `Producto`: Clase abstracta que contiene los atributos comunes (`idProducto`, `nombre`, `precioCompra`, `stockActual` y `stockMinimo`).
* `ProductoVenta`: Representa los productos destinados a la venta directa.
* `Insumo`: Representa los productos utilizados de forma interna para realizar servicios.
* `ProductoMixto`: Representa los productos que pueden tener más de un tipo de uso.
* `Venta`: Representa la transacción comercial realizada.
* `DetalleVenta`: Almacena el detalle de los productos incluidos dentro de una venta.

> **Nota:** Se reutilizan constructores, *getters*, *setters* y demás métodos implementados para evitar duplicación de código.

### Clases que se incorporan
* `Persona`: Clase abstracta que define los atributos generales de los individuos.
* `Cliente`: Hereda de `Persona` y almacena la información de los clientes.
* `Trabajadora`: Hereda de `Persona` y gestiona la información del personal del salón.

---

## 3. Base de datos
**Nombre de la base de datos:** `salon_belleza`

Para esta versión inicial se trabaja con **tres tablas principales**:

### Tabla `producto`
Almacena los datos correspondientes a productos e insumos.
* `id_producto`: Clave Primaria (PK) autoincrementable.
* `nombre`: Nombre descriptivo del producto.
* `tipo_producto`: Clasificación del producto.
* `precio_compra`: Valor monetario de adquisición.
* `precio_venta`: Precio al público (cuando corresponda).
* `stock_actual`: Existencias disponibles.
* `stock_minimo`: Umbral de inventario permitido.

### Tabla `cliente`
Almacena la información de contacto y personal de los clientes.
* `id_cliente`: Clave Primaria (PK) autoincrementable.
* `nombre`: Nombre completo.
* `dni`: Documento nacional de identidad.
* `telefono`: Número de contacto.
* `correo`: Dirección de correo electrónico.

### Tabla `trabajadora`
Almacena la información del personal que presta servicios en el establecimiento.
* `id_trabajadora`: Clave Primaria (PK) autoincrementable.
* `nombre`: Nombre completo de la trabajadora.
* `dni`: Documento nacional de identidad.
* `telefono`: Número de contacto.
* `correo`: Dirección de correo electrónico.
* `especialidad`: Área de servicio o función principal.

> **Nota arquitectónica:** La base de datos incluirá un script SQL para crear la estructura e insertar datos de prueba. La clase `Persona` es un modelo abstracto de Java y no requiere una tabla independiente en la base de datos.

---

## 4. Conexión con JDBC
Se utiliza la API **JDBC** (*Java Database Connectivity*) para la comunicación con MySQL.

### Herramientas utilizadas
* `Connection`: Interfaz para la apertura y sesión de comunicación con MySQL.
* `Statement` / `PreparedStatement`: Interfaces para enviar sentencias SQL hacia la base de datos.
* `ResultSet`: Objeto que almacena y permite iterar sobre las filas de una consulta SQL.
* `Scanner`: Componente para capturar las entradas de teclado del usuario en la consola.

---

## 5. Métodos principales

### Métodos de conexión
* `conectar()`: Establece la comunicación inicial con MySQL.
* `cerrarConexion()`: Libera los recursos y finaliza la sesión con el servidor.

### Métodos CRUD
Implementados sobre las clases de acceso a datos (**DAO**) para las tablas `producto`, `cliente` y `trabajadora`:
* `registrar()`: Ejecuta instrucciones de inserción (`INSERT INTO`).
* `listar()`: Ejecuta consultas de selección general (`SELECT * FROM`).
* `buscarPorId()`: Filtra un registro específico según su identificador único.
* `actualizar()`: Modifica registros persistidos (`UPDATE`).
* `eliminar()`: Suprime un registro de la base de datos (`DELETE`).

> **Regla de ejecución:** Se utiliza `executeUpdate()` para las operaciones de modificación/escritura (`INSERT`, `UPDATE`, `DELETE`) y `executeQuery()` para las lecturas con retorno de datos (`SELECT`).

---

## 6. Menú principal
La aplicación despliega un menú interactivo en consola organizado de la siguiente manera:

1. **Gestión de productos**
2. **Gestión de clientes**
3. **Gestión de trabajadoras**
4. **Salir del programa**

Cada módulo de gestión dispone de su propio **submenú** para seleccionar las operaciones CRUD individuales. El programa permanece en ejecución mediante un bucle hasta seleccionar la opción de salida.

---

## 7. Estructura inicial del proyecto

```text
Laboratorio10/
├── src/
│   ├── model/
│   │   ├── Persona.java
│   │   ├── Cliente.java
│   │   ├── Trabajadora.java
│   │   ├── Producto.java
│   │   ├── ProductoVenta.java
│   │   ├── Insumo.java
│   │   ├── ProductoMixto.java
│   │   ├── Venta.java
│   │   └── DetalleVenta.java
│   ├── dao/
│   │   ├── ProductoDAO.java
│   │   ├── ClienteDAO.java
│   │   └── TrabajadoraDAO.java
│   ├── database/
│   │   └── Conexion.java
│   └── Main.java
└── database/
    └── salon_belleza.sql
```

## 8. Entregables

- [ ] Descripción detallada del contexto del proyecto.
- [ ] Script .sql ejecutable para recrear la base de datos salon_belleza.
- [ ] Código fuente documentado de las clases desarrolladas.
- [ ] Programa de consola interactivo con soporte a operaciones CRUD completas.
- [ ] Capturas de pantalla con evidencias del funcionamiento de la aplicación.