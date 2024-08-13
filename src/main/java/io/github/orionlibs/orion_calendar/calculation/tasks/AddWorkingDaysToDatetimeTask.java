package io.github.orionlibs.orion_calendar.calculation.tasks;

import io.github.orionlibs.orion_calendar.CalendarRules;
import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.DateTimeRules;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AddWorkingDaysToDatetimeTask
{
    public static DateTime run(DateTime datetime, long numberOfDays, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        LocalDate localDate = CalendarService.convertInstantToLocalDate(datetime.toInstant(), zone);
        ZonedDateTime zonedDatetime = null;

        while(numberOfDays > 0)
        {
            zonedDatetime = localDate.plusDays(1).atStartOfDay(zone);
            localDate = zonedDatetime.toLocalDate();

            if(CalendarService.isWeekday(zonedDatetime))
            {
                numberOfDays--;
            }

        }

        return DateTime.of(zonedDatetime);
    }
}