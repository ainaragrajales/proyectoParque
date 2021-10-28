import Models.*;

import javax.swing.*;
import java.util.ArrayList;

public class VPrincipal_SQLite {
    private JPanel VPanelPrincipal;
    private JTabbedPane tabbedPane1;
    private JPanel PestanaEspectaculos;
    private JList<EspectaculoSQLite> listaEspectaculosSQLite;
    private JButton bt_AnadirEsp;
    private JButton bt_ModEsp;
    private JButton bt_eliminar;
    private JLabel lb_ID;
    private JLabel lb_Esp;
    private JLabel lb_aforo;
    private JLabel lb_Desc;
    private JTextField et_Espectaculo;
    private JTextField et_aforo;
    private JTextField et_Descripcion;
    private JTextField et_ID_Espec;
    private JLabel lb_lugar;
    private JLabel lb_fecha;
    private JTextField et_lugar;
    private JTextField et_fecha;
    private JLabel lb_horario;
    private JLabel lb_precio;
    private JTextField et_horario;
    private JTextField et_precio;
    private JButton bt_GuardarEspectaculo;
    private JPanel PestanaClientes;
    private JPanel panelDatos;
    private JLabel et_dni;
    private JLabel et_nombre;
    private JLabel et_apellido;
    private JLabel et_edad;
    private JTextField campo_nombre;
    private JTextField campo_apellido;
    private JTextField campo_edad;
    private JTextField campo_dni;
    private JButton bt_guardarCli;
    private JList<Cliente> list_clientesSQLite;
    private JButton bt_nuevoCli;
    private JButton bt_modificarCli;
    private JButton bt_eliminarCli;
    private JPanel PestanaEmpleados;
    private JList<EmpleadoSQLite> listaEmpleadosSQLite;
    private JButton bt_EmpleAnadir;
    private JButton bt_ModEmpl;
    private JButton bt_eliminarEmple;
    private JPanel lb_NacioEmpl;
    private JLabel lb_dniEmple;
    private JLabel lb_Empleado;
    private JLabel lb_ApeEmpl;
    private JLabel lb_fechaNacEmpl;
    private JTextField et_emple;
    private JTextField et_apeEmple;
    private JTextField et_NacEmp;
    private JTextField et_dniEmp;
    private JLabel lb_FechaContratoEmpl;
    private JTextField et_fechaContrEmp;
    private JTextField et_NacioEmpl;
    private JLabel lb_CargoEmpl;
    private JTextField et_CargoEmpl;
    private JButton bt_GuardarEmple;
    private JPanel PanelEspectaculos;
    private JList<Cliente> listadoClientesEspectaculosSQLite;
    private JScrollPane resultadoClientesEspectaculosSQLite;
    private JList<EmpleadoSQLite> listaEmpleadosEspectaculosSQLite;
    private JScrollPane resultadoEmpleadosEspectaculosSQLite;
    private JLabel lb_infoMySql;
    private JTextArea textAreaInfoMySql;
    private JButton infoButton;
    private JLabel lb_responsable;
    private JComboBox<EmpleadoSQLite> comboBoxEmpleadosSQLite;
    private JButton bt_salir;
    private JButton bt_AnadirEspecCliente;
    private JButton bt_BorrarEspecCliente;
    private JComboBox comboBoxEspectaculos;
    private JButton bt_GuardarEspecCliente;


    private ArrayList<EspectaculoSQLite> espectaculosSQLite = new ArrayList<>();
    private ArrayList<Cliente> clientesSQLite = new ArrayList<>();
    private ArrayList<Espectaculos_Cliente> espectaculosClientesSQLite = new ArrayList<>();
    private ArrayList<Espectaculos_Empleado> espectaculosEmpleadosSQLite = new ArrayList<>();
    private ArrayList<EmpleadoSQLite> empleadosSQLite = new ArrayList<>();

    private JTable tabla;
    private JTable tabla2;

    private Cliente clienteSQLite;
    private EmpleadoSQLite empleadoSQLite;
    private EspectaculoSQLite espectaculoSQLite;
    private Espectaculos_Empleado espectaculoEmpleadoSQLite;


    public VPrincipal_SQLite() {

        //cargaDatos();
        CargaryRefrescarTodo();

        //Poner los botones de guardar en oculto
        bt_guardarCli.setVisible(false);
        bt_GuardarEspectaculo.setVisible(false);
        bt_GuardarEmple.setVisible(false);

        //cargar_j_list_clientes();
        //cargar_j_list_empleados();
        //cargar_j_list_espectaculos();

        list_clientesSQLite.addListSelectionListener(e -> actualizarClientes());
        listaEmpleadosSQLite.addListSelectionListener(e -> actualizarEmpleados());
        listaEspectaculosSQLite.addListSelectionListener(e -> actualizarEspectaculos());

        // Carga de los empleados en un model de combobox para seleccionar el responsable, luefo le paso el modelo al objeto comboBox de la ventana
        DefaultComboBoxModel<EmpleadoSQLite> empleModel = new DefaultComboBoxModel<>();
        for (EmpleadoSQLite empleado : empleadosSQLite) {
            empleModel.addElement(empleado);
        }
        comboBoxEmpleadosSQLite.setModel(empleModel);

        // Jlist clientes Jtable espectaculos
        DefaultListModel<Cliente> modelClienteEspectaculo = new DefaultListModel<>();
        for (Cliente c : clientesSQLite) {
            modelClienteEspectaculo.addElement(c);
        }
        listadoClientesEspectaculosSQLite.setModel(modelClienteEspectaculo);
        tabla = new JTable();
        tabla.setModel(new ListaClientesEspectaculosSQLite(new Cliente()));
        resultadoClientesEspectaculosSQLite.setViewportView(tabla);
        listadoClientesEspectaculosSQLite.addListSelectionListener(e -> {
            tabla.setModel(new ListaClientesEspectaculosSQLite(listadoClientesEspectaculosSQLite.getSelectedValue()));
            System.out.println(listadoClientesEspectaculosSQLite.getSelectedValue().getEspectaculosSQLite());
        });

        // Jlist empleados Jtable espectaculos
        DefaultListModel<EmpleadoSQLite> modelEmpleadoEspectaculo = new DefaultListModel<>();
        for (EmpleadoSQLite e : empleadosSQLite) {
            modelEmpleadoEspectaculo.addElement(e);
        }
        listaEmpleadosEspectaculosSQLite.setModel(modelEmpleadoEspectaculo);
        tabla2 = new JTable();
        tabla2.setModel(new ListaEmpleadosEspectaculosSQLite(new EmpleadoSQLite()));
        resultadoEmpleadosEspectaculosSQLite.setViewportView(tabla2);
        listaEmpleadosEspectaculosSQLite.addListSelectionListener(e -> {
            tabla2.setModel(new ListaEmpleadosEspectaculosSQLite(listaEmpleadosEspectaculosSQLite.getSelectedValue()));
            System.out.println(listaEmpleadosEspectaculosSQLite.getSelectedValue().getEspectaculosSQLite());
        });


        //Botones pestaña espectaculos
        bt_AnadirEsp.addActionListener(e -> {
            et_ID_Espec.setEnabled(true);
            //et_ID_Espec.setVisible(false);
            limpiarCampos();
            bt_GuardarEspectaculo.setVisible(true);
        });
        bt_ModEsp.addActionListener(e -> {
            et_ID_Espec.setEnabled(false);
            bt_GuardarEspectaculo.setVisible(true);
        });
        bt_eliminar.addActionListener(e -> {
            String aforoString = String.valueOf(et_aforo.getText());
            int aforoInt = Integer.parseInt(aforoString);

            String precioString = String.valueOf(et_precio.getText());
            double precioDouble = Double.parseDouble(precioString);
            String idString = String.valueOf(et_ID_Espec.getText());
            int idInt = Integer.parseInt(idString);
            String responsable = String.valueOf(comboBoxEmpleadosSQLite.getSelectedItem());
            espectaculoSQLite = new EspectaculoSQLite(idInt, et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), et_fecha.getText(), et_horario.getText(), precioDouble, responsable);

            espectaculosSQLite.remove(espectaculoSQLite);
            for (EspectaculoSQLite espectaculo1 : espectaculosSQLite) {
                System.out.println(espectaculo1);
            }
            new SQLite.Carga().eliminarEspectaculo(espectaculoSQLite);
            cargaDatos();
            cargar_j_list_espectaculos();

            limpiarCampos();
            bt_GuardarEspectaculo.setVisible(false);
        });
        bt_GuardarEspectaculo.addActionListener(e -> {
            if (!comprobarCamposVaciosEspectaculos()) {

                panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);

            } else if (!et_ID_Espec.isEnabled()) {

                String aforoString = String.valueOf(et_aforo.getText());
                int aforoInt = Integer.parseInt(aforoString);

                String precioString = String.valueOf(et_precio.getText());
                double precioDouble = Double.parseDouble(precioString);
                String responsable = String.valueOf(comboBoxEmpleadosSQLite.getSelectedItem());
                espectaculoSQLite = new EspectaculoSQLite(et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), et_fecha.getText(), et_horario.getText(), precioDouble, responsable);

                espectaculosSQLite.add(espectaculoSQLite);
                for (EspectaculoSQLite espectaculo1 : espectaculosSQLite) {
                    System.out.println(espectaculo1);
                }

                new SQLite.Carga().modificarEspectaculo(espectaculoSQLite);
                cargaDatos();
                cargar_j_list_espectaculos();

            } else {
                String aforoString = String.valueOf(et_aforo.getText());
                int aforoInt = Integer.parseInt(aforoString);

                String precioString = String.valueOf(et_precio.getText());
                double precioDouble = Double.parseDouble(precioString);
                String responsable = String.valueOf(comboBoxEmpleadosSQLite.getSelectedItem());
                espectaculoSQLite = new EspectaculoSQLite(et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), et_fecha.getText(), et_horario.getText(), precioDouble, responsable);

                espectaculosSQLite.add(espectaculoSQLite);
                for (EspectaculoSQLite espectaculo1 : espectaculosSQLite) {
                    System.out.println(espectaculo1);
                }

                new SQLite.Carga().espectaculoNuevo(espectaculoSQLite);

                // Hayo el id máximo del ultima inserción de espectaculos y se lo paso a la tabla de espectaculos_Empleados para hacer el insert
                int idmax = new SQLite.Carga().idMaxEspectaculos();
                // Comprobacion por pantalla del iD máximo:
                System.out.println("\nID máximo de la tabla espectáculo es: " + idmax);

                // Hago un cast del objeto seleccionado del combobox a objeto Empleado, para poder aaceder luego a su dni  y pasarselo al constructor y a la función para añadir a la table espectaculos_empleados
                Empleado ep = (Empleado) comboBoxEmpleadosSQLite.getSelectedItem();
                System.out.println("Empleado: " + ep.getNombreEmple() + ", " + ep.getDniEmple());



                // Añado el empleado responsable a la tabla de espectaculos_empleados, con el dni del empleado escogido del comboBox String) y el espectaculo recien añadido (int)
                espectaculoEmpleadoSQLite = new Espectaculos_Empleado(ep.getDniEmple(), idmax);
                espectaculosEmpleadosSQLite.add(espectaculoEmpleadoSQLite);

                // Añado al empleado relacionado con su espectáculo  a la tabla de espectáculos_empleados
                new SQLite.Carga().anadirEmpleadoEspectaculo(ep.getDniEmple(), idmax);


                // Saco por pantalla una lista con todos los empleados con sus espectáculos que hay en la base de datos de la tabal espectaculos_empleados
                //espectaculoEmpleado.mostrarEspectaculosEmpleados(espectaculosEmpleados);
                for (Espectaculos_Empleado ep1 : espectaculosEmpleadosSQLite) {
                    System.out.println(ep1);
                }


                cargaDatos();
                cargar_j_list_espectaculos();
                //et_ID_Espec.setVisible(true);
            }
            limpiarCampos();
            bt_GuardarEspectaculo.setVisible(false);
        });

        //Botones pestaña Clientes
        bt_nuevoCli.addActionListener(e -> {
            campo_dni.setEnabled(true);
            limpiarCampos();
            bt_guardarCli.setVisible(true);
        });
        bt_modificarCli.addActionListener(e -> {
            campo_dni.setEnabled(false);
            bt_guardarCli.setVisible(true);
        });
        bt_eliminarCli.addActionListener(e -> {
            //Añadir mensaje de confirmación

            String edadString = String.valueOf(campo_edad.getText());
            int edadInt = Integer.parseInt(edadString);
            clienteSQLite = new Cliente(campo_dni.getText(), campo_nombre.getText(), campo_apellido.getText(), edadInt);
            clientesSQLite.remove(clienteSQLite);
            for (Cliente cliente1 : clientesSQLite) {
                System.out.println(cliente1);
            }
            new SQLite.Carga().eliminarCliente(clienteSQLite);
            cargaDatos();
            cargar_j_list_clientes();


            limpiarCampos();
            bt_guardarCli.setVisible(false);
        });
        bt_guardarCli.addActionListener(e -> {
            if (!comprobarCamposVaciosClientes()) {
                panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);
            } else if (!campo_dni.isEnabled()) {
                String edadString = String.valueOf(campo_edad.getText());
                int edadInt = Integer.parseInt(edadString);
                clienteSQLite = new Cliente(campo_dni.getText(), campo_nombre.getText(), campo_apellido.getText(), edadInt);
                clientesSQLite.add(clienteSQLite);
                for (Cliente cliente1 : clientesSQLite) {
                    System.out.println(cliente1);
                }
                new SQLite.Carga().modificarCliente(clienteSQLite);
                cargaDatos();
                cargar_j_list_clientes();
            } else {
                String edadString = String.valueOf(campo_edad.getText());
                int edadInt = Integer.parseInt(edadString);
                clienteSQLite = new Cliente(campo_dni.getText(), campo_nombre.getText(), campo_apellido.getText(), edadInt);
                clientesSQLite.add(clienteSQLite);
                for (Cliente cliente1 : clientesSQLite) {
                    System.out.println(cliente1);
                }
                new SQLite.Carga().clienteNuevo(clienteSQLite);
                cargaDatos();
                cargar_j_list_clientes();

            }
            //new SQLite.Carga().listaClientesEspectaculo();
            limpiarCampos();
            bt_guardarCli.setVisible(false);
        });


        //Botones pestaña empleados
        bt_EmpleAnadir.addActionListener(e -> {
            et_dniEmp.setEnabled(true);
            limpiarCampos();
            bt_GuardarEmple.setVisible(true);
        });
        bt_ModEmpl.addActionListener(e -> {
            et_dniEmp.setEnabled(false);
            bt_GuardarEmple.setVisible(true);
        });
        bt_eliminarEmple.addActionListener(e -> {

            empleadoSQLite = new EmpleadoSQLite(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), et_NacEmp.getText(), et_fechaContrEmp.getText(), et_NacioEmpl.getText(), et_CargoEmpl.getText());
            empleadosSQLite.remove(empleadoSQLite);
            for (EmpleadoSQLite empleado1 : empleadosSQLite) {
                System.out.println(empleado1);
            }

            new SQLite.Carga().eliminarEmpleado(empleadoSQLite);
            cargaDatos();
            cargar_j_list_empleados();

            limpiarCampos();
            bt_GuardarEmple.setVisible(false);
        });
        bt_GuardarEmple.addActionListener(e -> {
            if (!comprobarCamposVaciosEmpleados()) {
                panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);
            } else if (!et_dniEmp.isEnabled()) {



                empleadoSQLite = new EmpleadoSQLite(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), et_NacEmp.getText(), et_fechaContrEmp.getText(), et_NacioEmpl.getText(), et_CargoEmpl.getText());
                empleadosSQLite.add(empleadoSQLite);
                for (EmpleadoSQLite empleado1 : empleadosSQLite) {
                    System.out.println(empleado1);
                }

                new SQLite.Carga().modificarEmpleado(empleadoSQLite);
                cargaDatos();
                cargar_j_list_empleados();


            } else {

                empleadoSQLite = new EmpleadoSQLite(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), et_NacEmp.getText(), et_fechaContrEmp.getText(), et_NacioEmpl.getText(), et_CargoEmpl.getText());
                empleadosSQLite.add(empleadoSQLite);
                for (EmpleadoSQLite empleado1 : empleadosSQLite) {
                    System.out.println(empleado1);
                }

                new SQLite.Carga().empleadoNuevo(empleadoSQLite);
                cargaDatos();
                cargar_j_list_empleados();

            }
            limpiarCampos();
            bt_GuardarEmple.setVisible(false);
        });

        //Boton pestaña información de la base de datos
        infoButton.addActionListener(e -> {
            String infoBaseDatos = new SQLite.Carga().infoMySql(textAreaInfoMySql);

            textAreaInfoMySql.setText(infoBaseDatos);
        });
    }

   public void CargaryRefrescarTodo(){
        cargaDatos();
        cargar_tablas_ralciones_SQLite();
        cargar_j_list_clientes();
        cargar_j_list_empleados();
        cargar_j_list_espectaculos();

   }

    public void cargaDatos() {
        empleadosSQLite = new SQLite.Carga().listaEmpleadosSQLite();

        espectaculosSQLite = new SQLite.Carga().listaEspectaculosSQLite();

        clientesSQLite = new SQLite.Carga().listaClientesSQLite();

        espectaculosClientesSQLite = new SQLite.Carga().listaClientesEspectaculosSQLite();

        espectaculosEmpleadosSQLite = new SQLite.Carga().listaEmpleadosEspetaculosSQLite();
    }

    private void cargar_tablas_ralciones_SQLite(){
        System.out.println("\n**************** CARGA DE DATOS DE LOS CLIENTES POR ESPECTACULO *****************\n");

        for (int i = 0; i < espectaculosSQLite.size(); i++) {
            System.out.println("Posicion(i): " + i);
            System.out.println("Espectaculo nº: " + espectaculosSQLite.get(i).getNo_Espect());
            for (int j = 0; j < espectaculosClientesSQLite.size(); j++) {

                for (int k = 0; k < clientesSQLite.size(); k++) {
                    if (espectaculosSQLite.get(i).getNo_Espect() == espectaculosClientesSQLite.get(j).getEspectaculo() && clientesSQLite.get(k).getDni().equalsIgnoreCase(espectaculosClientesSQLite.get(j).getCliente())) {
                        Cliente c = new Cliente(clientesSQLite.get(k).getDni(), clientesSQLite.get(k).getNombre(), clientesSQLite.get(k).getApellidos(), clientesSQLite.get(k).getEdad());
                        EspectaculoSQLite eSQLite = new EspectaculoSQLite(espectaculosSQLite.get(i).getNo_Espect(), espectaculosSQLite.get(i).getNombreEspec(), espectaculosSQLite.get(i).getAforo(), espectaculosSQLite.get(i).getDescripcion(), espectaculosSQLite.get(i).getLugar(), espectaculosSQLite.get(i).getFecha_Espec(), espectaculosSQLite.get(i).getHorario_espec(), espectaculosSQLite.get(i).getPrecio(), espectaculosSQLite.get(i).getResponsable());

                        System.out.println("Cliente: " + c);
                        System.out.println("DNI cliente: " + espectaculosClientesSQLite.get(j).getCliente());

                        // Añado el cliente al ArrayList de clientes dentro del espectáculo elegido y luego le hago un 'set' al array de espectáculos del cliente
                        espectaculosSQLite.get(i).getClientes().add(c);
                        c.setEspectaculosSQLite(clientesSQLite.get(k).getEspectaculosSQLite());

                        // Añado el espectaculo al Array de espectáculos del cliente elegido y luego le hago un 'set' al array de clientes del espectáculo
                        clientesSQLite.get(k).getEspectaculosSQLite().add(eSQLite);
                        eSQLite.setClientes(espectaculosSQLite.get(i).getClientes());


                        System.out.println("Espectaculos del cliente " + c.getNombre() + ": " + c.getEspectaculosSQLite());
                    }

                }
            }

            System.out.println("Clientes del espectáculo " + espectaculosSQLite.get(i).getNo_Espect() + " '" + espectaculosSQLite.get(i).getNombreEspec() + "': " + espectaculosSQLite.get(i).getClientes());
            System.out.println();


        }


        System.out.println("\n**************** CARGA DE DATOS DE LOS EMPLEADOS POR ESPECTACULO *****************\n");


        for (int i = 0; i < espectaculosSQLite.size(); i++) {
            System.out.println("Posicion(i): " + i);
            System.out.println("Espectaculo nº: " + espectaculosSQLite.get(i).getNo_Espect());


            for (int j = 0; j < espectaculosEmpleadosSQLite.size(); j++) {

                for (int k = 0; k < empleadosSQLite.size(); k++) {
                    if (espectaculosSQLite.get(i).getNo_Espect() == espectaculosEmpleadosSQLite.get(j).getEspectaculo() && empleadosSQLite.get(k).getDniEmple().equalsIgnoreCase(espectaculosEmpleadosSQLite.get(j).getEmpleado())) {
                        EmpleadoSQLite empleado = new EmpleadoSQLite(empleadosSQLite.get(k).getDniEmple(), empleadosSQLite.get(k).getNombreEmple(), empleadosSQLite.get(k).getApeEmple(), empleadosSQLite.get(k).getFechaNac(), empleadosSQLite.get(k).getFechaContr(), empleadosSQLite.get(k).getNacionalidad(), empleadosSQLite.get(k).getCargo());
                        EspectaculoSQLite e = new EspectaculoSQLite(espectaculosSQLite.get(i).getNo_Espect(), espectaculosSQLite.get(i).getNombreEspec(), espectaculosSQLite.get(i).getAforo(), espectaculosSQLite.get(i).getDescripcion(), espectaculosSQLite.get(i).getLugar(), espectaculosSQLite.get(i).getFecha_Espec(), espectaculosSQLite.get(i).getHorario_espec(), espectaculosSQLite.get(i).getPrecio(), espectaculosSQLite.get(i).getResponsable());

                        System.out.println("Empleado: " + empleado);
                        System.out.println("DNI empleado: " + espectaculosEmpleadosSQLite.get(j).getEmpleado());

                        // Añado el empleado al ArrayList de empleados dentro del espectaculo elegido y luego le hago un 'set' al array de espectáculos del empleado
                        espectaculosSQLite.get(i).getEmpleadosSQLite().add(empleado);
                        empleado.setEspectaculosSQLite(empleadosSQLite.get(k).getEspectaculosSQLite());


                        // Añado el espectaculo al Array de espectáculos del empleado y luego le hago un 'set' al array de empleados del espectáculo
                        empleadosSQLite.get(k).getEspectaculosSQLite().add(e);
                        e.setEmpleadosSQLite(espectaculosSQLite.get(k).getEmpleadosSQLite());

                        System.out.println("Espectaculos del empleado " + empleado.getNombreEmple() + ": " + empleado.getEspectaculosSQLite());
                    }

                }
            }

            System.out.println("Empleados del espectáculo " + espectaculosSQLite.get(i).getNo_Espect() + " '" + espectaculosSQLite.get(i).getNombreEspec() + "': " + espectaculosSQLite.get(i).getEmpleadosSQLite());
            System.out.println();
        }

        System.out.println("Ejemplo de clientes y empleados de el espectáculo 'El rey león':\n");

        System.out.println("El rey León: " + espectaculosSQLite.get(0).getClientes());
        System.out.println("El rey León: " + espectaculosSQLite.get(0).getEmpleadosSQLite());

        // Hago una comprobación, que se ha accedido a la base de datos:


        System.out.println("\n**************** CARGA DE DATOS EN LOS ARRAYLIST DESDE LA BASE DE DATOS DE MYSQL *****************\n");

        System.out.println("\nDatos del arrayList 'espectáculos':\n");

        for (EspectaculoSQLite ep : espectaculosSQLite) {
            System.out.format("%-5d%-20s%-10d%-50s%-20s%-12s%-12s%n", ep.getNo_Espect(), ep.getNombreEspec(), ep.getAforo(), ep.getDescripcion(), ep.getLugar(), ep.getFecha_Espec(), ep.getHorario_espec());
        }

        System.out.println("\nDatos del arrayList 'empleados':\n");

        for (EmpleadoSQLite em : empleadosSQLite) {
            System.out.format("%-15s%-20s%-15s%-15s%-15s%-20s%-20s%n", em.getDniEmple(), em.getNombreEmple(), em.getApeEmple(), em.getFechaNac(), em.getFechaContr(), em.getNacionalidad(), em.getCargo());
        }

        System.out.println("\nDatos del arrayList 'clientes':\n");

        for (Cliente c : clientesSQLite) {
            System.out.format("%-20s%-20s%-20s%-5d%n", c.getDni(), c.getNombre(), c.getApellidos(), c.getEdad());
        }

        System.out.println("\nDatos del arrayList 'EspectaculosClientes':\n");

        for (Espectaculos_Cliente ec : espectaculosClientesSQLite) {
            System.out.format("%-5d%-20s%-5d%n", ec.getIdEspecCli(), ec.getCliente(), ec.getEspectaculo());
        }

        System.out.println("\nDatos del arrayList 'EspectaculosEmpleados':\n");

        for (Espectaculos_Empleado ep : espectaculosEmpleadosSQLite) {
            System.out.format("%-5d%-20s%-5d%n", ep.getIdEspecEmp(), ep.getEmpleado(), ep.getEspectaculo());
        }
    }

    private void cargar_j_list_clientes() {
        DefaultListModel<Cliente> model = new DefaultListModel<>();
        for (Cliente cliente : clientesSQLite) {
            model.addElement(cliente);
        }
        list_clientesSQLite.setModel(model);
    }

    private void cargar_j_list_empleados() {
        DefaultListModel<EmpleadoSQLite> model = new DefaultListModel<>();
        for (EmpleadoSQLite empleado : empleadosSQLite) {
            model.addElement(empleado);
        }
        listaEmpleadosSQLite.setModel(model);
    }

    private void cargar_j_list_espectaculos() {
        DefaultListModel<EspectaculoSQLite> model = new DefaultListModel<>();
        for (EspectaculoSQLite espectaculo : espectaculosSQLite) {
            model.addElement(espectaculo);
        }
        listaEspectaculosSQLite.setModel(model);
    }

    public void actualizarClientes() {
        Cliente cliente = list_clientesSQLite.getSelectedValue();

        if (cliente != null) {
            campo_dni.setText(cliente.getDni());
            campo_nombre.setText(cliente.getNombre());
            campo_apellido.setText(cliente.getApellidos());
            campo_edad.setText(String.valueOf(cliente.getEdad()));
        }
    }

    public void actualizarEmpleados() {
        EmpleadoSQLite empleado = listaEmpleadosSQLite.getSelectedValue();

        if (empleado != null) {
            et_dniEmp.setText(empleado.getDniEmple());
            et_emple.setText(empleado.getNombreEmple());
            et_apeEmple.setText(empleado.getApeEmple());
            et_NacEmp.setText(String.valueOf(empleado.getFechaNac()));
            et_fechaContrEmp.setText(String.valueOf(empleado.getFechaContr()));
            et_NacioEmpl.setText(String.valueOf(empleado.getNacionalidad()));
            et_CargoEmpl.setText(String.valueOf(empleado.getCargo()));
        }
    }

    public void actualizarEspectaculos() {
        EspectaculoSQLite espectaculo = listaEspectaculosSQLite.getSelectedValue();

        if (espectaculo != null) {
            et_ID_Espec.setText(String.valueOf(espectaculo.getNo_Espect()));
            et_Espectaculo.setText(espectaculo.getNombreEspec());
            et_aforo.setText(String.valueOf(espectaculo.getAforo()));
            et_Descripcion.setText(espectaculo.getDescripcion());
            et_lugar.setText(espectaculo.getLugar());
            et_fecha.setText(String.valueOf(espectaculo.getFecha_Espec()));
            et_horario.setText(String.valueOf(espectaculo.getHorario_espec()));
            et_precio.setText(String.valueOf(espectaculo.getPrecio()));
            comboBoxEmpleadosSQLite.setSelectedItem(espectaculo.getResponsable());
        }
    }

    private void limpiarCampos() {

        campo_edad.setText("");
        campo_dni.setText("");
        campo_apellido.setText("");
        campo_nombre.setText("");


        et_dniEmp.setText("");
        et_nombre.setText("");
        et_apeEmple.setText("");
        et_NacEmp.setText("");
        et_fechaContrEmp.setText("");
        et_NacioEmpl.setText("");
        et_CargoEmpl.setText("");


        et_ID_Espec.setText("");
        et_Espectaculo.setText("");
        et_aforo.setText("");
        et_Descripcion.setText("");
        et_lugar.setText("");
        et_fecha.setText("");
        et_horario.setText("");
        et_precio.setText("");
        //mirar el combobox

    }

    private boolean comprobarCamposVaciosEspectaculos() {
        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(et_ID_Espec);
        campos.add(et_Espectaculo);
        campos.add(et_aforo);
        campos.add(et_Descripcion);
        campos.add(et_lugar);
        campos.add(et_fecha);
        campos.add(et_horario);
        campos.add(et_precio);

        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    private boolean comprobarCamposVaciosClientes() {

        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(campo_dni);
        campos.add(campo_nombre);
        campos.add(campo_apellido);
        campos.add(campo_edad);

        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    private boolean comprobarCamposVaciosEmpleados() {

        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(campo_dni);
        campos.add(campo_nombre);
        campos.add(campo_apellido);
        campos.add(campo_edad);

        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    public void panelMensajePersonalizado(String titulo, String mensaje, int tipo) {
        JButton okButton = new JButton("OK");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane(mensaje, tipo, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    public JPanel getVPanelPrincipal() {
        return VPanelPrincipal;
    }

    public static void main(String[] args) {

    }
}
