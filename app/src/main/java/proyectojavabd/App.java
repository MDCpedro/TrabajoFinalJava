package proyectojavabd;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.errorprone.annotations.ForOverride;
//Clase principal.
public class App {
//Main
    public static void main(String[] args) {
        // Creamos la ventana principal
        JFrame frame = new JFrame("Gestor de Empresa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setLayout(new GridLayout(5, 1));

        JLabel titulo = new JLabel("Administracion");

        JButton boton_artistas = new JButton("Artistas");
        JButton boton_directores = new JButton("Directores");
        JButton boton_proyectos = new JButton("Proyectos");
        JButton boton_recursos = new JButton("Recursos");
        // Añadimos los action listeners, cada uno abre una ventana diferente.
        boton_artistas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent operar) {
                new ListaArtistas();
            }
        });

        boton_directores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent operar) {
                new ListaDirectores();
            }
        });

        boton_proyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent operar) {
                new ListaProyectos();
            }
        });

        boton_recursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent operar) {
                new ListaRecursos();
            }
        });

        frame.add(panel);
        panel.add(titulo);
        panel.add(boton_artistas);
        panel.add(boton_directores);
        panel.add(boton_proyectos);
        panel.add(boton_recursos);
        frame.setVisible(true);

    }
}
