package dao;

import model.Trabajadora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadoraDao {
    private Connection connection;

    public TrabajadoraDao(Connection connection) {
        this.connection = connection;
    }

    // 1. REGISTRAR
    public void registrar(Trabajadora t) {
        String query = "INSERT INTO trabajadora (id_trabajadora, especialidad) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, t.getIdPersona()); // Hereda el ID de Persona
            ps.setString(2, t.getEspecialidad());

            int filas = ps.executeUpdate();
            System.out.println("Trabajadora registrada");
            System.out.println(" Filas insertadas: " + filas);
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en INSERT de trabajadora: " + e.getMessage());
        }
    }

    // 2. LISTAR
    public List<Trabajadora> listarTrabajadoras() {
        List<Trabajadora> lista = new ArrayList<>();
        String query = "SELECT p.id_persona, p.nombre, p.dni, p.telefono, p.correo, t.especialidad " +
                       "FROM trabajadora t INNER JOIN persona p ON t.id_trabajadora = p.id_persona";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                String especialidad = resultSet.getString("especialidad");

                Trabajadora t = new Trabajadora(idPersona, nombre, dni, telefono, correo, especialidad);
                lista.add(t);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al listar trabajadoras: " + e.getMessage());
        }
        return lista;
    }

    // 3. BUSCAR POR ID
    public Trabajadora buscarPorId(int idBuscar) {
        String query = "SELECT p.id_persona, p.nombre, p.dni, p.telefono, p.correo, t.especialidad " +
                       "FROM trabajadora t INNER JOIN persona p ON t.id_trabajadora = p.id_persona WHERE t.id_trabajadora = ?";
        Trabajadora t = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idBuscar);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                String especialidad = resultSet.getString("especialidad");

                t = new Trabajadora(idPersona, nombre, dni, telefono, correo, especialidad);
            }
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajadora: " + e.getMessage());
        }
        return t;
    }

    // 4. ACTUALIZAR
    public void actualizar(Trabajadora t) {
        String query = "UPDATE trabajadora SET especialidad = ? WHERE id_trabajadora = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, t.getEspecialidad());
            ps.setInt(2, t.getIdPersona());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar trabajadora en BD: " + e.getMessage());
        }
    }

    // 5. ELIMINAR
    public void eliminar(int idEliminar) {
        String query = "DELETE FROM trabajadora WHERE id_trabajadora = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idEliminar);

            int filas = ps.executeUpdate();
            System.out.println("Trabajadora eliminada");
            System.out.println(" Filas eliminadas: " + filas);
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en DELETE de trabajadora: " + e.getMessage());
        }
    }
}
