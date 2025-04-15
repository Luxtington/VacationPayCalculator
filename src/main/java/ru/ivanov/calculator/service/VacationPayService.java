package ru.ivanov.calculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ivanov.calculator.exception.CalculatorException;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacationPayService {

    private final DayChecker dayChecker;

    public Integer calculateVacationPay(Integer averageSalary, Integer vacationDaysQuantity,
                                        LocalDate vacationStartDate) throws CalculatorException {

        checkParamsForCorrectness(averageSalary, vacationDaysQuantity);

        if (vacationStartDate == null){
            return vacationPay(averageSalary, vacationDaysQuantity);
        }

        int copyOfVacationDays = vacationDaysQuantity;
        LocalDate tmpDate = vacationStartDate;
        int finalDaysQuantity = vacationDaysQuantity;

        while (copyOfVacationDays > 0){
            boolean flag = dayChecker.isHoliday(tmpDate) || dayChecker.isWeekend(tmpDate);
            finalDaysQuantity = flag ? finalDaysQuantity - 1 : finalDaysQuantity;
            tmpDate = tmpDate.plusDays(1);
            copyOfVacationDays--;
        }

        return vacationPay(averageSalary, finalDaysQuantity);
    }

    private int vacationPay(Integer averageSalary, Integer vacationDaysQuantity){
        return (int) ((averageSalary / 29.3) * vacationDaysQuantity);
    }

    private void checkParamsForCorrectness(Integer averageSalary, Integer vacationDaysQuantity) throws CalculatorException {
        if (averageSalary < 0){
            log.error("Некорректный размер З/П: {}", averageSalary);
            throw new CalculatorException("Incorrect value of salary: average salary < 0");
        }
        
        if (vacationDaysQuantity < 0){
            log.error("Некорректная продолжительность отпуска: {}", vacationDaysQuantity);
            throw new CalculatorException("Incorrect value of days in vacation: vacation's duration < 0");
        }
    }
}
