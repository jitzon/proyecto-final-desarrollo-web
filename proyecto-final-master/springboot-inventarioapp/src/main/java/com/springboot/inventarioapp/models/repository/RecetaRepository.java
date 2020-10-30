package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;


import com.springboot.inventarioapp.models.entity.Receta;


public interface RecetaRepository extends CrudRepository<Receta, Integer> {

}
