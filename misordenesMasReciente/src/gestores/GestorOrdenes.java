package gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.ConfigDisp;
import modelos.Orden;
import utiles.Conexion;

public class GestorOrdenes {
  public ArrayList<Orden> TraerOrdenes(String zona) throws Exception {
    PreparedStatement pst = null;
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String str;
    switch ((str = zona).hashCode()) {
      case 48:
        if (str.equals("0")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM (SELECT DISTINCT row_number() over(partition by b.chk_num order by date_closed asc) as  Posision,a.id as id, b.chk_num, date_open, date_closed, isnull(name,' ') as name, date_send,date_delete, [zone],a.chk_seq FROM micros.order_dtl a inner join micros.chk_dtl b on a.chk_seq = b.chk_seq where date_send is null and date_closed > DATEADD(mi,-30,GETDATE()) and zone<100 and b.order_type_seq != 3 order by date_closed asc) x where x.posision = 1");
          break;
        } 
      case 49:
        if (str.equals("1")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=1 order by a.date_closed asc");
          break;
        } 
      case 50:
        if (str.equals("2")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=2 order by a.date_closed asc");
          break;
        } 
      case 51:
        if (str.equals("3")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, chk_num, date_open, date_closed, name, date_send,date_delete, [zone],chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where date_send is null and date_closed >  DATEADD(mi,-30,GETDATE()) and zone=3 order by date_closed asc");
          break;
        } 
      case 52:
        if (str.equals("4")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=4 order by a.date_closed asc");
          break;
        } 
      default:
        pst = conn.prepareStatement("SELECT * FROM (SELECT DISTINCT row_number() over(partition by b.chk_num order by date_closed asc) as  Posision,a.id as id, b.chk_num, date_open, date_closed, isnull(name,' ') as name, date_send,date_delete, [zone],a.chk_seq FROM micros.order_dtl a inner join micros.chk_dtl b on a.chk_seq = b.chk_seq where date_send is null and date_closed > DATEADD(mi,-30,GETDATE()) and zone<100 and b.order_type_seq != 3 order by date_closed asc) x where x.posision = 1");
        break;
    } 
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      if (rs.getString("name").contains("1") || rs.getString("name").contains("2") || 
        rs.getString("name").contains("3") || rs.getString("name").contains("4") || 
        rs.getString("name").contains("5") || rs.getString("name").contains("6") || 
        rs.getString("name").contains("7") || rs.getString("name").contains("8") || 
        rs.getString("name").contains("9") || rs.getString("name").contains("0")) {
        a.setNombre("");
      } else {
        a.setNombre(rs.getString("name"));
      } 
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> TraerOrdeneslifo(ConfigDisp lcd) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String condicion = "and (";
    if (lcd.getZ1().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=1   or "; 
    if (lcd.getZ2().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=2   or "; 
    if (lcd.getZ3().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=3   or "; 
    if (lcd.getZ4().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=4   or "; 
    if (lcd.getSz().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone>100   or "; 
    condicion = condicion.substring(0, condicion.length() - 4);
    condicion = String.valueOf(condicion) + " ) ";
    PreparedStatement pst = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE())  and zone<100 $$$ order by a.date_closed desc".replace("$$$", condicion));
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> TraerOrdenesEnviadas(String zona) throws Exception {
    PreparedStatement pst = null;
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String str;
    switch ((str = zona).hashCode()) {
      case 48:
        if (str.equals("0")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM (SELECT distinct row_number() over(partition by b.chk_num order by date_closed asc) as  Posision,a.id as id, b.chk_num, a.date_open, a.date_closed, isnull(name, ' ') as name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl a left join micros.chk_dtl b on a.chk_seq = b.chk_seq WHERE a.date_send IS NOT NULL  AND a.date_send > DATEADD(mi,-30,GETDATE()) and b.order_type_seq != 3 ORDER BY a.date_send DESC) x where x.posision = 1");
          break;
        } 
      case 49:
        if (str.equals("1")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null  AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=1 order by a.date_send desc");
          break;
        } 
      case 50:
        if (str.equals("2")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null  AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=2 order by a.date_send desc");
          break;
        } 
      case 51:
        if (str.equals("3")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null  AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=3 order by a.date_send desc");
          break;
        } 
      case 52:
        if (str.equals("4")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null  AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=4 order by a.date_send desc");
          break;
        } 
      default:
        pst = conn.prepareStatement("SELECT * FROM (SELECT distinct row_number() over(partition by b.chk_num order by date_closed asc) as  Posision,a.id as id, b.chk_num, a.date_open, a.date_closed, isnull(name, ' ') as name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl a left join micros.chk_dtl b on a.chk_seq = b.chk_seq WHERE a.date_send IS NOT NULL  AND a.date_send > DATEADD(mi,-30,GETDATE()) and b.order_type_seq != 3 ORDER BY a.date_send DESC) x where x.posision = 1");
        break;
    } 
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      if (rs.getString("name").contains("1") || rs.getString("name").contains("2") || 
        rs.getString("name").contains("3") || rs.getString("name").contains("4") || 
        rs.getString("name").contains("5") || rs.getString("name").contains("6") || 
        rs.getString("name").contains("7") || rs.getString("name").contains("8") || 
        rs.getString("name").contains("9") || rs.getString("name").contains("0")) {
        a.setNombre("");
      } else {
        a.setNombre(rs.getString("name"));
      } 
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> TraerOrdenesEnviadas(ConfigDisp lcd) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String condicion = "and (";
    if (lcd.getZ1().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=1   or "; 
    if (lcd.getZ2().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=2   or "; 
    if (lcd.getZ3().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=3   or "; 
    if (lcd.getZ4().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=4   or "; 
    if (lcd.getSz().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone<100   or "; 
    condicion = condicion.substring(0, condicion.length() - 4);
    condicion = String.valueOf(condicion) + " ) ";
    PreparedStatement pst = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq WHERE a.date_send IS NOT NULL  AND a.date_send > DATEADD(mi,-30,GETDATE()) $$$ ORDER BY a.date_send DESC".replace("$$$", condicion));
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> PantallaTraerOrdenesEnviadas(ConfigDisp lcd) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String condicion = "and (";
    if (lcd.getZ1().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=1   or "; 
    if (lcd.getZ2().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=2   or "; 
    if (lcd.getZ3().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=3   or "; 
    if (lcd.getZ4().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=4   or "; 
    if (lcd.getSz().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone>100   or "; 
    condicion = condicion.substring(0, condicion.length() - 4);
    condicion = String.valueOf(condicion) + " ) ";
    PreparedStatement pst = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_se WHERE a.date_send IS NOT NULL AND a.date_delete IS NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) $$$ ORDER BY a.date_send DESC".replace("$$$", condicion));
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> PantallaTraerOrdenesEnviadas(String zona) throws Exception {
    PreparedStatement pst = null;
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String str;
    switch ((str = zona).hashCode()) {
      case 48:
        if (str.equals("0")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq WHERE a.date_send IS NOT NULL AND a.date_delete IS NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) ORDER BY a.date_send DESC");
          break;
        } 
      case 49:
        if (str.equals("1")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null AND a.date_delete IS NULL AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=1 order by a.date_send desc");
          break;
        } 
      case 50:
        if (str.equals("2")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null AND a.date_delete IS NULL AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=2 order by a.date_send desc");
          break;
        } 
      case 51:
        if (str.equals("3")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null AND a.date_delete IS NULL AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=3 order by a.date_send desc");
          break;
        } 
      case 52:
        if (str.equals("4")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null AND a.date_delete IS NULL AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and zone=4 order by a.date_send desc");
          break;
        } 
      case 1569:
        if (str.equals("12")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM (SELECT distinct row_number() over(partition by b.chk_num order by date_closed asc) as  Posision,a.id as id, b.chk_num, date_open, date_closed, isnull(name, ' ') as name, date_send, date_delete, [zone], a.chk_seq FROM micros.order_dtl a left join micros.chk_dtl b on a.chk_seq = b.chk_seq WHERE date_send IS NOT NULL  and date_delete is null AND date_send > DATEADD(mi,-30,GETDATE()) and b.order_type_seq != 3 ORDER BY date_send DESC) x where x.posision = 1");
          break;
        } 
      case 1633:
        if (str.equals("34")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is not null AND a.date_delete IS NULL AND  a.date_send >  DATEADD(mi,-30,GETDATE()) and (zone=3 or zone=4) order by a.date_send desc");
          break;
        } 
      default:
        pst = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq WHERE a.date_send IS NOT NULL AND a.date_delete IS NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) ORDER BY a.date_send DESC");
        break;
    } 
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> TraerOrdenesCliente(String zona) throws Exception {
    PreparedStatement pst = null;
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String str;
    switch ((str = zona).hashCode()) {
      case 49:
        if (str.equals("1")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT top 6 a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, zone, a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq  WHERE a.date_send IS NOT NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) AND zone=1 ORDER BY a.date_send DESC");
          break;
        } 
      case 50:
        if (str.equals("2")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT top 6 a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, zone, a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq  WHERE a.date_send IS NOT NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) AND zone=2 ORDER BY a.date_send DESC");
          break;
        } 
      case 51:
        if (str.equals("3")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT top 6 a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, zone, a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq  WHERE a.date_send IS NOT NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) AND zone=3 ORDER BY a.date_send DESC");
          break;
        } 
      case 52:
        if (str.equals("4")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT top 6 a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, zone, a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq  WHERE a.date_send IS NOT NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) AND zone=4 ORDER BY a.date_send DESC");
          break;
        } 
      default:
        pst = conn.prepareStatement("SELECT top 6 a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, zone, a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq  WHERE a.date_send IS NOT NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) AND zone=1 ORDER BY a.date_send DESC");
        break;
    } 
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> TraerEnviadasyDisponibles() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("(SELECT top 6 zone, b.chk_num , 1 FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq WHERE a.date_send IS NOT NULL AND a.date_send > DATEADD(mi,-30,GETDATE()) ORDER BY a.date_send DESC) UNION ALL (SELECT top 6 zone, b.chk_num ,0 FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE())  and zone<100 order by a.date_closed asc)");
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setZona(rs.getString("zone"));
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("1"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public void InsertarOrdenes() throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("INSERT INTO micros.order_dtl  SELECT DISTINCT CASE WHEN (c.rvc_seq = 6  and isnumeric(LEFT(trim(c.id), charindex(' ',c.id) -1))=1 and c.id is not null) then CAST(LEFT(trim(c.id), charindex(' ',c.id) -1) AS INT)  ELSE CAST(c.chk_num AS INT) END AS chk_num, c.chk_open_date_time AS date_open, c.chk_clsd_date_time AS date_closed, NULL AS date_send,NULL as date_delete, case when (c.rvc_seq = 6  and isnumeric(LEFT(trim(c.id), charindex(' ',c.id) -1))=1 and c.id is not null) then substring(c.id, charindex(' ',trim(c.id)) +1,15) ELSE substring(CAST(c.id AS VARCHAR),1,16) END AS name, CASE WHEN ISNULL(uz.zone,1)=1 THEN 1 ELSE uz.zone END AS zona, NULL AS id, c.chk_seq FROM micros.chk_dtl AS c LEFT JOIN micros.order_dtl AS o ON o.chk_seq = c.chk_seq LEFT JOIN micros.trans_dtl td ON td.chk_seq=c.chk_seq LEFT JOIN micros.uws_zone uz ON uz.uws_seq=td.uws_seq WHERE chk_open = 'F'   AND o.chk_seq IS NULL AND chk_clsd_date_time > DATEADD(mi,-30,GETDATE()) ORDER BY chk_clsd_date_time DESC");
    pst.executeUpdate();
  }
  
  public void EnviarOrden(int id) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("UPDATE micros.order_dtl SET date_send = GETDATE() WHERE id = " + id);
    pst.executeUpdate();
  }
  
  public void VolverOrden(int id) throws Exception {
    boolean tienequevolver = DebeVolver(Integer.valueOf(id));
    if (tienequevolver) {
      Conexion c = new Conexion();
      Connection conn = c.getConnection();
      PreparedStatement pst = conn.prepareStatement("UPDATE micros.order_dtl SET date_send = NULL, date_delete = NULL WHERE id = " + id);
      pst.executeUpdate();
    } else {
      Conexion c = new Conexion();
      Connection conn = c.getConnection();
      PreparedStatement pst = conn.prepareStatement("UPDATE micros.order_dtl SET date_delete = GETDATE() WHERE id = " + id);
      pst.executeUpdate();
    } 
  }
  
  public boolean DebeVolver(Integer id) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    PreparedStatement pst = conn.prepareStatement("select * from  micros.order_dtl WHERE date_delete = null and id = " + id);
    ResultSet rs = pst.executeQuery();
    if (rs.next())
      return false; 
    return true;
  }
  
  public ArrayList<Orden> TraerOrdeneslifo(String zona) throws Exception {
    PreparedStatement pst = null;
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String str;
    switch ((str = zona).hashCode()) {
      case 48:
        if (str.equals("0")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl a left join micros.chk_dtl b  on a.chk_seq = b.chk_seq  where date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE())   and zone<100 and b.order_type_seq != 3 order by a.date_closed desc");
          break;
        } 
      case 49:
        if (str.equals("1")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=1 order by a.date_closed desc");
          break;
        } 
      case 50:
        if (str.equals("2")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=2 order by a.date_closed desc");
          break;
        } 
      case 51:
        if (str.equals("3")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=3 order by a.date_closed desc");
          break;
        } 
      case 52:
        if (str.equals("4")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send,a.date_delete, [zone],a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE()) and zone=4 order by a.date_closed desc");
          break;
        } 
      case 1569:
        if (str.equals("12")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM (SELECT DISTINCT row_number() over(partition by b.chk_num order by date_closed asc) as  Posision,a.id as id, b.chk_num, date_open, date_closed, isnull(name,' ') as name, date_send,date_delete, [zone],a.chk_seq FROM  micros.order_dtl a inner join micros.chk_dtl b on a.chk_seq = b.chk_seq where date_send is null and date_closed > DATEADD(mi,-30,GETDATE())  and zone<100 and b.order_type_seq != 3 order by date_closed asc) x where x.posision = 1");
          break;
        } 
      case 1633:
        if (str.equals("34")) {
          PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, chk_num, date_open, date_closed, name, date_send,date_delete, [zone],chk_seq FROM micros.order_dtl where date_send is null and date_closed >  DATEADD(mi,-30,GETDATE()) and (zone=3 or zone=4) order by date_closed desc");
          break;
        } 
      default:
        pst = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl a left join micros.chk_dtl b  on a.chk_seq = b.chk_seq  where date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE())   and zone<100 and b.order_type_seq != 3 order by a.date_closed desc");
        break;
    } 
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
  
  public ArrayList<Orden> TraerOrdenes(ConfigDisp lcd) throws Exception {
    Conexion c = new Conexion();
    Connection conn = c.getConnection();
    String condicion = "and (";
    if (lcd.getZ1().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=1   or "; 
    if (lcd.getZ2().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=2   or "; 
    if (lcd.getZ3().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=3   or "; 
    if (lcd.getZ4().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone=4   or "; 
    if (lcd.getSz().intValue() > 0)
      condicion = String.valueOf(condicion) + "zone<100   or "; 
    condicion = condicion.substring(0, condicion.length() - 4);
    condicion = String.valueOf(condicion) + " ) ";
    PreparedStatement pst = conn.prepareStatement("SELECT a.id, b.chk_num, a.date_open, a.date_closed, a.name, a.date_send, a.date_delete, [zone], a.chk_seq FROM micros.order_dtl A inner join micros.chk_dtl B on A.chk_seq = b.chk_seq where a.date_send is null and a.date_closed >  DATEADD(mi,-30,GETDATE())  and zone<100 $$$ order by a.date_closed asc".replace("$$$", condicion));
    ResultSet rs = pst.executeQuery();
    ArrayList<Orden> ordenes = new ArrayList<>();
    while (rs.next()) {
      Orden a = new Orden();
      a.setChk(rs.getInt("chk_num"));
      a.setChk_seq(rs.getInt("chk_seq"));
      a.setId(rs.getInt("id"));
      a.setNombre(rs.getString("name"));
      a.setFecha_env(rs.getString("date_send"));
      a.setFecha_delete(rs.getString("date_delete"));
      a.setFecha_crea(rs.getString("date_open"));
      a.setFecha_cierre(rs.getString("date_closed"));
      a.setZona(rs.getString("zone"));
      ordenes.add(a);
    } 
    return ordenes;
  }
}