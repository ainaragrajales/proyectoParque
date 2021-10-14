import Models.*;
import MySQL.Carga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VInicio {

    private JPanel VPanelInicio;
    private JButton clientesButton;
    private JButton empleadosButton;
    private JButton espectaculosButton;

    // Para comprobar si la ventana ya está abierta
    public static boolean ventanaAbierta = false;

    // Cargo en ArrayList las tablas de espectaculos, clientes y empleados y le paso los datos que recojo de la Base de datos
    ArrayList<Espectaculo> espectaculos = new ArrayList<>();
    ArrayList<Empleado> empleados = new ArrayList<>();
   final static ArrayList<Cliente> clientes = new Carga().listaClientes();
    ArrayList<Espectaculos_Cliente> espectaculosClientes = new ArrayList<>();
    ArrayList<Espectaculos_Empleado> espectaculosEmpleados = new ArrayList<>();


    public VInicio() {



        espectaculos = new Carga().listaEspectaculos();
        empleados = new Carga().listaEmpleados();
        //clientes = new Carga().listaClientes();
        espectaculosClientes = new Carga().listaClientesEspectaculo();
        espectaculosEmpleados = new Carga().listaEmpleadosEspectaculo();

        // Hago una comprobación, que se ha accedido a la base de datos



        /*System.out.println();
        System.out.println("Datos del arrayList espectáculos, recogidos de la base de datos:\n");

        for (Espectaculo e : espectaculos) {
            System.out.format("%-5d%-20s%-10d%-50s%-20s%-12s%-12s%n", e.getNo_Espect(), e.getNombreEspec(), e.getAforo(), e.getDescripcion(), e.getLugar(), e.getFecha_Espec(), e.getHorario_espec());
        }*/


        //Mostrar en consola

        // CARGA DE DATOS DE LOS CLIENTES DE UN ESPECTACULO  Y LOS EMPLEADOS DE UN ESPECTACULO

        // Aquí hay que cargar los clientes que tiene  cada espectaculo:
        // Voy recorriendo los espectaculos

        System.out.println("\n**************** CARGA DE DATOS DE LOS CLIENTES POR ESPECTACULO *****************\n");

        for (int i = 0; i < espectaculos.size(); i++) {
            System.out.println("Posicion(i): " + i);
            System.out.println("Espectaculo nº: " + espectaculos.get(i).getNo_Espect());
            for (int j = 0; j < espectaculosClientes.size(); j++) {

                for (int k = 0; k < clientes.size(); k++) {
                    if (espectaculos.get(i).getNo_Espect() == espectaculosClientes.get(j).getEspectaculo() && clientes.get(k).getDni().equalsIgnoreCase(espectaculosClientes.get(j).getCliente())) {
                        Cliente c = new Cliente(clientes.get(k).getDni(), clientes.get(k).getNombre(), clientes.get(k).getApellidos(), clientes.get(k).getEdad());
                        Espectaculo e = new Espectaculo(espectaculos.get(i).getNombreEspec());

                        System.out.println("Cliente: " + c);
                        System.out.println("DNI cliente: " + espectaculosClientes.get(j).getCliente());

                        // Añado el cliente al ArrayList de clientes dentro del espectáculo elegido y luego le hago un 'set' al array de espectáculos del cliente
                        espectaculos.get(i).getClientes().add(c);
                        c.setEspectaculos(clientes.get(k).getEspectaculos());

                        // Añado el espectaculo al Array de espectáculos del cliente elegido y luego le hago un 'set' al array de clientes del espectáculo
                        clientes.get(k).getEspectaculos().add(e);
                        e.setClientes(espectaculos.get(i).getClientes());


                        System.out.println("Espectaculos del cliente " + c.getNombre() + ": " + c.getEspectaculos());
                    }

                }
            }

            System.out.println("Clientes del espectáculo " + espectaculos.get(i).getNo_Espect() + " '" + espectaculos.get(i).getNombreEspec() + "': " + espectaculos.get(i).getClientes());
            System.out.println();


        }


        System.out.println("\n**************** CARGA DE DATOS DE LOS EMPLEADOS POR ESPECTACULO *****************\n");


        for (int i = 0; i < espectaculos.size(); i++) {
            System.out.println("Posicion(i): " + i);
            System.out.println("Espectaculo nº: " + espectaculos.get(i).getNo_Espect());


            for (int j = 0; j < espectaculosEmpleados.size(); j++) {

                for (int k = 0; k < empleados.size(); k++) {
                    if (espectaculos.get(i).getNo_Espect() == espectaculosEmpleados.get(j).getEspectaculo() && empleados.get(k).getDniEmple().equalsIgnoreCase(espectaculosEmpleados.get(j).getEmpleado())) {
                        Empleado empleado = new Empleado(empleados.get(k).getDniEmple(), empleados.get(k).getNombreEmple(), empleados.get(k).getApeEmple(), empleados.get(k).getFechaNac(), empleados.get(k).getFechaContr(), empleados.get(k).getNacionalidad(), empleados.get(k).getCargo());
                        Espectaculo e = new Espectaculo(espectaculos.get(i).getNombreEspec());

                        System.out.println("Empleado: " + empleado);
                        System.out.println("DNI empleado: " + espectaculosEmpleados.get(j).getEmpleado());

                        // Añado el empleado al ArrayList de empleados dentro del espectaculo elegido y luego le hago un 'set' al array de espectáculos del empleado
                        espectaculos.get(i).getEmpleados().add(empleado);
                        empleado.setEspectaculos(empleados.get(k).getEspectaculos());


                        // Añado el espectaculo al Array de espectáculos del empleado y luego le hago un 'set' al array de empleados del espectáculo
                        empleados.get(k).getEspectaculos().add(e);
                        e.setEmpleados(espectaculos.get(k).getEmpleados());

                        System.out.println("Espectaculos del empleado " + empleado.getNombreEmple() + ": " + empleado.getEspectaculos());
                    }

                }
            }

            System.out.println("Empleados del espectáculo " + espectaculos.get(i).getNo_Espect() + " '" + espectaculos.get(i).getNombreEspec() + "': " + espectaculos.get(i).getEmpleados());
            System.out.println();
        }

        System.out.println("Ejemplo de clientes y empleados de el espectáculo 'El rey león':\n");

        System.out.println("El rey León: " + espectaculos.get(0).getClientes());
        System.out.println("El rey León: " + espectaculos.get(0).getEmpleados());

        // Hago una comprobación, que se ha accedido a la base de datos:


        System.out.println("\n**************** CARGA DE DATOS EN LOS ARRAYLIST DESDE LA BASE DE DATOS DE MYSQL *****************\n");

        System.out.println("\nDatos del arrayList 'espectáculos':\n");

        for (Espectaculo ep : espectaculos) {
            System.out.format("%-5d%-20s%-10d%-50s%-20s%-12s%-12s%n", ep.getNo_Espect(), ep.getNombreEspec(), ep.getAforo(), ep.getDescripcion(), ep.getLugar(), ep.getFecha_Espec(), ep.getHorario_espec());
        }

        System.out.println("\nDatos del arrayList 'empleados':\n");

        for (Empleado em : empleados) {
            System.out.format("%-15s%-20s%-15s%-15s%-15s%-20s%-20s%n", em.getDniEmple(), em.getNombreEmple(), em.getApeEmple(), em.getFechaNac(), em.getFechaContr(), em.getNacionalidad(), em.getCargo());
        }

        System.out.println("\nDatos del arrayList 'clientes':\n");

        for (Cliente c : clientes) {
            System.out.format("%-20s%-20s%-20s%-5d%n", c.getDni(), c.getNombre(), c.getApellidos(), c.getEdad());
        }

        System.out.println("\nDatos del arrayList 'EspectaculosClientes':\n");

        for (Espectaculos_Cliente ec : espectaculosClientes) {
            System.out.format("%-5d%-20s%-5d%n", ec.getIdEspecCli(), ec.getCliente(), ec.getEspectaculo());
        }

        System.out.println("\nDatos del arrayList 'EspectaculosEmpleados':\n");

        for (Espectaculos_Empleado ep : espectaculosEmpleados) {
            System.out.format("%-5d%-20s%-5d%n", ep.getIdEspecEmp(), ep.getEmpleado(), ep.getEspectaculo());
        }

        espectaculosButton.addActionListener(e -> {
            if (!ventanaAbierta) {
                JFrame frame = new JFrame("Parque My Sql");
                //VPrincipal_MySQL vp = new VPrincipal_MySQL(clientes);
                frame.setContentPane((new VPrincipal_MySQL(clientes)).VPanelPrincipal);
                //(new VPrincipal_MySQL(clientes)).cargaDatos();
                frame.setMinimumSize(new Dimension(1200, 600)); // Lo ajusto a un tamaño para que se vea bien
                frame.setLocationRelativeTo(null); // Saca la ventana al centro
                frame.pack();
                frame.setVisible(true);
                ventanaAbierta = true;
            } else {
                JOptionPane.showMessageDialog(null, "La ventana Principal ya esta abierta!!!");
            }
           // abrirVPrincipal();
        });
    }


    // Con esta función recojo los datos de la BD de MySql y se lo paso al ArrayList de espectaculos


   /* public static List<Espectaculo> listaEspectaculos() {

        List<Espectaculo> espectaculos = new ArrayList<>();
        String sql = "SELECT * FROM espectaculos";

        try {
            //Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dam3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET", "root", "damnoc1819");


            Statement sentencia = conexion.createStatement();

            // hace la consulta
            ResultSet resul = sentencia.executeQuery("SELECT * FROM espectaculos");

            while (resul.next()) {
                // Creo un objeto 'espectaculo' vacío
                Espectaculo espectaculo = new Espectaculo();

                // voy pasáandole los atributos al objeto 'espectaculo'
                espectaculo.setNo_Espect(resul.getInt(1));
                espectaculo.setNombreEspec(resul.getString(2));
                espectaculo.setAforo(resul.getInt(3));
                espectaculo.setDescripcion(resul.getString(4));
                espectaculo.setLugar(resul.getString(5));
                espectaculo.setFecha_Espec(resul.getString(6));
                espectaculo.setHorario_espec(resul.getString(7));


                // Añado el objeto 'espectaculo' al ArrayList espectaculos
                espectaculos.add(espectaculo);
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();

        } catch (SQLException | ClassNotFoundException e) {
            // hacer algo con la excepcion
        }

        // La función me devuelve el ArrayList de espectaculos
        return espectaculos;
    }*/

    public void abrirVPrincipal() {
        JFrame frame = new JFrame("Gestión Parques");
        VPrincipal_MySQL vp = new VPrincipal_MySQL(clientes);
        frame.setContentPane(vp.getVPanelPrincipal());
        /*vp.CargaCLientes();
        vp.CargaEmples();
        vp.CargaESpectaculos();
        vp.CargaCli_Espec();
        vp.CargaEmp_Espec();*/
        vp.cargaDatos();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLocation(50, 50); // Posiciono la ventana es la esquina superior izquierda, para que no estorbe a otras ventanas
        //frame.setLocationRelativeTo(null); // Saca la ventana al centro
        frame.pack();
        frame.setVisible(true);
    }

    // Le paso la clase main para que se EJECUTE AL ARRANCAR LA APLICACION
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Inicio");
        frame.setContentPane(new VInicio().VPanelInicio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLocation(50, 50); // Posiciono la ventana es la esquina superior izquierda, para que no estorbe a otras ventanas
        //frame.setLocationRelativeTo(null); // Saca la ventana al centro
        frame.pack();
        frame.setVisible(true);
    }
}
