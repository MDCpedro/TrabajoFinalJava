package proyectojavabd;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.BorderFactory;



public class ListaArtistas extends JFrame {
    
    public void menu() {
        String url = "jdbc:mysql://localhost:3306/bdproyectofinal";
        Connection conexion = DriverManager.getConnection(url, "root", "");

        this.setTitle("Actividad Swing 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        

        this.add(panel);
        this.setVisible(true);
    }

}
