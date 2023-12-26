package proyecto1Consola;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import proyecto1BackEnd.Empresa;

public class PanelRegistro extends JPanel {
    private JTextField[] textFields = new JTextField[12];
    private String[] savedTexts = new String[12];
    private String ArchivoClientes;
    private Empresa empresa;
    private App app;
    private JFrame frame;

    public PanelRegistro(Empresa empresa,App app,JFrame frame,String ArchivoClientes) {
        this.empresa = empresa;
        this.app = app;
        this.frame = frame;
        this.ArchivoClientes = ArchivoClientes;
        setLayout(new GridLayout(13, 1)); // Arrange components in a grid
        setBackground(Color.BLACK);

        for (int i = 0; i < 12; i++) {
            textFields[i] = new JTextField();
            if (i==0){
                JLabel label = new JLabel("Usuario:");
                add(label);
                add(textFields[i]);
            }
            if (i==1){
                JLabel label = new JLabel("Clave:");
                add(label);
                add(textFields[i]);
            }
            if (i==2){
                JLabel label = new JLabel("Nombre:");
                add(label);
                add(textFields[i]);
            }
            if (i==3){
                JLabel label = new JLabel("Telefono:");
                add(label);
                add(textFields[i]);
            }
            if (i==4){
                JLabel label = new JLabel("Fecha de Nacimiento(yyyy-mm-dd):");
                add(label);
                add(textFields[i]);
            }
            if (i==5){
                JLabel label = new JLabel("Nacionalidad:");
                add(label);
                add(textFields[i]);
            }
            if (i==6){
                JLabel label = new JLabel("Numero de Documento:");
                add(label);
                add(textFields[i]);
            }
            if (i==7){
                JLabel label = new JLabel("Numero de Tarjeta de credito:");
                add(label);
                add(textFields[i]);
            }
            if (i==8){
                JLabel label = new JLabel("Fecha de vencimiento tarjeta (yyyy-mm-dd):");
                add(label);
                add(textFields[i]);
            }
            if (i==9){
                JLabel label = new JLabel("Numero de licencia:");
                add(label);
                add(textFields[i]);
            }
            if (i==10){
                JLabel label = new JLabel("Pais de expedicion de licencia:");
                add(label);
                add(textFields[i]);
            }
            if (i==11){
                JLabel label = new JLabel("Fecha de vencimiento de licencia:");
                add(label);
                add(textFields[i]);
            }
            
            
        }

        JButton saveButton = new JButton("Save Text");
        add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                for (int i = 0; i < 12; i++) {
                    savedTexts[i] = textFields[i].getText();
                }
                Boolean x = app.RegistrarNuevoCliente(savedTexts[0], savedTexts[1], savedTexts[2], savedTexts[3], savedTexts[4], savedTexts[5], savedTexts[6], savedTexts[7], savedTexts[8], savedTexts[9], savedTexts[10], savedTexts[11], empresa, ArchivoClientes);
                if (x == true){
                    JOptionPane.showMessageDialog(null, "El registro se realizo correctamente");
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El registro no pudo ser realizado, por favor verifique su informacion");
                }
                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "El registro no pudo ser realizado, por favor verifique su informacion");

                }
            }
        });
    }
}
