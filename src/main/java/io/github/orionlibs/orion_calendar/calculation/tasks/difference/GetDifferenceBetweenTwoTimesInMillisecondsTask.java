package io.github.orionlibs.orion_calendar.calculation.tasks.difference;

import io.github.orionlibs.orion_assert.InvalidArgumentException;
import io.github.orionlibs.orion_calendar.time.Time;
import io.github.orionlibs.orion_calendar.time.TimeRules;
import java.time.temporal.ChronoUnit;

public class GetDifferenceBetweenTwoTimesInMillisecondsTask
{
    public static long run(Time time1, Time time2) throws InvalidArgumentException
    {
        TimeRules.isValid(time1);
        TimeRules.isValid(time2);
        return ChronoUnit.MILLIS.between(time1.toLocalTime(), time2.toLocalTime());
    }
}
