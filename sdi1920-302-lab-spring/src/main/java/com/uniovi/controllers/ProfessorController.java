package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;
import com.uniovi.validators.AddProfessorFormValidator;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private AddProfessorFormValidator addProfValidator;
	
	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorList", professorService.getProfessor());
		return "professor/list";
	}
	
	@RequestMapping(value = "/professor/add")
	public String getProfessor(Model model) {
		model.addAttribute("professor", new Professor());
		return "professor/add";
	}

	@RequestMapping(value="/professor/add", method=RequestMethod.POST)
	public String setProfessor(@Validated Professor prof, BindingResult result) {
		addProfValidator.validate(prof, result);
		if (result.hasErrors()) {
			return "professor/add";
		}	
		professorService.addProfessor(prof);
		return "redirect:/professor/list";
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorService.getProfessor(id));
		return "professor/details";
	}
	
	@RequestMapping("/professor/delete/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		professorService.deleteProfessor(id);
		return "redirect:/professor/list";
	}
	

	/**
	 * Muestra los datos del profesor a editar.
	 */
	@RequestMapping(value = "/professor/edit", method = RequestMethod.POST)
	public String getEdit(Model model, @ModelAttribute Professor professor) {
		model.addAttribute("professor", professorService.getProfessor(professor.getId()));
		System.out.println(professor.toString());
		System.out.println(professorService.getProfessor(professor.getId()).toString());
		return "professor/edit";
	}

	/**
	 * Introduce los datos editados en la BD y cambia a /professor/details.
	 */
	@RequestMapping(value = "/professor/edit")
	public String setEdit(Model model, @ModelAttribute Professor professor) {
		model.addAttribute("professor", professorService.getProfessor(professor.getId()));
		professor.setId(professor.getId());
		professorService.addProfessor(professor);
		return "redirect:/professor/details/" + professor.getId();
	}
}
