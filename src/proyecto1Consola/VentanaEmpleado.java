package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Empleado;
import proyecto1BackEnd.Empresa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaEmpleado extends JFrame {
    private App app;
    private Empresa empresa;
    private Empleado empleado;
    private JPanel currentPanel;

    public VentanaEmpleado(App app, Empresa empresa, Empleado empleado) {
        this.app = app;
        this.empresa = empresa;
        this.empleado = empleado;

        // Set the title of the JFrame
        setTitle("VentanaEmpleado");

        // Set the layout manager to BorderLayout
        setLayout(new BorderLayout());

        // Create two new buttons
        JButton button1 = new JButton("Recibir carro");
        JButton button2 = new JButton("Cambiar estado carro");

        // Add action listeners to the buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                if (currentPanel != null) {
                    remove(currentPanel);
                }

                // Create a new panel and set it as the current panel
                currentPanel = new PanelRecibirCarro(empresa,app,empleado); // Replace Panel1 with the actual class you want to use
                add(currentPanel, BorderLayout.CENTER);

                // Refresh the JFrame
                validate();
                repaint();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                if (currentPanel != null) {
                    remove(currentPanel);
                }

                // Create a new panel and set it as the current panel
                currentPanel = new PanelRecibirCarro(empresa,app,empleado); // Replace Panel2 with the actual class you want to use
                add(currentPanel, BorderLayout.CENTER);

                // Refresh the JFrame
                validate();
                repaint();
            }
        });

        // Create a new JPanel with a FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Add the buttons to the JPanel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add the JPanel to the north part of the BorderLayout
        add(buttonPanel, BorderLayout.NORTH);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the size of the JFrame
        setSize(700, 500);

        // Make the JFrame visible
        setVisible(true);

        getContentPane().setBackground(Color.BLACK);
    }
}

