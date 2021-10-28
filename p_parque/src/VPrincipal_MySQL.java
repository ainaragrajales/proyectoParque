import Models.*;
import MySQL.Carga;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class VPrincipal_MySQL {
    public JPanel VPanelPrincipal;
    private JTabbedPane tabbedPane1;
    private JPanel PestanaEspectaculos;
    private JList<Espectaculo> listaEspectaculos;
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
    private JList<Cliente> list_clientes;
    private JButton bt_nuevoCli;
    private JButton bt_modificarCli;
    private JButton bt_eliminarCli;
    private JPanel PestanaEmpleados;
    private JList<Empleado> listaEmpleados;
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
    private JList<Cliente> listadoClientesEspectaculos;
    private JList<Empleado> listaEmpleadosEspectaculos;
    private JScrollPane resultadoClientesEspectaculos;

    private JScrollPane resultadoEmpleadosEspectaculos;
    private JLabel lb_responsable;
    private JComboBox<String> comboBoxEmpleados;
    private JLabel lb_infoMySql;
    private JTextArea textAreaInfoMySql;
    private JButton infoButton;
    private JButton bt_salir;
    private JButton bt_AnadirEspecCliente;
    private JButton bt_BorrarEspecCliente;
    private JButton bt_GuardarEspecCliente;
    private JTextField textField1;

    private JTable tabla;
    private JTable tabla2;
    private JComboBox<Espectaculo> comboBoxEspectaculos;
    private JButton bt_AnadirEspecEmpleado;
    private JButton bt_BorrarEspecEmpleado;
    private JComboBox<Espectaculo> comboBoxEspectaculos2;
    private JButton bt_GuardarEspecEmpleado;


    private ArrayList<Espectaculo> espectaculos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Espectaculos_Cliente> espectaculosClientes = new ArrayList<>();
    private ArrayList<Espectaculos_Empleado> espectaculosEmpleados = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

    private Cliente cliente;
    private Empleado empleado;
    private Espectaculo espectaculo;
    private Espectaculos_Empleado espectaculoEmpleado;
    private Espectaculos_Cliente espectaculosCliente;

    public VPrincipal_MySQL() {


        CargaryRefrescarTodo();

        //Poner los botones de guardar en oculto
        bt_guardarCli.setVisible(false);
        bt_GuardarEspectaculo.setVisible(false);
        bt_GuardarEmple.setVisible(false);
        comboBoxEspectaculos.setVisible(false);
        bt_GuardarEspecCliente.setVisible(false);
        comboBoxEspectaculos2.setVisible(false);
        bt_GuardarEspecEmpleado.setVisible(false);

        //Cargar las listas
        list_clientes.addListSelectionListener(e -> actualizarClientes());
        listaEspectaculos.addListSelectionListener(e -> actualizarEspectaculos());
        listaEmpleados.addListSelectionListener(e -> actualizarEmpleados());

        //Botones de la pestaña Clientes
        bt_nuevoCli.addActionListener(e -> {

            campo_dni.setEnabled(true);
            limpiarCampos();
            bt_guardarCli.setVisible(true);
        });
        bt_guardarCli.addActionListener(e -> {
            if (!comprobarCamposVacios()) {
                panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);
            } else if (!campo_dni.isEnabled()) {
                String edadString = String.valueOf(campo_edad.getText());
                int edadInt = Integer.parseInt(edadString);
                cliente = new Cliente(campo_dni.getText(), campo_nombre.getText(), campo_apellido.getText(), edadInt);
                clientes.add(cliente);
                for (Cliente cliente1 : clientes) {
                    System.out.println(cliente1);
                }
                new Carga().modificarCliente(cliente);
                cargaDatos();
                cargar_j_list_clientes();
            } else {
                String edadString = String.valueOf(campo_edad.getText());
                int edadInt = Integer.parseInt(edadString);
                cliente = new Cliente(campo_dni.getText(), campo_nombre.getText(), campo_apellido.getText(), edadInt);
                clientes.add(cliente);
                for (Cliente cliente1 : clientes) {
                    System.out.println(cliente1);
                }
                new Carga().clienteNuevo(cliente);
                cargaDatos();
                cargar_j_list_clientes();

            }
            //new Carga().listaClientesEspectaculo();
            limpiarCampos();
            bt_guardarCli.setVisible(false);
        });
        bt_modificarCli.addActionListener(e -> {

            campo_dni.setEnabled(false);
            bt_guardarCli.setVisible(true);


        });
        bt_eliminarCli.addActionListener(e -> {
            //JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

            String edadString = String.valueOf(campo_edad.getText());
            int edadInt = Integer.parseInt(edadString);
            cliente = new Cliente(campo_dni.getText(), campo_nombre.getText(), campo_apellido.getText(), edadInt);
            clientes.remove(cliente);
            for (Cliente cliente1 : clientes) {
                System.out.println(cliente1);
            }
            new Carga().eliminarCliente(cliente);
            cargaDatos();
            cargar_j_list_clientes();


            limpiarCampos();
            bt_guardarCli.setVisible(false);
        });


        //Botones de la pestaña Espectaculos
        bt_AnadirEsp.addActionListener(e -> {

            et_ID_Espec.setEnabled(true);
            //et_ID_Espec.setVisible(false);
            limpiarCampos();
            bt_GuardarEspectaculo.setVisible(true);

        });
        bt_GuardarEspectaculo.addActionListener(e -> {
            if (!comprobarCamposVaciosEspectaculos()) {

                panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);

            } else if (!et_ID_Espec.isEnabled()) {

                String aforoString = String.valueOf(et_aforo.getText());
                int aforoInt = Integer.parseInt(aforoString);
                String fechaString = String.valueOf(et_fecha.getText());
                Date fechaDate = Date.valueOf(fechaString);
                String horarioString = String.valueOf(et_horario.getText());
                Time horarioTime = Time.valueOf(horarioString);
                String precioString = String.valueOf(et_precio.getText());
                double precioDouble = Double.parseDouble(precioString);
                String responsable = String.valueOf(comboBoxEmpleados.getSelectedItem());
                //String idString = String.valueOf(et_ID_Espec.getText());
                //int idInt = Integer.parseInt(idString);
                espectaculo = new Espectaculo(et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), fechaDate, horarioTime, precioDouble, responsable);

                espectaculos.add(espectaculo);
                for (Espectaculo espectaculo1 : espectaculos) {
                    System.out.println(espectaculo1);
                }

                new Carga().modificarEspectaculo(espectaculo);
                cargaDatos();
                cargar_j_list_espectaculos();

            } else {
                //SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                String aforoString = String.valueOf(et_aforo.getText());
                int aforoInt = Integer.parseInt(aforoString);
                String fechaString = String.valueOf(et_fecha.getText());
                Date fechaDate = Date.valueOf(fechaString);
                String horarioString = String.valueOf(et_horario.getText());
                Time horarioTime = Time.valueOf(horarioString);
                String precioString = String.valueOf(et_precio.getText());
                double precioDouble = Double.parseDouble(precioString);
                //String idString = String.valueOf(et_ID_Espec.getText());
                //int idInt = Integer.parseInt(idString);
                String responsable = String.valueOf(comboBoxEmpleados.getSelectedItem());
                espectaculo = new Espectaculo(et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), fechaDate, horarioTime, precioDouble, responsable);

                espectaculos.add(espectaculo);
                for (Espectaculo espectaculo1 : espectaculos) {
                    System.out.println(espectaculo1);
                }

                new Carga().espectaculoNuevo(espectaculo);

                // Hayo el id máximo del ultima inserción de espectaculos y se lo paso a la tabla de espectaculos_Empleados para hacer el insert
                int idmax = new Carga().idMaxEspectaculos();
                // Comprobacion por pantalla del iD máximo:
                System.out.println("\nID máximo de la tabla espectáculo es: " + idmax);

                // Hago un cast del objeto seleccionado del combobox a objeto Empleado, para poder aaceder luego a su dni  y pasarselo al constructor y a la función para añadir a la table espectaculos_empleados
                Empleado ep = (Empleado) comboBoxEmpleados.getSelectedItem();
                System.out.println("Empleado: " + ep.getNombreEmple() + ", " + ep.getDniEmple());



                // Añado el empleado responsable a la tabla de espectaculos_empleados, con el dni del empleado escogido del comboBox String) y el espectaculo recien añadido (int)
                espectaculoEmpleado = new Espectaculos_Empleado(ep.getDniEmple(), idmax);
                espectaculosEmpleados.add(espectaculoEmpleado);

                // Añado al empleado relacionado con su espectáculo  a la tabla de espectáculos_empleados
                new Carga().anadirEmpleadoEspectaculo(ep.getDniEmple(), idmax);


                // Saco por pantalla una lista con todos los empleados con sus espectáculos que hay en la base de datos de la tabal espectaculos_empleados
                //espectaculoEmpleado.mostrarEspectaculosEmpleados(espectaculosEmpleados);
                for (Espectaculos_Empleado ep1 : espectaculosEmpleados) {
                    System.out.println(ep1);
                }


                cargaDatos();
                cargar_j_list_espectaculos();
                //et_ID_Espec.setVisible(true);

            }
            limpiarCampos();
            bt_GuardarEspectaculo.setVisible(false);
        });
        bt_ModEsp.addActionListener(e -> {

            et_ID_Espec.setEnabled(false);
            bt_GuardarEspectaculo.setVisible(true);

        });
        bt_eliminar.addActionListener(e -> {

            String aforoString = String.valueOf(et_aforo.getText());
            int aforoInt = Integer.parseInt(aforoString);
            String fechaString = String.valueOf(et_fecha.getText());
            Date fechaDate = Date.valueOf(fechaString);
            String horarioString = String.valueOf(et_horario.getText());
            Time horarioTime = Time.valueOf(horarioString);
            String precioString = String.valueOf(et_precio.getText());
            double precioDouble = Double.parseDouble(precioString);
            String idString = String.valueOf(et_ID_Espec.getText());
            int idInt = Integer.parseInt(idString);
            String responsable = String.valueOf(comboBoxEmpleados.getSelectedItem());
            espectaculo = new Espectaculo(idInt, et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), fechaDate, horarioTime, precioDouble, responsable);

            espectaculos.remove(espectaculo);
            for (Espectaculo espectaculo1 : espectaculos) {
                System.out.println(espectaculo1);
            }
            new Carga().eliminarEspectaculo(espectaculo);
            cargaDatos();
            cargar_j_list_espectaculos();

            limpiarCampos();
            bt_GuardarEspectaculo.setVisible(false);

        });


        //Botones de la pestaña empleados
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
            String fechaNacString = String.valueOf(et_NacEmp.getText());
            Date fechaNacDate = Date.valueOf(fechaNacString);
            String fechaContString = String.valueOf(et_fechaContrEmp.getText());
            Date fechaContDate = Date.valueOf(fechaContString);

            empleado = new Empleado(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), fechaNacDate, fechaContDate, et_NacioEmpl.getText(), et_CargoEmpl.getText());
            empleados.remove(empleado);
            for (Empleado empleado1 : empleados) {
                System.out.println(empleado1);
            }

            new Carga().eliminarEmpleado(empleado);
            cargaDatos();
            cargar_j_list_empleados();

            limpiarCampos();
            bt_GuardarEmple.setVisible(false);
        });
        bt_GuardarEmple.addActionListener(e -> {
            if (!comprobarCamposVaciosEmpleados()) {
                panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);
            } else if (!et_dniEmp.isEnabled()) {

                String fechaNacString = String.valueOf(et_NacEmp.getText());
                Date fechaNacDate = Date.valueOf(fechaNacString);
                String fechaContString = String.valueOf(et_fechaContrEmp.getText());
                Date fechaContDate = Date.valueOf(fechaContString);

                empleado = new Empleado(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), fechaNacDate, fechaContDate, et_NacioEmpl.getText(), et_CargoEmpl.getText());
                empleados.add(empleado);
                for (Empleado empleado1 : empleados) {
                    System.out.println(empleado1);
                }

                new Carga().modificarEmpleado(empleado);
                cargaDatos();
                cargar_j_list_empleados();


            } else {
                String fechaNacString = String.valueOf(et_NacEmp.getText());
                Date fechaNacDate = Date.valueOf(fechaNacString);
                String fechaContString = String.valueOf(et_fechaContrEmp.getText());
                Date fechaContDate = Date.valueOf(fechaContString);

                empleado = new Empleado(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), fechaNacDate, fechaContDate, et_NacioEmpl.getText(), et_CargoEmpl.getText());
                empleados.add(empleado);
                for (Empleado empleado1 : empleados) {
                    System.out.println(empleado1);
                }

                new Carga().empleadoNuevo(empleado);
                cargaDatos();
                cargar_j_list_empleados();

            }
            limpiarCampos();
            bt_GuardarEmple.setVisible(false);
        });

        //Botones de la pestaña espectaculosClientes
        bt_AnadirEspecCliente.addActionListener(e -> {
            // Activo los botones para que se vean
            comboBoxEspectaculos.setVisible(true);
            bt_GuardarEspecCliente.setVisible(true);

        });
        bt_BorrarEspecCliente.addActionListener(e -> {
            try {
                // Seleccionar una fila del Jtable: // Devuelve un número
                if (tabla.getSelectedRow() != -1) {

                    TableModel m = tabla.getModel();

                    // Necesito el DNI del cliente y el ID del espectáculo que selecciono del Jtable:

                    // 1º Accedo al cliente que he seleccionado del Jlist, y asi saco su DNI
                    Cliente cliente = listadoClientesEspectaculos.getSelectedValue();
                    System.out.println("Cliente elegido: " + cliente.getNombre() + " " + cliente.getDni());
                    System.out.println("ID fila seleccionada: " + tabla.getSelectedRow());

                    // 2º ID de la fila del JTable, donde selecciono el espectáculo, Suponiendo que el id lo muestras en la primera columna, me coge el valor del ID de la fila seleccionada y la columna 0 (que es el ID)
                    int idEspectaculo = (int) m.getValueAt(tabla.getSelectedRow(), 0);
                    System.out.println("ID del espectáculo: " + idEspectaculo);

                    // 3º Instancio un objeto 'Espectaculos_Cliente' con los parámetros del dni cliente y el id del espectáculo y lo quito del Array de 'Espectaculos_Cliente'

                    espectaculosCliente = new Espectaculos_Cliente(cliente.getDni(), idEspectaculo);

                    // 4º Borro de la BBDD (le paso el dniCliente e ID espectaculo) y del ArrayLIst 'Espectaculos_Cliente'
                    //new Carga().eliminarEspectaculoCliente(cliente.getDni(), idEspectaculo);
                    new Carga().eliminarEspectaculoCliente(espectaculosCliente);
                    espectaculosClientes.remove(espectaculosCliente);

                    espectaculosCliente.mostrarEspectaculosCliente(espectaculosClientes);

                    tabla.remove(tabla.getSelectedRow());
                    tabla.repaint();


                    // 5º Vuelvo a cargar y refrescar
                    CargaryRefrescarTodo();


                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un espectáculo del cliente !!");
                }
            } catch (Exception ex) {
                //ex.printStackTrace();
            }

        });
        bt_GuardarEspecCliente.addActionListener(e -> {
            // Añado un espectáculo a un cliente, y lo añado a la tabla Espectaculos_Clientes, hay que coger el cliente seleccionado del Jlist y el espectaculo de combo

            if (listadoClientesEspectaculos.getSelectedValue() != null) {

                Cliente cliente = listadoClientesEspectaculos.getSelectedValue();

                // Hago un cast del objeto seleccionado del combobox a objeto Cliente, para poder aaceder luego a su dni  y pasárselo al constructor y a la función para añadir a la table espectaculos_clientes
                Espectaculo espectaculo = (Espectaculo) comboBoxEspectaculos.getSelectedItem();
                assert espectaculo != null;
                System.out.println("Espectaculo: " + espectaculo.getNombreEspec() + ", " + espectaculo.getNo_Espect());

                espectaculosCliente = new Espectaculos_Cliente(cliente.getDni(), espectaculo.getNo_Espect());

                String nombreCliente = cliente.getNombre() + " " + cliente.getApellidos();

                if (espectaculosClientes.contains(espectaculosCliente)) {
                    JOptionPane.showMessageDialog(null, "Error !!... el cliente '" + nombreCliente.toUpperCase() + "' ya tiene el espectáculo '" + comboBoxEspectaculos.getSelectedItem() + "'  ");
                } else {
                    espectaculosClientes.add(espectaculosCliente);
                    espectaculosCliente.mostrarEspectaculosCliente(espectaculosClientes);

                    new Carga().anadirClienteEspectaculo(cliente.getDni(), espectaculo.getNo_Espect());

                    CargaryRefrescarTodo();
                }


            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un cliente !!");

            }

            comboBoxEspectaculos.setVisible(false);
            bt_GuardarEspecCliente.setVisible(false);


        });

        //Botones de la pestaña espectaculosEmpleado
        bt_AnadirEspecEmpleado.addActionListener(e -> {
            // Activo los botones para que se vean
            comboBoxEspectaculos2.setVisible(true);
            bt_GuardarEspecEmpleado.setVisible(true);
        });
        bt_BorrarEspecEmpleado.addActionListener(e -> {
            // Primero miro los espectaculos que es responsable el empleado, para que no se quede sin responsable
            // Si quieres borrarlo, antes tienes que cambiar de responsable
            TableModel m = tabla2.getModel();
            boolean correcto = true;
            Empleado empleadoBorrar = listaEmpleadosEspectaculos.getSelectedValue();
            String espectaculoEmpleadoResponsableQueQuieroBorrar = "";
            int idEspElegido = (int) m.getValueAt(tabla2.getSelectedRow(), 0);
            //System.out.println("ID del espectáculo Elegido: " + idEspElegido);
            // Compruebo que si el empleado seleccionado es tambien el responsable del espectáculo seleccionado y también coincide con el ID del espectáculo seleccionado --> NO PUEDO BORRARLO
            for (Espectaculo espectaculo1 : espectaculos) {
                if (empleadoBorrar.getNombreEmple().equalsIgnoreCase(espectaculo1.getResponsable()) && espectaculo1.getNo_Espect() == idEspElegido) {
                    //System.out.println("Empleado responsable espectáculo: " + espectaculo1.getResponsable());
                    //System.out.println("Empleado seleccionado de la lista: " + empleadoBorrar.getNombreEmple());
                    correcto = false;
                    espectaculoEmpleadoResponsableQueQuieroBorrar = espectaculo1.getNombreEspec();
                }
            }
            // System.out.println(correcto);
            if (correcto) {
                try {
                    // Seleccionar una fila del Jtable: // Devuelve un número
                    if (tabla2.getSelectedRow() != -1) {
                        //TableModel m = tabla2.getModel();
                        // Necesito el DNI del cliente y el ID del espectáculo que selecciono del Jtable:
                        // 1º Accedo al cliente que he seleccionado del Jlist, y asi saco su DNI
                        Empleado empleado = listaEmpleadosEspectaculos.getSelectedValue();
                        System.out.println("Empleado elegido: " + empleado.getNombreEmple() + " " + empleado.getDniEmple());
                        System.out.println("ID fila seleccionada: " + tabla2.getSelectedRow());
                        // 2º ID de la fila del JTable, donde selecciono el espectáculo, Suponiendo que el id lo muestras en la primera columna, me coge el valor del ID de la fila seleccionada y la columna 0 (que es el ID)
                        int idEspectaculo = (int) m.getValueAt(tabla2.getSelectedRow(), 0);
                        System.out.println("ID del espectáculo: " + idEspectaculo);
                        // 3º Instancio un objeto 'Espectaculos_Cliente' con los parámetros del dni cliente y el id del espectáculo y lo quito del Array de 'Espectaculos_Cliente'
                        espectaculoEmpleado = new Espectaculos_Empleado(empleado.getDniEmple(), idEspElegido);
                        // 4º Borro de la BBDD (le paso el dniCliente e ID espectaculo) y del ArrayLIst 'Espectaculos_Cliente'
                        new Carga().eliminarEspectaculoEmpleado(espectaculoEmpleado);
                        espectaculosEmpleados.remove(espectaculoEmpleado);
                        espectaculoEmpleado.mostrarEspectaculosEmpleados(espectaculosEmpleados);
                        // 5º Vuelvo a cargar y refrescar
                        CargaryRefrescarTodo();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecciona un espectáculo del cliente !!");
                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                }
            } else {
                System.out.println(espectaculoEmpleadoResponsableQueQuieroBorrar);
                JOptionPane.showMessageDialog(null, "No puedes borrar '" + espectaculoEmpleadoResponsableQueQuieroBorrar.toUpperCase() + "' porque " + empleadoBorrar.getNombreEmple().toUpperCase() + " es el  responsable, tienes que cambiar ANTES de responsable.");
            }
        });
        bt_GuardarEspecEmpleado.addActionListener(e -> {
            // Añado un espectáculo a un cliente, y lo añado a la tabla Espectaculos_Clientes, hay que coger el cliente seleccionado del Jlist y el espectaculo de combo

            if (listaEmpleadosEspectaculos.getSelectedValue() != null) {

                Empleado empleado = listaEmpleadosEspectaculos.getSelectedValue();

                // Hago un cast del objeto seleccionado del combobox a objeto Espectáculo, para poder aaceder luego a su dni  y pasárselo al constructor y a la función para añadir a la table espectaculos_empleados
                Espectaculo espectaculo = (Espectaculo) comboBoxEspectaculos2.getSelectedItem();
                assert espectaculo != null;
                System.out.println("Espectáculo: " + espectaculo.getNombreEspec() + ", " + espectaculo.getNo_Espect());

                Espectaculos_Empleado especEmple = new Espectaculos_Empleado(empleado.getDniEmple(), espectaculo.getNo_Espect());

                String nombreEmpleado = empleado.getNombreEmple() + " " + empleado.getApeEmple();

                if (espectaculosEmpleados.contains(especEmple)) {
                    JOptionPane.showMessageDialog(null, "Error !!... el empleado '" + nombreEmpleado.toUpperCase() + "' ya tiene el espectáculo '" + comboBoxEspectaculos2.getSelectedItem() + "'  ");
                } else {
                    espectaculosEmpleados.add(especEmple);
                    especEmple.mostrarEspectaculosEmpleados(espectaculosEmpleados);

                    new Carga().anadirEmpleadoEspectaculo(empleado.getDniEmple(), espectaculo.getNo_Espect());

                    CargaryRefrescarTodo();
                }


            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un cliente !!");

            }

            comboBoxEspectaculos2.setVisible(false);
            bt_GuardarEspecEmpleado.setVisible(false);
        });

        // Información de la base de datos:
        infoButton.addActionListener(e -> {

            String infoBaseDatos = new Carga().infoMySql(textAreaInfoMySql);

            textAreaInfoMySql.setText(infoBaseDatos);

        });

    }

    public void CargaryRefrescarTodo(){
        cargaDatos();
        cargar_tablas_relaciones();
        cargar_j_list_clientes();
        cargar_j_list_empleados();
        cargar_j_list_espectaculos();
        cargar_combobox_responsables();
        cargar_combobox_espectaculosClientes();
        cargar_j_list_jtable_espectaculosClientes();
        cargar_j_list_jtable_espectaculosEmpleados();

    }

    public void cargaDatos() {

        empleados = new Carga().listaEmpleados();

        espectaculos = new Carga().listaEspectaculos();

        clientes = new Carga().listaClientes();

        espectaculosClientes = new Carga().listaClientesEspectaculo();

        espectaculosEmpleados = new Carga().listaEmpleadosEspectaculo();

    }

    private void cargar_tablas_relaciones() {
        System.out.println("\n**************** CARGA DE DATOS DE LOS CLIENTES POR ESPECTACULO *****************\n");

        for (int i = 0; i < espectaculos.size(); i++) {
            System.out.println("Posicion(i): " + i);
            System.out.println("Espectaculo nº: " + espectaculos.get(i).getNo_Espect());
            for (int j = 0; j < espectaculosClientes.size(); j++) {

                for (int k = 0; k < clientes.size(); k++) {
                    if (espectaculos.get(i).getNo_Espect() == espectaculosClientes.get(j).getEspectaculo() && clientes.get(k).getDni().equalsIgnoreCase(espectaculosClientes.get(j).getCliente())) {
                        Cliente c = new Cliente(clientes.get(k).getDni(), clientes.get(k).getNombre(), clientes.get(k).getApellidos(), clientes.get(k).getEdad());
                        Espectaculo e = new Espectaculo(espectaculos.get(i).getNo_Espect(), espectaculos.get(i).getNombreEspec(), espectaculos.get(i).getAforo(), espectaculos.get(i).getDescripcion(), espectaculos.get(i).getLugar(), espectaculos.get(i).getFecha_Espec(), espectaculos.get(i).getHorario_espec(), espectaculos.get(i).getPrecio(), espectaculos.get(i).getResponsable());

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
                        Espectaculo e = new Espectaculo(espectaculos.get(i).getNo_Espect(), espectaculos.get(i).getNombreEspec(), espectaculos.get(i).getAforo(), espectaculos.get(i).getDescripcion(), espectaculos.get(i).getLugar(), espectaculos.get(i).getFecha_Espec(), espectaculos.get(i).getHorario_espec(), espectaculos.get(i).getPrecio(), espectaculos.get(i).getResponsable());

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
    }

    public void cargar_j_list_clientes() {
        DefaultListModel<Cliente> model = new DefaultListModel<>();
        for (Cliente cliente : clientes) {
            model.addElement(cliente);
        }
        list_clientes.setModel(model);
    }

    public void cargar_j_list_empleados() {
        DefaultListModel<Empleado> model = new DefaultListModel<>();
        for (Empleado empleado : empleados) {
            model.addElement(empleado);
        }
        listaEmpleados.setModel(model);
    }

    public void cargar_j_list_espectaculos() {
        DefaultListModel<Espectaculo> model = new DefaultListModel<>();
        for (Espectaculo espectaculo : espectaculos) {
            model.addElement(espectaculo);
        }
        listaEspectaculos.setModel(model);
    }

    private void cargar_combobox_responsables() {
        // Carga de los empleados en un model de combobox para seleccionar el responsable, luefo le paso el modelo al objeto comboBox de la ventana
        DefaultComboBoxModel<String> empleModel = new DefaultComboBoxModel<>();

        for (Empleado empleado : empleados) {
            empleModel.addElement(empleado.getNombreEmple());

        }
        comboBoxEmpleados.setModel(empleModel);
    }

    private void cargar_combobox_espectaculosClientes(){
        // Carga de los clientes en un model de combobox para seleccionar el responsable, luefo le paso el modelo al objeto comboBox de la ventana
        DefaultComboBoxModel<Espectaculo> espectaculoModel = new DefaultComboBoxModel<>();

        for (Espectaculo espectaculo : espectaculos) {
            espectaculoModel.addElement(espectaculo);

        }

        comboBoxEspectaculos.setModel(espectaculoModel);
        comboBoxEspectaculos2.setModel((espectaculoModel));
    }

    private void cargar_j_list_jtable_espectaculosClientes() {
        // Jlist clientes Jtable espectaculos
        DefaultListModel<Cliente> modelClienteEspectaculo = new DefaultListModel<>();
        for (Cliente c : clientes) {
            modelClienteEspectaculo.addElement(c);
        }

        listadoClientesEspectaculos.setModel(modelClienteEspectaculo);

        tabla = new JTable();
        tabla.setModel(new ListaClientesEspectaculosModel(new Cliente()));
        resultadoClientesEspectaculos.setViewportView(tabla);

        listadoClientesEspectaculos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tabla.setModel(new ListaClientesEspectaculosModel(listadoClientesEspectaculos.getSelectedValue()));
                System.out.println(listadoClientesEspectaculos.getSelectedValue().getEspectaculos());
            }
        });
    }

    private void cargar_j_list_jtable_espectaculosEmpleados() {
        // Jlist empleados Jtable espectaculos
        DefaultListModel<Empleado> modelEmpleadoEspectaculo = new DefaultListModel<>();
        for (Empleado e : empleados) {
            modelEmpleadoEspectaculo.addElement(e);
        }

        listaEmpleadosEspectaculos.setModel(modelEmpleadoEspectaculo);

        tabla2 = new JTable();
        tabla2.setModel(new ListaEmpleadosEspectaculosModel(new Empleado()));
        resultadoEmpleadosEspectaculos.setViewportView(tabla2);

        listaEmpleadosEspectaculos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                tabla2.setModel(new ListaEmpleadosEspectaculosModel(listaEmpleadosEspectaculos.getSelectedValue()));
                System.out.println(listaEmpleadosEspectaculos.getSelectedValue().getEspectaculos());

            }
        });
    }



    /*

    public void cargar_j_list_jtable_espectaculosEmpleados() {



        tabla2 = new JTable();
        tabla2.setModel(new ListaEmpleadosEspectaculosModel(new Empleado()));
        resultadoEmpleadosEspectaculos.setViewportView(tabla2);

        listaEmpleadosEspectaculos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                tabla2.setModel(new ListaEmpleadosEspectaculosModel(listaEmpleadosEspectaculos.getSelectedValue()));


            }
        });
    }

    public void actualizarJTable() {
        ArrayList<Espectaculo> espectaculos;
        espectaculos = new Carga().listaEspectaculos();

        JtableModel modelo = new JtableModel(espectaculos);
        //tabla.setModel(modelo);
    }
*/

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

    public void actualizarClientes() {
        Cliente cliente = list_clientes.getSelectedValue();

        if (cliente != null) {
            campo_dni.setText(cliente.getDni());
            campo_nombre.setText(cliente.getNombre());
            campo_apellido.setText(cliente.getApellidos());
            campo_edad.setText(String.valueOf(cliente.getEdad()));
        }
    }

    public void actualizarEmpleados() {
        Empleado empleado = listaEmpleados.getSelectedValue();

        if (empleado != null) {
            et_dniEmp.setText(empleado.getDniEmple());
            et_nombre.setText(empleado.getNombreEmple());
            et_apeEmple.setText(empleado.getApeEmple());
            et_NacEmp.setText(String.valueOf(empleado.getFechaNac()));
            et_fechaContrEmp.setText(String.valueOf(empleado.getFechaContr()));
            et_NacioEmpl.setText(String.valueOf(empleado.getNacionalidad()));
            et_CargoEmpl.setText(String.valueOf(empleado.getCargo()));
        }
    }

    public void actualizarEspectaculos() {
        Espectaculo espectaculo = listaEspectaculos.getSelectedValue();

        if (espectaculo != null) {
            et_ID_Espec.setText(String.valueOf(espectaculo.getNo_Espect()));
            et_Espectaculo.setText(espectaculo.getNombreEspec());
            et_aforo.setText(String.valueOf(espectaculo.getAforo()));
            et_Descripcion.setText(espectaculo.getDescripcion());
            et_lugar.setText(espectaculo.getLugar());
            et_fecha.setText(String.valueOf(espectaculo.getFecha_Espec()));
            et_horario.setText(String.valueOf(espectaculo.getHorario_espec()));
            et_precio.setText(String.valueOf(espectaculo.getPrecio()));
            comboBoxEmpleados.setSelectedItem(espectaculo.getResponsable());
        }
    }

    public JPanel getVPanelPrincipal() {
        return VPanelPrincipal;
    }

    private boolean comprobarCamposVacios() {

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

    public void panelMensajePersonalizado(String titulo, String mensaje, int tipo) {
        JButton okButton = new JButton("OK");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane(mensaje, tipo, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

/*    private void createUIComponents() {
        // TODO: place custom component creation code here
    }*/
}
