package io.github.orionlibs.orion_calendar.conversion.tasks.sqldate;

import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.time.Time;
import java.sql.Timestamp;

public class ConvertSQLTimestampToDateTimeObjectTask
{
    public static DateTime run(Timestamp SQLDate)
    {

        if(SQLDate != null)
        {
            Date date = Date.of(SQLDate.getYear() + 1900, SQLDate.getMonth() + 1, SQLDate.getDate());
            Time time = Time.of(SQLDate.getHours(), SQLDate.getMinutes(), SQLDate.getSeconds());
            return DateTime.of(date, time);
        }
        else
        {
            return null;
        }

    }
}