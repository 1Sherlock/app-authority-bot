package uz.soft.cosmos.appauthoritybot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.soft.cosmos.appauthoritybot.service.BotService;

import java.util.List;

@Component
public class AuthorityBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Autowired
    BotService botService;

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){

        } else if (update.hasCallbackQuery()){

        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

}
