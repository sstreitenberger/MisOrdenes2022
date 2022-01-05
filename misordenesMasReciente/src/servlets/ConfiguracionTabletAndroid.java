package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorTablets;
import modelos.ConfigDisp;
import modelos.Tablet;


@WebServlet("/ConfiguracionTabletAndroid")
public class ConfiguracionTabletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String tabletid = request.getParameter("a");
			GestorTablets gt = new GestorTablets();
			ArrayList<ConfigDisp> atablet = gt.TraerConfigTablets();
			request.setAttribute("tablet", atablet);
			request.setAttribute("a", tabletid);
			request.getRequestDispatcher("/configTabletsAndroid.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			
			
			// leo todos los parametros del request
            Enumeration<?> paramNames = request.getParameterNames();
            // mientras exista elements
             GestorTablets gt = new GestorTablets();
            
            while (paramNames.hasMoreElements()) {
                  // leo el nombre del atributo
                  String paramName = (String) paramNames.nextElement();
                  // si el atributo empieza con asistencia, busco el id del empleado
                  if (paramName.contains("tablet_")) {
                    String tablet_id = paramName.split("_")[1];
                   
                        String[] paramValue = request.getParameterValues(paramName);
                    
                        
                        
                        // si no es vacio, grabo segun opcion seleccionada
                        //  String partir= values.toString();
    			        Tablet t = new Tablet();
    			       
    			        t.setId_Tablet(Integer.valueOf(tablet_id));
    			        gt.DeleteConfigTablets(Integer.valueOf(tablet_id));
    			        for (String x : paramValue) {
    			        	System.out.println( tablet_id + "=" + x);
    			        	t.setZona(Integer.valueOf(x));
    			        	gt.InsertConfigTablets(t);
    			        }
    			       // System.out.print(caja);
    			      //System.out.print(zona);
    			       }
                        
                  }
            

			        
			 
			        
			

        	String tabletid=request.getParameter("a");
			request.getRequestDispatcher("/tabletandroid"+tabletid.trim()+".html").forward(request, response);
		}catch(Exception e){
			response.getWriter().append(e.toString());
		}
	}

}
