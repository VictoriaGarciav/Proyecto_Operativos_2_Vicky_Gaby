/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;

import EDD.Lista;


public class Directorio {
    private String nombre;
    private Lista archivos; // Lista de archivos en el directorio
    private Lista subdirectorios; // Lista de subdirectorios

    public Directorio(String nombre) {
        this.nombre = nombre;
        this.archivos = new Lista();
        this.subdirectorios = new Lista();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getArchivos() {
        return archivos;
    }

    public Lista getSubdirectorios() {
        return subdirectorios;
    }

    // Métodos para manipular archivos y subdirectorios
    public void agregarArchivo(Archivo archivo) {
        archivos.InsertarFinal(archivo);
    }

    public void eliminarArchivo(String nombreArchivo) {
        for (int i = 0; i < archivos.getSize(); i++) {
            Archivo archivo = (Archivo) archivos.get(i);
            if (archivo.getNombre().equals(nombreArchivo)) {
                archivos.EliminarPorReferencia(archivo);
                return;
            }
        }
    }

    public void agregarSubdirectorio(Directorio dir) {
        subdirectorios.InsertarFinal(dir);
    }

    public boolean eliminarSubdirectorio(String nombreSubdir) {
        for (int i = 0; i < subdirectorios.getSize(); i++) {
            Directorio dir = (Directorio) subdirectorios.get(i);
            if (dir.getNombre().equals(nombreSubdir)) {
                subdirectorios.EliminarPorReferencia(dir);
                return true;
            }
        }
        return false;
    }

    public void mostrarEstructura() {
        System.out.println("Directorio: " + nombre);
        System.out.println("Archivos:");
        archivos.mostrar();
        System.out.println("Subdirectorios:");
        subdirectorios.mostrar();
    }
}
