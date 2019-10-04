package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

//Nosso Dao vai ter uma depend�ncia com a conex�o 
public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null; //aponta para posi��o 0
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					);
			
			/*Esse comando sql vai ser executado e o resultado vai cair dentro do meu ResultSet
			 * */
			
			st.setInt(1, id);
			rs = st.executeQuery(); //executa uma consulta sql
			
			if(rs.next()) { //se retornar verdadeiro ele vai ter retornado a tabela
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId")); //colocar igual ao que est� no banco de dados
				dep.setName(rs.getString("DepName")); //instanciamos o departamento e setamos os valores dele 
				Seller obj = new Seller();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(dep); //queremos um objeto department montado e fizemos ali encima ele 
				
				return obj; //retorna o obj Seller
				
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
			//n�o precisa fechar a conex�o, porque o mesmo objeto pode servir para realizar mais de uma opera��o 
		}
		
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
