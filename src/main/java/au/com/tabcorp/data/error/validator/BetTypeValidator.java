package au.com.tabcorp.data.error.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BetTypeValidator  implements ConstraintValidator<BetType, String> {

    List<String> validBetTypes = Arrays.asList("WIN", "PLACE", "TRIFECTA", "DOUBLE","QUADDIE");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return validBetTypes.contains(value);

    }

	
}
