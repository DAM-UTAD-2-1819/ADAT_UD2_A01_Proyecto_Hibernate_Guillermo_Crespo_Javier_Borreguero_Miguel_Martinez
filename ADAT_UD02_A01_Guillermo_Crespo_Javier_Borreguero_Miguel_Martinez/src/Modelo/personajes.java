package Modelo;

public class personajes {
	
	private int ID;
	private String Nombre_Personaje;
	private int ID_Juego;
	
	//Constructor de la tabla personajes
	public personajes(){
		
	}
	
	//getters y setters de los atributos
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getNombre_Personaje() {
		return Nombre_Personaje;
	}
	public void setNombre_Personaje(String nombre_Personaje) {
		this.Nombre_Personaje = nombre_Personaje;
	}
	public int getID_Juego() {
		return ID_Juego;
	}
	public void setID_Juego(int iD_Juego) {
		this.ID_Juego = iD_Juego;
	}

}
