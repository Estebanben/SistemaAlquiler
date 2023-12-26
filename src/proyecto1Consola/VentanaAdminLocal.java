package proyecto1Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.AdministradorLocal;
import proyecto1BackEnd.Empresa;

public class VentanaAdminLocal extends JFrame {
	
	private JPanel panelCentral; //este panel debe modificarse según el boton que oprima el usuario
	
	private JFrame frame ;
	private JPanel panBotones ;
	private JPanel panSaludo ;
	
	//botones
	
	private JButton btnRegistrar;
	private JButton btnEliminar;
	
	//labelsss
	private JLabel labelSaludo;
		
	
	// metodossss
	public VentanaAdminLocal(App app, Empresa empresa, String pathArchivo, AdministradorLocal al) { 


		frame = new JFrame();
		frame.setTitle("Administador Local");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1200,500);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		
		
		//Panel de saludo
		
		panSaludo = new JPanel();
		panSaludo.setBackground(Color.DARK_GRAY);
		labelSaludo = new JLabel("Bienvenido Administrador Local! ");
		labelSaludo.setForeground(Color.WHITE);
		panSaludo.add(labelSaludo);
		
		
		
		
		//Panel Central
		
		
		//Panel botones
		
		panBotones = new JPanel();
		panBotones.setPreferredSize(new Dimension(1200,50));
		panBotones.setBackground(Color.BLACK);
		
		btnRegistrar= new JButton("Registrar Empleado") ;
		btnEliminar = new JButton("Eliminar Empleado");
		
		panBotones.add(btnRegistrar);
		panBotones.add(btnEliminar);
		
		
		//Añadimos al frame principal
		
		frame.add(panSaludo,BorderLayout.NORTH);
		frame.add(panBotones,BorderLayout.SOUTH);
		
		
		//ACTION LISTENERS
		
		btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new PanelRegistrarEmpleado panel
                PanelRegistroEmpleado panelRegistrarEmpleado = new PanelRegistroEmpleado(app, empresa, pathArchivo,al);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = panelRegistrarEmpleado;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
            }
        });// cierra action listener registrar
		
	
		
		btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new PanelRegistrarEmpleado panel
                PanelEliminarEmpleado panelEliminarEmpleado = new PanelEliminarEmpleado(app, empresa, pathArchivo,al);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = panelEliminarEmpleado;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
            }
        });
		
		
		
	}


	
	
	
	
	
}
