package com.practica.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.inventario.model.Elemento;
import com.practica.inventario.response.ElementoResponseRest;
import com.practica.inventario.service.IElementoService;

@RestController
@RequestMapping("/api/inventario")
public class ElementoRestController {
	
	@Autowired
	IElementoService elementoService;
	
	@GetMapping("/elemento")
	public ResponseEntity <ElementoResponseRest> searchElemento(){
		ResponseEntity<ElementoResponseRest> response = elementoService.search(); 
		return response;
	}
	
	@PostMapping("/elemento")
	public ResponseEntity <ElementoResponseRest> save(@RequestBody Elemento elemento){
		ResponseEntity<ElementoResponseRest> response = elementoService.save(elemento);
		return response;
	}

	@GetMapping("/elemento/{id}")
	public ResponseEntity <ElementoResponseRest> searchElementoById(@PathVariable Long id){
		ResponseEntity<ElementoResponseRest> response = elementoService.searchById(id);
		return response;
	}	
	
	@PutMapping("/elemento/{id}")
	public ResponseEntity<ElementoResponseRest> update(@RequestBody Elemento elemento, @PathVariable Long id) {
		
		ResponseEntity<ElementoResponseRest> response = elementoService.update(elemento, id);
		return response;
	}
	
	@DeleteMapping("/elemento/{id}")
	public ResponseEntity<ElementoResponseRest> delete(@PathVariable Long id) {
		
		ResponseEntity<ElementoResponseRest> response = elementoService.deleteById(id);
		return response;
	}
	
	
	
}
