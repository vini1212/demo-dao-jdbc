package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	//opera��o de inserir um objeto no meu banco de dados 
	void insert(Department obj);
	
	void update(Department obj);
	
	void deleteById(Integer id);
	
	Department findById(Integer id); //vai ser respons�vel em pegar esse id e consultar no banco de dados se cont�m um departamento com esse Id
	
	List<Department> findAll();
}
