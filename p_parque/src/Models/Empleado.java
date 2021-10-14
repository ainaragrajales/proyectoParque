package Models;

import java.util.ArrayList;

public class Empleado {

    // Atributos

    private String dniEmple;
    private String nombreEmple;
    private String ApeEmple;
    private String fechaNac;
    private String fechaContr;
    private String Nacionalidad;
    private String cargo;

    // Relacion con espectaculo --> 1 empleado tiene muchos espectáculos
    // IMPORTANTE al declarar el ArrayList, poner al final '=new ArrayList<>()' porque sino nos genera un 'NullPointerexception'

    private ArrayList<Espectaculo> espectaculos= new ArrayList<>();


    //Constructores

    public Empleado(String dniEmple, String nombreEmple, String apeEmple, String fechaNac, String fechaContr, String nacionalidad, String cargo) {
        this.dniEmple = dniEmple;
        this.nombreEmple = nombreEmple;
        ApeEmple = apeEmple;
        this.fechaNac = fechaNac;
        this.fechaContr = fechaContr;
        Nacionalidad = nacionalidad;
        this.cargo = cargo;
    }

    // Constructor vacío

    public Empleado() {
    }


    // Getters y Setters

    public String getDniEmple() {
        return dniEmple;
    }

    public void setDniEmple(String dniEmple) {
        this.dniEmple = dniEmple;
    }

    public String getNombreEmple() {
        return nombreEmple;
    }

    public void setNombreEmple(String nombreEmple) {
        this.nombreEmple = nombreEmple;
    }

    public String getApeEmple() {
        return ApeEmple;
    }

    public void setApeEmple(String apeEmple) {
        ApeEmple = apeEmple;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFechaContr() {
        return fechaContr;
    }

    public void setFechaContr(String fechaContr) {
        this.fechaContr = fechaContr;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public ArrayList<Espectaculo> getEspectaculos() {
        return espectaculos;
    }

    public void setEspectaculos(ArrayList<Espectaculo> espectaculos) {
        this.espectaculos = espectaculos;
    }

// To String()


    @Override
    public String toString() {
        return nombreEmple;
    }


}
