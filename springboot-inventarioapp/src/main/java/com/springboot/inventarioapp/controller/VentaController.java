package com.springboot.inventarioapp.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.inventarioapp.models.entity.Producto;
import com.springboot.inventarioapp.models.entity.Tienda;

import com.springboot.inventarioapp.models.entity.Venta;
import com.springboot.inventarioapp.models.service.IVentaService;
import com.springboot.inventarioapp.models.service.IProductoService;
import com.springboot.inventarioapp.models.service.IRecetaService;


@Controller
@RequestMapping("/views/ventas")
public class VentaController {

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IRecetaService recetaService;

	@Autowired
	private IProductoService productoService;

	
	@GetMapping("/")
	public String listarVentas(Model model) {
		List<Venta> listadoVentas= ventaService.listarTodos();

		model.addAttribute("titulo", "Reporte de Ventas");
		model.addAttribute("ventas", listadoVentas);

		return "/views/ventas/listar";
	}

	@GetMapping("/existencias")
	public String listarExistencias(Model model) {
		List<Producto> listadoProducto= productoService.listarTodos();

		model.addAttribute("titulo", "Reporte de Existencias");
		model.addAttribute("productos", listadoProducto);

		return "/views/ventas/listarExistencias";
	}

}
