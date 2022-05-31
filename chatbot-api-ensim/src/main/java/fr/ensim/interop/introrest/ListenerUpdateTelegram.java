package fr.ensim.interop.introrest;

import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.Update;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ListenerUpdateTelegram implements CommandLineRunner {
	@Value("${telegram.api.url}")
	private String telegramApiUrl;

	@Value("${telegram.bot.id}")
	private String telegramBotId;

	private AtomicInteger offsetValue = new AtomicInteger(0);

	//pooling
	@Override
	public void run(String... args) throws Exception {
		Logger.getLogger("ListenerUpdateTelegram").log(Level.INFO, "Démarage du listener d'updates Telegram...");

		Timer timer = new Timer ("Timer");

		TimerTask task = new TimerTask() {
			@SneakyThrows
			@Override
			public void run() {
				// Operation de pooling pour capter les evenements Telegram
				URI uri = new URI(telegramApiUrl + telegramBotId + "/getUpdates?offset=" + offsetValue);
				offsetValue.getAndDecrement();
				// call updates
				RestTemplate restTemplate = new RestTemplate();
				ApiResponseUpdateTelegram responseEntity = restTemplate.getForObject(
						uri,
						ApiResponseUpdateTelegram.class
				);
				// retrieve operations to do
				for (Update result : responseEntity.getResult()) {
					System.out.println(result.getMessage().getText());
				}
				// launch operation
			}
		};

		long delay = 1000;
		new Timer().schedule(task, delay,1000);

	}
}
