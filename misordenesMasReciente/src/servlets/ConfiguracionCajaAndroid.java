package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorCajas;
import modelos.Caja;


@WebServlet("/ConfiguracionCajaAndroid")
public class ConfiguracionCajaAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String tabletid = request.getParameter("a");
			GestorCajas gc = new GestorCajas();
			ArrayList<Caja> acajas = gc.TraerCajas();
			request.setAttribute("cajas", acajas);
			request.setAttribute("a", tabletid);
			request.getRequestDispatcher("/configCajasAndroid.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			
			Map<String, String[]> parameters = request.getParameterMap();
			for(String parameter : parameters.keySet()) {
			    if(parameter.toLowerCase().startsWith("caja")) {
			    	String[] split = parameter.split("_");
			    	String caja=split[1];
			        String[] zona = parameters.get(parameter);
			        
			      //  String partir= values.toString();
			        Caja c = new Caja();
			       if(zona != null && caja != null && zona[0] != null){
			        c.setuws(Integer.valueOf(caja));
			        c.setZona(Integer.valueOf(zona[0]));
			       
			        new GestorCajas().GuardarConfigCajas(c);
			       // System.out.print(caja);
			      //System.out.print(zona);
			       }
			        
			    }
			}
			String tabletid=request.getParameter("a");
			request.getRequestDispatcher("/tabletandroid"+tabletid.trim()+".html").forward(request, response);
		}catch(Exception e){
			response.getWriter().append(e.toString());
		}
	}

}
