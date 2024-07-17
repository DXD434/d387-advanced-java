

package edu.wgu.d387_sample_code.internationalization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200") // This is Needed for the front end
@RestController
public class WelcomeController {

    @GetMapping("/welcome")

    public ResponseEntity displayWelcome (@RequestParam("lang") String lang) { // This request the html lang parameter.
        Locale locale = Locale.forLanguageTag(lang); // This creates a locale object based on lang parameter
        WelcomeMessage welcomeMessage = new WelcomeMessage(locale); //This creates a welcomeMessage to pull the corresponding lang
        return new ResponseEntity (welcomeMessage.getWelcomeMessage(), HttpStatus.OK); // this is for the respond
    }
}