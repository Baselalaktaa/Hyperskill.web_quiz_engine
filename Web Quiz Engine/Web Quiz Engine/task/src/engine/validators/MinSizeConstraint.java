package engine.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ArrayLengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSizeConstraint {
    int length() default  0;
    String message() default "too small";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
