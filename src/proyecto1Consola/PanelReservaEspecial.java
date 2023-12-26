package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Administrador;
import proyecto1BackEnd.Empresa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelReservaEspecial extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button;

    public PanelReservaEspecial(Empresa empresa, Administrador admin,App app) {
        // Initialize the text fields
        label1 = new JLabel("Placa del carro a transladar");
        label2 = new JLabel("Sede de inicio");
        label3 = new JLabel("Sede final");
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        setBackground(Color.BLACK);

        // Initialize the button
        button = new JButton("Generar reserva especial");

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
                String x = app.PrepararTransladoCarro(text1,text2,text3,empresa,admin);
                if(x.equals("Reserva exitosa")){
                    JOptionPane.showMessageDialog(null, "Reserva exitosa");
                }
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la reserva, verifique la informacion y vuelva a intentar");
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
