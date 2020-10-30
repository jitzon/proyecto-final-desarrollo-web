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
import com.springboot.inventarioapp.models.entity.Receta;

import com.springboot.inventarioapp.models.entity.Egreso;
import com.springboot.inventarioapp.models.entity.EgresoReceta;
import com.springboot.inventarioapp.models.service.IEgresoService;
import com.springboot.inventarioapp.models.service.IEgresoRecetaService;
import com.springboot.inventarioapp.models.service.IProductoService;
import com.springboot.inventarioapp.models.service.IRecetaService;

@Controller
@RequestMapping("/views/egresos")
public class EgresoController {

	@Autowired
	private IEgresoService egresoService;
	
	@Autowired
	private IEgresoRecetaService egresoRecetaService;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IRecetaService recetaService;
	
	
	@GetMapping("/")
	public String listarEgresos(Model model) {
		List<Egreso> listadoEgresos = egresoService.listarTodos();
		List<EgresoReceta> listadoEgresosReceta = egresoRecetaService.listarTodos();

		model.addAttribute("titulo", "Lista de Egresos");
		model.addAttribute("egresos", listadoEgresos);
		model.addAttribute("egresosReceta", listadoEgresosReceta);

		return "/views/egresos/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		Egreso egreso = new Egreso();
		List<Producto> listProductos = productoService.listarTodos();

		model.addAttribute("titulo", "Formulario: Nuevo Ingreso");
		model.addAttribute("egresos", egreso);
		model.addAttribute("productos", listProductos);
		return "/views/egresos/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Egreso egreso, BindingResult result,
			Model model, RedirectAttributes attribute) {
		List<Producto> listProductos = productoService.listarTodos();

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Egreso");
			model.addAttribute("egresos", egreso);
			model.addAttribute("productos", listProductos);
			
			System.out.println("Existieron errores en el formulario");			
			return "/views/egresos/frmCrear";
		}

		egresoService.guardar(egreso);
		System.out.println("Egreso registrado con exito!");
		attribute.addFlashAttribute("success", "Egreso registrado con exito!");
		return "redirect:/views/egresos/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idEgreso, Model model, RedirectAttributes attribute) {
			
		Egreso egreso = null;
		
		if (idEgreso > 0) {
			egreso = egresoService.buscarPorId(idEgreso);
			
			if (egreso == null) {
				System.out.println("Error: El ID del egreso no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del egreso no existe!");
				return "redirect:/views/ingresos/";
			}
		}else {
			System.out.println("Error: Error con el ID del Egreso");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del egreso");
			return "redirect:/views/egresos/";
		}
		
		List<Producto> listProductos = productoService.listarTodos();

		model.addAttribute("titulo", "Formulario: Editar Egreso");
		model.addAttribute("egresos", egreso);
		model.addAttribute("productos", listProductos);
		

		return "/views/egresos/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idEgreso, RedirectAttributes attribute) {

		Egreso egreso = null;
		
		if (idEgreso > 0) {
			egreso = egresoService.buscarPorId(idEgreso);
			
			if (egreso == null) {
				System.out.println("Error: El ID del egreso no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del egreso no existe!");
				return "redirect:/views/egresos/";
			}
		}else {
			System.out.println("Error: Error con el ID del egreso");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del Egreso!");
			return "redirect:/views/egresos/";
		}		
		
		egresoService.eliminar(idEgreso);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/egresos/";
	}

}