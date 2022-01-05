package gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelos.Config;
import utiles.Conexion;

public class GestorAlerta {
  public Config TraerConfig() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("select oc.id,oc.fecha,oc.alertA,oc.AlertB,oc.AlertC from micros.micros.order_config oc where oc.id=1");
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
      Config a = new Config();
      a.setId(Integer.valueOf(rs.getInt("id")));
      a.setFecha(Integer.valueOf(rs.getInt("fecha")));
      a.setAlertA(Integer.valueOf(rs.getInt("alertA")));
      a.setAlertB(Integer.valueOf(rs.getInt("AlertB")));
      a.setAlertC(Integer.valueOf(rs.getInt("AlertC")));
      return a;
    } 
    return null;
  }
  
  public boolean GuardarConfig(Config c) throws Exception {
    if (!UpdateConfigCaja(c))
      InsertConfigCaja(c); 
    return true;
  }
  
  private boolean InsertConfigCaja(Config p) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String query = "INSERT INTO micros.micros.order_config ( id, fecha, alertA, AlertB, AlertC ) VALUES ( ?, ?, ?, ?, ? )";
    PreparedStatement pst = conn.prepareStatement(query);
    pst.setInt(1, p.getId().intValue());
    pst.setInt(2, p.getFecha().intValue());
    pst.setInt(3, p.getAlertA().intValue());
    pst.setInt(4, p.getAlertB().intValue());
    pst.setInt(5, p.getAlertC().intValue());
    return (pst.executeUpdate() > 0);
  }
  
  private boolean UpdateConfigCaja(Config p) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String query = "UPDATE micros.micros.order_config SET alertA = ?, AlertB = ?, AlertC = ? WHERE id=? ";
    PreparedStatement pst = conn.prepareStatement(query);
    pst.setInt(1, p.getAlertA().intValue());
    pst.setInt(2, p.getAlertB().intValue());
    pst.setInt(3, p.getAlertC().intValue());
    pst.setInt(4, p.getId().intValue());
    return (pst.executeUpdate() > 0);
  }
}
