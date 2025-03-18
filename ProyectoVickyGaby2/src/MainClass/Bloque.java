/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;


public class Bloque {
    private int id;
    private int siguiente; // Puntero al siguiente bloque (-1 si es el último)
    private boolean ocupado;

    public Bloque(int id) {
        this.id = id;
        this.siguiente = -1; // -1 indica que no hay siguiente bloque
        this.ocupado = false;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void mostrarInfo() {
        System.out.println("Bloque ID: " + id);
        System.out.println("Siguiente Bloque: " + (siguiente == -1 ? "Ninguno" : siguiente));
        System.out.println("Estado: " + (ocupado ? "Ocupado" : "Libre"));
    }
}
