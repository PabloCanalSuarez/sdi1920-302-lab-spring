package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository pRepo;

	public List<Professor> getProfessor() {
		List<Professor> listP = new ArrayList<>();
		pRepo.findAll().forEach(listP::add);
		return listP;
	}

	public Professor getProfessor(Long id) {
		return pRepo.findById(id).get();
	}

	public void addProfessor(Professor professor) {
		pRepo.save(professor);
	}

	public void deleteProfessor(Long id) {
		pRepo.deleteById(id);
	}

}
