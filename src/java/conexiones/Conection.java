/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entities.Articulo;
import entities.Categoria;
import entities.Color;
import entities.Talla;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pipo
 */
public class Conection {
    public static Connection conexion;
    Statement sentencia;
    
    public static void getControlador(){
        try{
            String controlador="com.mysql.jdbc.Driver";
            Class.forName (controlador).newInstance();
            //System.out.print("Controlador cargado exitosamente");
        }
        catch (Exception e) {
            System.out.print("Error al cargar el Controlador");
        }
    }
    
    public static void getConection(){
        try {
            String DSN="jdbc:mysql://127.2.49.130;3306/cedi";
            String user="adminjKuXw67";
            String password="KmMkqTyj5Ktq";
            conexion=(Connection) DriverManager.getConnection(DSN,user,password);
            //System.out.print("Conexion exitosa");
        } catch (SQLException ex) {
            System.out.print("Error al cargar la conexion");
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static List<Categoria> getCategorias(){
        getControlador();
        getConection();
        List<Categoria> categorias = new ArrayList<Categoria>();
        Categoria categoria;
        String query = "SELECT * FROM CATEGORIAS";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre_categoria = rs.getString("nombre");
                categoria = new Categoria();
                categoria.setId(id);
                categoria.setNombre(nombre_categoria);
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }
    
    public static List<String> getListadoArticulos(){
        getControlador();
        getConection();
        @SuppressWarnings("Convert2Diamond")
        List<String> articulos = new ArrayList<String>();
        String query = "select * from mae_articulo";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               String categoria = rs.getString("nombre");
               articulos.add(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articulos;
    }
    
    public static List<Color> getListadoColores(){
        getControlador();
        getConection();
        List<Color> colores = new ArrayList<Color>();
        String query = "select * from mae_color";
        Color color;
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               String nombre = rs.getString("nombre");
               int id_color = rs.getInt("id");
               color = new Color();
               color.setId(id_color);
               color.setNombre(nombre);
               colores.add(color);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colores;
    }
    
    public static List<String> getListadotallas(){
        getControlador();
        getConection();
        @SuppressWarnings("Convert2Diamond")
        List<String> tallas = new ArrayList<String>();
        String query = "select * from mae_talla";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               String color = rs.getString("nombre");
               tallas.add(color);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tallas;
    }
    
    public static int devuelve_Id_Categoria(String categoria){
        getControlador();
        getConection();
        int id_categoria = 0;
        String query = "SELECT * FROM CATEGORIAS WHERE NOMBRE='"+categoria+"'";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               id_categoria = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_categoria;
    }
    
    public static int devuelve_Id_Color(String color){
        getControlador();
        getConection();
        int id_color = 0;
        String query = "SELECT * FROM MAE_COLOR WHERE NOMBRE='"+color+"'";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               id_color = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_color;
    }
    
    public static int devuelve_Id_Talla(String talla){
        getControlador();
        getConection();
        int id_talla = 0;
        String query = "SELECT * FROM MAE_TALLA WHERE NOMBRE='"+talla+"'";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               id_talla = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_talla;
    }
    
    public static int devuelve_Id_Mae_Articulo(String mae_articulo){
        getControlador();
        getConection();
        int id_mae_articulo = 0;
        String query = "SELECT * FROM MAE_ARTICULO WHERE NOMBRE='"+mae_articulo+"'";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               id_mae_articulo = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_mae_articulo;
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static Vector<String> getListaArticulosPorCategoria(String categoria){
        getControlador();
        getConection();
        @SuppressWarnings("UseOfObsoleteCollectionType")
        Vector<String> inventarios = new Vector<>();
        int id_categoria = devuelve_Id_Categoria(categoria);
        String query =  "select ma.nombre " +
                        "from articulos_por_categoria ac, mae_articulo ma, categorias ca " +
                        "where " +
                        "ca.id = ac.id_categorias and " +
                        "ma.id = ac.id_mae_articulo and " +
                        "ca.id = "+id_categoria+";";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               String nombre_categoria = rs.getString("nombre");
               inventarios.add(nombre_categoria);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventarios;
    }
    
    public static int getListaInventariosCategoriayTalla(String talla, String categoria, String mae_art){
        getControlador();
        getConection();
        int rowCount = 0;
        System.out.println("Lista inventario cantidades");
        System.out.println(talla);
        System.out.println(categoria);
        System.out.println(mae_art);
        int id_talla = devuelve_Id_Talla(talla);
        int id_categoria = devuelve_Id_Categoria(categoria);
        int id_mae_art = devuelve_Id_Mae_Articulo(mae_art);
        String query =  "select count(*) " +
                        "from articulos_detalle ad, mae_articulo ma, categorias ca, mae_talla mt " +
                        "where " +
                        "ma.id = ad.id_mae_articulo " +
                        "and ca.id = ad.id_categorias " +
                        "and mt.id = ad.id_talla " +
                        "and ad.id_mae_articulo = "+id_mae_art+" " +
                        "and ad.id_categorias = "+id_categoria+" " +
                        "and ad.id_talla = "+id_talla+";";
        try {
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            rowCount = rs.getInt(1); 
            rs.close();
            st.close();
//            System.out.println("numero de registros encontrados: "+rowCount);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("cantidad total por filtro: "+rowCount);
        System.out.println("");
        return rowCount;
    }
    
    public static List<String> get_maestro_tallas_por_articulo(String nombre_articulo){
        getControlador();
        getConection();
        int id_mae_articulo = devuelve_Id_Mae_Articulo(nombre_articulo);
        @SuppressWarnings("Convert2Diamond")
        List<String> lista_tallas = new ArrayList<String>();
        String query =  "select mt.nombre " +
                        "from mae_talla mt, mae_articulo ma " +
                        "where ma.id_tipo_talla = mt.id_tipo_talla and " +
                        "ma.id = "+id_mae_articulo+";";
        
        try {
            try (Statement st = (Statement) conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
                while(rs.next()){
                    String nombre_talla = rs.getString("nombre");
                    lista_tallas.add(nombre_talla);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return lista_tallas;
    } 
    
    public int getListadoFiltroGeneral(){
        return 0;
    }
    
    public static String ingresarArticulos(String categoria, String articulo, String talla,
                                                             String color, int cantidad){
        getControlador();
        getConection();
        String MSG_RESPONSE = "";
        int id_categoria = devuelve_Id_Categoria(categoria);
        int id_articulo = devuelve_Id_Mae_Articulo(articulo);
        int id_talla = devuelve_Id_Talla(talla);
        int id_color = devuelve_Id_Color(color);
        String query =  "INSERT INTO articulos_detalle VALUES(1, "+id_articulo+", "
                + ""+id_categoria+", "+id_color+", "+id_talla+")";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(query);
            for(int i=0;i<cantidad;i++){
                pst.executeUpdate();
            }
            MSG_RESPONSE = "DONE";
        } catch (SQLException ex) {
            MSG_RESPONSE = "No se pudo ejecutar operacion";
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MSG_RESPONSE;
    }
//    public static void main(String[]args){
////        getControlador();
////        getConection();
//        getListaInventariosCategoriayTalla(2,1,1);
//    }
    
    /**
     *
     * @param id_categoria
     * @return
     */
    public static List<Articulo> getListaArticulos(int id_categoria){
        getControlador();
        getConection();
        List<Articulo> lista_articulos = new ArrayList<Articulo>();
        //int id_categoria = devuelve_Id_Categoria(categoria);
        String query =  "select ma.nombre, ma.id " +
                        "from articulos_por_categoria ac, mae_articulo ma, categorias ca " +
                        "where " +
                        "ca.id = ac.id_categorias and " +
                        "ma.id = ac.id_mae_articulo and " +
                        "ca.id = "+id_categoria+";";
        try {
            try (Statement st = (Statement) conexion.createStatement(); 
                          ResultSet rs = st.executeQuery(query)) {
                while(rs.next()){
                    String nombre_articulo = rs.getString("nombre");
                    int id_articulo = rs.getInt("id");
                    Articulo articulo = new Articulo();
                    articulo.setId(id_articulo);
                    articulo.setNombre(nombre_articulo);
                    lista_articulos.add(articulo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_articulos;
    }
    
    public static List<Talla> getListaTallas(int id_mae_articulo){
        getControlador();
        getConection();
        List<Talla> lista_tallas = new ArrayList<Talla>();
        String query =  "select mt.nombre, mt.id " +
                        "from mae_talla mt, mae_articulo ma " +
                        "where ma.id_tipo_talla = mt.id_tipo_talla and " +
                        "ma.id = "+id_mae_articulo+";";
        Talla talla;
        try {
            try (Statement st = (Statement) conexion.createStatement(); ResultSet rs = st.executeQuery(query)) {
                while(rs.next()){
                    String nombre_talla = rs.getString("nombre");
                    int id_talla = rs.getInt("id");
                    talla = new Talla();
                    talla.setId(id_talla);
                    talla.setNombre(nombre_talla);
                    lista_tallas.add(talla);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return lista_tallas;
    } 
}
