package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class GetTimeZoneOffsetStringFromUTCTask
{
    public static String run(DateTime datetime)
    {
        ZonedDateTime zonedDateTime = CalendarService.convertDatetimeToZonedDatetime(datetime);
        TimeZone timezone = TimeZone.getTimeZone(datetime.getZoneID());
        int timezoneOffset = timezone.getOffset(zonedDateTime.toInstant().getEpochSecond());
        int a = Math.abs(timezoneOffset / 3_600_000);
        int b = Math.abs((timezoneOffset / 60_000) % 60);
        String offset = String.format("%02d:%02d", a, b);
        return (timezoneOffset >= 0 ? "+" : "-") + offset;
    }
}