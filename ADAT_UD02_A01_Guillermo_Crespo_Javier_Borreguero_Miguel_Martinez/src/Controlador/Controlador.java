package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import BD_Manager.BD_Manager;
import Videojuegos.Videojuego;
import Vistas.*;
import Interface.Intercambio;
import FileManager.FileManager;
import HibernateManager.HibernateManager;

public class Controlador {
	BD_Manager mBD = new BD_Manager();
	FileManager mFM = new FileManager();
	HibernateManager mHM = new HibernateManager();
	Inicio mVista = new Inicio();
	
	//Llamadas a BD_Manager
	public void ImprimirDatos() {
		mBD.LeerTodos();
	}
	public void ImprimirVideojuegos() {
		mBD.LeerTodosAux();
	}
	public void ImprimirDatosPer() {
		mBD.LeerTodosPer();
	}
	public void InsertarBBDD() {
		mBD.Añadir();
	}
	public void InsertarBBDDPer() {
		mBD.AñadirPer();
	}
	public void TXT2BBDD() {
		mBD.EscribirTodos();
	}
	
	//Llamadas a File_Manager
	public void Leer_Fichero() {
		mFM.LeerTodos();
	}
	public void Leer_FicheroPer() {
		mFM.LeerTodosPer();
	}
	public void Escribir_Fichero() {
		mFM.EscribirTodos();
	}
	public void Escribir_FicheroPer() {
		mFM.EscribirTodosPer();
	}
	public void BBDD2TXT(){
		mFM.Añadir();
	}
	public void BBDD2TXTPer(){
		mFM.EscribirTodosPer();
	}
	
	
	//Llamadas a Hibernate_Manager
	
	public void BBDD2TXTHB(){
		mHM.EscribirTodos();
	}
	public void BBDD2TXTPerHB(){
		mHM.EscribirTodosPer();
	}
	public void ImprimirDatosHB() {
		mHM.LeerTodos();
	}
	
	public void ImprimirDatosPerHB() {
		mHM.LeerTodosPer();
	}
	public void InsertarHB() {
		mHM.Añadir();
	}
	public void InsertarPerHB() {
		mHM.AñadirPer();
	}
	
	
	
	public void Cargar_Inicio() {
		try {
			
			mVista.CargarMenuPrincipal();
			
		} catch ( IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
