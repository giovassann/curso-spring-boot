package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromeRestController {

    /**
     * Endpoint para validar si una palabra es palindroma usando StringBuilder.
     * @param word Es la palabra a validar.
     * @return Un mensaje que indica si la palabra es palindroma o no.
     */
    @GetMapping({"/is-palindrome/{word}", "/palindrome/{word}", "/palindromo/{word}"})
    public String isPalindrome(@PathVariable("word") String word){
        StringBuilder sb = new StringBuilder();
        sb.append(word);
        sb.reverse();
        if(sb.toString().equals(word)){
            return "The word "+word+" is indeed palindrome";
        }else{
            return "The word "+word+" isn't indeed palindrome";
        }
    }
}
