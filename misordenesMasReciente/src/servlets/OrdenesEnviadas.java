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

@WebServlet("/OrdenesEnviadas")
public class OrdenesEnviadas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String pzona = request.getParameter("zona");
			GestorOrdenes go = new GestorOrdenes();
			ArrayList<Orden> ordenes = go.TraerOrdenesEnviadas(pzona);
			
		
			for (int i = 0; i < ordenes.size(); i++) {
				if (i < 1 && (ordenes.get(i).getZona().equals(pzona) || pzona.equals("0")) ) {
					
					if (ordenes.get(i).getFecha_delete()!=null){
						response.getWriter()
						.append("<div class=\"round-button col-md-1\" onclick=\"volver("
								+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
								+ "<div class=\"round-button-circle-borrado\">" + "<a class=\"round-button\">"
								+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
								
								+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
								+ "</a>" + "</div>"
								+ "</div>");
					}else{
					response.getWriter()
					.append("<div class=\"round-button col-md-1\" onclick=\"volver("
							+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
							+ "<div class=\"round-button-circle-utimo\">" + "<a class=\"round-button\">"
							+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
							
							+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
							+ "</a>" + "</div>"
							+ "</div>");
					}
				} else {

					if (i < 6 && (ordenes.get(i).getZona().equals(pzona) || pzona.equals("0"))) {
						
						if (ordenes.get(i).getFecha_delete()!=null){
							response.getWriter()
							.append("<div class=\"round-button col-md-1\" onclick=\"volver("
									+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
									+ "<div class=\"round-button-circle-borrado\">" + "<a class=\"round-button\">"
									+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
									
									+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
									+ "</a>" + "</div>"
									+ "</div>");
						}else{
						response.getWriter()
								.append("<div class=\"round-button col-md-1\" onclick=\"volver("
										+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
										+ "<div class=\"round-button-circle-reciente\">" + "<a class=\"round-button\">"
										+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
										+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
										+ "</a>" + "</div>"
										+ "</div>");
						}
						} else {
						
							if (ordenes.get(i).getFecha_delete()!=null){
								response.getWriter()
								.append("<div class=\"round-button col-md-1\" onclick=\"volver("
										+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
										+ "<div class=\"round-button-circle-borrado\">" + "<a class=\"round-button\">"
										+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
										
										+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
										+ "</a>" + "</div>"
										+ "</div>");
							}else{
							response.getWriter()
								.append("<div class=\"round-button col-md-1\" onclick=\"volver("
										+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
										+ "<div class=\"round-button-circle-enviado\">" + "<a class=\"round-button\">"
										+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
										+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
										+ "</a>" + "</div>"
										+ "</div>");
							}
					}
				}
			}

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
