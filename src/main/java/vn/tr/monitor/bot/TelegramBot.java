//package vn.tr.monitor.bot;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//@Slf4j
//@Component
//public class TelegramBot extends TelegramLongPollingBot {
//
//	private final String botUsername;
//
//	public TelegramBot(@Value("${telegram.bot.token}") String botToken, @Value("${telegram.bot.username}") String botUsername) {
//		super(botToken);
//		this.botUsername = botUsername;
//	}
//
//	/**
//	 * This method is called whenever an update is received from Telegram. An 'Update' can be a message, a button click, a new member joining, etc.
//	 */
//	@Override
//	public void onUpdateReceived(Update update) {
//		// We only want to handle messages with text
//		if (update.hasMessage() && update.getMessage().hasText()) {
//			String messageText = update.getMessage().getText();
//			long chatId = update.getMessage().getChatId();
//
//			log.info("Received message from {}: {}", chatId, messageText);
//
//			String response;
//			switch (messageText) {
//				case "/start":
//					response = "Hello! I am your amazing bot. How can I help you today?";
//					break;
//				case "/help":
//					response = "You can use /start to greet me. For now, I just echo your messages.";
//					break;
//				default:
//					response = "You said: " + messageText;
//					break;
//			}
//
//			sendMessage(chatId, response);
//		}
//	}
//
//	/**
//	 * A simple method to send a text message to a specific chat.
//	 */
//	public void sendMessage(long chatId, String messageText) {
//		SendMessage message = new SendMessage();
//		message.setChatId(chatId);
//		message.setText(messageText);
//		message.setParseMode("Markdown");
//
//		try {
//			execute(message);
//			log.info("Successfully sent notification to chat {}", chatId);
//		} catch (TelegramApiException e) {
//			log.error("Failed to send message to chat {}: {}", chatId, e.getMessage());
//		}
//	}
//
//	/**
//	 * This method returns the bot's username. It's used by the library to identify the bot.
//	 */
//	@Override
//	public String getBotUsername() {
//		return botUsername;
//	}
//}
