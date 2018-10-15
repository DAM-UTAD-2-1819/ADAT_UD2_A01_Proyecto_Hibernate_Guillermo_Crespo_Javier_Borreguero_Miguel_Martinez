package Modelo;

public class videojuegos {
	
	private int ID;
	private String Nombre;
	private String Fecha_Lanzamiento;
	private String Desarrollador;
	private String Plataforma;
	
	// constructor de la tabla de videojuegos
	public videojuegos() {
		
	}
	//getters y setters de los atributos de la tabla videojuegos
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = ID;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getFecha_Lanzamiento() {
		return Fecha_Lanzamiento;
	}

	public void setFecha_Lanzamiento(String fecha_Lanzamiento) {
		this.Fecha_Lanzamiento = fecha_Lanzamiento;
	}

	public String getDesarrollador() {
		return Desarrollador;
	}

	public void setDesarrollador(String desarrollador) {
		this.Desarrollador = desarrollador;
	}

	public String getPlataforma() {
		return Plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.Plataforma = plataforma;
	}
	
	

}
