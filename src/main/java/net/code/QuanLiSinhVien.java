package net.code;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QuanLiSinhVien {

	 static EntityManagerFactory factory;
	 static EntityManager entityManager;
	public static void main(String[] args) {
		
		begin();
		
		//create();
		//update();
		find();
		end();
		
		

	}

	

	
	private static void begin() {
			factory = Persistence.createEntityManagerFactory("quanlisinhvienUnit");
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
		}
	private static void create() {
		DsLop dsLop= new DsLop();
		dsLop.setMa_sv("18424024");
		dsLop.setHo_ten("Huỳnh Văn Hậu");
		dsLop.setGioi_tinh("Nam");
		dsLop.setCmnd("215360350");
		dsLop.setLop("18HCB");
		entityManager.persist(dsLop);
	}
	private static void update() {
		
		DsLop existLop = new DsLop();
		existLop.setMa_sv("18424024");
		existLop.setHo_ten("Nguyễn Văn A");
		existLop.setGioi_tinh("Nam");
		existLop.setCmnd("123455678");
		existLop.setLop("18HCB");
		entityManager.merge(existLop);	}
	
	private static void find() {
		
		String primaryKey = "18424024";
		DsLop lop = entityManager.find(DsLop.class, primaryKey);
		System.out.println(lop.getMa_sv());
		System.out.println(lop.getHo_ten());
		System.out.println(lop.getGioi_tinh());
		System.out.println(lop.getCmnd());
		System.out.println(lop.getLop());
	}
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
