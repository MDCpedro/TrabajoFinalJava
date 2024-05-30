package proyectojavabd;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ListaDirectores extends JFrame {
    public ListaDirectores() {
        //Creamos la consulta SQL que usaremos en nuestro metodo.
        String lista_directores_default = "SELECT * FROM Directores";
        //Creamos la ventana de la lista de directores.
        this.setTitle("Lista de Directores");
        this.setSize(250, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(500, 500));
        modelo.setColumnIdentifiers(new String[] { "ID", "Nombre", "Apellidos"});

        //Cargamos los datos de los directores en la tabla mediante este metodo.
        cargarDatosDirectores(modelo, lista_directores_default);

        this.setVisible(true);
        this.add(panel);
        this.add(scroll);
    }
    //Metodo que carga los datos de los directores en la tabla.
    public void cargarDatosDirectores(DefaultTableModel modelo, String filtro) {
        try {
            //Conexion a la base de datos.
            String url = "jdbc:mysql://localhost:3306/bdproyectofinal";
            Connection conexion = DriverManager.getConnection(url, "root", "");
            PreparedStatement select = conexion.prepareStatement(filtro);
            java.sql.ResultSet rs = select.executeQuery();
            //Bucle para añadir los datos de los artistas a la tabla.
            while (rs.next()) {
                modelo.addRow(new Object[] { rs.getInt("ID"), rs.getString("Nombre"), rs.getString("Apellidos")});
            }
            rs.close();
            select.close();
            conexion.close();
        //Control de errores.
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos (Puede que el servidor no este encendido).");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}
