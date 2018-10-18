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
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Controlador.Controlador;
import Modelo.videojuegos;
import Videojuegos.Videojuego;
import Modelo.Modelo;
import Modelo.personajes;
import Vistas.Inicio;

public class HibernateManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		videojuegos v = new videojuegos();

		
		
		
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getDesarrollador();
		v.getPlataforma();
		
		
		personajes  p = new personajes();
		


		v.setID(1);
		v.setNombre("AnaMari");
		v.setFecha_Lanzamiento("");
		v.setDesarrollador("");
		v.setPlataforma("");

		personajes p1 = new personajes();

		p1.setID(1);
		p1.setNombre_Personaje("");
		p1.setjuego();
        
		//Creación query necesaria para leer los datos de la tabla videojuegos
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		Query q = s.createQuery("Select v from videojuegos v");
		
		//s.save(e);
		// s.update(e);

		List resultado = q.list();
		Iterator empleadositerador = resultado.iterator();
		while (empleadositerador.hasNext()) {
			videojuegos vdo = (videojuegos) empleadositerador.next();
			int ID = vdo.getID();
			
		
			System.out.println("id: " + vdo.getID() + "\n" + "Nombre: " + vdo.getNombre() + "\n" + "Fecha Lanzamiento: "
					+ vdo.getFecha_Lanzamiento() + "\n" + "Desarollador: " + vdo.getDesarrollador() + "\n"
					+ "Plataforma: " + vdo.getPlataforma() + "\n");
		}
		s.getTransaction().commit();
		s.close();
		System.exit(0);
		
		//Creación query para leer los datos de la tabla personajes
		
		SessionFactory sf2 = new Configuration().configure().buildSessionFactory();
		Session s2 = sf2.openSession();
		s2.beginTransaction();
		Query q2 = s2.createQuery("Select p from personajes p");
		List resultado2 = q2.list();
		Iterator personajesIterator = resultado2.iterator();
		while (personajesIterator.hasNext()) {
			personajes pnj = (personajes) personajesIterator.next();
		}
		
	}
	

}
