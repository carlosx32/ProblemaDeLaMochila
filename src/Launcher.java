import knapsack.Modelo.Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carandy
 */
public class Launcher {
	Modelo mimodelo;
	public Launcher(){
		mimodelo=new Modelo();
                mimodelo.iniciar();
	}
	
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
    }

}