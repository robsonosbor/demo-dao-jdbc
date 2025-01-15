package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		//Department obj = new Department(1, "Books");
		
		//Seller seller = new Seller(1, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("===== TEST 1: seller findById =====");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		System.out.println("\n===== TEST 2: seller findByDepartment =====");
		List<Seller> sellers = sellerDao.findByDepartment(new Department(2, null));
		
		for (Seller seller2 : sellers) {
			System.out.println(seller2);
		}
		
		System.out.println("\n===== TEST 3: seller findAll =====");
		sellers = sellerDao.findAll();
		
		for (Seller seller2 : sellers) {
			System.out.println(seller2);
		}
		
		System.out.println("\n===== TEST 4: seller insert =====");
		Seller selllerInsert = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, new Department(2, null));
		//sellerDao.insert(selllerInsert);
		
		System.out.println("\n===== TEST 5: seller update =====");
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");
		sellerDao.update(seller);
		System.out.println("Update completed");
		
		System.out.println("\n===== TEST 6: seller delete =====");
		sellerDao.deleteById(11);
		System.out.println("Delete completed");

	}

}
