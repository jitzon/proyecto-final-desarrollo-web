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


import com.springboot.inventarioapp.models.entity.Receta;

import com.springboot.inventarioapp.models.entity.EgresoReceta;
import com.springboot.inventarioapp.models.service.IEgresoRecetaService;
import com.springboot.inventarioapp.models.service.IRecetaService;


@Controller
@RequestMapping("/views/egresosReceta")
public class EgresoRecetaController {

	@Autowired
	private IEgresoRecetaService egresoRecetaService;
	

	@Autowired
	private IRecetaService recetaService;

	
	
	@GetMapping("/")
	public String listarEgresoRecetas(Model model) {
		List<EgresoReceta> listadoEgresoRecetas = egresoRecetaService.listarTodos();


		model.addAttribute("titulo", "Lista de Egresos");
		model.addAttribute("egresosReceta", listadoEgresoRecetas);

		return "/views/egresosReceta/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		EgresoReceta egresoReceta = new EgresoReceta();
		List<Receta> listRecetas = recetaService.listarTodos();

		model.addAttribute("titulo", "Formulario: Nuevo Egreso Receta");
		model.addAttribute("egresosReceta", egresoReceta);
		model.addAttribute("recetas", listRecetas);
		return "/views/egresosReceta/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute EgresoReceta egresoReceta, BindingResult result,
			Model model, RedirectAttributes attribute) {
		List<Receta> listRecetas = recetaService.listarTodos();

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Egreso Receta");
			model.addAttribute("egresosReceta", egresoReceta);
			model.addAttribute("recetas", listRecetas);
			
			System.out.println("Existieron errores en el formulario");			
			return "/views/egresosReceta/frmCrear";
		}

		egresoRecetaService.guardar(egresoReceta);
		System.out.println("EgresoReceta registrado con exito!");
		attribute.addFlashAttribute("success", "EgresoReceta registrado con exito!");
		return "redirect:/views/egresosReceta/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idEgresoReceta, Model model, RedirectAttributes attribute) {
			
		EgresoReceta egresoReceta = null;
		
		if (idEgresoReceta > 0) {
			egresoReceta = egresoRecetaService.buscarPorId(idEgresoReceta);
			
			if (egresoReceta == null) {
				System.out.println("Error: El ID del egresoReceta no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del egresoReceta no existe!");
				return "redirect:/views/egresosReceta/";
			}
		}else {
			System.out.println("Error: Error con el ID del EgresoReceta");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del egresoReceta");
			return "redirect:/views/egresosReceta/";
		}
		
		List<Receta> listRecetas = recetaService.listarTodos();

		model.addAttribute("titulo", "Formulario: Editar EgresoReceta");
		model.addAttribute("egresosRecetas", egresoReceta);
		model.addAttribute("recetas", listRecetas);
		

		return "/views/egresosReceta/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idEgresoReceta, RedirectAttributes attribute) {

		EgresoReceta egresoReceta = null;
		
		if (idEgresoReceta > 0) {
			egresoReceta = egresoRecetaService.buscarPorId(idEgresoReceta);
			
			if (egresoReceta == null) {
				System.out.println("Error: El ID del egresoReceta no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del egresoReceta no existe!");
				return "redirect:/views/egresosReceta/";
			}
		}else {
			System.out.println("Error: Error con el ID del egresoReceta");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del EgresoReceta!");
			return "redirect:/views/egresosReceta/";
		}		
		
		egresoRecetaService.eliminar(idEgresoReceta);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/egresosReceta/";
	}

}