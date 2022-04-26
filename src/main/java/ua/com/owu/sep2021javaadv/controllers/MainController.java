package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.sep2021javaadv.models.Customer;
import ua.com.owu.sep2021javaadv.services.CustomerValidator;

@RestController
@AllArgsConstructor
public class MainController {
    private CustomerValidator customerValidator;

    @GetMapping("/")
    public ResponseEntity<String> stringResponseEntity(@RequestBody @Validated Customer customer, BindingResult result) {
        System.out.println("handler");
        result.getAllErrors().forEach(objectError -> System.out.println(objectError.getObjectName() + " " + objectError.getDefaultMessage()));


        return new ResponseEntity<>("good", HttpStatus.OK);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("initBinder");
        binder.addValidators(customerValidator);

    }


}
