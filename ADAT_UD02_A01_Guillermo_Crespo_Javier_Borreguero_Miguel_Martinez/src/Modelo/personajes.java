package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Videojuegos.Personajes;

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
	public void setID(int iD) {
		this.ID = iD;
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
	public void setjuego() {
		this.juego = juego;
	}

}
