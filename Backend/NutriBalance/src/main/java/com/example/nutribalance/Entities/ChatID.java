package com.example.nutribalance.entities;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
public class ChatID implements Serializable {
    private Long user;
    private Long coach;
    private LocalDateTime localDateTime;

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
