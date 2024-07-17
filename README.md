<strong> **DO NOT DISTRIBUTE OR PUBLICLY POST SOLUTIONS TO THESE LABS. MAKE ALL FORKS OF THIS REPOSITORY WITH SOLUTION CODE PRIVATE. PLEASE REFER TO THE STUDENT CODE OF CONDUCT AND ETHICAL EXPECTATIONS FOR COLLEGE OF INFORMATION TECHNOLOGY STUDENTS FOR SPECIFICS. ** </strong>

# WESTERN GOVERNORS UNIVERSITY 
## D387 – ADVANCED JAVA
<div>
B.  Modify the Landon Hotel scheduling application for localization and internationalization by doing the following:

1.   Install the Landon Hotel scheduling application in your integrated development environment (IDE). Modify the Java classes of application to display a welcome message by doing the following:

a.  Build resource bundles for both English and French (languages required by Canadian law). Include a welcome message in the language resource bundles.
</div>
<pre>
I created the following Resource Bundles for both English and French:

            translation_en_us.properties
                hello=Hello!
                welcome=Welcome to the Landon Hotel!

            translation_fr_ca.properties
                hello=Bonjour!
                welcome=Bienvenue à l'hôtel Landon
</pre>


<div>
b.Display the welcome message in both English and French by applying the resource bundles using a different thread for each language.
</div>

<pre>
I created internationalization.WelcomeController.java:

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

I also created internationalization.WelcomeMessage.java:

package edu.wgu.d387_sample_code.internationalization;
import java.util.Locale;
import java.util.ResourceBundle;


public class WelcomeMessage implements Runnable{

    Locale locale;

    // Constructor
    public WelcomeMessage(Locale locale) {
        this.locale = locale;
    }

    public String getWelcomeMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("translation", locale);
        return bundle.getString("welcome");
     }
    @Override
    public void run() {
        System.out.println(
                "Thread verification: " + getWelcomeMessage() +
                        ", ThreadID: " + Thread.currentThread().getId()
        );
    }
}

I modified D387SampleCodeApplication.java, LINES 15-23:

// I Created threads for the Welcome Message in French and English
		WelcomeMessage welcomeMessageEnglish = new WelcomeMessage(Locale.US);
		Thread englishWelcomeThread = new Thread(welcomeMessageEnglish);
		englishWelcomeThread.start();

		WelcomeMessage welcomeMessageFrench = new WelcomeMessage(Locale.CANADA_FRENCH);
		Thread frenchWelcomeThread = new Thread(welcomeMessageFrench);
		frenchWelcomeThread.start();

</pre>
