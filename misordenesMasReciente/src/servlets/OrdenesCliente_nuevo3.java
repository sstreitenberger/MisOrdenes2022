package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorOrdenes;
import modelos.Orden;

@WebServlet("/OrdenesCliente_nuevo3")
public class OrdenesCliente_nuevo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String pzona = request.getParameter("zona");
			GestorOrdenes go = new GestorOrdenes();
			ArrayList<Orden> ordenesEnviadas = go.PantallaTraerOrdenesEnviadas(pzona);
			ArrayList<Orden> ordenesDisponibles = go.TraerOrdeneslifo(pzona);

			String ze = "";
			String ce = "";
			String cn = "";
			String cnn = "";
			String co = "";
			String cn2 = "";
			String co2 = "";
			String co3 = "";
			String cn3 = "";
			String zo = "";
			String mytabla = "";

			//
			
			String cOrden1 = "", cOrden2= "", cOrden3 = "", cOrdenEntrega ="";
			String cNombre1 = "", cNombre2 = "", cNombre3 = "", cNombreEntrega ="";

			int var = 0;
			
			for(int j=0; j<30; j+=3)
			{
			  	  
			  if(!ordenesDisponibles.isEmpty())
			  {
			    //Chequeo primera ordenesDisponibles
			    
				  if(ordenesDisponibles.size() > j)
				  {

			      if(ordenesDisponibles.get(j).getNombre() != null)
			      {
			    	   if(!ordenesDisponibles.get(j).getNombre().matches(".*\\d.*")) 
					      {
			    		   	cOrden1 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + "</td>";
					        cNombre1 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + ordenesDisponibles.get(j).getNombre() + "</td>";
					      }
			    	   else
					      {
					    	cOrden1 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + "</td>";
						    cNombre1 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";
					      }
			        
			      }
			      else
			      {
			        cOrden1 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j).getChk() + "</td>";
			        cNombre1 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";
			      }
			  }
				  else
				  {
			          cOrden1 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + " " + "</td>";
			          cNombre1 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";	
				  }

			      //Chequeo segunda ordenesDisponibles 
			     
				  if(ordenesDisponibles.size() > j+1)
				  {
					  	  
			      if(ordenesDisponibles.get(j+1).getNombre() != null)
			      {
			    	  if(!ordenesDisponibles.get(j+1).getNombre().matches(".*\\d.*")) 
				      {
			    		  cOrden2 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j+1).getChk() + "</td>";
					        cNombre2 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + ordenesDisponibles.get(j+1).getNombre() + "</td>";
				      }
			    	  else
				      {
					    cOrden2 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j+1).getChk() + "</td>";
					    cNombre2 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";			    	  
				      }
				      }
			      else
			      {
			        cOrden2 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j+1).getChk() + "</td>";
			        cNombre2 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";
			      }
			  }
				  else
				  {
			          cOrden2 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + " " + "</td>";
			          cNombre2 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";	  
				  }
			     
			      //Chequeo tercer ordenesDisponibles
			    
				  if(ordenesDisponibles.size() > j+2)
			    	 {
			    		 
			    if(ordenesDisponibles.get(j+2).getNombre() != null)
			      {
			    	  if(!ordenesDisponibles.get(j+2).getNombre().matches(".*\\d.*"))
				      {
			    		  cOrden3 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j+2).getChk() + "</td>";
					        cNombre3 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + ordenesDisponibles.get(j+2).getNombre() + "</td>";
				      }
			    	  else
				      {
					    cOrden3 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j+2).getChk() + "</td>";
					    cNombre3 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";	  
				      }
				      		       
			      }
			      else
			      {
			        cOrden3 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + ordenesDisponibles.get(j+2).getChk() + "</td>";
			        cNombre3 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";
			      }
			            
			  }
				  else
				  {
			          cOrden3 = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:8%' class='nuevotd orden'>" + " " + "</td>";
			          cNombre3 = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:15%' class='nuevotd orden'>" + " " + "</td>";
				  }
				
				  
			    }
			  else
			  {
				  	cOrden1 = "<td style='background-color:rgba(229,167,112,0.60); width:8%' class='nuevotd orden'>" + " " + "</td>";
				  	cNombre1 = "<td style='background-color:rgba(229,167,112,0.45); width:15%' class='nuevotd orden'>" + " " + "</td>";	  
				  	cOrden2 = "<td style='background-color:rgba(229,167,112,0.60); width:8%' class='nuevotd orden'>" + " " + "</td>";
				  	cNombre2 = "<td style='background-color:rgba(229,167,112,0.45); width:15%' class='nuevotd orden'>" + " " + "</td>";	  
				  	cOrden3 = "<td style='background-color:rgba(229,167,112,0.60); width:8%' class='nuevotd orden'>" + " " + "</td>";
				  	cNombre3 = "<td style='background-color:rgba(229,167,112,0.45); width:15%' class='nuevotd orden'>" + " " + "</td>";  
			  }
			      //OrdenesEnviadas
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			     
			  if(!ordenesEnviadas.isEmpty())
			      {

			        if(ordenesEnviadas.size() > var)
			        {
			        	
			        //Chequeo primera ordenesEnviadas
			        if(j == 0)
			        {
			          if(ordenesEnviadas.get(var).getNombre() != null)
			          {
			        	  if(!ordenesEnviadas.get(var).getNombre().matches(".*\\d.*")) 
				        	{
					            cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60); width:10%'> <span class='animacion'>" + ordenesEnviadas.get(var).getChk() + "</span></td>"; 
					            cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45); width:20%'> <span class='animacion'>" + ordenesEnviadas.get(var).getNombre() + "</span></td>";
				        	}
			        	  else
				        	{
				        	 cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60); width:10%'> <span class='animacion'>" + ordenesEnviadas.get(var).getChk() + "</span></td>"; 
					         cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45); width:20%'> <span class='animacion'>" + " " + "</span></td>";
				        	}

			          }
			          else
			          {
			            cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60); width:10%'> <span class='animacion'>" + ordenesEnviadas.get(var).getChk() + "</span></td>"; 
			            cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45); width:20%'> <span class='animacion'>" + " " + "</span></td>";
			          }	
			        }
			        else{
			        	
    		
			          if(ordenesEnviadas.get(var).getNombre() != null)
			          {
			        	  if(!ordenesEnviadas.get(var).getNombre().matches(".*\\d.*")) {
			        		 
			        		  cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:10%'>" + ordenesEnviadas.get(var).getChk() + "</td>"; 
					          cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:20%'>" + ordenesEnviadas.get(var).getNombre() + "</td>";
			        	  }
			        	  else
				        	{
			        		  cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:10%'>" + ordenesEnviadas.get(var).getChk() + "</td>"; 
						      cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:20%'>" + " " + "</td>";
				        	}
			            
			          }
			          else
			          {
			            cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60);color: #7f2f1c; width:10%'>" + ordenesEnviadas.get(var).getChk() + "</td>"; 
			            cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45);color: #7f2f1c; width:20%'>" + " " + "</td>";
			          }  
			        	      	
			        
			        }
			  
			     
			      }
			      
			      else
			      {
			    	  cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60); width:10%'>" + " " + "</td>"; 
			          cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45); width:20%'>" + " " + "</td>";
			      }
			        
			      }
			      else
			      {
			    	  cOrdenEntrega = "<td style='background-color:rgba(229,167,112,0.60); width:10%'>" + " " + "</td>"; 
			            cNombreEntrega = "<td style='background-color:rgba(229,167,112,0.45); width:20%'>" + " " + "</td>";
			      }
			  
			      var++;
			      
			      mytabla += "<tr>" + cOrden1 + cNombre1 + cOrden2 + cNombre2 + cOrden3 + cNombre3 + cOrdenEntrega + cNombreEntrega + "</tr>";
			      
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
