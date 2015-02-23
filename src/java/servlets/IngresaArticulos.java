/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.InventariosController;
import entities.RegistroArticulo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pipo
 */
public class IngresaArticulos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String dataSave = "";
            //String actions[] = request.getParameterValues("action_form");
            String[] ListaArticulos = request.getParameterValues("action_form");
            //String myJsonData = request.getParameter("strIdcategoria");
            //String categoria = request.getParameter("action_form");
            for(String est : ListaArticulos){
                dataSave = dataSave + est;
            }
            String valor = "";
            List<RegistroArticulo> listadoArticulos = new ArrayList<RegistroArticulo>();
            char metadata[]= new char[dataSave.length()];
            metadata = dataSave.toCharArray();
            int counter = 0;
            int counter2 = 0;
            int artIngresados = 0;
            String categoria = "";
            String articulo = "";
            String talla = "";
            String color = "";
            @SuppressWarnings("UseOfObsoleteCollectionType")
            Vector<String> regs = new Vector<String>();
            String msg;
            int cantidad = 0;
//            for(char md : metadata){
//                String item = String.valueOf(md);
//                if(!item.equals(",")){
//                    valor = valor + item;
//                }else if(item.equals(",")){
//                    counter2 = counter2 + 1;
//                    if(counter2 == 6){counter2 = 0;}
//                    if(counter2 == 1){categoria = valor;}
//                    if(counter2 == 2){articulo = valor;}
//                    if(counter2 == 3){talla = valor;}
//                    if(counter2 == 4){color = valor;}
//                    if(counter2 == 5){
//                        cantidad = Integer.parseInt(valor);
//                        msg = InventariosController.ingresarArticulos(categoria, articulo, talla,
//                                                                               color, cantidad);
//                        regs.add(msg);
//                    }
//                    valor = "";
//                }
//            }

//            int cantNumber = Ingteger.parseInt(cantidad);
//            String respuesta = InventariosController.ingresarArticulos(categoria, articulo, talla,
//                                                                               color, cantNumber);
//            request.getSession().setAttribute("respuesta", respuesta); 
//            response.sendRedirect("ingresarArticulo.jsp");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ingresar Articulos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='center'>");
            out.println("<h1>Resultado</h1>");
             int regsExitosos = 0;
             int regsNoHechos;
             
            for(char md : metadata){
                String item = String.valueOf(md);
                if(!item.equals(",")){
                    valor = valor + item;
                }else if(item.equals(",")){
                    counter = counter + 1;
                    if( counter == 6){ 
//                        msg = InventariosController.ingresarArticulos(categoria, articulo, talla,
//                                                                               color, cantidad);
                        //regs.add(msg);
                        counter = 0;
                    }
                    //out.println("<h1>" + valor+"  "+ counter + "</h1>");
                    if(counter == 1){
                        categoria = valor;  
//                        out.println("<h1>" + categoria + "</h1>");
                    }
                    if(counter == 2){
                        articulo = valor;
//                        out.println("<h1>" + articulo + "</h1>");
                    }
                    if(counter == 3){
                        talla = valor;
//                        out.println("<h1>" + talla + "</h1>");
                    }
                    if(counter == 4){
                        color = valor;
                        //out.println("<h1>" + color + "</h1>");
                    }
                    if(counter == 5){
                       
                        cantidad = Integer.parseInt(valor);
                         artIngresados = artIngresados + cantidad;
                        System.out.println(cantidad);
                        
//                        out.println("<h1>" + categoria + "</h1>");
//                        out.println("<h1>" + articulo + "</h1>");
//                        out.println("<h1>" + talla + "</h1>");
//                        out.println("<h1>" + color + "</h1>");
//                        out.println("<h1>" + valor + "   " +counter+ "</h1>");
                        msg = InventariosController.ingresarArticulos(categoria, articulo, talla,
                                                                               color, cantidad);
                        regs.add(msg);
                    }
                    
                    valor = "";
                }
            }
            for(String ms : regs){
                 if(ms.equals("DONE")){regsExitosos = regsExitosos + 1;}
             }
            out.println("<h3> Se ingresaron " + artIngresados + " con éxito</h3>");
            out.println("<a href='index.jsp'>Regresar a consultas</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
