package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.DepartmentDaoJdbc;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* Sellers test
		Department obj = new Department(1, "Books");
		
		Seller seller = new Seller(1, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
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
	*/
		
		// Department tests
		System.out.println("===== TEST 1: department findAll =====");
		DepartmentDao dep = DaoFactory.createDepartmentDao();
		for(Department d : dep.findAll())
			System.out.println(d);
		
		System.out.println("\n===== TEST 2: department findById =====");
		System.out.print("Enter Department ID to find: ");
		int id = sc.nextInt();
		Department d = dep.findById(id);
		if(d != null)
			System.out.println(d);
		else
			System.out.println("Department no found.");
		
		System.out.println("\n===== TEST 3: department insert =====");
		sc.nextLine();
		System.out.print("Enter Department name to insert: ");	
		String depName = sc.nextLine();
		dep.insert(new Department(null, depName));

		System.out.println("\n===== TEST 4: department update =====");
		System.out.println("Enter Department id and new name to update: ");
		dep.update(new Department(8, "Furniture"));
		
		System.out.println("\n===== TEST 5: department delete =====");
		System.out.print("Enter Department id to delete: ");		
		id = sc.nextInt();
		dep.deleteById(id);
		
		
		
		
		sc.close();
		
	}

}
