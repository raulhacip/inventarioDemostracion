package com.practica.inventario.service;

import org.springframework.http.ResponseEntity;

import com.practica.inventario.model.Elemento;
import com.practica.inventario.response.ElementoResponseRest;

public interface IElementoService {

	public ResponseEntity<ElementoResponseRest> search();
	public ResponseEntity<ElementoResponseRest> searchById(Long id);
	public ResponseEntity<ElementoResponseRest> save(Elemento Elemento);
	public ResponseEntity<ElementoResponseRest> update(Elemento Elemento,Long id);
	public ResponseEntity<ElementoResponseRest> deleteById(Long id);	
	
}
