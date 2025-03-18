/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Proyecto;

import MainClass.SistemaArchivos;
import Interfaces.Pantalla;

public class Proyecto224252SOVictoriaGarcia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       SistemaArchivos sistema = new SistemaArchivos(20);

        Pantalla appWindow = new Pantalla(sistema);
        appWindow.setVisible(true);
    
    }
}
