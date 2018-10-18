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
		
		p.setID(1);
		p.setNombre_Personaje("");
		p.setjuego();
		
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
			
		
		
			
		}
	
		s.getTransaction().commit();
		s.close();
		System.exit(0);
	}
	

}
