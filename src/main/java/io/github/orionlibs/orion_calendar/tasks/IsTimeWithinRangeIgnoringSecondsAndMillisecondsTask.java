package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.time.Time;
import java.time.LocalTime;

public class IsTimeWithinRangeIgnoringSecondsAndMillisecondsTask
{
    public static boolean run(Time timeToCheck, Time time1, Time time2)
    {
        LocalTime localTimeToCheck = timeToCheck.toLocalTime();
        localTimeToCheck = localTimeToCheck.minusSeconds(localTimeToCheck.getSecond());
        localTimeToCheck = localTimeToCheck.minusNanos(localTimeToCheck.getNano());
        LocalTime localTime1 = time1.toLocalTime();
        localTime1 = localTime1.minusSeconds(localTime1.getSecond());
        localTime1 = localTime1.minusNanos(localTime1.getNano());
        LocalTime localTime2 = time2.toLocalTime();
        localTime2 = localTime2.minusSeconds(localTime2.getSecond());
        localTime2 = localTime2.minusNanos(localTime2.getNano());
        if(CalendarService.isTimeBeforeOrEqualsAnother(time1, time2))
        {
            return localTimeToCheck.equals(localTime1)
                            || localTimeToCheck.equals(localTime2)
                            || (localTimeToCheck.isAfter(localTime1)
                            && localTimeToCheck.isBefore(localTime2));
        }
        else
        {
            return localTimeToCheck.equals(localTime1) ||
                            localTimeToCheck.equals(localTime2) ||
                            (localTimeToCheck.isBefore(localTime1)
                                            && localTimeToCheck.isAfter(localTime2));
        }
    }
}