package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventarioapp.models.entity.Ingreso;


public interface IngresoRepository extends CrudRepository<Ingreso, Long> {

}
