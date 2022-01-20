package uz.soft.cosmos.appauthoritybot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.soft.cosmos.appauthoritybot.bot.BotConstant;

import java.util.ArrayList;
import java.util.List;

@Service
public class ButtonService {
    public InlineKeyboardButton generateButton(String textUz, String textRu, String callBackData, String language) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(language.equals("ru") ? textRu : textUz);
        button.setCallbackData(callBackData + language);
        return button;
    }

    public InlineKeyboardButton generateButtonWithUrl(String textUz, String textRu, String language, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(language.equals("ru") ? textRu : textUz);
        button.setUrl(url);
        return button;
    }

    public InlineKeyboardMarkup languageButton() {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        row.add(generateButton(BotConstant.UZBEK, BotConstant.UZBEK, "language#", "uz"));
        row.add(generateButton(BotConstant.RUSSIAN, BotConstant.RUSSIAN, "language#", "ru"));

        rows.add(row);
        replyKeyboardMarkup.setKeyboard(rows);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboard enterNumber(String language) {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton(language.equals("ru") ? BotConstant.SHARENUMBERRU : BotConstant.SHARENUMBERUZ);
        keyboardButton.setRequestContact(true);
        row.add(keyboardButton);
        rows.add(row);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup menuButton(String language, Boolean isAdmin) {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();


//        if (isAdmin) {
//
//        }

        row.add(generateButton("Menu1", "Menu1", "menu#", language));
        row.add(generateButton("Menu2", "Menu2", "menu#", language));

        row1.add(generateButton(BotConstant.SETTINGSUZ, BotConstant.SETTINGSRU, "settings#", language));

        rows.add(row);
        rows.add(row1);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }
}
