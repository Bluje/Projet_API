package fr.ensim.interop.introrest.controller;


import fr.ensim.interop.introrest.model.telegram.Joke;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import fr.ensim.interop.introrest.model.telegram.Joke;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class JokeRestController {

    static  Map<Integer, Joke> jokes;
    static{
        jokes = new HashMap<>();
        jokes.put(1, new Joke(1,"Noisette", "Que dit une noisette quand elle tombe dans l’eau ? Je me noix","0/5"));
        jokes.put(1, new Joke(1, "How do you comfort a JavaScript bug?", "You console it.", "0"));
        jokes.put(2, new Joke(2, "Why did the child component have such great self-esteem?", "Because its parent kept giving it props!", "0"));
        jokes.put(3, new Joke(3, "Why do C# and Java developers keep breaking their keyboards", "Because they use a strongly typed language", "0"));
    }
    Random generator = new Random();
    Object[] values = jokes.values().toArray();
    Joke randomJoke;


    @GetMapping("/jokes")
    public ResponseEntity<Joke> sendJoke() {
        randomJoke = (Joke) values[generator.nextInt(values.length)];
        return ResponseEntity.ok(randomJoke);
    }
}
