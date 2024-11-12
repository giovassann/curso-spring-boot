package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @GetMapping({"/greetings/{name}/{lastname}", "/hola/{name}/{lastname}"})
    public String greeting(@PathVariable("name") String name, @PathVariable("lastname") String lastname){
        return "Greetings " + name + " " + lastname;
    }
}
