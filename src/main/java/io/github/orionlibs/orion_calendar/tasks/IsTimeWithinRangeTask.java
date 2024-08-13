package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.time.Time;
import java.time.LocalTime;

public class IsTimeWithinRangeTask
{
    public static boolean run(Time timeToCheck, Time time1, Time time2)
    {
        LocalTime localTimeToCheck = timeToCheck.toLocalTime();
        LocalTime localTime1 = time1.toLocalTime();
        LocalTime localTime2 = time2.toLocalTime();
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