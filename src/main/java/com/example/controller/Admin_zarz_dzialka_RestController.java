package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Dzialki;
import com.example.model.Iban;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.service.Admin_zarz_dzialka_Service;
import java.util.ArrayList;

@RestController
public class Admin_zarz_dzialka_RestController {
	
	@Autowired
	private Admin_zarz_dzialka_Service admin_zarz_dzialkaService;
	
	@RequestMapping(path="/admin_zarz_dzialka/get", method=RequestMethod.GET)
	public Iterable<Dzialki> getAllDzialkowicz(){
		return admin_zarz_dzialkaService.getAllAdmin_zarz_dzialka();
	}
    @RequestMapping(value = "/admin_zarz_dzialka/get/{id}", method = RequestMethod.GET)
	public Dzialki getDzialkowiczById(@PathVariable("id") long id){
		return admin_zarz_dzialkaService.getAdmin_zarz_dzialkaById(id);
	}


         
        @RequestMapping(value = "/admin_zarz_dzialka/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Dzialki save(@RequestBody Dzialki dzialka){
 
       Iterable<Dzialki> dzialki =  admin_zarz_dzialkaService.getAllAdmin_zarz_dzialka();
           ArrayList<Dzialki> listaDzialek = new ArrayList();
     
        for (Object info : dzialki) {
            listaDzialek.add((Dzialki) info);
        }
       
  dzialka.setNrDzialki(listaDzialek.get(listaDzialek.size()-1).getNrDzialki()+1);
 
     return  admin_zarz_dzialkaService.saveAdmin_zarz_dzialka(dzialka);
 
}
 @RequestMapping(value = "/admin_zarz_dzialka/update",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Dzialki update(@RequestBody Dzialki dzialki){
          
 
     return  admin_zarz_dzialkaService.saveAdmin_zarz_dzialka(dzialki);
 
}
        @RequestMapping(value ="/admin_zarz_dzialka/delete", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void  delete(@RequestParam("id")  String id){
        this.admin_zarz_dzialkaService.deleteAdmin_zarz_dzialka(Long.parseLong(id));
    }
}


