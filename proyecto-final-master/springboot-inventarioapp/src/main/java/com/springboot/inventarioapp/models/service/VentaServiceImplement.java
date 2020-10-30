package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.Venta;
import com.springboot.inventarioapp.models.repository.VentaRepository;

@Service
public class VentaServiceImplement implements IVentaService {
	
	@Autowired
	private VentaRepository ventaRepository;
	@Override
	public List<Venta> listarTodos() {
		
		
		return (List<Venta>) ventaRepository.findAll();
	}

	@Override
	public void guardar(Venta venta) {
		
		ventaRepository.save(venta);
		
}

	@Override
	public Venta buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}



}
