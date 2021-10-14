package Models;

public class Espectaculos_Cliente {

    // Atributos

    private int idEspecCli;
    private String cliente;
    private int espectaculo;

    // Constructores

    public Espectaculos_Cliente() {
    }

    public Espectaculos_Cliente(int idEspecCli, String cliente, int espectaculo) {
        this.idEspecCli = idEspecCli;
        this.cliente = cliente;
        this.espectaculo = espectaculo;
    }

    // Getters y Setters


    public int getIdEspecCli() {
        return idEspecCli;
    }

    public void setIdEspecCli(int idEspecCli) {
        this.idEspecCli = idEspecCli;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(int espectaculo) {
        this.espectaculo = espectaculo;
    }

    // To String


    @Override
    public String toString() {
        return "Espectaculos_Cliente{" +
                "idEspecCli=" + idEspecCli +
                ", cliente='" + cliente + '\'' +
                ", espectaculo=" + espectaculo +
                '}';
    }
}
