package com.sprhib.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sprhib.model.Team;

@Component
public class TeamValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Team.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Team t = (Team) target;
		if (t.getRating() < 0 || t.getRating() > 10) {
			errors.rejectValue("rating", "team.rating.range");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "name", "team.name.empty");
	}
}
