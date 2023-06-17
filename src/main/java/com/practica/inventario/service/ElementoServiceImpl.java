package com.practica.inventario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.inventario.dao.IElementoDao;
import com.practica.inventario.model.Elemento;
import com.practica.inventario.response.ElementoResponseRest;

@Service
public class ElementoServiceImpl implements IElementoService {

	@Autowired 
	IElementoDao elementoDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ElementoResponseRest> search() {
		ElementoResponseRest response = new ElementoResponseRest();
		try {
			List <Elemento> elemento = (List<Elemento>)elementoDao.findAll();
			response.getElementoResponse().setElemento(elemento);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		}catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ElementoResponseRest> searchById(Long id) {
		ElementoResponseRest response = new ElementoResponseRest();
		List <Elemento> lista = new ArrayList<>();
		try {
			Optional<Elemento> elemento = elementoDao.findById(id);
			if (elemento.isPresent()) {
				lista.add(elemento.get());
				response.getElementoResponse().setElemento(lista);
				response.setMetadata("Respuesta ok", "00", "registro encontrado");				
			}else {
				response.setMetadata("Respuesta nok", "-1", "No encontrado");
				return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ElementoResponseRest> save(Elemento elemento) {
		ElementoResponseRest response = new ElementoResponseRest();
		List<Elemento> list = new ArrayList<>();
		try {
			
			elemento.calcularDepreciacion((float)0.04);
			elemento.toString();
			
			Elemento elementoguardar = elementoDao.save(elemento);
			if (elementoguardar != null) {
				list.add(elementoguardar);
				response.getElementoResponse().setElemento(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");				
			}else {
				response.setMetadata("Respuesta ok", "-1", "Error guardando");
				return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional
	public ResponseEntity<ElementoResponseRest> update(Elemento elemento, Long id) {
		ElementoResponseRest response = new ElementoResponseRest();
		List<Elemento> list = new ArrayList<>();
		
		try {
			
			Optional<Elemento> elementoSearch = elementoDao.findById(id);
			
			if (elementoSearch.isPresent()) {
				// se procederá a actualizar el registro
				elementoSearch.get().setNombre(elemento.getNombre());
				elementoSearch.get().setDescripcion(elemento.getDescripcion());
				elementoSearch.get().setEstado(elemento.getEstado());
				elementoSearch.get().setFechaCompra(elemento.getFechaCompra());
				elementoSearch.get().setValorCompra(elemento.getValorCompra());
				//elementoSearch.get().setId(elemento.getId());
				elementoSearch.get().setSerial(elemento.getSerial());
				elementoSearch.get().calcularDepreciacion((float)0.04);
				
				Elemento elementoToUpdate = elementoDao.save(elementoSearch.get());
				
				if (elementoToUpdate != null) {
					list.add(elementoToUpdate);
					response.getElementoResponse().setElemento(list);
					response.setMetadata("Respuesta ok", "00", "Categoria actualizada");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
					return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ElementoResponseRest> deleteById(Long id) {
		
		ElementoResponseRest response = new ElementoResponseRest();
		
		try {
			
			elementoDao.deleteById(id);
			response.setMetadata("respuesta ok", "00", "Registro eliminado");
			
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<ElementoResponseRest>(response, HttpStatus.OK);
		
	}
	
	
}
