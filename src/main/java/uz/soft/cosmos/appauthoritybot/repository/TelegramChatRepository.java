package uz.soft.cosmos.appauthoritybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.soft.cosmos.appauthoritybot.entity.TelegramChat;

import java.util.UUID;

public interface TelegramChatRepository extends JpaRepository<TelegramChat, UUID> {
    Boolean existsByChatId(Long chatId);
    TelegramChat findByChatId(Long chatId);
}
