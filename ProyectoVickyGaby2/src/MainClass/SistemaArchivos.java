/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;

import Functions.EntradaAsignacion;
import Functions.SimulacionDisco;
import Functions.TablaAsignacionArchivos;
import MainClass.Archivo;
import MainClass.Directorio;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


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
    public boolean crearSubdirectorio(String ruta, String nombreSubdirectorio) {
        // Primero, verificar que la ruta no esté vacía
        if (ruta == null || ruta.isEmpty() || nombreSubdirectorio == null || nombreSubdirectorio.isEmpty()) {
            return false; // Si la ruta o el nombre están vacíos, retornamos falso
        }

        // Crear el directorio en el sistema de archivos
        File directorio = new File(ruta + "/" + nombreSubdirectorio);

        // Verificar si el directorio ya existe
        if (directorio.exists()) {
            System.out.println("El subdirectorio ya existe.");
            return false; // El subdirectorio ya existe, no hacemos nada
        }

        // Intentamos crear el directorio
        boolean resultado = directorio.mkdirs();

        if (resultado) {
            System.out.println("Subdirectorio creado exitosamente en la ruta: " + ruta + "/" + nombreSubdirectorio);
            return true; // El subdirectorio fue creado con éxito
        } else {
            System.out.println("Error al crear el subdirectorio.");
            return false; // Hubo un problema al crear el subdirectorio
        }
    }
    
    public boolean existeDirectorio(String ruta) {
        File dir = new File(ruta);
        return dir.exists() && dir.isDirectory();
    }

    public boolean eliminarDirectorio(String ruta, String nombre) {
        if (!esAdministrador) {
            System.out.println("Acceso denegado. Solo el administrador puede eliminar directorios.");
            return false;
        }

        Directorio directorioPadre = buscarDirectorio(ruta);
        if (directorioPadre != null) {
            directorioPadre.eliminarSubdirectorio(nombre);
            System.out.println("Directorio eliminado exitosamente.");
            return true;
        } else {
            System.out.println("El directorio especificado no existe.");
            return false;
        }
        
    }
    
    public boolean actualizarDirectorio(String ruta, String nombreViejo, String nuevoNombre) {
        if (!esAdministrador) {
            System.out.println("Acceso denegado. Solo el administrador puede actualizar directorios.");
            return false;
        }

        Directorio directorioPadre = buscarDirectorio(ruta);
        if (directorioPadre != null) {
            // Buscar el directorio dentro del directorio padre
            Directorio directorioAActualizar = null;
            for (int i = 0; i < directorioPadre.getSubdirectorios().getSize(); i++) {
                Directorio dir = (Directorio) directorioPadre.getSubdirectorios().get(i);
                if (dir.getNombre().equals(nombreViejo)) {
                    directorioAActualizar = dir;
                    break;
                }
            }

            if (directorioAActualizar != null) {
                // Cambiar la información del directorio
                directorioAActualizar.setNombre(nuevoNombre);  // Actualizamos el nombre
                
                System.out.println("Directorio actualizado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró el directorio a actualizar.");
                return false;
            }
        } else {
            System.out.println("El directorio especificado no existe.");
            return false;
        }
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

    public Directorio buscarDirectorio(String ruta) {
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

    public Archivo obtenerArchivo(Directorio directorio, String nombreArchivo) {
        for (int i = 0; i < directorio.getArchivos().getSize(); i++) {
            Archivo archivo = (Archivo) directorio.getArchivos().get(i);
            if (archivo.getNombre().equals(nombreArchivo)) {
                return archivo;
            }
        }
        return null;
    }
    
    public boolean actualizarArchivo(String ruta, String nombreArchivo, String nuevoNombre, int nuevoTamano, String permisos) {
        Directorio directorioPadre = buscarDirectorio(ruta);  // Asegúrate de que la ruta sea correcta
        if (directorioPadre != null) {
            Archivo archivo = obtenerArchivo(directorioPadre, nombreArchivo);
            if (archivo != null) {
                archivo.setNombre(nuevoNombre);
                archivo.setTamaño(nuevoTamano);
                archivo.setPermisos(permisos);
                return true;
            } else {
                System.out.println("El archivo no se encontró para actualizar.");
                return false;
            }
        }
        return false;
    }
    public int obtenerPrimerBloque(String nombreArchivo) {
        for (int i = 0; i < tablaAsignacion.getTabla().getSize(); i++) {
            EntradaAsignacion entrada = (EntradaAsignacion) tablaAsignacion.getTabla().get(i);
            if (entrada.getNombreArchivo().equals(nombreArchivo)) {
                return entrada.getPrimerBloque();
            }
        }
        return -1; // No encontrado
    }
   
    
}
//    private void mostrarAsignacionEnJTree() {
//        DefaultTreeModel treeModel = (DefaultTreeModel) jtreeArchivos.getModel();
//        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
//
//        // Nodo raíz para la tabla de asignación
//        DefaultMutableTreeNode tablaNode = new DefaultMutableTreeNode("Tabla de Asignación");
//
//        for (int i = 0; i < sistema.getTablaAsignacion().getTabla().getSize(); i++) {
//            EntradaAsignacion entrada = (EntradaAsignacion) sistema.getTablaAsignacion().getTabla().get(i);
//
//            String infoArchivo = entrada.getNombreArchivo() + 
//                                 " | Bloques: " + entrada.getCantidadBloques() + 
//                                 " | Primer Bloque: " + entrada.getPrimerBloque();
//
//            DefaultMutableTreeNode archivoNode = new DefaultMutableTreeNode(infoArchivo);
//            tablaNode.add(archivoNode);
//        }
//
//        // Limpiar y agregar la nueva info al árbol
//        root.removeAllChildren();
//        root.add(tablaNode);
//        treeModel.reload();
//    }
//    

