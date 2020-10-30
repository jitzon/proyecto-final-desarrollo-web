package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.Egreso;
import com.springboot.inventarioapp.models.repository.EgresoRepository;

@Service
public class EgresoServiceImplement implements IEgresoService {
	
	@Autowired
	private EgresoRepository egresoRepository;
	
	@Override
	public List<Egreso> listarTodos() {
		return (List<Egreso>) egresoRepository.findAll();
	}

	@Override
	public void guardar(Egreso egreso) {
		egresoRepository.save(egreso);
	}

	@Override
	public Egreso buscarPorId(int id)  {		
		return egresoRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id){		
		egresoRepository.deleteById(id);
	}

}
