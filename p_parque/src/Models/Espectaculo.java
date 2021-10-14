package Models;

import java.util.ArrayList;

public class Espectaculo {

    // Atributos

    private int no_Espect;
    private String nombreEspec;
    private int aforo;
    private String descripcion;
    private String lugar;
    private String fecha_Espec; //en sql es Date
    private String horario_espec; //en sql es Time
    private int precio;

    //lo mejor sería hacer un campo que sea fecha_hora y hacerlo con el DateFormatter

    // Relaciones con las otras clases 1 espectaculo tiene muchos clientes y tiene muchos empleados
    // IMPORTANTE al declarar el ArrayList, poner al final '=new ArrayList<>()' porque sino nos genera un 'NullPointerexception'

    private ArrayList<Cliente> clientes =new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

    // Constructor

    public Espectaculo(int no_Espect, String nombreEspec, int aforo, String descripcion, String lugar, String fecha_Espec, String horario_espec, int precio) {
        this.no_Espect = no_Espect;
        this.nombreEspec = nombreEspec;
        this.aforo = aforo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha_Espec = fecha_Espec;
        this.horario_espec = horario_espec;
        this.precio=precio;
    }

    // Constructor vacío
    public Espectaculo() {
    }

    public Espectaculo(String nombreEspec) {
        this.nombreEspec = nombreEspec;
    }

    // Getters y Setters

    public int getNo_Espect() {
        return no_Espect;
    }

    public void setNo_Espect(int no_Espect) {
        this.no_Espect = no_Espect;
    }

    public String getNombreEspec() {
        return nombreEspec;
    }

    public void setNombreEspec(String nombreEspec) {
        this.nombreEspec = nombreEspec;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha_Espec() {
        return fecha_Espec;
    }

    public void setFecha_Espec(String fecha_Espec) {
        this.fecha_Espec = fecha_Espec;
    }

    public String getHorario_espec() {
        return horario_espec;
    }

    public void setHorario_espec(String horario_espec) {
        this.horario_espec = horario_espec;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    //toString() -> ya se pondrá bonito... solo pongo el nombre de momento para no ocupar tanto espacio
    @Override
    public String toString() {
        return nombreEspec;
    }

}
