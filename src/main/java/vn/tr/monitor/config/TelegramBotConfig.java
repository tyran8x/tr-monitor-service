//package vn.tr.monitor.config;
//
//import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TelegramBotConfig {
//
//	final InstanceRepository repository;
//
//	@Value("${telegram.bot.token}")
//	private String botToken;
//
//	@Value("${telegram.bot.chat-id}")
//	private String chatId;
//
//	public TelegramBotConfig(InstanceRepository repository) {
//		this.repository = repository;
//	}
//
//}
