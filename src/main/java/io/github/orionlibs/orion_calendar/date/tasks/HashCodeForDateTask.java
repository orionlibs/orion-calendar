package io.github.orionlibs.orion_calendar.date.tasks;

import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;

public class HashCodeForDateTask
{
    public static int run(Date date)
    {
        DateRules.isValid(date);
        int defaultPrimeNumberForHashing = 31;
        int hash = 3;
        hash = defaultPrimeNumberForHashing * hash + date.getYear();
        hash = defaultPrimeNumberForHashing * hash + date.getMonth();
        return defaultPrimeNumberForHashing * hash + date.getDay();
    }
}