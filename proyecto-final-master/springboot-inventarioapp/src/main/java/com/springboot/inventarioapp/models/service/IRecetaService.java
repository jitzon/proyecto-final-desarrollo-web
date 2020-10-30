package com.springboot.inventarioapp.models.service;

import java.util.List;

import com.springboot.inventarioapp.models.entity.Receta;

public interface IRecetaService {
	
	public List<Receta> listarTodos();
	public void guardar(Receta ingreso);
	public Receta buscarPorId(int id);
	public void eliminar(int id);
	
}
