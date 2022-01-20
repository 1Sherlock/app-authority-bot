package uz.soft.cosmos.appauthoritybot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.soft.cosmos.appauthoritybot.bot.AuthorityBot;
import uz.soft.cosmos.appauthoritybot.bot.BotConstant;
import uz.soft.cosmos.appauthoritybot.entity.TelegramChat;
import uz.soft.cosmos.appauthoritybot.entity.enums.TelegramChatStatus;
import uz.soft.cosmos.appauthoritybot.repository.TelegramChatRepository;

@Service
public class BotService {

    @Autowired
    AuthorityBot authorityBot;

    @Autowired
    ButtonService buttonService;

    @Autowired
    TelegramChatRepository telegramChatRepository;

    public DeleteMessage deleteTopMessage(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        if (update.hasMessage()) {
            deleteMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            deleteMessage.setMessageId(update.getMessage().getMessageId());
        } else if (update.hasCallbackQuery()) {
            deleteMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }
        return deleteMessage;
    }

    public void welcomeText(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(BotConstant.welcomeTextRu);
        sendMessage.setReplyMarkup(buttonService.languageButton());
        authorityBot.execute(sendMessage);
    }

    public void setLang(Update update, String language) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        boolean isAdmin = update.getCallbackQuery().getMessage().getChatId().equals(273769261L);

        if (telegramChatRepository.existsByChatId(update.getCallbackQuery().getMessage().getChatId())) {
            TelegramChat byChatId = telegramChatRepository.findByChatId(update.getCallbackQuery().getMessage().getChatId());
            if (byChatId.getPhoneNumber() == null || byChatId.getPhoneNumber().length() == 0) {
                byChatId.setLanguage(language);
                byChatId.setStatus(TelegramChatStatus.REGISTRATION);
                telegramChatRepository.save(byChatId);
                sendMessage.setText(language.equals("ru") ? BotConstant.ENTERPHONENUMBERRU : BotConstant.ENTERPHONENUMBERUZ);
                sendMessage.setReplyMarkup(buttonService.enterNumber(language));
            } else {
                byChatId.setLanguage(language);
                telegramChatRepository.save(byChatId);
                sendMessage.setText((language.equals("ru") ? BotConstant.LANGUAGECHANGEDRU + BotConstant.MENUTEXTRU : BotConstant.LANGUAGECHANGEDUZ + BotConstant.MENUTEXTUZ));
                sendMessage.setReplyMarkup(buttonService.menuButton(language, isAdmin));
            }
        } else {
            TelegramChat telegramChat = new TelegramChat(update.getCallbackQuery().getMessage().getChatId(), language, TelegramChatStatus.REGISTRATION, "", "");
            telegramChatRepository.save(telegramChat);
            sendMessage.setText(language.equals("ru") ? BotConstant.ENTERPHONENUMBERRU : BotConstant.ENTERPHONENUMBERUZ);
            sendMessage.setReplyMarkup(buttonService.enterNumber(language));
        }

        authorityBot.execute(sendMessage);
    }
}
