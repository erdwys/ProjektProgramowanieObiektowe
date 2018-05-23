package com.example.controller;

import com.example.model.Dostep;
import com.example.model.Dzialki;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Dzialkowicz;
import com.example.model.Informacja;
import com.example.model.imp.ImplDostep;
import com.example.model.imp.ImplDzialki;
import com.example.model.imp.ImplInformacja;
import com.example.model.imp.ImplDzialkowicz;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.service.User_Service;
import com.example.service.User_powiadomienia_Service;
import com.example.service.User_rozrachunki_Service;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.security.core.Authentication;

@RestController
public class User_powiadomienia_RestController {

    @Autowired
    private User_powiadomienia_Service user_powiadomienia_Service;
    Dzialkowicz dzialkowiczLog = new Dzialkowicz();

    @RequestMapping(path = "/user_powiadomienia/Adminget", method = RequestMethod.GET)
    public Iterable<Informacja> getAllDzialkowiczOdAdmin(HttpServletRequest arg0, HttpServletResponse arg1,
            Authentication authentication) {
        Dostep dostep = new Dostep();
        ImplDostep impdostep = new ImplDostep();
        dostep = impdostep.getByLogin(authentication.getName());
        ImplDzialkowicz impldzialkowicz = new ImplDzialkowicz();
        dzialkowiczLog = impldzialkowicz.getById(dostep.getNrDzialkowicza());
        Dzialki dzialki = new Dzialki();
        ImplDzialki impldzialki = new ImplDzialki();
        dzialki = impldzialki.getByIdDzialkowicz(dzialkowiczLog.getNrDzialkowicza());
        List<Informacja> listaAll = new ArrayList<>();
        ImplInformacja implpowiadomienia = new ImplInformacja();
        listaAll = implpowiadomienia.getAll();
        List<Informacja> lista = new ArrayList<>();
        for (Informacja item : listaAll) {
            if (Objects.equals(item.getDzialki().getNrDzialki(), dzialki.getNrDzialki()) && Objects.equals(item.getNadawca(), "ADMIN")) {
                lista.add(item);
            }
        }
        return user_powiadomienia_Service.getUser_powiadomieniaDzialka(lista);
    }
    
     @RequestMapping(path = "/user_powiadomienia/Userget", method = RequestMethod.GET)
    public Iterable<Informacja> getAllDzialkowiczOdUser(HttpServletRequest arg0, HttpServletResponse arg1,
            Authentication authentication) {
        Dostep dostep = new Dostep();
        ImplDostep impdostep = new ImplDostep();
        dostep = impdostep.getByLogin(authentication.getName());
        ImplDzialkowicz impldzialkowicz = new ImplDzialkowicz();
        dzialkowiczLog = impldzialkowicz.getById(dostep.getNrDzialkowicza());
        Dzialki dzialki = new Dzialki();
        ImplDzialki impldzialki = new ImplDzialki();
        dzialki = impldzialki.getByIdDzialkowicz(dzialkowiczLog.getNrDzialkowicza());
        List<Informacja> listaAll = new ArrayList<>();
        ImplInformacja implpowiadomienia = new ImplInformacja();
        listaAll = implpowiadomienia.getAll();
        List<Informacja> lista = new ArrayList<>();
        for (Informacja item : listaAll) {
            if (Objects.equals(item.getDzialki().getNrDzialki(), dzialki.getNrDzialki())&& Objects.equals(item.getNadawca(), "USER")) {
                lista.add(item);
            }
        }
        return user_powiadomienia_Service.getUser_powiadomieniaDzialka(lista);
    }
    
    
   
}
