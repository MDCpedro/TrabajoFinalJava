package proyectojavabd;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import static java.lang.String.valueOf;
//Clase de listaArtistas.
public class ListaArtistas extends JFrame {
//Ventana de lista de artistas.
    public ListaArtistas() {
        //Creamos las consultas SQL que usaremos en nuestro metodo.
        String lista_artistas_default = "SELECT * FROM Artistas";
        String lista_artistas_anyos_exp = "SELECT * FROM Artistas ORDER BY AnyosExp DESC";
        //Creamos la ventana de la lista de artistas.
        this.setTitle("Lista de Artistas");
        this.setSize(500, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(500, 500));
        modelo.setColumnIdentifiers(new String[] { "ID", "Nombre", "Apellidos", "Especialidad", "AñosExp", "ID_Proyecto"});

        JButton boton_anyadir_artistas = new JButton("Añadir Artista");
        JButton refrescar_tabla = new JButton("Refrescar Tabla");
        JButton ordenar_anyos_exp = new JButton("Ordenar por Años de Experiencia");
        //Cargamos los datos de los artistas en la tabla mediante este metodo.
        cargarDatosArtistas(modelo, lista_artistas_default);
        //Añadimos listeners a los botones.
        //El boton de añadir artistas nos abrira la ventana de añadir artistas.
        boton_anyadir_artistas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent operar) {
                new AddArtista();
            }
        });
        //Este boton usa el metodo con la consulta de ordenar por años de experiencia.
        ordenar_anyos_exp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent operar) {
                modelo.setRowCount(0);
                cargarDatosArtistas(modelo, lista_artistas_anyos_exp);
            }
        });
        //Este boton refresca la tabla, volviendo a cargar los datos de los artistas.
        refrescar_tabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent operar) {
                modelo.setRowCount(0);
                cargarDatosArtistas(modelo, lista_artistas_default);
                
            }
        });

        this.setVisible(true);
        this.add(panel);
        panel.add(scroll);
        panel.add(refrescar_tabla);
        panel.add(ordenar_anyos_exp);
        panel.add(boton_anyadir_artistas);
        this.pack();
    }
    
    //Metodo para cargar los datos de los artistas en la tabla.
    public void cargarDatosArtistas(DefaultTableModel modelo, String filtro) {
        try {
            //Conexion a la base de datos.
            String url = "jdbc:mysql://localhost:3306/bdproyectofinal";
            Connection conexion = DriverManager.getConnection(url, "root", "");
            PreparedStatement select = conexion.prepareStatement(filtro);
            java.sql.ResultSet rs = select.executeQuery();
            //Bucle para añadir los datos de los artistas a la tabla.
            while (rs.next()) {
                String id = valueOf(rs.getInt("ID"));
                String AnyosExp = valueOf(rs.getInt("AnyosExp"));
                String ID_Proyecto = valueOf(rs.getInt("ID_Proyecto"));     
                modelo.addRow(new String[] { id, rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Especialidad"), AnyosExp, ID_Proyecto});
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
