package com.example.nutribalance.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
public class ChatID implements Serializable {
    private Long user;
    private Long coach;
    private LocalDateTime localDateTime;
    // Convenience method to convert LocalDateTime to Timestamp
//    public void setLocalDateTime(LocalDateTime localDateTime) {
//        this.localDateTime = Timestamp.valueOf(localDateTime);
//    }
//
//    // Convenience method to convert Timestamp to LocalDateTime
//    public LocalDateTime getLocalDateTime() {
//        return localDateTime.toLocalDateTime();
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatID chatID= (ChatID) o;
        return Objects.equals(coach, chatID.coach) &&
                Objects.equals(user, chatID.user)&&
                Objects.equals(localDateTime, chatID.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, user,localDateTime);
    }
}
