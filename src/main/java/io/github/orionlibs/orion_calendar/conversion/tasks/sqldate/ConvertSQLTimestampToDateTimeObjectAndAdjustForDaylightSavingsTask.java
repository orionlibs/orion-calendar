package io.github.orionlibs.orion_calendar.conversion.tasks.sqldate;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.SQLTimestamp;
import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.time.Time;
import java.sql.Timestamp;

public class ConvertSQLTimestampToDateTimeObjectAndAdjustForDaylightSavingsTask
{
    public static DateTime run(Timestamp SQLDate)
    {
        if(SQLDate != null)
        {
            int timezoneOffsetInHours = CalendarService.getDaylightSavingsHoursToAdd();
            SQLTimestamp datetime = CalendarService.addHoursToDatetime(SQLDate, timezoneOffsetInHours);
            Date date = Date.of(datetime.getYear() + 1900, datetime.getMonth() + 1, datetime.getDate());
            Time time = Time.of(datetime.getHours(), datetime.getMinutes(), datetime.getSeconds());
            return DateTime.of(date, time);
        }
        else
        {
            return null;
        }
    }
}