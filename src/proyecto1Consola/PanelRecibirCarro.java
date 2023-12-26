package proyecto1Consola;

import javax.swing.*;

import proyecto1BackEnd.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;



public class PanelRecibirCarro extends JPanel {
    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox;
    private JButton button;
    private Empresa empresa;
    private Empleado empleado;

    public PanelRecibirCarro(Empresa empresa, App app,Empleado empleado) {
        // Initialize the text field
        textField = new JTextField(10);
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        setBackground(Color.BLACK);

        // Initialize the combo box
        comboBox = new JComboBox<>(new String[]{"Alquilado", "Disponible", "Reservado","Limpieza","Mantenimiento"});

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // If "Disponible" is selected, make textField1 and textField2 invisible
                    if (comboBox.getSelectedItem().equals("Disponible")) {
                        textField1.setVisible(false);
                        textField2.setVisible(false);
                        PanelRecibirCarro.this.revalidate();
                        PanelRecibirCarro.this.repaint();
                    } else {
                        // If another item is selected, make textField1 and textField2 visible
                        textField1.setVisible(true);
                        textField2.setVisible(true);
                        PanelRecibirCarro.this.revalidate();
                        PanelRecibirCarro.this.repaint();
                    }
                }
            }
        });

        // Initialize the button
        button = new JButton("Cambiar Estado");

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // Capture the text from the text field
                String text = textField.getText();
                String text1 = textField1.getText();
                String text2 = textField2.getText();

                // Capture the selected item from the combo box
                String selectedItem = (String) comboBox.getSelectedItem();

                // Pass the text and selected item to a method in another class
                app.PrepararCambioDeEstado(empresa,empleado,text,selectedItem,text1,text2);
                JOptionPane.showMessageDialog(null, "El cambio de estado se realizo con exito");

                }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "no fue posible realizar el cambio de estado, por favor verifique la informacion y vuelva a intentar");
                }
            }
        });

        // Add the text field, combo box, and button to the panel
        add(comboBox);
        add(textField);
        add(textField1);
        add(textField2);
        add(button);
    }
}
