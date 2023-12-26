package proyecto1Consola;
import java.awt.Color;

import javax.swing.*;

import proyecto1BackEnd.Empresa;

public class VentanaReg extends JFrame {
    private String ArchivoClientes;
    private Empresa empresa;
    private App app;
    public VentanaReg(Empresa empresa,App app,String ArchivoClientes) {
        this.empresa = empresa;
        this.app = app;
        this.ArchivoClientes = ArchivoClientes;
        // Initialize the JFrame
        setTitle("VentanReg");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of PanelReg and add it to the JFrame
        PanelRegistro panelReg = new PanelRegistro(empresa,app,this,ArchivoClientes);
        add(panelReg);

        // Make the JFrame visible
        setVisible(true);

        getContentPane().setBackground(Color.BLACK);
    }
}