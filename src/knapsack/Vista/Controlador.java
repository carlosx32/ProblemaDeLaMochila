/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Controlador implements ActionListener {

    private VistaPrincipal ventana;

    Controlador(VistaPrincipal aThis) {
        ventana = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (((JButton) e.getSource()).getText().equalsIgnoreCase("Iniciar")) {
                ventana.getModelo().crearCampos(Integer.parseInt(ventana.getjTextField4().getText()),
                        Integer.parseInt(ventana.getnObjetos().getText()));
            } 
        }
    }

}
