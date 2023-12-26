package proyecto1Consola;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Categoria;
import proyecto1BackEnd.Empresa;
import proyecto1BackEnd.Estado;
import proyecto1BackEnd.Sede;



public class PanelRegistrarCarro extends JPanel {

	
	// Placa, Transmision,  Color,  Marca, Modelo, Capacidad , Categoria categoria,Estado estado,Sede sede,Empresa empresa,String filePath
	
	public PanelRegistrarCarro(App app, Empresa empresa,Administrador admin,String ArchivoCarros ) {
	setBackground(Color.BLACK);
		
	JLabel placa = new JLabel("Placa");
	JTextField pidePlaca = new JTextField(10);
	add(placa);
	add(pidePlaca);
	
	
	JLabel transmision = new JLabel("Transmision ");
	JTextField pideTransmision = new JTextField(10);
	add(transmision);
	add(pideTransmision);
	
	JLabel color = new JLabel("Color ");
	JTextField pideColor = new JTextField(10);
	add(color);
	add(pideColor);
	
	
	JLabel marca = new JLabel("Marca ");
	JTextField pideMarca = new JTextField(10);
	add(marca);
	add(pideMarca);
	
	JLabel modelo = new JLabel("Modelo");
	JTextField pideModelo = new JTextField(10);
	add(modelo);
	add(pideModelo);
	
	
	JLabel capacidad = new JLabel("Capacidad");
	JTextField pideCapacidad = new JTextField(10);
	add(capacidad);
	add(pideCapacidad);
	
	
	JLabel categoria = new JLabel("Categoría");
	JTextField pideCategoria = new JTextField(10);
	add(categoria);
	add(pideCategoria);
	
	
//	JLabel estado = new JLabel("Estado () ");
//	JTextField pideEstado = new JTextField(10);
//	add(estado);
//	add(pideEstado);
//	
	
	JLabel sede = new JLabel("Sede");
	JTextField pideSede = new JTextField(10);
	add(sede);
	add(pideSede);
	
	
//	JLabel empresax = new JLabel("Empresa");
//	JTextField pideEmpresax = new JTextField(10);
//	add(empresax);
//	add(pideEmpresax);
	
	
	JButton registrar = new JButton("Registrar");
	add(registrar);
	
	//Action listeners
	
        registrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            // Capture the text from each text field
            String text1 = pidePlaca.getText();
            String text2 = pideTransmision.getText();
            String text3 = pideColor.getText();
            String text4 = pideMarca.getText();
            String text5 = pideModelo.getText();
            String text6 = pideCapacidad.getText();
            String text7 =pideCategoria.getText();
            String text8 = pideSede.getText();
           

            // Pass the text to a method in another class
           
            String x = app.PreparaCompraCarro(text1, text2, text3, text4, text5, text6, text7,text8, empresa , admin, ArchivoCarros);
			if (x.equals("Exitosos")){
				JOptionPane.showMessageDialog(null, "Carro registrado exitosamente");
			}
			else{
                JOptionPane.showMessageDialog(null, "No se puedo registrar el vehículo que pena pai");
			}

            }
            catch(Exception E){
                JOptionPane.showMessageDialog(null, "No se puedo registrar el vehículo que pena pai");
            }
        }
    });
	
	

	
}
	



}
