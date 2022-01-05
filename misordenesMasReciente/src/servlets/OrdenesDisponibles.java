
package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorAlerta;
import gestores.GestorOrdenes;
import modelos.Config;
import modelos.Orden;

@WebServlet("/OrdenesDisponibles")
public class OrdenesDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int alertA = 0;
		int alertB = 0;
		int alertC = 0;

		try {
			
			Config configalerta = new GestorAlerta().TraerConfig();
			alertA=configalerta.getAlertA();
			alertB=configalerta.getAlertB();
			alertC=configalerta.getAlertC();
			String pzona = request.getParameter("zona");
			GestorOrdenes go = new GestorOrdenes();
			ArrayList<Orden> ordenes = go.TraerOrdenes(pzona);
			
		
			for (int i = 0; i < ordenes.size(); i++) {
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();

				String inputString1 = ordenes.get(i).getFecha_cierre();
				inputString1 = inputString1.substring(0, 19);
				Date date1 = myFormat.parse(inputString1);

				long diff = date.getTime() - date1.getTime();

				if (TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS) > alertA) {
					if (TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS) > alertB) {
						if (TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS) > alertC) {
							
							if (ordenes.get(i).getZona().equals(pzona) || pzona.equals("0")) {
								
							response.getWriter()
									.append("<div class=\"round-button col-md-1\" onclick=\"enviar("
											+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
											+ "<div class=\"round-button-circle-alertC\">"
											+ "<a class=\"round-button\">"
											
											+ ordenes.get(i).getChk() + " "
											+ ordenes.get(i).getNombre()
											+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
											+ "</a>" + "</div>" + "</div>");
							}
							
						} else {if (ordenes.get(i).getZona().equals(pzona) || pzona.equals("0")) {

							response.getWriter()
									.append("<div class=\"round-button col-md-1\" onclick=\"enviar("
											+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
											+ "<div class=\"round-button-circle-alertB\">"
											+ "<a class=\"round-button\">" 
											
											+ ordenes.get(i).getChk() + " "
											+ ordenes.get(i).getNombre() 
											+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
											+ "</a>" + "</div>" + "</div>");
						}
						}
					} else {if (ordenes.get(i).getZona().equals(pzona)|| pzona.equals("0")) {

						response.getWriter()
								.append("<div class=\"round-button col-md-1\" onclick=\"enviar("
										+ ordenes.get(i).getId() + ")\" id=\"" + ordenes.get(i).getId() + "\">"
										+ "<div class=\"round-button-circle-alertA\">" + "<a class=\"round-button\">"
										
										+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
										+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
										+ "</a>" + "</div>"
										+ "</div>");
					}
					}

				} else {if (ordenes.get(i).getZona().equals(pzona) || pzona.equals("0")) {
					response.getWriter()
							.append("<div class=\"round-button col-md-1\" onclick=\"enviar(" + ordenes.get(i).getId()
									+ ")\" id=\"" + ordenes.get(i).getId() + "\">"
									+ "<div class=\"round-button-circle\">" + "<a class=\"round-button\">"
									
									+ ordenes.get(i).getChk() + " " + ordenes.get(i).getNombre()
									+"    <br>    "+"Z"+ ordenes.get(i).getZona() 
									+ "</a>" + "</div>"
									+ "</div>");
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
