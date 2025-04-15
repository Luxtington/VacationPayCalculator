package ru.ivanov.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.calculator.exception.CalculatorException;
import ru.ivanov.calculator.service.VacationPayService;

import java.time.LocalDate;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final VacationPayService vacationPayService;

    @GetMapping("/calculate")
    public ResponseEntity<Integer> calculate(@RequestParam Integer averageSalary,
                                    @RequestParam Integer daysQuantity,
                                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate vacationStartDay) throws CalculatorException {
        log.info("Приняты данные из запроса: З/П = {}, Количество дней в отпуске = {}, дата выхода в отпуск = {}",
                averageSalary, daysQuantity, vacationStartDay);
        Integer vacationPay = vacationPayService.calculateVacationPay(averageSalary, daysQuantity, vacationStartDay);
        log.info("Подсчитано количество отпускных: {}", vacationPay);
        return new ResponseEntity<>(vacationPay, HttpStatus.OK);
    }
}

/**
 * Рассчитывает сумму отпускных на основе средней зарплаты и количества дней отпуска.
 *
 * @param averageSalary   Средняя зарплата за 12 месяцев (в рублях). Должна быть > 0.
 * @param daysQuantity    Количество дней отпуска. Должно быть > 0.
 * @param vacationStartDay (Опционально) Дата начала отпуска в формате yyyy-MM-dd.
 *                        Если указана, праздники и выходные исключаются из расчета.
 * @return Рассчитанная сумма отпускных (в рублях).
 * @throws CalculatorException Если переданы некорректные данные (например, отрицательная зарплата).
 * @apiExample Примеры запросов:
 *     1. Без даты:
 *        GET /calculate?averageSalary=50000&daysQuantity=14
 *     2. С датой:
 *        GET /calculate?averageSalary=50000&daysQuantity=14&vacationStartDay=2025-04-30
 */
