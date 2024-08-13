package io.github.orionlibs.orion_calendar.calculation.tasks.difference;

import io.github.orionlibs.orion_assert.InvalidArgumentException;
import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class GetDifferenceBetweenTwoDatesInMillisecondsTask
{
    public static long run(Date date1, Date date2) throws InvalidArgumentException
    {
        DateRules.isValid(date1);
        DateRules.isValid(date2);
        return TimeUnit.DAYS.toMillis(ChronoUnit.DAYS.between(date1.toLocalDate(), date2.toLocalDate()));
    }
}
