package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
//		System.out.println("=== TEST 1: Seller findById ===");
//		Seller seller = sellerDao.findById(2);
//		
//		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: Seller findByDepartament ===");
		Department departamento = new Department(2, null);
		List <Seller> listaVendedor = sellerDao.findByDepartment(departamento);
		
		for(Seller vendedor: listaVendedor ) {
			System.out.println(vendedor);
		}
		
		
		System.out.println("\n=== TEST 3: Seller findAll ===");
	
		List <Seller> listatodos = sellerDao.findAll();
		
		for(Seller vendedor: listatodos ) {
			System.out.println(vendedor);
		}
	}

}
