package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorLCDs;
import modelos.ConfigDisp;
import modelos.LCD;


@WebServlet("/ConfiguracionLCD")
public class ConfiguracionLCD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String tabletid = request.getParameter("a");
			GestorLCDs gl = new GestorLCDs();
			ArrayList<ConfigDisp> alcds = gl.TraerConfigLCDs();
			request.setAttribute("lcds", alcds);
			request.setAttribute("a", tabletid);
			request.getRequestDispatcher("/configLCDs.jsp").forward(request, response);
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
             GestorLCDs gl = new GestorLCDs();
            
            while (paramNames.hasMoreElements()) {
                  // leo el nombre del atributo
                  String paramName = (String) paramNames.nextElement();
                  // si el atributo empieza con asistencia, busco el id del empleado
                  if (paramName.contains("LCD_")) {
                    String lcd_id = paramName.split("_")[1];
                   
                        String[] paramValue = request.getParameterValues(paramName);
                    
                        
                        
                        // si no es vacio, grabo segun opcion seleccionada
                        //  String partir= values.toString();
    			        LCD l = new LCD();
    			       
    			        l.setId_lcd(Integer.valueOf(lcd_id));
    			        gl.DeleteConfigLCD(Integer.valueOf(lcd_id));
    			        for (String x : paramValue) {
    			        	System.out.println( lcd_id + "=" + x);
    			        	l.setZona(Integer.valueOf(x));
    			        	gl.InsertConfigLCD(l);
    			        }
    			       // System.out.print(caja);
    			      //System.out.print(zona);
    			       }
                        
                  }
            

			        
			 
			        
			

        	String tabletid=request.getParameter("a");
			request.getRequestDispatcher("/tablet"+tabletid.trim()+".html").forward(request, response);
		}catch(Exception e){
			response.getWriter().append(e.toString());
		}
	}

}
