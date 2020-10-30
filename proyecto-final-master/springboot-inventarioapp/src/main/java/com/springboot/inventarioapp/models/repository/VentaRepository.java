package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;


import com.springboot.inventarioapp.models.entity.Venta;


public interface VentaRepository extends CrudRepository<Venta, Integer> {

}
