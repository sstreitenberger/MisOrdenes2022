package modelos;

public class Orden {
	  private int chk;
	  
	  private int id;
	  
	  private int chk_seq;
	  
	  private String nombre;
	  
	  private String zona;
	  
	  private String fecha_env;
	  
	  private String fecha_delete;
	  
	  private String fecha_crea;
	  
	  private String fecha_cierre;
	  
	  public int getChk() {
	    return this.chk;
	  }
	  
	  public void setChk(int chk) {
	    this.chk = chk;
	  }
	  
	  public int getId() {
	    return this.id;
	  }
	  
	  public void setId(int id) {
	    this.id = id;
	  }
	  
	  public int getChk_seq() {
	    return this.chk_seq;
	  }
	  
	  public void setChk_seq(int chk_seq) {
	    this.chk_seq = chk_seq;
	  }
	  
	  public String getNombre() {
	    return this.nombre;
	  }
	  
	  public void setNombre(String nombre) {
	    this.nombre = nombre;
	  }
	  
	  public String getZona() {
	    return this.zona;
	  }
	  
	  public void setZona(String zona) {
	    this.zona = zona;
	  }
	  
	  public String getFecha_env() {
	    return this.fecha_env;
	  }
	  
	  public void setFecha_env(String fecha_env) {
	    this.fecha_env = fecha_env;
	  }
	  
	  public String getFecha_delete() {
	    return this.fecha_delete;
	  }
	  
	  public void setFecha_delete(String fecha_delete) {
	    this.fecha_delete = fecha_delete;
	  }
	  
	  public String getFecha_crea() {
	    return this.fecha_crea;
	  }
	  
	  public void setFecha_crea(String fecha_crea) {
	    this.fecha_crea = fecha_crea;
	  }
	  
	  public String getFecha_cierre() {
	    return this.fecha_cierre;
	  }
	  
	  public void setFecha_cierre(String fecha_cierre) {
	    this.fecha_cierre = fecha_cierre;
	  }
	}
