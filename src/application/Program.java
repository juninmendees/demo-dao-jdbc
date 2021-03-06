package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: Seller findById ===");
		Seller seller = sellerDao.findById(2);
		
		System.out.println(seller);
		
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
		
		System.out.println("\n=== TEST 4: Seller Insert ===");
		Seller newseller = new Seller(null, "Anabelle","Anabelle@Gmail",new Date(),4000.0,departamento);
		sellerDao.insert(newseller);
		
		System.out.println("Inser??o realizada com sucesso! ID Gerado: " +newseller.getId());
		
		System.out.println("\n=== TEST 5: Seller Update ===");
		newseller = sellerDao.findById(9);
		newseller.setName("Martha Waine");
		sellerDao.update(newseller);
		
		System.out.println("Update completed");
		
		System.out.println("Inser??o realizada com sucesso! ID Gerado: " +newseller.getId());
		
		System.out.println("\n=== TEST 6: Seller Delete ===");
		System.out.println("Digite o ID do vendedor que ser? deletado");
		int idVendedor = sc.nextInt();
		
		sellerDao.deleteById(idVendedor);
		System.out.println("Vendedor ID "+idVendedor +" foi deletado!");
		

	}
}
