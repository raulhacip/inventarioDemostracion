package com.practica.inventario.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



import com.practica.inventario.model.Elemento;
import com.practica.inventario.response.ElementoResponseRest;
import com.practica.inventario.service.IElementoService;

class ElementoRestControllerTest {

	@InjectMocks
	ElementoRestController elementoRestController;
	
	@Mock
	private IElementoService elementoService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Elemento elemento= new Elemento();
		elemento.setNombre("Computador Lenovo");
		elemento.setDescripcion("computador lenovo mouse y teclado");
		elemento.setEstado("ACTIVO");
		elemento.setFechaCompra(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
		elemento.setSerial("001");
		elemento.calcularDepreciacion((float)0.04);
		when(elementoService.save(any(Elemento.class))).thenReturn(
					new ResponseEntity<ElementoResponseRest>(HttpStatus.OK));
		ResponseEntity<ElementoResponseRest> response = elementoRestController.save(elemento);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		//System.out.println("EJECUTADO..." + response.getBody().toString());
	}
	

	@Test
	public void updateTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Elemento elemento= new Elemento();
		elemento.setNombre("Computador Lenovo");
		elemento.setDescripcion("computador lenovo mouse y teclado");
		elemento.setEstado("ACTIVO");
		elemento.setFechaCompra(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
		elemento.setSerial("001");
		elemento.setId(1);
		elemento.calcularDepreciacion((float)0.04);
		
		when(elementoService.update(any(Elemento.class),(long) 1)).thenReturn(
					new ResponseEntity<ElementoResponseRest>(HttpStatus.OK));
		ResponseEntity<ElementoResponseRest> response = elementoRestController.save(elemento);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	
	
}
