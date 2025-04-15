package ru.ivanov.calculator.util;

import lombok.Getter;

import java.time.Month;

@Getter
public enum Holidays {

    NEW_YEAR(1, Month.JANUARY),
    CHRISTMAS(7, Month.JANUARY),
    DEFENDER_DAY(23, Month.FEBRUARY),
    WOMEN_DAY(8, Month.MARCH),
    LABOR_DAY(1, Month.MAY),
    VICTORY_DAY(9, Month.MAY),
    RUSSIA_DAY(12, Month.JUNE),
    UNITY_DAY(4, Month.NOVEMBER),
    BEFORE_NEW_YEAR(31, Month.DECEMBER);

    private final int day;
    private final Month month;

    Holidays(int day, Month month) {
        this.day = day;
        this.month = month;
    }
}
