package com.example.service;

import java.util.List;

import com.example.model.Informacja;

public interface Admin_zarz_powiadomienia_Service {
	
	  Iterable<Informacja> getAllAdmin_zarz_powiadomienia();
	 Informacja getAdmin_zarz_powiadomieniaById(long id);
          Informacja saveAdmin_zarz_powiadomienia(Informacja informacja);
          void deleteAdmin_zarz_powiadomienia(long id);
	
}
