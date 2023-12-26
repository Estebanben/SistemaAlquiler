package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearSede extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button;

    public PanelCrearSede(App app,Empresa empresa,Administrador admin,String ArchivoSedes) {
        // Initialize the text fields

        label1 = new JLabel("Nombre Sede a crear"); 
        label2 = new JLabel("Direccion Sede"); 
        label3 = new JLabel("Hora de apertura (HH:mm)"); 
        label4 = new JLabel("Hora de cierre (HH:mm)"); 
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
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
                String text4 = textField4.getText();

                // Pass the text to a method in another class
                String x = app.PrepararCrearSede(text1,text2,text3,text4,empresa,admin,ArchivoSedes);
                if (x.equals("La sede se creo exitosamente")){
                    JOptionPane.showMessageDialog(null, "La sede se creo exitosamente");
                }
                else{
                    JOptionPane.showMessageDialog(null, "no fue posible crear la sede, verifique la informacion y vuelva a intentar");
                }
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible crear la sede, verifique la informacion y vuelva a intentar");
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
        add(label4);
        add(textField4);
        add(button);
    }
}