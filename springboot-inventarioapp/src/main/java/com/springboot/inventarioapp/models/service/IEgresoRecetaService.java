package com.springboot.inventarioapp.models.service;

import java.util.List;

import com.springboot.inventarioapp.models.entity.EgresoReceta;

public interface IEgresoRecetaService {
	
	public List<EgresoReceta> listarTodos();
	public void guardar(EgresoReceta egreso);
	public EgresoReceta buscarPorId(int id);
	public void eliminar(int id);
	
}
