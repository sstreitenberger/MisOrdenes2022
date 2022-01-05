package gestores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Caja;
import utiles.Conexion;

public class GestorCajas {
  public ArrayList<Caja> TraerCajas() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("SELECT d.uws_seq, dd.name, d.obj_num, CASE WHEN ISNULL(z.zone,0)=0 THEN 1 ELSE z.zone END AS zone FROM micros.uws_def d LEFT JOIN micros.uws_zone z ON z.uws_seq=d.uws_seq LEFT JOIN micros.dev_def dd ON dd.obj_num=d.obj_num WHERE  dd.name like '%UWS%' ORDER by dd.name");
    ResultSet rs = pst.executeQuery();
    ArrayList<Caja> cajas = new ArrayList<>();
    while (rs.next()) {
      Caja a = new Caja();
      a.setuws(rs.getInt("uws_seq"));
      a.setnombre(rs.getString("name"));
      a.setObj_num(rs.getString("obj_num"));
      a.setZona(Integer.valueOf(rs.getInt("zone")));
      cajas.add(a);
    } 
    return cajas;
  }
  
  public boolean GuardarConfigCajas(Caja c) throws Exception {
    if (!UpdateConfigCaja(c))
      InsertConfigCaja(c); 
    return true;
  }
  
  private boolean InsertConfigCaja(Caja p) throws Exception {
    try {
      Conexion c = new Conexion();
      Connection conn = c.getConnection();
      String query = "INSERT INTO micros.uws_zone ( uws_seq, zone ) VALUES ( ?, ?)";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setInt(1, p.getuws_seq());
      pst.setInt(2, p.getZona().intValue());
      return (pst.executeUpdate() > 0);
    } catch (Exception e) {
      System.out.println("ERROR EN INSERT  " + e.getMessage());
      return false;
    } 
  }
  
  private boolean UpdateConfigCaja(Caja p) throws Exception {
    try {
      Conexion c = new Conexion();
      Connection conn = c.getConnection();
      String query = "UPDATE micros.uws_zone SET zone = ? WHERE uws_seq=? ";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setInt(1, p.getZona().intValue());
      pst.setInt(2, p.getuws_seq());
      return (pst.executeUpdate() > 0);
    } catch (Exception e) {
      System.out.println("ERROR EN INSERT  " + e.getMessage());
      return false;
    } 
  }
}
