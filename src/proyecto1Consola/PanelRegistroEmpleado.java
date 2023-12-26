package proyecto1Consola;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.AdministradorLocal;
import proyecto1BackEnd.Cliente;
import proyecto1BackEnd.Empresa;

public class PanelRegistroEmpleado extends JPanel{


	private AdministradorLocal al;
	private App app;
	private Empresa empresa;
	private String pathArchivo;
	
	
	
	public PanelRegistroEmpleado(App app,Empresa empresa,String pathArchivo, AdministradorLocal al) {
		
		//pide: usuario, contraseña
		
		JLabel labelUser = new JLabel("Usuario Nuevo");
		JTextField pideUser = new JTextField(10);
		
		
		JLabel labelContrasena = new JLabel("Contraseña Nueva");
		JTextField pideContrasena = new JTextField(10);
		
		
		JButton enviar = new JButton("Enviar");
		setBackground(Color.BLACK);
		
		add(labelUser);
		add(pideUser);
		add(labelContrasena);
		add(pideContrasena);
		add(enviar);
		
		
	
	
	
	
	
	//Action Listeners
		
		enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // Capture the text from the text field
                String usuario = pideUser.getText();
                String contrasena = pideContrasena.getText();

                // Pass the text and selected item to a method in another class
                String x = app.traductorRegistroEmpleado(usuario, contrasena, al, empresa, pathArchivo);
				if (x.equals("Empleado creado correctamente")){
                	JOptionPane.showMessageDialog(null, "El cambio de estado se realizo con exito");
				}
				else{
                    JOptionPane.showMessageDialog(null, "no fue posible realizar el cambio de estado, por favor verifique la informacion y vuelva a intentar");
				}

                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible realizar el cambio de estado, por favor verifique la informacion y vuelva a intentar");
                }
            }
        });
		
		
	
	
	}
	
	
}
