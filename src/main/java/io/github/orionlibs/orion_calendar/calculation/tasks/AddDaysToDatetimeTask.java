package io.github.orionlibs.orion_calendar.calculation.tasks;

import io.github.orionlibs.orion_calendar.CalendarRules;
import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.DateTimeRules;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AddDaysToDatetimeTask
{
    public static DateTime run(DateTime datetime, long numberOfDays, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        if(numberOfDays == 0)
        {
            return DateTime.of(datetime.getDate(), datetime.getTime(), datetime.getZoneID());
        }
        else
        {
            ZonedDateTime zonedDatetime = CalendarService.convertInstantToLocalDate(datetime.toInstant(), zone)
                            .plusDays(numberOfDays)
                            .atStartOfDay(zone);
            return DateTime.of(zonedDatetime);
        }
    }
}