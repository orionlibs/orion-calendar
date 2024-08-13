package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import java.time.ZonedDateTime;

public class IsDateWithinRangeTask
{
    public static boolean run(DateTime dateTimeToCheck, DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetimeToCheck = CalendarService.convertDatetimeToZonedDatetime(dateTimeToCheck);
        ZonedDateTime zonedDatetime1 = CalendarService.convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = CalendarService.convertDatetimeToZonedDatetime(dateTime2);
        if(CalendarService.isDateTimeBeforeOrEqualsAnother(dateTime1, dateTime2))
        {
            return zonedDatetimeToCheck.isEqual(zonedDatetime1)
                            || zonedDatetimeToCheck.isEqual(zonedDatetime2)
                            || (zonedDatetimeToCheck.isAfter(zonedDatetime1)
                            && zonedDatetimeToCheck.isBefore(zonedDatetime2));
        }
        else
        {
            return zonedDatetimeToCheck.isEqual(zonedDatetime1) ||
                            zonedDatetimeToCheck.isEqual(zonedDatetime2) ||
                            (zonedDatetimeToCheck.isBefore(zonedDatetime1)
                                            && zonedDatetimeToCheck.isAfter(zonedDatetime2));
        }
    }
}