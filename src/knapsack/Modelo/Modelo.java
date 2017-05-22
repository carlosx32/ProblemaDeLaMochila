/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack.Modelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import knapsack.Vista.VistaPrincipal;

/**
 *
 * @author carandy
 */
public class Modelo {

    private VistaPrincipal vista;
    private boolean[] pack;
    private int[] weight, value;
    private int k, nObjetos, maxWeight;

    public boolean[] getPack() {
        return pack;
    }
    
    public VistaPrincipal getVista() {
        if (vista == null) {
            vista = new VistaPrincipal(this);
        }
        return vista;
    }
    
    public void iniciar() {
        this.getVista().setVisible(true);
    }

    public void crearCampos(int maxW, int nObjetos) {
        this.maxWeight = maxW;
        this.nObjetos = nObjetos;

        weight = new int[nObjetos+1];
        value = new int[nObjetos+1];
        this.pack = new boolean[nObjetos+1];
        for (k = 0; k < nObjetos; k++) {
            weight[k] = Integer.parseInt(JOptionPane.showInputDialog(vista, "Ingrese el peso del articulo" + (k + 1)));
            value[k] = Integer.parseInt(JOptionPane.showInputDialog(vista, "Ingrese el beneficio del articulo" + (k + 1)));
        }
        
        crearArchivo();
        solucion();
    }
    

    void crearArchivo() {
        try {
            File file = new File("test\\BandP.txt");
            if (!file.exists()) {
                System.out.println("Creando en " + file.getPath());
                file.createNewFile();
            } else {
                PrintWriter pw = new PrintWriter(file);
                pw.println(maxWeight + "\n" + nObjetos);
                for (k = 0; k < nObjetos; k++) {
                    pw.println(weight[k] + " " + value[k]);
                }
                pw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void solucion() {
    	for(k=0;k<nObjetos;k++){
    		getVista().getTextArea().setText(getVista().getTextArea().getText() +"Articulo "+ k+1 +" ("  + weight[k] + "kg, $" + value[k] + "), \n");
        }
    	
    	
        knapSack01(maxWeight, pack, weight, value);
        showKnapSack(maxWeight, pack, weight, value);
    }

    void knapSack01(int maxWeight, boolean[] pack, int[] weight, int[] value) {
        int n = pack.length - 1;
        boolean[][] trial = new boolean[maxWeight + 1][n + 1];
        int[] bestVal = new int[maxWeight + 1];
        int wt;

        for (wt = 1; wt <= maxWeight; wt++) {
            int bestK = 0, testWt;

            // Initial guess:  the knapsack for wt-1.
            bestVal[wt] = bestVal[wt - 1];
            for (k = 1; k <= n; k++) {
                testWt = wt - weight[k];
                if (testWt >= 0 && !trial[testWt][k]) {
                    if (bestVal[wt] < value[k] + bestVal[testWt]) {
                        bestK = k;
                        bestVal[wt] = value[k] + bestVal[testWt];
                    }
                }
            }
            if (bestK > 0) {
                testWt = wt - weight[bestK];
                System.arraycopy(trial[testWt], 0, trial[wt], 0, n + 1);
                trial[wt][bestK] = true;
            } else // Can't get here, so finish using the wt-1 solution
            {
                System.arraycopy(trial[wt - 1], 0, trial[wt], 0, n + 1);
            }
        }
        System.arraycopy(trial[maxWeight], 0, pack, 0, n + 1);
    }

    void showKnapSack(int maxWeight, boolean[] pack, int[] weight, int[] value) {
        int sumWeight = 0, sumValue = 0,
                n = pack.length - 1;
        getVista().getTextArea().setText(getVista().getTextArea().getText() +"Solucion optima para  " + maxWeight+" kg\n");
        for ( k = 1; k <= n; k++) {
            if (pack[k]) {
                sumWeight += weight[k];
                sumValue += value[k];
                getVista().getTextArea().setText(getVista().getTextArea().getText() +"(" + weight[k] + "kg, $" + value[k] + "), ");
            }
        }
        getVista().getTextArea().setText(getVista().getTextArea().getText() +"\n\tPeso total :  " + sumWeight+"kg");
        getVista().getTextArea().setText(getVista().getTextArea().getText() +"\n\tBeneficio Optimo value:   $" + sumValue);
    }


}
