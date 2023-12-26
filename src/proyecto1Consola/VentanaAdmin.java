package proyecto1Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

public class VentanaAdmin extends JFrame {
   
	private JFrame frame ;
	private JPanel panBotones ;
	private JPanel panSaludo ;
	private JPanel panelCentral;
	
	
	//botones de la barra superior
	
	private JButton btnRegistrarCarro;
	private JButton btnDarBajaCarro;
	private JButton btnCrearSeguro;
	private JButton btnReservaEspecial;
	private JButton btnCrearSede;
	private JButton btnModificarSede;
	private JButton btnLogCarro;
	private JButton btnCrearAdminLocal;
	private JButton btnCrearCategoria;
	
	//label de saludo
	
	private JLabel labelSaludo;
	
	
	
	
	
	//METODOS 
	
	
	public VentanaAdmin(Administrador admin,App app, Empresa empresa, String ArchivoCategorias, String ArchivoCarros, String ArchivoAdminsLocales, String ArchivoSedes, String ArchivoSeguros) { 
		
		frame = new JFrame();
		frame.setTitle("Administador Global");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1200,500);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		
		
		//Panel donde van los botones
		
		panBotones = new JPanel();
		panBotones.setBackground(Color.black);
		panBotones.setLayout(new GridLayout());
		panBotones.setPreferredSize(new Dimension(1200,50));
		
		
		
		
		
		
		btnRegistrarCarro = new JButton("Registrar Carro");
		btnDarBajaCarro = new JButton("Dar Carro de Baja");
		btnCrearSeguro = new JButton("Crear Seguro");
		btnReservaEspecial = new JButton("Reserva Especial");
		btnCrearSede = new JButton("Crear Sede") ;
		btnModificarSede = new JButton("Modificar Sede");
		btnCrearAdminLocal = new JButton("Crear Admin Local");
		btnCrearCategoria = new JButton("Crear Categoría");
		
		
		panBotones.add(btnRegistrarCarro);
		panBotones.add(btnDarBajaCarro);
		panBotones.add(btnCrearSeguro);
		panBotones.add(btnReservaEspecial);
		panBotones.add(btnCrearSede);
		panBotones.add(btnModificarSede);
		panBotones.add(btnCrearAdminLocal);
		panBotones.add(btnCrearCategoria);
		
		
		//PANEL DE SALUDO
		
		panSaludo = new JPanel();
		panSaludo.setBackground(Color.DARK_GRAY);
		labelSaludo = new JLabel("Bienvenido Administrador Global! ");
		labelSaludo.setForeground(Color.WHITE);
		panSaludo.add(labelSaludo);
		
		
		
		//PANELES CENTRALES DODNDE DEBE ENTRAR TODA LA FUNCIONALIDAD
		
		
		//Añadimos todo al frame principal
		
		
		frame.add(panBotones, BorderLayout.SOUTH);
		frame.add(panSaludo, BorderLayout.NORTH);
		
		
		
		
		//Action listeners
		
		
		
		
		btnRegistrarCarro.addActionListener(new ActionListener() {
           
			
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelRegistrarCarro prc = new PanelRegistrarCarro(app, empresa,admin,ArchivoCarros );

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = prc;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar la jeepeta, verifique la informacion y vuelva a intentar");};
			
			
			}
        });

		btnCrearCategoria.addActionListener(new ActionListener() {
           
			
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelCrearCategoria prc = new PanelCrearCategoria(empresa, admin,ArchivoCategorias,app);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = prc;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar la categoria");};
			
			}
        });
		
		btnCrearAdminLocal.addActionListener(new ActionListener() {
           
			
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelCrearAdminL prc = new PanelCrearAdminL(empresa, admin,ArchivoAdminsLocales,app);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = prc;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar el admin");};
			
			}
        });
		btnModificarSede.addActionListener(new ActionListener() {
           
			
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelModificarSede prc = new PanelModificarSede(empresa, admin,app);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = prc;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar la jeepeta, verifique la informacion y vuelva a intentar");};
			
			
			}
        });
		btnCrearSede.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelCrearSede prc = new PanelCrearSede(app,empresa,admin,ArchivoSedes);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = prc;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar la sede");};
			
			
			}
        });

		btnReservaEspecial.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelReservaEspecial prc = new PanelReservaEspecial(empresa,admin,app);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = prc;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible generar la reserva");};
			
			
			}
        });

		btnCrearSeguro.addActionListener(new ActionListener() {
           
			@Override
            public void actionPerformed(ActionEvent e) {
                
				try {
				
				// Create a new PanelRegistrarEmpleado panel
                PanelCrearSeguro pcs = new PanelCrearSeguro(empresa, admin,ArchivoSeguros, app);

                // If there is a current panel, remove it
                if (panelCentral != null) {
                    frame.getContentPane().remove(panelCentral);
                }

                // Set the new panel as the current panel
                panelCentral = pcs;

                // Add the new panel to the center of the JFrame
                frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

                // Refresh the JFrame
                frame.revalidate();
                frame.repaint();
			} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar la categoria");};
			
			}
        });
		
		
		
		
		
btnDarBajaCarro.addActionListener(new ActionListener() {
    
	
		@Override
     public void actionPerformed(ActionEvent e) {
         
		try {
			
			
         PanelDarBajaCarro prc = new PanelDarBajaCarro(empresa, app,admin);

         // If there is a current panel, remove it
         if (panelCentral != null) {
             frame.getContentPane().remove(panelCentral);
         }

         // Set the new panel as the current panel
         panelCentral = prc;

         // Add the new panel to the center of the JFrame
         frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

         // Refresh the JFrame
         frame.revalidate();
         frame.repaint();
		} catch(Exception E) {JOptionPane.showMessageDialog(null, "no fue posible registrar el admin");};
		
		}
 });

		

	
	}	
	
	
	
}

	
	
	

