package proyectojavabd;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.sql.SQLException;
import javax.swing.JTextField;


public class AddArtista extends JFrame{
    public AddArtista() {
        //Conexion a la base de datos.
        String url = "jdbc:mysql://localhost:3306/bdproyectofinal";
        //Creamos la ventana de añadir artista.
        this.setTitle("A�adir Artista");
        this.setSize(500, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        JLabel nombre = new JLabel("Nombre");
        JTextField nombre_texto = new JTextField();

        JLabel apellidos = new JLabel("Apellidos");
        JTextField apellidos_texto = new JTextField();

        JLabel especialidad = new JLabel("Especialidad");
        JTextField especialidad_texto = new JTextField();

        JLabel anyos_exp = new JLabel("A�os de experiencia");
        JTextField anyos_exp_texto = new JTextField();

        JLabel id_proyecto = new JLabel("ID Proyecto");
        JTextField id_proyecto_texto = new JTextField();

        JButton boton_anyadir_artista = new JButton("A�adir Artista");
        //Al pulsar este boton se extraen los datos de los textfields y se añaden a la base de datos.
        boton_anyadir_artista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent operar) {
                try {
                    //extraemos los datos de los textfields.
                    String nombreArtista = nombre_texto.getText();
                    String apellidosArtista = apellidos_texto.getText();
                    String especialidadArtista = especialidad_texto.getText();
                    String anyosExpArtista = anyos_exp_texto.getText();
                    String idProyectoArtista = id_proyecto_texto.getText();
                    int anyosExp = Integer.parseInt(anyosExpArtista);
                    int idProyecto = Integer.parseInt(idProyectoArtista);
                    //Hacemos la conexion y el insert.
                    Connection conexion = DriverManager.getConnection(url, "root", "");
                    PreparedStatement insert = conexion.prepareStatement("INSERT INTO Artistas (Nombre, Apellidos, Especialidad, AnyosExp, ID_Proyecto) VALUES (?, ?, ?, ?, ?)");

                    insert.setString(1, nombreArtista);
                    insert.setString(2, apellidosArtista);
                    insert.setString(3, especialidadArtista);
                    insert.setInt(4, anyosExp);
                    insert.setInt(5, idProyecto);

                    insert.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Artista A�adido correctamente");
                    insert.close();
                    conexion.close();
                
        
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al ��dir el artista. Quizas el ID del proyecto no existe");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Introduce valores validos (Solo numeros en A�os de experiencia y ID Proyecto)");
                } 
        
            }
        });


        this.setVisible(true);
        this.add(panel);
        panel.add(nombre);
        panel.add(nombre_texto);
        panel.add(apellidos);
        panel.add(apellidos_texto);
        panel.add(especialidad);
        panel.add(especialidad_texto);
        panel.add(anyos_exp);
        panel.add(anyos_exp_texto);
        panel.add(id_proyecto);
        panel.add(id_proyecto_texto);
        panel.add(boton_anyadir_artista);
        this.pack();
    }
    
}
