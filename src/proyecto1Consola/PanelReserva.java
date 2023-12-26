package proyecto1Consola;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proyecto1BackEnd.*;


public class PanelReserva extends JPanel {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private Cliente cliente;
    private Empresa empresa;
    private String filePath;
    private App app;

    public PanelReserva(Cliente cliente,Empresa empresa,String filePath,App app) {
        // Create the text fields
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
        textField5 = new JTextField(10);
        JLabel label1 = new JLabel("Nombre categoria");
        JLabel label2 = new JLabel("Nombre sede recogida");
        JLabel label3 = new JLabel("Nombre sede entrega");
        JLabel label4 = new JLabel("Fecha y hora recogida (yyyy-mm-dd HH:mm)");
        JLabel label5 = new JLabel("Fecha y hora entrega (yyyy-mm-dd HH:mm)");
        setBackground(Color.BLACK);

        // Add the text fields to the panel
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

        // Create a button
        JButton button = new JButton("Reservar");

        // Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // Extract the text from the text fields
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                String text3 = textField3.getText();
                String text4 = textField4.getText();
                String text5 = textField5.getText();

                // Send the text to a method in another class
                Boolean x = app.PrepararReserva(text1, text2, text3, text4, text5, empresa, cliente, filePath);
                if (x == true){
                    JOptionPane.showMessageDialog(null, "La reserva fue realizada con exito");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la reserva, verifique la informacion y vuelva a intentar");
                }

                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la reserva, verifique la informacion y vuelva a intentar");

                }
            }
        });

        // Add the button to the panel
        add(button);
    }
}