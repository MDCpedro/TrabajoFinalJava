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

public class ListaProyectos extends JFrame {
    public ListaProyectos() {
        String lista_proyectos = "SELECT * FROM proyectos";

        this.setTitle("Lista de Proyectos");
        this.setSize(700, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(500, 500));
        modelo.setColumnIdentifiers(new Object[] { "ID", "Titulo", "Descripcion", "FechaInicio", "FechaFinalizacion", "Presupuesto", "Activo", "Director"});

        cargarDatosProyectos(modelo, lista_proyectos);

        this.setVisible(true);
        this.add(panel);
        this.add(scroll);
    }

    public void cargarDatosProyectos(DefaultTableModel modelo, String filtro) {
        try {
            String url = "jdbc:mysql://localhost:3306/bdproyectofinal";

            Connection conexion = DriverManager.getConnection(url, "root", "");
            PreparedStatement select = conexion.prepareStatement(filtro);
            java.sql.ResultSet rs = select.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[] { rs.getInt("ID"), rs.getString("Titulo"), rs.getString("Descripcion"), rs.getString("FechaInicio"),
                rs.getString("FechaFinalizacion"), rs.getString("Presupuesto"), rs.getString("Activo"), rs.getString("Director")});
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
