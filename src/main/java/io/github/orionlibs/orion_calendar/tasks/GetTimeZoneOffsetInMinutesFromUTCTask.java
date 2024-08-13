package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.date.InvalidDateException;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.time.InvalidTimeException;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class GetTimeZoneOffsetInMinutesFromUTCTask
{
    public static void main(String[] args) throws InvalidDateException, InvalidTimeException
    {
        /*if(ZoneId.of("GB").getRules().isDaylightSavings(Instant.now()))
        {
            return TimeZone.getTimeZone("GB").getDSTSavings() / Calendar.millisecondsInAnHour;
        }
        else
        {
            return 0;
        }*/
        System.out.println(run(DateTime.of("2021-10-01", "10:09:00")));
        System.out.println(run(DateTime.of("2021-11-01", "10:09:00")));
    }


    public static int run(DateTime datetime)
    {
        ZonedDateTime zonedDateTime = CalendarService.convertDatetimeToZonedDatetime(datetime);
        TimeZone timezone = TimeZone.getTimeZone(datetime.getZoneID());
        int timezoneOffset = timezone.getOffset(zonedDateTime.toInstant().getEpochSecond());
        int a = Math.abs(timezoneOffset / 3_600_000);
        int b = Math.abs((timezoneOffset / 60_000) % 60);
        return (a * 60) + b;
    }
}