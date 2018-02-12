package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Dzialkowicz;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.service.Admin_zarz_user_Service;

@RestController
public class Admin_zarz_user_RestController {
	
	@Autowired
	private Admin_zarz_user_Service admin_zarz_userService;
	
	@RequestMapping(path="/admin_zarz_user/get", method=RequestMethod.GET)
	public Iterable<Dzialkowicz> getAllDzialkowicz(){
		return admin_zarz_userService.getAllAdmin_zarz_user();
	}
    @RequestMapping(value = "/admin_zarz_user/get/{id}", method = RequestMethod.GET)
	public Dzialkowicz getDzialkowiczById(@PathVariable("id") long id){
		return admin_zarz_userService.getAdmin_zarz_userById(id);
	}


         
        @RequestMapping(value = "/admin_zarz_user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Dzialkowicz save(@RequestBody Dzialkowicz dzialkowicz){
 
  
 
     return  admin_zarz_userService.saveAdmin_zarz_user(dzialkowicz);
 
}
 @RequestMapping(value = "/admin_zarz_user/update",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Dzialkowicz update(@RequestBody Dzialkowicz dzialkowicz){
          
 
     return  admin_zarz_userService.saveAdmin_zarz_user(dzialkowicz);
 
}
        @RequestMapping(value ="/admin_zarz_user/delete", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void  delete(@RequestParam("id")  String id){
        this.admin_zarz_userService.deleteAdmin_zarz_user(Long.parseLong(id));
    }
}


