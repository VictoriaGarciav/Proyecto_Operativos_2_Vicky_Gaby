/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;


public class EntradaAsignacion {
    private String nombreArchivo;
    private int cantidadBloques;
    private int primerBloque;

    public EntradaAsignacion(String nombreArchivo, int cantidadBloques, int primerBloque) {
        this.nombreArchivo = nombreArchivo;
        this.cantidadBloques = cantidadBloques;
        this.primerBloque = primerBloque;
    }

    // Getters y Setters
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getCantidadBloques() {
        return cantidadBloques;
    }

    public void setCantidadBloques(int cantidadBloques) {
        this.cantidadBloques = cantidadBloques;
    }

    public int getPrimerBloque() {
        return primerBloque;
    }

    public void setPrimerBloque(int primerBloque) {
        this.primerBloque = primerBloque;
    }

    public void mostrarInfo() {
        System.out.println("Archivo: " + nombreArchivo);
        System.out.println("Bloques asignados: " + cantidadBloques);
        System.out.println("Primer Bloque: " + primerBloque);
    }
}
