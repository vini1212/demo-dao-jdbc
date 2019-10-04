package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	//esse método não vai precisa expor a implementação e sim deixando apenas a interface que deseja implementar
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
