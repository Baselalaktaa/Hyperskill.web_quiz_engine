package engine.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArrayLengthValidator implements ConstraintValidator<MinSizeConstraint , String[]> {
    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        return value.length >= MinSizeConstraint.class.getDeclaredFields().length;
    }
}
