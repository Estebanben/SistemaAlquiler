package proyecto1Consola;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.AdministradorLocal;
import proyecto1BackEnd.Empresa;

public class PanelEliminarEmpleado extends JPanel{

	private AdministradorLocal al;
	private App app;
	private Empresa empresa;
	private String pathArchivo;
	
	
	//String UsuarioEmpleadoEliminar,Empresa empresa,AdministradorLocal adminLocal
	
	
	public PanelEliminarEmpleado(App app, Empresa empresa, String pathArchivo,AdministradorLocal al){
		
		
		JLabel usuarioEliminar = new JLabel("Usuario del empleado a eliminar: ");
		JTextField escribirUsuario = new JTextField(10);
		JButton eliminar = new JButton("Eliminar Empleado");
		setBackground(Color.BLACK);
		
		add(usuarioEliminar);
		add(escribirUsuario);	
		add(eliminar);
		
		
	
		
		//Action Listeners
		
				eliminar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                try{
		                // Capture the text from the text field
		                String usuario = escribirUsuario.getText();
		               
		                // Pass the text and selected item to a method in another class
		                String x = app.PrepararEliminacionEmpleado(usuario, empresa, al);
						if (x.equals("Se elimino el empleado de manera correcta")){
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
