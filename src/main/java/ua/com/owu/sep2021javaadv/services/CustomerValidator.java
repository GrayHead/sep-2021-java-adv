package ua.com.owu.sep2021javaadv.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.owu.sep2021javaadv.models.Customer;

@Component
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports");
        return clazz.equals(Customer.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validate");
        Customer customer = (Customer) target;
        if (customer.getAge() < 0) {
//            throw new RuntimeException();
            errors.rejectValue("age", "400", "age is not normal");

        }

    }
}
