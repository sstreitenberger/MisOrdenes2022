package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorOrdenes;


@WebServlet("/VolverOrden")
public class VolverOrden extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		GestorOrdenes go = new GestorOrdenes();
		try {
			boolean elimina;
		
			
			go.VolverOrden(Integer.valueOf((request.getParameter("id"))));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}


