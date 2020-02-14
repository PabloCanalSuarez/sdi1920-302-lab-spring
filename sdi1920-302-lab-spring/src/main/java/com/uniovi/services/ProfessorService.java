package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorService {

	private List<Professor> professorList = new LinkedList<Professor>();

	@PostConstruct
	public void init() {
		professorList.add(new Professor(1L, "1", "Paco", "Cañal Suárez", "ASR"));
		professorList.add(new Professor(1L, "1", "Carmina", "Castaño García", "SDI"));
		professorList.add(new Professor(1L, "1", "Juan", "García Menéndez", "ASW"));
		professorList.add(new Professor(1L, "1", "Julia", "Álvarez Núñez", "DLP"));
	}

	public List<Professor> getProfessor() {
		return this.professorList;
	}

	public Professor getProfessor(Long id) {
		return professorList.stream().filter(prof -> prof.getId().equals(id)).findFirst().get();
	}

	public void addProfessor(Professor professor) {
		if(professor.getId() == null) {
			professor.setId(professorList.get(professorList.size()-1).getId()+1);
		}
		professorList.add(professor);
	}

	public void deleteProfessor(Long id) {
		professorList.removeIf(prof -> prof.getId().equals(id));
	}

}
