package com.example.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static com.example.DataMigrationExcelToDatabase.readFromExcelAndSaveToDatabase;
import com.example.model.Dostep;
import com.example.model.Dzialki;
import com.example.model.imp.ImplDzialkowicz;
import com.example.model.Dzialkowicz;
import com.example.model.imp.ImplDostep;
import com.example.model.imp.ImplDzialki;
import com.example.service.Admin_zarz_powiadomienia_Service;
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
import java.time.LocalDateTime;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.model.Informacja;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
@Controller
public class WebController {
   
@Autowired
    private Admin_zarz_powiadomienia_Service admin_zarz_powiadomieniaService;

    
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
      @RequestMapping(value="/admin_zarz_powiadomienia")
    public String admin_zarz_powiadomienia(){
        return "admin_zarz_powiadomienia";
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
    
      @RequestMapping(value="/user_Powiadomienia")
    public String user_powiadomienia(){
        return "user_Powiadomienia";
    }
          @RequestMapping(value="/user_konto")
    public String user_konto(){
        return "user_konto";
    }
           @RequestMapping(value="/user_kontakt")
    public String user_kontakt(){
        return "user_kontakt";
    }
    
    
          @RequestMapping(value="/user_kontakt_historia")
    public String user_kontakt_historia(){
        return "user_kontakt_historia";
    }
    
    @RequestMapping(value = "/send_Form", method = RequestMethod.POST)
    public String send_Form(@RequestBody String string, Authentication authentication) {

        Dostep dostep = new Dostep();
        ImplDostep impdostep = new ImplDostep();
        dostep = impdostep.getByLogin(authentication.getName());
        ImplDzialkowicz impldzialkowicz = new ImplDzialkowicz();
        Dzialkowicz dzialkowiczLog = new Dzialkowicz();
        dzialkowiczLog = impldzialkowicz.getById(dostep.getNrDzialkowicza());
        Dzialki dzialki = new Dzialki();
        ImplDzialki impldzialki = new ImplDzialki();
        dzialki = impldzialki.getByIdDzialkowicz(dzialkowiczLog.getNrDzialkowicza());

        Iterable<Informacja> informacje = admin_zarz_powiadomieniaService.getAllAdmin_zarz_powiadomienia();
        ArrayList<Informacja> listaInformacji = new ArrayList();
        ArrayList<Informacja> listaInformacjiDotDzialki = new ArrayList();

        for (Object info : informacje) {
            listaInformacji.add((Informacja) info);
        }
        for (int i = 0; i < listaInformacji.size(); i++) {
            if (listaInformacji.get(i).getDzialki().getNrDzialki().equals(dzialki.getNrDzialki())) {
                listaInformacjiDotDzialki.add(listaInformacji.get(i));
            }
        }

        String[] infoFormat = string.split("=|\r");

        Informacja wiadomosc = new Informacja(Long.MIN_VALUE, "USER", dzialki, listaInformacjiDotDzialki.size(), Calendar.getInstance().get(Calendar.YEAR), 0D, "Data otrzymania: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + ", Nadawca: " + infoFormat[1] + ", Tytuł wiadomości: " + infoFormat[3] + ", Treść wiadomości: " + infoFormat[5] + "");
    admin_zarz_powiadomieniaService.saveAdmin_zarz_powiadomienia(wiadomosc);
        
        return "user_kontakt";

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