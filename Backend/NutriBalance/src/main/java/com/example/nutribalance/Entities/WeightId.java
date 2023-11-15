package com.example.nutribalance.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class WeightId implements Serializable {
    private Date date;
    private Long user;

    // Constructors, getters, and setters...

    // Implement equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightId weightId = (WeightId) o;
        return Objects.equals(date, weightId.date) &&
                Objects.equals(user, weightId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, user);
    }
}
