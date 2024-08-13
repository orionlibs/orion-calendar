package io.github.orionlibs.orion_calendar.calculation.tasks;

import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.CalendarRules;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.DateTimeRules;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class AddYearsToDatetimeTask
{
    public static DateTime run(DateTime datetime, int numberOfYears, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        LocalDate localDate = datetime.toLocalDate().plus(numberOfYears, ChronoUnit.YEARS);
        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(),
                        localDate.getMonthValue(),
                        localDate.getDayOfMonth(),
                        datetime.getTime().getHours(),
                        datetime.getTime().getMinutes(),
                        datetime.getTime().getSeconds(),
                        datetime.getTime().getMilliseconds() * Calendar.nanosecondsInAMillisecond);
        return DateTime.of(ZonedDateTime.of(localDateTime, zone));
    }
}