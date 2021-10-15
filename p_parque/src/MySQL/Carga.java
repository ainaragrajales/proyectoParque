package MySQL;

import Models.*;
import com.mysql.jdbc.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Carga {

    public ArrayList<Espectaculo> listaEspectaculos() {

        ArrayList<Espectaculo> espectaculos = new ArrayList<>();
        String sql = "SELECT * FROM espectaculos";

        try {
            //Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque", "root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            // hace la consulta
            ResultSet resul = sentencia.executeQuery("SELECT * FROM espectaculos order by fecha_Espec desc");

            while (resul.next()) {
                // Creo un objeto 'espectaculo' vacío
                Espectaculo espectaculo = new Espectaculo();

                // voy pasáandole los atributos al objeto 'espectaculo'
                espectaculo.setNo_Espect(resul.getInt(1));
                espectaculo.setNombreEspec(resul.getString(2));
                espectaculo.setAforo(resul.getInt(3));
                espectaculo.setDescripcion(resul.getString(4));
                espectaculo.setLugar(resul.getString(5));
                espectaculo.setFecha_Espec(resul.getDate(6));
                espectaculo.setHorario_espec(resul.getTime(7));
                espectaculo.setPrecio(resul.getInt(8));


                // Añado el objeto 'espectaculo' al ArrayList espectaculos
                espectaculos.add(espectaculo);
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();
            //conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            // hacer algo con la excepcion
        }

        // La función me devuelve el ArrayList de espectaculos
        return espectaculos;
    }

    public ArrayList<Empleado> listaEmpleados() {

        ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try {
            //Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque", "root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            // hace la consulta
            ResultSet resul = sentencia.executeQuery("SELECT * FROM empleados");

            while (resul.next()) {
                // Creo un objeto 'espectaculo' vacío
                Empleado empleado = new Empleado();

                // voy pasáandole los atributos al objeto 'espectaculo'
                empleado.setDniEmple(resul.getString(1));
                empleado.setNombreEmple(resul.getString(2));
                empleado.setApeEmple(resul.getString(3));
                empleado.setFechaNac(resul.getString(4));
                empleado.setFechaContr(resul.getString(5));
                empleado.setNacionalidad(resul.getString(6));
                empleado.setCargo(resul.getString(7));


                // Añado el objeto 'espectaculo' al ArrayList espectaculos
                empleados.add(empleado);
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();
            // Cerrar conexion
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            // hacer algo con la excepcion
        }


        // La función me devuelve el ArrayList de espectaculos
        return empleados;
    }


    public ArrayList<Cliente> listaClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try {
            //Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");

            Statement sentencia = (Statement) conexion.createStatement();

            // hace la consulta
            ResultSet resul = sentencia.executeQuery("SELECT * FROM clientes");

            while (resul.next()) {
                // Creo un objeto 'espectaculo' vacío
                Cliente cliente = new Cliente();

                // voy pasáandole los atributos al objeto 'espectaculo'
                cliente.setDni(resul.getString(1));
                cliente.setNombre(resul.getString(2));
                cliente.setApellidos(resul.getString(3));
                cliente.setEdad(resul.getInt(4));


                // Añado el objeto 'espectaculo' al ArrayList espectaculos
                clientes.add(cliente);
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();
            // Cerrar conexion
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            // hacer algo con la excepcion
        }


        // La función me devuelve el ArrayList de espectaculos
        return clientes;
    }


    public ArrayList<Espectaculos_Cliente> listaClientesEspectaculo() {

        ArrayList<Espectaculos_Cliente> espectaculosClientes = new ArrayList<>();
        String sql = "select * from Espectaculos_Clientes";

        try {
            //Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            // hace la consulta
            ResultSet resul = sentencia.executeQuery("select * from Espectaculos_Clientes");

            while (resul.next()) {
                // Creo un objeto 'espectaculo' vacío
                Espectaculos_Cliente espectaculos_cliente = new Espectaculos_Cliente();

                // voy pasáandole los atributos al objeto 'espectaculo'
                espectaculos_cliente.setIdEspecCli(resul.getInt(1));
                espectaculos_cliente.setCliente(resul.getString(2));
                espectaculos_cliente.setEspectaculo(resul.getInt(3));


                // Añado el objeto 'espectaculo' al ArrayList espectaculos
                espectaculosClientes.add(espectaculos_cliente);
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();
            // Cerrar conexion
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            // hacer algo con la excepcion
        }


        // La función me devuelve el ArrayList de espectaculos
        return espectaculosClientes;
    }


    public ArrayList<Espectaculos_Empleado> listaEmpleadosEspectaculo() {

        ArrayList<Espectaculos_Empleado> espectaculosEmpleados = new ArrayList<>();
        String sql = "select * from Espectaculos_Empleados";

        try {
            //Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            // hace la consulta
            ResultSet resul = sentencia.executeQuery("select * from Espectaculos_Empleados");

            while (resul.next()) {
                // Creo un objeto 'espectaculo' vacío
                Espectaculos_Empleado espectaculos_empleado = new Espectaculos_Empleado();

                // voy pasáandole los atributos al objeto 'espectaculo'
                espectaculos_empleado.setIdEspecEmp(resul.getInt(1));
                espectaculos_empleado.setEmpleado(resul.getString(2));
                espectaculos_empleado.setEspectaculo(resul.getInt(3));


                // Añado el objeto 'espectaculo' al ArrayList espectaculos
                espectaculosEmpleados.add(espectaculos_empleado);
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();
            // Cerrar conexion
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            // hacer algo con la excepcion
        }


        // La función me devuelve el ArrayList de espectaculos
        return espectaculosEmpleados;
    }


    public void clienteNuevo(Cliente cliente) {

        PreparedStatement ps;
        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            sql = "insert into clientes(dniCli, nombreCli, ApesCli, edad) values (?,?,?,?)";

            ps = (PreparedStatement) conexion.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setInt(4, cliente.getEdad());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se han insertado los datos");

            sentencia.close();
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    public void modificarCliente(Cliente cliente) {

        PreparedStatement ps;
        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            sql = "update clientes set  nombreCli=?, ApesCli=?, edad=? where dniCli=?";

            ps = (PreparedStatement) conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setInt(3, cliente.getEdad());
            ps.setString(4, cliente.getDni());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se han insertado los datos");

            sentencia.close();
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    public void eliminarCliente(Cliente cliente) {

        PreparedStatement ps;
        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver");


            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            sql = "delete from clientes where dniCli=?";

            ps = (PreparedStatement) conexion.prepareStatement(sql);
            ps.setString(1, cliente.getDni());


            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se ha borrado el cliente " + cliente.getNombre());

            sentencia.close();
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }


    public void espectaculoNuevo(Espectaculo espectaculo) {

        PreparedStatement ps;
        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "elena", "elena123321");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pruebaParque","root", "several975:burn:month:War");


            Statement sentencia = (Statement) conexion.createStatement();

            sql = "insert into espectaculos(no_espec, nombreEspec, aforo, descripcion, lugar, precio) values (?,?,?,?,?,?)";

            ps = (PreparedStatement) conexion.prepareStatement(sql);
            ps.setInt(1, espectaculo.getNo_Espect());
            ps.setString(2, espectaculo.getNombreEspec());
            ps.setInt(3, espectaculo.getAforo());
            ps.setString(4, espectaculo.getDescripcion());
            ps.setString(5, espectaculo.getLugar());
            //ps.setDate(6, null);
            //ps.setTime(7, null);
            ps.setDouble(6, espectaculo.getPrecio());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se han insertado los datos");

            sentencia.close();
            conexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
}
