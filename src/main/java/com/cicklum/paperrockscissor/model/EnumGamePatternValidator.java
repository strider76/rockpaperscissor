package com.cicklum.paperrockscissor.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumGamePatternValidator implements ConstraintValidator<EnumGameOptionPattern, Enum<?>> {

    private Pattern pattern;

    @Override
    public void initialize(EnumGameOptionPattern enumPattern) {

        try {
	    pattern = Pattern.compile(enumPattern.regexp());
	} catch (PatternSyntaxException e) {
	    throw new IllegalArgumentException("Given regex is invalid", e);
	}

    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
	if (value==null)
	    return true;
	Matcher matcher = pattern.matcher(value.name());
	return matcher.matches();
    }
}
