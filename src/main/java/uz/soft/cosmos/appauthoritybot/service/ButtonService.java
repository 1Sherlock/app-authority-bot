package uz.soft.cosmos.appauthoritybot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

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


}
