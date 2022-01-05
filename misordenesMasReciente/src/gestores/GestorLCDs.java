package gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.ConfigDisp;
import modelos.LCD;
import utiles.Conexion;

public class GestorLCDs {
  public ArrayList<LCD> TraerLCDs() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("select lcd_id,zone from micros.lcd_zone");
    ResultSet rs = pst.executeQuery();
    ArrayList<LCD> lcds = new ArrayList<>();
    while (rs.next()) {
      LCD l = new LCD();
      l.setId_lcd(Integer.valueOf(rs.getInt("lcd_id")));
      l.setZona(Integer.valueOf(rs.getInt("zone")));
      lcds.add(l);
    } 
    return lcds;
  }
  
  public boolean InsertConfigLCD(LCD l) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String query = "INSERT INTO micros.lcd_zone ( lcd_id, zone ) VALUES ( ?, ?)";
    PreparedStatement pst = conn.prepareStatement(query);
    pst.setInt(1, l.getId_lcd().intValue());
    pst.setInt(2, l.getZona().intValue());
    return (pst.executeUpdate() > 0);
  }
  
  public boolean DeleteConfigLCD(Integer id_lcd) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String query = "DELETE FROM micros.lcd_zone where lcd_id=" + id_lcd.toString().trim();
    PreparedStatement pst = conn.prepareStatement(query);
    return (pst.executeUpdate() > 0);
  }
  
  public ArrayList<ConfigDisp> TraerConfigLCDs() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT lz.lcd_id AS lcd_id, isnull(l1.zone,0) AS z1, isnull(l2.zone,0) AS z2, isnull(l3.zone,0) AS z3, isnull(l4.zone,0) AS z4, isnull(l100.zone,0) AS sz FROM micros.lcd_zone lz LEFT JOIN micros.lcd_zone l1 ON lz.lcd_id=l1.lcd_id AND l1.zone=1 LEFT JOIN micros.lcd_zone l2 ON lz.lcd_id=l2.lcd_id AND l2.zone=2 LEFT JOIN micros.lcd_zone l3 ON lz.lcd_id=l3.lcd_id AND l3.zone=3 LEFT JOIN micros.lcd_zone l4 ON lz.lcd_id=l4.lcd_id AND l4.zone=4 LEFT JOIN micros.lcd_zone l100 ON lz.lcd_id=l100.lcd_id AND l100.zone=100");
    ResultSet rs = pst.executeQuery();
    ArrayList<ConfigDisp> lcds = new ArrayList<>();
    while (rs.next()) {
      ConfigDisp cl = new ConfigDisp();
      cl.setDisp_id(Integer.valueOf(rs.getInt("lcd_id")));
      cl.setZ1(Integer.valueOf(rs.getInt("z1")));
      cl.setZ2(Integer.valueOf(rs.getInt("z2")));
      cl.setZ3(Integer.valueOf(rs.getInt("z3")));
      cl.setZ4(Integer.valueOf(rs.getInt("z4")));
      cl.setSz(Integer.valueOf(rs.getInt("sz")));
      lcds.add(cl);
    } 
    return lcds;
  }
  
  public ConfigDisp TraerConfigLCDxid(Integer id) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT lz.lcd_id AS lcd_id, isnull(l1.zone,0) AS z1, isnull(l2.zone,0) AS z2, isnull(l3.zone,0) AS z3, isnull(l4.zone,0) AS z4, isnull(l100.zone,0) AS sz FROM micros.lcd_zone lz LEFT JOIN micros.lcd_zone l1 ON lz.lcd_id=l1.lcd_id AND l1.zone=1 LEFT JOIN micros.lcd_zone l2 ON lz.lcd_id=l2.lcd_id AND l2.zone=2 LEFT JOIN micros.lcd_zone l3 ON lz.lcd_id=l3.lcd_id AND l3.zone=3 LEFT JOIN micros.lcd_zone l4 ON lz.lcd_id=l4.lcd_id AND l4.zone=4 LEFT JOIN micros.lcd_zone l100 ON lz.lcd_id=l100.lcd_id AND l100.zone=100 WHERE lz.lcd_id=?");
    pst.setInt(1, id.intValue());
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
      ConfigDisp cl = new ConfigDisp();
      cl.setDisp_id(Integer.valueOf(rs.getInt("lcd_id")));
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
