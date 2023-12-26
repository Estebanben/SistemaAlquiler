package proyecto1Consola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import proyecto1BackEnd.*;


public class PanelSesion extends JPanel {
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;
    private Empresa empresaM;  
    private String tipoUsuarioL;
    private App app;
    private JFrame frame;
    private String ArchivoAdmins;
    private String ArchivoAlquileres;
    private String ArchivoCarros;
    private String ArchivoCategorias;
    private String ArchivoClientes;
    private String ArchivoConductoresAd;
    private String ArchivoEmpleados;
    private String ArchivoReservas;
    private String ArchivoSedes;
    private String ArchivoSeguros;

    public PanelSesion(Empresa empresa,String tipoUsuario,App app,JFrame frame,String ArchivoAdmins,String ArchivoAlquileres,String ArchivoCarros,String ArchivoCategorias,String ArchivoClientes,String ArchivoConductoresAd,String ArchivoEmpleados,String ArchivoReservas,String ArchivoSedes,String ArchivoSeguros) {  
        this.empresaM = empresa;
        this.tipoUsuarioL = tipoUsuario;
        this.app = app;
        this.frame = frame;

        JLabel label1 = new JLabel("Usuario:");
        JLabel label2 = new JLabel("Clave:");
        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        button = new JButton("Iniciar Sesion");
        setBackground(Color.BLACK);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String Usuario = textField1.getText();
                String Clave = textField2.getText();

                ArrayList<Object> InicSesionExitosa = app.IniciarSesion(Usuario, Clave, tipoUsuario, empresa);
                Object FirstElement = InicSesionExitosa.get(0);
                Boolean InicTrue = (Boolean) FirstElement;
                Object SecondElement = InicSesionExitosa.get(1);
                if (InicTrue==true){
                    if(tipoUsuario.equals("Admin")){
                        Administrador admin = (Administrador) SecondElement;
                        JFrame ventanaAdmin = new VentanaAdmin(admin,app,empresa, ArchivoCategorias, ArchivoCarros,  ArchivoAdmins,  ArchivoSedes,  ArchivoSeguros);
                    }
                    else if (tipoUsuario.equals("AdminLocal")){
                        AdministradorLocal al = (AdministradorLocal)SecondElement;
                        JFrame ventanaAdminLoc = new VentanaAdminLocal(app,empresa,ArchivoEmpleados,al);
                    }
                    else if (tipoUsuario.equals("Empleado")){
                        Empleado emp = (Empleado) SecondElement;
                        JFrame ventanaEmpleado = new VentanaEmpleado(app,empresa,emp);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null, "Por favor verificar la informacion ingresada");
                }
                }
                catch (Exception E){
                    JOptionPane.showMessageDialog(null, "Por favor verificar la informacion ingresada");

                }
            }
        });

        
        add(label1);
        add(textField1);
        add(label2);
        add(textField2);
        add(button);
    }
}
