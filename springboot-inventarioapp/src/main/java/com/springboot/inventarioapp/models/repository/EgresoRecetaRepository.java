package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventarioapp.models.entity.EgresoReceta;


public interface EgresoRecetaRepository extends CrudRepository<EgresoReceta, Integer> {

}
