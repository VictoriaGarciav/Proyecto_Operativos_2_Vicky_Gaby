/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import EDD.Lista;

public class TablaAsignacionArchivos {
    private Lista tabla;

    public TablaAsignacionArchivos() {
        this.tabla = new Lista();
    }

    public void agregarEntrada(String nombreArchivo, int cantidadBloques, int primerBloque) {
        EntradaAsignacion nuevaEntrada = new EntradaAsignacion(nombreArchivo, cantidadBloques, primerBloque);
        tabla.InsertarFinal(nuevaEntrada);
    }

    public void eliminarEntrada(String nombreArchivo) {
        for (int i = 0; i < tabla.getSize(); i++) {
            EntradaAsignacion entrada = (EntradaAsignacion) tabla.get(i);
            if (entrada.getNombreArchivo().equals(nombreArchivo)) {
                tabla.EliminarPorReferencia(entrada);
                return;
            }
        }
    }

    public void mostrarTabla() {
        System.out.println("Tabla de Asignación de Archivos:");
        for (int i = 0; i < tabla.getSize(); i++) {
            EntradaAsignacion entrada = (EntradaAsignacion) tabla.get(i);
            entrada.mostrarInfo();
            System.out.println("--------------------");
        }
    }

    // ✅ Método Getter que me pediste
    public Lista getTabla() {
        return this.tabla;
    }
}

