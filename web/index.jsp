<%-- 
    Document   : menuCategorias
    Created on : 7/12/2014, 03:13:11 PM
    Author     : pipo
--%>

<%@page import="entities.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Vector"%>
<%@page import="controller.InventariosController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	      <title>CONSULTA DE INVENTARIO</title>
        <meta name="description" content="A tutorial on how to recreate the effect of YouTube's little left side menu. The idea is to slide a little menu icon to the right side while revealing some menu item list beneath. " />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="css/default.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <script src="js/modernizr.custom.js"></script>
        <script src="js/ytmenu.js"></script>
	</head>
	<body>
		<div class="container">	
			<!-- Codrops top bar -->
			<header class="clearfix">
            <div align="center">
			<h1><br>CONSULTA DE INVENTARIO <span><br>CENTRO DE DISTRIBUCIÓN</span>	
			</h1>
             <img src="Logo.png" style="width:17%;position: relative;float: left;left: 10%; margin-top:-152px">
            <img src="logo_emtelco.png" style="width:12%;position: relative; float:right; right: 12%; margin-top:-146px ">
            </div>            
	</header>
			<div>
				<p>Por favor seleccione la categoría de los productos que desea consultar, tenga en cuenta que los productos cuyo stock sea igual a <strong>0</strong> no serán visualizados por el usuario.</p>
                </div>
    <body>
    <form method='post' action='TakerInfo'>
      <% String nodata = "No hay informacion para mostrar en esta categoría"; %>
         <div class="main" align="left">

            <div align="left" style="float:left;">
                <div align="left" class="dr-trigger"><span class="dr-icon dr-icon-menu">
                    </span><a class="dr-label">Menú de consulta</a>
                    <input type="hidden" id="categoria_seleccionada" name="selecciona_categoria">
                </div>
                
                <ul>  
                    <%
                        List<Categoria> listadoCategorias = 
                                InventariosController.getListaCategorias();
                        for(Categoria v : listadoCategorias){ 
                            %>
                            <li><button id="<%= v.getNombre() %>" style=" width: max-content; height: 20px" onclick="getName(this.id)"><%= v.getNombre() %></button></li>
                            <%
                        }
                    %>
                </ul>
                <div align="left" class="dr-trigger"><span class="dr-icon dr-icon-menu">
                    </span><a class="dr-label">Menú Administrativo</a>
                    <input type="hidden" id="action_form" name="action_form">
                </div>
                <ul>  
                    <li>
                        <button id="ingreso_art" name="ingreso_art" style=" width: 200px; height: 22px" onclick="getName(this.id)"><%= "Ingresar artículo" %></button>
                    </li>
                    
                </ul>
            </div>
            <div id="datosinventario" style="float:right; border-style: double; width: 800px;">
                <%
                    //String accion = (String)session.getAttribute("action_answer");
                    String categoria = (String)session.getAttribute("categoria"); 
                    Vector<String> listaArticulosPorCategoria = 
                           InventariosController.getListaArticulosPorCategoria(categoria);
                    if(categoria != null && listaArticulosPorCategoria != null){
                        %>
                        <table style="width: 800px;">
                                <thead>
                                <tr>
                                  <th colspan="13" align="center"><%= categoria %></th>
                                     
                                </tr>
                                </thead>
                                <tbody align="left" style=" width: 800px;" >
                                    <%
                                        int cantTotalTalla = 0;
                                        for(String articulo : listaArticulosPorCategoria){
                                            %>
                                            <tr>
                                                <th align="center" valign="middle" rowspan="2" style=" border-color: #000; border-style: inset">
                                                    <strong><%= articulo %></strong>
                                                </th>
                                                <th align="center" style=" border-color: #555; border-style: solid">TALLAS</th> 
                                                <%
                                                    List<String> lista_tallas = InventariosController.getTallasPorArticulo(articulo);
                                                    for(String li : lista_tallas){
                                                        %>
                                                            <th align="center" style=" border-color: #555; border-style: solid">
                                                                <strong><%= li %></strong>
                                                            </th>
                                                        <%
                                                    }
                                                %> 
                                            </tr>
                                            <tr> 
                                                <th align="center" style="width: 100px; border-color: #678889; border-style: solid">CANTIDAD</th>
                                                <%
                                                    for(String li : lista_tallas){
                                                        int cantTalla = InventariosController.getNumeroTallasPorArticuloYCategoria(li, categoria, articulo);
                                                        cantTotalTalla = cantTotalTalla + cantTalla;
                                                        %>
                                                            <td align="center" style=" border-color: #678889; border-style: solid"><%= cantTalla %></td>
                                                        <%
                                                    }
                                                %>
                                                <th align="center">TOTAL</th>
                                                <th align="center" style="width: 50px; border-color: #000; border-style: ridge"><%= cantTotalTalla %></th>
                                                
                                            </tr>
                                            
                                            <%
                                            cantTotalTalla = 0;
                                        }
                                    %>
                                 
                                 </tbody>
                                 
                            </table>
                                                
                        <%

                    }else{
                        %>
                            <tr>
                              <th colspan="13" align="center"><div><%= "No hay datos para mostrar" %></div></th>
                            </tr>
                        <%
                    }
                %>
            </div>
            
            </div>

        <script text>
            function getName(id) {
                var idC = document.getElementById("action_form");
                idC.value = id;
                var templateInfo = '';
                templateInfo = templateInfo + '<div style="float: left;" align="center">'+id+'</div>'
                                            + '<div style="float: right;">TOTAL</div>';
                <% 
                       // String categoria = request.getParameter("selecciona_categoria");
                       // System.out.println(request.getParameter("selecciona_categoria"));

                        //Vector<String> listaArticulosPorCategoria = 
                          //      InventariosController.getListaArticulosPorCategoria("");
                        //System.out.println(categoria);
                %>
                var capa = document.getElementById("datosinventario").innerHTML = templateInfo;

            }
        </script>
<!-- /container -->
    </body>	
    </form>
</html>
