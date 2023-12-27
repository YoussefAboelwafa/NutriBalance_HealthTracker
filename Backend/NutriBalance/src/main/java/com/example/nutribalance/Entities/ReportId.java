package com.example.nutribalance.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@Data
@NoArgsConstructor
public class ReportId implements Serializable {
    private Long user;
    private Long coach;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportId reportId= (ReportId) o;
        return Objects.equals(coach, reportId.coach) &&
                Objects.equals(user, reportId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, user);
    }
}
