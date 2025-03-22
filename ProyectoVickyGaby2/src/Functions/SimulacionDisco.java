/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;
import EDD.Lista;
import MainClass.Bloque;


public class SimulacionDisco {
    private Bloque[] bloques;
    private Lista bloquesLibres;
    private int totalBloques;

    public SimulacionDisco(int totalBloques) {
        this.totalBloques = totalBloques;
        this.bloques = new Bloque[totalBloques];
        this.bloquesLibres = new Lista();

        // Inicializamos los bloques y los marcamos como libres
        for (int i = 0; i < totalBloques; i++) {
            bloques[i] = new Bloque(i);
            bloquesLibres.InsertarFinal(i);
        }
    }

    public int asignarBloques(int cantidad) {
        if (cantidad > bloquesLibres.getSize()) {
            System.out.println("No hay suficientes bloques disponibles.");
            return -1;
        }

        int primerBloque = (int) bloquesLibres.get(0);
        int bloqueActual = primerBloque;

        for (int i = 0; i < cantidad; i++) {
            int siguienteBloque = (i < cantidad - 1) ? (int) bloquesLibres.get(i + 1) : -1;
            bloques[bloqueActual].setOcupado(true);
            bloques[bloqueActual].setSiguiente(siguienteBloque);
            bloquesLibres.EliminarPorReferencia(bloqueActual);
            bloqueActual = siguienteBloque;
        }

        return primerBloque;
    }

    public void liberarBloques(int primerBloque) {
        int bloqueActual = primerBloque;

        while (bloqueActual != -1) {
            int siguiente = bloques[bloqueActual].getSiguiente();
            bloques[bloqueActual].setOcupado(false);
            bloques[bloqueActual].setSiguiente(-1);
            bloquesLibres.InsertarFinal(bloqueActual);
            bloqueActual = siguiente;
        }
    }

    public void mostrarEstado() {
        System.out.println("Estado del disco:");
        for (int i = 0; i < totalBloques; i++) {
            bloques[i].mostrarInfo();
        }
    }
    
    public Bloque[] getBloques() {
        return bloques;
    }

    }
