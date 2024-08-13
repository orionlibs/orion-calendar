package io.github.orionlibs.orion_calendar.datetime;

import io.github.orionlibs.orion_calendar.datetime.tasks.EqualsForDateTimeTask;
import io.github.orionlibs.orion_calendar.datetime.tasks.HashCodeForDateTimeTask;

class DateTimeInternalService
{
    public static int hashCode(DateTime dateTime)
    {
        return HashCodeForDateTimeTask.run(dateTime);
    }


    public static boolean equals(DateTime dateTime, Object other)
    {
        return EqualsForDateTimeTask.run(dateTime, other);
    }
}