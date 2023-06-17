package com.practica.inventario.dao;

import org.springframework.data.repository.CrudRepository;

import com.practica.inventario.model.Elemento;

public interface IElementoDao extends CrudRepository<Elemento,Long>  {

}
