package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

//Nosso Dao vai ter uma dependência com a conexão 
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
		ResultSet rs = null; //aponta para posição 0
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
				Department dep = instantiateDepartment(rs); 
				Seller obj = instantiateSeller(rs, dep);	
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
			
			//não precisa fechar a conexão, porque o mesmo objeto pode servir para realizar mais de uma operação 
		}
		
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); //queremos um objeto department montado e fizemos ali encima ele 
		
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException { //vai propagar a exceção porque está sendo tratada encima
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId")); //colocar igual ao que está no banco de dados
		dep.setName(rs.getString("DepName")); //instanciamos o departamento e setamos os valores dele
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null; //aponta para posição 0
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "order by Name");
			
			/*Esse comando sql vai ser executado e o resultado vai cair dentro do meu ResultSet
			 * */

			rs = st.executeQuery(); //executa uma consulta sql
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>(); //criou um map vazio para guardar qualquer departamento que instanciar
			
			while(rs.next()) { //tem que percorrer meu ResultSet enquanto tiver um próximo
				//se não existir ai sim vou precisar instanciar o departamento
				Department dep = map.get(rs.getInt("DepartmentId")); //tenta buscar com o método get um departamento que tenha esse Id
				
				//se o departamento não existir ai sim ele vai instanciar e vai salvar o departamento no map 
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
								 
				Seller obj = instantiateSeller(rs, dep);	
				list.add(obj);
				
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
			//não precisa fechar a conexão, porque o mesmo objeto pode servir para realizar mais de uma operação 
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null; //aponta para posição 0
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "order by Name");
			
			/*Esse comando sql vai ser executado e o resultado vai cair dentro do meu ResultSet
			 * */
			
			st.setInt(1, department.getId());
			rs = st.executeQuery(); //executa uma consulta sql
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>(); //criou um map vazio para guardar qualquer departamento que instanciar
			
			while(rs.next()) { //tem que percorrer meu ResultSet enquanto tiver um próximo
				//se não existir ai sim vou precisar instanciar o departamento
				Department dep = map.get(rs.getInt("DepartmentId")); //tenta buscar com o método get um departamento que tenha esse Id
				
				//se o departamento não existir ai sim ele vai instanciar e vai salvar o departamento no map 
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
								 
				Seller obj = instantiateSeller(rs, dep);	
				list.add(obj);
				
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
			//não precisa fechar a conexão, porque o mesmo objeto pode servir para realizar mais de uma operação 
		}
	}
	
}
