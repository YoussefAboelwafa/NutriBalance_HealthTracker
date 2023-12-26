package com.example.nutribalance.dto;

import com.example.nutribalance.Entities.Notification;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NotificationDto {

    private Long notificationId;
    private String message;
    private LocalDateTime date;
    private int type;

    // Other fields as needed

    public static NotificationDto fromEntity(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setNotificationId(notification.getNotification_id());
        dto.setMessage(notification.getMessage());
        dto.setDate(notification.getDate());
        dto.setType(notification.getType());
        // Map other fields as needed
        return dto;
    }

    // Getters and setters
}

