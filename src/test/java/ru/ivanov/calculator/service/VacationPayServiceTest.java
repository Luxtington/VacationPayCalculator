package ru.ivanov.calculator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ivanov.calculator.exception.CalculatorException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class VacationPayServiceTest {

    private DayChecker dayChecker;
    private VacationPayService vacationPayService;

    @BeforeEach
    void setUp(){
        dayChecker = new DayChecker();
        vacationPayService = new VacationPayService(dayChecker);
    }


    @Test
    @DisplayName("Тестирование подсчёта отпускных с указанием только З/П и количества дней отпуска")
    void testCalculationOfVacationPayWithoutStartDateOfVacation() throws CalculatorException {
        int actualRes = vacationPayService.calculateVacationPay(50_000, 14, null);
        Assertions.assertEquals(23_890, actualRes);
    }

    @Test
    @DisplayName("Тестирование подсчёта отпускных с указанием З/П, количества дней отпуска, дату выхода в отпуск")
    void testCalculationOfVacationPayWithStartDateOfVacation() throws CalculatorException {
        LocalDate testDate = LocalDate.of(2025, Month.APRIL, 30);
        int actualRes = vacationPayService.calculateVacationPay(150_000, 14, testDate);
        Assertions.assertEquals(40_955, actualRes);
    }
}