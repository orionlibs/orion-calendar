package io.github.orionlibs.orion_calendar.datetime.tasks;

import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.DateTimeRules;

public class HashCodeForDateTimeTask
{
    public static int run(DateTime dateTime)
    {
        DateTimeRules.isValid(dateTime);
        int defaultPrimeNumberForHashing = 31;
        int hash = 3;
        hash = defaultPrimeNumberForHashing * hash + dateTime.getDate().hashCode();
        hash = defaultPrimeNumberForHashing * hash + dateTime.getTime().hashCode();
        return defaultPrimeNumberForHashing * hash + dateTime.getZoneID().hashCode();
    }
}