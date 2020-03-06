package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;
import com.uniovi.entities.User;
import com.uniovi.services.ProfessorService;

@Component
public class AddProfessorFormValidator implements Validator {
	
	@Autowired
	private ProfessorService professorService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor user = (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		if (user.getNombre().length() < 5 || user.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (user.getApellido().length() < 5 || user.getApellido().length() > 24) {
			errors.rejectValue("apellido", "Error.signup.lastName.length");
		}
		if (!user.getCategoria().contains("Cat")) {
			errors.rejectValue("categoria", "Error.signup.cat");
		}
	}
}
