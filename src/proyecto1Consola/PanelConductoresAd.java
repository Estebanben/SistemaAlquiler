package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConductoresAd extends JPanel {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button;
    private App app;
    private Empresa empresa;
    private String ArchivoConductoresAd;
    private  PanelAlquiler frame;
    

    public PanelConductoresAd(App app,Empresa empresa,String ArchivoConductoresAd,PanelAlquiler frame) {
        this.app=app;
        this.empresa = empresa;
        this.ArchivoConductoresAd=ArchivoConductoresAd;
        this.frame = frame;

        // Initialize the text fields
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
        textField5 = new JTextField(10);

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
                String text4 = textField4.getText();
                String text5 = textField5.getText();

                // Pass the text to a method in another class
                ConductorAdicional x = app.RegistrarConductorAdicional(text1, text2, text3, text4, text5, empresa, ArchivoConductoresAd);
                frame.ActualizarConductoresAd(x);
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "No fue posible crear el conductor adicional, por favor verifique la informacion y vuelva a intentarlo");
                }
            }
        });

        // Add the text fields and button to the panel
        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
        add(textField5);
        add(button);
    }
}
