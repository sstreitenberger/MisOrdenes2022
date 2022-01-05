package utiles;


import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import gestores.GestorOrdenes;

public class OrderTask implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		TimerTask vodTimer = new OrderTaskExec(servletContextEvent);


		Timer timer = new Timer();		
		timer.schedule(vodTimer, 0, 5000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	class OrderTaskExec extends TimerTask {

		ServletContextEvent servletContextEvent;

		public OrderTaskExec(ServletContextEvent servletContextEvent) {
			this.servletContextEvent = servletContextEvent;
		}

		@Override
		public void run() {
			try {
				GestorOrdenes go = new GestorOrdenes();
				go.InsertarOrdenes();
				//System.out.print("OK");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
}