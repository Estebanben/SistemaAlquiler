package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearCategoria extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button;

    public PanelCrearCategoria(Empresa empresa,Administrador admin,String ArchivoCategorias,App app) {
        // Initialize the text fields
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        label1 = new JLabel("Nombre Categoria a crear"); 
        label2 = new JLabel("Tarifa temporada alta"); 
        label3 = new JLabel("Tarifa temporada baja"); 
        setBackground(Color.BLACK);

        // Initialize the button
        button = new JButton("Crear");

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // Capture the text from each text field
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                String text3 = textField3.getText();

                // Pass the text to a method in another class
                String x = app.PrepararCrearCategoria(text1,text2,text3,empresa,admin,ArchivoCategorias);
                if (x.equals("La categoria fue creada con exito")){
                    JOptionPane.showMessageDialog(null, "Categoria creada exitosamente");

                }
                else{
                    JOptionPane.showMessageDialog(null, "no fue posible crear la categoria, verifique la informacion y vuelva a intentar");
                }
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible crear la categoria, verifique la informacion y vuelva a intentar");
                }
            }
        });

        // Add the text fields and button to the panel
        add(label1);
        add(textField1);
        add(label2);
        add(textField2);
        add(label3);
        add(textField3);
        add(button);
    }
}
