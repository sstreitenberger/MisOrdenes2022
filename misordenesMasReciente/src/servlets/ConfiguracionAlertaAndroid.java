package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorAlerta;
import modelos.Config;


@WebServlet("/ConfiguracionAlertaAndroid")
public class ConfiguracionAlertaAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String tabletid = request.getParameter("a");
			GestorAlerta gc = new GestorAlerta();
			Config aconfig = gc.TraerConfig();
			request.setAttribute("config", aconfig);
			request.setAttribute("a", tabletid);
			request.getRequestDispatcher("/configAlertaAndroid.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			String A =request.getParameter("AlertaA").trim();
			String B =request.getParameter("AlertaB").trim();
			String C =request.getParameter("AlertaC").trim();
			Integer ia=0,ib=0,ic=0;
			
			if(A!= null){
		 ia= Integer.valueOf(A);}
			if(B!= null){
		 ib= Integer.valueOf(B);
			}		
			if(C!= null){
			 ic= Integer.valueOf(C);
			}
			        Config c = new Config();
			       
			       
			       c.setAlertA(ia);
			       c.setAlertB(ib);
			       c.setAlertC(ic);
			       c.setId(1);
			        new GestorAlerta().GuardarConfig(c);
			     
			        
			    
			

			    	String tabletid=request.getParameter("a");
					request.getRequestDispatcher("/tabletandroid"+tabletid.trim()+".html").forward(request, response);
		}catch(Exception e){
			response.getWriter().append(e.toString());
		}
	}

}
