package Models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class JtableModel extends AbstractTableModel {

    private String[] columnas = {"ID", "Nombre", "Aforo", "Descripción", "Lugar", "Fecha", "Hora"};
    private ArrayList<Espectaculo> espectaculos = null;

    public JtableModel(ArrayList<Espectaculo> espectaculos) {
        this.espectaculos = espectaculos;
    }

    @Override
    public int getRowCount() {
        return espectaculos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = format.parse("2021-11-31");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        int id;
        String nombre;
        int aforo;
        String desc;
        String lugar;
        String fecha;
        String hora;

        /*
        try {
            Espectaculo e = espectaculos.get(rowIndex);
            id = e.getNo_Espect();
            nombre = e.getNombreEspec();
            aforo = e.getAforo();
            desc = e.getDescripcion();
            lugar = e.getLugar();
            fecha = e.getFecha_Espec();
            hora = e.getHorario_espec();

            return switch (columnIndex) {


                case 0 -> String.valueOf(id);
                case 1 -> nombre;
                case 2 -> String.valueOf(aforo);
                case 3 -> desc;
                case 4 -> lugar;
                case 5 -> fecha;
                case 6 -> hora;
                default -> "";


            };



        } catch (NullPointerException ignored) {
            return "";
        }

         */
        return "";
    }
}
