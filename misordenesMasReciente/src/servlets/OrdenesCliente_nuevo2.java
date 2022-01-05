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

@WebServlet("/OrdenesCliente_nuevo2")
public class OrdenesCliente_nuevo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			GestorOrdenes go = new GestorOrdenes();
			ArrayList<Orden> ordenesEnviadas = go.TraerOrdenesEnviadas("0");
			ArrayList<Orden> ordenesDisponibles = go.TraerOrdeneslifo("0");

			String ze = "";
			String ce = "";
			String co = "";
			String zo = "";
			String mytabla = "";

			for (int j = 0; j < 6; j++) {

				if (!ordenesEnviadas.isEmpty()) {
					if (ordenesEnviadas.size() > j && ordenesEnviadas.get(j).getZona() != null) {
						if (j == 0) {

							ze = "<td class='tdnanimado text-center nuevotd'> <span class='animacion'>"
									+ ordenesEnviadas.get(j).getZona() + "</span></td>";

							if (ordenesEnviadas.get(j).getNombre() != null) {
								
								if (!ordenesEnviadas.get(j).getNombre().matches(".*\\d.*")) { //chequeo que No sea numerico
									
									if (ordenesEnviadas.get(j).getNombre().length() > 10) {
										ce = "<td class='tdnanimado nuevotd orden'> <span class='animacion'>"
												+ ordenesEnviadas.get(j).getChk() + " "
												+ ordenesEnviadas.get(j).getNombre().substring(0, 10) + "</span></td>";
									} else {
										ce = "<td class='tdnanimado nuevotd orden'> <span class='animacion'>"
												+ ordenesEnviadas.get(j).getChk() + " "
												+ ordenesEnviadas.get(j).getNombre() + "</span></td>";
									}
								} else {
									ce = "<td class='tdnanimado nuevotd orden'> <span class='animacion'>"
											+ ordenesEnviadas.get(j).getChk() + " " + "</span></td>";
								}

							} else {
								ce = "<td class='tdnanimado nuevotd orden'> <span class='animacion'>"
										+ ordenesEnviadas.get(j).getChk() + " " + "</span></td>";
							}
						} else {
							ze = "<td class='text-center nuevotd'>" + ordenesEnviadas.get(j).getZona() + "</td>";
							if (ordenesEnviadas.get(j).getNombre() != null) {
								if (!ordenesEnviadas.get(j).getNombre().matches(".*\\d.*")) { //chequeo que No sea numerico
									if (ordenesEnviadas.get(j).getNombre().length() > 10) {
										ce = "<td class='nuevotd orden'>" + ordenesEnviadas.get(j).getChk() + " "
												+ ordenesEnviadas.get(j).getNombre().substring(0, 10) + "</td>";
									} else {
										ce = "<td class='nuevotd orden'>" + ordenesEnviadas.get(j).getChk() + " "
												+ ordenesEnviadas.get(j).getNombre() + "</td>";
									}
								} else {
									ce = "<td class='nuevotd orden'>" + ordenesEnviadas.get(j).getChk() + " "
											+  "</td>";
								}
							} else {
								ce = "<td class='nuevotd orden'>" + ordenesEnviadas.get(j).getChk() + " " + "</td>";
							}
						}

					} else {
						ze = "<td class='  nuevotd'></td>";
						ce = "<td class=' nuevotd'></td>";
					}

				}
				else {
					ze = "<td class='  nuevotd'></td>";
					ce = "<td class=' nuevotd'></td>";
				}
				if (!ordenesDisponibles.isEmpty()) {
					if (ordenesDisponibles.size() > j && ordenesDisponibles.get(j).getZona() != null) {
						zo = "<td class=' text-center nuevotd'>" + ordenesDisponibles.get(j).getZona() + "</td>";
						if (ordenesDisponibles.get(j).getNombre() != null) {

							if (!ordenesDisponibles.get(j).getNombre().matches(".*\\d.*")) { //chequeo que No sea numerico
								if (ordenesDisponibles.get(j).getNombre().length() > 10) {
									co = "<td class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + " "
											+ ordenesDisponibles.get(j).getNombre().substring(0, 10) + "</td>";
								} else {
									co = "<td class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + " "
											+ ordenesDisponibles.get(j).getNombre() + "</td>";
								}
							} else {
								co = "<td class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + " " + "</td>";
							}

						} else {
							co = "<td class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + " " + "</td>";
						}
					} else {
						zo = "<td class=' text-center nuevotd'></td>";
						co = "<td class='textt-center nuevotd'></td>";
					}

				}else{
					zo = "<td class=' text-center nuevotd'></td>";
					co = "<td class='textt-center nuevotd'></td>";
				}

				mytabla += "<tr>" + zo + co + ze + ce + "</tr>";

			}

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
