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
        jokes.put(1, new Joke(1,"Noisette", "Que dit une noisette quand elle tombe dans l’eau ? Je me noix",2));
        jokes.put(2, new Joke(2, "Tiers", "Quel est le comble du mathématicien ? C'est de se faire piquer sa moitié par un tiers dans un car.", 8));
        jokes.put(3, new Joke(3, "Boomerang", "Comment est-ce qu'on appelle un boomerang qui ne revient pas ? Un chat disparu.", 9));
        jokes.put(4, new Joke(4, "Que dit un homme complexe à une femme réelle", "Viens danser! (dans C)", 10));
        jokes.put(5, new Joke(5, "Bebe", "Qu'est-ce qui est pire qu'un bébé dans une poubelle ? Un bébé dans deux poubelles.", 5));
        jokes.put(6, new Joke(6, "Chewing Gum", "Grâce à quoi peut-on enlever le chewing-gum dans les cheveux ? Le cancer.", 8));
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
