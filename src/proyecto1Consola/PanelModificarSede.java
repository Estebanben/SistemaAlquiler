package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelModificarSede extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button;

    public PanelModificarSede(Empresa empresa, Administrador admin,App app) {
        // Initialize the text fields

        label1 = new JLabel("Nombre Sede a modificar"); 
        label2 = new JLabel("Nuevo nombre de la sede"); 
        label3 = new JLabel("Nueva direccion de la sede"); 
        label4 = new JLabel("Nueva Hora de apertura (HH:mm)");
        label5 = new JLabel("Nueva Hora de cierre (HH:mm)");
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
        textField5 = new JTextField(10);
        setBackground(Color.BLACK);

        // Initialize the button
        button = new JButton("Submit");

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capture the text from each text field
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                String text3 = textField3.getText();
                String text4 = textField4.getText();
                String text5 = textField5.getText();

                // Pass the text to a method in another class
                app.PrepararModSede(text1, text2, text3, text4, text5, empresa, admin);
                JOptionPane.showMessageDialog(null, "El cambio de estado se realizo con exito");

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
        add(label5);
        add(textField5);
        add(button);
    }
}
