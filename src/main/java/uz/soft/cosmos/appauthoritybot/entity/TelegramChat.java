package uz.soft.cosmos.appauthoritybot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.soft.cosmos.appauthoritybot.entity.enums.TelegramChatStatus;
import uz.soft.cosmos.appauthoritybot.entity.template.AbstractEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TelegramChat extends AbstractEntity {
    private Long chatId;

    private String language;

    private TelegramChatStatus status;

    private String fullName;

    private String phoneNumber;
}

