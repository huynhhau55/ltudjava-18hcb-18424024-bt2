package net.code;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class QuanLiSinhVien {

	private static SessionFactory sessionFactory;
	public static void main(String[] args) {
		
		if(sessionFactory == null) {
			
		  Configuration configuration = new Configuration().configure();
		  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		  sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
		  
		  Session session = sessionFactory.openSession();
	      session.beginTransaction();
	      
	      Lop lop = new Lop("18HCB");
	      SinhVien sv1 = new SinhVien("18424024","Huỳnh Văn Hậu", "Nam", "18424024","18424024",lop);
	      SinhVien sv2 = new SinhVien("18424025","Nguyễn Văn A", "Nam", "18424025","18424025",lop);
	      SinhVien sv3 = new SinhVien("18424026","Trần Văn B", "Nam", "18424026","18424026",lop);
	      Set<SinhVien> sinhViens = new HashSet<SinhVien>();
	      sinhViens.add(sv1);
	      sinhViens.add(sv2);
	      sinhViens.add(sv3);
	      
	      lop.setSinhViens(sinhViens);
	      session.save(lop);
	      session.getTransaction().commit();
	      session.close();  
		}
	}

}
