package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromeRestController {

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
