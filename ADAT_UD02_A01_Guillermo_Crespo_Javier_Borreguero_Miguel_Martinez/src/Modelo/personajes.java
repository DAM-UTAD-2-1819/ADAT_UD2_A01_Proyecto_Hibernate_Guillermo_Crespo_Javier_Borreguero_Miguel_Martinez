package Modelo;



public class personajes {
	
	private int ID;
	private String Nombre_Personaje;
	private int juego;
	//Constructor de la tabla personajes
	public personajes(){
		
	}
	
	//getters y setters de los atributos
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getNombre_Personaje() {
		return Nombre_Personaje;
	}
	public void setNombre_Personaje(String nombre_Personaje) {
		this.Nombre_Personaje = nombre_Personaje;
	}
	public int getjuego(){
		return juego;
		
	}
	public void setjuego(int juego) {
		this.juego = juego;
	}

}
