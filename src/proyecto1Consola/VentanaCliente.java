package proyecto1Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.*;

public class VentanaCliente extends JFrame {

    private String ArchivoClientes;
    private JPanel currentPanel;
    private String ArchivoAlquileres;
    private String ArchivoReservas;
    private String ArchivoConductoresAd;
    private String ArchivoSeguros;
    private App app;
    private Empresa empresa;
    private Cliente cliente;

    public VentanaCliente(Cliente cliente,Empresa empresa,App app,String ArchivoClientes,String ArchivoAlquileres,String ArchivoReservas,String ArchivoConductoresAd,String ArchivoSeguros) {
        this.ArchivoAlquileres = ArchivoAlquileres;
        this.ArchivoClientes = ArchivoClientes;
        this.ArchivoReservas = ArchivoReservas;
        this.ArchivoConductoresAd = ArchivoConductoresAd;
        this.ArchivoSeguros = ArchivoSeguros;
        this.app = app;
        this.empresa = empresa;
        this.cliente = cliente;
        setTitle("VentanaCliente");

        setLayout(new BorderLayout());

        JButton buttonReservar = new JButton("Reservar");
        JButton buttonAlquilar = new JButton("Alquilar");

        JPanel panel = new JPanel();

        panel.add(buttonReservar);
        panel.add(buttonAlquilar);

        buttonReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelReserva panelR = new PanelReserva(cliente, empresa, ArchivoReservas, app);
                if (currentPanel != null) {
                    getContentPane().remove(currentPanel);
                }

                // Create a new panel and add it to the center of the JFrame
                currentPanel = panelR;
                add(currentPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
                // Add the panel to the JFrame
                add(panelR, BorderLayout.CENTER);

                // Revalidate and repaint the JFrame to show the new panel
                revalidate();
                repaint();
            }
        });
        buttonAlquilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelAlquiler panelA = new PanelAlquiler(app,cliente,empresa,ArchivoAlquileres,VentanaCliente.this,ArchivoConductoresAd);
                if (currentPanel != null) {
                    getContentPane().remove(currentPanel);
                }

                // Create a new panel and add it to the center of the JFrame
                currentPanel = panelA;
                add(currentPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
                // Add the panel to the JFrame
                add(panelA, BorderLayout.CENTER);

                // Revalidate and repaint the JFrame to show the new panel
                revalidate();
                repaint();
            }
        });

        add(panel, BorderLayout.NORTH);

        getContentPane().setBackground(Color.BLACK);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(700, 500);

        setVisible(true);
    }
}
