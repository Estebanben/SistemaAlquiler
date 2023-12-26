package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearAdminL extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button;

    public PanelCrearAdminL(Empresa empresa,Administrador admin,String ArchivoAdmins,App app) {
        // Initialize the text fields
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        label1 = new JLabel("Usuario admin local a crear"); 
        label2 = new JLabel("Clave admin local a crear"); 
        label3 = new JLabel("Nombre sede admin local"); 
        setBackground(Color.BLACK);

        // Initialize the button
        button = new JButton("Submit");

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
                String x = app.PrepararCrearAdminLocal(text1,text2,text3,empresa,ArchivoAdmins,admin);
                if (x.equals("El administrador local fue creado con exito")){
                    JOptionPane.showMessageDialog(null, "Administrador creado con exito");
                }
                else{
                    JOptionPane.showMessageDialog(null, "no fue posible crear el admin, verifique la informacion y vuelva a intentar");
                }

                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible crear el admin, verifique la informacion y vuelva a intentar");
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
