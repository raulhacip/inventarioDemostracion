package com.practica.inventario.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="elemento" )
public class Elemento implements Serializable{
	
	private static final long serialVersionUID = -7176940607314306539L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	

	@Column(name = "elem_serial", nullable = false)
	private String serial;
	
	@Column(name = "elem_nombre", nullable = false)
	private String nombre;
	
	@Column(name = "elem_descripcion", nullable = false)
	private String descripcion;

	@Column(name = "elem_fechacompra", nullable = false)
	private Timestamp fechaCompra;
	
	
	@Column(name = "elem_valorcompra", nullable = false)
	private float valorCompra;
	
	@Column(name = "elem_depreciacion", nullable = false)
	private float depreciacion;

	@Column(name = "elem_estado", nullable = false)
	private String estado;
	
	




	@Override
	public String toString() {
		return "Elemento [id=" + id + ", serial=" + serial + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", fechaCompra=" + fechaCompra + ", valorCompra=" + valorCompra + ", depreciacion=" + depreciacion+ ", estado=" + estado
				+ "]";
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Timestamp fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public float getDepreciacion() {
		return depreciacion;
	}

	public void setDepreciacion(float depreciacion) {
		this.depreciacion = depreciacion;
	}
	
	public void calcularDepreciacion(float porcentaje) {
		if (this.fechaCompra != null) {
			float operacion = 0;
			int anios = calcularAniosCalendar();
			System.out.println("Anios -->" + anios);
			if (anios > 0) {
				operacion = (anios * porcentaje) * this.valorCompra;
				this.depreciacion = this.valorCompra - operacion;
				System.out.println("operacion -->" + operacion);
				System.out.println("depreciacion -->" + this.depreciacion);
			}
		}
		 
	}
	
	
    public int calcularAniosCalendar() {
        Calendar inicio = Calendar.getInstance();
        inicio.setTimeInMillis(this.fechaCompra.getTime());        
        Calendar actual = Calendar.getInstance();
        int diferencia = actual.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        if (diferencia != 0 && inicio.get(Calendar.DAY_OF_YEAR) > actual.get(Calendar.DAY_OF_YEAR)) {
            diferencia--;
        }
        return diferencia;
    }    
	
	
	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}	
	
}
