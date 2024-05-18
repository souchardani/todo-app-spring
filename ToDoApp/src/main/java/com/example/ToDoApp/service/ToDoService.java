package com.example.ToDoApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.repo.IToDoRepo;

@Service
public class ToDoService {
	
	@Autowired
	IToDoRepo repo;
	
	public List<ToDo> getAllToDoItems() {
		List<ToDo> ToDoList = new ArrayList<>();
		repo.findAll().forEach(todo -> ToDoList.add(todo));
		
		return ToDoList;
		
	};
	
	public ToDo getToDoItemById(Long id){
		return repo.findById(id).get();
		
	};
	
	public boolean updateStatus(Long id){
		ToDo todo = getToDoItemById(id);
		todo.setStatus("completed");
		
		return saveOrUpdateToDoItem(todo);
	};
	
	public boolean saveOrUpdateToDoItem(ToDo todo){
		ToDo updatedObj = repo.save(todo);
		if (getToDoItemById(updatedObj.getId()) != null) {
			return true;
		}
		return false;
		
	};
	
	public boolean deleteToDoItem(Long id){
		repo.deleteById(id);
		if (repo.findById(id).isEmpty()) {
			return true;
		}	
		return false;
		
		
	};
}
