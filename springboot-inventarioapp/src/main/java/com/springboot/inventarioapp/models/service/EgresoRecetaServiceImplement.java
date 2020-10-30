package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.EgresoReceta;
import com.springboot.inventarioapp.models.repository.EgresoRecetaRepository;

@Service
public class EgresoRecetaServiceImplement implements IEgresoRecetaService {
	
	@Autowired
	private EgresoRecetaRepository egresoRepository;
	
	@Override
	public List<EgresoReceta> listarTodos() {
		return (List<EgresoReceta>) egresoRepository.findAll();
	}

	@Override
	public void guardar(EgresoReceta egreso) {
		egresoRepository.save(egreso);
	}

	@Override
	public EgresoReceta buscarPorId(int id)  {		
		return egresoRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id){		
		egresoRepository.deleteById(id);
	}

}
