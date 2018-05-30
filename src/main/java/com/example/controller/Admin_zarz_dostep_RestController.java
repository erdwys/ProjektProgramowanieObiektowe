package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Dostep;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.service.Admin_zarz_dostep_Service;
import java.util.ArrayList;

@RestController
public class Admin_zarz_dostep_RestController {
	
	@Autowired
	private Admin_zarz_dostep_Service admin_zarz_dostepService;
	
	@RequestMapping(path="/admin_zarz_dostep/get", method=RequestMethod.GET)
	public Iterable<Dostep> getAllDzialkowicz(){
		return admin_zarz_dostepService.getAllAdmin_zarz_dostep();
	}
    @RequestMapping(value = "/admin_zarz_dostep/get/{id}", method = RequestMethod.GET)
	public Dostep getDzialkowiczById(@PathVariable("id") long id){
		return admin_zarz_dostepService.getAdmin_zarz_dostepById(id);
	}


         
        @RequestMapping(value = "/admin_zarz_dostep/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Dostep save(@RequestBody Dostep dostep){
 
  
 
     return  admin_zarz_dostepService.saveAdmin_zarz_dostep(dostep);
 
}
 @RequestMapping(value = "/admin_zarz_dostep/update",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Dostep update(@RequestBody Dostep dostep){
    
       Dostep dostep1 = dostep; 
  this.admin_zarz_dostepService.deleteAdmin_zarz_dostep(dostep.getDzialkowicz().getNrDzialkowicza());
  
     return  admin_zarz_dostepService.saveAdmin_zarz_dostep(dostep1);
 
}
        @RequestMapping(value ="/admin_zarz_dostep/delete", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void  delete(@RequestParam("id")  String id){
        this.admin_zarz_dostepService.deleteAdmin_zarz_dostep(Long.parseLong(id));
    }
}


