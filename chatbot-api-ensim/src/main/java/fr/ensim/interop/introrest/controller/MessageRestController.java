package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.TextMessage;
import fr.ensim.interop.introrest.model.telegram.ApiResponseTelegram;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.ensim.interop.introrest.model.telegram.Message;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// pour histoires droles ref equipe
@RestController
public class MessageRestController {
	
	@Value("${telegram.api.url}")
	private String telegramApiUrl;

	@Value("${telegram.bot.id}")
	private String telegramBotId;
	
	//Opérations sur la ressource Message

	@GetMapping("/message/{id}/{text}")
	public ResponseEntity<Message> sendMessage(@PathVariable("id") int id, @PathVariable("text") String message) throws URISyntaxException {
		URI uri = new URI(telegramApiUrl + telegramBotId + "/sendMessage?chat_id=" + id + "&text=" + message);
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(uri, ApiResponseTelegram.class);

		return ResponseEntity.ok().build();
	}
}
