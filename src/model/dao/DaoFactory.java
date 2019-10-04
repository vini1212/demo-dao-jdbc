package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	//esse m�todo n�o vai precisa expor a implementa��o e sim deixando apenas a interface que deseja implementar
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
