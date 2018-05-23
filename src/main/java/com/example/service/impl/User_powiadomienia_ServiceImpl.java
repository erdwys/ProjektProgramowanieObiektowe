package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Informacja;
import com.example.repository.User_powiadomienia_Repository;
import com.example.service.User_powiadomienia_Service;
import java.util.ArrayList;


@Service("User_powiadomienia_Service")
public class User_powiadomienia_ServiceImpl implements User_powiadomienia_Service {

	@Autowired
	private User_powiadomienia_Repository user_powiadomienia_Repository;

        
         @Autowired
    public void setContactRepository(User_powiadomienia_Repository user_powiadomienia_Repository) {
        this.user_powiadomienia_Repository = user_powiadomienia_Repository;
    }
        @Override
	public  Iterable<Informacja> getAllUser_powiadomienia() {
		return user_powiadomienia_Repository.findAll();
	}
     
	@Override
	public Informacja getUser_powiadomieniaById(long id) {
		return user_powiadomienia_Repository.findOne(id);
	}
        
        @Override
	public Iterable<Informacja> getUser_powiadomieniaDzialka(List<Informacja> lista) {
            List<Long> listID = new ArrayList<>();
            for(Informacja item : lista){
	listID.add(item.getIdInformacja());
                
            }
        Iterable<Long> iterableID =listID;

            
		return user_powiadomienia_Repository.findAll(iterableID);
	}
}