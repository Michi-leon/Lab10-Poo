package dao;
import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private Connection connection;

    public ClienteDao(Connection connection) {
        this.connection = connection;
    }

    // 1. REGISTRAR:
    public void registrar(Cliente cliente) {
        String query = "INSERT INTO cliente (id_cliente, observacion, fecha_registro) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
             ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getObservacion());
            ps.setDate(3, Date.valueOf(cliente.getFechaRegistro()));

            int filas = ps.executeUpdate();
            System.out.println("Cliente registrado");
            System.out.println(" Filas insertadas: " + filas);
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en INSERT de cliente: " + e.getMessage());
        }
    }
    //2.Listar:
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT p.id_persona, p.nombre, p.dni, p.telefono, p.correo, c.id_cliente, c.observacion, c.fecha_registro FROM cliente c INNER JOIN persona p ON c.id_cliente = p.id_persona";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                int idCliente = resultSet.getInt("id_cliente");
                String observacion = resultSet.getString("observacion");
                String fechaBonita = resultSet.getString("fecha_texto");
                
                Cliente cliente = new Cliente(idPersona, nombre, dni, telefono, correo, idCliente, observacion, fechaBonita);
                lista.add(cliente);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // 3. BUSCAR POR ID:
    public Cliente buscarPorId(int idClienteBuscar) {
        String query = "SELECT p.id_persona, p.nombre, p.dni, p.telefono, p.correo, c.id_cliente, c.observacion, c.fecha_registro FROM cliente c INNER JOIN persona p ON c.id_cliente = p.id_persona WHERE c.id_cliente = ?";
        Cliente cliente = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idClienteBuscar);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                int idCliente = resultSet.getInt("id_cliente");
                String observacion = resultSet.getString("observacion");
                String fechaBonita = resultSet.getString("fecha_texto");

                cliente = new Cliente(idPersona, nombre, dni, telefono, correo, idCliente, observacion, fechaBonita);
            }
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    // 4. ACTUALIZAR:
    public void actualizar(Cliente cliente) {
        String query = "UPDATE cliente SET observacion = ? WHERE id_cliente = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getObservacion());
            ps.setInt(5, cliente.getIdCliente());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente en BD: " + e.getMessage());
        }
    }

    // 5. ELIMINAR: 
    public void eliminar(int idClienteEliminar) {
        String query = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idClienteEliminar);

            int filas = ps.executeUpdate();
            System.out.println("Cliente eliminado");
            System.out.println(" Filas eliminadas: " + filas);
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en DELETE de cliente: " + e.getMessage());
        }
    }
}
