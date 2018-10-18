package HibernateManager;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Modelo.videojuegos;
import Modelo.personajes;

public class HibernateManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		videojuegos v = new videojuegos();
		
		v.setID(1);
		v.setNombre("AnaMari");
		v.setFecha_Lanzamiento("");
		v.setDesarrollador("");
		v.setPlataforma("");
		
		
		personajes  p = new personajes();
		
		p.setID(1);
		p.setNombre_Personaje("");
		p.setjuego();
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		Query q = s.createQuery("Select v from videojuegos v");
		// s.save(e);
		// s.update(e);

		List resultado = q.list();
		Iterator empleadositerador = resultado.iterator();
		while (empleadositerador.hasNext()) {
			videojuegos vdo = (videojuegos) empleadositerador.next();
			System.out.println("id: "+vdo.getID()+"\n"  +   "Nombre: "+vdo.getNombre()+"\n"   +"Fecha Lanzamiento: "+vdo.getFecha_Lanzamiento()+"\n"   
			+ "Desarollador: "+vdo.getDesarrollador()+"\n"  + "Plataforma: "+vdo.getPlataforma()+"\n" );
		}
	
		s.getTransaction().commit();
		s.close();
		System.exit(0);
	}

}
