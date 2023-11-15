package com.example.nutribalance.Entities;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionId implements Serializable {
    private Long user;
    private Long coach;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       SubscriptionId subscriptionId= (SubscriptionId) o;
        return Objects.equals(coach, subscriptionId.coach) &&
                Objects.equals(user, subscriptionId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, user);
    }
}
