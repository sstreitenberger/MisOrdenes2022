package gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.ConfigDisp;
import modelos.LCD;
import modelos.Tablet;
import utiles.Conexion;

public class GestorTablets {
  public ArrayList<LCD> TraerTablets() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("select lcd_id,zone from micros.lcd_zone");
    ResultSet rs = pst.executeQuery();
    ArrayList<LCD> lcds = new ArrayList<>();
    while (rs.next()) {
      LCD l = new LCD();
      l.setId_lcd(Integer.valueOf(rs.getInt("tablet_id")));
      l.setZona(Integer.valueOf(rs.getInt("zone")));
      lcds.add(l);
    } 
    return lcds;
  }
  
  public boolean InsertConfigTablets(Tablet l) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String query = "INSERT INTO micros.tablet_zone ( tablet_id, zone ) VALUES ( ?, ?)";
    PreparedStatement pst = conn.prepareStatement(query);
    pst.setInt(1, l.getId_Tablet().intValue());
    pst.setInt(2, l.getZona().intValue());
    return (pst.executeUpdate() > 0);
  }
  
  public boolean DeleteConfigTablets(Integer p_id_tablet) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String query = "DELETE FROM micros.tablet_zone where tablet_id=" + p_id_tablet.toString().trim();
    PreparedStatement pst = conn.prepareStatement(query);
    return (pst.executeUpdate() > 0);
  }
  
  public ArrayList<ConfigDisp> TraerConfigTablets() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT lz.tablet_id AS tablet_id, ISNULL(l1.zone,0) AS z1, ISNULL(l2.zone,0) AS z2, ISNULL(l3.zone,0) AS z3, ISNULL(l4.zone,0) AS z4, ISNULL(l100.zone,0) AS sz FROM micros.tablet_zone lz LEFT JOIN micros.tablet_zone l1 ON lz.tablet_id=l1.tablet_id AND l1.zone=1 LEFT JOIN micros.tablet_zone l2 ON lz.tablet_id=l2.tablet_id AND l2.zone=2 LEFT JOIN micros.tablet_zone l3 ON lz.tablet_id=l3.tablet_id AND l3.zone=3 LEFT JOIN micros.tablet_zone l4 ON lz.tablet_id=l4.tablet_id AND l4.zone=4 LEFT JOIN micros.tablet_zone l100 ON lz.tablet_id=l100.tablet_id AND l100.zone=100 order by lz.tablet_id");
    ResultSet rs = pst.executeQuery();
    ArrayList<ConfigDisp> lcds = new ArrayList<>();
    while (rs.next()) {
      ConfigDisp cl = new ConfigDisp();
      cl.setDisp_id(Integer.valueOf(rs.getInt("tablet_id")));
      cl.setZ1(Integer.valueOf(rs.getInt("z1")));
      cl.setZ2(Integer.valueOf(rs.getInt("z2")));
      cl.setZ3(Integer.valueOf(rs.getInt("z3")));
      cl.setZ4(Integer.valueOf(rs.getInt("z4")));
      cl.setSz(Integer.valueOf(rs.getInt("sz")));
      lcds.add(cl);
    } 
    return lcds;
  }
  
  public ConfigDisp TraerConfigTabletsxid(Integer id) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT lz.tablet_id AS tablet_id, ISNULL(l1.zone,0) AS z1, ISNULL(l2.zone,0) AS z2, ISNULL(l3.zone,0) AS z3, ISNULL(l4.zone,0) AS z4, ISNULL(l100.zone,0) AS sz FROM micros.tablet_zone lz LEFT JOIN micros.tablet_zone l1 ON lz.tablet_id=l1.tablet_id AND l1.zone=1 LEFT JOIN micros.tablet_zone l2 ON lz.tablet_id=l2.tablet_id AND l2.zone=2 LEFT JOIN micros.tablet_zone l3 ON lz.tablet_id=l3.tablet_id AND l3.zone=3 LEFT JOIN micros.tablet_zone l4 ON lz.tablet_id=l4.tablet_id AND l4.zone=4 LEFT JOIN micros.tablet_zone l100 ON lz.tablet_id=l100.tablet_id AND l100.zone=100 WHERE lz.tablet_id=?");
    pst.setInt(1, id.intValue());
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
      ConfigDisp cl = new ConfigDisp();
      cl.setDisp_id(Integer.valueOf(rs.getInt("tablet_id")));
      cl.setZ1(Integer.valueOf(rs.getInt("z1")));
      cl.setZ2(Integer.valueOf(rs.getInt("z2")));
      cl.setZ3(Integer.valueOf(rs.getInt("z3")));
      cl.setZ4(Integer.valueOf(rs.getInt("z4")));
      cl.setSz(Integer.valueOf(rs.getInt("sz")));
      return cl;
    } 
    return null;
  }
}