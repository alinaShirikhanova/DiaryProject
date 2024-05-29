package org.example.model.utils;

import java.time.LocalDate;

public interface Repeatable {
    LocalDate getClosestDate(LocalDate currentDate);
}
