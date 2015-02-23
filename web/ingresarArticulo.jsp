<%-- 
    Document   : ingresarArticulo
    Created on : 12/12/2014, 06:33:29 AM
    Author     : pipo
--%>
<%@page import="entities.Articulo"%>
<%@page import="java.sql.Array"%>
<%@page import="entities.RegistroArticulo"%>
<%@page import="constantes.Constantes"%>
<%@page import="entities.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.InventariosController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<RegistroArticulo> articulos = new ArrayList<RegistroArticulo>(); 
%>
<html lang='en'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>.:Ingresar Articulo:.</title>
<!--Aqui hacemos referencia a los estilos que usaremos y a nuestra hoja de estilos style.css-->
<!-- <link rel="stylesheet" type="text/css" href="style2.css" /> -->
<script language="javascript" src="js/validation.js"></script>
<!--Instanciamos la etiqueta script para llamar a nuestro lenguaje funcional y el documento de validación-->

</head>
<body>
<div id="wrapper">
<!--Formulario de Logeo, en el submit, ejecuta metodo de validación y actua enviando a pagina execute.php -->
<form name="ingrarts" method='post' class="login-form" action='IngresaArticulos' onsubmit="return validarForm(this)">
  <div class="header">
    <h1 align="center"><img src="images/Logo.png" width="117" height="100"> <img src="images/logo_emtelco.png" width="100" height="102" style="margin-left:40px"><br>
        <label id="labeltitulo">Modificar Articulo</label></h1>
      <div>
          Seleccione una opcion
        <select name="accionModificar" id="accionModificar" onchange="cambiarAccion()" >  
            <option value="Seleccione">Seleccione</option>
            <option value="ingresar">Ingresar articulo</option>
            <option value="retirar">Retirar Articulo</option>
        </select>
        <input type="hidden" id="actionModicar" name="actionModicar">
        <label id="labelaction"></label>
      </div>
    <div align="center"><span >Por favor complete los campos (*) obligatorios para ingresar un articulo al sistema.</span></div>
  </div>
  <div class="content" align="center">
    
    <table id="addArts" align="center">
    <thead>
        <tr>
            <th align="center"> <label align="center">CATEGOR&Iacute;AS</label>
                <select name="categ" id="selectCategorias" onchange="mostrarArticulos()" name="categoria_seleccionada" >  
                </select>
            </th>
            <th align="center"> <label align="center">ART&Iacute;CULOS</label>
                <select id="selectNombreArt" name="selectNombreArt" style=" width: 250px;" onchange="mostrarTallas()">
                </select>
            </th>
            <th align="center"> <label align="center">TALLA</label>
                <input type="hidden" id="action_form" name="action_form">
                <select id="c_tallas" name="c_tallas" style=" width: 250px;">
                </select>
            </th>
            <th align="center"> <label>COLOR</label>
                <select id="color_seleccionado" name="color_seleccionado" style=" width: 250px;"> 
                </select>
            </th>
            <th align="center"> <label>Cantidad a ingresar</label>
                <input id="quantity" type="number" name="quantity" min="1" ></input>
            </th>
            <th align="center"> <input type="button"  onclick="agregarArticulo()" id="agregar" value="Agregar fila" />
            </th>
        </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>
  <div class="footer">
  <button class="button2">Cancelar</button>
  <button class="button" onclick="recogerDatos()">Guardar</button>
    
  </div>
</form>
</div>
<div class="gradient"></div>
</body>
<script src="jquery-1.11.1.js" type="text/javascript" ></script>

<script type="text/javascript">
        var guardarRegistro = false;
        $(document).ready(function() {
            $("#selectCategorias").load("CargadorCategorias");
            mostrarArticulos();
            mostrarTallas();
            mostrarColores();
            
        });  
        $(function(){
               
                // Evento que selecciona la fila y la elimina 
                $(document).on("click",".eliminar",function(){
                        var parent = $(this).parents().get(0);
                        $(parent).remove();
                });
        });
        function mostrarArticulos(){
            var idCategoria = $("#selectCategorias").val();
            $("#selectNombreArt").load("CargadorArticulos",{strIdcategoria: idCategoria});
        }
        function mostrarTallas(){
            var idArticulo = $("#selectNombreArt").val();
            $("#c_tallas").load("CargadorTallas",{strIdArticulo: idArticulo});
        }
        function mostrarColores(){
            $("#color_seleccionado").load("CargadorColores");
        }
        function validarForm(form){
            if(guardarRegistro === true){
                
            }else{
                alert('Ingrese por lo menos un registro');
                return false;
            }
            
        }
       
        function agregarArticulo(){
            var indiceCateg = document.getElementById("selectCategorias").options.selectedIndex;
            var categoria = document.getElementById("selectCategorias").options[indiceCateg].text;
            
            var indiceArt = document.getElementById("selectNombreArt").options.selectedIndex;
            var articulo = document.getElementById("selectNombreArt").options[indiceArt].text;
            
            var indiceTalla = document.getElementById("c_tallas").options.selectedIndex;
            var talla = document.getElementById("c_tallas").options[indiceTalla].text;
            
            var indiceColor = document.getElementById("color_seleccionado").options.selectedIndex;
            var color = document.getElementById("color_seleccionado").options[indiceColor].text;
            
            var cantidad = document.getElementById("quantity").value;
            
            if(categoria === "Seleccione"){
                alert('Seleccione una categoría');
            }else if(articulo === "Seleccione"){
                alert('Seleccione un artículo');
            }else if(talla === "Seleccione"){
                alert('Seleccione una talla');
            }else if(color === "Seleccione"){
                alert('Seleccione un color');
            }else if(cantidad <= 0){
                alert('Ingrese una cantidad');
            }
            else{
                var fila = '<tr>'+
                    '<td>'+categoria+'</td>'+
                    '<td>'+articulo+'</td>'+
                    '<td>'+talla+'</td>'+
                    '<td>'+color+'</td>'+
                    '<td>'+cantidad+'</td>'+
                    '<td class="eliminar"><input type="button" value="Quitar"></input></td>'
                    +'</tr>';
                $('#addArts tr:last').after(fila); 
                guardarRegistro = true;    
            }       
        }
        function recogerDatos(){
            //alert('se recojen datos');
            var c1 = 0;
            var c2 = 0;
            var json = new Array();
            var items = "";
            $("#addArts td").each(function(){
                var valor=$(this).eq(0).html();
                if(c1 < 5){
                    json[c2] = valor;
                    items = items + valor;
                    c1 = c1 + 1;
                    c2 = c2 + 1;
                }else{
                    
                    c1 = 0;
                }
                items = items + ",";
                //alert(valor);
            });
            var idC = document.getElementById("action_form");
            idC.value = items;
            
            for(i=0;i<json.length;i++){
                //alert(json[i]);
            }
            //alert('se envian datos recogidos');
        }
        function cambiarAccion(){
            var indiceModificar = document.getElementById("accionModificar").options.selectedIndex;
            var opcionModificar = document.getElementById("accionModificar").options[indiceModificar].text;
            document.getElementById("labeltitulo").innerHTML = opcionModificar;
            document.getElementById("actionModicar").innerHTML = opcionModificar;
        }
    </script>
</html>
