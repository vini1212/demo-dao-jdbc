package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	//esse método não vai precisa expor a implementação e sim deixando apenas a interface que deseja implementar
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	//conexao com o banco de dados
	public static DepartmentDao createDeparmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
