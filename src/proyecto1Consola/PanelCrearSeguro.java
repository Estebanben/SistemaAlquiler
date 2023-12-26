package proyecto1Consola;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

public class PanelCrearSeguro extends JPanel {

	
	//String NombreSeguro,String DescripcionSeguro,String PrecioSeguro,Empresa empresa, Administrador admin,String ArchivoSeguros
	
	
	public PanelCrearSeguro(  Empresa empresa, Administrador admin, String ArchivoSeguros, App app  ) {
		
		//String Nombre,String Descripcion, Integer Precio,String filePath,Empresa empresa
		
		
		JLabel nombre = new JLabel("Nombre del seguro");
		JTextField pideNombre = new JTextField(10);
		
		JLabel descripcion = new JLabel("Descripcion");
		JTextField pideDescripcion = new JTextField(50);
		
		JLabel precio = new JLabel("Precio");
		JTextField pidePrecio = new JTextField(10);
		setBackground(Color.BLACK);
		
		
		add(nombre);
		add(pideNombre);
		
		add(descripcion);
		add(pideDescripcion);
		
		add(precio);
		add(pidePrecio);
		
		
		JButton crear = new JButton("Crear Seguro");
		add(crear);
		
		
		crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // Capture the text from each text field
                String text1 = pideNombre.getText();
                String text2 = pideDescripcion.getText();
                String text3 = pidePrecio.getText();

                // Pass the text to a method in another class
                String x = app.PrepararCrearSeguro(text1, text2, text3, empresa, admin, ArchivoSeguros);
				if (x.equals("Exitoso")){
                	JOptionPane.showMessageDialog(null, "El seguro se creo perfecto papiiii");
				}
				else{
                    JOptionPane.showMessageDialog(null, "no fue posible crear el seguro, verifique la informacion y vuelva a intentar");
				}
                
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible crear el seguro, verifique la informacion y vuelva a intentar");
                }
            }
        });
		
		
		
	}
	
	
	
	
}
