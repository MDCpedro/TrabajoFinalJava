package proyectojavabd;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
//Basicamente se hacen los mismos pasos que en las otras clases.
public class ListaRecursos extends JFrame {
    public ListaRecursos() {
        String lista_recursos = "SELECT * FROM recursos";

        this.setTitle("Lista de Recursos");
        this.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(500, 500));
        modelo.setColumnIdentifiers(new Object[] { "ID", "ID_Proyecto", "Nombre", "Tipo", "FechaAsignacion", "Precio"});

        cargarDatosRecursos(modelo, lista_recursos);

        this.setVisible(true);
        this.add(panel);
        this.add(scroll);
    }
//Metodo que carga los datos de los recursos en la tabla.
    public void cargarDatosRecursos(DefaultTableModel modelo, String filtro) {
        try {
            String url = "jdbc:mysql://localhost:3306/bdproyectofinal";

            Connection conexion = DriverManager.getConnection(url, "root", "");
            PreparedStatement select = conexion.prepareStatement(filtro);
            java.sql.ResultSet rs = select.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[] { rs.getInt("ID"), rs.getInt("ID_Proyecto"), 
                rs.getString("Nombre"), rs.getString("Tipo"), rs.getString("FechaAsignacion"), rs.getString("Precio")});
            }
            rs.close();
            select.close();
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos (Puede que el servidor no este encendido).");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}
