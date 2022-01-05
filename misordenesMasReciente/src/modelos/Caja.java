package modelos;

public class Caja {
	  private Integer uws_seq;
	  
	  private Integer id;
	  
	  private String obj_num;
	  
	  private String nombre;
	  
	  private Integer zona;
	  
	  public String getnombre() {
	    return this.nombre;
	  }
	  
	  public void setnombre(String nombre) {
	    this.nombre = nombre;
	  }
	  
	  public int getuws_seq() {
	    return this.uws_seq.intValue();
	  }
	  
	  public void setuws(int chk) {
	    this.uws_seq = Integer.valueOf(chk);
	  }
	  
	  public String getObj_num() {
	    return this.obj_num;
	  }
	  
	  public void setObj_num(String obj_num) {
	    this.obj_num = obj_num;
	  }
	  
	  public Integer getZona() {
	    return this.zona;
	  }
	  
	  public void setZona(Integer zona) {
	    this.zona = zona;
	  }
	  
	  public int getId() {
	    return this.id.intValue();
	  }
	  
	  public void setId(int id) {
	    this.id = Integer.valueOf(id);
	  }
	}
