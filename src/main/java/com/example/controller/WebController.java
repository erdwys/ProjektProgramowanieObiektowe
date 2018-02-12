package com.example.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static com.example.DataMigrationExcelToDatabase.readFromExcelAndSaveToDatabase;
import com.example.model.imp.ImplDzialkowicz;
import com.example.model.Dzialkowicz;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WebController {
   
 


    
    @RequestMapping(value={"/","home"})
        public String home(){
            return "home";
        }
   
  
    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }
   
     @RequestMapping(value="/admin_zarz_user")
    public String admin_zarz_user(){
        return "admin_zarz_user";
    }
         @RequestMapping(value="/admin_zarz_dzialka")
    public String admin_zarz_dzialka(){
        return "admin_zarz_dzialka";
    }
    
     @RequestMapping(value="/admin_zarz_dostep")
           public String admin_zarz_dostep(){
        return "admin_zarz_dostep";
    }
           
         @RequestMapping(value="/admin_zarz_liczniki")
    public String admin_zarz_liczniki(){
        return "admin_zarz_liczniki";
    }
         @RequestMapping(value="/admin_upload")
    public String admin_upload(){
        return "admin_upload";
    }

     @PostMapping("/migration")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException, Exception {
        readFromExcelAndSaveToDatabase((FileInputStream) file.getInputStream());
              return "admin_upload";  
    }
    
         @RequestMapping(value="/admin_zarz_wyciagiJS")
    public String admin_zarz_wyciagiJS(){
        return "admin_zarz_wyciagiJS";
    }
      @RequestMapping(value="/admin_zarz_zobowiazania")
    public String admin_zarz_zobowiazania(){
        return "admin_zarz_zobowiazania";
    }
    
     @RequestMapping(value="/admin_zarz_bank")
    public String admin_zarz_bank(){
        return "admin_zarz_bank";
    }
    
     @RequestMapping(value="/user")
    public String user(){
        return "user";
    }
    
    
      @RequestMapping(value="/user_Rozrachunki")
    public String user_Rozrachunki(){
        return "user_Rozrachunki";
    }
       @RequestMapping(value="/user_Zobowiazania")
    public String user_Zobowiazania(){
        return "user_Zobowiazania";
    }
     
       @RequestMapping(value="/user_bank")
    public String user_bank(){
        return "user_bank";
    }
       @RequestMapping(value="/user_licznik")
    public String user_licznik(){
        return "user_licznik";
    }
    
          @RequestMapping(value="/user_ustawienia")
    public String user_ustawienia(){
        return "user_ustawienia";
    }
  
    @RequestMapping(value={"/login"})
    public String login(){
        return "login";
    }
   

   
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
}