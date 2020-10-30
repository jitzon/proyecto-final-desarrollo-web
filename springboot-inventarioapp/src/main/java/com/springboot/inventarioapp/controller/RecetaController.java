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
import com.springboot.inventarioapp.models.service.IRecetaService;
import com.springboot.inventarioapp.models.service.IProductoService;


@Controller
@RequestMapping("/views/recetas")
public class RecetaController {

	@Autowired
	private IRecetaService recetaService;

	@Autowired
	private IProductoService productoService;


	
	@GetMapping("/")
	public String listarIngresos(Model model) {
		List<Receta> listadoRecetas = recetaService.listarTodos();

		model.addAttribute("titulo", "Lista de recetas");
		model.addAttribute("recetas", listadoRecetas);

		return "/views/recetas/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		Receta receta = new Receta();
		List<Producto> listProductos = productoService.listarTodos();

		model.addAttribute("titulo", "Formulario: Nuevo Receta");
		model.addAttribute("receta", receta);
		model.addAttribute("productos", listProductos);
		return "/views/recetas/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Receta receta, BindingResult result,
			Model model, RedirectAttributes attribute) {
		List<Producto> listProductos = productoService.listarTodos();
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo receta");
			model.addAttribute("receta", receta);
			model.addAttribute("productos", listProductos);
			
			System.out.println("Existieron errores en el formulario");			
			return "/views/recetas/frmCrear";
		}

		recetaService.guardar(receta);
		System.out.println("Receta registrada con exito!");
		attribute.addFlashAttribute("success", "Receta registrado con exito!");
		return "redirect:/views/recetas/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idReceta, Model model, RedirectAttributes attribute) {
			
		Receta receta = null;
		
		if (idReceta > 0) {
			receta = recetaService.buscarPorId(idReceta);
			
			if (receta == null) {
				System.out.println("Error: El ID del receta no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del receta no existe!");
				return "redirect:/views/recetas/";
			}
		}else {
			System.out.println("Error: Error con el ID del receta");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del receta");
			return "redirect:/views/recetas/";
		}
		
		List<Producto> listProductos = productoService.listarTodos();
		

		model.addAttribute("titulo", "Formulario: Editar Receta");
		model.addAttribute("receta", receta);
		model.addAttribute("productos", listProductos);
		

		return "/views/recetas/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idReceta, RedirectAttributes attribute) {

		Receta receta = null;
		
		if (idReceta > 0) {
			receta = recetaService.buscarPorId(idReceta);
			
			if (receta == null) {
				System.out.println("Error: El ID del receta no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del receta no existe!");
				return "redirect:/views/recetas/";
			}
		}else {
			System.out.println("Error: Error con el ID del receta");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del receta!");
			return "redirect:/views/recetas/";
		}		
		
		recetaService.eliminar(idReceta);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/recetas/";
	}

}
