//package vn.tr.monitor.notifier;
//
//import de.codecentric.boot.admin.server.domain.entities.Instance;
//import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
//import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
//import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
//import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.client.RestTemplate;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//public class TelegramNotifier extends AbstractEventNotifier {
//
//	private final String botToken;
//	private final String chatId;
//	private final RestTemplate restTemplate;
//
//	public TelegramNotifier(String botToken, String chatId, InstanceRepository repository) {
//		super(repository);
//		this.botToken = botToken;
//		this.chatId = chatId;
//		this.restTemplate = new RestTemplate();
//	}
//
//	@Override
//	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
//		return Mono.fromRunnable(() -> {
//			if (event instanceof InstanceStatusChangedEvent statusChangeEvent) {
//				String status = statusChangeEvent.getStatusInfo().getStatus();
//				String serviceName = instance.getRegistration().getName();
//				String serviceUrl = instance.getRegistration().getServiceUrl();
//
//				String message = createMessage(serviceName, status, serviceUrl);
//				sendTelegramMessage(message);
//			}
//		});
//	}
//
//	private void sendTelegramMessage(String message) {
//		String url = String.format("https://api.telegram.org/bot%s/sendMessage", botToken);
//		String payload = String.format("chat_id=%s&text=%s&parse_mode=Markdown", chatId, message);
//
//		try {
//			restTemplate.postForObject(url + "?" + payload, null, String.class);
//		} catch (Exception e) {
//			log.error("Failed to send Telegram notification", e);
//		}
//	}
//
//	private String createMessage(String serviceName, String status, String serviceUrl) {
//		String statusEmoji = "❔";
//
//		if ("UP".equals(status)) {
//			statusEmoji = "✅";
//		} else if ("DOWN".equals(status)) {
//			statusEmoji = "❌";
//		} else if ("OFFLINE".equals(status)) {
//			statusEmoji = "⚫️";
//		} else if ("UNKNOWN".equals(status)) {
//			statusEmoji = "❓";
//		}
//
//		return String.format("%s *%s* (%s)\n*Status*: %s", statusEmoji, serviceName, serviceUrl, status);
//	}
//}
