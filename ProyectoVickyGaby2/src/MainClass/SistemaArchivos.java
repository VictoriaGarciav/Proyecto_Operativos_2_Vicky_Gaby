/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;

import Functions.SimulacionDisco;
import Functions.TablaAsignacionArchivos;
import MainClass.Archivo;
import MainClass.Directorio;


public class SistemaArchivos {
    private Directorio raiz;
    private SimulacionDisco disco;
    private TablaAsignacionArchivos tablaAsignacion;
    private boolean esAdministrador;

    public SistemaArchivos(int totalBloques) {
        this.raiz = new Directorio("root");
        this.disco = new SimulacionDisco(totalBloques);
        this.tablaAsignacion = new TablaAsignacionArchivos();
        this.esAdministrador = false;
    }
    
    public Directorio getRaiz() {
        return raiz;
    }

    public void setRaiz(Directorio raiz) {
        this.raiz = raiz;
    }

    public SimulacionDisco getDisco() {
        return disco;
    }

    public void setDisco(SimulacionDisco disco) {
        this.disco = disco;
    }

    public TablaAsignacionArchivos getTablaAsignacion() {
        return tablaAsignacion;
    }

    public void setTablaAsignacion(TablaAsignacionArchivos tablaAsignacion) {
        this.tablaAsignacion = tablaAsignacion;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void cambiarModo() {
        this.esAdministrador = !this.esAdministrador;
    }

    public boolean crearArchivo(String ruta, String nombre, int tamaño, String permisos) {
        if (!esAdministrador) {
            System.out.println("Acceso denegado. Solo el administrador puede crear archivos.");
            return false;
        }

        int primerBloque = disco.asignarBloques(tamaño);
        if (primerBloque == -1) {
            System.out.println("No hay espacio suficiente en el disco.");
            return false;
        }

        Archivo nuevoArchivo = new Archivo(nombre, tamaño, primerBloque, permisos);
        Directorio directorio = buscarDirectorio(ruta);

        if (directorio != null) {
            directorio.agregarArchivo(nuevoArchivo);
            tablaAsignacion.agregarEntrada(nombre, tamaño, primerBloque);
            System.out.println("Archivo creado exitosamente.");
            return true;
        } else {
            System.out.println("El directorio especificado no existe.");
            return false;
        }
    }

    public boolean eliminarArchivo(String ruta, String nombre) {
        if (!esAdministrador) {
            System.out.println("Acceso denegado. Solo el administrador puede eliminar archivos.");
            return false;
        }

        Directorio directorio = buscarDirectorio(ruta);
        if (directorio != null) {
            Archivo archivo = obtenerArchivo(directorio, nombre);
            if (archivo != null) {
                disco.liberarBloques(archivo.getBloqueInicial());
                directorio.eliminarArchivo(nombre);
                tablaAsignacion.eliminarEntrada(nombre);
                System.out.println("Archivo eliminado exitosamente.");
            } else {
                System.out.println("El archivo no existe.");
            }
        } else {
            System.out.println("El directorio especificado no existe.");
        }
        return false;
    }

    public boolean crearDirectorio(String ruta, String nombre) {
        if (!esAdministrador) {
            System.out.println("Acceso denegado. Solo el administrador puede crear directorios.");
            return false;
        }

        Directorio directorioPadre = buscarDirectorio(ruta);
        if (directorioPadre != null) {
            Directorio nuevoDirectorio = new Directorio(nombre);
            directorioPadre.agregarSubdirectorio(nuevoDirectorio);
            System.out.println("Directorio creado exitosamente.");
            return true;
        } else {
            System.out.println("El directorio especificado no existe.");
        }
        return false;
    }

    public boolean eliminarDirectorio(String ruta, String nombre, int tamaño, String permisos) {
        if (!esAdministrador) {
            System.out.println("Acceso denegado. Solo el administrador puede eliminar directorios.");
            return false;
        }

        Directorio directorioPadre = buscarDirectorio(ruta);
        if (directorioPadre != null) {
            directorioPadre.eliminarSubdirectorio(nombre);
            System.out.println("Directorio eliminado exitosamente.");
        } else {
            System.out.println("El directorio especificado no existe.");
        }
        return false;
    }

    public void mostrarEstructura() {
        raiz.mostrarEstructura();
    }

    public void mostrarTablaAsignacion() {
        tablaAsignacion.mostrarTabla();
    }

    public void mostrarEstadoDisco() {
        disco.mostrarEstado();
    }

    private Directorio buscarDirectorio(String ruta) {
        ruta = ruta.replace("[", "");
        ruta = ruta.replace("]", "");
        if (ruta.equals("root")) {
            return raiz;
        }

        String[] partes = ruta.split(",");
        Directorio actual = raiz;

        for (String parte : partes) {
            boolean encontrado = false;
            for (int i = 0; i < actual.getSubdirectorios().getSize(); i++) {
                Directorio subdir = (Directorio) actual.getSubdirectorios().get(i);
                if (subdir.getNombre().equals(parte)) {
                    actual = subdir;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return null;
            }
        }
        return actual;
    }

    private Archivo obtenerArchivo(Directorio directorio, String nombreArchivo) {
        for (int i = 0; i < directorio.getArchivos().getSize(); i++) {
            Archivo archivo = (Archivo) directorio.getArchivos().get(i);
            if (archivo.getNombre().equals(nombreArchivo)) {
                return archivo;
            }
        }
        return null;
    }
}
