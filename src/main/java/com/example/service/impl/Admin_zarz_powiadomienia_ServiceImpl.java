package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Informacja;
import com.example.repository.Admin_zarz_powiadomienia_Repository;
import com.example.service.Admin_zarz_powiadomienia_Service;


@Service("Admin_zarz_powiadomienia_Service")
public class Admin_zarz_powiadomienia_ServiceImpl implements Admin_zarz_powiadomienia_Service {

	@Autowired
	private Admin_zarz_powiadomienia_Repository admin_zarz_powiadomienia_Repository;

        
         @Autowired
    public void setContactRepository(Admin_zarz_powiadomienia_Repository admin_zarz_powiadomienia_Repository) {
        this.admin_zarz_powiadomienia_Repository = admin_zarz_powiadomienia_Repository;
    }
        
        
	@Override
	public  Iterable<Informacja> getAllAdmin_zarz_powiadomienia() {
		return admin_zarz_powiadomienia_Repository.findAll();
	}

	@Override
	public Informacja getAdmin_zarz_powiadomieniaById(long id) {
		return admin_zarz_powiadomienia_Repository.findOne(id);
	}
    
    @Override
    public Informacja saveAdmin_zarz_powiadomienia(Informacja informacja) {
        return this.admin_zarz_powiadomienia_Repository.save(informacja);
    }
    
    @Override
    public void deleteAdmin_zarz_powiadomienia(long id) {
        this.admin_zarz_powiadomienia_Repository.delete(id);
    }

  
}
