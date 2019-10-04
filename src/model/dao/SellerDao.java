package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	//opera��o de inserir um objeto no meu banco de dados 
		void insert(Seller obj);
		
		void update(Seller obj);
		
		void deleteById(Integer id);
		
		Seller findById(Integer id); //vai ser respons�vel em pegar esse id e consultar no banco de dados se cont�m um departamento com esse Id
		
		List<Seller> findAll();
}
