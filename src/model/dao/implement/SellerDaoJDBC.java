package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	private Connection conexao;
	
	public SellerDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Seller seller) {
		
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			st =conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			
			if (rs.next()) {
				Department departamento = instantiateDepartment(rs);
				Seller seller = instantiateSeller (rs);
				return seller;
			}
			else {
				System.out.println("Nenhum vendedor encontrado");
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
		
	
	}

	private Seller instantiateSeller(ResultSet rs) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(instantiateDepartment(rs));
		return seller;
	}
	
	private Seller instantiateSeller(ResultSet rs, Department departament) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(instantiateDepartment(rs));
		return seller;
	}


	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department departamento = new Department();
		departamento.setId(rs.getInt("DepartmentId"));
		departamento.setName(rs.getString("DepName"));
		return departamento;
	}

	@Override
	public List<Seller> findAll() {

		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st =conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id " 
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name "); 
			
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List <Seller> listaVendedores = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = instantiateSeller (rs,dep);
				listaVendedores.add(seller);
				
			}

			return listaVendedores;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	
	}

}
