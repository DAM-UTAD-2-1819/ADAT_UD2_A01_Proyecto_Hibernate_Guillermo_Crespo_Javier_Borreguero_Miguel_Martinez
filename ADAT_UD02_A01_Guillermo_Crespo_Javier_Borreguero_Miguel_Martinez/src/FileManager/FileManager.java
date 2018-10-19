package FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Controlador.Controlador;

import java.util.Scanner;

import Interface.Intercambio;
import Modelo.Modelo;
import Modelo.personajes;
import Modelo.videojuegos;
import Videojuegos.Personajes;
import Videojuegos.Videojuego;
import Vistas.Inicio;

public class FileManager implements Intercambio {
	private static final int id_Juego = 0;
	private String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private String archivo_personajes = "src/Modelo/personajes.txt";
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> listaPersonajes = new HashMap<Integer, Personajes>();
	Inicio mVista = new Inicio();

	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		FileReader fr = null;
		BufferedReader br = null;
		Controlador mControlador = new Controlador();
		try {
			fr = new FileReader(archivo_videojuegos);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null)
				System.out.println(linea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		mControlador.Cargar_Inicio();

		return ListaVideojuegos;
		// TODO Auto-generated method stub

	}

	public HashMap<Integer, Personajes> LeerTodosPer() {
		FileReader fr = null;
		BufferedReader br = null;
		Controlador mControlador = new Controlador();
		try {
			fr = new FileReader(archivo_personajes);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null)
				System.out.println(linea);
		} catch (IOException e) {

			e.printStackTrace();

		}
		mControlador.Cargar_Inicio();

		return listaPersonajes;

	}

	@Override
	public HashMap<Integer, Videojuego> EscribirTodos() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		Controlador mControlador = new Controlador();

		try {
			mVista.PedirDatosF(ListaVideojuegos);
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_videojuegos, true));
			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre() + "\n"
						+ "Plataforma: " + entry.getValue().getPlataforma() + "\n" + "Fecha de Lanzamiento: "
						+ entry.getValue().getFecha_Lanzamiento() + "\n" + "Desarrollador: "
						+ entry.getValue().getDesarrollador() + "\n");
			}

			bw.close();
			mControlador.Cargar_Inicio();

		} catch (IOException e1) {

			e1.printStackTrace();
		}

		return ListaVideojuegos;

	}

	public HashMap<Integer, Personajes> EscribirTodosPer() {

		Controlador mControlador = new Controlador();

		try {
			mVista.PedirDatoPerF(listaPersonajes);
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_personajes, true));
			for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {
				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre_Personaje() + "\n"
						+ "id_juego: " + entry.getValue().getID_Juego() + "\n");
			}
			bw.close();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		mControlador.Cargar_Inicio();
		return listaPersonajes;
	}

	@Override
	public HashMap<Integer, Videojuego> Añadir() {
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_videojuegos, false));

			PreparedStatement pstm;
			String cargar = "Select * from videojuegos";
			ResultSet rset;
			pstm = mModelo.conexion.prepareStatement(cargar);
			rset = pstm.executeQuery();
			while (rset.next()) {
				int id1 = rset.getInt(1);
				String Nombre = rset.getString("Nombre");
				String Fecha = rset.getString("Fecha_Lanzamiento");
				String Desarrollador = rset.getString("Desarrollador");
				String Plataforma = rset.getString("Plataforma");
				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(id1, mVideojuego);

			}

			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre() + "\n"
						+ "Fecha de Lanzamiento: " + entry.getValue().getFecha_Lanzamiento() + "\n" + "Desarrollador: "
						+ entry.getValue().getDesarrollador() + "\n" + "Plataforma: " + entry.getValue().getPlataforma()
						+ "\n");
			}
			bw.close();
			mControlador.Cargar_Inicio();
		} catch (IOException | SQLException e1) {

			e1.printStackTrace();
		}
		return ListaVideojuegos;
	}

	@Override
	public HashMap<Integer, Personajes> AñadirPer() {

		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_personajes, false));

			PreparedStatement pstm;
			String cargar = "Select * from personajes";
			ResultSet rset;
			pstm = mModelo.conexion.prepareStatement(cargar);
			rset = pstm.executeQuery();
			while (rset.next()) {
				int id1 = rset.getInt(1);
				String Nombre = rset.getString("Nombre_Personaje");
				int id_juego = rset.getInt("ID_Juego");
				Personajes mPersonaje = new Personajes(Nombre, id_juego);

				listaPersonajes.put(id1, mPersonaje);

			}

			for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {

				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre_Personaje() + "\n"
						+ "ID_Juego: " + entry.getValue().getID_Juego() + "\n");
			}
			bw.close();
			mControlador.Cargar_Inicio();
		} catch (IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaPersonajes;
	}

	@Override
	public HashMap<Integer, Videojuego> EscribirTodosHB() {
		// Copiar la tabla videojuegos de BB en el fichero
		videojuegos v = new videojuegos();

		Controlador mControlador = new Controlador();
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getDesarrollador();
		v.getPlataforma();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_videojuegos, false));

			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			Query q = s.createQuery("Select v from videojuegos v");
			List resultado = q.list();
			Iterator empleadositerador = resultado.iterator();

			while (empleadositerador.hasNext()) {
				videojuegos vdo = (videojuegos) empleadositerador.next();
				int ID = vdo.getID();
				String Nombre = vdo.getNombre();
				String Fecha = vdo.getFecha_Lanzamiento();
				String Plataforma = vdo.getPlataforma();
				String Desarrollador = vdo.getDesarrollador();

				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(ID, mVideojuego);
			}

			s.getTransaction().commit();
			s.close();

			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre() + "\n"
						+ "Fecha de Lanzamiento: " + entry.getValue().getFecha_Lanzamiento() + "\n" + "Desarrollador: "
						+ entry.getValue().getDesarrollador() + "\n" + "Plataforma: " + entry.getValue().getPlataforma()
						+ "\n");
			}

			bw.close();
			mControlador.Cargar_Inicio();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		return ListaVideojuegos;

	}

	public HashMap<Integer, Personajes> EscribirTodosPerHB() {
		// Copiar la tabla personajes de BBDD en el fichero
		personajes p = new personajes();
		Controlador mControlador = new Controlador();
		p.getID();
		p.getNombre_Personaje();
		p.getjuego();
		try {
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(archivo_personajes, false));
			SessionFactory sf2 = new Configuration().configure().buildSessionFactory();
			Session s2 = sf2.openSession();
			s2.beginTransaction();
			Query q2 = s2.createQuery("Select p from personajes p");
			List resultado2 = q2.list();
			Iterator personajesIterator = resultado2.iterator();
			while (personajesIterator.hasNext()) {
				personajes pnj = (personajes) personajesIterator.next();
				int ID = pnj.getID();
				String nombre_Personaje = pnj.getNombre_Personaje();
				int juego = pnj.getjuego();
				Personajes mPersonajes = new Personajes(nombre_Personaje, juego);
				listaPersonajes.put(ID, mPersonajes);
				
			}
			s2.getTransaction().commit();
			s2.close();
			for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {
				bw2.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre_Personaje()
						+ "videojuego: " + entry.getValue().getID_Juego());
			}
			bw2.close();
			mControlador.Cargar_Inicio();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaPersonajes;

	}

	@Override
	public HashMap<Integer, Videojuego> AñadirHB() {
		return null;

	}

	@Override
	public HashMap<Integer, Videojuego> LeerTodosHB() {
		// TODO Auto-generated method stub
		return null;
	}

}
