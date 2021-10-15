import Models.*;
import MySQL.Carga;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public VPrincipal_MySQL(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {

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


        bt_guardarCli.setVisible(false);
        bt_GuardarEspectaculo.setVisible(false);

        cargaDatos();
        //cargar_j_list();
        //actualizarJTable();


        cargar_j_list_clientes();
        cargar_j_list_empleados();
        cargar_j_list_espectaculos();


        list_clientes.addListSelectionListener(e -> actualizarClientes());

        listaEspectaculos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actualizarEspectaculos();
            }
        });

        listaEmpleados.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actualizarEmpleados();
            }
        });

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


        bt_AnadirEsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                et_ID_Espec.setEnabled(true);
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

                } else {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                    String aforoString = String.valueOf(et_aforo.getText());
                    int aforoInt = Integer.parseInt(aforoString);
                    String fechaString = String.valueOf(et_fecha.getText());
                    Date fechaDate = null;
                    try {
                        fechaDate = formato.parse(fechaString);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    String horarioString = String.valueOf(et_horario.getText());
                    //Time horarioTime = Time.valueOf(horarioString);
                    String precioString = String.valueOf(et_precio.getText());
                    double precioDouble = Double.parseDouble(precioString);
                    String idString = String.valueOf(et_ID_Espec.getText());
                    int idInt = Integer.parseInt(idString);
                    espectaculo = new Espectaculo(idInt, et_Espectaculo.getText(), aforoInt, et_Descripcion.getText(), et_lugar.getText(), null, null, precioDouble);

                    espectaculos.add(espectaculo);
                    for (Espectaculo espectaculo1: espectaculos){
                        System.out.println(espectaculo1);
                    }

                    new Carga().espectaculoNuevo(espectaculo);
                    cargaDatos();
                    cargar_j_list_espectaculos();

                }
                limpiarCampos();
                bt_GuardarEspectaculo.setVisible(false);
            }
        });
    }

    public void cargaDatos() {

        espectaculos = new Carga().listaEspectaculos();

        clientes = new Carga().listaClientes();

        espectaculosClientes = new Carga().listaClientesEspectaculo();

        espectaculosEmpleados = new Carga().listaEmpleadosEspectaculo();

    }

    public void CargaCLientes() {
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

        /*DefaultListModel<Cliente> model = new DefaultListModel<>();
        for (Espectaculo espectaculo : espectaculos) {
            model.addElement(espectaculo);
        }
        listadoClientesEspectaculos.setModel(model);*/
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

    public void actualizarJTable() {
        ArrayList<Espectaculo> espectaculos;
        espectaculos = new Carga().listaEspectaculos();

        JtableModel modelo = new JtableModel(espectaculos);
        //tabla.setModel(modelo);
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
