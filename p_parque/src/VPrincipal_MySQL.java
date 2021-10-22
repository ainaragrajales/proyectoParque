import Models.*;
import MySQL.Carga;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JButton BotonAnadirEspec;
    private JButton BotonBorrarEspec;
    private JButton BotonModificarEspec;
    private JScrollPane resultadoClientesEspectaculos;

    private JScrollPane resultadoEmpleadosEspectaculos;
    private JLabel lb_responsable;
    private JComboBox comboBoxEmpleados;
    private JLabel lb_infoMySql;
    private JTextArea textAreaInfoMySql;
    private JButton infoButton;
    private JTextField textField1;

    private JTable tabla;
    private JTable tabla2;

    private ArrayList<Espectaculo> espectaculos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Espectaculos_Cliente> espectaculosClientes = new ArrayList<>();
    private ArrayList<Espectaculos_Empleado> espectaculosEmpleados = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

    private Cliente cliente;
    private Empleado empleado;
    private Espectaculo espectaculo;
    private Espectaculos_Empleado espectaculoEmpleado;

    public VPrincipal_MySQL(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados, ArrayList<Espectaculos_Empleado> espectaculosEmpleados) {

        // Carga de los empleados en un model de combobox para seleccionar el responsable, luefo le paso el modelo al objeto comboBox de la ventana
        DefaultComboBoxModel<Empleado> empleModel = new DefaultComboBoxModel<>();

        for (Empleado empleado : empleados) {
            empleModel.addElement(empleado);

        }
        comboBoxEmpleados.setModel(empleModel);


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


        //Poner los botones de guardar en oculto
        bt_guardarCli.setVisible(false);
        bt_GuardarEspectaculo.setVisible(false);
        bt_GuardarEmple.setVisible(false);

        cargaDatos();
        //cargar_j_list();
        //actualizarJTable();


        cargar_j_list_clientes();
        cargar_j_list_empleados();
        cargar_j_list_espectaculos();


        list_clientes.addListSelectionListener(e -> actualizarClientes());

        listaEspectaculos.addListSelectionListener(e -> actualizarEspectaculos());

        listaEmpleados.addListSelectionListener(e -> actualizarEmpleados());

        //Botones de la pestaña Clientes
        bt_nuevoCli.addActionListener(e -> {

            campo_dni.setEnabled(true);
            limpiarCampos();
            bt_guardarCli.setVisible(true);
        });

        bt_guardarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        bt_modificarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                campo_dni.setEnabled(false);
                bt_guardarCli.setVisible(true);


            }
        });
        bt_eliminarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });



        //Botones de la pestaña Espectaculos
        bt_AnadirEsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                et_ID_Espec.setEnabled(true);
                //et_ID_Espec.setVisible(false);
                limpiarCampos();
                bt_GuardarEspectaculo.setVisible(true);

            }
        });

        bt_GuardarEspectaculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!comprobarCamposVaciosEspectaculos()){

                    panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);

                } else if (!et_ID_Espec.isEnabled()){

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
                    for (Espectaculo espectaculo1: espectaculos){
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
                    for (Espectaculo espectaculo1: espectaculos){
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
                    ;


                    // Añado el empleado responsable a la tabla de espectaculos_empleados, con el dni del empleado escogido del comboBox String) y el espectaculo recien añadido (int)
                    espectaculoEmpleado = new Espectaculos_Empleado(ep.getDniEmple(), idmax);
                    espectaculosEmpleados.add(espectaculoEmpleado);

                    // Añado al empleado relacionado con su espectáculo  a la tabla de espectáculos_empleados
                    new Carga().anadirEmpleadoEspectaculo(ep.getDniEmple(), idmax);


                    // Saco por pantalla una lista con todos los empleados con sus espectáculos que hay en la base de datos de la tabal espectaculos_empleados
                    //espectaculoEmpleado.mostrarEspectaculosEmpleados(espectaculosEmpleados);
                    for (Espectaculos_Empleado ep1: espectaculosEmpleados){
                        System.out.println(ep1);
                    }


                    cargaDatos();
                    cargar_j_list_espectaculos();
                    //et_ID_Espec.setVisible(true);

                }
                limpiarCampos();
                bt_GuardarEspectaculo.setVisible(false);
            }
        });

        bt_ModEsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                et_ID_Espec.setEnabled(false);
                bt_GuardarEspectaculo.setVisible(true);

            }
        });


        bt_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                for (Espectaculo espectaculo1: espectaculos){
                    System.out.println(espectaculo1);
                }
                new Carga().eliminarEspectaculo(espectaculo);
                cargaDatos();
                cargar_j_list_espectaculos();

                limpiarCampos();
                bt_GuardarEspectaculo.setVisible(false);

            }
        });


        bt_EmpleAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                et_dniEmp.setEnabled(true);
                limpiarCampos();
                bt_GuardarEmple.setVisible(true);
            }
        });
        bt_ModEmpl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                et_dniEmp.setEnabled(false);
                bt_GuardarEmple.setVisible(true);
            }
        });
        bt_eliminarEmple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaNacString = String.valueOf(et_NacEmp.getText());
                Date fechaNacDate = Date.valueOf(fechaNacString);
                String fechaContString = String.valueOf(et_fechaContrEmp.getText());
                Date fechaContDate = Date.valueOf(fechaContString);

                empleado = new Empleado(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), fechaNacDate, fechaContDate, et_NacioEmpl.getText(), et_CargoEmpl.getText());
                empleados.remove(empleado);
                for (Empleado empleado1: empleados){
                    System.out.println(empleado1);
                }

                new Carga().eliminarEmpleado(empleado);
                cargaDatos();
                cargar_j_list_empleados();

                limpiarCampos();
                bt_GuardarEmple.setVisible(false);
            }
        });
        bt_GuardarEmple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!comprobarCamposVaciosEmpleados()){
                    panelMensajePersonalizado("Campos Vacíos", "No puede haber campos vacíos. Comprueba todos los campos", 0);
                } else if (!et_dniEmp.isEnabled()){

                    String fechaNacString = String.valueOf(et_NacEmp.getText());
                    Date fechaNacDate = Date.valueOf(fechaNacString);
                    String fechaContString = String.valueOf(et_fechaContrEmp.getText());
                    Date fechaContDate = Date.valueOf(fechaContString);

                    empleado = new Empleado(et_dniEmp.getText(), et_emple.getText(), et_apeEmple.getText(), fechaNacDate, fechaContDate, et_NacioEmpl.getText(), et_CargoEmpl.getText());
                    empleados.add(empleado);
                    for (Empleado empleado1: empleados){
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
                    for (Empleado empleado1: empleados){
                        System.out.println(empleado1);
                    }

                    new Carga().empleadoNuevo(empleado);
                    cargaDatos();
                    cargar_j_list_empleados();

                }
                limpiarCampos();
                bt_GuardarEmple.setVisible(false);
            }
        });

        // Información de la base de datos:

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String infoBaseDatos = new Carga().infoMySql(textAreaInfoMySql);

                textAreaInfoMySql.setText(infoBaseDatos);

            }
        });


    }


    public void cargaDatos() {

        empleados = new Carga().listaEmpleados();

        espectaculos = new Carga().listaEspectaculos();

        clientes = new Carga().listaClientes();

        espectaculosClientes = new Carga().listaClientesEspectaculo();

        espectaculosEmpleados = new Carga().listaEmpleadosEspectaculo();

    }

    /*public void CargaCLientes() {
        clientes = new Carga().listaClientes();
    }

    public void CargaEmples() {
        empleados = new Carga().listaEmpleados();
    }

    public void CargaESpectaculos() {
        espectaculos = new Carga().listaEspectaculos();
    }

    public void CargaCli_Espec() {
        espectaculosClientes = new Carga().listaClientesEspectaculo();
    }

    public void CargaEmp_Espec() {
        espectaculosEmpleados = new Carga().listaEmpleadosEspectaculo();
    }


    public void cargar_j_list() {
        DefaultListModel<Cliente> model = new DefaultListModel<>();
        for (Cliente c : clientes) {
            model.addElement(c);
        }

        listadoClientesEspectaculos.setModel(model);

        //DefaultListModel<Cliente> model = new DefaultListModel<>();
        //for (Espectaculo espectaculo : espectaculos) {
        //    model.addElement(espectaculo);
        //}
        //listadoClientesEspectaculos.setModel(model);
    }*/

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
