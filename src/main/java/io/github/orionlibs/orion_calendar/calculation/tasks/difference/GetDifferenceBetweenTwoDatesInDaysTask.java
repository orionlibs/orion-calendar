package io.github.orionlibs.orion_calendar.calculation.tasks.difference;

import io.github.orionlibs.orion_assert.InvalidArgumentException;
import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;
import java.time.temporal.ChronoUnit;

public class GetDifferenceBetweenTwoDatesInDaysTask
{
    public static long run(Date date1, Date date2) throws InvalidArgumentException
    {
        DateRules.isValid(date1);
        DateRules.isValid(date2);
        return ChronoUnit.DAYS.between(date1.toLocalDate(), date2.toLocalDate());
    }
}
