package fr.ensim.interop.introrest.controller;


import fr.ensim.interop.introrest.model.telegram.Joke;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import fr.ensim.interop.introrest.model.telegram.Joke;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class JokeRestController {

    private Map<Integer, Joke> JokeDB = new ConcurrentHashMap<>();
    private AtomicInteger fakeSequence = new AtomicInteger(0);

    Random generator = new Random();
    Object[] values = JokeDB.values().toArray();
    Object randomValue = values[generator.nextInt(values.length)];
    String titleJoke = "Noisette"+new Random().nextInt();

    RestTemplate restTemplate = new RestTemplate();

    Joke j = new Joke();


    /*Joke j = new Joke();
        j.setId
        j.setTitle("Noisette");
        j.setText("Que dit une noisette quand elle tombe dans l’eau ? Je me noix.");
    *//*
    Joke joke = restTemplate.postForObject("http://127.0.0.1:9090/jokes",j, Joke.class);
*//*
    System.out.println("POST => "+ joke.getId());

    joke = restTemplate.getForObject("http://127.0.0.1:9090/jokes/{id}", Joke.class, joke.getId());
    System.out.println("GET => "+joke);
*/
    @PostMapping("/jokes")
    public ResponseEntity<Joke> creerJoke(@RequestBody Joke Joke) {
        // name obligatoire, non vide, non blanc)
        if(!StringUtils.hasText(Joke.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        // Si l’équipe existe déjà, le endpoint devra retourner le code http adapté
        if(JokeDB.values().stream().anyMatch(e -> e.getTitle().equals(Joke.getTitle()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Si l’équipe existe déjà, le endpoint devra retourner le code http adapté
        if(JokeDB.keySet().stream().anyMatch(id -> id == Joke.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if(Joke.getId() == null) {
            Joke.setId(fakeSequence.incrementAndGet());
        }
        Joke.setTitle(Joke.getTitle());
        JokeDB.put(Joke.getId(), Joke);

        System.out.println("creerJoke -> "+Joke);

        return ResponseEntity.ok().body(Joke);
    }

    @GetMapping("/jokes/{id}")
    public ResponseEntity<Joke> sendJoke(@PathVariable("id") int id) {
        if(JokeDB.containsKey(id)) {
            return ResponseEntity.ok(JokeDB.get(id));
        }

        return ResponseEntity.notFound().build();
    }
}
