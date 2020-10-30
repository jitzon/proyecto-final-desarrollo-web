package com.springboot.inventarioapp.models.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recetas")
public class Receta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="idproducto1")
	private Producto producto1;
	
	private int cantidad1;
	
	@ManyToOne
	@JoinColumn(name="idproducto2")
	private Producto producto2;
	
	private int cantidad2;
	
	@ManyToOne
	@JoinColumn(name="idproducto3")
	private Producto producto3;
	
	private int cantidad3;
	
	@ManyToOne
	@JoinColumn(name="idproducto4")
	private Producto producto4;
	
	private int cantidad4;
	
	@ManyToOne
	@JoinColumn(name="idproducto5")
	private Producto producto5;
	
	private int cantidad5;
	
	private float precio;

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Date getFecha() {
		return fecha;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	public Producto getProducto1() {
		return producto1;
	}




	public void setProducto1(Producto producto1) {
		this.producto1 = producto1;
	}




	public int getCantidad1() {
		return cantidad1;
	}




	public void setCantidad1(int cantidad1) {
		this.cantidad1 = cantidad1;
	}




	public Producto getProducto2() {
		return producto2;
	}




	public void setProducto2(Producto producto2) {
		this.producto2 = producto2;
	}




	public int getCantidad2() {
		return cantidad2;
	}




	public void setCantidad2(int cantidad2) {
		this.cantidad2 = cantidad2;
	}




	public Producto getProducto3() {
		return producto3;
	}




	public void setProducto3(Producto producto3) {
		this.producto3 = producto3;
	}




	public int getCantidad3() {
		return cantidad3;
	}




	public void setCantidad3(int cantidad3) {
		this.cantidad3 = cantidad3;
	}




	public Producto getProducto4() {
		return producto4;
	}




	public void setProducto4(Producto producto4) {
		this.producto4 = producto4;
	}




	public int getCantidad4() {
		return cantidad4;
	}




	public void setCantidad4(int cantidad4) {
		this.cantidad4 = cantidad4;
	}




	public Producto getProducto5() {
		return producto5;
	}




	public void setProducto5(Producto producto5) {
		this.producto5 = producto5;
	}




	public int getCantidad5() {
		return cantidad5;
	}




	public void setCantidad5(int cantidad5) {
		this.cantidad5 = cantidad5;
	}

	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio=precio;
	}



	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", producto1=" + producto1
				+ ", cantidad1=" + cantidad1 + ", producto2=" + producto2 + ", cantidad2=" + cantidad2 + ", producto3="
				+ producto3 + ", cantidad3=" + cantidad3 + ", producto4=" + producto4 + ", cantidad4=" + cantidad4
				+ ", producto5=" + producto5 + ", cantidad5=" + cantidad5 + ", precio=" + precio + "]";
	}
	
	

}
