package Models;

public class Espectaculos_Empleado {

    // Atributos

    private int idEspecEmp;
    private String empleado;
    private int espectaculo;

    // Constructor

    public Espectaculos_Empleado(int idEspecEmp, String empleado, int espectaculo) {
        this.idEspecEmp = idEspecEmp;
        this.empleado = empleado;
        this.espectaculo = espectaculo;
    }

    public Espectaculos_Empleado() {
    }

    // Getters y Setters


    public int getIdEspecEmp() {
        return idEspecEmp;
    }

    public void setIdEspecEmp(int idEspecEmp) {
        this.idEspecEmp = idEspecEmp;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(int espectaculo) {
        this.espectaculo = espectaculo;
    }


    // To String()


    @Override
    public String toString() {
        return "Espectaculos_Empleado{" +
                "idEspecEmp=" + idEspecEmp +
                ", empleado='" + empleado + '\'' +
                ", espectaculo=" + espectaculo +
                '}';
    }
}
