package Models;


import java.util.ArrayList;

public class EmpleadoSQLite {

    //Atributos
    private String dniEmple;
    private String nombreEmple;
    private String ApeEmple;
    private String fechaNac;
    private String fechaContr;
    private String Nacionalidad;
    private String cargo;

    private ArrayList<EspectaculoSQLite> espectaculosSQLite = new ArrayList<>();

    public EmpleadoSQLite(String dniEmple, String nombreEmple, String apeEmple, String fechaNac, String fechaContr, String nacionalidad, String cargo) {
        this.dniEmple = dniEmple;
        this.nombreEmple = nombreEmple;
        this.ApeEmple = apeEmple;
        this.fechaNac = fechaNac;
        this.fechaContr = fechaContr;
        this.Nacionalidad = nacionalidad;
        this.cargo = cargo;

    }

    public EmpleadoSQLite() {
    }

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

    public ArrayList<EspectaculoSQLite> getEspectaculosSQLite() {
        return espectaculosSQLite;
    }

    public void setEspectaculosSQLite(ArrayList<EspectaculoSQLite> espectaculosSQLite) {
        this.espectaculosSQLite = espectaculosSQLite;
    }

    @Override
    public String toString() {
        return nombreEmple;
    }
}
