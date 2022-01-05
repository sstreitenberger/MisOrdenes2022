package utiles;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	public Conexion() { }

	public Connection getConnection() throws Exception {
		InetAddress ip;
		ip = InetAddress.getLocalHost(); //192.168.198.136
	    // String url = "jdbc:sybase:Tds: " + ip + ":2638?ServiceName=micros";
	    // String url = "jdbc:sybase:Tds:172.20.122.100:2638?ServiceName=micros";
		//String url = "jdbc:sybase:Tds:"+ip.getHostAddress()+":2638?ServiceName=micros";
		String url = "jdbc:sybase:Tds:172.20.39.100:2638?ServiceName=micros";
		System.out.println("URL: "+url);
		String driver = "com.sybase.jdbc3.jdbc.SybDriver";
		String userName = "dba";
		String password = "micros";
		Class.forName(driver).newInstance();
		
		return DriverManager.getConnection(url, userName, password);
		

	}
		public Connection conexionBksrv4() throws Exception {
		String url = "jdbc:sqlserver://172.31.1.14:1433;databaseName=Alsea";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = "mobile";
		String password = ".M0b1l3$.";
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, userName, password);
	}
	
	
}

