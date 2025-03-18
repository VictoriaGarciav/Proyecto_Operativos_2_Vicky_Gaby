/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;


public class Archivo {
    private String nombre;
    private int tamaño; // En bloques
    private int bloqueInicial; // Puntero al primer bloque en la asignación encadenada
    private String permisos; // Puede ser "r" (solo lectura) o "rw" (lectura/escritura)

    public Archivo(String nombre, int tamaño, int bloqueInicial, String permisos) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.bloqueInicial = bloqueInicial;
        this.permisos = permisos;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getBloqueInicial() {
        return bloqueInicial;
    }

    public void setBloqueInicial(int bloqueInicial) {
        this.bloqueInicial = bloqueInicial;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public void mostrarInfo() {
        System.out.println("Archivo: " + nombre);
        System.out.println("Tamaño (bloques): " + tamaño);
        System.out.println("Bloque inicial: " + bloqueInicial);
        System.out.println("Permisos: " + permisos);
    }
}
