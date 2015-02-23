/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexiones.Conection;
import entities.Articulo;
import entities.Categoria;
import entities.Color;
import entities.Talla;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author pipo
 */
public class InventariosController {

    public static String ingresarArticulos(String categoria, String articulo, String talla,
                                                             String color, int cantidad) {
        return Conection.ingresarArticulos(categoria, articulo, talla, color, cantidad);
    }
    
    String parametro;
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public static List<Categoria> getListaCategorias(){
        return Conection.getCategorias();
    }
    
    public static List<Talla> getListaTallas(int idMaeArticulo){
        return Conection.getListaTallas(idMaeArticulo);
    }
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public static Vector getListaArticulosPorCategoria(String categoria){
        return Conection.getListaArticulosPorCategoria(categoria);
    }
    
    public static List<Articulo> getListaArticulos(int id_categoria){
        return Conection.getListaArticulos(id_categoria);
    }
    
    public static List<String> getTallasPorArticulo(String nombre_articulo){
      return Conection.get_maestro_tallas_por_articulo(nombre_articulo);
    }
    
    public static int getNumeroTallasPorArticuloYCategoria(String n_talla, String n_categoria, String n_articulo){
      return Conection.getListaInventariosCategoriayTalla(n_talla, n_categoria, n_articulo);
    }
    
    public static List<String> obtenerlistadoArticulos(){
        return Conection.getListadoArticulos();
    }
    
    public static List<String> getListadotallas(){
        return Conection.getListadotallas();
    }
    
    public static List<Color> getListadoColores(){
        return Conection.getListadoColores();
    }
}
