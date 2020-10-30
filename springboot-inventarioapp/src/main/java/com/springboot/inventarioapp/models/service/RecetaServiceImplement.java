package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.Receta;
import com.springboot.inventarioapp.models.repository.RecetaRepository;

@Service
public class RecetaServiceImplement implements IRecetaService {
	
	@Autowired
	private RecetaRepository recetaRepository;
	
	@Override
	public List<Receta> listarTodos() {
		return (List<Receta>) recetaRepository.findAll();
	}

	@Override
	public void guardar(Receta receta) {
		recetaRepository.save(receta);
	}

	@Override
	public Receta buscarPorId(int id)  {		
		return recetaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id){		
		recetaRepository.deleteById(id);
	}

}
