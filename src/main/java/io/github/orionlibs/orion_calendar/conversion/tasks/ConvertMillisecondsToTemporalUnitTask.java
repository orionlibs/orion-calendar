package io.github.orionlibs.orion_calendar.conversion.tasks;

import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.CalendarRules;
import java.time.temporal.ChronoUnit;

public class ConvertMillisecondsToTemporalUnitTask
{
    public static long run(long milliseconds, ChronoUnit unit)
    {
        CalendarRules.isValid(unit);
        int divisor = 1;

        if(unit == ChronoUnit.SECONDS)
        {
            divisor = Calendar.millisecondsInASecond;
        }
        else if(unit == ChronoUnit.MINUTES)
        {
            divisor = Calendar.millisecondsInAMinute;
        }
        else if(unit == ChronoUnit.HOURS)
        {
            divisor = Calendar.millisecondsInAnHour;
        }
        else if(unit == ChronoUnit.DAYS)
        {
            divisor = Calendar.millisecondsInADay;
        }
        else if(unit == ChronoUnit.WEEKS)
        {
            divisor = (Calendar.millisecondsInADay * Calendar.daysInAWeek);
        }
        else if(unit == ChronoUnit.MONTHS)
        {
            divisor = Calendar.millisecondsInAMonth;
        }
        else if(unit == ChronoUnit.YEARS)
        {
            divisor = (Calendar.millisecondsInADay * Calendar.daysInAYear);
        }

        return milliseconds / divisor;
    }
}
