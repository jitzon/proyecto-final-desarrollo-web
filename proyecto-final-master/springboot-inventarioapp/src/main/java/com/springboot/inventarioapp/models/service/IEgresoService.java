package com.springboot.inventarioapp.models.service;

import java.util.List;

import com.springboot.inventarioapp.models.entity.Egreso;

public interface IEgresoService {
	
	public List<Egreso> listarTodos();
	public void guardar(Egreso egreso);
	public Egreso buscarPorId(int id);
	public void eliminar(int id);
	
}
