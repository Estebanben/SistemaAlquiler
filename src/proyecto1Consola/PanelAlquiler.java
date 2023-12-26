package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.Cliente;
import proyecto1BackEnd.ConductorAdicional;
import proyecto1BackEnd.Empresa;
import proyecto1BackEnd.Seguro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class PanelAlquiler extends JPanel {
    private String Reserva;
    private JComboBox<String> comboBox;
    private JLabel jlabel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton buttonAlquiler;
    private App app;
    private Cliente cliente;
    private Empresa empresa;
    private String ArchivoAlquileres;
    private String ArchivoConductoresAd;
    private ArrayList<ConductorAdicional> conductoresAdicionalesAlq;
    private ArrayList<Seguro> segurosAlquiler;
    private VentanaCliente frame;

    public PanelAlquiler(App app,Cliente cliente,Empresa empresa,String ArchivoAlquileres,VentanaCliente frame,String ArchivoConductoresAd) {
        // Create the JComboBox
        setBackground(Color.BLACK);
        this.Reserva = "false";
        this.conductoresAdicionalesAlq = new ArrayList<>();
        PanelConductoresAd panelCond = new PanelConductoresAd(app, empresa, ArchivoConductoresAd, this);
        panelCond.setBackground(Color.BLACK);
        frame.add(panelCond, BorderLayout.SOUTH);

         //Create the JLabels
        JLabel jlabel0 = new JLabel("Reserva previa?");
        jlabel1 = new JLabel("Placa del carro");
        JLabel jlabel2 = new JLabel("Sede de recogida del carro");
        JLabel jlabel3 = new JLabel("Sede de entrega del carro");
        JLabel jlabel4 = new JLabel("Fecha y hora de recogida");
        JLabel jlabel5 = new JLabel("Fecha y hora de entrega");
        JLabel jlabel6 = new JLabel("Nombre del seguro a comprar (Opcional)");

        comboBox = new JComboBox<>();
        comboBox.addItem("No");
        comboBox.addItem("Si");


        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // If the second option is selected, hide textField1
                    if (e.getItem().equals("Si")) {
                        textField1.setVisible(false);
                        jlabel1.setVisible(false);
                        Reserva = "true";
                    } else if (e.getItem().equals("No")){
                        textField1.setVisible(true);
                        jlabel1.setVisible(true);
                        Reserva = "false";
                    }
                    // Revalidate and repaint the JPanel to show the changes
                    revalidate();
                    repaint();
                }
            }
        });

        // Create the JTextFields
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
        textField5 = new JTextField(10);
        textField6 = new JTextField(10);

       

        buttonAlquiler = new JButton("Alquilar");

        buttonAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from each JTextField
                try{
                if (Reserva.equals("false")){
                    String text1 = textField1.getText();
                    String text2 = textField2.getText();
                    String text3 = textField3.getText();
                    String text4 = textField4.getText();
                    String text5 = textField5.getText();
                    String text6 = textField6.getText();
                    Boolean x = app.PreparaAlquiler(conductoresAdicionalesAlq, text1, "false", text2, text3, text4, text5, text6, cliente, empresa, ArchivoAlquileres);
                    if(x==true){
                    JOptionPane.showMessageDialog(null, "Alquiler Realizado exitosamente");
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar el alquiler, verifique la informacion y vuelva a intentar");
                    }
                }
                else{
                    String text2 = textField2.getText();
                    String text3 = textField3.getText();
                    String text4 = textField4.getText();
                    String text5 = textField5.getText();
                    String text6 = textField6.getText();
                    Boolean x = app.PreparaAlquiler(conductoresAdicionalesAlq, "", "true", text2, text3, text4, text5, text6, cliente, empresa, ArchivoAlquileres);
                    if(x==true){JOptionPane.showMessageDialog(null, "Alquiler Realizado exitosamente");
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar el alquiler, verifique la informacion y vuelva a intentar");
                    }
                }
                }
                catch(Exception E){
                    System.out.println(E);
                    JOptionPane.showMessageDialog(null, "No se pudo realizar el alquiler, verifique la informacion y vuelva a intentar");
                }
            }
        });
        // Add the JComboBox and JTextFields to the JPanel
        add(jlabel0);
        add(comboBox);
        add(jlabel1);
        add(textField1);
        add(jlabel2);
        add(textField2);
        add(jlabel3);
        add(textField3);
        add(jlabel4);
        add(textField4);
        add(jlabel5);
        add(textField5);
        add(jlabel6);
        add(textField6);
        add(buttonAlquiler);


    }
    public void ActualizarConductoresAd(ConductorAdicional cond){
        ArrayList<ConductorAdicional>conductores = conductoresAdicionalesAlq;
        conductores.add(cond);
        conductoresAdicionalesAlq = conductores;
    }
}