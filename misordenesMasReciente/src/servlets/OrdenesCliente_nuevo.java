package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorOrdenes;
import modelos.Orden;

@WebServlet("/OrdenesCliente_nuevo")
public class OrdenesCliente_nuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
		
			GestorOrdenes go = new GestorOrdenes();
			ArrayList<Orden> ordenes = go.TraerEnviadasyDisponibles();

		
			
			String ze="";
			String ce="";
			String co="";
			String zo="";
			
				
				for (int j = 0; j < ordenes.size(); j++) {
					if (ordenes.get(j).getChk_seq()==1) { //pregunto por 1 por que son las enviadas
						//response.getWriter().append( ordenes.get(j).getZona() +"<br>");
						if(j==0){
							ze+= "<div class='divzanimado'><p class='animacion'>"+ ordenes.get(j).getZona() +"<br></p></div>";
							ce+="<div class='divnanimado'><p class='animacion'>"+ordenes.get(j).getChk() +"<br></p></div>";
						}else{
						ze+=ordenes.get(j).getZona() +"<br>";
						ce+=ordenes.get(j).getChk() +"<br>";
						}
								/*.append("<div class=\"row\">" + "<label class=\"id_recientes\">    animated infinite pulse "
										+ ordenes.get(i).getChk() + "</label>" + "<label  class=\"nombre_recientes\" >"
										+ ordenes.get(i).getNombre() 
										
										+ "</label>" + "</div>");*/
						
						
					} 
					else{
						
						zo+=ordenes.get(j).getZona() +"<br>";
						co+=ordenes.get(j).getChk() +"<br>";
						/*.append("<div class=\"row\">" + "<label class=\"id_recientes\">"
								+ ordenes.get(i).getChk() + "</label>" + "<label  class=\"nombre_recientes\" >"
								+ ordenes.get(i).getNombre() 
								
								+ "</label>" + "</div>");*/
						
					}
				}
		
				
				String mytabla ="<td id=\"zo\">"+zo+"</td> <td id=\"co\">"+co+" </td> <td id=\"ze\" >"+ze+" </td> <td id=\"ce\" >"+ce+"</td>";
			
				response.getWriter().append(mytabla);
			
		

	
		

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("error", "Servlet no permite POST");
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

}
