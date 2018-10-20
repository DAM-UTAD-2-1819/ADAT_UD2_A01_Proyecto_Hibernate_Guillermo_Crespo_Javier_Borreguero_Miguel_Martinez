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
import Modelo.Modelo;
import Modelo.personajes;
import Vistas.Inicio;

public class HibernateManager implements Intercambio {
	private static String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private static String archivo_personajes = "src/Modelo/personajes.txt";
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> listaPersonajes = new HashMap<Integer, Personajes>();
	Inicio mVista = new Inicio();
	public SessionFactory sf = new Configuration().configure().buildSessionFactory();
	public Session s = sf.openSession();

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

			s.beginTransaction();
			Query q = s.createQuery("Select v from videojuegos v");
			List resultado = q.list();
			Iterator videojuegositerador = resultado.iterator();

			while (videojuegositerador.hasNext()) {
				videojuegos vdo = (videojuegos) videojuegositerador.next();
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
				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre_Personaje() + "\n"
						+ "videojuego: " + entry.getValue().getID_Juego() + "\n");
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
		videojuegos v = new videojuegos();
		Controlador mControlador = new Controlador();

		s.beginTransaction();
		mVista.PedirDatosHB(ListaVideojuegos);
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

			v.setID(entry.getKey());
			v.setNombre(entry.getValue().getNombre());
			v.setFecha_Lanzamiento(entry.getValue().getFecha_Lanzamiento());
			v.setPlataforma(entry.getValue().getPlataforma());
			v.setDesarrollador(entry.getValue().getDesarrollador());
		}
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getPlataforma();
		v.getDesarrollador();

		s.save(v);
		s.getTransaction().commit();
		s.close();
		mControlador.Cargar_Inicio();
		return ListaVideojuegos;
	}

	@Override
	public HashMap<Integer, Personajes> AñadirPer() {
		personajes p = new personajes();
		Controlador mControlador = new Controlador();
		s.beginTransaction();
		mVista.PedirDatosPerHB(listaPersonajes);
		for (Entry<Integer, Personajes> entry: listaPersonajes.entrySet()) {
			p.setID(entry.getKey());
			p.setNombre_Personaje(entry.getValue().getNombre_Personaje());
			p.setjuego(entry.getValue().getID_Juego());
		}
		p.getID();
		p.getNombre_Personaje();
		p.getjuego();
		s.save(p);
		s.getTransaction().commit();
		s.close();
		mControlador.Cargar_Inicio();
		return listaPersonajes;
	}

	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		videojuegos v = new videojuegos();
		Controlador mControlador = new Controlador();
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getDesarrollador();
		v.getPlataforma();
		try {

			s.beginTransaction();
			Query q = s.createQuery("Select v from videojuegos v");
			List resultado = q.list();
			Iterator videojuegositerador = resultado.iterator();

			while (videojuegositerador.hasNext()) {
				videojuegos vdo = (videojuegos) videojuegositerador.next();
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

			mVista.sacarPantalla(ListaVideojuegos);
			mControlador.Cargar_Inicio();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;
	}

	@Override
	public HashMap<Integer, Personajes> LeerTodosPer() {
		Controlador mControlador = new Controlador();
		personajes p = new personajes();
		p.getID();
		p.getNombre_Personaje();
		p.getjuego();
		try {

			s.beginTransaction();
			Query q = s.createQuery("Select p from personajes p");
			List resultado = q.list();
			Iterator personajesIterator = resultado.iterator();

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

			mVista.sacarPantallaPer(listaPersonajes);
			mControlador.Cargar_Inicio();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPersonajes;
	}

}
