package io.github.orionlibs.orion_calendar.date;

import io.github.orionlibs.orion_calendar.date.tasks.EqualsForDateTask;
import io.github.orionlibs.orion_calendar.date.tasks.HashCodeForDateTask;
import io.github.orionlibs.orion_string.StringsService;

class DateInternalService
{
    public static int hashCode(Date date)
    {
        return HashCodeForDateTask.run(date);
    }


    public static boolean equals(Date date, Object other)
    {
        return EqualsForDateTask.run(date, other);
    }


    public static String formatMonthWith2Characters(Date date)
    {
        return StringsService.formatWith2Characters(date.getMonth());
    }


    public static String formatDayWith2Characters(Date date)
    {
        return StringsService.formatWith2Characters(date.getDay());
    }
}