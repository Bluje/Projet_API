package fr.ensim.interop.introrest.client;

import fr.ensim.interop.introrest.model.telegram.Joke;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class ClientRestTest {
	
	public static void main(String[] args) {
		String titleJoke = "Noisette" + new Random().nextInt();

		RestTemplate restTemplate = new RestTemplate();

		/*Joke j = new Joke();
		j.setTitle("Noisette");
		j.setText("Que dit une noisette quand elle tombe dans l’eau ? Je me noix.");
		Joke joke = restTemplate.postForObject("http://127.0.0.1:9090/jokes",j, Joke.class);
		System.out.println("POST => "+ joke.getId());

		joke = restTemplate.getForObject("http://127.0.0.1:9090/jokes/{id}", Joke.class, joke.getId());
		System.out.println("GET => "+joke);*/
		//Vous pouvez faire des tests d'appels d'API ici
	}
}
