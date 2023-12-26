package proyecto1Consola;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

public class PanelDarBajaCarro extends JPanel {

	
	
	
	
	public PanelDarBajaCarro (Empresa empresa, App app, Administrador admin) {
		
		JLabel placa = new JLabel("Placa");
		JTextField daPlaca = new JTextField(10);
		
		JButton darBaja = new JButton("Dar de Baja");
        setBackground(Color.BLACK);
		
		
		add(placa);
		add(daPlaca);
		add(darBaja);
		
		
		darBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // Capture the text from each text field
                String text1 = daPlaca.getText();


                // Pass the text to a method in another class
                String x = app.PrepararDarDeBajaCarro(text1, empresa, admin);
                if (x.equals("Se dio de baja el carro correctamente")){
                    JOptionPane.showMessageDialog(null, "El carro se eliminó correctamente :)");
                }
                else{
                    JOptionPane.showMessageDialog(null, "no fue posible dar de baja el vehículo, verifique la informacion y vuelva a intentar");
                }
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible dar de baja el vehículo, verifique la informacion y vuelva a intentar");
                }
            }
        });
		
		
		
		
		
		
		
	}
	
	
}
