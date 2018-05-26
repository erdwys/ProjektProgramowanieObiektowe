package com.example.service;

import java.util.List;

import com.example.model.Informacja;

public interface User_powiadomienia_Service {
	
 Iterable<Informacja> getAllUser_powiadomienia();
	 Informacja getUser_powiadomieniaById(long id);
 Iterable<Informacja> getUser_powiadomieniaDzialka(List<Informacja> lista);
	
}
