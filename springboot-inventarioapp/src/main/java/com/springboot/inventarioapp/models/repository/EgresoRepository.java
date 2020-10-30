package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventarioapp.models.entity.Egreso;


public interface EgresoRepository extends CrudRepository<Egreso, Integer> {

}
