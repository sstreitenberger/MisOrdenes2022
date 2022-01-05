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

@WebServlet("/OrdenesCliente")
public class OrdenesCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String pzona = request.getParameter("zona");
			GestorOrdenes go = new GestorOrdenes();
			ArrayList<Orden> ordenes = go.TraerOrdenesCliente(pzona);

	

			response.getWriter().append("<div class=\"col-md-4\">");
			for (int i = 0; i < ordenes.size(); i++) {
				if (i != 0) {
					if (ordenes.get(i).getZona().equals(pzona)) {
						response.getWriter()
								.append("<div class=\"row\">" + "<label class=\"id_recientes\">"
										+ ordenes.get(i).getChk() + "</label>" + "<label  class=\"nombre_recientes\" >"
										+ ordenes.get(i).getNombre() 
										
										+ "</label>" + "</div>");

					}
				}

			}
			if (ordenes.get(0).getZona().equals(pzona)) {
				response.getWriter().append("</div>");

				response.getWriter()
						.append("<div class=\"col-md-4\" >" + "<div class=\"row\">" + "<label class=\"id_actual\">"
								+ ordenes.get(0).getChk() + "</label>" + "</div>" + "<div class=\"row\">"
								+ "<label class=\"nombre_actual\" >" + ordenes.get(0).getNombre() 
								
								+ "</label>"
								+ "</div>" + "</div>");

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
