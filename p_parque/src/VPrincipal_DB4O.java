import javax.swing.*;

public class VPrincipal_DB4O {
    private JPanel VPanelPrincipal;
    private JTabbedPane tabbedPane1;
    private JPanel PestanaEspectaculos;
    private JList listaEspectaculos;
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
    private JList list_clientes;
    private JButton bt_nuevoCli;
    private JButton bt_modificarCli;
    private JButton bt_eliminarCli;
    private JPanel PestanaEmpleados;
    private JList listaEmpleados;
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
    private JList listadoClientesEspectaculos;
    private JScrollPane resultadoClientesEspectaculos;
    private JList listaEmpleadosEspectaculos;
    private JScrollPane resultadoEmpleadosEspectaculos;
    private JLabel lb_infoMySql;
    private JTextArea textAreaInfoMySql;
    private JButton infoButton;
    private JLabel lb_responsable;
    private JComboBox comboBoxEmpleados;
    private JButton bt_salir;
    private JButton bt_AnadirEspecCliente;
    private JButton bt_BorrarEspecCliente;
    private JComboBox comboBoxEspectaculos;
    private JButton bt_GuardarEspecCliente;

    public VPrincipal_DB4O(){

    }

    public JPanel getVPanelPrincipal() {
        return VPanelPrincipal;
    }
}
