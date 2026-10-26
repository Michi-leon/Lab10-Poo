import Controller.GestorCliente;
import dao.ClienteDao;
import dao.ProductoDao;
import dao.TrabajadoraDao;
import database.Conexion;
import model.Cliente;
import model.Producto;
import model.Trabajadora;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conexion conexionDB = new Conexion();
        Connection conexion = conexionDB.conectarBaseDatos();

        if (conexion == null) {
            System.out.println("NO SE PUEDE CONECTAR CON LA BASE DE DATOS");
            return;
        }

        // Inicializar DAOs y Gestores
        ClienteDao clienteDao = new ClienteDao(conexion);
        GestorCliente gestorCliente = new GestorCliente(clienteDao);
        ProductoDao productoDao = new ProductoDao(conexion);
        TrabajadoraDao trabajadoraDao = new TrabajadoraDao(conexion);

        int opcionPrincipal = 0;

        do {

            System.out.println("SISTEMA TIENDA Y SALÓN DE BELLEZA V2");
            System.out.println("1. GESTION DE PRODUCTOS");
            System.out.println("2. GESTION DE CLIENTES");
            System.out.println("3. GESTION DE TRABAJADORAS");
            System.out.println("4. SALIR DEL PROGRAMA");
            System.out.println("INGRESE UNA OPCION: ");

            if (scanner.hasNextInt()) {
                opcionPrincipal = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("POR FAVOR, INGRESE UN NUMERO VALIDO.");
                scanner.nextLine();
                continue;
            }

            switch (opcionPrincipal) {
                case 1:
                    menuProductos(scanner, productoDao);
                    break;
                case 2:
                    menuClientes(scanner, gestorCliente);
                    break;
                case 3:
                    menuTrabajadoras(scanner, trabajadoraDao);
                    break;
                case 4:
                    System.out.println("SALIENDO DEL SISTEMA. HASTA LUEGO");
                    conexionDB.cerrarConexion();
                    break;
                default:
                    System.out.println("OPCION NO VALIDA. INTENTE DE NUEVO.");
            }
        } while (opcionPrincipal != 4);

        scanner.close();
    }

    private static void menuProductos(Scanner scanner, ProductoDao productoDao) {
        int op;
        do {
            System.out.println("SUBMENU GESTIÓN DE PRODUCTOS >>>");
            System.out.println("1. REGISTRAR PRODUCTO");
            System.out.println("2. LISTAR PRODUCTOS");
            System.out.println("3. BUSCAR PRODUCTO POR ID");
            System.out.println("4. ACTUALIZAR PRODUCTO");
            System.out.println("5. ELIMINAR PRODUCTO");
            System.out.println("6. VOLVER AL MENU PRINCIPAL");
            System.out.println("INGRESE UNA OPCION: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.println("REGISTRAR PRODUCTO >>>");
                    System.out.println("Nombre del producto: ");
                    String nombreP = scanner.nextLine();
                    System.out.println("Precio: ");
                    double precioP = scanner.nextDouble();
                    System.out.println("Stock: ");
                    int stockP = scanner.nextInt();
                    scanner.nextLine();

                    Producto nuevoProducto = new Producto(0, nombreP, precioP, stockP);
                    productoDao.registrar(nuevoProducto);
                    break;

                case 2:
                    System.out.println("LISTA DE PRODUCTOS");
                    List<Producto> productos = productoDao.listarProductos();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        for (Producto p : productos) {
                            System.out.println("ID: " + p.getId() + " > Nombre: " + p.getNombre() + " > Precio: " + p.getPrecio() + " > Stock: " + p.getStock());
                        }
                    }
                    break;

                case 3:
                    System.out.println("BUSCAR PRODUCTO POR ID");
                    System.out.println("Ingrese ID del producto: ");
                    int idBuscProd = scanner.nextInt();
                    scanner.nextLine();
                    Producto prod = productoDao.buscarPorId(idBuscProd);
                    if (prod != null) {
                        System.out.println("Encontrado >> Nombre: " + prod.getNombre() + " > Precio: " + prod.getPrecio() + " > Stock: " + prod.getStock());
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("ACTUALIZAR PRODUCTO");
                    System.out.println("Ingrese ID del producto a actualizar: ");
                    int idActProd = scanner.nextInt();
                    scanner.nextLine();
                    Producto prodExistente = productoDao.buscarPorId(idActProd);
                    if (prodExistente != null) {
                        System.out.println("Nuevo nombre (" + prodExistente.getNombre() + "): ");
                        String nNom = scanner.nextLine();
                        System.out.println("Nuevo precio: ");
                        double nPre = scanner.nextDouble();
                        System.out.println("Nuevo stock: ");
                        int nSto = scanner.nextInt();
                        scanner.nextLine();

                        prodExistente.setNombre(nNom);
                        prodExistente.setPrecio(nPre);
                        prodExistente.setStock(nSto);
                        productoDao.actualizar(prodExistente);
                    } else {
                        System.out.println("El producto no existe.");
                    }
                    break;

                case 5:
                    System.out.println("ELIMINAR PRODUCTO");
                    System.out.println("Ingrese ID del producto a eliminar: ");
                    int idElimProd = scanner.nextInt();
                    scanner.nextLine();
                    productoDao.eliminar(idElimProd);
                    break;

                case 6:
                    System.out.println("REGRESANDO AL MENU PRINCIPAL >>>");
                    break;

                default:
                    System.out.println("OPCION INVALIDA.");
            }
        } while (op != 6);
    }

    private static void menuClientes(Scanner scanner, GestorCliente gestorCliente) {
        int op = 0;
        do {
            System.out.println("SUBMENÚ: GESTIÓN DE CLIENTES");
            System.out.println("1. REGISTRAR CLIENTE");
            System.out.println("2. LISTAR CLIENTES");
            System.out.println("3. BUSCAR CLIENTE POR ID");
            System.out.println("4. ACTUALIZAR > MODIFICAR CLIENTE");
            System.out.println("5. ELIMINAR CLIENTE");
            System.out.println("6. VOLVER AL MENU PRINCIPAL");
            System.out.println("INGRESE UNA OPCION: ");

            if (scanner.hasNextInt()) {
                op = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ENTRADA INVALIDA.");
                scanner.nextLine();
                continue;
            }

            switch (op) {
                case 1:
                    System.out.println("REGISTRAR CLIENTE");
                    System.out.println("ID de Persona: ");
                    int idPersona = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nombre completo: ");
                    String nombre = scanner.nextLine();
                    System.out.println("DNI (8 dígitos max): ");
                    String dni = scanner.nextLine();
                    System.out.println("Teléfono (9 dígitos max): ");
                    String telefono = scanner.nextLine();
                    System.out.println("Correo electrónico: ");
                    String correo = scanner.nextLine();
                    System.out.println("ID de Cliente: ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Observación: ");
                    String observacion = scanner.nextLine();

                    Cliente nuevoCliente = new Cliente(idPersona, nombre, dni, telefono, correo, idCliente, observacion);
                    gestorCliente.registrarCliente(nuevoCliente);
                    break;

                case 2:
                    System.out.println("LISTA DE CLIENTES");
                    gestorCliente.listarClientes();
                    break;

                case 3:
                    System.out.println("BUSCAR CLIENTE POR ID");
                    System.out.println("Ingrese ID del cliente: ");
                    int idBusc = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cEncontrado = gestorCliente.buscarCliente(idBusc);
                    if (cEncontrado != null) {
                        System.out.println("Cliente encontrado:");
                        cEncontrado.imprimirInformacion();
                    }
                    break;

                case 4:
                    System.out.println("MODIFICAR CLIENTE");
                    System.out.println("Ingrese el ID del cliente a modificar: ");
                    int idMod = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cMod = gestorCliente.buscarCliente(idMod);
                    if (cMod != null) {
                        System.out.println("1. Modificar Nombre");
                        System.out.println("2. Modificar Teléfono");
                        System.out.println("3. Modificar Correo");
                        System.out.println("4. Modificar Observación");
                        System.out.println("Elija qué desea modificar: ");
                        int subOpt = scanner.nextInt();
                        scanner.nextLine();

                        switch (subOpt) {
                            case 1:
                                System.out.println("Nuevo nombre: ");
                                String nNom = scanner.nextLine();
                                gestorCliente.modificarNombre(idMod, nNom);
                                break;
                            case 2:
                                System.out.println("Nuevo teléfono (9 dígitos max): ");
                                String nTel = scanner.nextLine();
                                gestorCliente.modificarTelefono(idMod, nTel);
                                break;
                            case 3:
                                System.out.println("Nuevo correo: ");
                                String nCor = scanner.nextLine();
                                gestorCliente.modificarCorreo(idMod, nCor);
                                break;
                            case 4:
                                System.out.println("Nueva observación: ");
                                String nObs = scanner.nextLine();
                                gestorCliente.modificarObservacion(idMod, nObs);
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("ELIMINAR CLIENTE");
                    System.out.println("Ingrese ID del cliente a eliminar: ");
                    int idElim = scanner.nextInt();
                    scanner.nextLine();
                    gestorCliente.eliminarCliente(idElim);
                    break;

                case 6:
                    System.out.println("REGRESANDO AL MENU PRINCIPAL>>");
                    break;

                default:
                    System.out.println("OPCION NO VALIDA.");
            }
        } while (op != 6);
    }

    private static void menuTrabajadoras(Scanner scanner, TrabajadoraDao trabajadoraDao) {
            int op = 0;
            do {
                System.out.println("SUBMENÚ: GESTIÓN DE TRABAJADORAS ");
                System.out.println("1. REGISTRAR TRABAJADORA");
                System.out.println("2. LISTAR TRABAJADORAS");
                System.out.println("3. BUSCAR TRABAJADORA POR ID");
                System.out.println("4. ACTUALIZAR TRABAJADORA");
                System.out.println("5. ELIMINAR TRABAJADORA");
                System.out.println("6. VOLVER AL MENU PRINCIPAL");
                System.out.println("INGRESE UNA OPCION: ");

                if (scanner.hasNextInt()) {
                    op = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("ENTRADA INVALIDA.");
                    scanner.nextLine();
                    continue;
                }

                switch (op) {
                    case 1:
                        System.out.println("REGISTRAR TRABAJADORA");
                        System.out.println("ID de Persona: ");
                        int idPersona = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Nombre completo: ");
                        String nombre = scanner.nextLine();
                        System.out.println("DNI (8 dígitos max): ");
                        String dni = scanner.nextLine();
                        System.out.println("Teléfono (9 dígitos max): ");
                        String telefono = scanner.nextLine();
                        System.out.println("Correo electrónico: ");
                        String correo = scanner.nextLine();
                        System.out.println("Especialidad: ");
                        String especialidad = scanner.nextLine();

                        Trabajadora nuevaTrabajadora = new Trabajadora(idPersona, nombre, dni, telefono, correo, especialidad);
                        trabajadoraDao.registrar(nuevaTrabajadora);
                        break;

                    case 2:
                        System.out.println("LISTA DE TRABAJADORAS");
                        List<Trabajadora> lista = trabajadoraDao.listarTrabajadoras();
                        if (lista.isEmpty()) {
                            System.out.println("No hay trabajadoras registradas en el sistema.");
                        } else {
                            for (Trabajadora t : lista) {
                                t.imprimirInformacion();
                                System.out.println();
                            }
                        }
                        break;

                    case 3:
                        System.out.println("BUSCAR TRABAJADORA POR ID");
                        System.out.println("Ingrese ID de la trabajadora: ");
                        int idBusc = scanner.nextInt();
                        scanner.nextLine();
                        Trabajadora tEncontrada = trabajadoraDao.buscarPorId(idBusc);
                        if (tEncontrada != null) {
                            System.out.println("Trabajadora encontrada:");
                            tEncontrada.imprimirInformacion();
                        } else {
                            System.out.println("Trabajadora con ID " + idBusc + " no encontrado.");
                        }
                        break;

                    case 4:
                        System.out.println("ACTUALIZAR TRABAJADORA");
                        System.out.println("Ingrese el ID de la trabajadora a modificar: ");
                        int idMod = scanner.nextInt();
                        scanner.nextLine();
                        Trabajadora tMod = trabajadoraDao.buscarPorId(idMod);
                        if (tMod != null) {
                            System.out.println("Nueva especialidad: ");
                            String nuevaEsp = scanner.nextLine();
                            tMod.setEspecialidad(nuevaEsp);
                            trabajadoraDao.actualizar(tMod);
                            System.out.println("La especialidad se ha modificado con éxito");
                        } else {
                            System.out.println("Trabajadora con ID " + idMod + " no encontrado.");
                        }
                        break;

                    case 5:
                        System.out.println("ELIMINAR TRABAJADORA");
                        System.out.println("Ingrese ID de la trabajadora a eliminar: ");
                        int idElim = scanner.nextInt();
                        scanner.nextLine();
                        trabajadoraDao.eliminar(idElim);
                        break;

                    case 6:
                        System.out.println("REGRESANDO AL MENU PRINCIPAL>>");
                        break;

                    default:
                        System.out.println("OPCION NO VALIDA.");
                }
            } while (op != 6);
        }
}