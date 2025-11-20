<strong> **DO NOT DISTRIBUTE OR PUBLICLY POST SOLUTIONS TO THESE LABS. MAKE ALL FORKS OF THIS REPOSITORY WITH SOLUTION CODE PRIVATE. PLEASE REFER TO THE STUDENT CODE OF CONDUCT AND ETHICAL EXPECTATIONS FOR COLLEGE OF INFORMATION TECHNOLOGY STUDENTS FOR SPECIFICS. ** </strong>

# WESTERN GOVERNORS UNIVERSITY 
## D387 – ADVANCED JAVA
---

## B.  Modify the Landon Hotel scheduling application for localization and internationalization by doing the following:

## 1.   Install the Landon Hotel scheduling application in your integrated development environment (IDE). Modify the Java classes of application to display a welcome message by doing the following:

## a.  Build resource bundles for both English and French (languages required by Canadian law). Include a welcome message in the language resource bundles.




### I created the following Resource Bundles for both English and French:
        
            
### translation_en_us.properties
            ```
                hello=Hello!
                welcome=Welcome to the Landon Hotel!
            ```
### translation_fr_ca.properties
            ```
                hello=Bonjour!
                welcome=Bienvenue à l'hôtel Landon
            ```      
---


## b.Display the welcome message in both English and French by applying the resource bundles using a different thread for each language.


---


### I created internationalization.WelcomeController.java:
                ```
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
                ```

### I also created internationalization.WelcomeMessage.java:

                    ```
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
                    ```

### I modified D387SampleCodeApplication.java, LINES 15-23:
                    ```
                    // I Created threads for the Welcome Message in French and English
                            WelcomeMessage welcomeMessageEnglish = new WelcomeMessage(Locale.US);
                            Thread englishWelcomeThread = new Thread(welcomeMessageEnglish);
                            englishWelcomeThread.start();

                            WelcomeMessage welcomeMessageFrench = new WelcomeMessage(Locale.CANADA_FRENCH);
                            Thread frenchWelcomeThread = new Thread(welcomeMessageFrench);
                            frenchWelcomeThread.start();
                    ```



## B2.   Modify the front end to display the price for a reservation in currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€) on different lines. <br>
## Note: It is not necessary to convert the values of the prices. <br>





### I modified  app.component.ts, lines 57-58
```
// B2 - Code that adds the CAD/EUR "prices"
this.rooms.forEach( room => { room.priceCAD = room.price; room.priceEUR = room.price})
```


### app.component.ts, lines 111-113
        ```
        // B2 - Code that adds the CAD/EUR "prices"
          priceCAD:string;
          priceEUR:string;
        ```

### app.component.html, lines 79-81
```
        < !-- B2 > - This Code adds CAD and EUR price listings -- >
        < strong > Price: CA${{room.priceCAD}} < /strong > < br >
        < strong > Price: EUR€{{room.priceEUR}} < /strong > < br >

```



## B3.  Display the time for an online live presentation held at the Landon Hotel by doing the following:
## a.  Write a Java method to convert times between eastern time (ET), mountain time (MT), and coordinated universal time (UTC) zones.



### I created TZConvert.java
        ```
        package edu.wgu.d387_sample_code.internationalization;
        
        import org.springframework.web.bind.annotation.CrossOrigin;
        import java.time.*;
        import java.time.format.DateTimeFormatter;
        
        @CrossOrigin(origins = "http://localhost:4200")
        public class TZConvert {
            public static String getTime() {
                ZonedDateTime time = ZonedDateTime.now();
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        
                ZonedDateTime est = time.withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime mst = time.withZoneSameInstant(ZoneId.of("America/Denver"));
                ZonedDateTime utc = time.withZoneSameInstant(ZoneId.of("UTC"));
        
                String times = est.format(timeFormat) + "EST, " + mst.format(timeFormat) + "MST, " + utc.format(timeFormat) + "UTC";
        
                return times;
            }
        }
        ```


## b3a.  Use the time zone conversion method from part B3a to display a message stating the time in all three times zones in hours and minutes for an online, live presentation held at the Landon Hotel. The times should be displayed as ET, MT, and UTC.


### I created TZConvert.java 
        ```
        package edu.wgu.d387_sample_code.internationalization;
        
        import org.springframework.web.bind.annotation.CrossOrigin;
        import java.time.*;
        import java.time.format.DateTimeFormatter;
        
        @CrossOrigin(origins = "http://localhost:4200")
        public class TZConvert {
            public static String getTime() {
                ZonedDateTime time = ZonedDateTime.now();
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        
                ZonedDateTime est = time.withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime mst = time.withZoneSameInstant(ZoneId.of("America/Denver"));
                ZonedDateTime utc = time.withZoneSameInstant(ZoneId.of("UTC"));
        
                String times = est.format(timeFormat) + "EST, " + mst.format(timeFormat) + "MST, " + utc.format(timeFormat) + "UTC";
        
                return times;
            }
        }
        ```
---


## b3b.  Use the time zone conversion method from part B3a to display a message stating the time in all three times zones in hours and minutes for an online, live presentation held at the Landon Hotel. The times should be displayed as ET, MT, and UTC.


---


### I created TZConvertController.java:
```
package edu.wgu.d387_sample_code.internationalization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TZConvertController {

    @GetMapping("/presentation")
    public ResponseEntity<String> announcePresentation() {
        String announcement = "ATTENTION: There is a presentation beginning at: " + TZConvert.getTime();
        return new ResponseEntity<String>(announcement, HttpStatus.OK);
    }
}
```

### I inserted this code in app.compontent.ts, LINES 23-24:
```
// B3B - Code to announce the presentation w/ time zone conversions
  announcePresentation$!: Observable<string>
```
###  I inserted this code in  app.component.ts, LINES 45-46
```
// B3B - This code adds the conference announcement
this.announcePresentation$ = this.httpClient.get(this.baseURL + '/presentation', {responseType: 'text'} )
```
### I inserted this code in app.component.html, LINES 45-46:
```
<!-- B3B - This code  adds presentation announcement -->
      <div class="scene" id="presentation">
        <h1>{{announcePresentation$ | async}}</h1>
      </div><br><br>
```

