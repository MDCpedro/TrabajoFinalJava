package proyectojavabd;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestor de Empresa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        JPanel panel = new JPanel();

        
        frame.add(panel);
        frame.setVisible(true);

    }
}
