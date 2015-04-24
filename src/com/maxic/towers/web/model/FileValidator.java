package com.maxic.towers.web.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return DoveFileWrapper.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		DoveFileWrapper file = (DoveFileWrapper) obj;
		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "valid.file");
		}
	}
}