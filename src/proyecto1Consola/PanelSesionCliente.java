package proyecto1Consola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import proyecto1BackEnd.*;

public class PanelSesionCliente extends JPanel {
    private JTextField TextFieldUsuario;
    private JTextField TextFieldClave;
    private String ArchivoClientes;
    private String ArchivoAlquileres;
    private String ArchivoReservas;
    private String ArchivoConductoresAd;
    private String ArchivoSeguros;
    private Empresa empresa;
    private App app;
    private JFrame frame;


    public PanelSesionCliente(Empresa empresa,App app,JFrame frame,String ArchivoClientes,String ArchivoAlquileres,String ArchivoReservas,String ArchivoConductoresAd,String ArchivoSeguros) {
        this.empresa = empresa;
        this.app = app;
        this.frame = frame;
        this.ArchivoClientes = ArchivoClientes;
        this.ArchivoAlquileres = ArchivoAlquileres;
        this.ArchivoReservas = ArchivoReservas;
        this.ArchivoConductoresAd = ArchivoConductoresAd;
        this.ArchivoSeguros = ArchivoSeguros;
        
        JLabel label1 = new JLabel("Usuario:");
        JLabel label2 = new JLabel("Clave:");
        TextFieldUsuario = new JTextField(20);
        TextFieldClave = new JTextField(20);
        JButton buttonInic = new JButton("Inciar Sesion");
        JButton buttonReg = new JButton("Registrarse");
        setBackground(Color.BLACK);

        add(label1);
        add(TextFieldUsuario);
        add(label2);
        add(TextFieldClave);
        add(buttonInic);
        add(buttonReg);

        buttonInic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String Usuario = TextFieldUsuario.getText();
                String Clave = TextFieldClave.getText();
                ArrayList<Object> InicSesionExitosa = app.IniciarSesion(Usuario, Clave, "Cliente", empresa);
                Object FirstElement = InicSesionExitosa.get(0);
                Object SecondElement = InicSesionExitosa.get(1);
                Cliente ccliente = (Cliente) SecondElement;
                Boolean InicTrue = (Boolean) FirstElement;
                if (InicTrue==true){
                    JFrame ventanaCliente = new VentanaCliente(ccliente,empresa,app,ArchivoClientes,ArchivoAlquileres,ArchivoReservas,ArchivoConductoresAd,ArchivoSeguros);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Por favor verificar la informacion ingresada");
                }
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "Por favor verificar la informacion ingresada");

                }
            }
        });

        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventReg = new VentanaReg(empresa,app,ArchivoClientes);
                ventReg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });
    }

}
