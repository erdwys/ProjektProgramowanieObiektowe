package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Informacja;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.service.Admin_zarz_powiadomienia_Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController
public class Admin_zarz_powiadomienia_RestController {
	
	@Autowired
	private Admin_zarz_powiadomienia_Service admin_zarz_powiadomieniaService;
	
	@RequestMapping(path="/admin_zarz_powiadomienia/get", method=RequestMethod.GET)
	public Iterable<Informacja> getAllDzialkowicz(){
		return admin_zarz_powiadomieniaService.getAllAdmin_zarz_powiadomienia();
	}
        
        @RequestMapping(path="/admin_zarz_powiadomienia/Adminget", method=RequestMethod.GET)
	public Iterable<Informacja> getAdminAllDzialkowicz(){
           Iterable powiadomienia = admin_zarz_powiadomieniaService.getAllAdmin_zarz_powiadomienia();
           ArrayList<Informacja> listaPowiadomien = new ArrayList();                 
 
           for(Object info : powiadomienia) {
             listaPowiadomien.add((Informacja) info);
             }
            listaPowiadomien.stream().filter((info) -> (info.getNadawca().equals("USER"))).forEachOrdered((info) -> {
                listaPowiadomien.remove(info);
            });
		return listaPowiadomien;
	}
        
        @RequestMapping(path="/admin_zarz_powiadomienia/Userget", method=RequestMethod.GET)
	public Iterable<Informacja> getUserAllDzialkowicz(){
         Iterable powiadomienia = admin_zarz_powiadomieniaService.getAllAdmin_zarz_powiadomienia();
           ArrayList<Informacja> listaPowiadomien = new ArrayList();          
           
        
     
           
           for(Object info : powiadomienia) {
             listaPowiadomien.add((Informacja) info);
             }
              while(listaPowiadomien.size()>0)
        {
            if(listaPowiadomien.get(listaPowiadomien.size()-1).getNadawca().equals("ADMIN"))
              listaPowiadomien.remove(listaPowiadomien.size()-1);
            
        }
            
		return listaPowiadomien;
	}
        
    @RequestMapping(value = "/admin_zarz_powiadomienia/get/{id}", method = RequestMethod.GET)
	public Informacja getDzialkowiczById(@PathVariable("id") long id){
		return admin_zarz_powiadomieniaService.getAdmin_zarz_powiadomieniaById(id);
	}


         
        @RequestMapping(value = "/admin_zarz_powiadomienia/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Informacja save(@RequestBody Informacja informacja){
 
  
 
     return  admin_zarz_powiadomieniaService.saveAdmin_zarz_powiadomienia(informacja);
 
}
         
        @RequestMapping(value = "/send_Form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Informacja send_Form(@RequestBody Informacja informacja){
 
  
 
     return  admin_zarz_powiadomieniaService.saveAdmin_zarz_powiadomienia(informacja);
 
}


 @RequestMapping(value = "/admin_zarz_powiadomienia/update",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Informacja update(@RequestBody Informacja informacja){
   
     return  admin_zarz_powiadomieniaService.saveAdmin_zarz_powiadomienia(informacja);
 
}
        @RequestMapping(value ="/admin_zarz_powiadomienia/delete", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void  delete(@RequestParam("id")  String id){
        this.admin_zarz_powiadomieniaService.deleteAdmin_zarz_powiadomienia(Long.parseLong(id));
    }
}


