package com.springboot.inventarioapp.models.service;

import java.util.List;
import com.springboot.inventarioapp.models.entity.Venta;
public interface IVentaService {
	
	public  List<Venta> listarTodos();
	// Estos metodos no se utilizaran, removerlos
	public void guardar (Venta venta);
	public Venta buscarPorId(Long id);
	public void eliminar (Long id);

}
