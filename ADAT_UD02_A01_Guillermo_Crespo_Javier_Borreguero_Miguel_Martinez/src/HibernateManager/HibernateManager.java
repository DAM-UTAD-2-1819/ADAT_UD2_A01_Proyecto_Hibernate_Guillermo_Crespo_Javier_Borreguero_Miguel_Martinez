package HibernateManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Controlador.Controlador;
import Interface.Intercambio;
import Modelo.videojuegos;
import Videojuegos.Personajes;
import Videojuegos.Videojuego;
import Modelo.personajes;
import Vistas.Inicio;

public class HibernateManager implements Intercambio  {
	private static String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private static String archivo_personajes = "src/Modelo/personajes.txt";
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> listaPersonajes = new HashMap<Integer, Personajes>();
	
		// TODO Auto-generated method stub
		@Override
		public HashMap<Integer, Videojuego> EscribirTodos() {
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

		public HashMap<Integer, Personajes> EscribirTodosPer() {
			// Copiar la tabla personajes de BBDD en el fichero
			personajes p = new personajes();
			Controlador mControlador = new Controlador();
			p.getID();
			p.getNombre_Personaje();
			p.getjuego();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_personajes, false));
				SessionFactory sf = new Configuration().configure().buildSessionFactory();
				Session s = sf.openSession();
				s.beginTransaction();
				Query q = s.createQuery("Select p from personajes p");
				List resultado2 = q.list();
				Iterator personajesIterator = resultado2.iterator();
				while (personajesIterator.hasNext()) {
					personajes pnj = (personajes) personajesIterator.next();
					int ID = pnj.getID();
					String nombre_Personaje = pnj.getNombre_Personaje();
					int juego = pnj.getjuego();
					Personajes mPersonajes = new Personajes(nombre_Personaje, juego);
					listaPersonajes.put(ID, mPersonajes);
					
				}
				s.getTransaction().commit();
				s.close();
				for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {
					bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre_Personaje()
							+ "videojuego: " + entry.getValue().getID_Juego());
				}
				bw.close();
				mControlador.Cargar_Inicio();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return listaPersonajes;

		}

		@Override
		public HashMap<Integer, Videojuego> Añadir() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HashMap<Integer, Videojuego> LeerTodos() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HashMap<Integer, Personajes> LeerTodosPer() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HashMap<Integer, Personajes> AñadirPer() {
			// TODO Auto-generated method stub
			return null;
		}
	
	}

